package com.kadu.validator;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
    ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidFolderValidator.class)
@Documented
public @interface ValidFolder
{

    String message() default "The folder is invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
