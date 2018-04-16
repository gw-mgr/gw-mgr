package com.gewei.fuwushang.controller;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.fuwushang.service.IOrderCxService;
import com.gewei.fuwushang.service.IUserService;
import com.gewei.model.OrderCx;
import com.gewei.model.excel.OrderCxExcel;
import com.gewei.model.vo.OrderCxVo;
import com.gewei.model.vo.UserVo;

/**
 * <p>
 * 财产保险订单表 前端控制器
 * </p>
 *
 * @since 2018-03-14
 */
@Controller
@RequestMapping("/mgr/orderCx")
public class OrderCxController extends BaseController {

    @Autowired 
    private IOrderCxService orderCxService;
    @Autowired 
    private IUserService userService;
    @GetMapping("/manager")
    public String manager() {
        return "admin/orderCx/orderCxList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(OrderCx orderCx, Integer page, Integer rows, String sort,String order) {
    	orderCx.setOrderId("01");
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	Map<String, Object> condition = new HashMap<String, Object>();
    	condition.put("orderFlag", "03");
//		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(val)) {
//			if("userName".equals(name)){
//				condition.put("user_name", val);
//			}
//			if("mtelphone".equals(name)){
//				condition.put("mtelphone", val);
//			}
//			if("wechat".equals(name)){
//				condition.put("wechat", val);
//			}
		    pageInfo.setCondition(condition);
//		}
    	orderCxService.selectDataGrid(pageInfo);
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = pageInfo.getRows();
    	if(list.size()>0){
	    	for(Map<String, Object> map:list){
	    		map.put("createTime", DateUtil.strToShot((String)map.get("createTime")));
	    		map.put("updateTime", DateUtil.strToShot((String)map.get("updateTime")));
	    		map.put("zsyx", new BigDecimal((Long)map.get("zsyx")).multiply(new BigDecimal("0.01")));
	    		map.put("jsyx", new BigDecimal((Long)map.get("jsyx")).multiply(new BigDecimal("0.01")));
	    		map.put("syxs", new BigDecimal((Long)map.get("syxs")).multiply(new BigDecimal("0.01")));
	    		map.put("zjq", new BigDecimal((Long)map.get("zjq")).multiply(new BigDecimal("0.01")));
	    		map.put("jqxs", new BigDecimal((Long)map.get("jqxs")).multiply(new BigDecimal("0.01")));
	    		map.put("ccs", new BigDecimal((Long)map.get("ccs")).multiply(new BigDecimal("0.01")));
	    		map.put("zbf", new BigDecimal((Long)map.get("zbf")).multiply(new BigDecimal("0.01")));
	    		map.put("sjzfyj", new BigDecimal((Long)map.get("sjzfyj")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgssqyjbl", new BigDecimal((Long)map.get("bxgssqyjbl")).multiply(new BigDecimal("0.01")));
	    		map.put("bgsml", new BigDecimal((Long)map.get("bgsml")).multiply(new BigDecimal("0.01")));
	    		map.put("gsglfbl", new BigDecimal((Long)map.get("gsglfbl")).multiply(new BigDecimal("0.01")));
	    		map.put("gsglfje", new BigDecimal((Long)map.get("gsglfje")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgskpyjbl", new BigDecimal((Long)map.get("bxgskpyjbl")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgskpyj", new BigDecimal((Long)map.get("bxgskpyj")).multiply(new BigDecimal("0.01")));
	    	}
    	}
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
    	 Subject currentUser = SecurityUtils.getSubject();
         PrincipalCollection collection = currentUser.getPrincipals();
         if (null != collection) {
             String loginName = collection.getPrimaryPrincipal().toString();
             UserVo userVo = userService.selectUserVoByLoginName(loginName);
             model.addAttribute("recorder", userVo.getName());
         }
        
        return "admin/orderCx/orderCxAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid OrderCx orderCx) {
    	System.out.println(orderCx.toString());
    	orderCx.setOrderId(StringUtils.getUUId());
    	orderCx.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
    	orderCx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
    	orderCx.setRegisterTime(DateUtil.strToLong(orderCx.getRegisterTime()));
    	orderCx.setExamineTime(DateUtil.strToLong(orderCx.getExamineTime()));
    	orderCx.setBusinessInsStartTime(DateUtil.strToLong(orderCx.getBusinessInsStartTime()));
    	orderCx.setTrafficInsStartTime(DateUtil.strToLong(orderCx.getTrafficInsStartTime()));
    	changeMoney(orderCx, "100");
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            orderCx.setMerchantId(userVo.getOrganizationId().toString());
            orderCx.setRecorder(userVo.getName());
            orderCx.setRecorderid(userVo.getId().toString());
        }
        boolean b = orderCxService.insert(orderCx);
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
    	EntityWrapper<OrderCx> orderCxWrapper = new EntityWrapper<OrderCx>();
    	orderCxWrapper.eq("ORDER_ID", id);
    	OrderCx orderCx = orderCxService.selectOne(orderCxWrapper);
    	orderCx.setOrderFlag("03");
        orderCx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
        boolean b = orderCxService.update(orderCx, orderCxWrapper);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 修改状态
     * @param id
     * @return
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public Object changeStatus(String id, String cloumn) {
    	EntityWrapper<OrderCx> orderCxWrapper;
    	boolean b = false;
    	if(id.indexOf(",")>0){
    		for(String str : id.split(",")){
    			orderCxWrapper = new EntityWrapper<OrderCx>();
    			orderCxWrapper.eq("ORDER_ID", str);
    	    	OrderCx orderCx = orderCxService.selectOne(orderCxWrapper);
    	    	if("orderFlag".equals(cloumn)){
    	    		orderCx.setOrderFlag(orderCx.getOrderFlag().equals("01")?"02":"01");
    	    	}
    	    	if("printFlag".equals(cloumn)){
    	    		orderCx.setPrintFlag(orderCx.getPrintFlag().equals("01")?"02":"01");
    	    	}
    	    	if("giftFlag".equals(cloumn)){
    	    		orderCx.setGiftFlag(orderCx.getGiftFlag().equals("01")?"02":"01");
    	    	}
    	    	if("checkoutFlag".equals(cloumn)){
    	    		orderCx.setCheckoutFlag(orderCx.getCheckoutFlag().equals("01")?"02":"01");
    	    	}
    	        orderCx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
    	        b = orderCxService.update(orderCx, orderCxWrapper);
    		}
    	}else{
    		orderCxWrapper = new EntityWrapper<OrderCx>();
    		orderCxWrapper.eq("ORDER_ID", id);
        	OrderCx orderCx = orderCxService.selectOne(orderCxWrapper);
        	if("orderFlag".equals(cloumn)){
        		orderCx.setOrderFlag(orderCx.getOrderFlag().equals("01")?"02":"01");
        	}
        	if("printFlag".equals(cloumn)){
        		orderCx.setPrintFlag(orderCx.getPrintFlag().equals("01")?"02":"01");
        	}
        	if("giftFlag".equals(cloumn)){
        		orderCx.setGiftFlag(orderCx.getGiftFlag().equals("01")?"02":"01");
        	}
        	if("checkoutFlag".equals(cloumn)){
        		orderCx.setCheckoutFlag(orderCx.getCheckoutFlag().equals("01")?"02":"01");
        	}
            orderCx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
            b = orderCxService.update(orderCx, orderCxWrapper);
    	}
        if (b) {
            return renderSuccess("修改成功！");
        } else {
            return renderError("修改失败！");
        }
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, String id) {
        OrderCxVo orderCx = orderCxService.selectOrderByOrderId(id);
        orderCx.setCreateTime(DateUtil.strToShot(orderCx.getCreateTime()));
        orderCx.setUpdateTime(DateUtil.strToShot(orderCx.getUpdateTime()));
        orderCx.setRegisterTime(DateUtil.strToShot(orderCx.getRegisterTime()));
        orderCx.setExamineTime(DateUtil.strToShot(orderCx.getExamineTime()));
        orderCx.setBusinessInsStartTime(DateUtil.strToShot(orderCx.getBusinessInsStartTime()));
        orderCx.setTrafficInsStartTime(DateUtil.strToShot(orderCx.getTrafficInsStartTime()));
        changeMoney(orderCx, "0.01");
        model.addAttribute("orderCx", orderCx);
        return "admin/orderCx/orderCxEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid OrderCx orderCx) {
    	OrderCxVo order = orderCxService.selectOrderByOrderId(orderCx.getOrderId());
    	changeMoney(orderCx, "100");
    	BeanUtils.copyNotNullProperties(orderCx, order);
    	order.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
    	order.setRegisterTime(DateUtil.strToLong(order.getRegisterTime()));
    	order.setExamineTime(DateUtil.strToLong(order.getExamineTime()));
    	order.setBusinessInsStartTime(DateUtil.strToLong(order.getBusinessInsStartTime()));
    	order.setTrafficInsStartTime(DateUtil.strToLong(order.getTrafficInsStartTime()));
    	
    	Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            order.setMerchantId(userVo.getOrganizationId().toString());
            order.setRecorder(userVo.getName());
            order.setRecorderid(userVo.getId().toString());
            order.setUpdateUser(userVo.getName());
        }
        OrderCx orderCx1 = new OrderCx();
        BeanUtils.copyNotNullProperties(order, orderCx1);
        boolean b = orderCxService.updateById(orderCx1);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    /**
     * 查看
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/show")
    public String showPage(Model model, String id) {
        OrderCxVo orderCx = orderCxService.selectOrderByOrderId(id);
        orderCx.setCreateTime(DateUtil.strToShot(orderCx.getCreateTime()));
        orderCx.setUpdateTime(DateUtil.strToShot(orderCx.getUpdateTime()));
        orderCx.setRegisterTime(DateUtil.strToShot(orderCx.getRegisterTime()));
        orderCx.setExamineTime(DateUtil.strToShot(orderCx.getExamineTime()));
        orderCx.setBusinessInsStartTime(DateUtil.strToShot(orderCx.getBusinessInsStartTime()));
        orderCx.setTrafficInsStartTime(DateUtil.strToShot(orderCx.getTrafficInsStartTime()));
        changeMoney(orderCx, "0.01");
        model.addAttribute("orderCx", orderCx);
        return "admin/orderCx/orderCxShow";
    }
    /**
     * 变更
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/changePage")
    public String changePage(Model model, String id) {
        OrderCxVo orderCx = orderCxService.selectOrderByOrderId(id);
        orderCx.setCreateTime(DateUtil.strToShot(orderCx.getCreateTime()));
        orderCx.setUpdateTime(DateUtil.strToShot(orderCx.getUpdateTime()));
        orderCx.setRegisterTime(DateUtil.strToShot(orderCx.getRegisterTime()));
        orderCx.setExamineTime(DateUtil.strToShot(orderCx.getExamineTime()));
        orderCx.setBusinessInsStartTime(DateUtil.strToShot(orderCx.getBusinessInsStartTime()));
        orderCx.setTrafficInsStartTime(DateUtil.strToShot(orderCx.getTrafficInsStartTime()));
        changeMoney(orderCx, "0.01");
        model.addAttribute("orderCx", orderCx);
        return "admin/orderCx/orderCxChange";
    }
    /**
     * 变更
     * @param 
     * @return
     */
    @PostMapping("/change")
    @ResponseBody
    public Object change(@Valid OrderCx orderCx) {
    	OrderCxVo order = orderCxService.selectOrderByOrderId(orderCx.getOrderId());
    	System.out.println("order-->" + order.toString());
    	
    	changeMoney(orderCx, "100");
    	BeanUtils.copyNotNullProperties(orderCx, order);
    	order.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
    	System.out.println("order-->" + order.toString());
    	Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            order.setUpdateUser(userVo.getName());
        }
        OrderCx orderCx1 = new OrderCx();
        BeanUtils.copyNotNullProperties(order, orderCx1);
        System.out.println("orderCx1-->" + orderCx1.toString());
        boolean b = orderCxService.updateById(orderCx1);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
     * 支付设置
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/payPage")
    public String payPage(Model model, String id) {
        OrderCxVo orderCx = orderCxService.selectOrderByOrderId(id);
        orderCx.setCreateTime(DateUtil.strToShot(orderCx.getCreateTime()));
        orderCx.setUpdateTime(DateUtil.strToShot(orderCx.getUpdateTime()));
        orderCx.setRegisterTime(DateUtil.strToShot(orderCx.getRegisterTime()));
        orderCx.setExamineTime(DateUtil.strToShot(orderCx.getExamineTime()));
        orderCx.setBusinessInsStartTime(DateUtil.strToShot(orderCx.getBusinessInsStartTime()));
        orderCx.setTrafficInsStartTime(DateUtil.strToShot(orderCx.getTrafficInsStartTime()));
        changeMoney(orderCx, "0.01");
        model.addAttribute("orderCx", orderCx);
        if("01".equals(orderCx.getYjzfdx()))
        	return "admin/orderCx/orderCxPaySales";
        else
        	return "admin/orderCx/orderCxPayOper";
    }
    /**
     * 支付更改
     * @param 
     * @return
     */
    @PostMapping("/payEdit")
    @ResponseBody
    public Object payEdit(@Valid OrderCx orderCx) {
    	OrderCxVo order = orderCxService.selectOrderByOrderId(orderCx.getOrderId());
    	System.out.println("order-->" + order.toString());
    	
    	changeMoney(orderCx, "100");
    	BeanUtils.copyNotNullProperties(orderCx, order);
    	order.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
    	System.out.println("order-->" + order.toString());
    	Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            order.setUpdateUser(userVo.getName());
        }
        OrderCx orderCx1 = new OrderCx();
        BeanUtils.copyNotNullProperties(order, orderCx1);
        System.out.println("orderCx1-->" + orderCx1.toString());
        boolean b = orderCxService.updateById(orderCx1);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    /**
     * 结账设置
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/accPage")
    public String accPage(Model model, String id) {
    	if(id.indexOf(",") > 0){
    		OrderCxVo orderCx = new OrderCxVo();
    		orderCx.setOrderId(id);
    		 model.addAttribute("orderCx", orderCx);
 	    	return "admin/orderCx/orderCxAcc";
    	}else{
    		 OrderCxVo orderCx = orderCxService.selectOrderByOrderId(id);
	        orderCx.setCreateTime(DateUtil.strToShot(orderCx.getCreateTime()));
	        orderCx.setUpdateTime(DateUtil.strToShot(orderCx.getUpdateTime()));
	        orderCx.setRegisterTime(DateUtil.strToShot(orderCx.getRegisterTime()));
	        orderCx.setExamineTime(DateUtil.strToShot(orderCx.getExamineTime()));
	        orderCx.setBusinessInsStartTime(DateUtil.strToShot(orderCx.getBusinessInsStartTime()));
	        orderCx.setTrafficInsStartTime(DateUtil.strToShot(orderCx.getTrafficInsStartTime()));
	        changeMoney(orderCx, "0.01");
	        model.addAttribute("orderCx", orderCx);
	    	return "admin/orderCx/orderCxAcc";
    	}
    }
    /**
     * 支付更改
     * @param 
     * @return
     */
    @PostMapping("/accEdit")
    @ResponseBody
    public Object accEdit(@Valid OrderCx orderCx) {
    	System.out.println("order-->" + orderCx.toString());
    	boolean b = false;
    	if(orderCx.getOrderId().indexOf(",") > 0){
    		for(String s : orderCx.getOrderId().split(",")){
    			OrderCxVo order = orderCxService.selectOrderByOrderId(s);
            	changeMoney(orderCx, "100");
            	BeanUtils.copyNotNullProperties(orderCx, order);
            	order.setOrderId(s);
            	order.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
            	System.out.println("order-->" + order.toString());
            	Subject currentUser = SecurityUtils.getSubject();
                PrincipalCollection collection = currentUser.getPrincipals();
                if (null != collection) {
                    String loginName = collection.getPrimaryPrincipal().toString();
                    UserVo userVo = userService.selectUserVoByLoginName(loginName);
                    order.setUpdateUser(userVo.getName());
                }
                OrderCx orderCx1 = new OrderCx();
                BeanUtils.copyNotNullProperties(order, orderCx1);
                System.out.println("orderCx1-->" + orderCx1.toString());
                b = orderCxService.updateById(orderCx1);
    		}
    	}else{
    		OrderCxVo order = orderCxService.selectOrderByOrderId(orderCx.getOrderId());
        	System.out.println("order-->" + order.toString());
        	
        	changeMoney(orderCx, "100");
        	BeanUtils.copyNotNullProperties(orderCx, order);
        	order.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
        	System.out.println("order-->" + order.toString());
        	Subject currentUser = SecurityUtils.getSubject();
            PrincipalCollection collection = currentUser.getPrincipals();
            if (null != collection) {
                String loginName = collection.getPrimaryPrincipal().toString();
                UserVo userVo = userService.selectUserVoByLoginName(loginName);
                order.setUpdateUser(userVo.getName());
            }
            OrderCx orderCx1 = new OrderCx();
            BeanUtils.copyNotNullProperties(order, orderCx1);
            System.out.println("orderCx1-->" + orderCx1.toString());
            b = orderCxService.updateById(orderCx1);
    	}
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
	 * 批量导出
	 * @param response
	 * @param id
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletResponse response, String id) throws IOException, InvalidFormatException {
		EntityWrapper<OrderCx> entityWrapper = new EntityWrapper<OrderCx>();
		
		if(!"all".equals(id)){
			entityWrapper.in("ORDER_ID", id.split(","));
		}
		List<OrderCx> list  = orderCxService.selectList(entityWrapper);
		
		List<OrderCxExcel> listEx = new ArrayList<OrderCxExcel>();
		
    	if(list.size()>0){
	    	for(OrderCx order:list){
	    		OrderCxExcel orderCxExcel = new OrderCxExcel();
	    		BeanUtils.copyNotNullProperties(order, orderCxExcel);
	    		listEx.add(orderCxExcel);
	    	}
    	}
		Workbook wb = new XSSFWorkbook();
		EasyExcel fastExcel = new EasyExcel(wb);
		fastExcel.createExcel(response, listEx);
		fastExcel.close();
	}
    
    public static OrderCxVo changeMoney(OrderCxVo orderCx, String charge){
    	if(orderCx.getBxgssqjqyjbl() != null)
    	orderCx.setBxgssqjqyjbl(orderCx.getBxgssqjqyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgssqyjbl() != null)
    	orderCx.setBxgssqyjbl(orderCx.getBxgssqyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgssqjqyj() != null)
    	orderCx.setBxgssqjqyj(orderCx.getBxgssqjqyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgssqyj() != null)
    	orderCx.setBxgssqyj(orderCx.getBxgssqyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgskpyjbl() != null)
    	orderCx.setBxgskpyjbl(orderCx.getBxgskpyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgskpyj() != null)
    	orderCx.setBxgskpyj(orderCx.getBxgskpyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getGsglfbl() != null)
    	orderCx.setGsglfbl(orderCx.getGsglfbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getGsglfje() != null)
    	orderCx.setGsglfje(orderCx.getGsglfje().multiply(new BigDecimal(charge)));
    	if(orderCx.getGsjsbl() != null)
    	orderCx.setGsjsbl(orderCx.getGsjsbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getGsjsje() != null)
    	orderCx.setGsjsje(orderCx.getGsjsje().multiply(new BigDecimal(charge)));
    	if(orderCx.getGstcbl() != null)
    	orderCx.setGstcbl(orderCx.getGstcbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getGstcje() != null)
    	orderCx.setGstcje(orderCx.getGstcje().multiply(new BigDecimal(charge)));
    	if(orderCx.getBgsml() != null)
    	orderCx.setBgsml(orderCx.getBgsml().multiply(new BigDecimal(charge)));
    	if(orderCx.getJsyx() != null)
    	orderCx.setJsyx(orderCx.getJsyx().multiply(new BigDecimal(charge)));
    	if(orderCx.getSyxs() != null)
    	orderCx.setSyxs(orderCx.getSyxs().multiply(new BigDecimal(charge)));
    	if(orderCx.getZsyx() != null)
    	orderCx.setZsyx(orderCx.getZsyx().multiply(new BigDecimal(charge)));
    	if(orderCx.getJjqx() != null)
    	orderCx.setJjqx(orderCx.getJjqx().multiply(new BigDecimal(charge)));
    	if(orderCx.getJqxs() != null)
    	orderCx.setJqxs(orderCx.getJqxs().multiply(new BigDecimal(charge)));
    	if(orderCx.getZjq() != null)
    	orderCx.setZjq(orderCx.getZjq().multiply(new BigDecimal(charge)));
    	if(orderCx.getCcs() != null)
    	orderCx.setCcs(orderCx.getCcs().multiply(new BigDecimal(charge)));
    	if(orderCx.getZbf() != null)
    	orderCx.setZbf(orderCx.getZbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getSyxyjbl() != null)
    	orderCx.setSyxyjbl(orderCx.getSyxyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getSyxyj() != null)
    	orderCx.setSyxyj(orderCx.getSyxyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getZhjrjbl() != null)
    	orderCx.setZhjrjbl(orderCx.getZhjrjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getZhjrjje() != null)
    	orderCx.setZhjrjje(orderCx.getZhjrjje().multiply(new BigDecimal(charge)));
    	if(orderCx.getJjxyjbl() != null)
    	orderCx.setJjxyjbl(orderCx.getJjxyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getJjxyj() != null)
    	orderCx.setJjxyj(orderCx.getJjxyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getCcsyjbl() != null)
    	orderCx.setCcsyjbl(orderCx.getCcsyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getCcsyj() != null)
    	orderCx.setCcsyj(orderCx.getCcsyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgsdqjl() != null)
    	orderCx.setBxgsdqjl(orderCx.getBxgsdqjl().multiply(new BigDecimal(charge)));
    	if(orderCx.getSjzfyj() != null)
    	orderCx.setSjzfyj(orderCx.getSjzfyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getRecorderyj() != null)
    	orderCx.setRecorderyj(orderCx.getRecorderyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getJdcssxbe() != null)
    	orderCx.setJdcssxbe(orderCx.getJdcssxbe().multiply(new BigDecimal(charge)));
    	if(orderCx.getJdcssxbf() != null)
    	orderCx.setJdcssxbf(orderCx.getJdcssxbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getDszrxbe() != null)
    	orderCx.setDszrxbe(orderCx.getDszrxbe().multiply(new BigDecimal(charge)));
    	if(orderCx.getDszrxbf() != null)
    	orderCx.setDszrxbf(orderCx.getDszrxbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getQcdqx() != null)
    	orderCx.setQcdqx(orderCx.getQcdqx().multiply(new BigDecimal(charge)));
    	if(orderCx.getSjzrxbe() != null)
    	orderCx.setSjzrxbe(orderCx.getSjzrxbe().multiply(new BigDecimal(charge)));
    	if(orderCx.getSjzrxbf() != null)
    	orderCx.setSjzrxbf(orderCx.getSjzrxbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getCkzrxbe() != null)
    	orderCx.setCkzrxbe(orderCx.getCkzrxbe().multiply(new BigDecimal(charge)));
    	if(orderCx.getCkzrxbf() != null)
    	orderCx.setCkzrxbf(orderCx.getCkzrxbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getBlddpsx() != null)
    	orderCx.setBlddpsx(orderCx.getBlddpsx().multiply(new BigDecimal(charge)));
    	if(orderCx.getZrssx() != null)
    	orderCx.setZrssx(orderCx.getZrssx().multiply(new BigDecimal(charge)));
    	if(orderCx.getCshhx() != null)
    	orderCx.setCshhx(orderCx.getCshhx().multiply(new BigDecimal(charge)));
    	if(orderCx.getFdcssssx() != null)
    	orderCx.setFdcssssx(orderCx.getFdcssssx().multiply(new BigDecimal(charge)));
    	if(orderCx.getJdcsstyx() != null)
    	orderCx.setJdcsstyx(orderCx.getJdcsstyx().multiply(new BigDecimal(charge)));
    	if(orderCx.getBjmp() != null)
    	orderCx.setBjmp(orderCx.getBjmp().multiply(new BigDecimal(charge)));
    	return orderCx;
    }
    public static OrderCx changeMoney(OrderCx orderCx, String charge){
    	if(orderCx.getBxgssqjqyjbl() != null)
    	orderCx.setBxgssqjqyjbl(orderCx.getBxgssqjqyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgssqyjbl() != null)
    	orderCx.setBxgssqyjbl(orderCx.getBxgssqyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgssqjqyj() != null)
    	orderCx.setBxgssqjqyj(orderCx.getBxgssqjqyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgssqyj() != null)
    	orderCx.setBxgssqyj(orderCx.getBxgssqyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgskpyjbl() != null)
    	orderCx.setBxgskpyjbl(orderCx.getBxgskpyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgskpyj() != null)
    	orderCx.setBxgskpyj(orderCx.getBxgskpyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getGsglfbl() != null)
    	orderCx.setGsglfbl(orderCx.getGsglfbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getGsglfje() != null)
    	orderCx.setGsglfje(orderCx.getGsglfje().multiply(new BigDecimal(charge)));
    	if(orderCx.getGsjsbl() != null)
    	orderCx.setGsjsbl(orderCx.getGsjsbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getGsjsje() != null)
    	orderCx.setGsjsje(orderCx.getGsjsje().multiply(new BigDecimal(charge)));
    	if(orderCx.getGstcbl() != null)
    	orderCx.setGstcbl(orderCx.getGstcbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getGstcje() != null)
    	orderCx.setGstcje(orderCx.getGstcje().multiply(new BigDecimal(charge)));
    	if(orderCx.getBgsml() != null)
    	orderCx.setBgsml(orderCx.getBgsml().multiply(new BigDecimal(charge)));
    	if(orderCx.getJsyx() != null)
    	orderCx.setJsyx(orderCx.getJsyx().multiply(new BigDecimal(charge)));
    	if(orderCx.getSyxs() != null)
    	orderCx.setSyxs(orderCx.getSyxs().multiply(new BigDecimal(charge)));
    	if(orderCx.getZsyx() != null)
    	orderCx.setZsyx(orderCx.getZsyx().multiply(new BigDecimal(charge)));
    	if(orderCx.getJjqx() != null)
    	orderCx.setJjqx(orderCx.getJjqx().multiply(new BigDecimal(charge)));
    	if(orderCx.getJqxs() != null)
    	orderCx.setJqxs(orderCx.getJqxs().multiply(new BigDecimal(charge)));
    	if(orderCx.getZjq() != null)
    	orderCx.setZjq(orderCx.getZjq().multiply(new BigDecimal(charge)));
    	if(orderCx.getCcs() != null)
    	orderCx.setCcs(orderCx.getCcs().multiply(new BigDecimal(charge)));
    	if(orderCx.getZbf() != null)
    	orderCx.setZbf(orderCx.getZbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getSyxyjbl() != null)
    	orderCx.setSyxyjbl(orderCx.getSyxyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getSyxyj() != null)
    	orderCx.setSyxyj(orderCx.getSyxyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getZhjrjbl() != null)
    	orderCx.setZhjrjbl(orderCx.getZhjrjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getZhjrjje() != null)
    	orderCx.setZhjrjje(orderCx.getZhjrjje().multiply(new BigDecimal(charge)));
    	if(orderCx.getJjxyjbl() != null)
    	orderCx.setJjxyjbl(orderCx.getJjxyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getJjxyj() != null)
    	orderCx.setJjxyj(orderCx.getJjxyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getCcsyjbl() != null)
    	orderCx.setCcsyjbl(orderCx.getCcsyjbl().multiply(new BigDecimal(charge)));
    	if(orderCx.getCcsyj() != null)
    	orderCx.setCcsyj(orderCx.getCcsyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getBxgsdqjl() != null)
    	orderCx.setBxgsdqjl(orderCx.getBxgsdqjl().multiply(new BigDecimal(charge)));
    	if(orderCx.getSjzfyj() != null)
    	orderCx.setSjzfyj(orderCx.getSjzfyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getRecorderyj() != null)
    	orderCx.setRecorderyj(orderCx.getRecorderyj().multiply(new BigDecimal(charge)));
    	if(orderCx.getJdcssxbe() != null)
    	orderCx.setJdcssxbe(orderCx.getJdcssxbe().multiply(new BigDecimal(charge)));
    	if(orderCx.getJdcssxbf() != null)
    	orderCx.setJdcssxbf(orderCx.getJdcssxbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getDszrxbe() != null)
    	orderCx.setDszrxbe(orderCx.getDszrxbe().multiply(new BigDecimal(charge)));
    	if(orderCx.getDszrxbf() != null)
    	orderCx.setDszrxbf(orderCx.getDszrxbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getQcdqx() != null)
    	orderCx.setQcdqx(orderCx.getQcdqx().multiply(new BigDecimal(charge)));
    	if(orderCx.getSjzrxbe() != null)
    	orderCx.setSjzrxbe(orderCx.getSjzrxbe().multiply(new BigDecimal(charge)));
    	if(orderCx.getSjzrxbf() != null)
    	orderCx.setSjzrxbf(orderCx.getSjzrxbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getCkzrxbe() != null)
    	orderCx.setCkzrxbe(orderCx.getCkzrxbe().multiply(new BigDecimal(charge)));
    	if(orderCx.getCkzrxbf() != null)
    	orderCx.setCkzrxbf(orderCx.getCkzrxbf().multiply(new BigDecimal(charge)));
    	if(orderCx.getBlddpsx() != null)
    	orderCx.setBlddpsx(orderCx.getBlddpsx().multiply(new BigDecimal(charge)));
    	if(orderCx.getZrssx() != null)
    	orderCx.setZrssx(orderCx.getZrssx().multiply(new BigDecimal(charge)));
    	if(orderCx.getCshhx() != null)
    	orderCx.setCshhx(orderCx.getCshhx().multiply(new BigDecimal(charge)));
    	if(orderCx.getFdcssssx() != null)
    	orderCx.setFdcssssx(orderCx.getFdcssssx().multiply(new BigDecimal(charge)));
    	if(orderCx.getJdcsstyx() != null)
    	orderCx.setJdcsstyx(orderCx.getJdcsstyx().multiply(new BigDecimal(charge)));
    	if(orderCx.getBjmp() != null)
    	orderCx.setBjmp(orderCx.getBjmp().multiply(new BigDecimal(charge)));
    	return orderCx;
    }
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;  
  
        Object obj = beanClass.newInstance();  
  
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
  
        return obj;  
    } 
}
