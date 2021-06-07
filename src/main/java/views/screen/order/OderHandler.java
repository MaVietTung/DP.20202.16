package views.screen.order;

import common.interfaces.Observable;
import common.interfaces.Observer;
import entity.order.OrderInterface;
import views.screen.FXMLScreenHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OderHandler extends FXMLScreenHandler implements Observable {
    private OrderInterface oder;
    List<Observer> observerList;
    public OderHandler(String screenPath, OrderInterface oder_convert) throws IOException {
        super(screenPath);
        this.oder = oder_convert;
        observerList = new ArrayList<>();
        //deleteButton.setOnMauseClicked(e -> {notifyObservers()}
        setOderInfo();
    }

    private void setOderInfo() {
        // info Oder to GUI here
    }
    private void setFunctional(){

    }
    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList){
            observer.update(this);
        }
    }
    public OrderInterface getOder(){
        return this.oder;
    }
}
