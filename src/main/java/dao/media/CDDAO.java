package dao.media;

import entity.db.AIMSDB;
import entity.media.CD;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

//Singleton: áp dụng singleton vì lớp CDDAO được truy cập thường xuyên mà không cần nhiều instance

/**
 * @author
 */
public class CDDAO extends MediaDAO {

    private static CDDAO instance;
    private CDDAO(){
        super();
    }

    public static CDDAO getInstance(){
        if (instance == null) instance = new CDDAO();
        return instance;
    }

    @Override
    //COntrol Coupling: tham so id thay doi thi luong thuc hien phuong thuc cung thay doi theo
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM "+
                "aims.CD " +
                "INNER JOIN aims.Media " +
                "ON Media.id = CD.id " +
                "where Media.id = " + id + ";";

        ResultSet res = AIMSDB.getConnection().createStatement().executeQuery(sql);
        if(res.next()) {

            // from media table
            String title = "";
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");

            // from CD table
            String artist = res.getString("artist");
            String recordLabel = res.getString("recordLabel");
            String musicType = res.getString("musicType");
            Date releasedDate = res.getDate("releasedDate");

            return new CD(id, title, category, price, quantity, type,
                    artist, recordLabel, musicType, releasedDate);

        } else {
            throw new SQLException();
        }
    }
}
