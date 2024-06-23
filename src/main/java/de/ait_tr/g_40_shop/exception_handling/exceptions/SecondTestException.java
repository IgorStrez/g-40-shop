package de.ait_tr.g_40_shop.exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 2 способ обработки ошибок
// ПЛЮС -  быстро и удобно без лишнего кода создаём
//         глобальный обработчик данного исключения
// МИНУС - пользователь не видит сообщения об ошибке,
//         следовательно, не понимает причин её возникновения
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SecondTestException extends RuntimeException {

    public SecondTestException(String message) {
        super(message);
    }
}