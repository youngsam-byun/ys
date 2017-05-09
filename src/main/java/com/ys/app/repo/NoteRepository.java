package com.ys.app.repo;

import com.ys.app.model.Note;

import java.util.List;

/**
 * Created by byun.ys on 4/11/2017.
 */

public interface NoteRepository {

    void setTable(String table);

    int create(Note note);

    Note read(int id);

    int update(Note note);

    int delete(int id);

    List<Note> getList(int pageNo, int pageSize);

    int getTotal();

    int getTotalBySearch(String keyword);

    List<Note> getListBySearch(int pageNo, int pageSize, String keyword);


}
