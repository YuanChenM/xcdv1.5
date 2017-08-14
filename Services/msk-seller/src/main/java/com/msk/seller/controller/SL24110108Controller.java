package com.msk.seller.controller;

import com.hoperun.core.exception.BusinessException;
import com.msk.common.base.BaseUploadController;
import com.msk.seller.bean.SL24110306Bean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.logic.SL24110108Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by writer on 2016/1/30.
 */
@Controller
@RequestMapping("SL24110108")
public class SL24110108Controller extends BaseUploadController {

    @Autowired
    private SL24110108Logic sL24110108Logic;

    @RequestMapping(value = "update",
        method = RequestMethod.POST)
    public void update(MultipartFile file, HttpServletResponse response, SL24110306Bean sL24110306Bean)
        throws IOException {
        /** 管理人员职务非空判断，为空 则提示必须选择，不为空，根据传值，设置该成员职务 */
        if (null == sL24110306Bean.getMemberDuties()) {
            throw new BusinessException("请选择管理人员的职务");
        }
        /** 根据传值，确定职务 */
        if ("1".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("公司董事长");
        }
        if ("2".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("公司总经理");
        }
        if ("3".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("公司副总经理");
        }
        if ("4".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("销售部经理");
        }
        if ("5".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("品控部经理");
        }
        if ("6".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("财务部经理");
        }
        if ("7".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("生产部经理");
        }
        if ("8".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("营销负责人");
        }
        if ("9".equals(sL24110306Bean.getMemberDuties())) {
            sL24110306Bean.setMemberDuties("业务联系人");
        }
        sL24110306Bean.setUpdId(getLoginUser().getEmplId());
        /** 更新成员信息 */
        this.sL24110108Logic.update(sL24110306Bean);
        /** 如果没有上传图像，则不做操作 */
        if (file.getSize() == 0) {
            super.callBack(null, "保存成功", response);
        } else {
            // 图片上传到ftp里面
            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                    + sL24110306Bean.getEpId() + "/";
            String uploadFileName = "";
            if ("公司董事长".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.BOAMAN;
            }
            if ("公司总经理".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.GENMAN;
            }
            if ("公司副总经理".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.VICMAN;
            }
            if ("销售部经理".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.SALMAN;
            }
            if ("品控部经理".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.QCMAN;
            }
            if ("财务部经理".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.FINANCE;
            }
            if ("生产部经理".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.PDMAN;
            }
            if ("营销负责人".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.SALEMAN;
            }
            if ("业务联系人".equals(sL24110306Bean.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.OPERMAN;
            }
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(file, uploadFilePath, uploadFileName);
            super.callBack(null, "保存成功", response);
        }
    }

    @RequestMapping("delete")
    public void deleteMa(HttpServletResponse response, SL24110306Bean sL24110306Bean) throws IOException {
        /**
         * 删除的时候，先根据用户的企业ID和成员ID查询用户的职位，在根据职位删除文件服务器上的图片
         * 因为用户可能会先选择职位，然后再点击删除时，这样的话页面传入的职务可能出现
         * 错误，所以先到数据库进行查询
         */
        SL24110306Bean sl24110306Bean1 = this.sL24110108Logic.findEntity(sL24110306Bean);
        this.sL24110108Logic.removeMa(sL24110306Bean);
        String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                + sL24110306Bean.getEpId() + "/";
        String uploadFileName = "";
        if (null != sl24110306Bean1) {
            if ("公司董事长".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.BOAMAN;
            }
            if ("公司总经理".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.GENMAN;
            }
            if ("公司副总经理".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.VICMAN;
            }
            if ("销售部经理".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.SALMAN;
            }
            if ("品控部经理".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.QCMAN;
            }
            if ("财务部经理".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.FINANCE;
            }
            if ("生产部经理".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.PDMAN;
            }
            if ("营销负责人".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.SALEMAN;
            }
            if ("业务联系人".equals(sl24110306Bean1.getMemberDuties())) {
                uploadFileName = BusinessConst.SLPath.OPERMAN;
            }
        }
        SLUploadFile slUploadFile = new SLUploadFile();
        slUploadFile.deleteFileFromFtp(uploadFilePath, uploadFileName);
        super.callBack(null, "删除成功", response);
    }

}
