package controller;

import common.exception.InvalidDeliveryInfoException;

import entity.cart.Cart;
import entity.cart.CartItem;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderItem;
import entity.shipping.DeliveryInfo;
import entity.shipping.ShippingConfigs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import distance_api.DistanceCalculatorAdapter;
import shipping_api.ShippingFeeCalculator;

//SOLID: Vi phạm SRP tồn tại nhiều hơn 1 lý do để thay đổi: Logic place order và các phương thức validate
/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */

// SOLID: VI PHAM NGUYEN LY SRP: Do có nhieu phuong thuc khong dam bao tinh dong goi do co nhieu nhiem vụ

// Concidental cohesion: co nhieu phuong thuc ma muc dich duoc su dung khong lien he mat thiet voi nhau

public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException {
        SessionInformation.getInstance().cartInstance.checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
        return new Order(SessionInformation.getInstance().cartInstance);
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    // Stamp Coupling: Truyen vao cau truc du lieu cua tham so info
    public DeliveryInfo processDeliveryInfo(HashMap info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
        DeliveryInfo deliveryInfo = new DeliveryInfo(
                String.valueOf(info.get("name")),
                String.valueOf(info.get("phone")),
                String.valueOf(info.get("province")),
                String.valueOf(info.get("address")),
                String.valueOf(info.get("instructions")));
        deliveryInfo.setDistanceCalculator(new DistanceCalculatorAdapter());
        deliveryInfo.setShippingFeeCalculator(new ShippingFeeCalculator());
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
    }


    //Các phương thức validate bị coincidental cohesion vì các phương thức này không liên quan gì đến class
    //Cần tách ra thành các hàm trong class xử lý String

    //Control coupling do bên gọi phương thức phải hiểu info gồm những key và value nào,
    //Khi code trong phương thức này thay đổi kéo theo phía gọi phương thức này cũng phải
    //Thay đổi theo
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
        || validateName(info.get("name"))
        || validateAddress(info.get("address"))) return;
        else throw new InvalidDeliveryInfoException();
    }
    //COntrol Coupling: tham so phoneNumber thay doi thi luong thuc hien phuong thuc cung thay doi theo
    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) return false;
        if (!phoneNumber.startsWith("0")) return false;
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    //COntrol Coupling: tham so name thay doi thi luong thuc hien phuong thuc cung thay doi theo
    public boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    //COntrol Coupling: tham so name thay doi thi luong thuc hien phuong thuc cung thay doi theo
    public boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
