package web.app.view;

import businessdomain.DomainHelper;
import web.infrastructure.Routes;


public class CommonViewHelper {

    public static String resolveModelKey(String viewName, boolean isXmlView) {
        String visibleModelKey = null;
        switch (viewName) {
            case Routes.PIZZA_EDIT_PAGE:
                visibleModelKey = DomainHelper.PIZZA;
                break;
            case Routes.PIZZA_LIST_PAGE:
                visibleModelKey = isXmlView ? DomainHelper.PIZZALIST_FOR_XML_MAPPING : DomainHelper.PIZZALIST;
                break;
            case Routes.ORDER_EDIT_PAGE:
                visibleModelKey = DomainHelper.ORDERS;
                break;
            case Routes.ORDER_LIST_PAGE:
                visibleModelKey = isXmlView ? DomainHelper.ORDERSLIST_FOR_XML_MAPPING : DomainHelper.ORDERSLIST;
                break;
            default:
        }
        return visibleModelKey;
    }
}
