package com.example.qgame.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public abstract class IController {

    @Autowired
    protected HttpServletRequest servletRequest;

    protected ModelAndView back(){
        String backUrl = servletRequest.getHeader("Referer");

        return new ModelAndView("redirect:" + backUrl);
    }
}
