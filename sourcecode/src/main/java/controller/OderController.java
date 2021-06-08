package controller;

import dao.order.OrderDAO;

import java.sql.SQLException;
import java.util.List;

public class OderController extends BaseController{

    public static List getALLOder() throws SQLException {
        return OrderDAO.getInstance().getALLOder();
    }
    public static void deleteOderByID(int id){
        OrderDAO.getInstance().deleteOderByID(id);
    }
}
