package com.jehoon.serviceapi.config;

import com.jehoon.core.common.CommonResponse;
import com.jehoon.core.common.CommonRollbackException;
import com.jehoon.core.common.ICommonResposne;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@RestControllerAdvice
public class ExceptionConfig {

    // 모든 예외 처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    final ICommonResposne handleCommonException(Exception e) {
        logRequestDetail(e);
        return CommonResponse.of("오류가 발생했습니다. 관리자에게 문의해주세요");
    }

    // 개발자가 의도적으로 반환한 예외 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommonRollbackException.class)
    final ICommonResposne handleCommonRollbackException(CommonRollbackException e) {
        log.error(e.getMessage(), e);
        if (StringUtils.isEmpty(e.getMessage())) {
            return CommonResponse.of(e.getTitle());
        }
        return CommonResponse.of(e.getTitle(), e.getMessage());
    }

    // JSON 형태가 올바르지 않을 떄
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    final ICommonResposne handleHttpMessageConversionException(HttpMessageConversionException e) {
        return CommonResponse.of("매개변수값이 올바르지 않습니다");
    }

    // 필수값 빼먹었을떄
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    final ICommonResposne handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errorMsg = new StringBuilder();

        for (var fieldError : e.getBindingResult().getFieldErrors()) {
            errorMsg
                    .append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("\n");
            ;
        }

        if (!errorMsg.isEmpty()) {
            errorMsg.setLength(errorMsg.length() - 1);
        }

        return CommonResponse.of("다음 필드에서 오류가 발생했습니다:", errorMsg.toString());
    }

    void logRequestDetail(Exception e) {
        var requestattributes = RequestContextHolder.getRequestAttributes();

        if (!(requestattributes instanceof ServletRequestAttributes)) {
            log.error("비동기 또는 다른 동작에 의해 호출됨");
            return;
        }

        var servletRequest = ((ServletRequestAttributes) requestattributes).getRequest();
        var requestURI = servletRequest.getRequestURI();
        var httpMethod = servletRequest.getMethod();

        // 쿼리 파라미터 로그
        var queryParams = new StringBuilder();
        var parameterNames = servletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            var paramName = parameterNames.nextElement();
            queryParams
                    .append(paramName)
                    .append("=")
                    .append(servletRequest.getParameter(paramName))
                    .append("\n");
        }

        log.error("ERROR API NAME : {} {} \n Params: {} , Error Message: {}"
                , httpMethod
                , requestURI
                , queryParams.toString()
                , e.getMessage()
        );
    }

}
