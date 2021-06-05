package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import utils.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;


//SOLID:Vi phạm SRP vì class này tồn tại nhiều hơn 1 lý do để thay đổi:
//thay đổi cách thức authentication và thay đổi phương pháp mã hóa mật khẩu
/**
 * @author
 */
public class AuthenticationController extends BaseController {

    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }

    public User getMainUser() throws ExpiredSessionException {
        if (SessionInformation.getInstance().mainUser == null || SessionInformation.getInstance().expiredTime == null || SessionInformation.getInstance().expiredTime.isBefore(LocalDateTime.now())) {
            logout();
            throw new ExpiredSessionException();
        } else return SessionInformation.getInstance().mainUser.cloneInformation();
    }


//    Content coupling do truy cập trực tiếp vào code của lớp SessionInformation
    public void login(String email, String password) throws Exception {
        try {
           User user = UserDAO.getInstance().authenticate(email, md5(password));
            if (Objects.isNull(user)) throw new FailLoginException();
            SessionInformation.getInstance().mainUser = user;
            SessionInformation.getInstance().expiredTime = LocalDateTime.now().plusHours(24);
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    public void logout() {
        SessionInformation.getInstance().mainUser = null;
        SessionInformation.getInstance().expiredTime = null;
    }

    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
     // CleanCode: 
     //ten bien digest bi trung voi thuoc tinh cua doi tuong md 
     //ten bien md la ten viet tat khong nen dung doi thanh messageDigest
     //ten bien sb la ten viet tat khong nen dung doi thanh stringBuilder
    private String md5(String message) {
        String cipherText = null; 
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(message.getBytes(StandardCharsets.UTF_8));
            // converting byte array to Hexadecimal String
            StringBuilder stringBuilder = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                stringBuilder.append(String.format("%02x", b & 0xff));
            }
            cipherText = stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Utils.getLogger(Utils.class.getName());
            cipherText = "";
        }
        return cipherText;
    }

}
