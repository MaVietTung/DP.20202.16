package distance_api;

import org.example.DistanceCalculator;


public class DistanceCalculatorAdapter implements IDistanceCalculator {

    private DistanceCalculator distanceCalculator;

    public DistanceCalculatorAdapter() {
        distanceCalculator = new DistanceCalculator();
    }

    public int calculate(String address, String province) {
        return distanceCalculator.calculateDistance(address, province);
    }
}

