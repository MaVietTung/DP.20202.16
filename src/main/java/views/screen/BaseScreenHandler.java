package views.screen;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import controller.AuthenticationController;
import controller.BaseController;
import controller.PaymentController;
import entity.invoice.Invoice;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Utils;
import views.notification.error.ErrorNotifier;
import views.notification.error.PopupErrorNotifier;
import views.screen.home.HomeScreenHandler;
import views.screen.payment.CreditCardInputScreenHandler;
import views.screen.popup.PopupScreen;


public abstract class BaseScreenHandler extends FXMLScreenHandler {

    private static final Logger LOGGER = Utils.getLogger(BaseScreenHandler.class.getName());


    private Scene scene;
    private BaseScreenHandler prev;
    protected final Stage stage;
    protected HomeScreenHandler homeScreenHandler;
    protected Hashtable<String, String> messages;
    private BaseController bController;
    private ErrorNotifier errorNotifier;

    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        this(stage, screenPath, null);
    }
    
    public BaseScreenHandler(Stage stage, String screenPath, Object data) throws IOException {
        super(screenPath);
        this.stage = stage;
        setErrorNotifier(PopupErrorNotifier.getInstance());

        try {
            setupData(data);
            setupFunctionality();
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            notifyError(ex);
        }
    }

    protected void setupData(Object data) throws Exception {
        if (data == null) {
            setupData();
        }
    };

    protected void setupData() throws Exception {}

    protected void setupFunctionality() throws Exception {};

    protected void notifyError(Exception ex) throws IOException {
        PopupScreen.error(ex.getMessage());
    }

    public void setErrorNotifier(ErrorNotifier errorNotifier) {
        this.errorNotifier = errorNotifier;
    }

    public void setPreviousScreen(BaseScreenHandler prev) {
        this.prev = prev;
    }

    public BaseScreenHandler getPreviousScreen() {
        return this.prev;
    }

    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public void setBController(BaseController bController){
        this.bController = bController;
    }

    public BaseController getBController(){
        return this.bController;
    }

    public void forward(Hashtable messages) {
        this.messages = messages;
    }

    public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
        this.homeScreenHandler = HomeScreenHandler;
    }

}
