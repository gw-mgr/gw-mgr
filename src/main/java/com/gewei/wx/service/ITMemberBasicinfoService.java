package com.gewei.wx.service;

import com.gewei.commons.result.PageInfo;
import com.gewei.model.TMemberBasicinfo;
import com.baomidou.mybatisplus.service.IService;

public interface ITMemberBasicinfoService extends IService<TMemberBasicinfo> {
	void selectDataGrid(PageInfo pageInfo);
}
