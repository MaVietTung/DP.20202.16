package views.screen.payment;

import entity.payment.CreditCard;
import entity.payment.PaymentCard;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.Utils;

import java.io.IOException;
import java.util.logging.Logger;

public class CreditCardInputScreenHandler extends PaymentInputScreenHandler {

    private static final Logger LOGGER = Utils.getLogger(CreditCardInputScreenHandler.class.getName());

    private TextField cvvCode;

    public CreditCardInputScreenHandler(String screenPath) throws IOException {
        super(screenPath);
    }

    @Override
    public PaymentCard getPaymentCard() {
        return new CreditCard(getCardNumber().getText(), getHolderName().getText(),
                getExpirationDate().getText(), Integer.parseInt(cvvCode.getText()));
    }

    @Override
    public void decorate() {
        cvvCode = new TextField();
        cvvCode.setText("185");
        cvvCode.setFont(new Font("Calibri", 24.0));
        addField("Card security code", cvvCode);
    }
}