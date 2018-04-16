package com.gewei.zhongshu.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.utils.BeanUtils;
import com.gewei.model.TChinaArea;
import com.gewei.model.YongJinZhengCe;
import com.gewei.model.vo.YongJinZhengCeVo;
import com.gewei.zhongshu.service.ITChinaAreaService;
import com.gewei.zhongshu.service.IYongJinZhengCeService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tiger
 * @since 2018-04-16
 */
@Controller
@RequestMapping("/mgr/yongJinZhengCe")
public class YongJinZhengCeController extends BaseController {

	@Autowired
	private IYongJinZhengCeService yongJinZhengCeService;
	@Autowired
	private ITChinaAreaService iTChinaAreaServiceImpl;

	@GetMapping("/manager")
	public String manager() {
		return "admin/yongJinZhengCe/yongJinZhengCeList";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(YongJinZhengCeVo yongJinZhengCeR, Integer page, Integer rows, String sort, String order) {
		System.out.println(JSON.toJSON(yongJinZhengCeR));
		YongJinZhengCe yongJinZhengCe = new YongJinZhengCe();
		BeanUtils.copyNotNullProperties(yongJinZhengCeR, yongJinZhengCe);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		yongJinZhengCe.setCountry(yongJinZhengCeR.getDistrict());
		StringBuilder stringBuilder = new StringBuilder("");
		String createTime = yongJinZhengCeR.getCreateTime();
		yongJinZhengCe.setCreateTime(null);
		if (createTime != null && !createTime.trim().equals("")) {
			stringBuilder.append(createTime.substring(0, 4));
			stringBuilder.append(createTime.substring(5, 7));
			stringBuilder.append(createTime.substring(8, 10));
			stringBuilder.append("000000");
			yongJinZhengCe.setCreateTime(stringBuilder.toString());
		}
		System.out.println(JSON.toJSON(yongJinZhengCe));
		EntityWrapper<YongJinZhengCe> ew = new EntityWrapper<YongJinZhengCe>(yongJinZhengCe);
		Page<YongJinZhengCe> pages = getPage(pageInfo);
		pages = yongJinZhengCeService.selectPage(pages, ew);
		List<YongJinZhengCe> records = pages.getRecords();
		HashMap<String, Object> columnMap = new HashMap<String, Object>(1);
		for (YongJinZhengCe yongJinZhengCe2 : records) {
			StringBuilder area = new StringBuilder("");
			String province = yongJinZhengCe2.getProvince();
			columnMap.put("ID", province);
			List<TChinaArea> list = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list != null && list.size() > 0) {
				String grantName = list.get(0).getName();
				area.append(grantName + "-");
			}
			String city = yongJinZhengCe2.getCity();
			columnMap.put("ID", city);
			List<TChinaArea> list2 = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list2 != null && list2.size() > 0) {
				String grantName = list2.get(0).getName();
				area.append(grantName + "-");
			}
			String country = yongJinZhengCe2.getCountry();
			columnMap.put("ID", country);
			List<TChinaArea> list3 = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list3 != null && list3.size() > 0) {
				String grantName = list3.get(0).getName();
				area.append(grantName + "-");
			}
			String areaName = (area.toString()).equals("") ? null : area.toString().substring(0, area.toString().length() - 1);
			yongJinZhengCe2.setProvince(areaName);
		}
		pageInfo.setRows(records);
		pageInfo.setTotal(pages.getTotal());
		return pageInfo;
	}

	/**
	 * 添加页面
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "admin/yongJinZhengCe/yongJinZhengCeAdd";
	}

	/**
	 * 添加
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(@Valid YongJinZhengCe yongJinZhengCe) {
		boolean b = yongJinZhengCeService.insert(yongJinZhengCe);
		if (b) {
			return renderSuccess("添加成功！");
		} else {
			return renderError("添加失败！");
		}
	}

	/**
	 * 编辑
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		YongJinZhengCe yongJinZhengCe = yongJinZhengCeService.selectById(id);
		model.addAttribute("yongJinZhengCe", yongJinZhengCe);
		return "admin/yongJinZhengCe/yongJinZhengCeEdit";
	}

	/**
	 * 编辑
	 */
	@PostMapping("/edit")
	@ResponseBody
	public Object edit(@Valid YongJinZhengCe yongJinZhengCe) {
		// yongJinZhengCe.setUpdateTime(new Date());
		boolean b = yongJinZhengCeService.updateById(yongJinZhengCe);
		if (b) {
			return renderSuccess("编辑成功！");
		} else {
			return renderError("编辑失败！");
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		EntityWrapper<YongJinZhengCe> wrapper = new EntityWrapper<YongJinZhengCe>();
		wrapper.eq("ID", id);
		boolean b = yongJinZhengCeService.delete(wrapper);
		if (b) {
			return renderSuccess("删除成功！");
		} else {
			return renderError("删除失败！");
		}
	}
}
