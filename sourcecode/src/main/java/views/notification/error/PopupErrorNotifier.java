package views.notification.error;
import java.io.IOException;

import views.screen.popup.PopupScreen;


public class PopupErrorNotifier extends ErrorNotifier {

    private PopupErrorNotifier() {

    }

    protected static ErrorNotifier instance() {
        return new PopupErrorNotifier();
    }

    public void notify(String message) throws IOException {
        PopupScreen.error(message);
    }
}

