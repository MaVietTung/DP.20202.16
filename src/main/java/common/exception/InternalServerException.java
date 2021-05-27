package common.exception;

//Data Coupling
public class InternalServerException extends PaymentException {
    public InternalServerException(String message) {
        super(message);
    }
}
