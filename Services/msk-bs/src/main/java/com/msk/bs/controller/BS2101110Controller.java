package com.msk.bs.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.bs.bean.BS2101105Bean;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.bs.logic.BS2101106Logic;
import com.msk.bs.logic.IBS2101102RsLogic;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsRequest;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.BsBasicInfo;
import com.msk.core.entity.SlShopInfo;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 买手账号信息编辑
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101110")
public class BS2101110Controller extends BaseUploadController {

    private static Logger logger = LoggerFactory.getLogger(BS2101110Controller.class);

    @Autowired
    private IBS2101102RsLogic ibs2101102RsLogic;
    @Autowired
    private BS2101106Logic bs2101106Logic;

    interface SqlId {
        /**
         * 查询卖家基本信息
         */
        static final String SQL_ID_FIND_SL_SELLER_FOR_SLACCOUNT = "findSlSellerForSlAccount";
    }

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SlShopInfo slShopInfo, BS2101105Bean bs2101105Bean, HttpServletRequest request, Model model) {
        logger.debug("买手店基本信息编辑");
        if (isDebug) {
            return null;
        }
        String flagNum = bs2101105Bean.getFlagNum();
        model.addAttribute("slShopInfo", slShopInfo);
        model.addAttribute("flagNum", flagNum);
        return "bs/BS2101110";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(MultipartFile file, SlShopInfo slShopInfo, BS2101105Bean bs2101105Bean, HttpServletRequest request, HttpServletResponse response,String loginId) throws IOException {

        RsRequest<IBS2101102RsParam> param = new RsRequest<IBS2101102RsParam>();
        param.setLoginId(loginId);
        IBS2101102RsParam ibs2101102RsParam = new IBS2101102RsParam();
        ibs2101102RsParam.setLoginId(loginId);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(ibs2101102RsParam);
        Date date = DateTimeUtil.getCustomerDate();
        ibs2101102RsParam.setCrtTime(date);
        ibs2101102RsParam.setUpdTime(date);
        ibs2101102RsParam.setActTime(date);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        param.setParam(ibs2101102RsParam);

        String slAccount2 = (String) request.getSession().getAttribute("slAccount1");
        String slCode = bs2101105Bean.getSlCode();
        //新增操作slCode为空
        if (null == slCode || "".equals(slCode)) {
            if (null != slAccount2 && !"".equals(slAccount2)) {

                BaseParam baseParam1 = new BaseParam();
                baseParam1.setFilter("slAccount2", slAccount2);
                BsBasicInfo slSeller1 = bs2101106Logic.findSlCode(baseParam1);
                if(null !=slSeller1){
                    String slCode1 = slSeller1.getSlCode();
                    slShopInfo.setSlCode(slCode1);
                    if (file.getSize() > 0) {
                        String imgPath = ImgUpload(file);
                        slShopInfo.setShopLogo(imgPath);
                    }
                }
            }
            //修改操作
        } else {
            BaseParam baseParam = new BaseParam();
            baseParam.setFilter("slCode2", slCode);
            SlShopInfo slShopInfo1 = bs2101106Logic.findShopId(baseParam);
            if (null != slShopInfo1) {
                slShopInfo.setShopId(slShopInfo1.getShopId());
            }
            slShopInfo.setSlCode(slCode);
            if (file.getSize() > 0) {
                String imgPath = ImgUpload(file);
                slShopInfo.setShopLogo(imgPath);
            }
        }
        param.getParam().setSlShopInfo(slShopInfo);
        ibs2101102RsLogic.editAccount(param);
        super.callBack(null, "保存成功", response);
    }

    public static String ImgUpload(MultipartFile file) throws IOException {
        //MultipartFile转换为file
        MultipartFile multipartFile = file;
        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file1 = fi.getStoreLocation();

        HashMap<String, File> map = new HashMap<String, File>();
        map.put("fileImg", file1);
        Map<String, String> result = FileUploadUtil.uploadFiles(map);
        /*String imgString = result.toString();
        String imgName = imgString.substring(imgString.indexOf("=") + 1, imgString.indexOf("}"));
        String imgPath = "http://t-file.xianchida.com/_download/" + imgName;*/
        String imgPath = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers()+result.get("fileImg");
        return imgPath;
    }

}
