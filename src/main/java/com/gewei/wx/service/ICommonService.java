package com.gewei.wx.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.model.AreaInfo;
import com.gewei.model.TChinaArea;
import com.gewei.model.TCustomerBasicinfo;

public interface ICommonService extends IService<TChinaArea> {
	/**
	 * @Author: Tiger
	 * @Description: TODO 根据父区域编号，查子区域列表
	 * @param @param parentId
	 * @param @return
	 * @return: List<TChinaArea>
	 * @date: 2018年3月23日 上午10:50:36
	 * @throws
	 */
	List<TChinaArea> findAreaNodeByParentId(String parentId);

	/**
	 * @Author: Tiger
	 * @Description: TODO 校验客户是否已经存在,若不存在则添加；若存在，则更新客户信息
	 * 		检验项目：用户名，电话号码，车牌号
	 * @param @param tCustomerBasicinfo
	 * @param @return
	 * @return: String
	 * @date: 2018年3月23日 上午10:49:21
	 * @throws
	 */
	String checkCustomerIsExsit(TCustomerBasicinfo tCustomerBasicinfo);

	/**
	 * @Author: Tiger
	 * @Description: TODO 发送短信验证码
	 * @param @param header
	 * @param @param phoneNo
	 * @param @return
	 * @return: RPCResult
	 * @date: 2018年3月23日 下午2:18:11
	 * @throws
	 */
	public void sendSMSCode(String telephone);

	/**
	 * @Author: Tiger
	 * @Description: TODO 区域信息
	 * @param @return
	 * @return: List<AreaInfo>
	 * @date: 2018年3月27日 上午10:35:25
	 * @throws
	 */
	List<AreaInfo> findAreaInfo();

	/**
	 * @Author: Tiger
	 * @Description: TODO
	 * @param @param personId
	 * @param @return
	 * @return: Map<String,Object>
	 * @date: 2018年3月29日 上午10:25:26
	 * @throws
	 */
	Map<String, Object> getLastBankInfoByPersonId(String personId);
}
