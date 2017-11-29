package web.app.view.resolvers;

import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;
import web.app.view.CommonViewHelper;

import java.util.Locale;

public class XmlViewResolver implements ViewResolver {
    private Marshaller marshaller;

    public XmlViewResolver(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        MarshallingView view = new MarshallingView();
        view.setModelKey(CommonViewHelper.resolveModelKey(viewName, true));
        view.setMarshaller(marshaller);
        return view;
    }
}
