package repository;

import businessdomain.Cheque;
import businessdomain.DiscountRecord;

import java.util.List;

public interface DiscountRecordRepository {

    DiscountRecord find(Long id);

    List<DiscountRecord> findByCheque(Cheque cheque);

    DiscountRecord save(DiscountRecord discountRecord);

}
