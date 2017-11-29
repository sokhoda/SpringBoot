package web.app.services;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public interface LoginService {
    ModelAndView doUserLogin(HttpSession session, ModelAndView modelAndView);
}