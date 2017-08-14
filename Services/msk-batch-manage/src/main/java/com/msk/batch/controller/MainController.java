package com.msk.batch.controller;

import com.msk.batch.bean.BasePageParam;
import com.msk.batch.bean.PageResult;
import com.msk.batch.logic.BatchRecordLogic;
import com.msk.core.entity.BatchRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jackjiang on 16/7/1.
 */
@Controller
public class MainController {
    @Autowired
    private BatchRecordLogic batchRecordLogic;
    /**
     * 进入Main页面
     * @return index页面
     */
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(BasePageParam param,Model model){
        PageResult<BatchRecord> pageResult = this.batchRecordLogic.findExecutingRecord(param);
        model.addAttribute("pageResult",pageResult);
        return "index";
    }
}
