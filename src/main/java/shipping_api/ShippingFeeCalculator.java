package shipping_api;

import java.util.List;


public class ShippingFeeCalculator implements IShippingFeeCalculator {

    public ShippingFeeCalculator() {

    }

    public int calculate(List orderMediaList, int distance) {
        return (int) (distance * 1.2);
    }
}

