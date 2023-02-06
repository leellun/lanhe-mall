package com.newland.lanhe.validator;

import org.springframework.core.annotation.AliasFor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author leell
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {OptionsValidator.class}
)
public @interface IntOptions {
    String message() default "{javax.validation.constraints.NotNull.message}";
    @AliasFor("options")
    int[] value() default {};
    @AliasFor("value")
    int[] options() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
