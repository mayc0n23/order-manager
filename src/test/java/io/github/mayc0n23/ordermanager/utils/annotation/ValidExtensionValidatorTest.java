package io.github.mayc0n23.ordermanager.utils.annotation;

import io.github.mayc0n23.ordermanager.model.enums.Extension;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidExtensionValidatorTest {

    @Test
    public void shouldReturnTrueWhenIsValidExtension() {
        Extension[] allowedExtensions = {Extension.TXT};
        ValidExtensionValidator validator = new ValidExtensionValidator();
        validator.initialize(createValidExtensionAnnotation(allowedExtensions));
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Content".getBytes());

        assertTrue(validator.isValid(file, mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void shouldReturnFalseWhenIsInvalidExtension() {
        Extension[] allowedExtensions = {Extension.TXT};
        ValidExtensionValidator validator = new ValidExtensionValidator();
        validator.initialize(createValidExtensionAnnotation(allowedExtensions));
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Content".getBytes());

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(builder);

        assertFalse(validator.isValid(file, context));
    }

    private ValidExtension createValidExtensionAnnotation(Extension[] allowedExtensions) {
        ValidExtension validExtension = mock(ValidExtension.class);
        when(validExtension.allowedExtensions()).thenReturn(allowedExtensions);
        return validExtension;
    }

}