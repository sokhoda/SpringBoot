//package com.app.com.dto;
//
//import com.businessdomain.DomainHelper;
//import com.businessdomain.Orders;
//
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import java.io.Serializable;
//import java.util.List;
//
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = DomainHelper.ORDERSLIST)
//public class OrdersList implements Serializable {
//
//    @XmlElement(name = DomainHelper.ORDERS, type = Orders.class)
//    private List<Orders> ordersList;
//
//    public OrdersList(List<Orders> ordersList) {
//        this.ordersList = ordersList;
//    }
//
//    public OrdersList() {
//    }
//
//    public List<Orders> getOrdersList() {
//        return ordersList;
//    }
//
//    public void setOrdersList(List<Orders> ordersList) {
//        this.ordersList = ordersList;
//    }
//}
