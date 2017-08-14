package com.msk.buyers.rest;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.buyers.bean.IBY121102Bean;
import com.msk.buyers.logic.IBY121102Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdMachining;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * Created by tao_zhifa on 2016/7/15.
 */
@RestController
public class IBY121102RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121101RsController.class);

    @Autowired
    private IBY121102Logic iby121202Logic;
    /**
     *获取产品类别
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/common/pdClass",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<PdClasses[]> pdClass(@RequestBody RsRequest<BaseParam> param){
        RsResponse<PdClasses[]> rs = new RsResponse<>();
        RsResponse<PdClasses[]>  pdClasses = iby121202Logic.findPdClasses(param.getParam());
        if(pdClasses.getResult().length != NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("产品类别获取成功");
            rs.setResult(pdClasses.getResult());
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("产品类别没有记录");
        }
        return rs;
    }

    /**
     * 产品二级分类获取
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/common/pdMachining",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<PdMachining[]> pdMachining(@RequestBody RsRequest<BaseParam> param){
        RsResponse<PdMachining[]> rs = new RsResponse<>();
        RsResponse<PdMachining[]> pdMachining = iby121202Logic.findPdMachining(param.getParam());
        if(pdMachining.getResult().length != NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("产品二级分类获取成功");
            rs.setResult(pdMachining.getResult());
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("产品二级分类没有记录");
        }
        return rs;
    }

    @RequestMapping(value = "/by/common/pdBreed",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<PdBreed[]> pdBreed(@RequestBody RsRequest<BaseParam> param){
        RsResponse<PdBreed[]> rs = new RsResponse<>();
        RsResponse<PdBreed[]> pdBreeds = iby121202Logic.findPdBreed(param.getParam());
        if(pdBreeds.getResult().length != NumberConst.IntDef.INT_ZERO){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("产品二级分类获取成功");
            rs.setResult(pdBreeds.getResult());
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("产品二级分类没有记录");
        }
        return rs;
    }

    /**
     * 查询买家类型
     * @param params
     * @return
     */
    @RequestMapping(value = "/by/common/SuperiorType",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<ByBuyerBasicInfo> findSuperiorTypeValue(@RequestBody RsRequest<ByBuyerBasicInfo> params){
        RsResponse<ByBuyerBasicInfo> rs = new RsResponse<>();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("buyerId",params.getParam().getBuyerId());
        ByBuyerBasicInfo byBuyerBasicInfo = iby121202Logic.findSuperiorType(baseParam);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("产品二级分类获取成功");
            rs.setResult(byBuyerBasicInfo);
        return rs;
    }

    /**
     * 查询营销状态
     * @param params
     * @return
     */
    @RequestMapping(value = "/by/common/findmMrketingsStatusValue",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<ByBuyerBasicInfo> findmMrketingsStatusValue(@RequestBody RsRequest<ByBuyerBasicInfo> params){
        RsResponse<ByBuyerBasicInfo> rs = new RsResponse<>();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("buyerId",params.getParam().getBuyerId());
        ByBuyerBasicInfo byBuyerBasicInfo = iby121202Logic.findmMrketingsStatus(baseParam);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("产品二级分类获取成功");
        rs.setResult(byBuyerBasicInfo);
        return rs;
    }


    /**
     * 获取营销工具
     * @param basicParam
     * @return
     */
    @RequestMapping(value = "/by/getMarketingToolValues",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBY121102Bean>> getMarketingToolValues(@RequestBody RsRequest<ByBuyerBasicInfo> basicParam) {
        RsResponse<List<IBY121102Bean>> rs = new RsResponse<>();
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("buyerId",basicParam.getParam().getBuyerId());
        Map<String, String> paymentMethod = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingTool.TYPE);
        RsRequest<ByBuyerBasicInfo> request = new RsRequest<ByBuyerBasicInfo>();
        ByBuyerBasicInfo param = new ByBuyerBasicInfo();
        param.setBuyerId(basicParam.getParam().getBuyerId());
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
//      String url = "http://localhost:8080/msk-by/api/by/findToolToBuyerId";
        String url = SystemServerManager.BuyersServerManage.getFindToolToBuyerId();
        RsResponse<ByBuyerBasicInfo> basicBean = RestClientUtil.post(url, request, new TypeReference<RsResponse<ByBuyerBasicInfo>>() {
        });
        ByBuyerBasicInfo byBuyerBasicInfo = basicBean.getResult();
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(paymentMethod);
        List<String> payTypeList = new ArrayList();
        if (byBuyerBasicInfo != null) {
            String markType[] = byBuyerBasicInfo.getMarketingTools().split(",");
            payTypeList = Arrays.asList(markType);
        }

        List<IBY121102Bean> paymentList = new ArrayList<>();

        for (String key : treeMap.keySet()) {
            IBY121102Bean recBean = new IBY121102Bean();
            if (byBuyerBasicInfo != null) {
                recBean.setTelMarketingStartTime(byBuyerBasicInfo.getTelMarketingStartTime());
                recBean.setTelMarketingEndTime(byBuyerBasicInfo.getTelMarketingEndTime());
            }
            recBean.setMarketingTools(key);
            recBean.setSetMarketingToolsName(treeMap.get(key));
            for (int j = 0; j < payTypeList.size(); j++) {
                if (key.equals(payTypeList.get(j))) {
                    recBean.setIsChecked("1");
                    break;
                }
            }
            paymentList.add(recBean);
        }
        rs.setResult(paymentList);
        rs.setMessage(" master数据取得成功");
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;
    }

}
