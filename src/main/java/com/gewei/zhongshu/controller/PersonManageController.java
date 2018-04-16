package com.gewei.zhongshu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.shiro.PasswordHash;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.model.Operator;
import com.gewei.model.OperatorRes;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.model.User;
import com.gewei.model.vo.OperatorVo;
import com.gewei.model.vo.ProductInfoVo;
import com.gewei.zhongshu.service.IOperatorService;
import com.gewei.zhongshu.service.IUserService;

/**
 * @Description: 审核管理控制层
 * @author: Tiger
 * @date: 2018年2月5日 下午8:44:05
 */
@Controller
@RequestMapping("/mgr/personManage")
public class PersonManageController extends BaseController {
	@Autowired
	private IUserService userService;
	@Autowired
	private PasswordHash passwordHash;
	@Autowired
	private IOperatorService iOperatorServiceImpl;

	@PostMapping("/operatorManage/roleList")
	@ResponseBody
	public Object roleList() {
		List<Map<String, String>> roleList = iOperatorServiceImpl.roleList();
		return roleList;
	}

	@GetMapping("/tOperatorManage/addPage")
	public String addPage() {
		return "admin/personManage/operatorManageAdd";
	}

	@GetMapping("/tOperatorManage/editPage")
	public String editPage(Model model, String operatorId) {
		OperatorRes operatorRes = iOperatorServiceImpl.getOperatorById(operatorId);
		boolean rootccCommissionFlag = "01".equals(operatorRes.getRootccCommissionFlag());
		operatorRes.setRootccCommissionFlag(rootccCommissionFlag ? "checked" : "unchecked");
		operatorRes.setRootccCommissionRateFlag(rootccCommissionFlag ? "unchecked" : "checked");
		operatorRes.setRootccCommissionStr(rootccCommissionFlag ? operatorRes.getRootccCommissionValue() / 100 + "" : operatorRes.getRootccCommissionValue() + "");
		boolean rootrsCommissionFlag = "01".equals(operatorRes.getRootrsCommissionFlag());
		operatorRes.setRootrsCommissionFlag(rootrsCommissionFlag ? "checked" : "unchecked");
		operatorRes.setRootrsCommissionRateFlag(rootrsCommissionFlag ? "unchecked" : "checked");
		operatorRes.setRootrsCommissionStr(rootrsCommissionFlag ? operatorRes.getRootrsCommissionValue() / 100 + "" : operatorRes.getRootrsCommissionValue() + "");
		boolean rootdkCommissionFlag = "01".equals(operatorRes.getRootdkCommissionFlag());
		operatorRes.setRootdkCommissionFlag(rootdkCommissionFlag ? "checked" : "unchecked");
		operatorRes.setRootdkCommissionRateFlag(rootdkCommissionFlag ? "unchecked" : "checked");
		operatorRes.setRootdkCommissionStr(rootdkCommissionFlag ? operatorRes.getRootdkCommissionValue() / 100 + "" : operatorRes.getRootdkCommissionValue() + "");
		model.addAttribute("operator", operatorRes);
		return "admin/personManage/operatorManageEdit";
	}

	@GetMapping("/tOperatorManage/lookPage")
	public String lookPage(Model model, String operatorId) {
		OperatorRes operatorRes = iOperatorServiceImpl.getOperatorById(operatorId);
		boolean rootccCommissionFlag = "01".equals(operatorRes.getRootccCommissionFlag());
		operatorRes.setRootccCommissionFlag(rootccCommissionFlag ? "checked" : "unchecked");
		operatorRes.setRootccCommissionRateFlag(rootccCommissionFlag ? "unchecked" : "checked");
		operatorRes.setRootccCommissionStr(rootccCommissionFlag ? operatorRes.getRootccCommissionValue() / 100 + "(元)" : operatorRes.getRootccCommissionValue() + "(%)");
		boolean rootrsCommissionFlag = "01".equals(operatorRes.getRootrsCommissionFlag());
		operatorRes.setRootrsCommissionFlag(rootrsCommissionFlag ? "checked" : "unchecked");
		operatorRes.setRootrsCommissionRateFlag(rootrsCommissionFlag ? "unchecked" : "checked");
		operatorRes.setRootrsCommissionStr(rootrsCommissionFlag ? operatorRes.getRootrsCommissionValue() / 100 + "(元)" : operatorRes.getRootrsCommissionValue() + "(%)");
		boolean rootdkCommissionFlag = "01".equals(operatorRes.getRootdkCommissionFlag());
		operatorRes.setRootdkCommissionFlag(rootdkCommissionFlag ? "checked" : "unchecked");
		operatorRes.setRootdkCommissionRateFlag(rootdkCommissionFlag ? "unchecked" : "checked");
		operatorRes.setRootdkCommissionStr(rootdkCommissionFlag ? operatorRes.getRootdkCommissionValue() / 100 + "(元)" : operatorRes.getRootdkCommissionValue() + "(%)");
		model.addAttribute("operator", operatorRes);
		return "admin/personManage/operatorManageLook";
	}

	@GetMapping("/operatorManage")
	public String operatorManage() {
		return "admin/personManage/operatorManage";
	}

	@PostMapping("/operatorManage/operatorAdd")
	@ResponseBody
	@Transactional
	public Object operatorAdd(OperatorVo operatorVoRe, Integer page, Integer rows, String sort, String order) {
		try {
			String currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString();
			String uuid = UUIDUtil.getUUID32().substring(0, 8);
			String userId = "operator" + currTime + uuid;
			// 插入user
			User user = new User();
			user.setId(userId);
			user.setLoginName(operatorVoRe.getUserName());
			user.setName(operatorVoRe.getUserName());
			user.setUserType("01");
			user.setPhone(operatorVoRe.getTelephone());
			user.setOrganizationId("");
			user.setStatus("01");
			user.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			List<User> list = userService.selectByName(userId, operatorVoRe.getUserName());
			if (list != null && !list.isEmpty()) {
				return renderError("登录名已存在!");
			}
			String salt = StringUtils.getUUId();
			String pwd = passwordHash.toHex("123456", salt);// 默认密码：123456
			user.setSalt(salt);
			user.setPassword(pwd);
			userService.addUser(user);
			// operator插入数据
			Operator operator = new Operator();
			operator.setBankCard(operatorVoRe.getBankCard());
			operator.setBankHost(operatorVoRe.getBankHost());
			operator.setBankName(operatorVoRe.getBankName());
			operator.setDescription(operatorVoRe.getDescription());
			operator.setOperId(userId);
			operator.setMail(operatorVoRe.getMail());
			operator.setRootccCommissionType(1 == Integer.parseInt(operatorVoRe.getRootccCommission()) ? "01" : "02");
			operator.setRootdkCommissionType(1 == Integer.parseInt(operatorVoRe.getRootdkCommission()) ? "01" : "02");
			operator.setRootrsCommissionType(1 == Integer.parseInt(operatorVoRe.getRootrsCommission()) ? "01" : "02");
			operator.setRootccCommissionValue(1 == Integer.parseInt(operatorVoRe.getRootccCommission()) ? Float.parseFloat(operatorVoRe.getRootccCommissionValue()) * 100
					: Float.parseFloat(operatorVoRe.getRootccCommissionValue().toString()));
			operator.setRootdkCommissionValue(1 == Integer.parseInt(operatorVoRe.getRootdkCommission()) ? Float.parseFloat(operatorVoRe.getRootdkCommissionValue()) * 100
					: Float.parseFloat(operatorVoRe.getRootdkCommissionValue().toString()));
			operator.setRootrsCommissionValue(1 == Integer.parseInt(operatorVoRe.getRootrsCommission()) ? Float.parseFloat(operatorVoRe.getRootrsCommissionValue()) * 100
					: Float.parseFloat(operatorVoRe.getRootrsCommissionValue().toString()));
			operator.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			iOperatorServiceImpl.insertOperator(operator);
			// 插入角色
			iOperatorServiceImpl.insertRoleOfOperator(operator.getOperId(), operatorVoRe.getRoleId());
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
		return renderSuccess("添加成功！");
	}

	@PostMapping("/operatorManage/operatorEdit")
	@ResponseBody
	@Transactional
	public Object operatorEdit(OperatorVo operatorVoRe) {
		try {
			System.out.println(JSON.toJSON(operatorVoRe));
			// 修改user
			// 插入user
			User user = new User();
			user.setId(operatorVoRe.getId());
			user.setLoginName(operatorVoRe.getUserName());
			user.setName(operatorVoRe.getUserName());
			user.setUserType("01");
			user.setPhone(operatorVoRe.getTelephone());
			user.setStatus("01");
			user.setOrganizationId("");
			userService.updateUser(user);
			// 修改operator
			Operator operator = new Operator();
			operator.setBankCard(operatorVoRe.getBankCard().toString());
			operator.setBankHost(operatorVoRe.getBankHost().toString());
			operator.setBankName(operatorVoRe.getBankName().toString());
			operator.setDescription(operatorVoRe.getDescription().toString());
			operator.setOperId(operatorVoRe.getId());
			operator.setMail(operatorVoRe.getMail().toString());
			operator.setRootccCommissionType(1 == Integer.parseInt(operatorVoRe.getRootccCommission()) ? "01" : "02");
			operator.setRootdkCommissionType(1 == Integer.parseInt(operatorVoRe.getRootdkCommission()) ? "01" : "02");
			operator.setRootrsCommissionType(1 == Integer.parseInt(operatorVoRe.getRootrsCommission()) ? "01" : "02");
			operator.setRootccCommissionValue(1 == Integer.parseInt(operatorVoRe.getRootccCommission()) ? Float.parseFloat(operatorVoRe.getRootccCommissionValue()) * 100
					: Float.parseFloat(operatorVoRe.getRootccCommissionValue().toString()));
			operator.setRootdkCommissionValue(1 == Integer.parseInt(operatorVoRe.getRootdkCommission()) ? Float.parseFloat(operatorVoRe.getRootdkCommissionValue()) * 100
					: Float.parseFloat(operatorVoRe.getRootdkCommissionValue().toString()));
			operator.setRootrsCommissionValue(1 == Integer.parseInt(operatorVoRe.getRootrsCommission()) ? Float.parseFloat(operatorVoRe.getRootrsCommissionValue()) * 100
					: Float.parseFloat(operatorVoRe.getRootrsCommissionValue().toString()));
			operator.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			iOperatorServiceImpl.updateOperator(operator);
			// 修改user_role
			iOperatorServiceImpl.updateRoleOfOperator(operator.getOperId(), operatorVoRe.getRoleId());
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
		return renderSuccess("添加成功！");
	}

	@PostMapping("/operatorManage/updateStatus")
	@ResponseBody
	@Transactional
	public Object updateOperatorStatus(String operatorId, String status) {
		try {
			Operator operator = new Operator();
			operator.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			operator.setOperId(operatorId);
			iOperatorServiceImpl.updateStatus(operator);
			User user = new User();
			user.setId(operatorId);
			user.setStatus(status);
			userService.updateStatus(user);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("内部错误，修改失败！");
		}
		return renderSuccess("修改成功！");
	}

	@PostMapping("/operatorManage/operatorDataGrid")
	@ResponseBody
	public PageInfo operatorDataGrid(OperatorVo operatorVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		String keywordInfo = operatorVo.getKeywordInfo();
		if (keywordInfo != null && !"".equals(keywordInfo)) {
			String keywordType = operatorVo.getKeywordType();
			if ("userName".equals(keywordType)) {
				condition.put("userName", operatorVo.getKeywordInfo());
			} else if ("telephone".equals(keywordType)) {
				condition.put("telephone", operatorVo.getKeywordInfo());
			}
		}
		pageInfo.setCondition(condition);
		iOperatorServiceImpl.operatorDataGrid(pageInfo);
		return pageInfo;
	}

	@GetMapping("/memberManage/memberLook")
	public String getMemberInfoById(Model model, String memberId) {
		TMemberBasicinfo memberInfo = iOperatorServiceImpl.getMemberInfoById(memberId);
		model.addAttribute("memberInfo", memberInfo);
		return "admin/personManage/memberManageLook";
	}

	@GetMapping("/memberManage")
	public String memberManage() {
		return "admin/personManage/memberManage";
	}

	@PostMapping("/memberDataGrid")
	@ResponseBody
	public Object memberListDataGrid(ProductInfoVo productInfoVo, Integer page, Integer rows, String sort, String order) {
		System.out.println("productInfoVo:" + JSON.toJSONString(productInfoVo));
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		String keywordInfo = productInfoVo.getKeywordInfo();
		if (keywordInfo != null && !"".equals(keywordInfo)) {
			String keywordType = productInfoVo.getKeywordType();
			if ("USER_NAME".equals(keywordType)) {
				condition.put("USER_NAME", keywordInfo);
			} else if ("TELEPHONE".equals(keywordType)) {
				condition.put("TELEPHONE", keywordInfo);
			} else if ("WECHAT_NAME".equals(keywordType)) {
				condition.put("WECHAT_NAME", keywordInfo);
			} else if ("WECHAT_NUM".equals(keywordType)) {
				condition.put("WECHAT_NUM", keywordInfo);
			}
		}
		pageInfo.setCondition(condition);
		System.out.println("condition:" + JSON.toJSONString(condition));
		iOperatorServiceImpl.memberListDataGrid(pageInfo);
		return pageInfo;
	}

	@PostMapping("/memberManage/updateStatus")
	@ResponseBody
	public Object updateMemberStatus(String memberId, String status) {
		try {
			TMemberBasicinfo tMemberBasicinfo = new TMemberBasicinfo();
			tMemberBasicinfo.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			tMemberBasicinfo.setStatus(status);
			tMemberBasicinfo.setUserId(memberId);
			iOperatorServiceImpl.updateMemberStatus(tMemberBasicinfo);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("内部错误，修改失败！");
		}
		return renderSuccess("修改成功！");
	}

	@GetMapping("/customerManage")
	public String customerManage() {
		return "admin/personManage/customerManage";
	}

	@GetMapping("/customerManage/customerLook")
	public String getCustomerInfoById(Model model, String customerId) {
		TCustomerBasicinfo tCustomerBasicinfo = iOperatorServiceImpl.getCustomerIdInfoById(customerId);
		tCustomerBasicinfo.setBusinessInsStartTime(formateTime(tCustomerBasicinfo.getBusinessInsStartTime()));
		tCustomerBasicinfo.setTrafficInsStartTime(formateTime(tCustomerBasicinfo.getTrafficInsStartTime()));
		tCustomerBasicinfo.setExamineTime(formateTime(tCustomerBasicinfo.getExamineTime()));
		tCustomerBasicinfo.setRegisterTime(formateTime(tCustomerBasicinfo.getRegisterTime()));
		model.addAttribute("customerInfo", tCustomerBasicinfo);
		return "admin/personManage/customerManageLook";
	}

	private String formateTime(String yyyyMMDDhhmmss) {
		StringBuffer result = new StringBuffer("");
		String year = yyyyMMDDhhmmss.substring(0, 4);
		String month = yyyyMMDDhhmmss.substring(4, 6);
		String day = yyyyMMDDhhmmss.substring(6, 8);
		result.append(year).append("-").append(month).append("-").append(day);
		return result.toString();
	}

	@PostMapping("/customerManage/updateStatus")
	@ResponseBody
	public Object updateCustomerStatus(String customerId, String status) {
		try {
			TCustomerBasicinfo tCustomerBasicinfo = new TCustomerBasicinfo();
			tCustomerBasicinfo.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			tCustomerBasicinfo.setStatus(status);
			tCustomerBasicinfo.setUserId(customerId);
			iOperatorServiceImpl.updateCustomerStatus(tCustomerBasicinfo);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("修改失败！");
		}
		return renderSuccess("修改成功！");
	}

	@PostMapping("/customerDataGrid")
	@ResponseBody
	public PageInfo customerDataGrid(ProductInfoVo productInfoVo, Integer page, Integer rows, String sort, String order) {
		System.out.println("productInfoVo:" + JSON.toJSONString(productInfoVo));
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		String keywordInfo = productInfoVo.getKeywordInfo();
		if (keywordInfo != null && !"".equals(keywordInfo)) {
			String keywordType = productInfoVo.getKeywordType();
			if ("USER_NAME".equals(keywordType)) {
				condition.put("USER_NAME", keywordInfo);
			} else if ("TELEPHONE".equals(keywordType)) {
				condition.put("TELEPHONE", keywordInfo);
			}
		}
		pageInfo.setCondition(condition);
		System.out.println("condition:" + JSON.toJSONString(condition));
		iOperatorServiceImpl.customerDataGrid(pageInfo);
		return pageInfo;
	}
}
