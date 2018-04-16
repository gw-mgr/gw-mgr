package com.gewei.fuwushang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.model.OrderInfo;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.TProductManage;
import com.gewei.model.vo.FOrderInfoVo;
import com.gewei.model.vo.UserVo;
import com.gewei.fuwushang.service.IOrderInfoService;
import com.gewei.fuwushang.service.ITCustomerBasicinfoService;
import com.gewei.fuwushang.service.ITProductManageService;
import com.gewei.fuwushang.service.IUserService;

/**
 * <p>
 * 订单表，包括贷款，理财和车 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-06
 */
@Controller
@RequestMapping("/mgr/orderInfo")
public class OrderInfoController extends BaseController {

    @Autowired 
    private IOrderInfoService orderInfoService;
    @Autowired 
    private IUserService userService;
    @Autowired 
    private ITCustomerBasicinfoService customerService;
    @Autowired 
    private ITProductManageService tProductManageService;
    @Autowired 
    private ITCustomerBasicinfoService tCustomerBasicinfoService;
    @GetMapping("/manager")
    public String manager(String type) {
        return "admin/order"+type+"/orderInfoList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(FOrderInfoVo orderInfoVo, Integer page, Integer rows, String sort,String order,String type, String name, String val) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		
		condition.put("orderType", type);
		System.out.println(condition.toString());
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(val)) {
			if("productName".equals(name)){
				condition.put("productName", val);
			}
			if("productId".equals(name)){
				condition.put("productId", val);
			}
		}
		pageInfo.setCondition(condition);
		orderInfoService.selectDataGrid(pageInfo);
		System.out.println(pageInfo.toString());
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(String type) {
        return "admin/order"+type+"/orderInfoAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     * @throws ParseException 
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid FOrderInfoVo orderInfoVo) throws ParseException {
    	TCustomerBasicinfo customerBasicinfo = new TCustomerBasicinfo();
        customerBasicinfo.setCardId(orderInfoVo.getCardId());
        customerBasicinfo.setUserName(orderInfoVo.getUserName());
        customerBasicinfo.setTelephone(orderInfoVo.getTelephone());
        PageInfo pageInfo = new PageInfo(1, 20, "userId", "asc");
        EntityWrapper<TCustomerBasicinfo> ew = new EntityWrapper<TCustomerBasicinfo>(customerBasicinfo);
        Page<TCustomerBasicinfo> pages = getPage(pageInfo);
        pages = tCustomerBasicinfoService.selectPage(pages, ew);
        String userId = StringUtils.getUUId();
        boolean b = false;
        if(pages.getTotal()<=0){
       	 BeanUtils.copyNotNullProperties(orderInfoVo, customerBasicinfo);
            customerBasicinfo.setUserId(userId);
            customerBasicinfo.setSex("03");
            customerBasicinfo.setStatus("01");
            customerBasicinfo.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
            customerBasicinfo.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
            b = customerService.insert(customerBasicinfo);
       }else{
    	    userId = pages.getRecords().get(0).getUserId();
       }
    	
    	OrderInfo orderInfo = new OrderInfo();
    	BeanUtils.copyNotNullProperties(orderInfoVo, orderInfo);
    	orderInfo.setOrderId(StringUtils.getUUId());
    	orderInfo.setUserId(userId);
        orderInfo.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
        orderInfo.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
        TProductManage tProductManage = tProductManageService.selectProductByProductId(orderInfoVo.getProductId());
        if("ROOTLC".equals(orderInfoVo.getOrderType())){
        	orderInfo.setOrderFlag("05");
        	orderInfo.setTargetAnnualIncome(tProductManage.getlGoalIncome());
        	int days = tProductManage.getlInvestDays();
        	Calendar nowTime = Calendar.getInstance();
        	String startStr = orderInfoVo.getBeginDate();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	nowTime.setTime(sdf.parse(startStr));
			nowTime.add(Calendar.DATE, days);
			orderInfo.setEndDate(sdf.format(nowTime.getTime()));
        }else{
        	orderInfo.setOrderFlag("01");
        }
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            orderInfo.setSalesMan(userVo.getId().toString());
            orderInfo.setRecorder(userVo.getId().toString());
        }
         b = orderInfoService.insert(orderInfo);
        
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
    public Object delete(Long id) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
        orderInfo.setOrderFlag("");
        boolean b = orderInfoService.updateById(orderInfo);
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
    public String editPage(Model model, Long id,String type) {
        OrderInfo orderInfo = orderInfoService.selectById(id);
        model.addAttribute("orderInfo", orderInfo);
        return "admin/order"+type+"/orderInfoEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid OrderInfo orderInfo) {
        orderInfo.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
        boolean b = orderInfoService.updateById(orderInfo);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
