package repository;

import businessdomain.Customer;
import businessdomain.Orders;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.states.State;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Repository("orderRepository")
public class JPAOrderRepo implements OrderRepository {

    @PersistenceContext
    private EntityManager em;


//    @PersistenceUnit()
//    private EntityManagerFactory emf;


    @Override
    public Orders find(Long id) {
        return em.find(Orders.class, id);
    }

    @Override
    public List<Orders> findAll() {
        TypedQuery<Orders> query = em.createNamedQuery("Order.findAll", Orders.class);
        return query.getResultList();
    }

    @Override
    public List<Orders> findByCustomer(Customer customer) {
        TypedQuery<Orders> query = em.createNamedQuery("Order" +
                ".findByCustomer", Orders.class);
        return query.setParameter("customer", customer).getResultList();
    }

    @Override
    @Transactional
    public Orders save(Orders order) {
        return em.merge(order);
    }

    @Override
    public List<Orders> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        TypedQuery<Orders> query = em.createNamedQuery("Order.findByDateBetween", Orders.class);
        return query.setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();
    }

    @Override
    public List<Orders> findByCustomerByState(Customer customer, State state) {
        TypedQuery<Orders> query = em.createNamedQuery("Order.findByCustomerByState", Orders.class);
        return query.setParameter("customer", customer)
                .setParameter("state", state)
                .getResultList();
    }

    @Override
    public List<Orders> findByDateBetweenByState(LocalDateTime fromDate, LocalDateTime toDate, State state) {
        TypedQuery<Orders> query = em.createNamedQuery("Order.findByDateBetweenByState", Orders.class);
        return query.setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .setParameter("state", state)
                .getResultList();
    }

    @Override
    public List<Orders> findByDateBetweenByStateByCustomer(
            LocalDateTime fromDate, LocalDateTime toDate, State state, Customer customer) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> order = cq.from(Orders.class);
        cq.select(order).
                where(cb.between(order.get("cheque").get("date"), fromDate,
                        toDate));

        TypedQuery<Orders> query = em.createQuery(cq);
        List<Orders> orderList = query.getResultList();
        return orderList;
    }
}
