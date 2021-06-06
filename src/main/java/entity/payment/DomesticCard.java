package entity.payment;

public class DomesticCard extends PaymentCard {
    int issueBank;

    public DomesticCard(int issueBank, String cardCode, String dateExpired, String cardHolderName) {
        super(cardHolderName, cardCode, dateExpired);
        this.issueBank = issueBank;
    }
}
