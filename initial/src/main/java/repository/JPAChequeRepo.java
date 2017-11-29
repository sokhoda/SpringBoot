package repository;

import businessdomain.Cheque;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository("chequeRepository")
public class JPAChequeRepo implements ChequeRepository {
    @PersistenceContext
    private EntityManager em;

    @PersistenceUnit()
    private EntityManagerFactory emf;

    @Override
    public Cheque find(Long id) {
        return em.find(Cheque.class, id);
    }

    @Transactional
    @Override
    public Cheque save(Cheque cheque) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
            Cheque newCheque = em.merge(cheque);
            newCheque.setTitle(Cheque.DEFAULT_TITLE + newCheque.getId());
        tr.commit();
        em.close();
        return newCheque;
    }

}
