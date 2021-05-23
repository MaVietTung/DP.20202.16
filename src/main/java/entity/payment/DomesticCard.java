package entity.payment;

public class DomesticCard extends PaymentCard {
    private int issueBank;
    private int cardNUmber;
    private int validFromDate;
    private String cardHolderName;
    public DomesticCard(int issueBank,int cardNUmber,int validFromDate,String cardHolderName){
        this.issueBank = issueBank;
        this.cardNUmber = cardNUmber;
        this.validFromDate = validFromDate;
        this.cardHolderName = cardHolderName;
    }
}
