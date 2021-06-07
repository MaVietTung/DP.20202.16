package entity.order;

import entity.shipping.DeliveryInfo;

import java.util.List;

public interface OrderInterface {
    public abstract List getListOrderMedia();
    public abstract int getShippingFees();
    public abstract DeliveryInfo getDeliveryInfo();
    public abstract int getSubtotal();
    public abstract int getTax();
    public abstract int getTotal();
}
