package subsystem.interbank;

import entity.payment.CreditCard;
import entity.payment.PaymentCard;
import entity.payment.PaymentTransaction;

public class InterbankSubsystemController {

	private static InterbankPayloadConverter interbankPayloadConverter = new InterbankPayloadConverter();
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();
	public PaymentTransaction refund(PaymentCard card, int amount, String contents) {
		return null;
	}
	//Data Coupling: Dung cac tham so de thuc hien luong thuc thi cua minh

	public PaymentTransaction payOrder(PaymentCard card, int amount, String contents) {
		String requestPayload = interbankPayloadConverter.convertToRequestPayload(card, amount, contents);
		String responseText = interbankBoundary.query(InterbankConfigs.PROCESS_TRANSACTION_URL, requestPayload);
		return interbankPayloadConverter.extractPaymentTransaction(responseText);
	}

}
