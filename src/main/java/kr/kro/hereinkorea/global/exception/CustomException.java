package kr.kro.hereinkorea.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String Message;
}
