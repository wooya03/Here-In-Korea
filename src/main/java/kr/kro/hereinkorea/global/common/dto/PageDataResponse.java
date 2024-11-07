package kr.kro.hereinkorea.global.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PageDataResponse<T> {
    private final T data;
    public static <T> PageDataResponse<T> create(final T data){
        return new PageDataResponse<>(data);
    }
}
