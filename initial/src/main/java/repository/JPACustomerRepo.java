package repository;

import businessdomain.Customer;
import businessdomain.LoyaltyCard;
import infrastructure.JPQLQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository("customerRepository")
public class JPACustomerRepo implements CustomerRepository {

    private static final String SELECT_C_FROM_CUSTOMER = "SELECT c from Customer c ";

    @PersistenceContext
    private EntityManager em;

//    @PersistenceUnit()
//    private EntityManagerFactory emf;

    @Override
    public List<Customer> findAll() {
        return em.createQuery("SELECT cus FROM Customer cus ", Customer.class).getResultList();
    }

    @Override
    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> findByName(String name) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByName",
                Customer.class);
        return query.setParameter("name", name).getResultList();
    }


    @Override
    public List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard) {
        return JPQLQueries.selectResultList(Customer.class, em,
                SELECT_C_FROM_CUSTOMER, "WHERE c.loyaltyCard.id = :id",
                 Collections.singletonList(loyaltyCard.getId()));
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return em.merge(customer);
    }

    @Override
    public int delete(Customer customer) {
        Query query = em.createNamedQuery("Customer.delete");
        return query.setParameter("customer", customer).executeUpdate();
    }

    @Override
    @Transactional
    public void remove(Customer customer) {
        Customer mergedCustomer =  em.merge(customer);
        em.remove(mergedCustomer);
    }


}
