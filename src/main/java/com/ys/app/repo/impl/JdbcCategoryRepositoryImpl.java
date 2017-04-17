package com.ys.app.repo.impl;

import com.ys.app.model.Category;
import com.ys.app.model.mapper.CategoryRowMapper;
import com.ys.app.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * Created by byun.ys on 4/13/2017.
 */
@Repository
public class JdbcCategoryRepositoryImpl extends BaseRepository<Category> implements CategoryRepository {

    private static final String TABLE = "Category";
    private static final String CATEGORY_INS = "Category_INS";
    private static final String CATEGORY_UPD = "Category_UPD";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String LIST_BY_SEARCH = "getListBySearch";
    private static final String GET_LIST = "getList";
    private static final String KEYWORD = "keyword";
    private static final String GET_TOTAL = "getTotal";


    @Autowired
    public JdbcCategoryRepositoryImpl(DataSource dataSource) {
        this.dataSource=dataSource;
        this.baseRowMapper = new CategoryRowMapper();
        this.setTable(TABLE);
    }
    
    @Override
    public int create(Category category) {
        return super.create(CATEGORY_INS, category);
    }

    @Override
    public Category read(int id) {

        return super.readbyId(id);

    }

    @Override
    public int update(Category category) {
        return super.update(CATEGORY_UPD, category);

    }

    @Override
    public int delete(int id) {
        return super.deleteById(id);

    }

    @Override
    public List<Category> getList(int pageNo, int pageSize) {
        return super.getList(GET_LIST, new SimpleEntry<String, Object>(PAGE_NO, pageNo), new SimpleEntry<String, Object>(PAGE_SIZE, pageSize));
    }

    @Override
    public List<Category> getListBySearch(int pageNo, int pageSize, String keyword) {
        return super.getList(LIST_BY_SEARCH, new SimpleEntry<String, Object>(PAGE_NO, pageNo),
                new SimpleEntry<String, Object>(PAGE_SIZE, pageSize),
                new SimpleEntry<String, Object>(KEYWORD, keyword)
        );
    }

    @Override
    public int getTotal() {
        return super.getTotal(GET_TOTAL);
    }

    @Override
    public int getTotalBySearch(String keyword) {
        return super.getTotal(GET_TOTAL, new AbstractMap.SimpleEntry<>(KEYWORD, keyword));
    }

    @Override
    public List<Category> getAll() {
        return super.getListAll();
    }
}
