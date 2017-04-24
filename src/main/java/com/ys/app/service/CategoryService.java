package com.ys.app.service;

import com.ys.app.model.Category;
import com.ys.app.util.UtilPagination;
import org.springframework.security.core.context.SecurityContext;

import java.util.List;
import java.util.Map;

/**
 * Created by byun.ys on 4/22/2017.
 */
public interface CategoryService {

    boolean create(Category category, SecurityContext securityContext);
    Category read(Integer id);
    boolean update(Category category,SecurityContext securityContext);
    boolean delete(Integer id,SecurityContext securityContext);

    List<Category> getList(Integer pageNo, Integer pageSize);
    List<Category> getListBySearch(Integer pageNo,Integer pageSize, String keyword);
    UtilPagination getPagination(Integer pageNo,Integer pageSize) ;
    UtilPagination getPaginationBySearch(Integer pageNo,Integer pageSize,String keyword) ;
    Map<Integer,Category> getAllHashMap();
}
