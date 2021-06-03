package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;
//SOLID: Vi pham OCP: PHAI THAY doi trong class khi sử dụng hàm tính toán khác DistanceCalculator
//SOLID VI pham DIP : Phu thuoc vao cac class nhu Oder
public class DeliveryInfo {

    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;
    //Data COupling: DUng tham so de thuc hien luong thuc thi
    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
    }

    //Stamp Coupling do truyền order nhưng không dùng
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        return (int) (distance * 1.2);
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
}
