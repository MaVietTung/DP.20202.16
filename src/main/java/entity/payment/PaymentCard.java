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

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }
}
