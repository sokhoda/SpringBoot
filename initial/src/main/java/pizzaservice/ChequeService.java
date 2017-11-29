package pizzaservice;

import businessdomain.Cheque;

public interface ChequeService {

    Cheque save(Cheque newCheque);

    Cheque find(Long id);

}
