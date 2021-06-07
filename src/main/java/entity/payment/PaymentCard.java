package entity.payment;

public class PaymentCard {
    private String cardCode;
    private String owner;
    private String dateExpired;
    //Data COupling: DUng tham so de thuc hien luong thuc thi
    public PaymentCard(String cardCode, String owner, String dateExpired) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
    }
}
