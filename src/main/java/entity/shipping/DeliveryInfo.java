package entity.shipping;

import entity.order.Order;
import shipping_api.IShippingFeeCalculator;

import java.util.List;

import distance_api.IDistanceCalculator;

public class DeliveryInfo {

    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected IDistanceCalculator distanceCalculator;
    protected IShippingFeeCalculator shippingFeeCalculator;

    //Data COupling: DUng tham so de thuc hien luong thuc thi
    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
    }

    //Stamp Coupling do truyền order nhưng không dùng
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculate(address, province);
        List orderMediaList = order.getOrderMediaList();
        return shippingFeeCalculator.calculate(orderMediaList, distance);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }

    public void setDistanceCalculator(IDistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public void setShippingFeeCalculator(IShippingFeeCalculator shippingFeeCalculator) {
        this.shippingFeeCalculator = shippingFeeCalculator;
    }
}
