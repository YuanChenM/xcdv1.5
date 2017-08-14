package com.msk.bs.controller;

import com.msk.bs.bean.BS2101105Bean;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.SlShopInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 买手账号信息编辑
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101111")
public class BS2101111Controller extends BaseUploadController {

    private static Logger logger = LoggerFactory.getLogger(BS2101111Controller.class);

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SlShopInfo slShopInfo, BS2101105Bean bs2101105Bean,HttpServletRequest request, Model model) {
        logger.debug("编辑冻品管家");
        if (isDebug) {
            return null;
        }
       String flagNum =  bs2101105Bean.getFlagNum();
        model.addAttribute("slShopInfo", slShopInfo);
        model.addAttribute("flagNum", flagNum);
        return "bs/BS2101110";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(MultipartFile file, SlShopInfo slShopInfo, HttpServletRequest request, HttpServletResponse response) throws IOException {


        RsRequest<IBS2101102RsParam> param = new RsRequest<IBS2101102RsParam>();
        param.setLoginId(this.getLoginUser().getUpdId());
        IBS2101102RsParam ibs2101102RsParam = new IBS2101102RsParam();
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(ibs2101102RsParam);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        param.setParam(ibs2101102RsParam);
       // String slCode = (String) request.getSession().getAttribute("slCode");
       // slShopInfo.setSlCode(slCode);
        param.getParam().setSlShopInfo(slShopInfo);
    /*    ibs2101102RsLogic.editAccount(param);*/
        if (file.getSize() > 0) {
            //图片上传到ftp里面
//            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + BusinessConst.SLPath.BASE + "/";
//            String uploadFileName = slAccount.getSlAccount();
//            SLUploadFile slUploadFile = new SLUploadFile();
//            slUploadFile.saveUploadFile(file, uploadFilePath, uploadFileName);
        }
        super.callBack(null, "保存成功", response);
    }
}
