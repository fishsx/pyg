package com.songxin.pyg.vo;

import java.io.Serializable;

/**
 * 操作结果VO
 * @author fishsx
 * @date 2018/12/4 下午7:22
 */
public class OperateResultVO implements Serializable {
    private static final long serialVersionUID = 4819411463031364811L;

    private Boolean isSuccess;
    private String message;
    private Object data;

    public OperateResultVO(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public OperateResultVO(Boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public OperateResultVO(Boolean isSuccess, String message, Object data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
