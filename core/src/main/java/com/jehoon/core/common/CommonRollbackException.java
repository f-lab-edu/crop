package com.jehoon.core.common;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class CommonRollbackException extends RuntimeException {

    private final String title;
    private final String message;

    public CommonRollbackException(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public CommonRollbackException(String title) {
        this.title = title;
        this.message = StringUtils.EMPTY;
    }
}
