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

import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.model.TProductManage;
import com.gewei.model.vo.UserVo;
import com.gewei.fuwushang.service.ITProductManageService;
import com.gewei.fuwushang.service.IUserService;

/**
 * <p>
 * 产品基本信息表 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-05
 */
@Controller
@RequestMapping("/mgr/tProductManage")
public class TProductManageController extends BaseController {

    @Autowired 
    private ITProductManageService tProductManageService;
    
    @Autowired 
    private IUserService userService;
    
    @GetMapping("/manager")
    public String manager(String type) {
        return "admin/product"+type+"/tProductManageList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(TProductManage tProductManage, Integer page, Integer rows, String sort,String order,String type, String name, String val, String productName) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		
		condition.put("productType", type);
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(val)) {
			if("productName".equals(name)){
				condition.put("productName", val);
			}
			if("productId".equals(name)){
				condition.put("productId", val);
			}
		}
		if (StringUtils.isNotBlank(productName)) {
			condition.put("productName", productName);
		}
		pageInfo.setCondition(condition);
		tProductManageService.selectDataGrid(pageInfo);
		return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(String type) {
        return "admin/product"+type+"/tProductManageAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid TProductManage tProductManage) {
    	tProductManage.setProductId(StringUtils.getUUId());
        tProductManage.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()));
        tProductManage.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()));
        tProductManage.setlNumberOfPerson(0);
        tProductManage.setStatus("02");
        
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            tProductManage.setMerchantId(userVo.getOrganizationId().toString());
            tProductManage.setHandleId(userVo.getId().toString());
        }
        boolean b = tProductManageService.insert(tProductManage);
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
        TProductManage tProductManage = new TProductManage();
        tProductManage.setProductId(id);
        tProductManage.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()));
        tProductManage.setStatus("00");
        boolean b = tProductManageService.updateById(tProductManage);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, String id, String type) {
        TProductManage tProductManage = tProductManageService.selectProductByProductId(id);
        model.addAttribute("tProductManage", tProductManage);
        return "admin/product"+type+"/tProductManageEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid TProductManage tProductManage) {
    	TProductManage productManage = tProductManageService.selectProductByProductId(tProductManage.getProductId());
        BeanUtils.copyNotNullProperties(tProductManage, productManage);
        productManage.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()));
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            productManage.setMerchantId(userVo.getOrganizationId().toString());
            productManage.setHandleId(userVo.getId().toString());
        }
        boolean b = tProductManageService.updateByProductId(productManage);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
