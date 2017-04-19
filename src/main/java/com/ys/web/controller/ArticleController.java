package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.service.ArticleService;
import com.ys.app.util.UtilPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by byun.ys on 4/18/2017.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private static final String FOLDER="/article";
    private static final String LIST_JSP = "/list.jsp";
    private static final String READ_JSP = "/read.jsp";
    private static final String PAGINATION = "pagination";
    private static final String ARTICLE_DTO_LIST = "articleDTOList";
    private static final String ARTICLE_DTO = "articleDTO";
    private static final String READ = "read";

    @Value("${articlecontroller.read.empty}")
    private final String ARTICLECONTROLLER_READ_EMPTY = "articlecontroller.read.empty";

    @Value("${articlecontroller.delete.fail}")
    private final String  ARTICLECONTROLLER_DELETE_FAIL= "articlecontroller.delete.fail";



    @Value("${page.size?:10}")
    private Integer pageSize=10;

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @RequestMapping(method=GET,value={"/list","/list/{pageNo}"})
    public ModelAndView home(ModelAndView modelAndview, @PathVariable(value = "pageNo",required = false) Integer pageNo){
        if(pageNo==null)
            pageNo=1;

        List<ArticleDTO> articleDTOList = articleService.getList(pageNo,pageSize);
        UtilPagination utilPagination=articleService.getPagination(pageNo,pageSize);
        modelAndview.addObject(ARTICLE_DTO_LIST,articleDTOList);
        modelAndview.addObject(PAGINATION,utilPagination);

        modelAndview.setViewName(FOLDER+ LIST_JSP);
        return modelAndview;
    }

    @RequestMapping(method=GET,value = {"/read/{id}"})
    public ModelAndView read(ModelAndView modelAndview, @PathVariable(value = "id") Integer id){

        ArticleDTO articleDTO=articleService.readArticle(id);

        if(articleDTO==null)
            throw new CustomException(this.getClass(), READ, ARTICLECONTROLLER_READ_EMPTY,new Throwable());

        modelAndview.addObject(ARTICLE_DTO,articleDTO);
        modelAndview.setViewName(FOLDER+ READ_JSP);
        return modelAndview;
    }


    @RequestMapping(method=POST,value = {"/delete/{id}"})
    public ModelAndView delete(ModelAndView modelAndview, @PathVariable(value = "id") Integer id){

        Boolean b=articleService.deleteArticle(id, SecurityContextHolder.getContext());

        if(b) {
            modelAndview.setViewName(FOLDER + LIST_JSP);
            return modelAndview;
        }else
            throw new CustomException(this.getClass(), READ, ARTICLECONTROLLER_DELETE_FAIL,new Throwable());
    }

}
