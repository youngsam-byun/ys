package com.ys.app.service;

import com.ys.app.model.Comment;
import com.ys.app.model.dto.CommentDTO;
import com.ys.app.util.UtilPagination;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import java.security.Principal;
import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */
public interface CommentService {

    void setTable(String table);

    boolean writeComment(Comment comment, Principal principal );

    CommentDTO readComment(Integer id);

    boolean updateComment(Comment comment, Principal  principal );

    boolean deleteComment(Integer id, Principal  principal );

    List<CommentDTO> getList(Integer pageNo, Integer pageSize);

    UtilPagination getPagination(Integer pageNo, Integer pageSize);

}
