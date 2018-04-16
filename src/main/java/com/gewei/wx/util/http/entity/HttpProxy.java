package com.gewei.wx.util.http.entity;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.HttpHost;

import com.gewei.wx.util.StringUtil;



/**
 * 
 * <pre>
 * <b>Http代理路由.</b>
 * <b>Description:</b>     
 * <b>Author:</b> tlibo 973297639@qq.com
 * <b>Date:</b> 2016年11月28日下午2:51:24
 * <b>Copyright:</b> Copyright ©1998-2016 iyooc.cn Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver   Date                    Author                           Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2016年11月28日下午2:51:24   tlibo 973297639@qq.com            new file.
 * </pre>
 */
public class HttpProxy implements Serializable {

    /** 序列化版本号. */
    private static final long serialVersionUID = 1L;

    protected String host = null;

    protected Integer port = null;

    // 当前使用的总次数.
    protected final AtomicLong usedCount = new AtomicLong(1);

    // 失败的总次数.
    protected final AtomicLong failedCount = new AtomicLong(1);

    /**
     * 构造方法.
     * 
     * @param host
     * @param port
     */
    public HttpProxy(String host, Integer port) {
        super();
        this.host = host;
        this.port = port;
    }

    /**
     * 判断代理路由是否有效.
     * 
     * @return
     */
    public boolean isValid() {
        return (StringUtil.hasText(this.host) && null != this.port);
    }

    /**
     * 返回 HOST地址.
     * 
     * @return
     */
    public String getHost() {
        return this.host;
    }

    /**
     * 返回 端口号.
     * 
     * @return
     */
    public Integer getPort() {
        return this.port;
    }

    /**
     * 构建 HttpHost实例.
     * 
     * @return
     */
    public HttpHost bulidHttpHost() {
        return new HttpHost(this.host, this.port);
    }

    /**
     * 累计当前使用的总次数加 1.
     * 
     * @return long 当前使用的总次数.
     */
    public long usedIncrement() {
        return this.usedCount.getAndIncrement();
    }

    /**
     * 累计当前失败的总次数加 1.
     * 
     * @return long 当前失败的总次数.
     */
    public long failedIncrement() {
        return this.usedCount.getAndIncrement();
    }

    /**
     * 当前失败的总次数.
     * 
     * @return
     */
    public long getUsedCount() {
        return this.usedCount.get();
    }

    /**
     * 当前失败的总次数.
     * 
     * @return
     */
    public long getFailedCount() {
        return this.failedCount.get();
    }

    /**
     * 当前成功的总次数.
     * 
     * @return
     */
    public long getSuccessCount() {
        return this.getUsedCount() - this.getFailedCount();
    }

    @Override
    public String toString() {
        return "host:" + this.host + ",port:" + this.port;
    }
}
