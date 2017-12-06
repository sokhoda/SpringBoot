package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.service.LoginService;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;


@Controller
public class CommonController {
    @Inject
    private LoginService loginService;

    @RequestMapping(Routes.DASHBOARD)
    public ModelAndView redirectDashboard(ModelAndView modelAndView, HttpSession session) {
        return loginService.doUserLogin(session, modelAndView);
    }
}
