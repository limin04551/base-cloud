package com.base.common.core.domain;

import cn.hutool.core.date.DateTime;
import com.base.common.core.constant.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @author min
 * @description
 * @date 2020/9/11
 */
@Data
@Slf4j
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;
    private Date timestamp;

    private R() {
        timestamp = DateTime.now();
    }


    public R(T data) {
        this();
        this.code = HttpStatus.SUCCESS;
        this.msg = HttpStatus.SUCCESS_MSG;
        this.data = data;
    }

    public R(String message) {
        this();
        this.code = HttpStatus.ERROR;
        this.msg = message;
    }



    public R(int code, String message) {
        this();
        this.code = code;
        this.msg = message;
    }


}
