package io.github.mayc0n23.ordermanager.utils.annotation;

import io.github.mayc0n23.ordermanager.model.enums.Extension;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidExtensionValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExtension {

    String message() default "Extensão de arquivo inválida.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Extension[] allowedExtensions() default {};

}