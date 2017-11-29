package pizzaservice;

import businessdomain.Pizza;
import businessdomain.PizzaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PizzaService {
    Pizza save(Pizza pizza);

    Pizza find(Long id);

    List<Pizza> findAll();

    List<Pizza> findByType(PizzaType type);

    List<Pizza> findByName(String name);

    void remove(Pizza pizza);

    String uploadFile(MultipartFile file);
}
