package shipping_api;

import java.util.List;


public interface IShippingFeeCalculator {

    public int calculate(List orderMediaList, int distance); 
}

