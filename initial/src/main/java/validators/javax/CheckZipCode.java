package validators.javax;

import businessdomain.AddressType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({PARAMETER, FIELD, METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckZipCodeValidator.class)
@Documented
public @interface CheckZipCode {

    String message() default "{zipcode.vs.address.type.not.valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    AddressType value();
}
