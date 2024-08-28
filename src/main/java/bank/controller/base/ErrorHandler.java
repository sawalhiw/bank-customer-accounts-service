package bank.controller.base;

import bank.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponseDto> handle(final Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
                .builder()
                .message(exception.getMessage())
                .statusCode(400)
                .build());
    }
}
