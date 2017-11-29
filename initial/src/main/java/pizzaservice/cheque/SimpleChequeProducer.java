package pizzaservice.cheque;

import businessdomain.Cheque;
import businessdomain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.ChequeService;
import pizzaservice.discount.DiscountCalculator;

import java.time.LocalDateTime;
import java.util.Map;

@Service("chequeProducer")
public class SimpleChequeProducer implements ChequeProducer {
    private DiscountCalculator discountCalculator;
    private ChequeService chequeService;

    @Autowired
    public SimpleChequeProducer(DiscountCalculator discountCalculator, ChequeService chequeService) {
        this.discountCalculator = discountCalculator;
        this.chequeService = chequeService;
    }

    public SimpleChequeProducer() {
    }

    @Transactional
    @Override
    public Cheque placeCheque(Map<Pizza, Integer> orderedPizzas) {
        Cheque cheque = createNewCheque();
        cheque.setTitle(Cheque.DEFAULT_TITLE);
        cheque.setDate(LocalDateTime.now());
        cheque.setTotalSum(getOrderSum(orderedPizzas));
        return cheque;
    }

    private Double getOrderSum(Map<Pizza, Integer> pizzaMap) {
        return pizzaMap.entrySet().stream()
                .mapToDouble(this::calculateOrderItemSum)
                .reduce(0., Double::sum);
    }

    private double calculateOrderItemSum(Map.Entry<Pizza, Integer> entry) {
        return entry.getKey().getPrice() * entry.getValue();
    }


    Cheque createNewCheque() {
        throw new IllegalStateException("Container couldn`t create Proxy");
    }

    public void setDiscountCalculator(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public void setChequeService(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
}
