package com.test.mintyn.exception;

import com.test.mintyn.dto.response.CardResponse;
import com.test.mintyn.dto.response.ErrorResponse;
import com.test.mintyn.dto.response.Payload;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.rmi.ServerException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value
            = CardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse
    handleException(CardNotFoundException ex) {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setPayload(new Payload());
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), cardResponse);
    }


    @ExceptionHandler(value
            = EmptyBinException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleException(EmptyBinException ex) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value
            = ServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse
    handleException(ServerException ex) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(value
            = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse
    handleException(BadCredentialsException ex) {
        return new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value
            = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse
    handleException(Exception ex) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

}
