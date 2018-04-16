package com.gewei.fuwushang.controller;


import javax.validation.Valid;

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
import com.gewei.fuwushang.service.IParameterService;
import com.gewei.model.Parameter;
import com.gewei.model.vo.ParameterVo;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-30
 */
@Controller
@RequestMapping("/mgr/parameter")
public class ParameterController extends BaseController {

    @Autowired private IParameterService parameterService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/parameter/parameterList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Parameter parameter, Integer page, Integer rows, String sort,String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        EntityWrapper<Parameter> ew = new EntityWrapper<Parameter>(parameter);
        Page<Parameter> pages = getPage(pageInfo);
        pages = parameterService.selectPage(pages, ew);
        pageInfo.setRows(pages.getRecords());
        pageInfo.setTotal(pages.getTotal());
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/parameter/parameterAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid ParameterVo parameterVo) {
    	System.out.println(parameterVo.toString());
//        boolean b = parameterService.insert(parameter);
//        if (b) {
//            return renderSuccess("添加成功！");
//        } else {
//            return renderError("添加失败！");
//        }
		return parameterVo;
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        Parameter parameter = new Parameter();
        parameter.setId(id);
        parameter.setStatus("01");
        boolean b = parameterService.updateById(parameter);
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
    public String editPage(Model model, Long id) {
        Parameter parameter = parameterService.selectById(id);
        model.addAttribute("parameter", parameter);
        return "admin/parameter/parameterEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Parameter parameter) {
        boolean b = parameterService.updateById(parameter);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    @PostMapping(value = "/list")
    @ResponseBody
    public Object getParameterList(String type){
    	 EntityWrapper<Parameter> wrapper = new EntityWrapper<Parameter>();
    	 wrapper.addFilter("type = {0}", type);
        return parameterService.selectList(wrapper);
    }
}
