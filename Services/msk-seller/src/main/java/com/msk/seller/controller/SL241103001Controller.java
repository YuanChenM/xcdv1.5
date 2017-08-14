package com.msk.seller.controller;

import com.alibaba.fastjson.JSONObject;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.LoginUser;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.ProvinceBean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL241103001Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * 增加卖家账户
 * Created by fjm on 2016/1/26.
 */
@Controller
@RequestMapping("SL241103001")
public class SL241103001Controller extends BaseUploadController {
    @Autowired
    private SL241103001Logic sl241103001Logic;

    @Autowired
    private CommonLogic commonLogic;

    @RequestMapping(value = "insert",
        method = RequestMethod.POST)
    public void insert(MultipartFile epthrfile, MultipartFile accountfile, MultipartFile licfile, MultipartFile taxfile,
        MultipartFile orgfile, MultipartFile balfile, SlEnterprise slEnterprise, SlSeller slSeller, SlAccount slAccount,
        String slConFlg, String[] slSecondaryClass, String provinceCode, String cityCode, String districtCode,
        String licType, String licTermUnliimited, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ParseException {

           //插入卖家账户信息
            String message = this.sl241103001Logic.insertSellerInfo(epthrfile,accountfile,licfile,taxfile,
                    orgfile, balfile,slEnterprise,slSeller,slAccount,
                    slConFlg,slSecondaryClass,provinceCode,cityCode,districtCode,
                licType,licTermUnliimited, request, response, getLoginUser().getEmplId());

            super.callBack("sl241103001CallBack", message, response);
        }
    }


