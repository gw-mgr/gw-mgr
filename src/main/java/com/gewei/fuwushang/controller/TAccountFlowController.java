package com.gewei.fuwushang.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import com.baomidou.mybatisplus.plugins.Page;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.fuwushang.service.ITAccountFlowService;
import com.gewei.fuwushang.service.IUserService;
import com.gewei.model.TAccountFlow;
import com.gewei.model.vo.UserVo;

/**
 * <p>
 * 服务商、会员结算流水表 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-16
 */
@Controller
@RequestMapping("/mgr/tAccountFlow")
public class TAccountFlowController extends BaseController {

    @Autowired 
    private ITAccountFlowService tAccountFlowService;
    @Autowired 
    private IUserService userService;
    @GetMapping("/manager")
    public String manager() {
        return "admin/tAccountFlow/tAccountFlowList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(TAccountFlow tAccountFlow, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            condition.put("personId", userVo.getOrganizationId());
            pageInfo.setCondition(condition);
        }
        tAccountFlowService.selectDataGrid(pageInfo);
		return pageInfo;
    }
    
}
