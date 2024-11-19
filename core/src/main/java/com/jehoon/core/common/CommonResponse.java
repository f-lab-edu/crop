package com.jehoon.core.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> implements ICommonResposne {

    private final String title;
    private final T content;

    public CommonResponse(String title, T content) {
        this.title = title;
        this.content = content;
    }

    private CommonResponse(String title) {
        this(title, null);
    }

    private CommonResponse(T content) {
        this(null, content);
    }

    public static <T> CommonResponse<T> of(String title, T content) {
        return new CommonResponse<T>(title, content);
    }

    public static <T> CommonResponse<T> of(String title) {
        return new CommonResponse<T>(title);
    }

    public static <T> CommonResponse<T> of(T content) {
        return new CommonResponse<T>(content);
    }
}
