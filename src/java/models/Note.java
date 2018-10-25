package models;

import database.ConnectionPool;
import database.NotesDBException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Note {
    
    private int id;
    private String date;
    private String content;
    
    public Note(){}
    
    public Note(int id, String date, String content)
            
    {setId(id); 
     setDate(date); 
     setContent(content);}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
  
    @Override
    public String toString()
    {
        return "id: " + id + " date: " + date + " content: " + content;
    }
}
