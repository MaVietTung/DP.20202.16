package controller;

import java.sql.SQLException;
import java.util.List;

import dao.media.MediaDAO;

/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */
public class HomeController extends BaseController {


    /**
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
    // Common coupling - Phương thức có thể truy cập bất cứ đâu
    public static List getAllMedia() throws SQLException{
        return new MediaDAO().getAllMedia();
    }
}
