package com.base.common.core.handle;

import com.base.common.core.annotation.IgnorReponseAdvice;
import com.base.common.core.constant.HttpStatus;
import com.base.common.core.domain.R;
import com.base.common.core.exception.base.BaseException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author min
 * @description
 * @date 2021/1/18
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    //判断支持的类型
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 检查注解是否存在，或者返回格式就是R,存在则忽略拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnorReponseAdvice.class)
                || methodParameter.getMethod().isAnnotationPresent(IgnorReponseAdvice.class)
                || methodParameter.getParameterType().equals(R.class)
        ) {
            return false;
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        if (o instanceof String) {
//            return o;
//        }

        return new R<>(o);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R handleNoHandlerFoundException(NoHandlerFoundException e) {
        return new R<>(HttpStatus.NOT_FOUND, "请求地址不存在");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R handleHttpMessageNotReadableExceptionException(HttpMessageNotReadableException e) {
        return new R<>(HttpStatus.ERROR, "请求体缺失");
    }

    @ExceptionHandler(IllegalStateException.class)
    public R handleIllegalStateException(IllegalStateException e) {
        return new R<>(HttpStatus.ERROR, String.format("参数缺失:%s", e.getMessage()));
    }

    @ExceptionHandler(RpcException.class)
    public Object RpcExceptionHandler(RpcException e) {
        e.printStackTrace();
        return new R<>(HttpStatus.ERROR, e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public Object baseExceptionHandler(BaseException e) {
        return e.getR();
    }

    @ExceptionHandler(ValidationException.class)
    public R handleValidationExceptionException(ValidationException e) {
        return new R<>(HttpStatus.ERROR, e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = "";
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            message += error.getDefaultMessage() + ";";
        }
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return new R<>(HttpStatus.ERROR, message);
    }

    /**
     * 处理所有不可知的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        // 打印堆栈信息
        if (e.getStackTrace().length > 0) {
            log.error(e.getStackTrace()[0].getClassName());
            log.error(e.getStackTrace()[0].getLineNumber() + "行 ");
        }
        log.error(e.getClass().getName());
        log.error(e.getMessage());
        e.printStackTrace();
        return new R(HttpStatus.ERROR, e.getStackTrace()[0].getClassName() + ";" + e.getStackTrace()[0].getLineNumber() + "行;" +
                e.getClass().getName() + ";" + e.getMessage());
    }


}
