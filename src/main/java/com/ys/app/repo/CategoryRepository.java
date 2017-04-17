package com.ys.app.repo;

import com.ys.app.model.Category;

import java.util.List;

/**
 * Created by byun.ys on 4/13/2017.
 */
public interface CategoryRepository {

    int create(Category category);
    Category read(int id);
    int update(Category category);
    int delete(int id);

    List<Category> getList(int pageNo, int pageSize);
    List<Category> getListBySearch(int pageNo,int pageSize, String keyword);
    int getTotal();
    int getTotalBySearch(String keyword);

    List<Category> getAll();


}
