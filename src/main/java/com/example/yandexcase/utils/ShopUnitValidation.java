package com.example.yandexcase.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = ShopUnitValidator.class)
@Documented
public @interface ShopUnitValidation {

    String message() default "Ошибка валидации";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
