package com.ys.app.service;

import com.ys.app.model.Category;
import com.ys.app.util.UtilPagination;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by byun.ys on 4/22/2017.
 */
public interface CategoryService {

    boolean create(Category category, Principal principal );
    Category read(Integer id);
    boolean update(Category category,Principal  principal );
    boolean delete(Integer id,Principal  principal );

    List<Category> getList(Integer pageNo, Integer pageSize);
    List<Category> getListBySearch(Integer pageNo,Integer pageSize, String keyword);
    UtilPagination getPagination(Integer pageNo,Integer pageSize) ;
    UtilPagination getPaginationBySearch(Integer pageNo,Integer pageSize,String keyword) ;
    Map<Integer,Category> getAllHashMap();
}
