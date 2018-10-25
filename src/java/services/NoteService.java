package services;

import database.NoteDB;
import models.Note;
import java.util.List;

public class NoteService {

    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Note get(String username) throws Exception {
        return noteDB.getNote(username);
    }

    public List<Note> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(int Id, String date, String content) throws Exception {
        Note n = new Note(Id, date, content);
        return noteDB.update(n);
    }

    public int delete(String id) throws Exception {
        Note deletedUser = noteDB.getNote(id);
        // do not allow the admin to be deleted
        return noteDB.delete(deletedUser);
    }

    public int insert(int Id, String date, String content) throws Exception {
        Note user = new Note(Id, date, content);
        return noteDB.insert(user);
    }
}
