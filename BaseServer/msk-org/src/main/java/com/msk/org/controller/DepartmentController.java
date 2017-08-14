package com.msk.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msk.org.bean.param.PageParam;
import com.msk.org.bean.result.BasePageResult;
import com.msk.org.logic.DepartmentLogic;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private DepartmentLogic departmentLogic;
    @RequestMapping(value = "list/init", method = RequestMethod.GET)
    public String init() {
        return "department/list";
    }
    @RequestMapping(value = "load/data", method = RequestMethod.POST)
    public @ResponseBody BasePageResult loadData(PageParam pageParam) {
        return departmentLogic.findPage(pageParam);
    }

}
