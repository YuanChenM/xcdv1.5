package com.msk.seller.controller;

import com.hoperun.core.consts.NumberConst;
import com.msk.common.base.BaseUploadController;
import com.msk.core.entity.SlAccount;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110101Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fjm on 2016/1/28.
 */
@Controller
@RequestMapping("SL24110101")
public class SL24110101Controller extends BaseUploadController{

    @Autowired
    private SL24110101Logic sL24110101Logic;

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void update(MultipartFile file,SlAccount slAccount,HttpServletResponse response) throws IOException {
        //Modified by xia_xiaojie on 2016/6/16. Modified start.
        String sellerAccount = slAccount.getSlAccount();
        String uploadFilePath = null;
        //Modified end.

        if (file.getSize() > 0) {
            //图片上传到ftp里面
            uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" +BusinessConst.SLPath.BASE+ "/";
            String uploadFileName = sellerAccount;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(file, uploadFilePath, uploadFileName);
        }

        //Modified by xia_xiaojie on 2016/6/16. Modified start.
        //SlAccount dbAccount = sL24110101Logic.findSellerAcount(sellerAccount);
        //int ver = (null == dbAccount.getVer()) ? NumberConst.IntDef.INT_ONE : dbAccount.getVer();

        //slAccount.setSlAccount(sellerAccount);
        //slAccount.setUpdId(this.getLoginUser().getUpdId());
        if (StringUtils.hasText(uploadFilePath)) {
            slAccount.setAccountImg(uploadFilePath);
        }
        slAccount.setUpdId(getLoginUser().getEmplId());
        sL24110101Logic.update(slAccount);
        //Modified end.

        super.callBack(null,"保存成功",response);
    }
}
