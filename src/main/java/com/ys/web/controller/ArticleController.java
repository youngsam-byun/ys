package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Article;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.service.ArticleService;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by byun.ys on 4/18/2017.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private static final String FOLDER="/article";
    private static final String PAGE_LIST = "/article_list.jsp";
    private static final String PAGE_READ = "/article_read.jsp";
    private static final String PAGE_WRITE = "/article_write.jsp";

    private static final String PAGINATION = "pagination";
    private static final String ARTICLE_DTO_LIST= "articleDTOList";
    private static final String ARTICLE_DTO = "articleDTO";
    private static final String READ = "read";
    private static final String WRITE = "write";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    public static final String REDIRECT_ARTICLE_LIST_1 = "redirect:/article/list/1";


    @Value("${articleController.read.empty}")
    private final String ARTICLECONTROLLER_READ_EMPTY = "articleController.read.empty";

    @Value("${articleController.delete.fail}")
    private final String  ARTICLECONTROLLER_DELETE_FAIL= "articleController.delete.fail";

    @Value("${articleController.write.fail}")
    private final String ARTICLECONTROLLER_WRITE_FAIL ="articleController.write.fail" ;

    @Value("${articleController.id.notnegative}")
    private static final String ID_SHOULD_NOT_BE_NEGATIVE_VALUE = "articleController.id.notNegative";
    @Value("${articleController.pageNo.notNegative}")
    private static final String PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE = "articleController.pageNo.notNegative";

    @Value("${articleController.keyword.notEmpty}")
    private String SEARCH_KEYWORD_SHOULD_NOT_BE_EMPTY="articleController.keyword.notEmpty";


    @Value("${page.size?:10}")
    private Integer pageSize=10;

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping(value={"/list","/list/{pageNo}"})
    public ModelAndView home(ModelAndView modelAndview, @PathVariable(value = "pageNo",required = false) Integer pageNo) {
        if(pageNo==null)
            pageNo=1;

        if(UtilValidation.isNegativeInt(pageNo))
            throw new CustomException(this.getClass(), LIST,PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE);

        List<ArticleDTO> articleDTOList = articleService.getList(pageNo,pageSize);
        UtilPagination utilPagination=articleService.getPagination(pageNo,pageSize);
        modelAndview.addObject(ARTICLE_DTO_LIST,articleDTOList);
        modelAndview.addObject(PAGINATION,utilPagination);

        modelAndview.setViewName(FOLDER+ PAGE_LIST);
        return modelAndview;
    }


    @GetMapping(value = {"/write"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    public ModelAndView getWrite(Article article, ModelAndView modelAndView){
        modelAndView.addObject("article",article);
        modelAndView.setViewName(FOLDER+ PAGE_WRITE);
        return modelAndView;

    }

    @PostMapping(value = {"/write"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    public ModelAndView write(@ModelAttribute("article") @Valid Article article, BindingResult bindingResult, ModelAndView modelAndView, final Authentication authentication) {

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER+ PAGE_WRITE);
            return modelAndView;
        }

        Boolean b=articleService.writeArticle(article,authentication);
        if(b) {
            modelAndView.setViewName(REDIRECT_ARTICLE_LIST_1);
            return  modelAndView;
        }else
            throw new CustomException(this.getClass(), WRITE, ARTICLECONTROLLER_WRITE_FAIL);
    }


    @GetMapping(value = {"/read/{id}"})
    public ModelAndView read(ModelAndView modelAndview, @PathVariable(value = "id") Integer id) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),READ,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        ArticleDTO articleDTO=articleService.readArticle(id);

        if(articleDTO==null)
            throw new CustomException(this.getClass(), READ, ARTICLECONTROLLER_READ_EMPTY);

        modelAndview.addObject(ARTICLE_DTO,articleDTO);
        modelAndview.setViewName(FOLDER+ PAGE_READ);
        return modelAndview;
    }


    @PostMapping( value = {"/delete/{id}"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    public ModelAndView delete(ModelAndView modelAndview, @PathVariable(value = "id") Integer id,final  Authentication authentication) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),DELETE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        Boolean b=articleService.deleteArticle(id, authentication);

        if(b) {
            modelAndview.setViewName(REDIRECT_ARTICLE_LIST_1);
            return modelAndview;
        }else
            throw new CustomException(this.getClass(), DELETE, ARTICLECONTROLLER_DELETE_FAIL);
    }



}
