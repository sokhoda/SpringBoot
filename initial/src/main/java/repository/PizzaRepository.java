package repository;

import businessdomain.Pizza;
import businessdomain.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByType(PizzaType type);

    List<Pizza> findByName(String name);
}
