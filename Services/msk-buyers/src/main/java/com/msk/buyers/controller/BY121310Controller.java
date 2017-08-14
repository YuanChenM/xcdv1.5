package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.BY121304Bean;
import com.msk.buyers.bean.BY121310Bean;
import com.msk.buyers.bean.BY121310Param;
import com.msk.buyers.bean.BY121310RsBean;
import com.msk.buyers.logic.BY121304Logic;
import com.msk.buyers.logic.BY121310Logic;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerPdCla;
import com.msk.core.entity.PdMachining;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 买家买家池归属
 * Created by tao_zhifa on 2016/7/11.
 */
@Controller
@RequestMapping("BY121310")
public class BY121310Controller extends BaseController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121310Controller.class);

    @Autowired
    private BY121304Logic by121304Logic;

    @Autowired
    private BY121310Logic by121310Logic;

    @Autowired
    private CommonLogic commonLogic;

    /**
     * 买家家池归属初始化
     *
     * @param buyerId
     * @param model
     * @return
     */
    @RequestMapping(value = "init/{buyerId}", method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        logger.debug("买家家池归属初始化");
        model.addAttribute("buyerId", buyerId);

        // 买家销售产品信息
        List<ByBuyerPdCla> pdClaList = by121304Logic.buyerPdClassificationFind(buyerId);
        model.addAttribute("pdClaList", pdClaList);
        // 销售产品
        PDInfoParam pdInfoParam = new PDInfoParam();
        List<PDInfoResult> pdClaCommList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();
        List<BY121304Bean> pdClaShowList = new ArrayList<>();
        for (int i = 0; i < pdClaCommList.size(); i++) {
            BY121304Bean pdCla = new BY121304Bean();
            pdCla.setClassCode(pdClaCommList.get(i).getClassesCode());
            pdCla.setClassName(pdClaCommList.get(i).getClassesName());
            pdCla.setIsChecked("0");
            for (int j = 0; j < pdClaList.size(); j++) {
                if (pdClaCommList.get(i).getClassesCode().equals(pdClaList.get(j).getClassCode())) {
                    pdCla.setIsChecked("1");
                    break;
                }
            }
            pdClaShowList.add(pdCla);
        }
        model.addAttribute("pdClaShowList", pdClaShowList);
        List<PdMachining> pdMachiningCommList = new ArrayList<>();

        List<BY121310Param> by121310List = new ArrayList<BY121310Param>();
        for (int i = 0; i < pdClaShowList.size(); i++) {
            String machiningCode = null;
            Map<String, String> pdMacMap = new HashMap<>();
            BY121310Param by121310Param = new BY121310Param();
            by121310Param.setClassCode(pdClaShowList.get(i).getClassCode());
            by121310Param.setClassName(pdClaShowList.get(i).getClassName());
            BaseParam param = new BaseParam();
            param.setFilter("classCode", pdClaShowList.get(i).getClassCode());
            for (int k = 0; k < pdClaList.size(); k++) {
                if (pdClaShowList.get(i).getClassCode().equals(pdClaList.get(k).getClassCode())) {
                    machiningCode = pdClaList.get(k).getMachiningCode();
                    by121310Param.setIsChecked("1");
                }
            }
            //调用接口查询二级分类信息
            pdMachiningCommList = by121304Logic.findPdMachining(param);

            List<PdMachining> pdMsCommList = new ArrayList<>();
            model.addAttribute("pdMachiningCommList", pdMachiningCommList);
            for (int k = 0; k < pdMachiningCommList.size(); k++) {
                if (pdMachiningCommList.get(k).getClassesCode().equals(pdClaShowList.get(i).getClassCode())) {
                    BY121310RsBean pdMachining = new BY121310RsBean();
                    pdMachining.setMachiningCode(pdMachiningCommList.get(k).getMachiningCode());
                    pdMachining.setMachiningName(pdMachiningCommList.get(k).getMachiningName());
                    for (int s = 0; s < pdClaList.size(); s++) {
                        if ((pdClaShowList.get(i).getClassCode().equals(pdClaList.get(s).getClassCode())) && (pdMachiningCommList.get(k).getMachiningCode().equals(pdClaList.get(s).getMachiningCode()))) {
                            pdMachining.setIsMachinChecked("1");
                        }
                    }
                    pdMsCommList.add(pdMachining);
                }

            }
            by121310Param.setPdMachiningList(pdMsCommList);
            by121310Param.setMachiningCodeU(machiningCode);
            by121310List.add(by121310Param);
        }
        model.addAttribute("by121310List", by121310List);


        BY121310Param parames = new BY121310Param();
        parames.setBuyerId(buyerId);
        //查询出买家类型和营销状态
        BY121310Bean sTypeAndMStatus = new BY121310Bean();
        sTypeAndMStatus = by121310Logic.findSuperiorType(parames);
        model.addAttribute("sTypeAndMStatus", sTypeAndMStatus);

        return "buyers/BY121310";
    }

    /**
     * 买家家池归属保存
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public
    @ResponseBody
    int updateMamachining(BY121310Param param) {

        super.setCommonParam(param);
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setUpdTime(currentDate);
        param.setBuyerId(param.getBuyerId());

        by121310Logic.updateDelFlg(param);
        String machiningCodeU = "";
        String classesCode = "";
        String[] buyerPdCla = param.getBuyerPdCla();
        String[] buyerPdMac = param.getBuyerPdMac();
        if (buyerPdMac == null) {
            classesCode = buyerPdCla[0];
        } else {
            machiningCodeU = buyerPdMac[0].split("_")[0];
            classesCode = buyerPdMac[0].split("_")[1];
        }
        BY121310Param by121310Param = new BY121310Param();
        //设置共通
        super.setCommonParam(by121310Param);

        if (buyerPdMac != null) {
            by121310Param.setMachiningCodeU(machiningCodeU);
        }
        by121310Param.setClassCode(classesCode);
        by121310Param.setBuyerId(param.getBuyerId());
        String flag = "0";
        List<ByBuyerPdCla> pdClaList = by121310Logic.findDelFlg(param.getBuyerId());
        for (int j = 0; j < pdClaList.size(); j++) {
            if (pdClaList.get(j).getClassCode().equals(classesCode)) {
                if (buyerPdMac != null) {
                    by121310Param.setMachiningCodeU(machiningCodeU);
                }
                by121310Param.setClassCode(classesCode);
                by121310Param.setBuyerId(param.getBuyerId());

                by121310Param.setUpdTime(currentDate);
                by121310Logic.updateMacining(by121310Param);
                flag = "0";
                break;
            } else {
                flag = "1";
            }

        }
        if (pdClaList.size() == 0 || flag == "1") {
            PDInfoParam pdInfoParam = new PDInfoParam();
            List<PDInfoResult> pdClaCommList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();
            for (int y = 0; y < pdClaCommList.size(); y++) {
                if (classesCode.equals(pdClaCommList.get(y).getClassesCode())) {
                    by121310Param.setClassName(pdClaCommList.get(y).getClassesName());
                }
            }
            Long id = commonLogic.maxId("BY_BUYER_PD_CLA", "ID");
            by121310Param.setId(id);
            if (buyerPdMac != null) {
                by121310Param.setMachiningCodeU(machiningCodeU);
            }
            by121310Param.setClassCode(classesCode);
            by121310Param.setBuyerId(param.getBuyerId());
            String dateString = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYMMDD_HHMMSS, DateTimeUtil.getCustomerDate());
            Date date = DateTimeUtil.parseDate(dateString, DateTimeUtil.FORMAT_YYMMDD_HHMMSS);
            by121310Param.setActTime(date);
            by121310Param.setCrtTime(DateTimeUtil.getCustomerDate());
            by121310Param.setUpdTime(DateTimeUtil.getCustomerDate());
            by121310Logic.save(by121310Param);
        }

        int count = NumberConst.IntDef.INT_ZERO;

        count = this.UpdateBrBuyerPoolRelationship(by121310Param);
//        return super.forward("/BY121310/init/"+param.getBuyerId()+"");
        return count;
    }

    @RequestMapping(value = "UpdateMarketingPeriod", method = RequestMethod.POST)
    public
    @ResponseBody
    int UpdateBrBuyerPoolRelationship(BY121310Param param) {

        //查询出买家类型和营销状态
        BY121310Bean by121310Bean = new BY121310Bean();
        by121310Bean = by121310Logic.findSuperiorType(param);
        param.setBuyerCode(by121310Bean.getBuyerCode());
        param.setLgcsAreaCode(by121310Bean.getLgcsAreaCode());
        param.setCityCode(by121310Bean.getCityCode());
        param.setSuperiorType(by121310Bean.getSuperiorType());

        RsRequest<BY121310Param> request = new RsRequest<>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
//        String url = "http://localhost:8380/msk-br-api/api/br/brBuyerPoolRelationship/updateMarketingPeriod";
        String url = SystemServerManager.BuyersReportServerManager.getUpdateMarketingAndSalePeriod();
        RsResponse<Integer> response = RestClientUtil.post(url, request, new TypeReference<RsResponse<Integer>>() {
        });
        if (response != null && response.getStatus().equals("F")) {
            return NumberConst.IntDef.INT_ZERO;
        } else {
            return response.getResult();
        }


    }
}
