package repository;

import businessdomain.Pizza;
import businessdomain.PizzaType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    Pizza find(Long id);

    List<Pizza> findByType(PizzaType type);

    List<Pizza> findByName(String name);
}
