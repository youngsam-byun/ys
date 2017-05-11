package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.Note;
import com.ys.app.model.User;
import com.ys.app.model.dto.NoteDTO;
import com.ys.app.model.validator.form.SearchForm;
import com.ys.app.service.NoteService;
import com.ys.app.service.impl.NoteServiceImpl;
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

import static com.ys.app.security.service.CustomUserDetailsService.extractUser;


/**
 * Created by byun.ys on 4/18/2017.
 */
@Controller
@RequestMapping("/note")
@PreAuthorize("hasAnyRole('USER','OPERATOR','ADMIN')")
public class NoteController {

    private static final String FOLDER="/note";
    private static final String PAGE_LIST = "/note_list";
    private static final String PAGE_READ = "/note_read";
    private static final String PAGE_CREATE = "/note_create";


    private static final String PAGINATION = "pagination";
    private static final String NOTE_DTOLIST = "noteDTOList";
    private static final String READ = "read";
    private static final String WRITE = "create";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String REDIRECT_NOTE_LIST_1 = "redirect:/note/list/1";


    private static final String NOTE = "note";
    private static final int DEFAULT_PAGE_SIZE = 10;


    @Value("${noteController.read.empty?:noteController.read.empty}")
    private String NOTECONTROLLER_READ_EMPTY = "noteController.read.empty";

    @Value("${noteController.delete.fail?:noteController.delete.fail}")
    private String  NOTECONTROLLER_DELETE_FAIL= "noteController.delete.fail";

    @Value("${noteController.create.fail?:noteController.create.fail}")
    private String NOTECONTROLLER_WRITE_FAIL ="noteController.write.fail" ;


    @Value("${noteController.id.notNegative?:noteController.id.notNegative}")
    private String ID_SHOULD_NOT_BE_NEGATIVE_VALUE = "noteController.id.notNegative";


    @Value("${noteController.pageNo.notNegative?:noteController.pageNo.notNegative}")
    private String PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE ;

    @Value("${page.size?:10}")
    private Integer pageSize;

    private NoteService noteService;

    @Autowired
    private NoteController(NoteService noteService) {

        this.noteService = noteService;
        if(pageSize==null)
            pageSize= DEFAULT_PAGE_SIZE;

    }


    @GetMapping(value={"/list","/list/{pageNo}"})
    private ModelAndView home(ModelAndView modelAndView, @PathVariable(required = false) Integer pageNo, Principal principal) {
        if(pageNo==null)
            pageNo=1;

        if(UtilValidation.isNegativeInt(pageNo))
            throw new CustomException(this.getClass(), LIST,PAGENO_SHOULD_NOT_BE_NEGATIVE_VALUE);

        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=extractUser(principal);
        Integer userId=user.getId();

        List<NoteDTO> noteDTOList = noteService.getList(userId,pageNo,pageSize);
        UtilPagination utilPagination=noteService.getPagination(userId,pageNo,pageSize);
        modelAndView.addObject(NOTE_DTOLIST,noteDTOList);
        modelAndView.addObject(PAGINATION,utilPagination);

        modelAndView.setViewName(FOLDER+ PAGE_LIST);
        return modelAndView;
    }

    @GetMapping(value = {"/create"})
    private ModelAndView getCreate(Note note, ModelAndView modelAndView){
        modelAndView.addObject("note",note);
        modelAndView.setViewName(FOLDER+ PAGE_CREATE);
        return modelAndView;

    }

    @PostMapping(value = {"/create"})
    private ModelAndView create(@ModelAttribute @Valid Note note, BindingResult bindingResult, ModelAndView modelAndView, Principal principal) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER+ PAGE_CREATE);
            return modelAndView;
        }

        User user=extractUser(principal);
        Integer userId=user.getId();

        Boolean b=noteService.create(userId,note,principal);
        if(b) {
            modelAndView.setViewName(REDIRECT_NOTE_LIST_1);
            return  modelAndView;
        }else
            throw new CustomException(this.getClass(), WRITE, NOTECONTROLLER_WRITE_FAIL);
    }


    @GetMapping(value = {"/read/{id}"})
    private ModelAndView read(ModelAndView modelAndView, @PathVariable Integer id,Principal principal) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),READ,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);


        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=extractUser(principal);
        Integer userId=user.getId();

        Note note=noteService.read(userId,id,principal);

        if(note==null)
            throw new CustomException(this.getClass(), READ, NOTECONTROLLER_READ_EMPTY);

        modelAndView.addObject(NOTE,note);
        modelAndView.setViewName(FOLDER+ PAGE_READ);
        return modelAndView;
    }

    @PostMapping( value = {"/delete/{id}"})
    private ModelAndView delete(ModelAndView modelAndView, @PathVariable Integer id, Principal principal) {

        if(UtilValidation.isNegativeInt(id))
            throw new CustomException(this.getClass(),DELETE,ID_SHOULD_NOT_BE_NEGATIVE_VALUE);

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        if(principal==null)
            principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user=extractUser(principal);
        Integer userId=user.getId();
        Boolean b=noteService.delete(userId,id, principal);

        if(b) {
            modelAndView.setViewName(REDIRECT_NOTE_LIST_1);
            return modelAndView;
        }else
            throw new CustomException(this.getClass(), DELETE, NOTECONTROLLER_DELETE_FAIL);
    }
}
