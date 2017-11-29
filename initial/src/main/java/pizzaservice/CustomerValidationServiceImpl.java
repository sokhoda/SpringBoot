package pizzaservice;

import businessdomain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {
    private static final String CUSTOMER = "customer";

    @Inject
    private ValidatorFactory validatorFactory;

    @Override
    public boolean isNotValid(Customer customer, BindingResult bindingResult, Class<?> group) {
        Validator customerValidator = validatorFactory.getValidator();
        Set<ConstraintViolation<Customer>> violations = customerValidator.validate(customer, group);
        for (ConstraintViolation<Customer> violation : violations) {
            String path = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            bindingResult.addError(new FieldError(CUSTOMER, path, message));
        }
        return violations.size() > 0;
    }
}