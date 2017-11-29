package repository;

import businessdomain.Cheque;

public interface ChequeRepository {
    Cheque find(Long id);

    Cheque save(Cheque newCheque);
}
