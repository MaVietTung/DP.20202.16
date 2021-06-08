package controller;

import common.exception.InvalidDeliveryInfoException;

import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.DeliveryInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

import distance_api.DistanceCalculatorAdapter;
import shipping_api.ShippingFeeCalculator;

import utils.ValidateUtil;

//SOLID: Vi phạm SRP tồn tại nhiều hơn 1 lý do để thay đổi: Logic place order và các phương thức validate
/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */

// SOLID: VI PHAM NGUYEN LY SRP: Do có nhieu phuong thuc khong dam bao tinh dong goi do co nhieu nhiem vụ

// Concidental cohesion: co nhieu phuong thuc ma muc dich duoc su dung khong lien he mat thiet voi nhau
//CLEAN CLASS: class controller chứa các phương thức validate
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
        SessionInformation.getInstance().getCartInstance().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
        return new Order(SessionInformation.getInstance().getCartInstance());
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
        ValidateUtil.validateDeliveryInfo(info);
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
}
