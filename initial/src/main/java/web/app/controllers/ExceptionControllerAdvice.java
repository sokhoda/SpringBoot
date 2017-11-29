package web.app.controllers;

import exceptions.PizzaTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({NumberFormatException.class, PizzaTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String error(Exception ex, HttpServletRequest req, Model model) {

        model.addAttribute("ex", ex);
        model.addAttribute("url", req.getRequestURL());
        return "errorPage";
    }

}
