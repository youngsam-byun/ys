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

    public static final String PREFIX = "C_";

    private static final String TABLE_NAME = "Comment";
    private static final String COMMENT_INS = "Comment_INS";
    private static final String COMMENT_READ = "Comment_READ";
    private static final String COMMENT_UPD = "Comment_UPD";

    private static final String COMMENT_LIST = "Comment_LIST";
    private static final String COMMENT_LISTBYSEARCH = "Comment_LISTBYSEARCH";
    private static final String COMMENT_GET_TOTAL = "Comment_getTotal";
    private static final String COMMENT_GET_TOTAL_BY_SEARCH = "Comment_getTotalBySearch";

    private static final String ID = "id";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String KEYWORD="keyword";


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
        return super.getTotal(COMMENT_GET_TOTAL);
    }

    @Override
    public int getTotalBySearch(String keyword) {
        return super.getTotal(COMMENT_GET_TOTAL_BY_SEARCH,new SimpleEntry<>(KEYWORD,keyword));
    }

    @Override
    public List<Comment> getListBySearch(int pageNo, int pageSize, String keyword) {
        return super.getList(COMMENT_LISTBYSEARCH, new SimpleEntry<>(PAGE_NO, pageNo),
                new SimpleEntry<>(PAGE_SIZE, pageSize),
                new SimpleEntry<>(KEYWORD, keyword));
    }


}
