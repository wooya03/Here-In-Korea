package kr.kro.hereinkorea.domain.member.exception;

import kr.kro.hereinkorea.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NotFoundMemberException extends CustomException {
    public static final CustomException EXCEPTION =
            new NotFoundMemberException();
    public NotFoundMemberException() {
        super(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다.");
    }
}
