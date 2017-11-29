package pizzaservice;

import businessdomain.Cheque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ChequeRepository;

@Service("chequeService")
public class SimpleChequeService implements ChequeService {
    @Autowired
    @Qualifier("chequeRepository")
    private ChequeRepository chequeRepository;

    public SimpleChequeService() {
    }

    public SimpleChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    @Transactional
    @Override
    public Cheque save(Cheque cheque) {
        return chequeRepository.save(cheque);

    }

    @Override
    public Cheque find(Long id) {
        return chequeRepository.find(id);
    }
}
