package com.jehoon.core.utils;

public class CustomCollectionUtil {

    public static <T> boolean isNullOrEmpty(T[] param) {
        return param == null || param.length == 0;
    }

    // 배열이 초기화 되고 첫번쨰 값으로 NULL이 들어가는 경우가 있는거 방지
    public static <T> boolean isNullOrEmptyWithFirstValue(T[] param) {
        return isNullOrEmpty(param) || param[0] == null;
    }
}
