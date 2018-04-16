package com.gewei.zhongshu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.result.ZTree;
import com.gewei.commons.result.ZTree2;
import com.gewei.commons.shiro.PasswordHash;
import com.gewei.commons.utils.DateUtil;
import com.gewei.fuwushang.service.IMessageService;
import com.gewei.mapper.TMerchantManageMapper;
import com.gewei.model.Message;
import com.gewei.model.TChinaArea;
import com.gewei.model.TMerchantManage;
import com.gewei.wx.service.IAccountService;
import com.gewei.zhongshu.service.ITChinaAreaService;
import com.gewei.zhongshu.service.ITMerchantManageService;
import com.gewei.zhongshu.service.IUserService;

/**
 * <p>
 * 服务商基本信息 服务实现类
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-26
 */
@Service
public class TMerchantManageServiceImpl extends ServiceImpl<TMerchantManageMapper, TMerchantManage> implements ITMerchantManageService {
	@Autowired
	private TMerchantManageMapper tMerchantManageMapper;
	@Autowired
	private ITChinaAreaService iTChinaAreaServiceImpl;
	@Autowired
	private IAccountService iAccountServiceImpl;
	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private IUserService userService;
	@Autowired
	private PasswordHash passwordHash;
	@Autowired
	private ITMerchantManageService tMerchantManageServiceImpl;
	@Autowired
	private IMessageService iMessageServiceImpl;

	@Override
	public List<ZTree2> selectTree() {
		List<TMerchantManage> organizationList = selectTreeGrid();
		List<ZTree2> trees = new ArrayList<ZTree2>();
		if (organizationList != null) {
			for (TMerchantManage organization : organizationList) {
				ZTree2 tree = new ZTree2();
				tree.setId(organization.getMerchantId());
				tree.setName(organization.getMerchantName());
				tree.setIconSkin("fi-folder icon-red");
				tree.setpId("all");
				trees.add(tree);
			}
			// 添加一个全部
			ZTree2 tree2 = new ZTree2();
			tree2.setId("all");
			tree2.setName("全部");
			tree2.setIconSkin("fi-folder icon-blue");
			tree2.setpId("0");
			trees.add(tree2);
		}
		return trees;
	}

	@Override
	public Object selectTree2() {
		List<TMerchantManage> organizationList = selectTreeGrid();
		return organizationList;
	}

	@Override
	public List<TMerchantManage> selectTreeGrid() {
		EntityWrapper<TMerchantManage> wrapper = new EntityWrapper<TMerchantManage>();
		wrapper.orderBy("MERCHANT_NAME");
		return tMerchantManageMapper.selectList(wrapper);
	}

	@Override
	public List<Map<String, String>> selectMerchantTypeList() {
		return tMerchantManageMapper.selectMerchantTypeList();
	}

	@Override
	public List<Map<String, String>> selectProvinceList(String pId) {
		return tMerchantManageMapper.selectProvinceList(pId);
	}

	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
		page.setOrderByField(pageInfo.getSort());
		page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
		List<Map<String, Object>> list = tMerchantManageMapper.selectMerchantPage(page, pageInfo.getCondition());
		for (Map<String, Object> map : list) {
			// 1.授权范围设置
			Object merchantTypeObj = map.get("merchantType");
			if (merchantTypeObj != null) {
				String merchantTypeStr = merchantTypeObj.toString();
				String[] merchantTypeArray = merchantTypeStr.split(",");
				String merchantTypes = "";
				for (String merchantType : merchantTypeArray) {
					if ("ROOTLC".equals(merchantType)) {
						merchantTypes += "理财产品,";
					} else if ("ROOTDK".equals(merchantType)) {
						merchantTypes += "贷款业务,";
					} else if ("ROOTQC".equals(merchantType)) {
						merchantTypes += "二手汽车,";
					} else if ("ROOTCD".equals(merchantType)) {
						merchantTypes += "车务代办,";
					} else if ("ROOTCM".equals(merchantType)) {
						merchantTypes += "汽车美容,";
					}
				}
				// 去除最后一个逗号
				merchantTypes = "".equals(merchantTypes) ? null : merchantTypes.substring(0, merchantTypes.length() - 1);
				map.put("merchantTypeName", merchantTypes);
			}
			// 2.授权区域设置
			String grantAreaNameRes = "";
			Object grantAreaObj = map.get("grantArea");
			if (grantAreaObj != null) {
				String grantAreaStr = grantAreaObj.toString();
				String[] grantAreaArray = grantAreaStr.split(",");
				if (!"".equals(grantAreaArray[0])) {
					HashMap<String, Object> columnMap = new HashMap<String, Object>(1);
					for (String grantArea : grantAreaArray) {
						String grantNameChildRes = "";
						String[] grantAreaIdArray = grantArea.split("-");
						for (String grantAreaId : grantAreaIdArray) {
							columnMap.put("ID", grantAreaId);
							List<TChinaArea> list2 = iTChinaAreaServiceImpl.selectByMap(columnMap);
							if (list2 != null && list2.size() > 0) {
								String grantName = list2.get(0).getName();
								grantNameChildRes += grantName + "-";
							}
						}
						grantNameChildRes = "".equals(grantNameChildRes) ? "" : grantNameChildRes.substring(0, grantNameChildRes.length() - 1);
						grantAreaNameRes += grantNameChildRes + ",";
					}
				}
			}
			grantAreaNameRes = "".equals(grantAreaNameRes) ? "" : grantAreaNameRes.substring(0, grantAreaNameRes.length() - 1);
			map.put("grantAreaName", grantAreaNameRes);
			// 2.服务商地址
			Object province = map.get("province");
			Object city = map.get("city");
			Object country = map.get("country");
			Object merchantAddr = map.get("merchantAddr");
			StringBuffer address = new StringBuffer("");
			if (province != null) {
				address.append(province.toString());
				address.append("-");
			}
			if (city != null) {
				address.append(city.toString());
				address.append("-");
			}
			if (country != null) {
				address.append(country.toString());
				address.append("-");
			}
			if (merchantAddr != null) {
				address.append(merchantAddr.toString());
			}
			map.put("address", address);
			// 3.服务商状态
			String status = map.get("status").toString();
			if ("01".equals(status)) {
				map.put("statusName", "申请中");
			} else if ("02".equals(status)) {
				map.put("statusName", "已上线");
			} else if ("03".equals(status)) {
				map.put("statusName", "已关闭");
			}
		}
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public List<TMerchantManage> selectAll() {
		EntityWrapper<TMerchantManage> wrapper = new EntityWrapper<TMerchantManage>();
		wrapper.orderBy("seq");
		return tMerchantManageMapper.selectList(wrapper);
	}

	@Override
	public Object selectAllTree() {
		// 获取所有的资源 tree形式，展示
		List<ZTree> trees = new ArrayList<ZTree>();
		String pid = "0";
		List<TChinaArea> tTChinaAreas = tMerchantManageMapper.selectChinaAreaAll(pid);
		if (tTChinaAreas == null) {
			return trees;
		}
		for (TChinaArea tChinaArea : tTChinaAreas) {
			ZTree tree = new ZTree();
			tree.setId(tChinaArea.getId().longValue());
			tree.setpId(tChinaArea.getPid().longValue());
			tree.setName(tChinaArea.getName());
			tree.setIconSkin("fi-flag icon-purple");
			tree.setOpen(tChinaArea.getId() == 0 ? true : false);
			trees.add(tree);
		}
		return trees;
	}

	@Override
	@Transactional
	public void updateStatus(String merchantId, String status) {
		try {
			Long currentTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			// 服务商校验
			TMerchantManage merchantManage = tMerchantManageServiceImpl.getMerchantInfoById(merchantId);
			merchantManage.setUpdateTime(currentTime);
			String applyId = merchantManage.getApplyId();// 申请人
			// 记录操作人
			Subject currentUser = SecurityUtils.getSubject();
			PrincipalCollection collection = currentUser.getPrincipals();
			String operatorId = null;
			if (null != collection) {
				String loginName = collection.getPrimaryPrincipal().toString();
				operatorId = userServiceImpl.getUserIdByLoginName(loginName);
				merchantManage.setOperatorId(operatorId);
			}
			merchantManage.setStatus(status);
			// 上架服务商，插入消息记录：1.通知申请人；2.通知服务商
			if (status.equals("02")) {
				// 1.通知申请人
				Message message = new Message();
				message.setUserId(applyId);
				message.setStatus("01");
				message.setContent("恭喜您，申请成功$#$" + merchantManage.getMerchantName());
				message.setType("32");// 服务商申请成功
				message.setOperatorId(operatorId);
				message.setCreateTime(currentTime.toString());
				message.setUpdateTime(currentTime.toString());
				iMessageServiceImpl.insert(message);
				// 2.通知服务商
				message.setContent("店铺已上架，快去管理产品吧~");
				message.setUserId(merchantId);
				message.setType("13");// 服务商申请成功
				iMessageServiceImpl.insert(message);
			} else if (status.equals("03")) {
				// 强制关闭服务商；1.通知服务商
				Message message = new Message();
				message.setUserId(merchantId);
				message.setStatus("01");
				message.setContent("店铺被强制关闭，如有疑问，请联系客服。");
				message.setType("14");// 服务商申请成功
				message.setOperatorId(operatorId);
				message.setCreateTime(currentTime.toString());
				message.setUpdateTime(currentTime.toString());
				iMessageServiceImpl.insert(message);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public String getParentAreaIdByAreaId(String grantAreaId) {
		return tMerchantManageMapper.getParentAreaIdByAreaId(grantAreaId);
	}

	@Override
	public List<TChinaArea> getChildAreaIdByAreaId(String grantAreaId) {
		return tMerchantManageMapper.getChildAreaIdByAreaId(grantAreaId);
	}

	@Override
	public List<Long> findGrantIdsByMerchantId(String merchantId) {
		String grantIdsByMerchantId = tMerchantManageMapper.findGrantIdsByMerchantId(merchantId);
		List<Long> arrayList = new ArrayList<Long>();
		if (grantIdsByMerchantId != null && !"".equals(grantIdsByMerchantId)) {
			String[] grantIdsByMerchantIdArray = grantIdsByMerchantId.split(",");
			if (grantIdsByMerchantIdArray != null && grantIdsByMerchantIdArray.length > 0) {
				for (String grantIdsByMerchantIds : grantIdsByMerchantIdArray) {
					String[] grantIds = grantIdsByMerchantIds.split("-");
					if (grantIds != null && grantIds.length > 0) {
						for (String grantId : grantIds) {
							if (!arrayList.contains(Long.parseLong(grantId))) {
								arrayList.add(Long.parseLong(grantId));
							}
						}
					}
				}
			}
		}
		return arrayList;
	}

	@Override
	public long getCountExamineMerchant() {
		return tMerchantManageMapper.getCountExamineMerchant();
	}

	@Override
	public List<Map<String, String>> getMerchantTypeList() {
		return tMerchantManageMapper.getMerchantTypeList();
	}

	@Override
	public TMerchantManage getMerchantInfoById(String merchantId) {
		return tMerchantManageMapper.getMerchantInfoById(merchantId);
	}

	@Override
	public List<Map<String, String>> selectMerchantTypeList(String pid) {
		return tMerchantManageMapper.getMerchantTypeList(pid);
	}
}
