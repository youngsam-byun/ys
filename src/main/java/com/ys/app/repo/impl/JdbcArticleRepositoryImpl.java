package com.ys.app.repo.impl;

import com.ys.app.model.Article;
import com.ys.app.model.mapper.ArticleRowMapper;
import com.ys.app.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * Created by byun.ys on 4/11/2017.
 */
@SuppressWarnings("unchecked")
@Repository
public class JdbcArticleRepositoryImpl extends BaseRepository<Article> implements ArticleRepository {


    public static final String PREFIX = "A_";

    private static final String TABLE_NAME = "Article";
    private static final String ARTICLE_INS = "Article_INS";
    private static final String ARTICLE_READ = "Article_READ";
    private static final String ARTICLE_UPD = "Article_UPD";
    private static final String ARTICLE_LIST = "Article_LIST";
    private static final String ARTICLE_LISTBYSEARCH = "Article_LISTBYSEARCH";
    private static final String ARTICLE_TOTAL = "Article_TOTAL";
    private static final String ARTICLE_TOTALBYSEARCH = "Article_TOTALBYSEARCH";


    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String KEYWORD = "keyword";
    private static final String ID = "id";



    @Autowired
    public JdbcArticleRepositoryImpl(DataSource dataSource) {
        this.dataSource=dataSource;
        this.baseRowMapper = new ArticleRowMapper();
        this.setTable(PREFIX+TABLE_NAME);
    }

    @Override
    public void setTable(String table) {
        super.setTable(table);
    }

    @Override
    public int create(Article article) {
        return super.create(ARTICLE_INS, article);
    }

    @Override
    public Article read(int id) {
        return super.executeStoredProcedure(ARTICLE_READ, new SimpleEntry<>(ID, id));
    }

    @Override
    public int update(Article article) {
        return super.update(ARTICLE_UPD, article);
    }

    @Override
    public int delete(int id) {
        return super.deleteByUpdateId(id);
    }

    @Override
    public List<Article> getList(int pageNo,int pageSize) {
        return super.getList(ARTICLE_LIST, new SimpleEntry<>(PAGE_NO, pageNo),new SimpleEntry<>(PAGE_SIZE, pageSize));
    }

    @Override
    public List<Article> getListBySearch(int pageNo,int pageSize,String keyword) {
        return super.getList(ARTICLE_LISTBYSEARCH, new SimpleEntry<>(PAGE_NO, pageNo),
                new SimpleEntry<>(PAGE_SIZE, pageSize),
                new SimpleEntry<>(KEYWORD, keyword));
    }

    @Override
    public int getTotal() {
        return super.getTotal(ARTICLE_TOTAL);
    }

    @Override
    public int getTotalBySearch(String keyword) {
        return super.getTotal(ARTICLE_TOTALBYSEARCH,new SimpleEntry<>(KEYWORD,keyword));
    }

}
