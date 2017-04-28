package com.ys.app.service;

import com.ys.app.model.Comment;
import com.ys.app.model.dto.CommentDTO;
import com.ys.app.util.UtilPagination;

import java.security.Principal;
import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */
public interface CommentService {

    void setTable(String table);

    boolean create(Comment comment, Principal principal );

    CommentDTO read(Integer id);

    boolean update(Comment comment, Principal  principal );

    boolean delete(Integer id, Principal  principal );

    List<CommentDTO> getList(Integer pageNo, Integer pageSize);

    UtilPagination getPagination(Integer pageNo, Integer pageSize);

    UtilPagination getPaginationBySearch(Integer pageNo,Integer pageSize,String keyword) ;

    List<CommentDTO> getListBySearch(Integer pageNo, Integer pageSize, String keyword) ;
}
