package com.msk.bms.inventory.controller;

import java.util.ArrayList;
import java.util.List;

import com.msk.bms.inventory.bean.SO152501ResultBean;
import com.msk.comm.bean.RestResponse;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.msk.common.bean.RestRequest;
import com.msk.common.utils.RestClientUtil;
import com.alibaba.fastjson.TypeReference;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.inventory.bean.SO152501Bean;
import com.msk.common.base.BaseController;

/**
 * SO15150801_选择产品画面
 * Created by wang_jianzhou on 2016/8/1.
 */
@Controller
@RequestMapping("SO152501")
public class SO152501Controller extends BaseController {

    int recordsTotal=0;//定义数据列表总数常量

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO152501Controller.class);

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(BasePageParam param, Model model) {
        // TreeMap<String,String> treeMap= this.so151201Logic.getsalePlatform(); // 查询销售平台
        // List salePlatformList = new ArrayList(treeMap.entrySet());
        // model.addAttribute("salePlatformList", salePlatformList);
        // model.addAttribute("param", param);
        logger.debug("初始化页面");
        return "/inventory/SO152501";
    }

    /**
     * @param basePageParam basePageParam
     * @return PageResult<SO151201Bean>
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SO152501Bean> search(BasePageParam basePageParam) {
        logger.info("查询数据库");
        DbUtils.buildLikeCondition(basePageParam, "lgcsCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "warehouseCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "lgcsName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "warehouseName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "supplierCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "supplyPlayFrom", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "supplierName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdName", DbUtils.LikeMode.FRONT);


        RestRequest rsRequest = new RestRequest();
        rsRequest.setAuth("MSK00001");
        rsRequest.setLoginId("msk01");
        rsRequest.setSiteCode("1");
        rsRequest.setParam(basePageParam);
        String url = SystemServerManager.SoInventoryServerManager.getGetDistributionList();
        RsResponse<SO152501ResultBean> rsResponse = RestClientUtil.post(url, rsRequest, new TypeReference<RsResponse<SO152501ResultBean>>() {
        });
        PageResult<SO152501Bean> result = new PageResult<SO152501Bean>();
        List<SO152501Bean> so152501BeanList =new ArrayList<SO152501Bean>();
        if (rsResponse.getResult() !=null){
            so152501BeanList = rsResponse.getResult().getSo152501BeanList();
        }
        if(so152501BeanList.size()>10){
            result.setData(so152501BeanList.subList(0,10));
        }else{
            result.setData(so152501BeanList);
        }
        if(basePageParam.getStartPos()==0){
            this.recordsTotal=so152501BeanList.size();
        }
        result.setRecordsTotal(this.recordsTotal);
        return result;
    }

}
