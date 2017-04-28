package com.ys.app.repo;

import com.ys.app.model.Comment;

import java.util.List;

/**
 * Created by byun.ys on 4/11/2017.
 */

public interface CommentRepository {

    void setTable(String table);

    int create(Comment comment);

    Comment read(int id);

    int update(Comment comment);

    int delete(int id);

    List<Comment> getList(int pageNo, int pageSize);

    int getTotal();

    int getTotalBySearch(String keyword);

    List<Comment> getListBySearch(int pageNo, int pageSize, String keyword);


}
