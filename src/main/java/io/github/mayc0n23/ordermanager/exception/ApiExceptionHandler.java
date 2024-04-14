package io.github.mayc0n23.ordermanager.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Objects;

import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.GENERIC_ERROR_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.VALIDATION_ERROR_MESSAGE;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetails> handleEntityNotFoundException(EntityNotFoundException ex) {
        final var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(buildProblemDetails(status, ex.getMessage()));
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<ProblemDetails> handleValidationFailedException(ValidationFailedException ex) {
        final var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(buildProblemDetails(status, ex.getMessage()));
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<ProblemDetails> handleDomainValidationException(DomainValidationException ex) {
        final var status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status)
                .body(buildProblemDetails(status, ex.getMessage()));
    }

    @ExceptionHandler(ReadFileErrorException.class)
    public ResponseEntity<ProblemDetails> handleReadFileErrorException(ReadFileErrorException ex) {
        final var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status)
                .body(buildProblemDetails(status, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        final var bindingResult = ex.getBindingResult();
        final var fieldErrors = bindingResult.getFieldErrors();
        final var messageError = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(VALIDATION_ERROR_MESSAGE);

        return ResponseEntity.status(status)
                .body(buildProblemDetails(HttpStatus.valueOf(status.value()), messageError));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleException(Exception ignored) {
        final var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status)
                .body(buildProblemDetails(status, GENERIC_ERROR_MESSAGE));
    }

    private ProblemDetails buildProblemDetails(HttpStatus status, String detail) {
        return ProblemDetails.builder()
                .status(status.value())
                .detail(detail)
                .timestamp(OffsetDateTime.now())
                .build();
    }

}