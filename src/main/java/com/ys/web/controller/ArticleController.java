package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Article;
import com.ys.app.model.dto.ArticleDTO;
import com.ys.app.model.validator.form.SearchForm;
import com.ys.app.service.ArticleService;
import com.ys.app.service.impl.ArticleServiceImpl;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


/**
 * Created by byun.ys on 4/18/2017.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private static final String FOLDER="/article";
    private static final String PAGE_LIST = "/article_list";
    private static final String PAGE_READ = "/article_read";
    private static final String PAGE_CREATE = "/article_create";
    private static final String PAGE_SEARCH = "/article_search";
    private static final String PAGE_UPDATE = "/article_update";

    private static final String PAGINATION = "pagination";
    private static final String ARTICLE_DTO_LIST= "articleDTOList";
    private static final String ARTICLE_DTO = "articleDTO";
    private static final String READ = "read";
    private static final String WRITE = "create";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String REDIRECT_ARTICLE_LIST_1 = "redirect:/article/list/1";
    private static final String UPDATE = "update";

    private static final String NO_PERMISSION_TO_ACCESS_THIS_ARTICLE = "No permission to access this article";


    @Value("${articleController.read.empty?:articleController.read.empty}")
    private String ARTICLECONTROLLER_READ_EMPTY;

    @Value("${articleController.delete.fail?:articleController.delete.fail}")
    private String  ARTICLECONTROLLER_DELETE_FAIL;

    @Value("${articleController.create.fail?:articleController.create.fail}")
    private String ARTICLECONTROLLER_WRITE_FAIL;

    @Value("${articleController.update.fail?:articleController.update.fail}")
    private String UPDATE_ARTICLE_FAILED ;

    @Value("${articleController.id.notNegative?:articleController.id.notNegative}")
    private String ID_SHOULD_NOT_BE_NEGATIVE_VALUE ;

    @Value("${articleController.keyword.notEmpty?:articleController.keyword.notEmpty}")
    private String SEARCH_KEYWORD_SHOULD_NOT_BE_EMPTY;


    @Value("${articleController.pageNo.notNegative?:articleController.pageNo.notNegative}")
    private String PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE;





    @Value("${page.size?:10}")
    private Integer pageSize=10;

    private ArticleService articleService;

    @Autowired
    private ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping(value={"/list","/list/{pageNo}"})
    private ModelAndView home(ModelAndView modelAndView, @PathVariable(required = false) Integer pageNo) {
        if(pageNo==null)
            pageNo=1;

        if(UtilValidation.isNegativeInt(pageNo))
            throw new CustomException(this.getClass(), LIST,PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE);

        List<ArticleDTO> articleDTOList = articleService.getList(pageNo,pageSize);
        UtilPagination utilPagination=articleService.getPagination(pageNo,pageSize);
        modelAndView.addObject(ARTICLE_DTO_LIST,articleDTOList);
        modelAndView.addObject(PAGINATION,utilPagination);

        modelAndView.setViewName(FOLDER+ PAGE_LIST);
        return modelAndView;
    }

    @PostMapping(value={"/search","/search/{pageNo}"})
    private ModelAndView getSearch(@ModelAttribute SearchForm searchForm,BindingResult bindingResult,@PathVariable(required = false) Integer pageNo, ModelAndView modelAndView) {

        if(bindingResult.hasErrors())
        {
            modelAndView.setViewName(PAGE_SEARCH);
            return modelAndView;
        }

        if(pageNo==null)
            pageNo=1;

        if(UtilValidation.isNegativeInt(pageNo))
            throw new CustomException(this.getClass(), LIST,PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE);

        String keyword=constructKeyword(searchForm);

        List<ArticleDTO> articleDTOList = articleService.getListBySearch(pageNo,pageSize,keyword);
        UtilPagination utilPagination=articleService.getPaginationBySearch(pageNo,pageSize,keyword);
        modelAndView.addObject(ARTICLE_DTO_LIST,articleDTOList);
        modelAndView.addObject(PAGINATION,utilPagination);
        modelAndView.setViewName(FOLDER+ PAGE_SEARCH);
        return modelAndView;
    }

    @GetMapping(value = {"/create"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView getCreate(Article article, ModelAndView modelAndView){
        modelAndView.addObject("article",article);
        modelAndView.setViewName(FOLDER+ PAGE_CREATE);
        return modelAndView;

    }

    @PostMapping(value = {"/create"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView create(@ModelAttribute @Valid Article article, BindingResult bindingResult, ModelAndView modelAndView, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER+ PAGE_CREATE);
            return modelAndView;
        }

        Boolean b=articleService.create(article,principal);
        if(b) {
            modelAndView.setViewName(REDIRECT_ARTICLE_LIST_1);
            return  modelAndView;
        }else
            throw new CustomException(this.getClass(), WRITE, ARTICLECONTROLLER_WRITE_FAIL);
    }


    @GetMapping(value = {"/read/{id}"})
    private ModelAndView read(ModelAndView modelAndView, @PathVariable Integer id) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),READ,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        ArticleDTO articleDTO=articleService.read(id);

        if(articleDTO==null)
            throw new CustomException(this.getClass(), READ, ARTICLECONTROLLER_READ_EMPTY);

        modelAndView.addObject(ARTICLE_DTO,articleDTO);
        modelAndView.setViewName(FOLDER+ PAGE_READ);
        return modelAndView;
    }

    @GetMapping(value = {"/update/{id}"})
    private ModelAndView getUpdate(@PathVariable Integer id, ModelAndView modelAndView, Principal principal) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(), UPDATE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        ArticleDTO articleDTO=articleService.read(id);
        if(articleDTO==null)
            throw new CustomException(this.getClass(), UPDATE, ARTICLECONTROLLER_READ_EMPTY);

        if(((ArticleServiceImpl)articleService).hasUpdatePermission(principal,articleDTO.getArticle())==false)
            throw new CustomException(this.getClass(), UPDATE, NO_PERMISSION_TO_ACCESS_THIS_ARTICLE);

        modelAndView.addObject(ARTICLE_DTO,articleDTO);
        modelAndView.setViewName(FOLDER+ PAGE_UPDATE);
        return modelAndView;
    }


    @PostMapping(value = {"/update"})
    private ModelAndView update(@ModelAttribute @Valid Article article, BindingResult bindingResult,ModelAndView modelAndView, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if(bindingResult.hasErrors()){
            modelAndView.setViewName(PAGE_UPDATE);
            return  modelAndView;
        }

        Boolean b=articleService.update(article,principal);

        if(b){
            modelAndView.setViewName(REDIRECT_ARTICLE_LIST_1);
            return modelAndView;
        }
        else
            throw new CustomException(this.getClass(), UPDATE, UPDATE_ARTICLE_FAILED);
    }

    @PostMapping( value = {"/delete/{id}"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView delete(ModelAndView modelAndView, @PathVariable Integer id,final  Principal principal) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),DELETE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        Boolean b=articleService.delete(id, principal);

        if(b) {
            modelAndView.setViewName(REDIRECT_ARTICLE_LIST_1);
            return modelAndView;
        }else
            throw new CustomException(this.getClass(), DELETE, ARTICLECONTROLLER_DELETE_FAIL);
    }


    private String constructKeyword(SearchForm searchForm){
        String key=searchForm.getKey();
        String value=searchForm.getValue();

        StringBuilder keyword=new StringBuilder();

        keyword.append(key);

        switch (key){
            case "title":
                keyword.append(" like '%").append(value).append("%'");
                break;
        }

        return keyword.toString();

    }

}
