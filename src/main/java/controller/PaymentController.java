package controller;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.cart.Cart;
import entity.payment.CreditCard;
import entity.payment.PaymentCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

//SOLID: Vi phạm OCP vì khi thêm mới phương thức thanh toán Sẽ phải chỉnh sửa code ở đây để
//có thể chọn thêm phương thức
/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 * 
 * @author hieud
 *
 */
//SOLID: Vi pham DIP do phu thuoc cac thong tin cua SessionInfomation
public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private PaymentCard card;

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link String String} represents the input date
	 * @return {@link String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                               the expected format
	 */
	// Control Coupling: tham so date duoc su dung de control luong thuc thi cua phuong thuc getExpirationDate
	// cleanCode Method: add them phuong thuc checkErrorDate
	private String[] checkErrorDate(String date){
		String[] strs = date.split("/");
		if (strs.length != 2) {
			throw new InvalidCardException();
		}
		return strs;
	}

	private String getExpirationDate(String date) throws InvalidCardException {
		String[] strs = checkErrorDate(date);
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

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
//	 * @param cardNumber     - the card number
//	 * @param cardHolderName - the card holder name
//	 * @param expirationDate - the expiration date in the format "mm/yy"
//	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link Map Map} represent the payment result with a
	 *         message.
	 */
	//Data Coupling: Truyen vao cac tham so va phuc vu luong thuc thi
	public Map<String, String> payOrder(int amount, String contents, PaymentCard paymentCard) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
            this.card = paymentCard;
			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.payOrder(paymentCard, amount, contents);

			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have successfully paid the order!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}

	public void emptyCart(){
        SessionInformation.getInstance().cartInstance.emptyCart();
    }
}