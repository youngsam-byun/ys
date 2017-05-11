package com.ys.app.service;

import com.ys.app.model.Note;
import com.ys.app.model.dto.NoteDTO;
import com.ys.app.util.UtilPagination;

import java.security.Principal;
import java.util.List;

/**
 * Created by byun.ys on 4/22/2017.
 */
public interface NoteService {

    boolean create(Integer userId,Note note, Principal principal);
    Note read(Integer userId,Integer id,Principal principal);
    boolean delete(Integer userId,Integer id, Principal principal);

    List<NoteDTO> getList(Integer userId,Integer pageNo, Integer pageSize);
    UtilPagination getPagination(Integer userId,Integer pageNo, Integer pageSize) ;

}
