package repository;

import businessdomain.Cheque;
import businessdomain.DiscountRecord;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("discountRecordRepository")
public class JPADiscountRecordRepo implements DiscountRecordRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DiscountRecord> findByCheque(Cheque cheque) {
        TypedQuery<DiscountRecord> query = em.createNamedQuery("DiscountRecord.findByCheque",
                DiscountRecord.class);
        return query.setParameter("cheque", cheque).getResultList();
    }

    @Override
    public DiscountRecord find(Long id) {
        return em.find(DiscountRecord.class, id);
    }

    @Override
    public DiscountRecord save(DiscountRecord discountRecord) {
        return em.merge(discountRecord);
    }
}
