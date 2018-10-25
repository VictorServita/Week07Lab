package database;

import models.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteDB {

    public int insert(Note n) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String preparedQuery = "INSERT INTO notes (datecreated, contents) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setDate(1, n.getDate());
            ps.setString(2, n.getContent());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + n.toString(), ex);
            throw new NotesDBException("Error inserting note");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public int update(Note n) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            String preparedSQL = "UPDATE notes SET contents = ? WHERE noteid = ?";

            PreparedStatement ps = connection.prepareStatement(preparedSQL);

            ps.setInt(1, n.getId());
         
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + n.toString(), ex);
            throw new NotesDBException("Error updating user");
        } finally {
            pool.freeConnection(connection);
        }
    }

    public List<Note> getAll() throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM notes;");
            rs = ps.executeQuery();
            List<Note> notes = new ArrayList<>();
            while (rs.next()) {
                notes.add(new Note(rs.getInt("noteid"),
                        rs.getString("datecreated"),
                        rs.getString("contents")));
            }
            pool.freeConnection(connection);
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting notes");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }

    /**
     * Get a single user by their username.
     *
     * @param username The unique username.
     * @return A User object if found, null otherwise.
     * @throws NotesDBException
     */
    public Note getNote(String noteid) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String selectSQL = "SELECT * FROM notes WHERE noteid = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(selectSQL);
            ps.setString(1, noteid);
            rs = ps.executeQuery();

            Note note = null;
            while (rs.next()) {
                note = new Note(rs.getInt("noteid"),
                        rs.getString("datecreated"),
                        rs.getString("contents"));
            }
            pool.freeConnection(connection);
            return note;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException("Error getting Users");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
            pool.freeConnection(connection);
        }
    }

    public int delete(Note n) throws NotesDBException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String preparedQuery = "DELETE FROM notes WHERE noteid = ?";
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, n.getId());
            int rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + n.toString(), ex);
            throw new NotesDBException("Error deleting User");
        } finally {
            pool.freeConnection(connection);
        }
    }
}
