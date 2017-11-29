package web.infrastructure;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyMappingExceptionResolver extends SimpleMappingExceptionResolver {

    public static final String URL = "url";
    public static final String EXCEPTION_ATTRIBUTE = "ex";
    public static final String MVC_EXCEPTION = "MVC exception: %s";

    public MyMappingExceptionResolver() {
        setWarnLogCategory(MyMappingExceptionResolver.class.getName());
    }

    @Override
    public String buildLogMessage(Exception e, HttpServletRequest req) {
        return String.format(MVC_EXCEPTION, e.getLocalizedMessage());
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = super.doResolveException(request, response, handler, ex);
        modelAndView.addObject(URL, request.getRequestURL());
        modelAndView.addObject(EXCEPTION_ATTRIBUTE, ex);

        return modelAndView;
    }
}
