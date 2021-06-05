package views.screen.payment;

import controller.PaymentController;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

public class PaymentScreenHandler extends BaseScreenHandler {

	private static final Logger LOGGER = Utils.getLogger(PaymentScreenHandler.class.getName());

	@FXML
	private Button btnConfirmPayment;

	@FXML
	private ImageView loadingImage;

	private Invoice invoice;

	@FXML
	private Label pageTitle;

	@FXML
	private TextField cardNumber;

	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

    @FXML
    private VBox vboxItems;

    private PaymentInputScreenHandler inputScreenHandler;

    @FXML
    RadioButton methodCreditCart;

	public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath, invoice);
	}

    @Override
	protected void setupData(Object data) throws Exception {
		this.invoice = (Invoice) data;
        setInputScreenHandler(new CreditCardInputScreenHandler(ViewsConfig.INPUT_PAYMENT));
	}

    @Override
	protected void setupFunctionality() throws Exception {
		btnConfirmPayment.setOnMouseClicked(e -> {
			try {
				confirmToPayOrder();
				((PaymentController) getBController()).emptyCart();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
        methodCreditCart.setOnMouseClicked(e -> {
        });
	}

    private void setInputScreenHandler(PaymentInputScreenHandler inputScreenHandler) {
        this.inputScreenHandler = inputScreenHandler;
        updateView();
    }

    private void updateView() {
        vboxItems.getChildren().clear();
        vboxItems.getChildren().add(inputScreenHandler.getContent());
    }

	void confirmToPayOrder() throws IOException{
		String contents = "pay order";
		PaymentController ctrl = (PaymentController) getBController();
        Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, inputScreenHandler.getPaymentCard());

		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, ViewsConfig.RESULT_SCREEN_PATH, response);
		resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreenHandler(homeScreenHandler);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}
}
