package views.screen.payment;

import controller.PaymentController;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	private Label errorText;
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
	private VBox inputLayout;

//	@FXML
//	private TextField securityCode;

	@FXML
	private RadioButton methodCreditCard;
//Stamp
	public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath);
		try {
			setupData(invoice);
			setupFunctionality();
			if(methodCreditCard.isSelected()){
				TextField textField = new TextField();
				textField.setFont(new Font(24));
				Label label = new Label();
				label.setFont(new Font(24));
				label.setText("Card security code:");
				inputLayout.getChildren().addAll(new ArrayList<>(Arrays.asList(label, textField)));
			}
		} catch (IOException ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error("Error when loading resources.");
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error(ex.getMessage());
		}
	}

	protected void setupData(Object dto) throws Exception {
		this.invoice = (Invoice) dto;
	}

	protected void setupFunctionality() throws Exception {
		btnConfirmPayment.setOnMouseClicked(e -> {
			try {
				confirmToPayOrder();
				((PaymentController) getBController()).emptyCart();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});

		methodCreditCard.setOnAction(actionEvent -> {
			if(methodCreditCard.isSelected()){
				TextField textField = new TextField();
				Label label = new Label();
				label.setText("Card security code:");
				inputLayout.getChildren().addAll(new ArrayList<>(Arrays.asList(label, textField)));
			}
		});
	}

   private

	void confirmToPayOrder() throws IOException{
		String contents = "pay order";
		PaymentController ctrl = (PaymentController) getBController();
		Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
				expirationDate.getText(), "1");
		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, ViewsConfig.RESULT_SCREEN_PATH, response);
		resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreenHandler(homeScreenHandler);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}
}