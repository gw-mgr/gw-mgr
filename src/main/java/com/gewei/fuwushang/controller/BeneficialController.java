package com.gewei.fuwushang.controller;

import java.util.Date;

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
import com.gewei.fuwushang.service.IBeneficialService;
import com.gewei.model.Beneficial;

/**
 * <p>
 * 人寿保险-受益人表 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-03
 */
@Controller
@RequestMapping("/beneficial")
public class BeneficialController extends BaseController {

    @Autowired private IBeneficialService beneficialService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/beneficial/beneficialList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Beneficial beneficial, Integer page, Integer rows, String sort,String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        EntityWrapper<Beneficial> ew = new EntityWrapper<Beneficial>(beneficial);
        Page<Beneficial> pages = getPage(pageInfo);
        pages = beneficialService.selectPage(pages, ew);
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
        return "admin/beneficial/beneficialAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Beneficial beneficial) {
        boolean b = beneficialService.insert(beneficial);
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
        Beneficial beneficial = new Beneficial();
        boolean b = beneficialService.updateById(beneficial);
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
        Beneficial beneficial = beneficialService.selectById(id);
        model.addAttribute("beneficial", beneficial);
        return "admin/beneficial/beneficialEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Beneficial beneficial) {
        boolean b = beneficialService.updateById(beneficial);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
