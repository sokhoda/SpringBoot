package repository;

import businessdomain.Pizza;
import businessdomain.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza find(Long id);

    List<Pizza> findByType(PizzaType type);

    List<Pizza> findByName(String name);
}
