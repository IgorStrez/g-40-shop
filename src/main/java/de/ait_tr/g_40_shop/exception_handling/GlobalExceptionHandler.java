package de.ait_tr.g_40_shop.exception_handling;

import de.ait_tr.g_40_shop.exception_handling.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 3 способ обработки ошибок
    // ПЛЮС -  мы получаем глобальный обработчик ошибок, который обрабатывает
    //         ошибки, выбрасываемые в любом месте нашего проекта, то есть нам
    //         не требуется писать такие обработчики в разных местах проекта.
    //         Также удобство в том, что вся логика обработки ошибок
    //         сконцентрирована в одном месте - в этом классе-адвайсе
    // МИНУС - данный способ нам не подходит, если нам требуется различная
    //         обработка одного и того же исключения для разных контроллеров
    @ExceptionHandler(ThirdTestException.class)
    public ResponseEntity<Response> handleException(ThirdTestException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FourthTestException.class)
    public ResponseEntity<Response> handleException(FourthTestException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response> handleException(ProductNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Response> handleException(CustomerNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerInactiveException.class)
    public ResponseEntity<Response> handleException(CustomerInactiveException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SavingProductException.class)
    public ResponseEntity<Response> handleException(SavingProductException e) {
        Throwable cause = e.getCause();
        Response response = cause == null ?
                new Response(e.getMessage()) :
                new Response(e.getMessage(), cause.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoActiveProductsException.class)
    public ResponseEntity<Response> handleException(NoActiveProductsException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConfirmationFailedException.class)
    public ResponseEntity<Response> handleException(ConfirmationFailedException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}