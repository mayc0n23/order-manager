package io.github.mayc0n23.ordermanager.utils.annotation;

import io.github.mayc0n23.ordermanager.model.enums.Extension;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ValidExtensionValidator implements ConstraintValidator<ValidExtension, MultipartFile> {

    private Extension[] allowedExtensions;

    @Override
    public void initialize(ValidExtension constraintAnnotation) {
        this.allowedExtensions = constraintAnnotation.allowedExtensions();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return false;
        }

        for (var allowedExtension : allowedExtensions) {
            if (allowedExtension.getContentType().equalsIgnoreCase(multipartFile.getContentType())) {
                return true;
            }
        }

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                .addConstraintViolation();

        return false;
    }
}
