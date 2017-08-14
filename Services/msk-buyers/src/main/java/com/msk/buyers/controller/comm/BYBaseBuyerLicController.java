package com.msk.buyers.controller.comm;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.BuyerLicenceBean;
import com.msk.buyers.bean.IBY121205RsParam;
import com.msk.buyers.bean.IBY121206RsParam;
import com.msk.buyers.controller.BY121106Controller;
import com.msk.buyers.logic.BY121304Logic;
import com.msk.common.base.BaseUploadController;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.core.entity.CommConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 买家证照信息画面
 *
 * @author yuan_chen
 */
@Controller
@RequestMapping("by/baseBuyerLicInfo")
public class BYBaseBuyerLicController extends BaseUploadController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BYBaseBuyerLicController.class);
    @Autowired
    private BY121304Logic by121304Logic;
    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId,Model model) {
        logger.debug("买家证照信息画面");
        String fileDownLoadServerUrl = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        model.addAttribute("buyerId",buyerId);
        //买家证照信息
        IBY121205RsParam licenceInfo = by121304Logic.buyerLicenceFind(buyerId);
        model.addAttribute("licenceInfo",licenceInfo);
        //买家证照图片信息
        IBY121206RsParam licencePic = by121304Logic.buyerLicencePicFind(buyerId);
        if(null != licencePic){
            if(!StringUtil.isNullOrEmpty(licencePic.getPicLicensePath())){
                licencePic.setPicLicensePath(fileDownLoadServerUrl + licencePic.getPicLicensePath());
            }
            if(!StringUtil.isNullOrEmpty(licencePic.getPicOrgStructurePath())){
                licencePic.setPicOrgStructurePath(fileDownLoadServerUrl + licencePic.getPicOrgStructurePath());
            }
            if(!StringUtil.isNullOrEmpty(licencePic.getPicFoodCirculationPath())){
                licencePic.setPicFoodCirculationPath(fileDownLoadServerUrl + licencePic.getPicFoodCirculationPath());
            }
            if(!StringUtil.isNullOrEmpty(licencePic.getPicTaxRegistrationPath())){
                licencePic.setPicTaxRegistrationPath(fileDownLoadServerUrl + licencePic.getPicTaxRegistrationPath());
            }
            if(!StringUtil.isNullOrEmpty(licencePic.getPicCertPath())){
                licencePic.setPicCertPath(fileDownLoadServerUrl + licencePic.getPicCertPath());
            }
        }
        model.addAttribute("licencePic",licencePic);
        //买家营业执照类型
        Map<String,String> licTypeMap = CodeMasterManager.findCodeMasterMap("LicType");
        List <CommConstant> licTypeList = new ArrayList<>();
        if(null != licTypeMap){
            licTypeList = new ArrayList(licTypeMap.entrySet());
        }
        model.addAttribute("licTypeList",licTypeList);
        //买家法人代表证件类型
        Map<String,String> legalLicTypeMap = CodeMasterManager.findCodeMasterMap("LegalLicType");
        List <CommConstant> legalLicTypeList = new ArrayList<>();
        if(null != legalLicTypeMap){
            legalLicTypeList = new ArrayList(legalLicTypeMap.entrySet());
        }
        model.addAttribute("legalLicTypeList",legalLicTypeList);

        return "buyers/comm/BASE_BUYER_LIC_INFO";
    }


    @RequestMapping(value = "saveFileServerId",
            method = RequestMethod.POST)
    public String basicLicInfoModify(IBY121206RsParam picInfoParam){
        logger.debug("买家证照图片更新");
        super.setCommonParam(picInfoParam);
        Date currentDate = DateTimeUtil.getCustomerDate();
        picInfoParam.setActTime(currentDate);
        picInfoParam.setCrtTime(currentDate);
        picInfoParam.setUpdTime(currentDate);

        //买家证照图片更新
        int updatePicCount = by121304Logic.buyerLicencePicModify(picInfoParam);

        return super.forward("/BY121304/init/"+picInfoParam.getBuyerId()+"");
    }

    /**
     * 买家证照信息更新
     * @param basicLicInfo
     */
    @RequestMapping(value = "update",
            method = RequestMethod.POST)
    public @ResponseBody String basicLicInfoModify(BuyerLicenceBean basicLicInfo){
        logger.debug("买家基本信息更新");
        super.setCommonParam(basicLicInfo);
        Date currentDate = DateTimeUtil.getCustomerDate();
        basicLicInfo.setActTime(currentDate);
        basicLicInfo.setCrtTime(currentDate);
        basicLicInfo.setUpdTime(currentDate);
        //买家证照信息更新
        int updateCount = by121304Logic.buyerLicenceModify(basicLicInfo);
        if(updateCount == NumberConst.IntDef.INT_ONE){
            return SystemConst.RsStatus.SUCCESS;
        }else{
            return SystemConst.RsStatus.FAIL;
        }
    }
}
