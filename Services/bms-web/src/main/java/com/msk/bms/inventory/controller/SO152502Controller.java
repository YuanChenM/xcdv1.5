package com.msk.bms.inventory.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bms.inventory.bean.SO152502Bean;
import com.msk.bms.inventory.bean.SO152502ResultBean;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * SO15150801_选择产品画面
 * Created by wang_jianzhou on 2016/8/1.
 */
@Controller
@RequestMapping("SO152502")
public class SO152502Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO152502Controller.class);
    int recordsTotal = 0;// 定义数据列表总数常量

    @RequestMapping(value = "init",method = RequestMethod.POST)
    public String init(BasePageParam param, Model model) {
      //  TreeMap<String,String> treeMap= this.so151201Logic.getsalePlatform();  // 查询销售平台
      //  List salePlatformList = new ArrayList(treeMap.entrySet());
     //   model.addAttribute("salePlatformList", salePlatformList);
        model.addAttribute("param", param);
        logger.debug("初始化页面");
        return "/inventory/SO152502";
    }

    /**
     * @param basePageParam basePageParam
     * @return PageResult<SO151201Bean>
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SO152502Bean> search(BasePageParam basePageParam) {
        logger.info("查询数据库");
        DbUtils.buildLikeCondition(basePageParam, "lgcsCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "warehouseCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "salePlatform", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slCodeDis", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdCode", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "lgcsName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "warehouseName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "slName", DbUtils.LikeMode.FRONT);
        DbUtils.buildLikeCondition(basePageParam, "pdName", DbUtils.LikeMode.FRONT);

        RestRequest rsRequest = new RestRequest();
        rsRequest.setAuth("MSK00001");
        rsRequest.setLoginId("msk01");
        rsRequest.setSiteCode("1");
        rsRequest.setParam(basePageParam);
        String url =  SystemServerManager.SoInventoryServerManager.getGetSellerInventoryList();
        RsResponse<SO152502ResultBean> rsResponse = RestClientUtil.post(url, rsRequest, new TypeReference<RsResponse<SO152502ResultBean>>() {
        });

        PageResult<SO152502Bean> result = new PageResult<SO152502Bean>();
        List<SO152502Bean> so152502BeanList = new ArrayList<SO152502Bean>();
        if (rsResponse.getResult()!=null){
            so152502BeanList =  rsResponse.getResult().getSo152502BeanList();
        }
        if(so152502BeanList.size()>10){
            result.setData(so152502BeanList.subList(0,10));
        }else{
            result.setData(so152502BeanList);
        }
        if(basePageParam.getStartPos()==0){
            this.recordsTotal=so152502BeanList.size();
        }
        result.setRecordsTotal(this.recordsTotal);
        return result;
    }
}
