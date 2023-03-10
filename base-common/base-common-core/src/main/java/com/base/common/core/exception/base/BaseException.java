package com.base.common.core.exception.base;

import com.base.common.core.domain.R;
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

    private R r;

    public BaseException(String msg) {
        this.r = new R(msg);
    }

    public BaseException(int code, String msg) {
        this.r = new R(code, msg);
    }
}
