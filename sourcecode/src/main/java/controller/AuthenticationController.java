package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import utils.MD5Util;
import utils.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

import static utils.EncodeUtils.md5;


//SOLID:Vi phạm SRP vì class này tồn tại nhiều hơn 1 lý do để thay đổi:
//thay đổi cách thức authentication và thay đổi phương pháp mã hóa mật khẩu
//Clean Class: Chứa phương thức md5 Không liên quan đến class
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
        if (SessionInformation.getInstance().getMainUser() == null || SessionInformation.getInstance().getCartInstance() == null || SessionInformation.getInstance().getExpiredTime().isBefore(LocalDateTime.now())) {
            logout();
            throw new ExpiredSessionException();
        } else return SessionInformation.getInstance().getMainUser().cloneInformation();
    }


//    Content coupling do truy cập trực tiếp vào code của lớp SessionInformation
    public void login(String email, String password) throws Exception {
        try {
           User user = UserDAO.getInstance().authenticate(email, MD5Util.md5(password));
            if (Objects.isNull(user)) throw new FailLoginException();
            SessionInformation.getInstance().setMainUser(user);
            SessionInformation.getInstance().setExpiredTime(LocalDateTime.now().plusHours(24)); ;
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    public void logout() {
        SessionInformation.getInstance().setMainUser(null);
        SessionInformation.getInstance().setExpiredTime(null);
    }
}
