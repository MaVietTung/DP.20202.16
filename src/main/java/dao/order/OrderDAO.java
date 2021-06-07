package dao.order;

import entity.db.AIMSDB;
import entity.media.Media;
import entity.order.OderInterface;
import entity.order.OrderDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class OrderDAO {
    private static OrderDAO instance;
    public static OrderDAO getInstance(){
        if (instance == null) return instance = new OrderDAO();
        else return instance;
    }
    public List getALLOder() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from OderTable");
        List oderList = new ArrayList<OderInterface>();
        while (res.next()) {
            OderInterface oder = new OrderDB(
                    res.getInt("id"),
                    res.getInt("shippingFees"),
                    res.getInt("subtotal"),
                    res.getInt("tax"),
                    res.getInt("total"),
                    res.getInt("deliveryInfoId"));
            oderList.add(oder);
        }
        return oderList;
    }
    public void deleteOderByID(int id){

    }
}
