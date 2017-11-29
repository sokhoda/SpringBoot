package pizzaservice;

import businessdomain.LoyaltyCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import repository.LoyaltyCardRepository;

public class SimpleLoyaltyCardService implements LoyaltyCardService{

    @Autowired
    LoyaltyCardRepository loyaltyCardRepository;

    public SimpleLoyaltyCardService(LoyaltyCardRepository loyaltyCardRepository) {
        this.loyaltyCardRepository = loyaltyCardRepository;
    }

    public SimpleLoyaltyCardService() {
    }

    @Override
    public LoyaltyCard find(Long id) {
        return loyaltyCardRepository.find(id);
    }

    @Transactional
    @Override
    public LoyaltyCard save(LoyaltyCard loyaltyCard) {
        return loyaltyCardRepository.save(loyaltyCard);
    }
}
