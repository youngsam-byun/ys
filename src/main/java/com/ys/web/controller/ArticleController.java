package com.ys.web.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.ys.app.exception.CustomException;
import com.ys.app.model.Article;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.service.ArticleService;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    private static final String WRITE_JSP = "/write.jsp";

    private static final String PAGINATION = "pagination";
    private static final String ARTICLE_DTO_LIST = "articleDTOList";
    private static final String ARTICLE_DTO = "articleDTO";
    private static final String READ = "read";
    private static final String WRITE = "write";
    private static final String DELETE = "delete";
    private static final String LIST = "list";


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

        modelAndview.setViewName(FOLDER+ LIST_JSP);
        return modelAndview;
    }


    @GetMapping(value = {"/write"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    public ModelAndView get_write(Article article,ModelAndView modelAndView){
        modelAndView.addObject("article",article);
        modelAndView.setViewName(FOLDER+ WRITE_JSP);
        return modelAndView;

    }

    @PostMapping(value = {"/write"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    public ModelAndView write(@ModelAttribute("article") @Valid Article article, BindingResult bindingResult, ModelAndView modelAndView) {

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER+WRITE_JSP);
            return modelAndView;
        }

        Boolean b=articleService.writeArticle(article,SecurityContextHolder.getContext());
        if(b) {
            modelAndView.setViewName(FOLDER+LIST_JSP);
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
        modelAndview.setViewName(FOLDER+ READ_JSP);
        return modelAndview;
    }


    @PostMapping( value = {"/delete/{id}"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    public ModelAndView delete(ModelAndView modelAndview, @PathVariable(value = "id") Integer id) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),DELETE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        Boolean b=articleService.deleteArticle(id, SecurityContextHolder.getContext());

        if(b) {
            modelAndview.setViewName(FOLDER + LIST_JSP);
            return modelAndview;
        }else
            throw new CustomException(this.getClass(), DELETE, ARTICLECONTROLLER_DELETE_FAIL);
    }



}
