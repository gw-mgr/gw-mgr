package com.gewei.fuwushang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gewei.commons.base.BaseController;
import com.gewei.fuwushang.service.TChinaAreaService;
import com.gewei.model.TChinaArea;
import com.google.gson.Gson;

/**
 * <p>
 * 中国区域表 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-09
 */
@Controller
@RequestMapping("/mgr/area")
public class TChinaAreaController extends BaseController {

    @Autowired private TChinaAreaService tChinaAreaService;
    
    @PostMapping(value = "/list")
    @ResponseBody
    public Object getAreaList(){
    	 EntityWrapper<TChinaArea> wrapper = new EntityWrapper<TChinaArea>();
        return tChinaAreaService.selectList(wrapper);
    }
    @GetMapping(value = "/json")
    @ResponseBody
    public Object getAreaJson(){
    	Gson gson = new Gson();
        return gson.toJson(tChinaAreaService.selectJson()).replace("[", "").replace("]", "").replace("\"id\":", "\"").replace(",\"name\":", "\":").replace("},{", ",");
    }
    public static void main(String[] args) {
		String str = "[{\"id\":110101,\"name\":\"东城区\"},{\"id\":110102,\"name\":\"西城区\"}]";
		System.out.println(str.replace("[", "").replace("]", "").replace("\"id\":", "\"").replace(",\"name\":", "\":").replace("},{", ","));
	}
}
