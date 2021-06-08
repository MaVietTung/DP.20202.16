package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import common.interfaces.Observable;
import entity.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Singleton: Su dung mau thiet ke Singleton boi vi trong he thon chi can mot the hien cua Cart
//SOLID: vi phạm SRP tồn tại nhiều hơn 1 lý do để thay đổi: ví dụ khi thay đổi cách tính giá
//Functional Cohesion: Cac phuong thuc deu thuc hien cung mot muc dich co quan he mat thiet voi nhau
// Clean code: ten bien cm doi thanh cartIteam, lstItem -> lstCartItem do list
public class Cart {
    private static Cart instance;

    ObservableList<CartItem> lstCartItem =  FXCollections.observableArrayList();

    private Cart() {
        lstCartItem.addAll(new ArrayList<>()) ;
    }

    public static Cart getInstance(){
        if(instance == null) instance = new Cart();
        return instance;
    }

    public void addCartMedia(CartItem cartItem){
        lstCartItem.add(cartItem);
    }

    public void removeCartMedia(CartItem cartItem){
        lstCartItem.remove(cartItem);
    }

    public ObservableList<CartItem> getListMedia(){
        return lstCartItem;
    }

    public void emptyCart(){
        lstCartItem.clear();
    }

    public int getTotalMedia(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cartItem = (CartItem) obj;
            total += cartItem.getQuantity();
        }
        return total;
    }

    public int calSubtotal(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cartItem = (CartItem) obj;
            total += cartItem.getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvailable = true;
        for (Object object : lstCartItem) {
            CartItem cartItem = (CartItem) object;
            int requiredQuantity = cartItem.getQuantity();
            int availQuantity = cartItem.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvailable = false;
        }
        if (!allAvailable) throw new MediaNotAvailableException("Some media not available");
    }

    //Control coupling: do tham số đầu vào là media thay doi thi luong phuong thuc thay doi theo
    public CartItem checkMediaInCart(int id){
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == id) return cartItem;
        }
        return null;
    }
}
