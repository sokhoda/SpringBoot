package repository;

import businessdomain.LoyaltyCard;

public interface LoyaltyCardRepository {

    LoyaltyCard find(Long id);

    LoyaltyCard save(LoyaltyCard LoyaltyCard);

}
