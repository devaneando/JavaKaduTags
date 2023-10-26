package com.kadu.validator;

import java.io.File;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidFolderValidator implements ConstraintValidator<ValidFolder, String>
{

    @Override
    public void initialize(ValidFolder constraintAnnotation)
    {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (null == value) {
            return true;
        }

        String fileName = value.trim();
        if (fileName.isEmpty()) {
            return true;
        }

        File file = new File(fileName);

        if (!file.exists()) {
            setValidationMessage(context, "\"" + fileName + "\" does not exist!");

            return false;
        }

        if (!file.isDirectory()) {
            setValidationMessage(context, "\"" + fileName + "\" is not a directory!");

            return false;
        }

        if (0 == file.toPath().getNameCount()) {
            setValidationMessage(context, "The ROOT folder is not allowed!");

            return false;
        }

        if (fileName.equals(System.getProperty("user.home"))) {
            setValidationMessage(context, "The HOME folder is not allowed!");

            return false;
        }

        return true;
    }

    private void setValidationMessage(ConstraintValidatorContext constraintContext, String message)
    {
        constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
