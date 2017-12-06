package com.service;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public interface LoginService {
    ModelAndView doUserLogin(HttpSession session, ModelAndView modelAndView);
}