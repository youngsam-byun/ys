package com.ys.app.repo;

import com.ys.app.model.Note;

import java.util.List;

/**
 * Created by byun.ys on 4/11/2017.
 */

public interface NoteRepository {

    void setTable(int userId);


    int create(Note note);

    Note read(int id);

    int deleteByUpdateSendDel(int id);

    int deleteByUpdateRecvDel(int id);

    List<Note> getList(int pageNo, int pageSize);

    int getTotal();




}
