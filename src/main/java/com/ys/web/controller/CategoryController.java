package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Category;
import com.ys.app.model.validator.form.SearchForm;
import com.ys.app.service.CategoryService;
import com.ys.app.service.impl.CategoryServiceImpl;
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

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


/**
 * Created by byun.ys on 4/18/2017.
 */
@Controller
@RequestMapping("/category")
@PreAuthorize("hasAnyRole('ADMIN')")
public class CategoryController {

    private static final String FOLDER="/category";
    private static final String PAGE_LIST = "/category_list";
    private static final String PAGE_READ = "/category_read";
    private static final String PAGE_CREATE = "/category_create";
    private static final String PAGE_SEARCH = "category_search";
    private static final String PAGE_UPDATE = "/category_update";


    private static final String PAGINATION = "pagination";
    private static final String CATEGORY_LIST= "categoryList";
    private static final String READ = "read";
    private static final String WRITE = "create";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String REDIRECT_CATEGORY_LIST_1 = "redirect:/category/list/1";
    private static final String UPDATE = "update";
    private static final String NO_PERMISSION_TO_ACCESS_THIS_CATEGORY = "No permission to access this category";

    private static final String CATEGORY = "category";
    private static final int DEFAULT_PAGE_SIZE = 10;


    @Value("${categoryController.read.empty?:categoryController.read.empty}")
    private String CATEGORYCONTROLLER_READ_EMPTY;

    @Value("${categoryController.delete.fail?:categoryController.delete.fail}")
    private String  CATEGORYCONTROLLER_DELETE_FAIL;

    @Value("${categoryController.create.fail?:categoryController.create.fail}")
    private String CATEGORYCONTROLLER_WRITE_FAIL;

    @Value("${categoryController.update.fail?:categoryController.update.fail}")
    private String UPDATE_CATEGORY_FAILED;

    @Value("${categoryController.id.notNegative?:categoryController.id.notNegative}")
    private String ID_SHOULD_NOT_BE_NEGATIVE_VALUE;

    @Value("${categoryController.keyword.notEmpty?:categoryController.keyword.notEmpty}")
    private String SEARCH_KEYWORD_SHOULD_NOT_BE_EMPTY;


    @Value("${categoryController.pageNo.notNegative?:categoryController.pageNo.notNegative}")
    private String PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE = "categoryController.pageNo.notNegative";

    @Value("${page.size?:10}")
    private Integer pageSize;

    private CategoryService categoryService;

    @Autowired
    private CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
        if(pageSize==null)
            pageSize= DEFAULT_PAGE_SIZE;

    }


    @GetMapping(value={"/list","/list/{pageNo}"})
    private ModelAndView home(ModelAndView modelAndView, @PathVariable(required = false) Integer pageNo) {
        if(pageNo==null)
            pageNo=1;

        if(UtilValidation.isNegativeInt(pageNo))
            throw new CustomException(this.getClass(), LIST,PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE);

        List<Category> categoryList = categoryService.getList(pageNo,pageSize);
        UtilPagination utilPagination=categoryService.getPagination(pageNo,pageSize);
        modelAndView.addObject(CATEGORY_LIST,categoryList);
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

        List<Category> categoryList = categoryService.getListBySearch(pageNo,pageSize,keyword);
        UtilPagination utilPagination=categoryService.getPaginationBySearch(pageNo,pageSize,keyword);
        modelAndView.addObject(CATEGORY_LIST,categoryList);
        modelAndView.addObject(PAGINATION,utilPagination);
        modelAndView.setViewName(FOLDER+ PAGE_SEARCH);
        return modelAndView;
    }

    @GetMapping(value = {"/create"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView getCreate(Category category, ModelAndView modelAndView){
        modelAndView.addObject("category",category);
        modelAndView.setViewName(FOLDER+ PAGE_CREATE);
        return modelAndView;

    }

    @PostMapping(value = {"/create"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView create(@ModelAttribute @Valid Category category, BindingResult bindingResult, ModelAndView modelAndView, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER+ PAGE_CREATE);
            return modelAndView;
        }

        Boolean b=categoryService.create(category,principal);
        if(b) {
            modelAndView.setViewName(REDIRECT_CATEGORY_LIST_1);
            return  modelAndView;
        }else
            throw new CustomException(this.getClass(), WRITE, CATEGORYCONTROLLER_WRITE_FAIL);
    }


    @GetMapping(value = {"/read/{id}"})
    private ModelAndView read(ModelAndView modelAndView, @PathVariable Integer id) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),READ,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        Category category=categoryService.read(id);

        if(category==null)
            throw new CustomException(this.getClass(), READ, CATEGORYCONTROLLER_READ_EMPTY);

        modelAndView.addObject(CATEGORY,category);
        modelAndView.setViewName(FOLDER+ PAGE_READ);
        return modelAndView;
    }

    @GetMapping(value = {"/update/{id}"})
    private ModelAndView getUpdate(@PathVariable Integer id, ModelAndView modelAndView, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(), UPDATE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        Category category=categoryService.read(id);
        if(category==null)
            throw new CustomException(this.getClass(), UPDATE, CATEGORYCONTROLLER_READ_EMPTY);

        if(((CategoryServiceImpl)categoryService).hasPermission(principal)==false)
            throw new CustomException(this.getClass(), UPDATE, NO_PERMISSION_TO_ACCESS_THIS_CATEGORY);

        modelAndView.addObject(CATEGORY,category);
        modelAndView.setViewName(FOLDER+ PAGE_UPDATE);
        return modelAndView;
    }


    @PostMapping(value = {"/update"})
    private ModelAndView update(@ModelAttribute @Valid Category category, BindingResult bindingResult,ModelAndView modelAndView, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(bindingResult.hasErrors()){
            modelAndView.setViewName(PAGE_UPDATE);
            return  modelAndView;
        }

        Boolean b=categoryService.update(category,principal);

        if(b){
            modelAndView.setViewName(REDIRECT_CATEGORY_LIST_1);
            return modelAndView;
        }
        else
            throw new CustomException(this.getClass(), UPDATE, UPDATE_CATEGORY_FAILED);
    }

    @PostMapping( value = {"/delete/{id}"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView delete(ModelAndView modelAndView, @PathVariable Integer id, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),DELETE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        Boolean b=categoryService.delete(id, principal);

        if(b) {
            modelAndView.setViewName(REDIRECT_CATEGORY_LIST_1);
            return modelAndView;
        }else
            throw new CustomException(this.getClass(), DELETE, CATEGORYCONTROLLER_DELETE_FAIL);
    }


    private String constructKeyword(SearchForm searchForm){
        String key=searchForm.getKey();
        String value=searchForm.getValue();

        StringBuilder keyword=new StringBuilder();

        keyword.append(key);

        switch (key){
            case "id":
                keyword.append("=").append(value);
                break;
            case "name":
                keyword.append(" like '%").append(value).append("%'");
                break;
        }

        return keyword.toString();

    }

}
