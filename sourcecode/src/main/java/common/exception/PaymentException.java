package common.exception;;
//Data Coupling
public class PaymentException extends RuntimeException {
	public PaymentException(String message) {
		super(message);
	}
}
