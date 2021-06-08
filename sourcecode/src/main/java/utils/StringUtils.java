package utils;

import common.exception.InvalidCardException;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Calendar;

public class StringUtils {

    /**
     * Validate the input date which should be in the format "mm/yy", and then
     * return a {@link String String} representing the date in the
     * required format "mmyy" .
     *
     * @param date - the {@link String String} represents the input date
     * @return {@link String String} - date representation of the required
     *         format
     * @throws InvalidCardException - if the string does not represent a valid date
     *                              in the expected format
     */
    public static String getExpirationDate(String date) throws InvalidCardException {
        String[] strs = date.split("/");
        if (strs.length != 2) {
            throw new InvalidCardException();
        }

        String expirationDate = null;
        int month = -1;
        int year = -1;

        try {
            month = Integer.parseInt(strs[0]);
            year = Integer.parseInt(strs[1]);
            if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
                throw new InvalidCardException();
            }
            expirationDate = strs[0] + strs[1];

        } catch (Exception ex) {
            throw new InvalidCardException();
        }

        return expirationDate;
    }
}
