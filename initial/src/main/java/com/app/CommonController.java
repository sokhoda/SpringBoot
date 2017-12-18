package com.app;

import com.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping("/swagger_api")
    public String toSwagger(Model model) {
        return "dist/swagger-ui";
    }


}
