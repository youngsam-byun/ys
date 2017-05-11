package com.ys.app.repo.impl;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import com.ys.app.model.Note;
import com.ys.app.model.mapper.NoteRowMapper;
import com.ys.app.repo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.List;

/**
 * Created by byun.ys on 4/11/2017.
 */
@SuppressWarnings("unchecked")
@Repository
public class JdbcNoteRepositoryImpl extends BaseRepository<Note> implements NoteRepository {

    public static final String PREFIX = "N_";


    private static final String NOTE_INS = "Note_INS";
    private static final String NOTE_READ = "Note_READ";

    private static final String NOTE_LIST = "Note_LIST";
    private static final String NOTE_TOTAL = "Note_TOTAL";

    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";

    private static final int SIZE = 10000;
    private static final String NOTE_DELETE_BY_RECV_DEL = "Note_deleteByRecvDel";
    private static final String NOTE_DELETE_BY_SEND_DEL = "Note_deleteBySendDel";
    private static final String USER_ID = "userId";
    private static final String RECV_TIME = "recvTime";
    private static final String ID = "id";
    private static final String TABLE = "table";
    private static final String NOTE_CREATE_TBL = "Note_Create_TBL";

    private static String TABLE_NO;
    private int userId;

    @Autowired
    public JdbcNoteRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.baseRowMapper = new NoteRowMapper();

    }

    @Override
    public void setTable(int userId) {
        setTableFromUserId(userId);
    }

    private  void setTableFromUserId(int userId){
        this.userId = userId;

        int no = userId / SIZE;
        no++;
        TABLE_NO = String.valueOf(no);
        super.setTable(PREFIX+TABLE_NO);
    }

    @Override
    public int create(Note note) {

        int r=-1;
        try {
            r=super.create(NOTE_INS, note);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg.contains("doesn't exist")) {
                setTableFromUserId(userId);
                createTable(table);

            }else
                return  -1;

        } finally {
            if(r==-1)
                return super.create(NOTE_INS, note);
            else
                return  r;
        }
    }

    private void createTable(String table) {
        super.executeSimple(NOTE_CREATE_TBL,new SimpleEntry<>(TABLE,table));
    }

    @Override
    public Note read(int id) {
        return super.executeStoredProcedure(NOTE_READ, new SimpleEntry<>(ID, id), new SimpleEntry<>(RECV_TIME, new Date()));
    }

    @Override
    public int deleteByUpdateSendDel(int id) {
        return super.executeStoredProcedureForObject(NOTE_DELETE_BY_SEND_DEL, new SimpleEntry<>(ID, id));
    }

    @Override
    public int deleteByUpdateRecvDel(int id) {
        return super.executeStoredProcedureForObject(NOTE_DELETE_BY_RECV_DEL, new SimpleEntry<>(ID, id));
    }


    @Override
    public List<Note> getList(int pageNo, int pageSize) {
        return super.getList(NOTE_LIST, new SimpleEntry<>(PAGE_NO, pageNo), new SimpleEntry<>(PAGE_SIZE, pageSize), new SimpleEntry<>(USER_ID, userId));
    }


    @Override
    public int getTotal() {
        return super.getTotal(NOTE_TOTAL, new SimpleEntry<>(USER_ID, userId));
    }

}
