package org.adobe.interview.romannumeral.exception.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.adobe.interview.romannumeral.enumerations.RomanNumeralError;
import org.adobe.interview.romannumeral.model.ErrorDetail;
import org.adobe.interview.romannumeral.model.RomanNumeralResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Exception handler for the number converter application.
 */
@ControllerAdvice
@Slf4j
public class RomanNumeralExceptionHandler {


    /**
     * Handles NumberConverterException thrown by the application.
     *
     * @param ex          the exception.
     * @param webRequest  the web request context.
     * @return a response entity containing the error details.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RomanNumeralResponse> handleNumberConverterException(IllegalArgumentException ex, WebRequest webRequest) {
        return buildErrorResponse(RomanNumeralError.INVALID_INPUT_RANGE, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RomanNumeralResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest webRequest) {
        return buildErrorResponse(RomanNumeralError.INVALID_INPUT_RANGE, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MissingServletRequestParameterException when a required query parameter is missing.
     *
     * @param webRequest  the web request context.
     * @return a response entity containing the error details.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RomanNumeralResponse> handleMissingServletRequestParameterException(WebRequest webRequest) {
        return buildErrorResponse(RomanNumeralError.INVALID_INPUT, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles TypeMismatchException when there is a mismatch in the request data type.
     *
     * @param ex          the exception.
     * @param webRequest  the web request context.
     * @return a response entity containing the error details.
     */
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<RomanNumeralResponse> handleTypeMismatchException(TypeMismatchException ex, WebRequest webRequest) {
        return buildErrorResponse(RomanNumeralError.INVALID_INPUT, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic exceptions thrown by the application.
     *
     * @param ex          the exception.
     * @param webRequest  the web request context.
     * @return a response entity containing the error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RomanNumeralResponse> handleGenericException(Exception ex, WebRequest webRequest) {
        logError(webRequest, "Internal server error. Exception: ", ex.getMessage());
        return buildErrorResponse(RomanNumeralError.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Logs the error details including the requested URI and message.
     *
     * @param webRequest  the web request context.
     * @param message     the error message to log.
     * @param details     additional details to include in the log.
     */
    private void logError(WebRequest webRequest, String message, String details) {
        ServletWebRequest request = (ServletWebRequest) webRequest;
        String uri = request.getRequest().getRequestURI();
        log.error("Requested URI: {} - {} {}", uri, message, details != null ? details : "");
    }

    /**
     * Builds a response entity containing the error details.
     *
     * @param error       the error details.
     * @param status      the HTTP status to return.
     * @return a response entity with the error details.
     */
    private ResponseEntity<RomanNumeralResponse> buildErrorResponse(RomanNumeralError error, HttpStatus status) {
        RomanNumeralResponse response = RomanNumeralResponse.builder()
                .romanNumeralError(ErrorDetail.builder().code(error.getErrorCode())
                                                        .message(error.getErrorMessage()).build())
                .build();
        return new ResponseEntity<>(response, status);
    }

}
