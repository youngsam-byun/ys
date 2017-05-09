package com.ys.app.model.mapper;

import com.ys.app.model.Note;
import com.ys.app.model.Note;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byun.ys on 4/11/2017.
 */
public class NoteRowMapper extends BaseRowMapper<Note> {


    public static final String ID = "id";
    public static final String SENDER_ID = "senderId";
    public static final String RECEIVER_ID = "receiverId";
    public static final String MSG = "msg";
    public static final String SEND_TIME = "sendTime";
    public static final String RECEIVE_TIME = "receiveTime";
    public static final String READ = "read";
    public static final String SEND_DEL = "sendDel";
    public static final String RECEIVE_DEL = "receiveDel";


    @Override
    public Note mapRow(ResultSet rs, int rowNum) throws SQLException {

        Note note = new Note();

        note.setId(rs.getInt(ID));
        note.setSenderId(rs.getInt(SENDER_ID));
        note.setReceiverId(rs.getInt(RECEIVER_ID));
        note.setMsg(rs.getString(MSG));
        note.setSendTime(rs.getTimestamp(SEND_TIME));
        note.setReceiveTime(rs.getTimestamp(RECEIVE_TIME));
        note.setRead(rs.getBoolean(READ));
        note.setSendDel(rs.getBoolean(SEND_DEL));
        note.setReceiveDel(rs.getBoolean(RECEIVE_DEL));

        return note;

    }

    @Override
    public MapSqlParameterSource createParameterSource(Note note) {
        return new MapSqlParameterSource()
                .addValue(SENDER_ID,note.getSenderId())
                .addValue(RECEIVER_ID,note.getReceiverId())
                .addValue(MSG,note.getMsg())
                .addValue(SEND_TIME,note.getSendTime())
                .addValue(RECEIVE_TIME,note.getReceiveTime())
                .addValue(READ,note.isRead())
                .addValue(SEND_DEL,note.isSendDel())
                .addValue(RECEIVE_DEL,note.isReceiveDel());

    }

    @Override
    public MapSqlParameterSource updateParameterSource(Note note) {
        return new MapSqlParameterSource()
                .addValue(ID,note.getId())
                .addValue(SENDER_ID,note.getSenderId())
                .addValue(RECEIVER_ID,note.getReceiverId())
                .addValue(MSG,note.getMsg())
                .addValue(SEND_TIME,note.getSendTime())
                .addValue(RECEIVE_TIME,note.getReceiveTime())
                .addValue(READ,note.isRead())
                .addValue(SEND_DEL,note.isSendDel())
                .addValue(RECEIVE_DEL,note.isReceiveDel());
    }
}
