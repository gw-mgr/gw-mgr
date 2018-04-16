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
import com.gewei.fuwushang.service.IBeneficialService;
import com.gewei.fuwushang.service.IOrderSxService;
import com.gewei.fuwushang.service.IPolicyholderService;
import com.gewei.fuwushang.service.IUserService;
import com.gewei.model.Beneficial;
import com.gewei.model.OrderSx;
import com.gewei.model.OrderSxBxsx;
import com.gewei.model.Policyholder;
import com.gewei.model.excel.OrderSxExcel;
import com.gewei.model.vo.OrderSxVo;
import com.gewei.model.vo.TzljForOrderSx;
import com.gewei.model.vo.UserVo;
import com.gewei.zhongshu.service.IOrderSxBxsxService;

/**
 * <p>
 * 人寿保险订单表 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-01
 */
@Controller
@RequestMapping("/mgr/orderSx")
public class OrderSxController extends BaseController {
	@Autowired
	private IOrderSxService orderSxService;
    @Autowired 
    private IUserService userService;
    @Autowired 
    private IPolicyholderService policyholderService;
    @Autowired 
    private IBeneficialService beneficialService;
	@Autowired
	private IOrderSxBxsxService iOrderSxBxsxServiceImpl;
	@GetMapping("/manager")
	public String manager() {
		return "admin/orderSx/orderSxList";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(OrderSx orderSx, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
    	condition.put("orderFlag", "03");
    	pageInfo.setCondition(condition);
		orderSxService.selectDataGrid(pageInfo);
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = pageInfo.getRows();
    	if(list.size()>0){
	    	for(Map<String, Object> map:list){
	    		map.put("createTime", DateUtil.strToShot((String)map.get("createTime")));
	    		map.put("updateTime", DateUtil.strToShot((String)map.get("updateTime")));
	    		map.put("bxgszxsqyjbl", new BigDecimal((Long)map.get("bxgszxsqyjbl")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgszxsqyj", new BigDecimal((Long)map.get("bxgszxsqyj")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgsfx1sqyjbl", new BigDecimal((Long)map.get("bxgsfx1sqyjbl")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgsfx1sqyj", new BigDecimal((Long)map.get("bxgsfx1sqyj")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgsfx2sqyjbl", new BigDecimal((Long)map.get("bxgsfx2sqyjbl")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgsfx2sqyj", new BigDecimal((Long)map.get("bxgsfx2sqyj")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgskpyjbl", new BigDecimal((Long)map.get("bxgskpyjbl")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgskpyj", new BigDecimal((Long)map.get("bxgskpyj")).multiply(new BigDecimal("0.01")));
	    		map.put("gsglfje", new BigDecimal((Long)map.get("gsglfje")).multiply(new BigDecimal("0.01")));
	    		map.put("gsglfbl", new BigDecimal((Long)map.get("gsglfbl")).multiply(new BigDecimal("0.01")));
	    		map.put("gstcje", new BigDecimal((Long)map.get("gstcje")).multiply(new BigDecimal("0.01")));
	    		map.put("gstcbl", new BigDecimal((Long)map.get("gstcbl")).multiply(new BigDecimal("0.01")));
	    		map.put("gsjsje", new BigDecimal((Long)map.get("gsjsje")).multiply(new BigDecimal("0.01")));
	    		map.put("gsjsbl", new BigDecimal((Long)map.get("gsjsbl")).multiply(new BigDecimal("0.01")));
	    		map.put("bfhj", new BigDecimal((Long)map.get("bfhj")).multiply(new BigDecimal("0.01")));
	    		map.put("tbrnsr", new BigDecimal((Long)map.get("tbrnsr")).multiply(new BigDecimal("0.01")));
	    		map.put("bbrnsr", new BigDecimal((Long)map.get("bbrnsr")).multiply(new BigDecimal("0.01")));
	    		map.put("jzx", new BigDecimal((Long)map.get("jzx")).multiply(new BigDecimal("0.01")));
	    		map.put("zxs", new BigDecimal((Long)map.get("zxs")).multiply(new BigDecimal("0.01")));
	    		map.put("jfx1", new BigDecimal((Long)map.get("jfx1")).multiply(new BigDecimal("0.01")));
	    		map.put("fxs1", new BigDecimal((Long)map.get("fxs1")).multiply(new BigDecimal("0.01")));
	    		map.put("jfx2", new BigDecimal((Long)map.get("jfx2")).multiply(new BigDecimal("0.01")));
	    		map.put("fxs2", new BigDecimal((Long)map.get("fxs2")).multiply(new BigDecimal("0.01")));
	    		map.put("salesManCommission", new BigDecimal((Long)map.get("salesManCommission")).multiply(new BigDecimal("0.01")));
	    		map.put("zxyjbl", new BigDecimal((Long)map.get("zxyjbl")).multiply(new BigDecimal("0.01")));
	    		map.put("zxyjje", new BigDecimal((Long)map.get("zxyjje")).multiply(new BigDecimal("0.01")));
	    		map.put("fxyjbl1", new BigDecimal((Long)map.get("fxyjbl1")).multiply(new BigDecimal("0.01")));
	    		map.put("fxyjje1", new BigDecimal((Long)map.get("fxyjje1")).multiply(new BigDecimal("0.01")));
	    		map.put("fxyjbl2", new BigDecimal((Long)map.get("fxyjbl2")).multiply(new BigDecimal("0.01")));
	    		map.put("fxyjje2", new BigDecimal((Long)map.get("fxyjje2")).multiply(new BigDecimal("0.01")));
	    		map.put("zhjrjbl", new BigDecimal((Long)map.get("zhjrjbl")).multiply(new BigDecimal("0.01")));
	    		map.put("zhjrjje", new BigDecimal((Long)map.get("zhjrjje")).multiply(new BigDecimal("0.01")));
	    		map.put("bxgsdqjl", new BigDecimal((Long)map.get("bxgsdqjl")).multiply(new BigDecimal("0.01")));
	    		map.put("recordertc", new BigDecimal((Long)map.get("recordertc")).multiply(new BigDecimal("0.01")));
	    		map.put("bgsml", new BigDecimal((Long)map.get("bgsml")).multiply(new BigDecimal("0.01")));
	    		map.put("sjzfyj", new BigDecimal((Long)map.get("sjzfyj")).multiply(new BigDecimal("0.01")));
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
            model.addAttribute("recorder", userVo.getId());
            model.addAttribute("recorderName", userVo.getName());
        }
		model.addAttribute("orderId", StringUtils.getUUId());
		return "admin/orderSx/orderSxAdd";
	}

	/**
	 * 添加
	 * @param 
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid OrderSxVo orderSxVo) {
		System.out.println(orderSxVo.toString());
		OrderSx orderSx = new OrderSx();
		List<OrderSxBxsx> tbsxBxsx = orderSxVo.getTbsx_bxsx();
		if (tbsxBxsx != null && tbsxBxsx.size() >= 1) {
			for (OrderSxBxsx orderSxBxsx : tbsxBxsx) {
				EntityWrapper<OrderSxBxsx> orderSxBxsxWrapper = new EntityWrapper<OrderSxBxsx>();
				orderSxBxsxWrapper.eq("ORDER_ID", orderSxVo.getOrderId());
				orderSxBxsxWrapper.eq("ID", orderSxBxsx.getId());
				OrderSxBxsx orderSxBxsx2 = iOrderSxBxsxServiceImpl.selectOne(orderSxBxsxWrapper);
				if (orderSxBxsx2 == null) {
					orderSxBxsx2 = new OrderSxBxsx();
					orderSxBxsx2.setOrderId(orderSxVo.getOrderId());
					BeanUtils.copyNotNullProperties(orderSxBxsx, orderSxBxsx2);
					iOrderSxBxsxServiceImpl.insert(orderSxBxsx2);
					continue;
				}
				BeanUtils.copyNotNullProperties(orderSxBxsx, orderSxBxsx2);
				iOrderSxBxsxServiceImpl.update(orderSxBxsx2, orderSxBxsxWrapper);
			}
		}
		List<TzljForOrderSx> tzlj = orderSxVo.getTzlj();
		//张三#$#0.03#$#0.03@张四#$#0.04#$#0.04@张五#$#0.05#$#0.05
		StringBuffer bxtx = new StringBuffer("");
		if (tzlj != null && tzlj.size() >= 1) {
			for(TzljForOrderSx tz : tzlj){
				bxtx.append(tz.getTzzhmc()).append("#$#").append(tz.getFpbl()).append("#$#").append(tz.getZjbxfpbl()).append("@");
			}
		}
		orderSx.setTzljbxtx(bxtx.length() == 0 ? bxtx.toString() : bxtx.substring(0, bxtx.length() - 1).toString());
		
		BeanUtils.copyNotNullProperties(orderSxVo, orderSx);
		orderSx.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		orderSx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		orderSx.setOrderType("ROOTRS");
		Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            orderSx.setMerchantId(userVo.getOrganizationId().toString());
            orderSx.setRecorder(userVo.getName());
            orderSx.setRecorderid(userVo.getId().toString());
            orderSx.setUpdateUser(userVo.getId().toString());
        }
        changeMoney(orderSx, "100");
		boolean b = false;
		
		Policyholder policyholder = new Policyholder();
		BeanUtils.copyNotNullProperties(orderSxVo, policyholder);
		Policyholder p = policyholderService.selectPolicyholder(policyholder);
		if(p == null){
			policyholder.setPid(StringUtils.getUUId());
			policyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
			policyholder.setStatus("0");
			System.out.println("policyholder--" + policyholder.toString());
			b = policyholderService.insert(policyholder);
			orderSx.setPolicyholderId(policyholder.getPid());
		}else{
			orderSx.setPolicyholderId(p.getPid());
		}
		Policyholder bpolicyholder = new Policyholder();
		bpolicyholder.setUserName(orderSxVo.getUserNameB());
		bpolicyholder.setSex(orderSxVo.getSexB());
		bpolicyholder.setMarryFlag(orderSxVo.getMarryFlagB());
		bpolicyholder.setBirthDate(orderSxVo.getBirthDateB());
		bpolicyholder.setCertType(orderSxVo.getCertTypeB());
		bpolicyholder.setCertNo(orderSxVo.getCertNoB());
		bpolicyholder.setNationality(orderSxVo.getNationalityB());
		bpolicyholder.setHousehold(orderSxVo.getHouseholdB());;
		bpolicyholder.setValidityDate(orderSxVo.getValidityDateB());
		bpolicyholder.setResidentType(orderSxVo.getResidentTypeB());
		bpolicyholder.setRprovince(orderSxVo.getProvinceB());
		bpolicyholder.setRcity(orderSxVo.getCityB());
		bpolicyholder.setRdistrict(orderSxVo.getDistrictB());
		bpolicyholder.setResidentialAddress(orderSxVo.getResidentialAddressB());
		bpolicyholder.setTelphone(orderSxVo.getTelphoneB());
		bpolicyholder.setMtelphone(orderSxVo.getMtelphoneB());
		bpolicyholder.setMail(orderSxVo.getMailB());
		bpolicyholder.setWorkUnit(orderSxVo.getWorkUnitB());
		bpolicyholder.setJobContent(orderSxVo.getJobContentB());
		bpolicyholder.setIndustry(orderSxVo.getIndustryB());
		bpolicyholder.setOccupation(orderSxVo.getOccupationB());
		Policyholder bp = policyholderService.selectPolicyholder(bpolicyholder);
		if(bp == null){
			bpolicyholder.setPid(StringUtils.getUUId());
			bpolicyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
			bpolicyholder.setStatus("0");
			System.out.println("bpolicyholder--" + bpolicyholder.toString());
			b = policyholderService.insert(bpolicyholder);
			orderSx.setInsuredId(bpolicyholder.getPid());
		}else{
			orderSx.setInsuredId(bp.getPid());
		}
		
		List<Beneficial> syr = orderSxVo.getSyr();
		if (syr != null && syr.size() >= 1) {
			
		}
		
		Beneficial beneficial = new Beneficial();
		BeanUtils.copyNotNullProperties(orderSxVo, beneficial);
		beneficial.setSex(orderSxVo.getSexS());
		beneficial.setBirthDate(orderSxVo.getBirthDateS());
		beneficial.setCertType(orderSxVo.getCertTypeS());
		beneficial.setCertNo(orderSxVo.getCertNoS());
		beneficial.setValidityDate(orderSxVo.getValidityDateS());
		beneficial.setResidentialAddress(orderSxVo.getResidentialAddressS());
		beneficial.setOrderId(orderSx.getOrderId());
		beneficial.setPersonId(StringUtils.getUUId());
		beneficial.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		b = beneficialService.insert(beneficial);
		orderSx.setBeneficiaryId(beneficial.getPersonId());
		b = orderSxService.insert(orderSx);
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
	public Object delete(String orderId) {
		OrderSx orderSx = new OrderSx();
		orderSx.setOrderId(orderId);
		orderSx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		orderSx.setOrderFlag("03");
		boolean b = orderSxService.updateById(orderSx);
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
    	EntityWrapper<OrderSx> orderSxWrapper;
    	boolean b = false;
    	if(id.indexOf(",")>0){
    		for(String str : id.split(",")){
    			orderSxWrapper = new EntityWrapper<OrderSx>();
    			orderSxWrapper.eq("ORDER_ID", str);
    	    	OrderSx orderSx = orderSxService.selectOne(orderSxWrapper);
    	    	if("orderFlag".equals(cloumn)){
    	    		orderSx.setOrderFlag(orderSx.getOrderFlag().equals("01")?"02":"01");
    	    	}
    	    	if("printFlag".equals(cloumn)){
    	    		orderSx.setPrintFlag(orderSx.getPrintFlag().equals("01")?"02":"01");
    	    	}
    	    	if("giftFlag".equals(cloumn)){
    	    		orderSx.setGiftFlag(orderSx.getGiftFlag().equals("01")?"02":"01");
    	    	}
    	    	if("checkoutFlag".equals(cloumn)){
    	    		orderSx.setCheckoutFlag(orderSx.getCheckoutFlag().equals("01")?"02":"01");
    	    	}
    	        orderSx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
    	        b = orderSxService.update(orderSx, orderSxWrapper);
    		}
    	}else{
    		orderSxWrapper = new EntityWrapper<OrderSx>();
    		orderSxWrapper.eq("ORDER_ID", id);
        	OrderSx orderSx = orderSxService.selectOne(orderSxWrapper);
        	if("orderFlag".equals(cloumn)){
        		orderSx.setOrderFlag(orderSx.getOrderFlag().equals("01")?"02":"01");
        	}
        	if("printFlag".equals(cloumn)){
        		orderSx.setPrintFlag(orderSx.getPrintFlag().equals("01")?"02":"01");
        	}
        	if("giftFlag".equals(cloumn)){
        		orderSx.setGiftFlag(orderSx.getGiftFlag().equals("01")?"02":"01");
        	}
        	if("checkoutFlag".equals(cloumn)){
        		orderSx.setCheckoutFlag(orderSx.getCheckoutFlag().equals("01")?"02":"01");
        	}
            orderSx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
            b = orderSxService.update(orderSx, orderSxWrapper);
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
		OrderSxVo orderSxVo = orderSxService.selectOrderByOrderId(id);
		OrderSx orderSx = new OrderSx();
		BeanUtils.copyNotNullProperties(orderSxVo, orderSx);
		orderSx.setCreateTime(DateUtil.strToShot(orderSx.getCreateTime()));
		orderSx.setUpdateTime(DateUtil.strToShot(orderSx.getUpdateTime()));
		orderSx.setDdDate(DateUtil.strToShot(orderSx.getDdDate()));
		changeMoney(orderSx, "0.01");
		BeanUtils.copyNotNullProperties(orderSx, orderSxVo);
		Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            model.addAttribute("recorder", userVo.getId());
            model.addAttribute("recorderName", userVo.getName());
        }
        System.out.println(orderSxVo.toString());
		model.addAttribute("orderSx", orderSxVo);
		return "admin/orderSx/orderSxEdit";
	}

	/**
	 * 编辑
	 * @param 
	 * @return
	 */
	@PostMapping("/edit")
	@ResponseBody
	public Object edit(@Valid OrderSxVo orderSxVo) {
		System.out.println(orderSxVo.toString());
		OrderSx orderSx = new OrderSx();
		BeanUtils.copyNotNullProperties(orderSxVo, orderSx);
		changeMoney(orderSx, "100");
		OrderSxVo order = orderSxService.selectOrderByOrderId(orderSxVo.getOrderId());
		BeanUtils.copyNotNullProperties(orderSx, order);
		
		Policyholder policyholder = new Policyholder();
		policyholder.setPid(orderSxVo.getPolicyholderId());
		Policyholder p = policyholderService.selectPolicyholder(policyholder);
		boolean b = false;
		if(p == null){
			BeanUtils.copyNotNullProperties(orderSxVo, policyholder);
			policyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
			policyholder.setStatus("0");
			b = policyholderService.insert(policyholder);
		}else{
			BeanUtils.copyNotNullProperties(orderSxVo, policyholder);
			System.out.println(policyholder.toString());
			b = policyholderService.updateById(policyholder);
		}
		
		Policyholder bpolicyholder = new Policyholder();
		bpolicyholder.setPid(orderSxVo.getInsuredId());
		
		Policyholder po = policyholderService.selectPolicyholder(bpolicyholder);
		bpolicyholder.setUserName(orderSxVo.getUserNameB());
		bpolicyholder.setSex(orderSxVo.getSexB());
		bpolicyholder.setMarryFlag(orderSxVo.getMarryFlagB());
		bpolicyholder.setBirthDate(orderSxVo.getBirthDateB());
		bpolicyholder.setCertType(orderSxVo.getCertTypeB());
		bpolicyholder.setCertNo(orderSxVo.getCertNoB());
		bpolicyholder.setNationality(orderSxVo.getNationalityB());
		bpolicyholder.setHousehold(orderSxVo.getHouseholdB());;
		bpolicyholder.setValidityDate(orderSxVo.getValidityDateB());
		bpolicyholder.setResidentType(orderSxVo.getResidentTypeB());
		bpolicyholder.setRprovince(orderSxVo.getProvinceB());
		bpolicyholder.setRcity(orderSxVo.getCityB());
		bpolicyholder.setRdistrict(orderSxVo.getDistrictB());
		bpolicyholder.setResidentialAddress(orderSxVo.getResidentialAddressB());
		bpolicyholder.setTelphone(orderSxVo.getTelphoneB());
		bpolicyholder.setMtelphone(orderSxVo.getMtelphoneB());
		bpolicyholder.setMail(orderSxVo.getMailB());
		bpolicyholder.setWorkUnit(orderSxVo.getWorkUnitB());
		bpolicyholder.setJobContent(orderSxVo.getJobContentB());
		bpolicyholder.setIndustry(orderSxVo.getIndustryB());
		bpolicyholder.setOccupation(orderSxVo.getOccupationB());
		if(po == null){
			BeanUtils.copyNotNullProperties(orderSxVo, bpolicyholder);
			bpolicyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
			bpolicyholder.setStatus("0");
			b = policyholderService.insert(bpolicyholder);
		}else{
			b = policyholderService.updateById(bpolicyholder);
		}
		
		Beneficial beneficial = new Beneficial();
		beneficial.setPersonId(orderSxVo.getBeneficiaryId());
		Beneficial be = beneficialService.selectBeneficial(beneficial);
		
		BeanUtils.copyNotNullProperties(orderSxVo, beneficial);
		beneficial.setSex(orderSxVo.getSexS());
		beneficial.setBirthDate(orderSxVo.getBirthDateS());
		beneficial.setCertType(orderSxVo.getCertTypeS());
		beneficial.setCertNo(orderSxVo.getCertNoS());
		beneficial.setValidityDate(orderSxVo.getValidityDateS());
		beneficial.setResidentialAddress(orderSxVo.getResidentialAddressS());
		beneficial.setOrderId(orderSx.getOrderId());
		beneficial.setPersonId(StringUtils.getUUId());
		beneficial.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		if(be == null){
			bpolicyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
			bpolicyholder.setStatus("0");
			b = beneficialService.insert(beneficial);
		}else{
			b = beneficialService.updateById(beneficial);
		}
		OrderSx orderUp = new OrderSx();
		BeanUtils.copyNotNullProperties(orderSxVo, orderUp);
		orderUp.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            orderUp.setMerchantId(userVo.getOrganizationId().toString());
            orderUp.setRecorder(userVo.getName());
            orderUp.setRecorderid(userVo.getId().toString());
            orderUp.setUpdateUser(userVo.getName());
        }
		b = orderSxService.updateById(orderUp);
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
        OrderSxVo orderSx = orderSxService.selectOrderByOrderId(id);
        orderSx.setCreateTime(DateUtil.strToShot(orderSx.getCreateTime()));
        orderSx.setUpdateTime(DateUtil.strToShot(orderSx.getUpdateTime()));
        OrderSx order = new OrderSx();
        BeanUtils.copyNotNullProperties(orderSx, order);
        changeMoney(order, "0.01");
        BeanUtils.copyNotNullProperties(order, orderSx);
        model.addAttribute("orderSx", orderSx);
        return "admin/orderSx/orderSxShow";
    }
    /**
     * 变更
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/changePage")
    public String changePage(Model model, String id) {
    	OrderSxVo orderSx = orderSxService.selectOrderByOrderId(id);
        orderSx.setCreateTime(DateUtil.strToShot(orderSx.getCreateTime()));
        orderSx.setUpdateTime(DateUtil.strToShot(orderSx.getUpdateTime()));
        OrderSx order = new OrderSx();
        BeanUtils.copyNotNullProperties(orderSx, order);
        changeMoney(order, "0.01");
        BeanUtils.copyNotNullProperties(order, orderSx);
        model.addAttribute("orderSx", orderSx);
        return "admin/orderSx/orderSxChange";
    }
    /**
     * 变更
     * @param 
     * @return
     */
    @PostMapping("/change")
    @ResponseBody
    public Object change(@Valid OrderSx orderSx) {
    	OrderSxVo order = orderSxService.selectOrderByOrderId(orderSx.getOrderId());
    	OrderSx orderUp = new OrderSx();
    	BeanUtils.copyNotNullProperties(order, orderUp);
    	BeanUtils.copyNotNullProperties(orderSx, orderUp);
    	changeMoney(orderUp, "100");
    	boolean b = orderSxService.updateById(orderUp);
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
    	OrderSxVo orderSx = orderSxService.selectOrderByOrderId(id);
        orderSx.setCreateTime(DateUtil.strToShot(orderSx.getCreateTime()));
        orderSx.setUpdateTime(DateUtil.strToShot(orderSx.getUpdateTime()));
        OrderSx order = new OrderSx();
        BeanUtils.copyNotNullProperties(orderSx, order);
        changeMoney(order, "0.01");
        BeanUtils.copyNotNullProperties(order, orderSx);
        model.addAttribute("orderSx", orderSx);
        if("01".equals(orderSx.getYjzfdx()))
        	return "admin/orderSx/orderSxPaySales";
        else
        	return "admin/orderSx/orderSxPayOper";
    }
    
    /**
     * 支付更改
     * @param 
     * @return
     */
    @PostMapping("/payEdit")
    @ResponseBody
    public Object payEdit(@Valid OrderSx orderSx) {
    	OrderSxVo order = orderSxService.selectOrderByOrderId(orderSx.getOrderId());
    	OrderSx orderUp = new OrderSx();
    	BeanUtils.copyNotNullProperties(order, orderUp);
    	BeanUtils.copyNotNullProperties(orderSx, orderUp);
    	changeMoney(orderUp, "100");
    	boolean b = orderSxService.updateById(orderUp);
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
    		OrderSxVo orderSx = new OrderSxVo();
    		orderSx.setOrderId(id);
    		model.addAttribute("orderSx", orderSx);
 	    	return "admin/orderSx/orderSxAcc";
    	}else{
    		OrderSxVo orderSx = orderSxService.selectOrderByOrderId(id);
            orderSx.setCreateTime(DateUtil.strToShot(orderSx.getCreateTime()));
            orderSx.setUpdateTime(DateUtil.strToShot(orderSx.getUpdateTime()));
            OrderSx order = new OrderSx();
            BeanUtils.copyNotNullProperties(orderSx, order);
            changeMoney(order, "0.01");
            BeanUtils.copyNotNullProperties(order, orderSx);
            model.addAttribute("orderSx", orderSx);
        	return "admin/orderSx/orderSxAcc";
    	}
    }
    /**
     * 支付更改
     * @param 
     * @return
     */
    @PostMapping("/accEdit")
    @ResponseBody
    public Object accEdit(@Valid OrderSx orderSx) {
    	boolean b = false;
    	if(orderSx.getOrderId().indexOf(",") > 0){
    		for(String s : orderSx.getOrderId().split(",")){
		    	OrderSxVo order = orderSxService.selectOrderByOrderId(s);
		    	OrderSx orderUp = new OrderSx();
		    	BeanUtils.copyNotNullProperties(order, orderUp);
		    	BeanUtils.copyNotNullProperties(orderSx, orderUp);
		    	orderUp.setOrderId(s);
		    	changeMoney(orderUp, "100");
		    	b = orderSxService.updateById(orderUp);
    		}
    	}else{
    		OrderSxVo order = orderSxService.selectOrderByOrderId(orderSx.getOrderId());
	    	OrderSx orderUp = new OrderSx();
	    	BeanUtils.copyNotNullProperties(order, orderUp);
	    	BeanUtils.copyNotNullProperties(orderSx, orderUp);
	    	changeMoney(orderUp, "100");
	    	b = orderSxService.updateById(orderUp);
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
   		EntityWrapper<OrderSx> entityWrapper = new EntityWrapper<OrderSx>();
   		
   		if(!"all".equals(id)){
   			entityWrapper.in("ORDER_ID", id.split(","));
   		}
   		List<OrderSx> list  = orderSxService.selectList(entityWrapper);
   		
   		List<OrderSxExcel> listEx = new ArrayList<OrderSxExcel>();
   		
       	if(list.size()>0){
   	    	for(OrderSx order:list){
   	    		OrderSxExcel orderSxExcel = new OrderSxExcel();
   	    		BeanUtils.copyNotNullProperties(order, orderSxExcel);
   	    		listEx.add(orderSxExcel);
   	    	}
       	}
   		Workbook wb = new XSSFWorkbook();
   		EasyExcel fastExcel = new EasyExcel(wb);
   		fastExcel.createExcel(response, listEx);
   		fastExcel.close();
   	}
    
	public static OrderSx changeMoney(OrderSx orderSx, String charge){
		if(orderSx.getBxgszxsqyjbl() != null) 
			orderSx.setBxgszxsqyjbl(orderSx.getBxgszxsqyjbl().multiply(new BigDecimal(charge)));
		if(orderSx.getBxgszxsqyj() != null) 
			orderSx.setBxgszxsqyj(orderSx.getBxgszxsqyj().multiply(new BigDecimal(charge)));
		if(orderSx.getGstcje() != null)
			orderSx.setGstcje(orderSx.getGstcje().multiply(new BigDecimal(charge)));
		if(orderSx.getGstcbl() != null) 
			orderSx.setGstcbl(orderSx.getGstcbl().multiply(new BigDecimal(charge)));
		if(orderSx.getGsjsje() != null)
			orderSx.setGsjsje(orderSx.getGsjsje().multiply(new BigDecimal(charge)));
		if(orderSx.getGsjsbl() != null)
			orderSx.setGsjsbl(orderSx.getGsjsbl().multiply(new BigDecimal(charge)));
		if(orderSx.getGsglfje() != null)
			orderSx.setGsglfje(orderSx.getGsglfje().multiply(new BigDecimal(charge)));
		if(orderSx.getGsglfbl() != null) 
			orderSx.setGsglfbl(orderSx.getGsglfbl().multiply(new BigDecimal(charge)));
		if(orderSx.getBxgskpyj() != null)
			orderSx.setBxgskpyj(orderSx.getBxgskpyj().multiply(new BigDecimal(charge)));
		if(orderSx.getBxgskpyjbl() != null)
			orderSx.setBxgskpyjbl(orderSx.getBxgskpyjbl().multiply(new BigDecimal(charge)));
		if(orderSx.getBxgsfx1sqyjbl() != null)
			orderSx.setBxgsfx1sqyjbl(orderSx.getBxgsfx1sqyjbl().multiply(new BigDecimal(charge)));
		if(orderSx.getBxgsfx1sqyj() != null)
			orderSx.setBxgsfx1sqyj(orderSx.getBxgsfx1sqyj().multiply(new BigDecimal(charge)));
		if(orderSx.getBxgsfx2sqyjbl() != null)
			orderSx.setBxgsfx2sqyjbl(orderSx.getBxgsfx2sqyjbl().multiply(new BigDecimal(charge)));
		if(orderSx.getBxgsfx2sqyj() != null)
			orderSx.setBxgsfx2sqyj(orderSx.getBxgsfx2sqyj().multiply(new BigDecimal(charge)));
		if(orderSx.getBfhj() != null)
			orderSx.setBfhj(orderSx.getBfhj().multiply(new BigDecimal(charge)));
		if(orderSx.getTbrnsr() != null) 
			orderSx.setTbrnsr(orderSx.getTbrnsr().multiply(new BigDecimal(charge)));
		if(orderSx.getBbrnsr() != null)
			orderSx.setBbrnsr(orderSx.getBbrnsr().multiply(new BigDecimal(charge)));
		if(orderSx.getJzx() != null)
			orderSx.setJzx(orderSx.getJzx().multiply(new BigDecimal(charge)));
		if(orderSx.getZxs() != null) 
			orderSx.setZxs(orderSx.getZxs().multiply(new BigDecimal(charge)));
		if(orderSx.getJfx1() != null) 
			orderSx.setJfx1(orderSx.getJfx1().multiply(new BigDecimal(charge)));
		if(orderSx.getFxs1() != null)
			orderSx.setFxs1(orderSx.getFxs1().multiply(new BigDecimal(charge)));
		if(orderSx.getJfx2() != null) 
			orderSx.setJfx2(orderSx.getJfx2().multiply(new BigDecimal(charge)));
		if(orderSx.getFxs2() != null)
			orderSx.setFxs2(orderSx.getFxs2().multiply(new BigDecimal(charge)));
		if(orderSx.getSalesManCommission() != null)
			orderSx.setSalesManCommission(orderSx.getSalesManCommission().multiply(new BigDecimal(charge)));
		if(orderSx.getZxyjbl() != null)
			orderSx.setZxyjbl(orderSx.getZxyjbl().multiply(new BigDecimal(charge)));
		if(orderSx.getZxyjje() != null)
			orderSx.setZxyjje(orderSx.getZxyjje().multiply(new BigDecimal(charge)));
		if(orderSx.getFxyjbl1() != null)
			orderSx.setFxyjbl1(orderSx.getFxyjbl1().multiply(new BigDecimal(charge)));
		if(orderSx.getFxyjje1() != null) 
			orderSx.setFxyjje1(orderSx.getFxyjje1().multiply(new BigDecimal(charge)));
		if(orderSx.getFxyjbl2() != null)
			orderSx.setFxyjbl2(orderSx.getFxyjbl2().multiply(new BigDecimal(charge)));
		if(orderSx.getFxyjje2() != null) 
			orderSx.setFxyjje2(orderSx.getFxyjje2().multiply(new BigDecimal(charge)));
		if(orderSx.getZhjrjbl() != null) 
			orderSx.setZhjrjbl(orderSx.getZhjrjbl().multiply(new BigDecimal(charge)));
		if(orderSx.getZhjrjje() != null)
			orderSx.setZhjrjje(orderSx.getZhjrjje().multiply(new BigDecimal(charge)));
		if(orderSx.getBxgsdqjl() != null) 
			orderSx.setBxgsdqjl(orderSx.getBxgsdqjl().multiply(new BigDecimal(charge)));
		if(orderSx.getRecordertc() != null)
			orderSx.setRecordertc(orderSx.getRecordertc().multiply(new BigDecimal(charge)));
		if(orderSx.getBgsml() != null) 
			orderSx.setBgsml(orderSx.getBgsml().multiply(new BigDecimal(charge)));
    	return orderSx;
    }
}
