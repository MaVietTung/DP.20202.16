package entity.media;

import java.sql.SQLException;

public class AudioBook extends Media{
    String author;
    String format;
    String language;
    String accent;
    int lengthInMinute;
    public AudioBook() throws SQLException{

    }
    public  AudioBook(int id, String title, String category, int price, int quantity, String type,String author,String format,String language,String accent,int lengthInMinute) throws SQLException {
        super(id,title,category,price,quantity,type);
        this.author = author;
        this.format = format;
        this.language = language;
        this.accent = accent;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getFormat(){
        return this.format;
    }
    public String getLanguage(){
        return this.language;
    }
    public String getAccent(){
        return this.accent;
    }
}
