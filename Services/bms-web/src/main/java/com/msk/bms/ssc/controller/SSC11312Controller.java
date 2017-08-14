package com.msk.bms.ssc.controller;

import com.hoperun.core.consts.NumberConst;
import com.msk.bms.ssc.controller.common.ISSCDeliveryPreInfoRestUtil;
import com.msk.bms.ssc.controller.common.ISSCDifferRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.StringUtil;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 业务控制器：管理生产商入库差异单详情页面的业务
 * Created by xia_xiaojie on 2016/7/5.
 */
@Controller
@RequestMapping("/SSC11312")
public class SSC11312Controller extends BaseController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SSC11312Controller.class);

    /**
     * 图片文件后缀
     */
    private static String[] IMG_SUFFIXS = {"png", "jpg", "jpeg", "bmp", "gif", "PNG", "JPG", "JPEG", "BMP", "GIF"};

    /**
     * 根据差异单ID，初始化差异单详情页面
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String show(SSC11311Bean ssc11311Bean, Model model) {
        SSC11311Param queryParam = new SSC11311Param();
        queryParam.setDifferId(ssc11311Bean.getDifferId());

        RsResponse<SSC11311Result> respResult = ISSCDifferRestUtil.queryDifferBasics(queryParam);
        List<SSC11311Bean> sscDifferBasics = respResult.getResult().getSscDifferBasics();

        if (!CollectionUtils.isEmpty(sscDifferBasics)) {
            ssc11311Bean = sscDifferBasics.get(NumberConst.IntDef.INT_ZERO);
        }
        return this.init(ssc11311Bean, model);
    }

    /**
     * 生产商入库差异单详情详情页面初始化
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public String init(SSC11311Bean ssc11311Bean, Model model) {
        SSC11312Param queryParam = new SSC11312Param();
        queryParam.setDifferId(ssc11311Bean.getDifferId());
        RsResponse<SSC11312Result> respResult = ISSCDifferRestUtil.queryDifferDetails(queryParam);
        List<SSC11312Bean> sscDifferDetails = respResult.getResult().getSscDifferDetails();

        //获取随车资料列表
        if(!StringUtil.isNullOrEmpty(ssc11311Bean.getDeliveryPreIntoId())){
            SSC11316Param reqQueryParam = new SSC11316Param();
            reqQueryParam.setPaging(false);
            reqQueryParam.setDeliveryPreIntoIds(Arrays.asList(ssc11311Bean.getDeliveryPreIntoId().split(",")));
            RsResponse<SSC11316Result> preResult = ISSCDeliveryPreInfoRestUtil.findPreIntoBasicList(reqQueryParam);
            List<SSC11316Bean> deliveryPreIntoList = new ArrayList<>();

            if(preResult.getResult() != null){
                deliveryPreIntoList = preResult.getResult().getSscDeliveryPreIntos();

                //文件下载-特殊字符处理
                try {
                    for (SSC11316Bean bean : deliveryPreIntoList) {
                        if(!StringUtil.isNullOrEmpty(bean.getBusinessFileName())){
                            bean.setBusinessFileNameStr(URLEncoder.encode(bean.getBusinessFileName(), "UTF-8"));
                            //判断随车资料是否为图片
                            if(isPic(bean.getBusinessFileName())){
                                bean.setBusinessFileFlag(true);
                            }
                        }
                        if(!StringUtil.isNullOrEmpty(bean.getQuarantineFileName())){
                            bean.setQuarantineFileNameStr(URLEncoder.encode(bean.getQuarantineFileName(), "UTF-8"));
                            if(isPic(bean.getQuarantineFileName())){
                                bean.setQuarantineFileFlag(true);
                            }
                        }
                        if(!StringUtil.isNullOrEmpty(bean.getInventoryFileName())){
                            bean.setInventoryFileNameStr(URLEncoder.encode(bean.getInventoryFileName(), "UTF-8"));
                            if(isPic(bean.getInventoryFileName())){
                                bean.setInventoryFileFlag(true);
                            }
                        }
                        if(!StringUtil.isNullOrEmpty(bean.getReportFileName())){
                            bean.setReportFileNameStr(URLEncoder.encode(bean.getReportFileName(), "UTF-8"));
                            if(isPic(bean.getReportFileName())){
                                bean.setReportFileFlag(true);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            model.addAttribute("deliveryPreIntoList", deliveryPreIntoList);
        }

        model.addAttribute("ssc11311Bean", ssc11311Bean);
        model.addAttribute("sscDifferDetails", sscDifferDetails);
        model.addAttribute("downLoadUrl", SystemServerManager.CommonServerManager.getFileServerDownloadProxy());
        return "ssc/SSC11312";
    }

    /**
     * 根据文件名判断是否为图片
     */
    private static boolean isPic(String name) {
        boolean isPic = false;

        for (String suffix : IMG_SUFFIXS) {
            if(name.endsWith(suffix)){
                isPic = true;
            }
        }

        return isPic;
    }

    /**
     * 图片弹出页面
     */
    @RequestMapping(value = "showPic", method = RequestMethod.POST)
    public String showPic(String picPath,Model model) {
        model.addAttribute("picPath",picPath);
        return "ssc/SSC1131201";
    }

}
