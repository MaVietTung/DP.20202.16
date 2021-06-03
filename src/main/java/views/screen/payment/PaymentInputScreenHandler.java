package views.screen.payment;

import entity.payment.PaymentCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;

import java.io.IOException;
import java.util.logging.Logger;

public abstract class PaymentInputScreenHandler extends FXMLScreenHandler {

    private static final Logger LOGGER = Utils.getLogger(PaymentInputScreenHandler.class.getName());

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField holderName;

    @FXML
    private TextField expirationDate;

    @FXML
    private VBox inputBox;

    public TextField getCardNumber() {
        return cardNumber;
    }

    public TextField getHolderName() {
        return holderName;
    }

    public TextField getExpirationDate() {
        return expirationDate;
    }

    //Stamp
    public PaymentInputScreenHandler(String screenPath) throws IOException {
        super(screenPath);
        decorate();
    }

    public abstract PaymentCard getPaymentCard();

    public abstract void decorate();

    public void addField(String title, TextField inputField) {
        Font font = new Font("Calibri", 24.0);
        Label label = new Label();
        label.setText(title);
        label.setFont(font);
        inputBox.getChildren().add(label);
        inputBox.getChildren().add(inputField);
    }
}