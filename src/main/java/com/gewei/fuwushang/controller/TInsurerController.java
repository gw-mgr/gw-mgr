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
import com.gewei.fuwushang.service.ITInsurerService;
import com.gewei.model.TInsurer;

/**
 * <p>
 * 保险公司列表 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-16
 */
@Controller
@RequestMapping("/mgr/tInsurer")
public class TInsurerController extends BaseController {

    @Autowired private ITInsurerService tInsurerService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/tInsurer/tInsurerList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(TInsurer tInsurer, Integer page, Integer rows, String sort,String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        EntityWrapper<TInsurer> ew = new EntityWrapper<TInsurer>(tInsurer);
        Page<TInsurer> pages = getPage(pageInfo);
        pages = tInsurerService.selectPage(pages, ew);
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
        return "admin/tInsurer/tInsurerAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid TInsurer tInsurer) {
        boolean b = tInsurerService.insert(tInsurer);
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
        boolean b = tInsurerService.deleteById(id);
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
        TInsurer tInsurer = tInsurerService.selectById(id);
        model.addAttribute("tInsurer", tInsurer);
        return "admin/tInsurer/tInsurerEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid TInsurer tInsurer) {
        boolean b = tInsurerService.updateById(tInsurer);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    @PostMapping(value = "/list")
    @ResponseBody
    public Object getInsurerList(){
    	 EntityWrapper<TInsurer> wrapper = new EntityWrapper<TInsurer>();
        return tInsurerService.selectList(wrapper);
    }   
}
