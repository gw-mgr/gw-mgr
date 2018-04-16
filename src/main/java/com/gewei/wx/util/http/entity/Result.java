package com.gewei.wx.util.http.entity;

import com.gewei.wx.util.StringUtil;

/**
 * 
 * <pre>
 * <b>接口响应数据 封装模型.</b>
 * <b>Description:</b>     
 * <b>Author:</b> tlibo 973297639@qq.com
 * <b>Date:</b> 2016年11月28日下午2:51:58
 * <b>Copyright:</b> Copyright ©1998-2016 iyooc.cn Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver   Date                    Author                           Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2016年11月28日下午2:51:58   tlibo 973297639@qq.com            new file.
 * </pre>
 */
public class Result {

    /** 序列化版本号. */
    private static final long serialVersionUID = 1L;

    /**
     * 结果代码, 一般默认：200 为成功, -200为异常.
     */
    protected int code;

    /**
     * 结果对象, 当success==true, data方可有效.
     */
    protected Object data;

    /**
     * 结果消息.
     */
    protected String msg;

    /**
     * 异常信息实例.
     */
    protected Throwable cause;

    /**
     * 构造方法
     */
    public Result() {
        this.code = 200;
    }

    /**
     * 构造方法
     * 
     * @param code 响应状态码
     */
    public Result(final int code) {
        this(code, null, null, null);
    }

    /**
     * 构造方法, 用于正常
     * 
     * @param result 结果对象
     */
    public Result(final Object data) {
        this(200, data, null, null);
    }

    /**
     * 构造方法, 用于正常
     * 
     * @param code 响应状态码
     * @param result 结果对象
     */
    public Result(final int code, final Object data) {
        this(code, data, null, null);
    }

    /**
     * 构造方法, 用于失败
     * 
     * @param code 响应状态码
     * @param msg
     */
    public Result(final int code, final String msg) {
        this(code, null, msg, null);
    }

    /**
     * 构造方法, 用于失败.
     * 
     * @param code 响应状态码
     * @param msg
     * @param cause
     */
    public Result(final int code, final String msg, final Throwable cause) {
        this(code, null, msg, cause);
    }

    /**
     * 构造方法, 用于自定义结果信息.
     * 
     * @param code
     * @param data
     * @param msg
     * @param cause
     */
    public Result(final int code, final Object data, final String msg, final Throwable cause) {
        super();
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.cause = cause;
    }
    /**
     * 构造方法, 用于自定义结果信息.
     * 
     * @param code
     * @param data
     * @param msg
     */
    public Result(final int code, final Object data, final String msg) {
        super();
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
    /**
     * 获取 结果代码.
     * 
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置 结果代码, 具有覆盖性.
     * 
     * @param msg
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回 结果对象.
     * 
     * @return Object
     */
    public Object getData() {
        return data;
    }

    /**
     * 返回 结果对象并转为指定类型.
     * 
     * @param <T> 指定类型
     * @param clazz 指定类型
     * @return T
     */
    public <T> T getData(final Class<T> clazz) {
        return (null != data ? clazz.cast(data) : null);
    }

    /**
     * 设置结果.
     * 
     * @param data
     */
    public void setData(final Object data) {
        this.data = data;
    }

    /**
     * 获取 结果消息.
     * 
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置 结果消息, 具有覆盖性.
     * 
     * @param msg
     */
    public void setMsg(final String msg) {
        this.msg = msg;
    }

    /**
     * 在原有msg 前面追加结果信息.
     * 
     * @param msg
     */
    public void prependMsg(final String msg) {
        this.msg = (StringUtil.hasText(msg) ? msg : "") + ", " + this.msg;
    }

    /**
     * 在原有msg 后面附加结果信息.
     * 
     * @param msg
     */
    public void appendMsg(final String msg) {
        this.msg = this.msg + ", " + (StringUtil.hasText(msg) ? msg : "");
    }

    /**
     * 获取 异常实例.
     * 
     * @return
     */
    public Throwable getCause() {
        return cause;
    }

    /**
     * 设置 异常实例
     * 
     * @param cause
     */
    public void setCause(final Throwable cause) {
        this.cause = cause;
    }

}
