package entity.payment;

/**
 * @author
 */
public class CreditCard extends PaymentCard {

    protected int cvvCode;
    //Data COupling: DUng tham so de thuc hien luong thuc thi

    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        super(cardCode, owner, dateExpired);
        this.cvvCode = cvvCode;
    }
}
