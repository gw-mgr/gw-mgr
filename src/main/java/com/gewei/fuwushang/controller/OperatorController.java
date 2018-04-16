package com.gewei.fuwushang.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.report.excel.EasyExcel;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.shiro.PasswordHash;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.fuwushang.service.IOperatorService;
import com.gewei.fuwushang.service.IRoleService;
import com.gewei.fuwushang.service.IUserRoleService;
import com.gewei.fuwushang.service.IUserService;
import com.gewei.mapper.UserRoleMapper;
import com.gewei.model.Operator;
import com.gewei.model.Role;
import com.gewei.model.User;
import com.gewei.model.UserRole;
import com.gewei.model.excel.OperatorExcel;
import com.gewei.model.vo.FOperatorVo;
import com.gewei.model.vo.UserVo;

/**
 * <p>
 * 操作员表 前端控制器
 * </p>
 * @since 2018-01-25
 */
@Controller
@RequestMapping("/mgr/operator")
public class OperatorController extends BaseController {
	@Autowired
	private IOperatorService operatorService;
	@Autowired
	private PasswordHash passwordHash;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@GetMapping("/manager")
	public String manager() {
		return "admin/operator/operatorList";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(Operator operator, Integer page, Integer rows, String sort, String order, String name, String val) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(val)) {
			if ("userName".equals(name)) {
				condition.put("userName", val);
			}
			if ("telphone".equals(name)) {
				condition.put("telphone", val);
			}
			pageInfo.setCondition(condition);
		}
		operatorService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加页面
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/operator/operatorAdd";
	}

	/**
	 * 添加
	 * @param 
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid FOperatorVo operator) {
		EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
		entityWrapper.eq("NAME", operator.getUserName());
		User u = userService.selectOne(entityWrapper);
		if (u != null) {
			return renderError("该用户名已存在!");
		}
		String id = StringUtils.getUUId();
		User user = new User();
		Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            user.setOrganizationId(userVo.getOrganizationId());
        }
		user.setId(id);
		user.setLoginName(operator.getUserName());
		user.setName(operator.getUserName());
		String salt = StringUtils.getUUId();
		String pwd = passwordHash.toHex("123456", salt);
		user.setSalt(salt);
		user.setPassword(pwd);
		user.setUserType("02");
		user.setPhone(operator.getTelephone());
		user.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
		boolean b = userService.insert(user);
		UserRole userRole = new UserRole();
		userRole.setUserId(id);
		userRole.setRoleId(Long.valueOf(operator.getRoleId()));
		b = userRoleService.insert(userRole);
		Operator op = new Operator();
		BeanUtils.copyNotNullProperties(operator, op);
		op.setOperId(id);
		op.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
		b = operatorService.insert(op);
		if (b) {
			return renderSuccess("添加成功！");
		} else {
			return renderError("添加失败！");
		}
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	public Object delete(String id) {
		if (id.indexOf(",") > 0) {
			String arr[] = id.split(",");
			for (String str : arr) {
				userService.deleteById(str);
				operatorService.deleteById(str);
				List<UserRole> userRoles = userRoleMapper.selectByUserId(str);
				if (userRoles != null && !userRoles.isEmpty()) {
					for (UserRole userRole : userRoles) {
						userRoleMapper.deleteById(userRole.getId());
					}
				}
			}
		} else {
			userService.deleteById(id);
			operatorService.deleteById(id);
			List<UserRole> userRoles = userRoleMapper.selectByUserId(id);
			if (userRoles != null && !userRoles.isEmpty()) {
				for (UserRole userRole : userRoles) {
					userRoleMapper.deleteById(userRole.getId());
				}
			}
		}
		return renderSuccess("删除成功！");
	}

	/**
	 * 编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/showPage")
	public String showPage(Model model, String id) {
		System.out.println("ID---" + id);
		FOperatorVo operator = operatorService.selectByOpId(id);
		model.addAttribute("operator", operator);
		return "admin/operator/operatorShow";
	}
	
	/**
	 * 编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, String id) {
		System.out.println("ID---" + id);
		FOperatorVo operator = operatorService.selectByOpId(id);
		model.addAttribute("operator", operator);
		return "admin/operator/operatorEdit";
	}

	/**
	 * 编辑
	 * @param 
	 * @return
	 */
	@PostMapping("/edit")
	@ResponseBody
	public Object edit(@Valid FOperatorVo operator) {
		boolean b = false;
		EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
		entityWrapper.eq("NAME", operator.getUserName());
		User u = userService.selectOne(entityWrapper);
		if (u == null) {
			User user = new User();
			Subject currentUser = SecurityUtils.getSubject();
	        PrincipalCollection collection = currentUser.getPrincipals();
	        if (null != collection) {
	            String loginName = collection.getPrimaryPrincipal().toString();
	            UserVo userVo = userService.selectUserVoByLoginName(loginName);
	            user.setOrganizationId(userVo.getOrganizationId());
	        }
			user.setId(operator.getOperId());
			user.setLoginName(operator.getUserName());
			user.setName(operator.getUserName());
			String salt = StringUtils.getUUId();
			String pwd = passwordHash.toHex("123456", salt);
			user.setSalt(salt);
			user.setPassword(pwd);
			user.setUserType("02");
			user.setPhone(operator.getTelephone());
			user.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			b = userService.insert(user);
		}else{
			u.setLoginName(operator.getUserName());
			u.setName(operator.getUserName());
			u.setPhone(operator.getTelephone());
			b = userService.updateById(u);
		}
		List<UserRole> userRoles = userRoleMapper.selectByUserId(operator.getOperId());
		if (userRoles != null && !userRoles.isEmpty()) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}
		UserRole userRole = new UserRole();
		userRole.setUserId(operator.getOperId());
		userRole.setRoleId(Long.valueOf(operator.getRoleId()));
		b = userRoleService.insert(userRole);
		FOperatorVo op = operatorService.selectByOpId(operator.getOperId());
		BeanUtils.copyNotNullProperties(operator, op);
		Operator oper = new Operator();
		BeanUtils.copyNotNullProperties(op, oper);
		op.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
		b = operatorService.updateById(oper);
		
		if (b) {
			return renderSuccess("编辑成功！");
		} else {
			return renderError("编辑失败！");
		}
	}

	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletResponse response, String id) throws IOException, InvalidFormatException {
		EntityWrapper<Operator> entityWrapper = new EntityWrapper<Operator>();
		
		if(!"all".equals(id)){
			entityWrapper.in("OPER_ID", id.split(","));
		}
		List<Operator> list  = operatorService.selectList(entityWrapper);
		List<OperatorExcel> listEx = new ArrayList<OperatorExcel>();
		for(Operator operator : list){
			OperatorExcel operatorExcel = new OperatorExcel();
			BeanUtils.copyNotNullProperties(operator, operatorExcel);
			operatorExcel.setRootdkCommissionType(operatorExcel.getRootdkCommissionType().equals("01")?"提成":"提成比例");
			operatorExcel.setRootccCommissionType(operatorExcel.getRootccCommissionType().equals("01")?"提成":"提成比例");
			operatorExcel.setRootrsCommissionType(operatorExcel.getRootrsCommissionType().equals("01")?"提成":"提成比例");
			EntityWrapper<User> userEntityWrapper = new EntityWrapper<User>();
			userEntityWrapper.eq("ID", operator.getOperId());
			User u = userService.selectOne(userEntityWrapper);
			if (u != null) {
				operatorExcel.setUserName(u.getName());
				operatorExcel.setTelephone(u.getPhone());
			}
			List<UserRole> userRoles = userRoleMapper.selectByUserId(operator.getOperId());
			if (userRoles != null && !userRoles.isEmpty()) {
				Role role = roleService.selectById(userRoles.get(0).getRoleId());
				operatorExcel.setRoleName(role.getName());
			}
			listEx.add(operatorExcel);
		}
		Workbook wb = new XSSFWorkbook();
		EasyExcel fastExcel = new EasyExcel(wb);
		fastExcel.createExcel(response, listEx);
		fastExcel.close();
	}
	
	@PostMapping(value = "/list")
	@ResponseBody
	public Object getOperatorList() {
		EntityWrapper<Operator> wrapper = new EntityWrapper<Operator>();
		return operatorService.selectList(wrapper);
	}
}
