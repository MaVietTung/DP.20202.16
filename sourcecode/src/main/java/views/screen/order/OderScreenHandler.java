package views.screen.order;

import common.interfaces.Observable;
import common.interfaces.Observer;
import controller.OderController;
import entity.order.OrderDB;
import entity.order.OrderInterface;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OderScreenHandler extends BaseScreenHandler implements Observer {
    public static Logger LOGGER = Utils.getLogger(OderScreenHandler.class.getName());
    private ArrayList<Object> oderList;

    public OderScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        try {
            setupData(null);
            setupFunctionality();
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error(ex.getMessage());
        }
    }

    public void setupFunctionality() {
    }

    public void setupData(Object o) {
        setBController(new OderController());
        try{
            List oderList =  this.getBController().getALLOder();
            this.oderList = new ArrayList<>();
            for(Object oder: oderList){
                OrderInterface oder_convert = (OrderInterface) oder;
                OderHandler oderHandler = new OderHandler(ViewsConfig.ORDER_HOME_PATH, oder_convert);
                oderHandler.attach(this);
                this.oderList.add(oder_convert);
            }
        } catch (SQLException | IOException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public OderController getBController(){
        return (OderController) super.getBController();
    }
    @Override
    public void update(Observable observable) {
            update((OderHandler) observable);
    }

    private void update(OderHandler oderHandler) {
        OrderDB oderDB = (OrderDB) oderHandler.getOder();
        if(oderDB.checkConfirm()){

        }else {

        }
    }
}
