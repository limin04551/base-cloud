package com.base.common.core.exception.base;

import com.base.common.core.domain.R;
import com.base.common.core.utils.MessageUtils;
import io.netty.util.internal.StringUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author min
 * @description
 * @date 2023/3/7
 */
@Data
@Slf4j
public class BaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    private R r;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String code, Object... args) {
        String message = MessageUtils.message(code, args);
        this.r = new R(message);
    }

    public BaseException(String msg) {
        this.r = new R(msg);
    }

    public BaseException(int code, String msg) {
        this.r = new R(code, msg);
    }

    @Override
    public String getMessage() {
        String message;
        message = r.getMsg();
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }
}
