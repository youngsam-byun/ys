package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.User;
import com.ys.app.model.validator.PasswordUpdateFormValidator;
import com.ys.app.model.validator.form.PasswordUpdateForm;
import com.ys.app.model.validator.form.SearchForm;
import com.ys.app.security.CustomUserDetails;
import com.ys.app.security.service.CustomUserDetailsService;
import com.ys.app.service.UserService;
import com.ys.app.util.UtilPagination;
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
import java.util.Date;
import java.util.List;


/**
 * Created by byun.ys on 4/18/2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final String FOLDER = "/user";

    private static final String REDIRECT_USER_LOGIN = "redirect:/user/welcome";
    private static final String REDIRECT_HOME = "redirect:/";

    private static final String USER = "user";
    private static final String UPDATE = "update";
    private static final String SIGN_UP = "signUp";
    private static final String UPDATE_PASSWORD = "updatePassword";

    private static final String PAGE_LOGIN = "/user_login.jsp";
    private static final String PAGE_WELCOME = "/user_welcome.jsp";
    private static final String PAGE_SIGNUP = "/user_signUp.jsp";
    private static final String PAGE_UPDATE= "/user_update.jsp";
    private static final String PAGE_UPDATE_PASSWORD ="/user_updatePassword.jsp" ;

    private static final String PAGE_RESET_PASSWORD_EXPIRED = "/user_resetPasswordExpired.jsp";
    private static final String RESET_PASSWORD = "resetPassword";
    private static final String REDIRECT_USER_UPDATE_PASSWORD = "redirect:/user/updatePassword";
    private static final String PAGE_USER_LIST = "user_list.jsp";
    private static final String PAGE_LIST_BY_SEARCH = "user_listBySearch.jsp";
    private static final String USER_LIST = "userList";
    private static final String PAGE_SEARCH = "/user_search.jsp";
    private static final String PAGINATION = "pagination";


    @Value("${userController.signUp.fail?:userController.signUp.fail}")
    private String USERCONTROLLER_SIGNUP_FAIL;
    @Value("${userController.update.fail?:userController.update.fail}")
    private String USERCONTROLLER_UPDATE_FAIL;
    @Value("${userController.updatePassword.fail?:userController.updatePassword.fail}")
    private String USER_CONTROLLER_UPDATE_PASSWORD_FAIL;
    @Value("${userController.resetPassword.fail?:userController.resetPassword.fail}")
    private String USER_CONTROLLER_RESET_PASSWORD_FAIL ;

    @Value("${userController.resetPassword.notExist?:userController.resetPassword.fail}")
    private String USER_DOES_NOT_EXIST ;

    @Value("${page.size?:10}")
    private Integer pageSize;

    private UserService userService;
    private PasswordUpdateFormValidator passwordUpdateFormValidator;

    @Value("${resetExpiration}")
    private long resetExpiration=3600000;

    @Autowired
    public UserController(UserService userService, PasswordUpdateFormValidator passwordUpdateFormValidator) {
        this.userService = userService;
        this.passwordUpdateFormValidator =passwordUpdateFormValidator;
    }


    @GetMapping(value = {"/login"})
    private ModelAndView home(ModelAndView modelAndview) {
        modelAndview.setViewName(FOLDER + PAGE_LOGIN);
        return modelAndview;
    }

    @GetMapping(value = {"/welcome"})
    private ModelAndView welcome(ModelAndView modelAndview) {
        modelAndview.setViewName(FOLDER + PAGE_WELCOME);
        return modelAndview;
    }

    @GetMapping(value = {"/signup"})
    private ModelAndView getSignUp(ModelAndView modelAndView) {
        modelAndView.setViewName(FOLDER + PAGE_SIGNUP);
        modelAndView.addObject(USER, new User());
        return modelAndView;

    }

    @PostMapping(value = {"/signup"})
    private ModelAndView signUp(@ModelAttribute @Valid User user, BindingResult bindingResult, ModelAndView modelAndView, final Principal principal) {


        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER + PAGE_SIGNUP);
            return modelAndView;
        }

        Boolean b = userService.create(user);

        if (b) {
            modelAndView.setViewName(REDIRECT_USER_LOGIN);
            return modelAndView;
        } else
            throw new CustomException(this.getClass(), SIGN_UP, USERCONTROLLER_SIGNUP_FAIL);
    }


    @GetMapping(value = {"/update"})
    private ModelAndView getUpdate(ModelAndView modelAndView) {
        modelAndView.setViewName(FOLDER + PAGE_UPDATE);
        modelAndView.addObject(USER, new User());
        return modelAndView;

    }

    @PostMapping(value = {"/update"})
    private ModelAndView update(@ModelAttribute("user") @Valid User user,BindingResult bindingResult, ModelAndView modelAndView,Principal principal) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER + PAGE_UPDATE);
            return modelAndView;
        }

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Boolean b = userService.update(user, principal);

        if (b) {
            modelAndView.setViewName(REDIRECT_HOME);
            return modelAndView;
        } else
            throw new CustomException(this.getClass(), UPDATE, USERCONTROLLER_UPDATE_FAIL);
    }


    @GetMapping(value = {"/updatePassword"})
    private ModelAndView getUpdatePassword(ModelAndView modelAndView) {
        modelAndView.setViewName(FOLDER + PAGE_UPDATE_PASSWORD);
        return modelAndView;
    }

    @PostMapping(value = {"/updatePassword"})
    private ModelAndView updatePassword(@ModelAttribute @Valid PasswordUpdateForm passwordUpdateForm,
                                       BindingResult bindingResult,
                                       ModelAndView modelAndView,
                                       Principal principal
                                       ) {

        //contrived injection for testing, spring security mocking not retrieving pricipal
        //it should pull from spring security contextholder
        principal=(Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        passwordUpdateFormValidator.validate(passwordUpdateForm,bindingResult);

        if(bindingResult.hasErrors()){
            modelAndView.setViewName(FOLDER+PAGE_UPDATE_PASSWORD);
            return  modelAndView;
        }

        String password=passwordUpdateForm.getPassword();
        String passwordConfirm=passwordUpdateForm.getPasswordConfirm();


        User user= retrieveUserFromPrincipal(principal);
        user.setPassword(password);
        user.setPasswordConfirm(passwordConfirm);

        Boolean b = userService.updatePassword(user, principal);

        if (b) {
            modelAndView.setViewName(REDIRECT_HOME);
            return modelAndView;
        } else
            throw new CustomException(this.getClass(), UPDATE_PASSWORD, USER_CONTROLLER_UPDATE_PASSWORD_FAIL);
    }


    @GetMapping(value = {"/resetPassword"})
    private ModelAndView getResetPassword(@RequestParam("str") String str, ModelAndView modelAndView) {

        User user= userService.readByStr(str);
        if(user==null)
            throw new CustomException(this.getClass(),RESET_PASSWORD, USER_DOES_NOT_EXIST);

        Date updateTime=user.getUpdateTime();
        if(isResetExpired(updateTime)) {
            modelAndView.setViewName(PAGE_RESET_PASSWORD_EXPIRED);
            return  modelAndView;
        }

        CustomUserDetailsService.programticSignIn(user);
        
        modelAndView.setViewName(REDIRECT_USER_UPDATE_PASSWORD);
        return modelAndView;
    }

    @GetMapping(value = {"/list","/list/{pageNo}"})
    @PreAuthorize("hasAnyRole('ADMIN')")
    private ModelAndView getList(@PathVariable(required = false) Integer pageNo, ModelAndView modelAndView) {

        if(pageNo==null)
            pageNo=1;

        List<User> userList=userService.getList(pageNo,pageSize);
        UtilPagination utilPagination=userService.getPagination(pageNo,pageSize);

        modelAndView.addObject(USER_LIST,userList);
        modelAndView.addObject(PAGINATION,utilPagination);
        modelAndView.setViewName(FOLDER + PAGE_USER_LIST);
        return modelAndView;
    }

    @PostMapping(value = {"/search","/search/{pageNo}"})
    @PreAuthorize("hasAnyRole('ADMIN')")
    private ModelAndView getListBySearch(@ModelAttribute SearchForm searchForm,BindingResult bindingResult,@PathVariable(required = false) Integer pageNo,  ModelAndView modelAndView) {

        if(bindingResult.hasErrors()){
            modelAndView.setViewName(PAGE_SEARCH);
            return modelAndView;
        }

        if(pageNo==null)
            pageNo=1;

        String keyword=constructKeyword(searchForm);

        List<User> userList=userService.getListBySearch(pageNo,pageSize,keyword);
        UtilPagination utilPagination=userService.getPaginationBySearch(pageNo,pageSize,keyword);

        modelAndView.addObject(USER_LIST,userList);
        modelAndView.addObject(PAGINATION,utilPagination);
        modelAndView.setViewName(FOLDER + PAGE_LIST_BY_SEARCH);
        return modelAndView;
    }

    private boolean isResetExpired(Date updateTime) {
        Date currentTime=new Date();

        return currentTime.getTime()-updateTime.getTime()>=resetExpiration;
    }

    private User retrieveUserFromPrincipal(Principal principal){
        return ((CustomUserDetails)principal).getUser();
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
            case "email":
                keyword.append(" like '%").append(value).append("%'");
                break;
            case "username":
                keyword.append(" like '%").append(value).append("%'");
                break;
        }

        return keyword.toString();

    }

}
