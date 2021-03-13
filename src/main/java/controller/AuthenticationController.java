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



/**
 * @author
 */

/**
 * Coincidental cohesion, do có phương thức md5(String message) dùng để mã hóa chuỗi theo hàm băm md5
 * không liên quan đến các phương thức khác phục vụ cho mục đích quản lý trong lớp AuthenticationController
 * không phục vụ cho tính năng của lớp này
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
// Common Coupling: truy cáº­p vÃ o dá»¯ liá»‡u static cá»§a lá»›p SessionInformation
    public User getMainUser() throws ExpiredSessionException {
        if (SessionInformation.mainUser == null || SessionInformation.expiredTime == null || SessionInformation.expiredTime.isBefore(LocalDateTime.now())) {
            logout();
            throw new ExpiredSessionException();
        } else return SessionInformation.mainUser.cloneInformation();
    }


//    Content coupling do truy cáº­p trá»±c tiáº¿p vÃ o code cá»§a lá»›p SessionInformation
    public void login(String email, String password) throws Exception {
        try {
            User user = new UserDAO().authenticate(email, md5(password));
            if (Objects.isNull(user)) throw new FailLoginException();
            SessionInformation.mainUser = user;
            SessionInformation.expiredTime = LocalDateTime.now().plusHours(24);
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }
// Common Coupling: Thay Ä‘á»•i dá»¯ liÃªu static cá»§a lá»›p SessionInformation
    public void logout() {
        SessionInformation.mainUser = null;
        SessionInformation.expiredTime = null;
    }

    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */

    private String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Utils.getLogger(Utils.class.getName());
            digest = "";
        }
        return digest;
    }

}
