package com.gewei.wx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.utils.AppUtil;
import com.gewei.commons.utils.DateUtil;
import com.gewei.commons.utils.UUIDUtil;
import com.gewei.mapper.CommonMapper;
import com.gewei.model.AreaInfo;
import com.gewei.model.SmsCode;
import com.gewei.model.TChinaArea;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.wx.service.ICommonService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

@Service
public class CommonServiceImpl extends ServiceImpl<CommonMapper, TChinaArea> implements ICommonService {
	@Autowired
	private CommonMapper commonMapper;

	@Override
	public List<TChinaArea> findAreaNodeByParentId(String parentId) {
		return commonMapper.findAreaNodeByParentId(parentId);
	}

	@Override
	public String checkCustomerIsExsit(TCustomerBasicinfo tCustomerBasicinfoR) {
		try {
			String userName = tCustomerBasicinfoR.getUserName();
			String telephone = tCustomerBasicinfoR.getTelephone();
			String carNum = tCustomerBasicinfoR.getCarNum();
			HashMap<String, String> condition = new HashMap<String, String>(3);
			if (userName != null) {
				condition.put("USER_NAME", userName);
			}
			if (telephone != null) {
				condition.put("TELEPHONE", telephone);
			}
			if (carNum != null) {
				condition.put("CAR_NUM", carNum);
			}
			TCustomerBasicinfo tCustomerBasicinfo = commonMapper.queryCustomer(condition);
			if (tCustomerBasicinfo == null) {
				// 客户不存咋，则添加客户
				tCustomerBasicinfo = new TCustomerBasicinfo();
				String currTime = DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString();
				String uuid = UUIDUtil.getUUID32().substring(0, 16);
				String userId = currTime + uuid;
				tCustomerBasicinfo.setUserId(userId);
				tCustomerBasicinfo.setUserName(userName == null ? "" : userName);
				tCustomerBasicinfo.setTelephone(telephone == null ? "" : telephone);
				tCustomerBasicinfo.setCarNum(carNum == null ? "" : carNum);
				tCustomerBasicinfo.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
				tCustomerBasicinfo.setUpdateTime(DateUtil.get_Long$yyyyMMddHHmmss(new Date()).toString());
				commonMapper.insertCustomer(tCustomerBasicinfo);
			} else {
				// 客户已存在，更新用户信息
			}
			return tCustomerBasicinfo.getUserId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void sendSMSCode(String phoneNumber) {
		try {
			// 生成验证码
			String code = UUIDUtil.getRandomCode(4).toString();
			// 查询手机号,保存验证码记录
			SmsCode smsCode = commonMapper.getSMSCodeByTelephone(phoneNumber);
			long currenTime = DateUtil.get_Long$currentTimeMillis();
			if (smsCode == null) {
				smsCode = new SmsCode();
			}
			smsCode.setAddr(phoneNumber);
			smsCode.setSmsCode(code);
			smsCode.setFlag("01");
			smsCode.setCreateTime(DateUtil.get_Long$yyyyMMddHHmmss(currenTime).toString());
			smsCode.setDeadTime(DateUtil.get_Long$yyyyMMddHHmmss(currenTime + 60000).toString());
			commonMapper.saveOrUpdateSmsCode(smsCode);
			// TODO:发送短信
			try {
				String str = DateUtil.get_Long$yyyyMMddHHmmss(currenTime).toString();
				String time = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10, 12) + ":" + str.substring(12, 14);
				SmsSingleSender ssender = new SmsSingleSender(Integer.parseInt(AppUtil.getPropertie("sms_appId")), AppUtil.getPropertie("sms_appKey"));
				SmsSingleSenderResult result = ssender.send(0, "86", phoneNumber, "尊敬的用户，您的短信验证码为" + code + "（请勿泄露），您于" + time + "在格威平台进行操作，感谢您的使用！", "", "");
				System.out.print(result);
			} catch (HTTPException e) {
				// HTTP响应码错误
				System.out.println("HTTP响应码错误");
			} catch (JSONException e) {
				// json解析错误
				System.out.println("json解析错误");
			} catch (IOException e) {
				// 网络IO错误
				System.out.println("网络IO错误");
			}
			// TODO:发送短信
			System.out.println("发送短信验证码：telephone=" + phoneNumber + ",code=" + code);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<AreaInfo> findAreaInfo() {
		List<TChinaArea> list = commonMapper.findAreaNodeByParentId("0");
		List<AreaInfo> father = new ArrayList<AreaInfo>();
		// 省
		for (TChinaArea tChinaArea : list) {
			Integer id = tChinaArea.getId();
			if (id != 0) {
				AreaInfo areaInfo = new AreaInfo();
				areaInfo.setText(tChinaArea.getName());
				areaInfo.setValue(tChinaArea.getId().toString());
				// 市区
				List<TChinaArea> list2 = commonMapper.findAreaNodeByParentId(tChinaArea.getId().toString());
				if (list2 != null && list2.size() > 0) {
					List<AreaInfo> child = new ArrayList<AreaInfo>();
					for (TChinaArea tChinaArea2 : list2) {
						AreaInfo areaInfo2 = new AreaInfo();
						areaInfo2.setValue(tChinaArea2.getId().toString());
						areaInfo2.setText(tChinaArea2.getName());
						child.add(areaInfo2);
						// 县
						List<TChinaArea> list3 = commonMapper.findAreaNodeByParentId(tChinaArea2.getId().toString());
						if (list3 != null && list3.size() > 0) {
							List<AreaInfo> child2 = new ArrayList<AreaInfo>();
							for (TChinaArea tChinaArea3 : list3) {
								AreaInfo areaInfo3 = new AreaInfo();
								areaInfo3.setValue(tChinaArea3.getId().toString());
								areaInfo3.setText(tChinaArea3.getName());
								child2.add(areaInfo3);
							}
							areaInfo2.setChildren(child2);
						}
					}
					areaInfo.setChildren(child);
				}
				father.add(areaInfo);
			}
		}
		return father;
	}

	@Override
	public Map<String, Object> getLastBankInfoByPersonId(String personId) {
		return commonMapper.getLastBankInfoByPersonId(personId);
	}
}
