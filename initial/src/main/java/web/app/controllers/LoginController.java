package web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import web.app.services.LoginService;
import web.infrastructure.Routes;

import javax.inject.Inject;

@Controller
public class LoginController {
    @Inject
    private LoginService loginService;

    @RequestMapping(name = Routes.APP_LOGINP)
    public String login() {
        return Routes.LOGIN_PAGE;
    }

}
