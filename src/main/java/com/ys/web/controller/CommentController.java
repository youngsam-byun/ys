package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Comment;
import com.ys.app.model.dto.CommentDTO;
import com.ys.app.model.validator.form.SearchForm;
import com.ys.app.service.CommentService;
import com.ys.app.service.impl.CommentServiceImpl;
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
@RequestMapping("/comment")
public class CommentController {

    private static final String FOLDER="/comment";
    private static final String PAGE_LIST = "/comment_list";
    private static final String PAGE_READ = "/comment_read";
    private static final String PAGE_WRITE = "/comment_create";
    private static final String PAGE_SEARCH = "comment_search";
    private static final String PAGE_UPDATE = "/comment_update";

    private static final String PAGINATION = "pagination";
    private static final String COMMENT_DTO_LIST= "commentDTOList";
    private static final String COMMENT_DTO = "commentDTO";
    private static final String READ = "read";
    private static final String WRITE = "create";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String REDIRECT_COMMENT_LIST_1 = "redirect:/comment/list/1";
    private static final String UPDATE = "update";
    private static final String NO_PERMISSION_TO_ACCESS_THIS_COMMENT = "No permission to access this comment";
    private static final int DEFAULT_PAGE_SIZE = 10;


    @Value("${commentController.read.empty?:commentController.read.empty}")
    private String COMMENTCONTROLLER_READ_EMPTY;

    @Value("${commentController.delete.fail?:commentController.delete.fail}")
    private String  COMMENTCONTROLLER_DELETE_FAIL;

    @Value("${commentController.create.fail?:commentController.write.fail}")
    private String COMMENTCONTROLLER_WRITE_FAIL;

    @Value("${commentController.update.fail?:commentController.update.fail}")
    private String UPDATE_COMMENT_FAILED;

    @Value("${commentController.id.notNegative?:commentController.id.notNegative}")
    private String ID_SHOULD_NOT_BE_NEGATIVE_VALUE ;

    @Value("${commentController.keyword.notEmpty?:commentController.keyword.notEmpty}")
    private String SEARCH_KEYWORD_SHOULD_NOT_BE_EMPTY;


    @Value("${commentController.pageNo.notNegative?:commentController.pageNo.notNegative}")
    private String PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE ;

    @Value("${page.size?:10}")
    private Integer pageSize;

    private CommentService commentService;

    @Autowired
    private CommentController(CommentService commentService) {
        this.commentService = commentService;

        if(pageSize==null)
            pageSize= DEFAULT_PAGE_SIZE;

    }


    @GetMapping(value={"/list","/list/{pageNo}"})
    private ModelAndView home(ModelAndView modelAndView, @PathVariable(required = false) Integer pageNo) {
        if(pageNo==null)
            pageNo=1;

        if(UtilValidation.isNegativeInt(pageNo))
            throw new CustomException(this.getClass(), LIST,PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE);

        List<CommentDTO> commentDTOList = commentService.getList(pageNo,pageSize);
        UtilPagination utilPagination=commentService.getPagination(pageNo,pageSize);
        modelAndView.addObject(COMMENT_DTO_LIST,commentDTOList);
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

        List<CommentDTO> commentDTOList = commentService.getListBySearch(pageNo,pageSize,keyword);
        UtilPagination utilPagination=commentService.getPaginationBySearch(pageNo,pageSize,keyword);
        modelAndView.addObject(COMMENT_DTO_LIST,commentDTOList);
        modelAndView.addObject(PAGINATION,utilPagination);
        modelAndView.setViewName(FOLDER+ PAGE_SEARCH);
        return modelAndView;
    }

    @GetMapping(value = {"/create"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView getCreate(Comment comment, ModelAndView modelAndView){
        modelAndView.addObject("comment",comment);
        modelAndView.setViewName(FOLDER+ PAGE_WRITE);
        return modelAndView;

    }

    @PostMapping(value = {"/create"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView create(@ModelAttribute @Valid Comment comment, BindingResult bindingResult, ModelAndView modelAndView, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER+ PAGE_WRITE);
            return modelAndView;
        }

        Boolean b=commentService.create(comment,principal);
        if(b) {
            modelAndView.setViewName(REDIRECT_COMMENT_LIST_1);
            return  modelAndView;
        }else
            throw new CustomException(this.getClass(), WRITE, COMMENTCONTROLLER_WRITE_FAIL);
    }


    @GetMapping(value = {"/read/{id}"})
    private ModelAndView read(ModelAndView modelAndView, @PathVariable Integer id) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),READ,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        CommentDTO commentDTO=commentService.read(id);

        if(commentDTO==null)
            throw new CustomException(this.getClass(), READ, COMMENTCONTROLLER_READ_EMPTY);

        modelAndView.addObject(COMMENT_DTO,commentDTO);
        modelAndView.setViewName(FOLDER+ PAGE_READ);
        return modelAndView;
    }

    @GetMapping(value = {"/update/{id}"})
    private ModelAndView getUpdate(@PathVariable Integer id, ModelAndView modelAndView, final Principal principal) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(), UPDATE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        CommentDTO commentDTO=commentService.read(id);
        if(commentDTO==null)
            throw new CustomException(this.getClass(), UPDATE, COMMENTCONTROLLER_READ_EMPTY);

        if(((CommentServiceImpl)commentService).hasUpdatePermission(principal,commentDTO.getComment())==false)
            throw new CustomException(this.getClass(), UPDATE, NO_PERMISSION_TO_ACCESS_THIS_COMMENT);

        modelAndView.addObject(COMMENT_DTO,commentDTO);
        modelAndView.setViewName(FOLDER+ PAGE_UPDATE);
        return modelAndView;
    }


    @PostMapping(value = {"/update"})
    private ModelAndView update(@ModelAttribute @Valid Comment comment, BindingResult bindingResult,ModelAndView modelAndView, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(bindingResult.hasErrors()){
            modelAndView.setViewName(PAGE_UPDATE);
            return  modelAndView;
        }

        Boolean b=commentService.update(comment,principal);

        if(b){
            modelAndView.setViewName(REDIRECT_COMMENT_LIST_1);
            return modelAndView;
        }
        else
            throw new CustomException(this.getClass(), UPDATE, UPDATE_COMMENT_FAILED);
    }

    @PostMapping( value = {"/delete/{id}"})
    @PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
    private ModelAndView delete(ModelAndView modelAndView, @PathVariable Integer id,final  Principal principal) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),DELETE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        Boolean b=commentService.delete(id, principal);

        if(b) {
            modelAndView.setViewName(REDIRECT_COMMENT_LIST_1);
            return modelAndView;
        }else
            throw new CustomException(this.getClass(), DELETE, COMMENTCONTROLLER_DELETE_FAIL);
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
            case "body":
                keyword.append(" like '%").append(value).append("%'");
                break;
        }

        return keyword.toString();

    }

}
