package com.gewei.zhongshu.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.commons.report.excel.EasyExcel;
import com.gewei.commons.result.PageInfo;
import com.gewei.commons.shiro.PasswordHash;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.StringUtils;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.model.TAccount;
import com.gewei.model.TChinaArea;
import com.gewei.model.TMerchantManage;
import com.gewei.model.TMerchantManageRe;
import com.gewei.model.User;
import com.gewei.model.excel.MerchantManageExcel;
import com.gewei.model.vo.MerchantVo;
import com.gewei.model.vo.UserVo;
import com.gewei.wx.service.IAccountService;
import com.gewei.zhongshu.service.ITChinaAreaService;
import com.gewei.zhongshu.service.ITMerchantManageService;
import com.gewei.zhongshu.service.IUserService;

/**
 * <p>
 * 服务商基本信息 前端控制器
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-26
 */
@Controller
@RequestMapping("/mgr/tMerchantManage")
public class TMerchantManageController extends BaseController {
	@Autowired
	private IAccountService iAccountServiceImpl;
	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private PasswordHash passwordHash;
	@Autowired
	private ITChinaAreaService iTChinaAreaServiceImpl;
	@Autowired
	private ITMerchantManageService tMerchantManageServiceImpl;

	@GetMapping("/manager")
	public Object manager(String status) {
		return "admin/tMerchantManage/tMerchantManageList";
	}

	/**
	 * 添加
	 * @param 
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("/add")
	@ResponseBody
	@Transactional
	public Object add(@Valid TMerchantManageRe tMerchantManageRe, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		try {
			System.out.println("tMerchantManageRe:" + JSON.toJSON(tMerchantManageRe));
			// 添加服务商基本信息======================================================================
			TMerchantManage tMerchantManage = new TMerchantManage();
			long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
			String uuid = UUIDUtil.getUUID32().substring(0, 8);
			String merchantId = "FWS" + currTime + uuid;
			tMerchantManage.setMerchantId(merchantId);
			tMerchantManage.setMerchantName(tMerchantManageRe.getMerchantName());
			// 图片上传
			if (file.getSize() != 0) {// 判断上传的文件是否为空
				String path = null;// 文件路径
				String type = null;// 文件类型
				String fileName = file.getOriginalFilename();// 文件原名称
				// 判断文件类型
				type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
				// 项目在容器中实际发布运行的根路径
				String realPath = request.getSession().getServletContext().getRealPath("static/upload/");
				// 设置存放图片文件的路径
				path = realPath + "\\" + tMerchantManage.getMerchantId() + "_" + currTime + "." + type;
				// 转存文件到指定的路径
				file.transferTo(new File(path));
				String merchantPhotoUrl = "/static/upload/" + tMerchantManage.getMerchantId() + "_" + currTime + "." + type;
				tMerchantManage.setMerchantPhotoUrl(merchantPhotoUrl);
			} else {
				String merchantPhotoUrl = "/static/upload/default.jpg";
				tMerchantManage.setMerchantPhotoUrl(merchantPhotoUrl);
			}
			// 服务商类型
			tMerchantManage.setMerchantType(tMerchantManageRe.getMerchantType());
			// 授权区域封装
			if (tMerchantManageRe.getGrantAreaIds() != null && tMerchantManageRe.getGrantAreaIds().length > 0) {
				String grantArea = assembleGrantArea(tMerchantManageRe.getGrantAreaIds());
				tMerchantManage.setGrantArea(grantArea);
			}
			tMerchantManage.setProvince("省/直辖市".equals(tMerchantManageRe.getProvince()) ? null : tMerchantManageRe.getProvince());
			tMerchantManage.setCity("城市".equals(tMerchantManageRe.getCity()) ? null : tMerchantManageRe.getCity());
			tMerchantManage.setCountry("县区".equals(tMerchantManageRe.getCountry()) ? null : tMerchantManageRe.getCountry());
			tMerchantManage.setMerchantAddr(tMerchantManageRe.getMerchantAddr());
			tMerchantManage.setTelphone(tMerchantManageRe.getTelphone());
			tMerchantManage.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()));
			tMerchantManage.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()));
			tMerchantManage.setMerchantDescript(tMerchantManageRe.getMerchantDescript());
			// 记录操作人
			Subject currentUser = SecurityUtils.getSubject();
			PrincipalCollection collection = currentUser.getPrincipals();
			if (null != collection) {
				String loginName = collection.getPrimaryPrincipal().toString();
				String operatorId = userServiceImpl.getUserIdByLoginName(loginName);
				tMerchantManage.setOperatorId(operatorId);
			}
			tMerchantManage.setStatus("01");
			tMerchantManageServiceImpl.insert(tMerchantManage);
			// 插入账务记录
			TAccount tAccount = new TAccount();
			tAccount.setBalance(0L);
			tAccount.setPersonId(tMerchantManage.getMerchantId());
			tAccount.setBankHostName("");
			tAccount.setBankCardId("");
			tAccount.setTotalIncome(0L);
			tAccount.setSettleApplying(0L);
			tAccount.setBalance(0L);
			tAccount.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			iAccountServiceImpl.insert(tAccount);
			// 初始化一个微信端的admin账号======================================================================
			// 添加服务商admin账户,作为服务商系统的操作员======================================================================
			UserVo userVo = new UserVo();
			userVo.setId(UUIDUtil.getUUID32());
			userVo.setLoginName(tMerchantManage.getMerchantName());
			List<User> list = userServiceImpl.selectByLoginName(userVo);
			if (list != null && !list.isEmpty()) {
				return renderError("登录名已存在!");
			}
			String salt = StringUtils.getUUId();
			String pwd = passwordHash.toHex("admin", salt);
			userVo.setSalt(salt);
			userVo.setPassword(pwd);
			userVo.setName(tMerchantManage.getMerchantName());
			userVo.setUserType("02");
			userVo.setPhone(tMerchantManageRe.getTelphone());
			userVo.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
			userVo.setOrganizationId(tMerchantManage.getMerchantId());
			User user = new User();
			BeanUtils.copyProperties(userVo, user);
			userServiceImpl.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
		return renderSuccess("添加成功！");
	}

	/**
	 * @Author: Tiger
	 * @Description: 拼装授权区域
	 * @param @param grantAreaId
	 * @param @return
	 * @return: String
	 * @date: 2018年2月11日 上午10:25:55
	 * @throws
	 */
	private String assembleGrantArea(String[] grantAreaIdArray) {
		ArrayList<String> grantAreaList = new ArrayList<String>();
		for (String grantAreaId : grantAreaIdArray) {
			grantAreaList.add(grantAreaId);
		}
		// 去除子元素
		ArrayList<String> removeChildAreas = removeChildAreas(grantAreaList);
		// 如果勾选中国，返回为：中国
		if (removeChildAreas.contains("0")) {
			return "0";
		}
		// 没有勾选中国，遍历所有勾选的元素，返回为：父元素-勾选元素
		StringBuffer grantArea = new StringBuffer();
		for (String grantAreaId : removeChildAreas) {
			// 父元素
			String parentAreaId = tMerchantManageServiceImpl.getParentAreaIdByAreaId(grantAreaId);
			if (!"0".equals(parentAreaId)) {
				// 父元素的父元素
				String parent2AreaId = tMerchantManageServiceImpl.getParentAreaIdByAreaId(parentAreaId);
				if (!"0".equals(parent2AreaId)) {
					grantArea.append(parent2AreaId);
					grantArea.append("-");
				}
				grantArea.append(parentAreaId);
				grantArea.append("-");
			}
			grantArea.append(grantAreaId);
			grantArea.append(",");
		}
		return grantArea.toString().substring(0, grantArea.length() - 1);
	}

	/**
	 * @Author: Tiger
	 * @Description: 若勾选了父元素，则去除勾选的子元素
	 * @param @param grantAreaIdArray
	 * @return: void
	 * @date: 2018年2月21日 下午6:38:22
	 * @throws
	 */
	private ArrayList<String> removeChildAreas(ArrayList<String> grantAreaList) {
		ArrayList<String> childAreaIdList = new ArrayList<String>();
		if (grantAreaList.contains("0")) {
			// 勾选中国
			grantAreaList.removeAll(grantAreaList);
			grantAreaList.add("0");
		} else {
			// 未勾选中国
			for (String grantAreaId : grantAreaList) {
				// 所有子元素
				List<TChinaArea> childAreaList = tMerchantManageServiceImpl.getChildAreaIdByAreaId(grantAreaId);
				if (childAreaList != null) {
					for (TChinaArea tChinaArea : childAreaList) {
						String childAreaId = tChinaArea.getId().toString();
						childAreaIdList.add(childAreaId);
						// 所有二级子元素
						List<TChinaArea> child2AreaList = tMerchantManageServiceImpl.getChildAreaIdByAreaId(childAreaId);
						if (child2AreaList != null) {
							for (TChinaArea tChina2Area : child2AreaList) {
								String child2AreaId = tChina2Area.getId().toString();
								childAreaIdList.add(child2AreaId);
								// 所有三级子元素
								List<TChinaArea> child3AreaList = tMerchantManageServiceImpl.getChildAreaIdByAreaId(child2AreaId);
								if (child3AreaList != null) {
									for (TChinaArea tChina3Area : child3AreaList) {
										String child3AreaId = tChina3Area.getId().toString();
										childAreaIdList.add(child3AreaId);
									}
								}
							}
						}
					}
				}
			}
		}
		// 勾选其他父元素，去除子元素
		for (String childAreaId : childAreaIdList) {
			grantAreaList.remove(childAreaId);
		}
		return grantAreaList;
	}

	@GetMapping("/exportExcel")
	@ResponseBody
	public Object exportExcel(MerchantVo merchantVo, HttpServletResponse response) {
		try {
			// 1.查询要导出的数据
			PageInfo pageInfo = new PageInfo(0, 9999999, "UPDATE_TIME", "desc");
			Map<String, Object> condition = getCondition(merchantVo);
			pageInfo.setCondition(condition);
			tMerchantManageServiceImpl.selectDataGrid(pageInfo);
			@SuppressWarnings({ "unchecked" })
			List<Map<String, Object>> exportExcelList = pageInfo.getRows();
			// 2.封装到EXCEL
			Workbook wb = new XSSFWorkbook();
			EasyExcel fastExcel = new EasyExcel(wb);
			List<MerchantManageExcel> list = new ArrayList<MerchantManageExcel>();
			for (Map<String, Object> merchantManageMap : exportExcelList) {
				MerchantManageExcel merchantManageExcel = new MerchantManageExcel();
				merchantManageExcel.setMerchantId(merchantManageMap.get("merchantId").toString());
				merchantManageExcel.setMerchantName(merchantManageMap.get("merchantName").toString());
				merchantManageExcel.setMerchantType(merchantManageMap.get("merchantTypeName").toString());
				merchantManageExcel.setMerchantDescript(merchantManageMap.get("merchantDescript").toString());
				merchantManageExcel.setMerchantAddr(merchantManageMap.get("address").toString());
				merchantManageExcel.setGrantArea(merchantManageMap.get("grantAreaName").toString());
				merchantManageExcel.setCreateTime(merchantManageMap.get("createTime").toString());
				merchantManageExcel.setTelphone(merchantManageMap.get("telphone").toString());
				merchantManageExcel.setBalance(merchantManageMap.get("balance").toString());
				merchantManageExcel.setStatus(merchantManageMap.get("statusName").toString());
				list.add(merchantManageExcel);
			}
			fastExcel.createExcel(response, list);
			fastExcel.close();
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("导出失败！");
		}
		return renderSuccess("导出成功！");
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(MerchantVo merchantVo, Integer page, Integer rows, String sort, String order) throws UnsupportedEncodingException {
		System.out.println("merchantVo:" + JSON.toJSONString(merchantVo));
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = getCondition(merchantVo);
		pageInfo.setCondition(condition);
		tMerchantManageServiceImpl.selectDataGrid(pageInfo);
		return pageInfo;
	}

	private Map<String, Object> getCondition(MerchantVo merchantVo) throws UnsupportedEncodingException {
		Map<String, Object> condition = new HashMap<String, Object>();
		if (merchantVo.getCreateStartTime() != null && !"".equals(merchantVo.getCreateStartTime())) {
			String createStartTime = merchantVo.getCreateStartTime().replaceAll("-", "") + "000000";
			condition.put("createStartTime", createStartTime);
		}
		if (merchantVo.getCreateEndTime() != null && !"".equals(merchantVo.getCreateEndTime())) {
			String createEndTime = merchantVo.getCreateEndTime().replaceAll("-", "") + "000000";
			condition.put("createEndTime", createEndTime);
		}
		if (merchantVo.getProvince() != null && !"".equals(merchantVo.getProvince()) && !"省/直辖市".equals(merchantVo.getProvince())) {
			condition.put("province", merchantVo.getProvince());
		}
		if (merchantVo.getCity() != null && !"".equals(merchantVo.getCity()) && !"城市".equals(merchantVo.getCity())) {
			condition.put("city", merchantVo.getCity());
		}
		if (merchantVo.getCountry() != null && !"".equals(merchantVo.getCountry()) && !"县区".equals(merchantVo.getCountry())) {
			condition.put("country", merchantVo.getCountry());
		}
		if (merchantVo.getMerchantType() != null && !"all".equals(merchantVo.getMerchantType())) {
			condition.put("merchantType", merchantVo.getMerchantType());
		}
		String keywordInfo = merchantVo.getKeywordInfo();
		if (keywordInfo != null && !"".equals(keywordInfo)) {
			condition.put("keywordInfo", keywordInfo);
		}
		return condition;
	}

	/**
	 * @Author: Tiger
	 * @Description: 修改服务商状态status
	 * @param @param id
	 * @param @return
	 * @return: Object
	 * @date: 2018年2月6日 下午10:49:33
	 * @throws
	 */
	@PostMapping("/updateStatus")
	@ResponseBody
	public Object updateStatus(String merchantId, String status) {
		try {
			// 服务商校验
			EntityWrapper<TMerchantManage> entityWrapper = new EntityWrapper<TMerchantManage>();
			entityWrapper.eq("MERCHANT_ID", merchantId);
			TMerchantManage merchantManage = tMerchantManageServiceImpl.selectOne(entityWrapper);
			if (merchantManage == null)
				return renderError("服务商不存在");
			// status校验
			if (status == null || !status.equals("01") || !status.equals("02") || !status.equals("03"))
				return renderError("status参数有误");
			tMerchantManageServiceImpl.updateStatus(merchantId, status);
		} catch (Exception e) {
			return renderError("修改失败！");
		}
		return renderSuccess("修改成功！");
	}

	/**
	 * 编辑
	 * @param 
	 * @return
	 */
	@PostMapping("/edit")
	@ResponseBody
	public Object edit(@Valid TMerchantManageRe tMerchantManageRe, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Long currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date());
		try {
			if (file.getSize() != 0) {// 判断上传的文件是否为空
				String path = null;// 文件路径
				String type = null;// 文件类型
				String fileName = file.getOriginalFilename();// 文件原名称
				// 判断文件类型
				type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
				// 项目在容器中实际发布运行的根路径
				String realPath = request.getSession().getServletContext().getRealPath("static/upload/");
				// 设置存放图片文件的路径
				path = realPath + "\\" + tMerchantManageRe.getMerchantId() + "_" + currTime + "." + type;
				// 转存文件到指定的路径
				file.transferTo(new File(path));
				String merchantPhotoUrl = "/static/upload/" + tMerchantManageRe.getMerchantId() + "_" + currTime + "." + type;
				tMerchantManageRe.setMerchantPhotoUrl(merchantPhotoUrl);
			}
			// 授权区域封装
			if (tMerchantManageRe.getGrantAreaIds() != null && tMerchantManageRe.getGrantAreaIds().length > 0) {
				String grantArea = assembleGrantArea(tMerchantManageRe.getGrantAreaIds());
				tMerchantManageRe.setGrantArea(grantArea);
			}
			tMerchantManageRe.setProvince("省/直辖市".equals(tMerchantManageRe.getProvince()) ? null : tMerchantManageRe.getProvince());
			tMerchantManageRe.setCity("城市".equals(tMerchantManageRe.getCity()) ? null : tMerchantManageRe.getCity());
			tMerchantManageRe.setCountry("县区".equals(tMerchantManageRe.getCountry()) ? null : tMerchantManageRe.getCountry());
			tMerchantManageRe.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()));
			// 记录操作人
			Subject currentUser = SecurityUtils.getSubject();
			PrincipalCollection collection = currentUser.getPrincipals();
			if (null != collection) {
				String loginName = collection.getPrimaryPrincipal().toString();
				String operatorId = userServiceImpl.getUserIdByLoginName(loginName);
				tMerchantManageRe.setOperatorId(operatorId);
			}
			tMerchantManageRe.setStatus(tMerchantManageRe.getStatus());
			TMerchantManage tMerchantManage = new TMerchantManage();
			BeanUtils.copyProperties(tMerchantManageRe, tMerchantManage);
			tMerchantManageServiceImpl.updateById(tMerchantManage);
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
		return renderSuccess("添加成功！");
	}

	/**
	* 查询所有的授权区域资源tree
	*/
	@RequestMapping("/merchantGrant/allTrees")
	@ResponseBody
	public Object allTree() {
		return tMerchantManageServiceImpl.selectAllTree();
	}

	/**
	 * 查询所有的授权区域资源tree
	 */
	@RequestMapping("/merchantGrant/findGrantIdsByMerchantId")
	@ResponseBody
	public Object findGrantIdsByMerchantId(String merchantId) {
		List<Long> resources = tMerchantManageServiceImpl.findGrantIdsByMerchantId(merchantId);
		return renderSuccess(resources);
	}

	@PostMapping("/addMerchantTypeList")
	@ResponseBody
	public Object addMerchantTypeList() {
		List<Map<String, String>> merchantTypeList = tMerchantManageServiceImpl.selectMerchantTypeList();
		return merchantTypeList;
	}

	@PostMapping("/merchantTypeList")
	@ResponseBody
	public Object merchantTypeList(String pid) {
		List<Map<String, String>> merchantTypeList = tMerchantManageServiceImpl.selectMerchantTypeList(pid);
		return merchantTypeList;
	}

	@PostMapping("/chinaAreaList")
	@ResponseBody
	public Object provinceList(String pId) {
		List<Map<String, String>> provinceList = tMerchantManageServiceImpl.selectProvinceList(pId);
		return provinceList;
	}

	@GetMapping("/orderInfo")
	public String orderInfo(String orderType) {
		return "admin/orderManage/tOrderInfo";
	}

	@GetMapping("/addPage")
	public String addPage() {
		return "admin/tMerchantManage/tMerchantManageAdd";
	}

	@GetMapping("/editPage")
	public String editPage(Model model, String merchantId) {
		TMerchantManage tMerchantManage = tMerchantManageServiceImpl.selectById(merchantId);
		System.out.println("+++++++++++++++++++" + tMerchantManage);
		HashMap<String, Object> columnMap = new HashMap<String, Object>(1);
		String province = tMerchantManage.getProvince();
		if (province != null && !"".equals(province)) {
			columnMap.put("ID", province);
			List<TChinaArea> list = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list != null && list.size() > 0) {
				String grantName = list.get(0).getName();
				tMerchantManage.setProvince(grantName);
				;
			}
		}
		String city = tMerchantManage.getCity();
		if (city != null && !"".equals(city)) {
			columnMap.put("ID", city);
			List<TChinaArea> list = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list != null && list.size() > 0) {
				String grantName = list.get(0).getName();
				tMerchantManage.setCity(grantName);
				;
			}
		}
		String country = tMerchantManage.getCountry();
		if (country != null && !"".equals(country)) {
			columnMap.put("ID", country);
			List<TChinaArea> list = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list != null && list.size() > 0) {
				String grantName = list.get(0).getName();
				tMerchantManage.setCountry(grantName);
				;
			}
		}
		model.addAttribute("tMerchantManage", tMerchantManage);
		System.out.println("+++++++++++++++++++" + tMerchantManage);
		return "admin/tMerchantManage/tMerchantManageEdit";
	}

	@GetMapping("/lookPage")
	public String lookPage(Model model, String merchantId) {
		TMerchantManage tMerchantManage = tMerchantManageServiceImpl.selectById(merchantId);
		TMerchantManageRe tMerchantManageRe = new TMerchantManageRe();
		BeanUtils.copyProperties(tMerchantManage, tMerchantManageRe);
		// 1.授权范围设置
		String merchantTypeStr = tMerchantManageRe.getMerchantType();
		if (merchantTypeStr != null) {
			String[] merchantTypeArray = merchantTypeStr.split(",");
			String merchantTypes = "";
			for (String merchantType : merchantTypeArray) {
				if ("ROOTLC".equals(merchantType)) {
					merchantTypes += "理财产品,";
				} else if ("ROOTDK".equals(merchantType)) {
					merchantTypes += "贷款业务,";
				} else if ("ROOTQC".equals(merchantType)) {
					merchantTypes += "二手汽车,";
				} else if ("ROOTCD".equals(merchantType)) {
					merchantTypes += "车务代办,";
				} else if ("ROOTCM".equals(merchantType)) {
					merchantTypes += "汽车美容,";
				}
			}
			// 去除最后一个逗号
			merchantTypes = "".equals(merchantTypes) ? null : merchantTypes.substring(0, merchantTypes.length() - 1);
			tMerchantManageRe.setMerchantType(merchantTypes);
		}
		// 2.授权区域设置
		String grantAreaNameRes = "";
		String grantAreaStr = tMerchantManageRe.getGrantArea();
		if (grantAreaStr != null) {
			String[] grantAreaArray = grantAreaStr.split(",");
			if (!"".equals(grantAreaArray[0])) {
				HashMap<String, Object> columnMap = new HashMap<String, Object>(1);
				for (String grantArea : grantAreaArray) {
					String grantNameChildRes = "";
					String[] grantAreaIdArray = grantArea.split("-");
					for (String grantAreaId : grantAreaIdArray) {
						columnMap.put("ID", grantAreaId);
						List<TChinaArea> list = iTChinaAreaServiceImpl.selectByMap(columnMap);
						if (list != null && list.size() > 0) {
							String grantName = list.get(0).getName();
							grantNameChildRes += grantName + "-";
						}
					}
					grantNameChildRes = "".equals(grantNameChildRes) ? "" : grantNameChildRes.substring(0, grantNameChildRes.length() - 1);
					grantAreaNameRes += grantNameChildRes + ",";
				}
			}
		}
		grantAreaNameRes = "".equals(grantAreaNameRes) ? "" : grantAreaNameRes.substring(0, grantAreaNameRes.length() - 1);
		tMerchantManageRe.setGrantArea(grantAreaNameRes);
		// 2.服务商地址
		String province = tMerchantManageRe.getProvince();
		String city = tMerchantManageRe.getCity();
		String country = tMerchantManageRe.getCountry();
		String merchantAddr = tMerchantManageRe.getMerchantAddr();
		StringBuffer address = new StringBuffer("");
		HashMap<String, Object> columnMap = new HashMap<String, Object>(1);
		if (province != null && !"".equals(province)) {
			columnMap.put("ID", province);
			List<TChinaArea> list = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list != null && list.size() > 0) {
				province = list.get(0).getName();
			}
			tMerchantManageRe.setProvince(province);
			address.append(province);
			address.append("-");
		}
		if (city != null && !"".equals(city)) {
			columnMap.put("ID", city);
			List<TChinaArea> list = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list != null && list.size() > 0) {
				city = list.get(0).getName();
			}
			tMerchantManageRe.setCity(city);
			address.append(city);
			address.append("-");
		}
		if (country != null && !"".equals(country)) {
			columnMap.put("ID", country);
			List<TChinaArea> list = iTChinaAreaServiceImpl.selectByMap(columnMap);
			if (list != null && list.size() > 0) {
				country = list.get(0).getName();
			}
			tMerchantManageRe.setCountry(country);
			address.append(country);
			address.append("-");
		}
		if (merchantAddr != null) {
			address.append(merchantAddr);
		}
		tMerchantManageRe.setMerchantAddr(address.toString());
		// 服务商余额
		columnMap.clear();
		columnMap.put("PERSON_ID", merchantId);
		List<TAccount> tAccountList = iAccountServiceImpl.selectByMap(columnMap);
		long balance = tAccountList.get(0).getBalance();
		tMerchantManageRe.setBalanceResp(((float) balance / 100) == 0 ? "0" : ((float) balance / 100) + "");
		model.addAttribute("tMerchantManage", tMerchantManageRe);
		return "admin/tMerchantManage/tMerchantManageLook";
	}
}
