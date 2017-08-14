package com.msk.org.controller;

import com.msk.org.bean.param.PageParam;
import com.msk.org.bean.result.BasePageResult;
import com.msk.org.entity.OrgDepartment;
import com.msk.org.entity.OrgEmploy;
import com.msk.org.logic.DepartmentLogic;
import com.msk.org.logic.OrgEmployLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("employ")
public class EmployController {
    @Autowired
    private OrgEmployLogic employLogic;
    @Autowired
    private DepartmentLogic departmentLogic;
    @RequestMapping(value = "list/init", method = RequestMethod.GET)
    public String init() {
        return "employ/list";
    }
    @RequestMapping(value = "load/data", method = RequestMethod.POST)
    public @ResponseBody BasePageResult loadData(PageParam pageParam) {
        return employLogic.findPage(pageParam);
    }
    @RequestMapping(value = "view/{employCode}", method = RequestMethod.GET)
    public String view(@PathVariable("employCode") String employCode, Model model){
        List<OrgDepartment> departmentList = this.departmentLogic.findEmployDepartment(employCode);
        model.addAttribute("employDepartmentList",departmentList);
        model.addAttribute("employCode",employCode);
        return "employ/view";
    }
    @RequestMapping(value = "load/data/{employCode}", method = RequestMethod.GET)
    public @ResponseBody OrgEmploy loadData(@PathVariable("employCode") String employCode){
        return this.employLogic.findEmployByEmployCode(employCode);
    }


}
