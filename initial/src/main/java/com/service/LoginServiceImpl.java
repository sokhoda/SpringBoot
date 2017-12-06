package com.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";
    private static final String USER_NAME = "userName";
    private static final String USER_ROLES = "userRoles";
    private static final String DASHBOARD = "dashboard";
    private static final String ANONYMOUS = "anonymous";

    @Inject
    private CustomerService customerService;

    @Override
    public ModelAndView doUserLogin(HttpSession session, ModelAndView modelAndView) {
        populateUserNameAndRoles(modelAndView);
        return customerService.updateUserInSession(modelAndView, session);
    }

    private void populateUserNameAndRoles(ModelAndView model) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addObject(TITLE, "SECURE AREA");
        model.addObject(MESSAGE, "Only Authorised Users Can See This Page");
        model.addObject(USER_NAME, "userName");//getUserName(principal));
        model.addObject(USER_ROLES, "ADMIN_ROLE");//getUserRoles(principal));
        model.setViewName(DASHBOARD);
    }

    private String getUserName(Object principal) {
        if (principal == null || !(principal instanceof UserDetails)) {
            return ANONYMOUS;
        } else {
            return ((UserDetails) principal).getUsername();
        }
    }

    private Collection<String> getUserRoles(Object principal) {
        if (principal == null || !(principal instanceof UserDetails)) {
            return Arrays.asList("none");
        } else {
            Set<String> roles = new HashSet<String>();
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                roles.add(grantedAuthority.getAuthority());
                System.out.println(grantedAuthority.getAuthority());
            }
            return roles;
        }
    }
}