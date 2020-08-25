package deveficiente.mercadolivre.comum.api.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = IdExistsValidator.class)
@Documented
public @interface IdExists {
    String message() default "{registro.nao.existe}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> targetClass();
    String idField() default "id";
}
