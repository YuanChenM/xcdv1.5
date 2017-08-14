package com.msk.batch.controller;

import com.msk.batch.logic.BatchDefinitionLogic;
import com.msk.core.entity.BatchDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by jackjiang on 16/7/1.
 */
@Controller
public class BatchDefinitionController {
    @Autowired
    private BatchDefinitionLogic batchDefinitionLogic;

    @RequestMapping(value = "batch/def/list")
    public String batchDefinitionList(Model model){
        List<BatchDefinition> batchDefinitionList = this.batchDefinitionLogic.findBatchDefinition();
        model.addAttribute("batchDefinitionList",batchDefinitionList);
        return "batchDefList";
    }



}
