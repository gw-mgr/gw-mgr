package com.gewei.zhongshu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.shiro.PasswordHash;
import com.gewei.commons.utils.StringUtils;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.model.Role;
import com.gewei.model.User;
import com.gewei.model.vo.UserVo;
import com.gewei.zhongshu.service.ITMerchantManageService;
import com.gewei.zhongshu.service.IUserService;

/**
 * @description：用户管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/mgr/user")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;
	@Autowired
	private PasswordHash passwordHash;
	@Autowired
	private ITMerchantManageService iTMerchantManageServiceImpl;

	/**
	 * 所属服务商列表
	 */
	@PostMapping(value = "/merchantList")
	@ResponseBody
	public Object tree() {
		return iTMerchantManageServiceImpl.selectTree();
	}

	/**
	 * 所属服务商列表
	 */
	@PostMapping(value = "/merchantList2")
	@ResponseBody
	public Object tree2() {
		return iTMerchantManageServiceImpl.selectTree2();
	}

	/**
	 * 用户管理页
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/user/user";
	}

	/**
	 * 用户管理列表
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userVo.getName())) {
			condition.put("name", userVo.getName());
		}
		if (userVo.getOrganizationId() != null && !userVo.getOrganizationId().equals("all")) {
			condition.put("organizationId", userVo.getOrganizationId());
		}
		if (userVo.getCreatedateStart() != null) {
			condition.put("startTime", userVo.getCreatedateStart());
		}
		if (userVo.getCreatedateEnd() != null) {
			condition.put("endTime", userVo.getCreatedateEnd());
		}
		pageInfo.setCondition(condition);
		userService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加用户页
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/user/userAdd";
	}

	/**
	 * 添加用户
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid UserVo userVo) {
		List<User> list = userService.selectByLoginName(userVo);
		if (list != null && !list.isEmpty()) {
			return renderError("登录名已存在!");
		}
		String salt = StringUtils.getUUId();
		String pwd = passwordHash.toHex(userVo.getPassword(), salt);
		userVo.setId(UUIDUtil.getUUID32());
		userVo.setSalt(salt);
		userVo.setPassword(pwd);
		userVo.setStatus("01");
		userService.insertByVo(userVo);
		return renderSuccess("添加成功");
	}

	/**
	 * 编辑用户页
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, String id) {
		UserVo userVo = userService.selectVoById(id);
		List<Role> rolesList = userVo.getRolesList();
		List<Long> ids = new ArrayList<Long>();
		for (Role role : rolesList) {
			ids.add(role.getId());
		}
		model.addAttribute("roleIds", ids);
		model.addAttribute("user", userVo);
		return "admin/user/userEdit";
	}

	/**
	 * 编辑用户
	 */
	@RequiresRoles("admin")
	@PostMapping("/edit")
	@ResponseBody
	public Object edit(@Valid UserVo userVo) {
		List<User> list = userService.selectByLoginName(userVo);
		if (list != null && !list.isEmpty()) {
			return renderError("登录名已存在!");
		}
		// 更新密码
		if (StringUtils.isNotBlank(userVo.getPassword())) {
			User user = userService.selectById(userVo.getId());
			String salt = user.getSalt();
			String pwd = passwordHash.toHex(userVo.getPassword(), salt);
			userVo.setPassword(pwd);
		}
		userService.updateByVo(userVo);
		return renderSuccess("修改成功！");
	}

	/**
	 * 修改密码页
	 */
	@GetMapping("/editPwdPage")
	public String editPwdPage() {
		return "admin/user/userEditPwd";
	}

	/**
	 * 修改密码
	 */
	@PostMapping("/editUserPwd")
	@ResponseBody
	public Object editUserPwd(String oldPwd, String pwd) {
		User user = userService.selectById(getUserId());
		String salt = user.getSalt();
		if (!user.getPassword().equals(passwordHash.toHex(oldPwd, salt))) {
			return renderError("老密码不正确!");
		}
		userService.updatePwdByUserId(getUserId(), passwordHash.toHex(pwd, salt));
		return renderSuccess("密码修改成功！");
	}

	/**
	 * 删除用户
	 */
	@RequiresRoles("admin")
	@PostMapping("/delete")
	@ResponseBody
	public Object delete(String id) {
		String currentUserId = getUserId();
		if (id.equals(currentUserId)) {
			return renderError("不可以删除自己！");
		}
		// 逻辑删除
		User user = new User();
		user.setId(id);
		user.setStatus("02");
		userService.updateStatus(user);
		return renderSuccess("删除成功！");
	}
}