package entity.order;

import entity.shipping.DeliveryInfo;

import java.util.List;

public class OrderDB implements OderInterface{
    int id;
    boolean confirm;
    public boolean checkConfirm(){
        return confirm;
    }
    public OrderDB(int id, int shippingFees, int subtotal, int tax,  int total, int deliveryID) {
    }

    @Override
    public List getListOrderMedia() {
        return null;
    }

    @Override
    public int getShippingFees() {
        return 0;
    }

    @Override
    public DeliveryInfo getDeliveryInfo() {
        return null;
    }

    @Override
    public int getSubtotal() {
        return 0;
    }

    @Override
    public int getTax() {
        return 0;
    }

    @Override
    public int getTotal() {
        return 0;
    }
}
