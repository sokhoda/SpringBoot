package pizzaservice;

import businessdomain.LoyaltyCard;

public interface LoyaltyCardService {
    LoyaltyCard find(Long id);

    LoyaltyCard save(LoyaltyCard loyaltyCard);
}