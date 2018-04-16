package com.gewei.zhongshu.service;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.commons.result.PageInfo;
import com.gewei.model.OrderInfo;
/**
 * <p>
 * 订单服务类
 * </p>
 *
 * @author tiger
 * @since 2018-02-22
 */
public interface IOrderInfoService extends IService<OrderInfo> {
	long getOrderTotalNum();
	long getOrderTotalMoney();
	long getYesterdayOrderTotalNum();
	long getYesterdayOrderTotalMoney();
	Map<String,Long> getYesterdayOrderNumOfAllOrderType();
	void getOrderInsDataGroupByProvince(PageInfo pageInfo);
	void orderListGroupByProvince(PageInfo pageInfo);
	List<Map<String, String>> getOrderTypeList();
}
