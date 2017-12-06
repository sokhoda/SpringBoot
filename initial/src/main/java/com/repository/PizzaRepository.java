package com.repository;

import com.businessdomain.Pizza;
import com.businessdomain.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByType(PizzaType type);

    List<Pizza> findByName(String name);
}
