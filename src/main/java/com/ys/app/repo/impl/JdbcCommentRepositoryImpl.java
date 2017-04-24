package com.ys.app.repo.impl;

import com.ys.app.model.Comment;
import com.ys.app.model.mapper.CommentRowMapper;
import com.ys.app.repo.CommentRepository;
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
public class JdbcCommentRepositoryImpl extends BaseRepository<Comment> implements CommentRepository {


    private static final String TABLE_NAME = "Comment";
    private static final String COMMENT_INS = "Comment_INS";
    private static final String COMMENT_UPD = "Comment_UPD";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String COMMENT_LIST = "Comment_LIST";
    private static final String G_GET_TOTAL = "G_getTotal";
    private static final String ID = "id";
    private static final String COMMENT_READ = "Comment_READ";
    public static final String PREFIX = "C_";


    @Autowired
    public JdbcCommentRepositoryImpl(DataSource dataSource) {
        this.dataSource=dataSource;
        this.baseRowMapper = new CommentRowMapper();
        this.setTable(PREFIX+TABLE_NAME);
    }

    @Override
    public void setTable(String table) {
        super.setTable(table);
    }

    @Override
    public int create(Comment comment) {
        return super.create(COMMENT_INS, comment);
    }

    @Override
    public Comment read(int id) {
        return super.executeStoredProcedure(COMMENT_READ, new SimpleEntry<>(ID, id));
    }

    @Override
    public int update(Comment comment) {
        return super.update(COMMENT_UPD, comment);
    }

    @Override
    public int delete(int id) {
        return super.deleteByUpdateId(id);
    }

    @Override
    public List<Comment> getList(int pageNo,int pageSize) {
        return super.getList(COMMENT_LIST, new SimpleEntry<>(PAGE_NO, pageNo),new SimpleEntry<>(PAGE_SIZE, pageSize));
    }


    @Override
    public int getTotal() {
        return super.getTotal(G_GET_TOTAL);
    }



}
