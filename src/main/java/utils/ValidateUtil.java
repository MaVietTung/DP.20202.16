package utils;

import common.exception.InvalidDeliveryInfoException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class ValidateUtil {

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
    public static void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
                || validateName(info.get("name"))
                || validateAddress(info.get("address"))) return;
        else throw new InvalidDeliveryInfoException();
    }
    //COntrol Coupling: tham so phoneNumber thay doi thi luong thuc hien phuong thuc cung thay doi theo
    public static boolean validatePhoneNumber(String phoneNumber) {
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
    public static boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    //COntrol Coupling: tham so name thay doi thi luong thuc hien phuong thuc cung thay doi theo
    public static boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
