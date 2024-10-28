package com.np.suprimpoudel.exception.handler;

import com.np.suprimpoudel.dto.Base;
import com.np.suprimpoudel.exception.SubError;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Base<String> handleRuntimeException(RuntimeException exception) {
        Base<String> customException = new Base<>();
        customException.setData(exception.getMessage());
        return customException;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Base<String> handleGeneralException(Exception exception) {
        Base<String> customException = new Base<>();
        customException.setData(exception.getMessage());
        return customException;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public Base<String> handleExpiredJWTToken() {
        Base<String> customException = new Base<>();
        customException.setData("Session expired. Please login again to continue");
        return customException;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Base<String> handleBadCredentialsException() {
        Base<String> customException = new Base<>();
        customException.setData("Invalid username or password");

        return customException;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Base<String> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        Base<String> customException = new Base<>();
        String userFriendlyMessage = parseConstraintViolationMessage(exception.getMessage());
        customException.setData(userFriendlyMessage);

        return customException;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Base<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Base<String> customException = new Base<>();
        customException.setData("Invalid arguments");

        List<SubError> customExceptionList = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> customExceptionList.add(new SubError(error.getField(), error.getDefaultMessage())));
        customException.setErrors(customExceptionList);

        return customException;
    }

    private String parseConstraintViolationMessage(String originalMessage) {
        Pattern pattern = Pattern.compile("Duplicate entry '(.*?)' for key '(.*?)'");
        Matcher matcher = pattern.matcher(originalMessage);
        if (matcher.find()) {
            String duplicateValue = matcher.group(1);
            return "The value '" + duplicateValue + "' already exists";
        }
        return originalMessage;
    }
}