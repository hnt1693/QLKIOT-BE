package com.fil.authentication.config;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.exceptions.AuthenticationException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExpHandlers extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        List<String> errors = new ArrayList<String>();
        Map<String, String> mapError = new HashMap<>();
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrorList) {
            if (!mapError.containsKey(error.getField())) {
                mapError.put(error.getField(), error.getDefaultMessage());
                errors.add(error.getDefaultMessage());
            }
        }


        ResponseAPI responseData = new ResponseAPI(HttpStatus.BAD_REQUEST, "Dư liệu không hợp lê", null, mapError);
        return handleExceptionInternal(
                ex, responseData, headers, HttpStatus.OK, request);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        List<String> errors = new ArrayList<>();
        errors.add(error);
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.BAD_REQUEST, "Params is not valid", null);
        return handleExceptionInternal(
                ex, responseData, headers, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        List<String> errors = new ArrayList<>();
        errors.add(builder.toString());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.METHOD_NOT_ALLOWED, "Method not supported", errors);
        return handleExceptionInternal(
                ex, responseData, headers, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        List<String> errors = new ArrayList<>();
        errors.add(builder.toString());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage(), errors);
        return handleExceptionInternal(
                ex, responseData, headers, HttpStatus.OK, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.BAD_REQUEST, "Dữ liệu không hợp lệ", null, errors);
        return handleExceptionInternal(
                ex, responseData, new HttpHeaders(), HttpStatus.OK, request);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> sqlExceptionHandler(org.hibernate.exception.ConstraintViolationException ex,
                                                      WebRequest request) {
        List<String> errors = new ArrayList<>();

        errors.add(ex.getConstraintName().replaceAll("_", " ").replace("constraint", "") + " đã tồn tại");
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.BAD_REQUEST, "Dữ liệu không hợp lệ", null, errors);
        return handleExceptionInternal(
                ex, responseData, new HttpHeaders(), HttpStatus.OK, request);
    }


    @ExceptionHandler({BadCredentialsException.class,
            InsufficientAuthenticationException.class, OAuth2AuthenticationException.class,
            InvalidGrantException.class, InternalAuthenticationServiceException.class,
            AuthenticationException.class, InvalidGrantException.class
    })
    public ResponseEntity<Object> handleBadCredentialsException(Exception ex,
                                                                WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.UNAUTHORIZED, "Xác thực không thành công", null, errors);
        return handleExceptionInternal(
                ex, responseData, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.BAD_REQUEST, "Method type mismatch", errors);
        return handleExceptionInternal(
                ex, responseData, new HttpHeaders(), HttpStatus.OK, request);
    }


    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.FORBIDDEN, "Truy cập bị từ chối", null, errors);
        return handleExceptionInternal(
                ex, responseData, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.BAD_REQUEST, "HttpMediaTypeNotAcceptable", errors);
        return handleExceptionInternal(
                ex, responseData, new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFound(Exception ex,
                                                       WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.BAD_REQUEST, "Dữ liệu không hợp lệ", null, errors);
        return handleExceptionInternal(
                ex, responseData, new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex,
                                            WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        logger.error(ex.getStackTrace()[0].getClassName() + " : " + ex.getLocalizedMessage());
        ResponseAPI responseData = new ResponseAPI(HttpStatus.BAD_REQUEST, "Thao tác không thành công", null, errors);
        return handleExceptionInternal(
                ex, responseData, new HttpHeaders(), HttpStatus.OK, request);
    }

}
