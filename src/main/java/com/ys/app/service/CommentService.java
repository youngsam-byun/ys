package com.ys.app.service;

import com.ys.app.model.Comment;
import com.ys.app.util.UtilPagination;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */
public interface CommentService {

    void setTable(String table);
    boolean  writeComment(Comment comment, SecurityContext securityContext);
    Comment readComment(Integer id)  ;
    boolean  updateComment(Comment comment, SecurityContext securityContext);
    boolean  deleteComment(Integer id, SecurityContext securityContext) ;
    List<Comment> getList(Integer pageNo, Integer pageSize) ;
    UtilPagination getPagination(Integer pageNo, Integer pageSize) ;

}
