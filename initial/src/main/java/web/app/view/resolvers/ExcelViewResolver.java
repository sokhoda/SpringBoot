package web.app.view.resolvers;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import web.app.view.ExcelView;

import java.util.Locale;

public class ExcelViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return new ExcelView(viewName);
    }
}
