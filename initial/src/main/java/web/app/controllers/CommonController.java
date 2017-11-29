package web.app.controllers;

import exceptions.CustomerAddressException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.app.services.LoginService;
import web.infrastructure.Routes;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;


@Controller
public class CommonController {
    private static final String ADDRESS_NOT_VALID = "Address not valid";
    @Inject
    private LoginService loginService;

    @RequestMapping(Routes.DASHBOARD)
    public ModelAndView redirectDashboard(ModelAndView modelAndView, HttpSession session) {
        return loginService.doUserLogin(session, modelAndView);
    }

    @RequestMapping(Routes.CUSTOMER_ADDRESS_EXCEPTION)
    public void customerAddressException() {
        throw new CustomerAddressException(ADDRESS_NOT_VALID);
    }
}
