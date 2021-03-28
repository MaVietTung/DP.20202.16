package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 */
//Singleton: Co the ap dung mau thiet ke Singleton vao lop SessionInfomation de dam bao trong mot phien chi co mot the hien cua cac thong tin mot phien
public class SessionInformation {

    public   User mainUser;
    public   Cart cartInstance = new Cart();
    public   LocalDateTime expiredTime;

    private static SessionInformation singleton;
    private SessionInformation(){}

    public static SessionInformation getInstance(){
        if (SessionInformation.singleton == null){
            SessionInformation.singleton = new SessionInformation();
        }
        return SessionInformation.singleton;
    }
}
