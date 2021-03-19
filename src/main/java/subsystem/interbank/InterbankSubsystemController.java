package subsystem.interbank;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public class InterbankSubsystemController {

	private static InterbankPayloadConverter interbankPayloadConverter = new InterbankPayloadConverter();
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	//Data Coupling
	/**
	 * Communicational cohesion, cac phuong thuc refund va payOrder
	 * co cung dau vao va tra ve cung mot dau ra
	 */

	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		return null;
	}
	//Data Coupling: Dung cac tham so de thuc hien luong thuc thi cua minh
	// SOLID: vi pham OCP, neu thay doi phuong thuc thanh toan phai sua code
	// SOLID: vi pham DIP, payOrder phu thuoc vao lop cai dat chi tiet CreditCard
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		String requestPayload = interbankPayloadConverter.convertToRequestPayload(card, amount, contents);
		String responseText = interbankBoundary.query(InterbankConfigs.PROCESS_TRANSACTION_URL, requestPayload);
		return interbankPayloadConverter.extractPaymentTransaction(responseText);
	}

}
