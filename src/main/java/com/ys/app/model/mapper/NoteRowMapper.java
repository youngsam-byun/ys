package com.ys.app.model.mapper;

import com.ys.app.model.Note;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byun.ys on 4/11/2017.
 */
public class NoteRowMapper extends BaseRowMapper<Note> {


    public static final String ID = "id";
    public static final String SEND_ID = "sendId";
    public static final String RECV_ID = "recvId";
    public static final String MSG = "msg";
    public static final String SEND_TIME = "sendTime";
    public static final String RECV_TIME = "recvTime";
    public static final String READ = "read";
    public static final String SEND_DEL = "sendDel";
    public static final String RECV_DEL = "recvDel";


    @Override
    public Note mapRow(ResultSet rs, int rowNum) throws SQLException {

        Note note = new Note();

        note.setId(rs.getInt(ID));
        note.setSendId(rs.getInt(SEND_ID));
        note.setRecvId(rs.getInt(RECV_ID));
        note.setMsg(rs.getString(MSG));
        note.setSendTime(rs.getTimestamp(SEND_TIME));
        note.setRecvTime(rs.getTimestamp(RECV_TIME));
        note.setRead(rs.getBoolean(READ));
        note.setSendDel(rs.getBoolean(SEND_DEL));
        note.setRecvDel(rs.getBoolean(RECV_DEL));

        return note;

    }

    @Override
    public MapSqlParameterSource createParameterSource(Note note) {
        return new MapSqlParameterSource()
                .addValue(SEND_ID,note.getSendId())
                .addValue(RECV_ID,note.getRecvId())
                .addValue(MSG,note.getMsg())
                .addValue(SEND_TIME,note.getSendTime())
                .addValue(RECV_TIME,note.getRecvTime())
                .addValue(READ,note.isRead())
                .addValue(SEND_DEL,note.isSendDel())
                .addValue(RECV_DEL,note.isRecvDel());

    }

    @Override
    public MapSqlParameterSource updateParameterSource(Note note) {
        return new MapSqlParameterSource()
                .addValue(ID,note.getId())
                .addValue(SEND_ID,note.getSendId())
                .addValue(RECV_ID,note.getRecvId())
                .addValue(MSG,note.getMsg())
                .addValue(SEND_TIME,note.getSendTime())
                .addValue(RECV_TIME,note.getRecvTime())
                .addValue(READ,note.isRead())
                .addValue(SEND_DEL,note.isSendDel())
                .addValue(RECV_DEL,note.isRecvDel());
    }
}
