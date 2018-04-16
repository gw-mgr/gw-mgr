package com.gewei.fuwushang.service;

import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}