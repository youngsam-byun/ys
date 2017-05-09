package com.ys.app.repo.impl;

import com.ys.app.model.Note;
import com.ys.app.model.mapper.NoteRowMapper;
import com.ys.app.repo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * Created by byun.ys on 4/11/2017.
 */
@SuppressWarnings("unchecked")
@Repository
public class JdbcNoteRepositoryImpl extends BaseRepository<Note> implements NoteRepository {

    public static final String PREFIX = "=N_";

    private static final String TABLE_NAME = "Note";
    private static final String NOTE_INS = "Note_INS";
    private static final String NOTE_READ = "Note_READ";
    private static final String NOTE_UPD = "Note_UPD";

    private static final String G_GET_LIST = "G_getList";
    private static final String G_GET_TOTAL = "Note_getTotal";
    private static final String G_GET_LIST_BY_SEARCH = "G_getListBySearch";
    private static final String G_GET_TOTAL_BY_SEARCH = "G_getTotalBySearch";

    private static final String KEYWORD="keyword";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String ID = "id";


    @Autowired
    public JdbcNoteRepositoryImpl(DataSource dataSource) {
        this.dataSource=dataSource;
        this.baseRowMapper = new NoteRowMapper();
        this.setTable(PREFIX+TABLE_NAME);
    }

    @Override
    public void setTable(String table) {
        super.setTable(table);
    }

    @Override
    public int create(Note note) {
        return super.create(NOTE_INS, note);
    }

    @Override
    public Note read(int id) {
        return super.executeStoredProcedure(NOTE_READ, new SimpleEntry<>(ID, id));
    }

    @Override
    public int update(Note note) {
        return super.update(NOTE_UPD, note);
    }

    @Override
    public int delete(int id) {
        return super.deleteByUpdateId(id);
    }

    @Override
    public List<Note> getList(int pageNo,int pageSize) {
        return super.getList(G_GET_LIST, new SimpleEntry<>(PAGE_NO, pageNo),new SimpleEntry<>(PAGE_SIZE, pageSize));
    }


    @Override
    public int getTotal() {
        return super.getTotal(G_GET_TOTAL);
    }

    @Override
    public int getTotalBySearch(String keyword) {
        return super.getTotal(G_GET_TOTAL_BY_SEARCH,new SimpleEntry<>(KEYWORD,keyword));
    }

    @Override
    public List<Note> getListBySearch(int pageNo, int pageSize, String keyword) {
        return super.getList(G_GET_LIST_BY_SEARCH, new SimpleEntry<>(PAGE_NO, pageNo),
                new SimpleEntry<>(PAGE_SIZE, pageSize),
                new SimpleEntry<>(KEYWORD, keyword));
    }


}
