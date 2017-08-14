package com.msk.seller.controller;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.seller.bean.SlEpAgentAuthBean;
import com.msk.seller.logic.SL241101010Logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by writer on 2016/1/27.
 */
@Controller
@RequestMapping("SL241101010")
public class SL241101010Controller extends BaseUploadController{

    @Autowired
    private SL241101010Logic SL241101010Logic;

    /**
     * 更新数据
     */
    @RequestMapping("update")
    public @ResponseBody int update(SlEpAgentAuthBean slEpAgentAuthBean) {
        int count=0;
        slEpAgentAuthBean.setUpdId(getLoginUser().getEmplId());
        slEpAgentAuthBean.setUpdTime(DateTimeUtil.getCustomerDate());
        int num=SL241101010Logic.saveData(slEpAgentAuthBean);
        if(num>0){
            count=1;
        }
        return count;
    }
    /**
     * 删除数据
     */
    @RequestMapping("delete")
    public @ResponseBody int delete(String slCode,String epId,String markFlg) {
        int count=0;
        int num=SL241101010Logic.deleteData(slCode, epId, markFlg);
        if(num>0){
            count=1;
        }
        return count;
    }
}
