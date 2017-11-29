package validators;

import businessdomain.DomainHelper;
import businessdomain.Pizza;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import web.app.dto.PizzaList;

@Component
public class PizzaValidator implements Validator {
    private static final Integer PIZZA_MAX_PRICE = 999;
    private static final String PIZZA_PRICE_CAN_T_BE_NEGATIVE = "pizza price can`t be negative";
    private static final String PIZZA_PRICE_IS_TOO_HIGH = "pizza price is too high";
    private static final String PIZZA_NAME_IS_REQUIRED = "pizza name is required";
    private static final String NEGATIVE_VALUE = "negativeValue";
    private static final String PRICE_TOO_HIGH = "price.too.high";
    private static final String NAME_REQUIRED = "name.required";

    @Override
    public boolean supports(Class<?> clazz) {
        return Pizza.class.equals(clazz) || PizzaList.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!(target instanceof Pizza)) {
            return;
        }
        Pizza pizza = (Pizza) target;
        Object[] errorArgs = {DomainHelper.PIZZA};
        if (pizza.getPrice() < 0) {
            errors.rejectValue(DomainHelper.PIZZA_PRICE, NEGATIVE_VALUE, errorArgs, PIZZA_PRICE_CAN_T_BE_NEGATIVE);
        }
        if (pizza.getPrice() > PIZZA_MAX_PRICE) {
            errors.rejectValue(DomainHelper.PIZZA_PRICE, PRICE_TOO_HIGH, new Object[]{DomainHelper.PIZZA, PIZZA_MAX_PRICE}, PIZZA_PRICE_IS_TOO_HIGH);
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, DomainHelper.PIZZA_NAME, NAME_REQUIRED, errorArgs, PIZZA_NAME_IS_REQUIRED);
    }
}
