package com.msk.seller.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlEpWorkshop;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110300501Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by writer on 2016/1/27.
 */
@Controller
@RequestMapping("SL24110300501")
public class SL24110300501Controller extends BaseUploadController {

    @Autowired
    private SL24110300501Logic sL24110300501Logic;
    @Autowired
    private CommonLogic commonLogic;

    /**
     * 保存车间概括信息
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save(String jspSL24110300501_epId, MultipartFile file, SlEpWorkshop slEpWorkshop, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //  if(null==request.getSession().getAttribute("jsp_epId") || "".equals(request.getSession().getAttribute("jsp_epId"))){
        if (StringUtil.isNullOrEmpty(jspSL24110300501_epId)) {
            throw new BusinessException("请先创建账号信息");
        }
        /**获取用户存储的当前epID*/
        // Long slEpId = (Long)request.getSession().getAttribute("jsp_epId");
        Long slEpId = StringUtil.toLong(jspSL24110300501_epId);

        Long maxId = this.commonLogic.maxId("SL_EP_WORKSHOP", "WORKSHOP_ID");
        if (null == slEpWorkshop.getWorkshopName() || "".equals(slEpWorkshop.getWorkshopName().trim())) {
            throw new BusinessException("车间名称不可为空");
        }
        /**查询车间名称是否已经存在,如果存在  提示不能重复录入，不存在继续保存*/
        this.sL24110300501Logic.findIfExist(slEpId, slEpWorkshop.getWorkshopName());
        if (null != slEpWorkshop.getProcess() || null != slEpWorkshop.getProduct() || null != slEpWorkshop.getWorkshopName()) {
            //保存生产车间信息
            slEpWorkshop.setWorkshopId(maxId);

            //Modified by xia_xiaojie on 2016/6/22. Modified start.
            //slEpWorkshop.setCrtId(super.getLoginUser().getCrtId());
            //Modified end.
            slEpWorkshop.setCrtId(getLoginUser().getEmplId());
            this.sL24110300501Logic.saveSlEpWorkshop(slEpWorkshop, slEpId);
            if (file.getSize() != 0) {
                String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + slEpId + "/";
                String uploadFileName = BusinessConst.SLPath.EPWORKSHOPDES + maxId;
                SLUploadFile slUploadFile = new SLUploadFile();
                slUploadFile.saveUploadFile(file, uploadFilePath, uploadFileName);
            }
            super.callBack(null, "上传成功", response);
        }
    }
}
