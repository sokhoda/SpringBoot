package web.app.view.resolvers;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import web.app.view.PdfView;

import java.util.Locale;

public class PdfViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return new PdfView();
    }
}
