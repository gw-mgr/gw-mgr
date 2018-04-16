package com.gewei.fuwushang.service;


import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.SalesMan;

/**
 *
 * SalesMan 表数据服务层接口
 *
 */
public interface ISalesManService extends IService<SalesMan> {

    void selectDataGrid(PageInfo pageInfo);

    
}