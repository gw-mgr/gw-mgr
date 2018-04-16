package com.gewei.fuwushang.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.gewei.commons.report.excel.EasyExcel;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.fuwushang.service.ITCustomerBasicinfoService;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.excel.CustomerExcel;
import com.gewei.model.vo.EditOrderInfo;

/**
 * <p>
 * 会员基本信息表，以会员编号%8取莫横向分表。 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-14
 */
@Controller
@RequestMapping("/mgr/tCustomerBasicinfo")
public class TCustomerBasicinfoController extends BaseController {

    @Autowired 
    private ITCustomerBasicinfoService tCustomerBasicinfoService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/tCustomerBasicinfo/tCustomerBasicinfoList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(TCustomerBasicinfo tCustomerBasicinfo, Integer page, Integer rows, String sort,String order, String name, String val) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        EntityWrapper<TCustomerBasicinfo> ew = new EntityWrapper<TCustomerBasicinfo>();
        ew.eq("STATUS", "01");
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(val)) {
			if ("userName".equals(name)) {
				ew.like("USER_NAME", val);
			}
			if ("telphone".equals(name)) {
				ew.eq("TELEPHONE", val);
			}
		}
        Page<TCustomerBasicinfo> pages = getPage(pageInfo);
        pages = tCustomerBasicinfoService.selectPage(pages, ew);
        pageInfo.setRows(pages.getRecords());
        pageInfo.setTotal(pages.getTotal());
        return pageInfo;
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
		TCustomerBasicinfo customer = tCustomerBasicinfoService.getCustomerInfoByUserId(id);
		model.addAttribute("tCustomerBasicinfo", customer);
		return "admin/tCustomerBasicinfo/tCustomerBasicinfoShow";
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
				TCustomerBasicinfo customer = tCustomerBasicinfoService.getCustomerInfoByUserId(str);
				customer.setStatus("02");
				customer.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
				tCustomerBasicinfoService.updateCustomerStatus(customer);
			}
		} else {
			TCustomerBasicinfo customer = tCustomerBasicinfoService.getCustomerInfoByUserId(id);
			customer.setStatus("02");
			customer.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			tCustomerBasicinfoService.updateCustomerStatus(customer);
		}
		return renderSuccess("删除成功！");
	}
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletResponse response, String id) throws IOException, InvalidFormatException {
		EntityWrapper<TCustomerBasicinfo> entityWrapper = new EntityWrapper<TCustomerBasicinfo>();
		
		if(!"all".equals(id)){
			entityWrapper.in("USER_ID", id.split(","));
		}
		List<TCustomerBasicinfo> list  = tCustomerBasicinfoService.selectList(entityWrapper);
		List<CustomerExcel> listExcel = new ArrayList<CustomerExcel>();
		for(TCustomerBasicinfo customer : list){
			CustomerExcel excel = new CustomerExcel();
			BeanUtils.copyNotNullProperties(customer, excel);
			listExcel.add(excel);
		}
		Workbook wb = new XSSFWorkbook();
		EasyExcel fastExcel = new EasyExcel(wb);
		fastExcel.createExcel(response, listExcel);
		fastExcel.close();
	}
}
