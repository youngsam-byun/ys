package com.ys.web.controller;

import com.ys.app.exception.CustomException;
import com.ys.app.model.User;
import com.ys.app.model.validator.PasswordUpdateFormValidator;
import com.ys.app.model.validator.form.PasswordUpdateForm;
import com.ys.app.security.CustomUserDetails;
import com.ys.app.security.service.CustomUserDetailsService;
import com.ys.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;


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


    @Value("${userController.signUp.fail}")
    private final String USERCONTROLLER_SIGNUP_FAIL = "userController.signUp.fail";
    @Value("${userController.update.fail}")
    private final String USERCONTROLLER_UPDATE_FAIL = "userController.update.fail";
    @Value("${userController.updatePassword.fail}")
    private static final String USER_CONTROLLER_UPDATE_PASSWORD_FAIL = "userController.updatePassword.fail";
    @Value("${userController.resetPassword.fail}")
    private static final String USER_CONTROLLER_RESET_PASSWORD_FAIL = "userController.resetPassword.fail";

    @Value("${userController.resetPassword.notExist}")
    private static final String USER_DOES_NOT_EXIST = "userController.resetPassword.notExist";

    @Value("${page.size?:10}")
    private Integer pageSize = 10;

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
    public ModelAndView welcome(ModelAndView modelAndview) {
        modelAndview.setViewName(FOLDER + PAGE_WELCOME);
        return modelAndview;
    }

    @GetMapping(value = {"/signup"})
    public ModelAndView getSignUp(ModelAndView modelAndView) {
        modelAndView.setViewName(FOLDER + PAGE_SIGNUP);
        modelAndView.addObject(USER, new User());
        return modelAndView;

    }

    @PostMapping(value = {"/signup"})
    public ModelAndView signUp(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, ModelAndView modelAndView, final Principal principal) {


        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER + PAGE_SIGNUP);
            return modelAndView;
        }

        Boolean b = userService.createUser(user, principal);

        if (b) {
            modelAndView.setViewName(REDIRECT_USER_LOGIN);
            return modelAndView;
        } else
            throw new CustomException(this.getClass(), SIGN_UP, USERCONTROLLER_SIGNUP_FAIL);
    }


    @GetMapping(value = {"/update"})
    public ModelAndView getUpdate(ModelAndView modelAndView) {
        modelAndView.setViewName(FOLDER + PAGE_UPDATE);
        modelAndView.addObject(USER, new User());
        return modelAndView;

    }

    @PostMapping(value = {"/update"})
    public ModelAndView update(@ModelAttribute("user") @Valid User user,BindingResult bindingResult, ModelAndView modelAndView,final Principal principal) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(FOLDER + PAGE_UPDATE);
            return modelAndView;
        }

        Boolean b = userService.updateUser(user, principal);

        if (b) {
            modelAndView.setViewName(REDIRECT_HOME);
            return modelAndView;
        } else
            throw new CustomException(this.getClass(), UPDATE, USERCONTROLLER_UPDATE_FAIL);
    }


    @GetMapping(value = {"/updatePassword"})
    public ModelAndView getUpdatePassword(ModelAndView modelAndView) {
        modelAndView.setViewName(FOLDER + PAGE_UPDATE_PASSWORD);
        return modelAndView;
    }

    @PostMapping(value = {"/updatePassword"})
    public ModelAndView updatePassword(@ModelAttribute @Valid PasswordUpdateForm passwordUpdateForm,
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
    public ModelAndView getResetPassword(@RequestParam("str") String str, ModelAndView modelAndView) {

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

    private boolean isResetExpired(Date updateTime) {
        Date currentTime=new Date();

        return currentTime.getTime()-updateTime.getTime()>=resetExpiration;
    }

    private User retrieveUserFromPrincipal(Principal principal){
        return ((CustomUserDetails)principal).getUser();
    }


}
