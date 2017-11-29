package pizzaservice;

import businessdomain.Cheque;
import businessdomain.DiscountRecord;

import java.util.List;

public interface DiscountRecordService {

    DiscountRecord find(Long id);

    List<DiscountRecord> findByCheque(Cheque cheque);

    DiscountRecord save(DiscountRecord discountRecord);

}
