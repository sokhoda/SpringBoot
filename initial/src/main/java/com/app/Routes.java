package com.app;

public class Routes {
    //    login
    public static final String APP_LOGINP = "/app/loginp";
    public static final String LOGIN_PAGE = "login";

    //    dashboard
    public static final String REDIRECT_DASHBOARD_PAGE = "redirect:../dashboard";
    public static final String DASHBOARD = "/dashboard";

    //    order

    public static final String ORDER_EDIT_PAGE = "/order/orderedit";
    public static final String ORDER_LIST_PAGE = "/order/orderlist";
    public static final String ORDER_CREATE = "/order/create";
    public static final String ORDER_ADDNEW = "/order/addnew";
    public static final String ORDERS_LIST = "/orders/list";

    //    pizza
    public static final String PIZZA_EDIT_PAGE = "pizza/pizzaedit";
    public static final String REDIRECT_PIZZA_LIST_PAGE = "redirect:../pizza/list";
    public static final String PIZZA_LIST_PAGE = "pizza/pizzalist";
    public static final String PIZZA_CREATE = "/pizza/create";
    public static final String PIZZA_EDIT = "/pizza/edit";
    public static final String PIZZA_ADD_NEW = "/pizza/addnew";
    public static final String PIZZA_TYPE_EXCEPTION = "/pizza/pizzatypeexception";
    public static final String PIZZA_PRICE_EXCEPTION = "/pizza/pizzapriceexception";
    public static final String PIZZA_PIZZALIST_UPLOAD = "/pizza/pizzalist/upload";
    public static final String PIZZA_REMOVE = "/pizza/remove";
    public static final String PIZZA_LIST = "/pizza/list";

    //    customer
    public static final String CUSTOMER_EDIT_PAGE = "customer/customeredit";
    public static final String REDIRECT_CUSTOMER_LIST_PAGE = "redirect:../customer/list";
    public static final String CUSTOMER_LIST_PAGE = "customer/customerlist";
    public static final String CUSTOMER_CREATE = "/customer/create";
    public static final String CUSTOMER_EDIT = "/customer/edit";
    public static final String CUSTOMER_ADD_NEW = "/customer/addnew";
    public static final String CUSTOMER_CUSTOMERLIST_UPLOAD = "/customer/customerlist/upload";
    public static final String CUSTOMER_REMOVE = "/customer/remove";
    public static final String CUSTOMER_LIST = "/customer/list";
    //    utils
    public static final String UTILS_UPLOAD_CUSTOMER_LIST_PAGE = "utils/upload/customerListUpload";
    public static final String UTILS_UPLOAD_PIZZA_LIST_UPLOAD_PAGE = "utils/upload/pizzaListUpload";

    //    exceptions
    public static final String CUSTOMER_ADDRESS_EXCEPTION = "/customeraddressexception";
    public static final String PIZZA_PIZZALIST_UPLOAD_IOEXCEPTION = "/pizza/pizzalist/upload/ioexception";

    //    mail
    public static final String MAIL_SEND = "/mail/send";
}
