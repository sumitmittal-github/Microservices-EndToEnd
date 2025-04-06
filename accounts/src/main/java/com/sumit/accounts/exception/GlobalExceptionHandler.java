package com.sumit.accounts.exception;

import com.sumit.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // To handle method arguments errors
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();

        // put all the error messages in a map
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
        validationErrorList.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(validationErrors);
    }


    // global exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(errorResponseDTO);
    }

    // Custom exception handlers
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto( webRequest.getDescription(false), HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now() );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(errorResponseDTO);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception, WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto( webRequest.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now() );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(errorResponseDTO);
    }

}