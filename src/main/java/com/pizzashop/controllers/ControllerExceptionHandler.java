package com.pizzashop.controllers;

import com.pizzashop.error.DtoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Obsługa wyjątków rzuconych przez kontrolery(np. ComplaintController)
 * Created by Bartosz Pigla on 01/01/2017.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerExceptionHandler {
    /**
     *obiekt zawierający listę pól które zostały nieprawidłowo przesłane do webservice
     */
    @Autowired
    MessageSource messageSource;

    /**
     * metoda tworząca listę błędów obieku który nie został prawidłowo zwalidowany
     * @param ex rodzaj wyjątku rzuconego przez kontroler
     * @return obiekt DTO zawierający listę pól oraz błędów
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<DtoError> processValidationError(MethodArgumentNotValidException ex){
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<DtoError> errors=new ArrayList<>();

        for (FieldError fieldError:fieldErrors
                ) {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            errors.add(new DtoError(fieldError.getField(),message));
        }
        return errors;
    }

    /**
     * metoda wywoływana w przypadku niepowodzenia zapisywania obiektu do bazy danych
     * @return komunikat zwracany w przypadku bledow HTML 500
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String runtimeException(){
        return "db error";
    }
}
