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
import com.gewei.commons.utils.DateUtil;
import com.gewei.fuwushang.service.IPolicyholderService;
import com.gewei.model.Policyholder;

/**
 * <p>
 * 投保人表 前端控制器
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-01-26
 */
@Controller
@RequestMapping("/mgr/policyholder")
public class PolicyholderController extends BaseController {

    @Autowired private IPolicyholderService policyholderService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/policyholder/policyholderList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Policyholder policyholder, Integer page, Integer rows, String sort,String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        EntityWrapper<Policyholder> ew = new EntityWrapper<Policyholder>(policyholder);
        Page<Policyholder> pages = getPage(pageInfo);
        pages = policyholderService.selectPage(pages, ew);
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
        return "admin/policyholder/policyholderAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Policyholder policyholder) {
        policyholder.setCreateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
        boolean b = policyholderService.insert(policyholder);
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
    public Object delete(String id) {
        Policyholder policyholder = new Policyholder();
        policyholder.setPid(id);
        policyholder.setStatus("02");
        boolean b = policyholderService.updateById(policyholder);
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
        Policyholder policyholder = policyholderService.selectById(id);
        model.addAttribute("policyholder", policyholder);
        return "admin/policyholder/policyholderEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Policyholder policyholder) {
        boolean b = policyholderService.updateById(policyholder);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
