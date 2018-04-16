package com.gewei.zhongshu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.result.Result;
import com.gewei.commons.utils.DateUtil;
import com.gewei.model.Message;
import com.gewei.zhongshu.service.IZMessageService;
import com.gewei.zhongshu.service.IUserService;

/**
 * <p>
 * 消息表 前端控制器
 * </p>
 *
 * @author tiger
 * @since 2018-04-07
 */
@Controller
@RequestMapping("/mgr/zmessage")
public class ZMessageController extends BaseController {
	@Autowired
	private IZMessageService messageService;
	@Autowired
	private IUserService userServiceImpl;

	@GetMapping("/manager")
	public String manager() {
		return "admin/zsmessage/messageList";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(Message message, Integer page, Integer rows, String sort, String order) {
		try {
			PageInfo pageInfo = new PageInfo(page, rows, sort, order);
			EntityWrapper<Message> wrapper = new EntityWrapper<Message>();
			ArrayList<String> value = new ArrayList<String>();
			value.add("01");
			value.add("02");
			wrapper.in("TYPE", value);
			wrapper.eq("STATUS", "01");
			Page<Message> pages = getPage(pageInfo);
			pages = messageService.selectPage(pages, wrapper);
			pageInfo.setRows(pages.getRecords());
			pageInfo.setTotal(pages.getTotal());
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("服务器内部错误");
		}
	}

	@GetMapping("/getCount")
	@ResponseBody
	public Object getCount() {
		Result result = new Result();
		try {
			EntityWrapper<Message> wrapper = new EntityWrapper<Message>();
			ArrayList<String> value = new ArrayList<String>();
			value.add("01");
			value.add("02");
			wrapper.in("TYPE", value);
			wrapper.eq("STATUS", "01");
			long totalCount = messageService.selectCount(wrapper);
			result.setTotalCount(totalCount);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("服务器内部错误");
			result.setSuccess(false);
		}
		return result;
	}

	@PostMapping("/editStatus")
	@ResponseBody
	public Object editStatus(Integer id) {
		try {
			Message message = new Message();
			message.setId(id);
			message.setStatus("02");
			// 记录操作人
			Subject currentUser = SecurityUtils.getSubject();
			PrincipalCollection collection = currentUser.getPrincipals();
			if (null != collection) {
				String loginName = collection.getPrimaryPrincipal().toString();
				String operatorId = userServiceImpl.getUserIdByLoginName(loginName);
				message.setOperatorId(operatorId);
			}
			message.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
			boolean b = messageService.updateById(message);
			if (b) {
				return renderSuccess("编辑成功！");
			} else {
				return renderError("编辑失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("编辑失败！");
		}
	}

	@GetMapping("/editAllStatus")
	@ResponseBody
	@Transactional
	public Object editAllStatus() {
		try {
			// 查询所有未读消息
			EntityWrapper<Message> wrapper = new EntityWrapper<Message>();
			ArrayList<String> value = new ArrayList<String>();
			value.add("01");
			value.add("02");
			wrapper.in("TYPE", value);
			wrapper.eq("STATUS", "01");
			List<Message> list = messageService.selectList(wrapper);
			for (Message message : list) {
				message.setStatus("02");
				// 记录操作人
				Subject currentUser = SecurityUtils.getSubject();
				PrincipalCollection collection = currentUser.getPrincipals();
				if (null != collection) {
					String loginName = collection.getPrimaryPrincipal().toString();
					String operatorId = userServiceImpl.getUserIdByLoginName(loginName);
					message.setOperatorId(operatorId);
				}
				message.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
				boolean b = messageService.updateById(message);
				if (!b) {
					return renderError("修改失败！");
				}
			}
			return renderSuccess("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("修改失败！");
		}
	}
}
