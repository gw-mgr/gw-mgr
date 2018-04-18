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
import com.gewei.commons.utils.BeanUtils;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.fuwushang.service.ITInsurerService;
import com.gewei.fuwushang.service.IUserService;
import com.gewei.model.TInsurer;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.model.excel.SalesManExcel;
import com.gewei.model.vo.UserVo;
import com.gewei.wx.service.ITMemberBasicinfoService;

/**
 * 业务员管理
 * 
 * @ClassName: SalesManController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author caoyifu@iyooc.cn
 * @date 2018年1月24日 下午4:44:12
 *
 */
@Controller
@RequestMapping("/mgr/salesman")
public class SalesManController extends BaseController {

	@Autowired
	private ITMemberBasicinfoService salesManService;
	@Autowired
	private ITInsurerService insurerService;
    @Autowired 
    private IUserService userService;
	/**
	 * 权限管理页
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/salesman/salesman";
	}

	/**
	 * 权限列表
	 *
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid(Integer page, Integer rows, String sort, String order, String searchName, String val) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		
		if (StringUtils.isNotBlank(searchName) && StringUtils.isNotBlank(val)) {
			if("userName".equals(searchName)){
				condition.put("user_name", val);
			}
			if("mtelphone".equals(searchName)){
				condition.put("mtelphone", val);
			}
			if("wechat".equals(searchName)){
				condition.put("wechat", val);
			}
		    pageInfo.setCondition(condition);
		}
		salesManService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加权限页
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/salesman/salesmanAdd";
	}

	/**
	 * 添加权限
	 *
	 * @param role
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid TMemberBasicinfo salesMan) {
		salesMan.setUserId(StringUtils.getUUId());
		Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            salesMan.setMerchantId(userVo.getOrganizationId());
        }
		salesMan.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		salesManService.insert(salesMan);
		return renderSuccess("添加成功！");
	}

	/**
	 * 删除权限
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String id) {
		if (id.indexOf(",") > 0) {
			String arr[] = id.split(",");
			for (String str : arr) {
				TMemberBasicinfo salesMan = salesManService.selectById(str);
				salesMan.setStatus("03");
				salesManService.updateById(salesMan);
			}
		} else {
			TMemberBasicinfo salesMan = salesManService.selectById(id);
			salesMan.setStatus("03");
			salesManService.updateById(salesMan);
		}
		return renderSuccess("删除成功！");
	}

	/**
	 * 编辑权限页
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, String id) {
		TMemberBasicinfo salesMan = salesManService.selectById(id);
		model.addAttribute("salesMan", salesMan);
		return "admin/salesman/salesmanEdit";
	}

	/**
	 * 查看权限页
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/showPage")
	public String showPage(Model model, String id) {
		TMemberBasicinfo salesMan = salesManService.selectById(id);
		TInsurer ins  = insurerService.selectById(salesMan.getInsCompany());
		salesMan.setInsCompany(ins.getInsurerName());
		model.addAttribute("salesMan", salesMan);
		return "admin/salesman/salesmanShow";
	}

	/**
	 * 编辑权限
	 *
	 * @param role
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(@Valid TMemberBasicinfo salesMan) {
		TMemberBasicinfo member = salesManService.selectById(salesMan.getUserId());
		BeanUtils.copyNotNullProperties(salesMan, member);
		member.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection collection = currentUser.getPrincipals();
        if (null != collection) {
            String loginName = collection.getPrimaryPrincipal().toString();
            UserVo userVo = userService.selectUserVoByLoginName(loginName);
            member.setMerchantId(userVo.getOrganizationId());
        }
		salesManService.updateById(member);
		return renderSuccess("编辑成功！");
	}

	/**
	 * 停用权限
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/disable")
	@ResponseBody
	public Object disable(String id) {
		TMemberBasicinfo salesMan = salesManService.selectById(id);
		salesMan.setStatus("02");
		salesManService.updateById(salesMan);
		return renderSuccess("停用成功！");
	}

	/**
	 * 启用权限
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/enable")
	@ResponseBody
	public Object enable(String id) {
		TMemberBasicinfo salesMan = salesManService.selectById(id);
		salesMan.setStatus("01");
		salesManService.updateById(salesMan);
		return renderSuccess("启用成功！");
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
		EntityWrapper<TMemberBasicinfo> entityWrapper = new EntityWrapper<TMemberBasicinfo>();
		entityWrapper.ne("STATUS", "03");
		if(!"all".equals(id)){
			entityWrapper.in("ID", id.split(","));
		}
		List<TMemberBasicinfo> list  = salesManService.selectList(entityWrapper);
		List<SalesManExcel> listEx = new ArrayList<SalesManExcel>();
		for(TMemberBasicinfo sale : list){
			SalesManExcel salesManExcel = new SalesManExcel();
			BeanUtils.copyNotNullProperties(sale, salesManExcel);
			TInsurer ins  = insurerService.selectById(salesManExcel.getInsCompany());
			salesManExcel.setInsCompanyName(ins.getInsurerName());
			listEx.add(salesManExcel);
		}
		Workbook wb = new XSSFWorkbook();
		EasyExcel fastExcel = new EasyExcel(wb);
		fastExcel.createExcel(response, listEx);
		fastExcel.close();
	}
}
