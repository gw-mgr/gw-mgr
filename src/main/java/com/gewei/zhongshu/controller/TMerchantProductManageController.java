package com.gewei.zhongshu.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.fuwushang.service.IMessageService;
import com.gewei.fuwushang.service.ITProductManageService;
import com.gewei.model.Message;
import com.gewei.model.TMerchantProductCategory;
import com.gewei.model.TProductManage;
import com.gewei.model.TProductManageResp;
import com.gewei.model.vo.ProductInfoVo;
import com.gewei.zhongshu.service.ITMerchantProductCategoryService;
import com.gewei.zhongshu.service.ITMerchantProductManageService;
import com.gewei.zhongshu.service.IUserService;

/**
 * <p>
 * 服务商的产品管理 前端控制器
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-29
 */
@Controller
@RequestMapping("/mgr/tMerchantProductManage")
public class TMerchantProductManageController extends BaseController {
	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private ITProductManageService tProductManageService;
	@Autowired
	private ITMerchantProductManageService tMerchantProductManageServiceImpl;
	@Autowired
	private ITProductManageService iTProductManageServiceImpl;
	@Autowired
	private IMessageService iMessageServiceImpl;
	@Autowired
	private ITMerchantProductCategoryService iTMerchantProductCategoryServiceImpl;

	@GetMapping("/manager")
	public String manager() {
		return "admin/tMerchantProductManage/tMerchantProductManageList";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(ProductInfoVo productInfoVo, Integer page, Integer rows, String sort, String order) {
		System.out.println("productInfoVo:" + JSON.toJSONString(productInfoVo));
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = getCondition(productInfoVo);
		pageInfo.setCondition(condition);
		tMerchantProductManageServiceImpl.selectDataGrid(pageInfo);
		return pageInfo;
	}

	private Map<String, Object> getCondition(ProductInfoVo productInfoVo) {
		Map<String, Object> condition = new HashMap<String, Object>();
		String keywordType = productInfoVo.getKeywordType();
		String keywordInfo = productInfoVo.getKeywordInfo();
		String productType = productInfoVo.getProductType();
		if (productType != null && !"all".equals(productType)) {
			condition.put("productType", productType);
		}
		if (keywordType != null && keywordInfo != null && !"".equals(keywordInfo)) {
			condition.put(keywordType, keywordInfo);
		}
		return condition;
	}

	// 产品适用范围上下架操作
	@GetMapping("/productInfo")
	public String getProductInfo(Model model, String productId, String productType) {
		try {
			System.out.println("productId:" + productId + ",productType:" + productType);
			TProductManage tProductManage = tProductManageService.selectProductByProductId(productId);
			TProductManageResp tProductManageResp = new TProductManageResp();
			BeanUtils.copyNotNullProperties(tProductManage, tProductManageResp);
			float currPrice = (float) tProductManage.getCurrPrice() / 100;
			tProductManageResp.setCurrPriceStr(currPrice + "(元)");
			float origPrice = (float) tProductManage.getOrigPrice() / 100;
			tProductManageResp.setOrigPriceStr(origPrice + "(元)");
			float lMixBuyMoney = (float) tProductManage.getlMixBuyMoney() / 100;
			tProductManageResp.setlMixBuyMoneyResp(lMixBuyMoney);
			long commission = tProductManage.getCommission();
			float commissionRate = tProductManage.getCommissionRate();
			// 佣金计算
			float com = currPrice * commissionRate / 100 + (float) commission / 100;
			String rate = "";
			if (commissionRate > 0) {
				rate = "&nbsp;&nbsp;&nbsp;佣金比例：" + commissionRate + "(%)";
			}
			DecimalFormat decimalFormat = new DecimalFormat("##0.00");
			String p = decimalFormat.format(com);
			tProductManageResp.setCommissionStr(p + "(元)" + rate);
			// 社保要求格式化
			tProductManageResp.setdSocialFlag("0".equals(tProductManage.getdSocialFlag()) ? "无" : "有");
			// 婚姻要求格式化
			if ("0".equals(tProductManage.getdMarryFlag())) {
				tProductManageResp.setdMarryFlag("未婚");
			} else if ("1".equals(tProductManage.getdMarryFlag())) {
				tProductManageResp.setdMarryFlag("离异");
			} else if ("2".equals(tProductManage.getdMarryFlag())) {
				tProductManageResp.setdMarryFlag("已婚");
			}
			// 车辆要求格式化
			if ("0".equals(tProductManage.getdCarFlag())) {
				tProductManageResp.setdCarFlag("无");
			} else if ("1".equals(tProductManage.getdCarFlag())) {
				tProductManageResp.setdCarFlag("按揭");
			} else if ("2".equals(tProductManage.getdCarFlag())) {
				tProductManageResp.setdCarFlag("全款");
			}
			// 房产要求格式化
			if ("0".equals(tProductManage.getdHouseFlag())) {
				tProductManageResp.setdHouseFlag("无");
			} else if ("1".equals(tProductManage.getdHouseFlag())) {
				tProductManageResp.setdHouseFlag("按揭");
			} else if ("2".equals(tProductManage.getdHouseFlag())) {
				tProductManageResp.setdHouseFlag("全款");
			}
			// 工作性质要求格式化
			if ("01".equals(tProductManage.getdWorkType())) {
				tProductManageResp.setdWorkType("民企");
			} else if ("02".equals(tProductManage.getdWorkType())) {
				tProductManageResp.setdWorkType("国企");
			} else if ("03".equals(tProductManage.getdWorkType())) {
				tProductManageResp.setdWorkType("公务员");
			} else if ("04".equals(tProductManage.getdWorkType())) {
				tProductManageResp.setdWorkType("自由职业");
			}
			// 收入格式化
			if ("1".equals(tProductManage.getdIncome())) {
				tProductManageResp.setdIncome("1000-3000(元)");
			} else if ("2".equals(tProductManage.getdIncome())) {
				tProductManageResp.setdIncome("3000-6000(元)");
			} else if ("3".equals(tProductManage.getdIncome())) {
				tProductManageResp.setdIncome("6000-10000(元)");
			} else if ("4".equals(tProductManage.getdIncome())) {
				tProductManageResp.setdIncome("10000-30000(元)");
			} else if ("5".equals(tProductManage.getdIncome())) {
				tProductManageResp.setdIncome("3万以上(元)");
			}
			model.addAttribute("tProductManage", tProductManageResp);
			if ("ROOTDK".equals(productType)) {
				System.out.println("tProductManage====" + JSON.toJSONString(tProductManageResp));
				return "admin/tMerchantProductManage/tProductManageDK";
			} else if ("ROOTLC".equals(productType)) {
				System.out.println("tProductManage====" + JSON.toJSONString(tProductManageResp));
				return "admin/tMerchantProductManage/tProductManageLC";
			} else if ("ROOTCM".equals(productType)) {
				System.out.println("tProductManage====" + JSON.toJSONString(tProductManageResp));
				return "admin/tMerchantProductManage/tProductManageCM";
			} else if (productType.contains("ROOTCD")) {
				System.out.println("tProductManage====" + JSON.toJSONString(tProductManageResp));
				return "admin/tMerchantProductManage/tProductManageCD";
			} else {
				System.out.println(123);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 产品适用范围上下架操作
	@PostMapping("/update")
	@ResponseBody
	public Object updateProductStatus(String productId, String status) {
		try {
			Long currentTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			// 产品校验
			EntityWrapper<TProductManage> entityWrapper = new EntityWrapper<TProductManage>();
			entityWrapper.eq("PRODUCT_ID", productId);
			TProductManage productManage = iTProductManageServiceImpl.selectOne(entityWrapper);
			if (productManage == null)
				return renderError("商品不存在");
			// status校验
			if (!(status.equals("01") || status.equals("02") || status.equals("03")))
				return renderError("status参数有误");
			// 修改产品状态
			productManage.setUpdateTime(currentTime);
			// 记录操作人
			Subject currentUser = SecurityUtils.getSubject();
			PrincipalCollection collection = currentUser.getPrincipals();
			String operatorId = null;
			if (null != collection) {
				String loginName = collection.getPrimaryPrincipal().toString();
				operatorId = userServiceImpl.getUserIdByLoginName(loginName);
				productManage.setHandleId(operatorId);
			}
			productManage.setStatus(status);
			iTProductManageServiceImpl.updateByProductId(productManage);
			// 上下架产品，通知服务商，插入消息记录
			String productType = productManage.getProductType();
			EntityWrapper<TMerchantProductCategory> entityWrapper2 = new EntityWrapper<TMerchantProductCategory>();
			entityWrapper2.eq("CATEGORY_ID", productType);
			TMerchantProductCategory merchantProductCategory = iTMerchantProductCategoryServiceImpl.selectOne(entityWrapper2);
			if (status.equals("03")) {
				// 强制下架
				Message message = new Message();
				message.setContent("(" + merchantProductCategory.getCategoryName() + "）的产品" + productManage.getProductName() + "被强制下架，如有疑问，请联系客服。");
				message.setUserId(productManage.getMerchantId());
				message.setType("12");
				message.setCreateTime(currentTime.toString());
				message.setUpdateTime(currentTime.toString());
				message.setOperatorId(operatorId);
				iMessageServiceImpl.insert(message);
			} else if (status.equals("01")) {
				// 恢复上架
				Message message = new Message();
				message.setContent("(" + merchantProductCategory.getCategoryName() + "）的产品" + productManage.getProductName() + "已重新上架，快去看看吧！");
				message.setUserId(productManage.getMerchantId());
				message.setType("11");
				message.setCreateTime(currentTime.toString());
				message.setUpdateTime(currentTime.toString());
				message.setOperatorId(operatorId);
				iMessageServiceImpl.insert(message);
			}
		} catch (Exception e) {
			return renderError("修改失败！");
		}
		return renderSuccess("修改成功！");
	}
}
