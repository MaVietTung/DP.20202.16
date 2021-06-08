package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
//Singleton: Co the ap dung mau thiet ke Singleton vao lop SessionInfomation de dam bao trong mot phien chi co mot the hien cua cac thong tin mot phien
public class SessionInformation {

    private   User mainUser;
    private   Cart cartInstance = Cart.getInstance();
    private    LocalDateTime expiredTime;
    private static SessionInformation singleton;
    private SessionInformation(){}

    public User getMainUser() {
        return mainUser;
    }

    public Cart getCartInstance() {
        return cartInstance;
    }
    public static SessionInformation getInstance(){
        if (SessionInformation.singleton == null){
            SessionInformation.singleton = new SessionInformation();
        }
        return SessionInformation.singleton;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setCartInstance(Cart cartInstance) {
        this.cartInstance = cartInstance;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }
}
