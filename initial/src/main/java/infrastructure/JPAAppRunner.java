package infrastructure;

import businessdomain.Cheque;
import businessdomain.Pizza;
import businessdomain.PizzaType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPAAppRunner {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory
                ("jpa");
//        System.out.println("metamodel" );
//        Set<EntityType<?>> mm = emf.getMetamodel().getEntities();
//        Set<ManagedType<?>> mt = emf.getMetamodel().getManagedTypes();
//
//        for (EntityType<?> entityType : mm) {
//            System.out.println(entityType.getName());
//        }
//
//        for (ManagedType<?> managedType : mt) {
//            System.out.println(managedType.getJavaType());
//        }

        EntityManager em = emf.createEntityManager();
        Pizza pizza = new Pizza(null, "Tomato", 90.1, PizzaType.VEGETERIAN);

        TypedQuery<Pizza> q = em.createQuery("Select p from Pizza p", Pizza.class);
        List<Pizza> list = q.getResultList();
        System.out.println(list);

        EntityTransaction et = em.getTransaction();
        et.begin();
        System.out.println("before persist " +  pizza.getPizzaId());
        System.out.println("contains before=" + em.contains(pizza));
        em.persist(pizza);

        Cheque cheque = em.find(Cheque.class, 1L);
        System.out.println("_____________________________");
        cheque.getDiscountList();

        Pizza pizza2 = em.getReference(Pizza.class, 2L);
//        Orders aorders2 = em.getRefqerence(Orders.class, 2L);

//        em.merge(pizza);
//        pizza.setStreetName("224324234");
//        System.out.println("orders2=" + aorders2);
//        aorders2.getCustomer();

//        System.out.println("pizza2=" + pizza2);
        System.out.println("after persist " +  pizza.getPizzaId());
        System.out.println("contains after=" + em.contains(pizza));
//        em.persist(oak);

        et.rollback();

        em.clear();

        et.begin();
        Pizza pizzaL = em.find(Pizza.class, 5L);
        pizzaL.setPrice(44.2);
//        em.refresh(pizzaL);
        System.out.println("Pizza " + pizzaL );
        System.out.println(em.contains(pizzaL));
//        em.detach(pizzaL);
//        System.out.println("detached Pizza " + pizzaL );
//        System.out.println(em.contains(pizzaL));
//        em.merge(pizzaL);
//        System.out.println("merged Pizza " + pizzaL );
//        System.out.println(em.contains(pizzaL));
        et.commit();

        System.out.println("contains==" + em.contains(pizzaL));
        em.clear();
        System.out.println("contains==" + em.contains(pizzaL));



        Pizza pizza1 = em.find(Pizza.class, 5L);
        System.out.println(pizza == pizza1);

        em.close();
        emf.close();
    }
}
