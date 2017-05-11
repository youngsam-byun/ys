package com.ys.app.repo.impl;

import com.ys.app.model.Category;
import com.ys.app.model.mapper.CategoryRowMapper;
import com.ys.app.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * Created by byun.ys on 4/13/2017.
 */
@SuppressWarnings("unchecked")
@Repository
public class JdbcCategoryRepositoryImpl extends BaseRepository<Category> implements CategoryRepository {

    private static final String TABLE_NAME = "Category";
    private static final String CATEGORY_INS = "Category_INS";
    private static final String CATEGORY_UPD = "Category_UPD";
    private static final String G_LIST_BY_SEARCH = "G_getListBySearch";
    private static final String GET_LIST = "G_getList";
    private static final String G_GET_TOTAL = "G_getTotal";
    private static final String G_GET_TOTAL_BY_SEARCH = "G_getTotalBySearch";

    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String KEYWORD = "keyword";


    private static final String CATEGORY_CREATE_TBL_ARTICLE = "Category_create_TBL_Article";
    private static final String CATEGORY_CREATE_TBL_COMMENT = "Category_create_TBL_Comment";
    private static final String CATEGORY_RENAME_TBL_ARTICLE = "Category_rename_TBL_Article";
    private static final String CATEGORY_RENAME_TBL_COMMENT = "Category_rename_TBL_Comment";
    private static final String CATEGORY_DROP_TBL_ARTICLE = "Category_drop_TBL_Article";
    private static final String CATEGORY_DROP_TBL_COMMENT = "Category_drop_TBL_Comment";
    private static final String OLD_TABLE = "oldTable";
    private static final String NEW_TABLE = "newTable";
    private static final String TABLE = "table";

    @Autowired
    public JdbcCategoryRepositoryImpl(DataSource dataSource) {
        this.dataSource=dataSource;
        this.baseRowMapper = new CategoryRowMapper();
        this.setTable(TABLE_NAME);
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
        return super.deleteByUpdateId(id);

    }

    @Override
    public List<Category> getList(int pageNo, int pageSize) {
        return super.getList(GET_LIST, new SimpleEntry<>(PAGE_NO, pageNo), new SimpleEntry<>(PAGE_SIZE, pageSize));
    }

    @Override
    public List<Category> getListBySearch(int pageNo, int pageSize, String keyword) {
        return super.getList(G_LIST_BY_SEARCH, new SimpleEntry<>(PAGE_NO, pageNo),
                new SimpleEntry<>(PAGE_SIZE, pageSize),
                new SimpleEntry<>(KEYWORD, keyword)
        );
    }

    @Override
    public int getTotal() {
        return super.getTotal(G_GET_TOTAL);
    }

    @Override
    public int getTotalBySearch(String keyword) {
        return super.getTotal(G_GET_TOTAL_BY_SEARCH, new AbstractMap.SimpleEntry<>(KEYWORD, keyword));
    }

    @Override
    public List<Category> getAll() {
        return super.getListAll();
    }

    @Override
    @Transactional
    public void createTable(String table) {
        super.executeSimple(CATEGORY_CREATE_TBL_ARTICLE,new SimpleEntry<>(TABLE,JdbcArticleRepositoryImpl.PREFIX+table));
        super.executeSimple(CATEGORY_CREATE_TBL_COMMENT,new SimpleEntry<>(TABLE,JdbcCommentRepositoryImpl.PREFIX+table));
    }

    @Override
    @Transactional
    public void renameTable(String oldTable, String newTable) {
        super.executeSimple(CATEGORY_RENAME_TBL_ARTICLE,new SimpleEntry<>(OLD_TABLE,JdbcArticleRepositoryImpl.PREFIX+oldTable),new SimpleEntry<>(NEW_TABLE,JdbcArticleRepositoryImpl.PREFIX+newTable));
        super.executeSimple(CATEGORY_RENAME_TBL_COMMENT,new SimpleEntry<>(OLD_TABLE,JdbcCommentRepositoryImpl.PREFIX+oldTable),new SimpleEntry<>(NEW_TABLE,JdbcCommentRepositoryImpl.PREFIX+newTable));
    }

    @Override
    @Transactional
    public void dropTable(String table) {

        super.executeSimple(CATEGORY_DROP_TBL_ARTICLE,new SimpleEntry<>(TABLE,JdbcArticleRepositoryImpl.PREFIX+table));
        super.executeSimple(CATEGORY_DROP_TBL_COMMENT,new SimpleEntry<>(TABLE,JdbcCommentRepositoryImpl.PREFIX+table));

    }
}
