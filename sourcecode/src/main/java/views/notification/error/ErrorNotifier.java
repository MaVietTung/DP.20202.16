package views.notification.error;
import java.io.IOException;


public abstract class ErrorNotifier {

    private static ErrorNotifier instance;

    protected static ErrorNotifier instance() {
        return null;
    }

    public abstract void notify(String message) throws IOException;

    public static ErrorNotifier getInstance() {
        if (instance == null) {
            synchronized(ErrorNotifier.class) {
                if (instance == null) {
                    instance = instance();
                }
            }
        }
        return instance;
    }
}

