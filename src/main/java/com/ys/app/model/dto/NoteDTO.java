package com.ys.app.model.dto;

import com.ys.app.model.Note;
import com.ys.app.model.User;

import javax.validation.Valid;

/**
 * Created by byun.ys on 5/9/2017.
 */
public class NoteDTO {

    User user;
    Note note;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
