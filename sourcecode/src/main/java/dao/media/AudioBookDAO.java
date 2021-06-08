package dao.media;

import entity.db.AIMSDB;
import entity.media.AudioBook;
import entity.media.Book;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class AudioBookDAO extends MediaDAO{
    private static AudioBookDAO instance;
    private AudioBookDAO(){
        super();
    }
    public static AudioBookDAO getInstance(){
        if (instance == null) instance = new AudioBookDAO();
        return instance;
    }
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM "+
                "aims.Book " +
                "INNER JOIN aims.Media " +
                "ON Media.id = AudioBook.id " +
                "where Media.id = " + id + ";";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()) {

            // from Media table
            String title = "";
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");

            // from Book table
            String author = res.getString("author");
            String format = res.getString("format");
            String language = res.getString("language");
            String accent = res.getString("accent");
            int lengthInMinute = res.getInt("lengthInMinute");
            return new AudioBook(id,title,category,price,quantity,type,author,format,language,accent,lengthInMinute);

        } else {
            throw new SQLException();
        }
    }
    }

