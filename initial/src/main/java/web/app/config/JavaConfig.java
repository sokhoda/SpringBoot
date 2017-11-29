package web.app.config;

import businessdomain.Customer;
import businessdomain.Orders;
import businessdomain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import web.app.dto.OrdersList;
import web.app.dto.PizzaList;
import web.app.view.resolvers.ExcelViewResolver;
import web.app.view.resolvers.JsonViewResolver;
import web.app.view.resolvers.PdfViewResolver;
import web.app.view.resolvers.XmlViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JavaConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ApplicationContext appContext;

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(excelViewResolver());
        resolvers.add(pdfViewResolver());
        resolvers.add(xmlViewResolver());
        resolvers.add(jsonViewResolver());
        resolvers.add(((ViewResolver) appContext.getBean("mvcViewResolver")));

        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    @Bean
    public ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }

    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }

    @Bean
    public ViewResolver xmlViewResolver() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Pizza.class, PizzaList.class, Customer.class, Orders.class, OrdersList.class);
        return new XmlViewResolver(marshaller);
    }

    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }
}
