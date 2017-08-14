package com.msk.product.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.web.utils.SessionManger;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.LoginUser;
import com.msk.core.entity.PdOrgStdItem;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141124showNameBean;
import com.msk.product.bean.PD14114601Bean;
import com.msk.product.bean.PD141146Bean;
import com.msk.product.logic.PD14114601Logic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * controller
 *
 * @author xhy
 */
@Controller
@RequestMapping(value = "pd14114601")
public class PD14114601Controller extends BaseUploadController {
    @Autowired
    private PD14114601Logic pd14114601Logic;

    /**
     * 图片文件后缀
     */
    private static String[] IMG_SUFFIXS = {"png", "jpg", "jpeg", "bmp", "gif", "PNG", "JPG", "JPEG", "BMP", "GIF"};

    /** 图片文件后缀 */
    private static long IMG_SIZE = 20 * 1024 * 1024 ;
    /**
     * 实例化页面
     *
     * @param model model
     * @return 界面
     * @author xhy
     */
    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, @RequestParam(value = "classestreeCode", required = false) String classestreeCode,
                       @RequestParam(value = "breedName", required = false) String breedName,
                       @RequestParam(value = "classesCode", required = false) String classesCode,
                       @RequestParam(value = "machiningCode", required = false) String machiningCode,
                       @RequestParam(value = "breedCode", required = false) String breedCode,
                       @RequestParam(value = "featureCode", required = false) String featureCode,
                       @RequestParam(value = "weightCode", required = false) String weightCode) {
        BaseParam param = new BaseParam();
        super.setCommonParam(param);
        param.setFilter("classestreeCode", classestreeCode);
        PdStandard standBean = this.pd14114601Logic.findOne(param);
        if (standBean == null) {
            throw new BusinessException("数据异常,请联系管理员(standardId找不到)!");
        }
        String standardId = standBean.getStandardId().toString();
        model.addAttribute("standardId", standardId);
        param.setFilter("standardId", standardId);
        //获取产品产品中源 和产品品种显示页面数量 xhy start
        List<PD141124showNameBean> showName = this.pd14114601Logic.findBreedNameAndFea(param);
        if (showName.size() > NumberConst.IntDef.INT_ZERO) {
            PD141124showNameBean names = new PD141124showNameBean();
            String feaNames = "";
            for (PD141124showNameBean beans : showName) {
                feaNames += beans.getFeatureNames() + "/";
            }
            names.setFeatureNames(feaNames.substring(NumberConst.IntDef.INT_ZERO, feaNames.length() - NumberConst.IntDef.INT_ONE));
            model.addAttribute("feaNames", names.getFeatureNames());
        }
        model.addAttribute("breedName", breedName);
        //end
        List<PD14114601Bean> list = this.pd14114601Logic.findList(param);
        if (list.size() > NumberConst.IntDef.INT_ZERO) {
            model.addAttribute("list", list);
        } else {
            List<PdOrgStdItem> itemList = this.pd14114601Logic.findListPdOrgStd(param);
            if (itemList.size() > NumberConst.IntDef.INT_ZERO) {
                for (PdOrgStdItem beans : itemList) {
                    PD14114601Bean stdNull = new PD14114601Bean();
                    stdNull.setStandardId(standBean.getStandardId());
                    stdNull.setOrgStdItemId(beans.getOrgStdItemId());
                    stdNull.setOrgStdItemName(beans.getOrgStdItemName());
                    list.add(stdNull);
                }
                model.addAttribute("list", list);
            }
        }
        if (StringUtils.isNotBlank(classesCode)) model.addAttribute("classesCode", classesCode);
        if(StringUtils.isNotBlank(machiningCode)) model.addAttribute("machiningCode", machiningCode);
        if(StringUtils.isNotBlank(breedCode)) model.addAttribute("breedCode", breedCode);
        if(StringUtils.isNotBlank(featureCode)) model.addAttribute("featureCode", featureCode);
        if(StringUtils.isNotBlank(weightCode)) model.addAttribute("weightCode", weightCode);
        return "pd/PD14114601";
    }

    /**
     * 保存修改数据
     *
     * @param model
     * @param bean
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "saveAndEdit",
            method = RequestMethod.POST)
    public void saveAndEdit(Model model, PD14114601Bean bean, @RequestParam(value = "classesCode", required = false) String classesCode, HttpServletResponse response) throws IOException, ParseException {
        BaseParam param = new BaseParam();
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(param);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        //判断上传的文件是否是图片形式
        if (null != bean) {
            if (bean.getOrgStdItemIdArray().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = 0; i < bean.getOrgStdItemIdArray().length; i++) {

                    MultipartFile orgMGoodValFile1 = bean.getOrgMGoodValFile1()[i];
                    if(StringUtils.isNotBlank(orgMGoodValFile1.getOriginalFilename())) {
                        Boolean checkFileSuffixResult = super.checkFileSuffix(orgMGoodValFile1, IMG_SUFFIXS);
                        if (!checkFileSuffixResult) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResult = super.checkFileSize(orgMGoodValFile1, IMG_SIZE);
                        if (!checkFileSizeResult) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile orgMGoodValFile2 = bean.getOrgMGoodValFile2()[i];
                    if(StringUtils.isNotBlank(orgMGoodValFile2.getOriginalFilename())) {
                        Boolean checkFileSuffixResult2 = super.checkFileSuffix(orgMGoodValFile2, IMG_SUFFIXS);
                        if (!checkFileSuffixResult2) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResult2 = super.checkFileSize(orgMGoodValFile2, IMG_SIZE);
                        if (!checkFileSizeResult2) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile orgMGoodValFile3 = bean.getOrgMGoodValFile3()[i];
                    if(StringUtils.isNotBlank(orgMGoodValFile3.getOriginalFilename())) {
                        Boolean checkFileSuffixResult3 = super.checkFileSuffix(orgMGoodValFile3, IMG_SUFFIXS);
                        if (!checkFileSuffixResult3) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResult3 = super.checkFileSize(orgMGoodValFile3, IMG_SIZE);
                        if (!checkFileSizeResult3) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile orgMGoodValFile4 = bean.getOrgMGoodValFile4()[i];
                    if(StringUtils.isNotBlank(orgMGoodValFile4.getOriginalFilename())) {
                        Boolean checkFileSuffixResult4 = super.checkFileSuffix(orgMGoodValFile4, IMG_SUFFIXS);
                        if (!checkFileSuffixResult4) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResult4 = super.checkFileSize(orgMGoodValFile4, IMG_SIZE);
                        if (!checkFileSizeResult4) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }

                    MultipartFile normalMValFile1 = bean.getOrgMNormalValFile1()[i];
                    if(StringUtils.isNotBlank(normalMValFile1.getOriginalFilename())) {
                        Boolean checkFileSuffixResultN = super.checkFileSuffix(normalMValFile1, IMG_SUFFIXS);
                        if (!checkFileSuffixResultN) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResultN = super.checkFileSize(normalMValFile1, IMG_SIZE);
                        if (!checkFileSizeResultN) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile normalMValFile2 = bean.getOrgMNormalValFile2()[i];
                    if(StringUtils.isNotBlank(normalMValFile2.getOriginalFilename())) {
                        Boolean checkFileSuffixResultN2 = super.checkFileSuffix(normalMValFile2, IMG_SUFFIXS);
                        if (!checkFileSuffixResultN2) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResultN2 = super.checkFileSize(normalMValFile2, IMG_SIZE);
                        if (!checkFileSizeResultN2) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile normalMValFile3 = bean.getOrgMNormalValFile3()[i];
                    if(StringUtils.isNotBlank(normalMValFile3.getOriginalFilename())) {
                        Boolean checkFileSuffixResultN3 = super.checkFileSuffix(normalMValFile3, IMG_SUFFIXS);
                        if (!checkFileSuffixResultN3) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResultN3 = super.checkFileSize(normalMValFile3, IMG_SIZE);
                        if (!checkFileSizeResultN3) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile normalMValFile4 = bean.getOrgMNormalValFile4()[i];
                    if(StringUtils.isNotBlank(normalMValFile4.getOriginalFilename())) {
                        Boolean checkFileSuffixResultN4 = super.checkFileSuffix(normalMValFile4, IMG_SUFFIXS);
                        if (!checkFileSuffixResultN4) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResultN4 = super.checkFileSize(normalMValFile4, IMG_SIZE);
                        if (!checkFileSizeResultN4) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile badMValFile1 = bean.getOrgMBadValFile1()[i];
                    if(StringUtils.isNotBlank(badMValFile1.getOriginalFilename())) {
                        Boolean checkFileSuffixResultB1 = super.checkFileSuffix(badMValFile1, IMG_SUFFIXS);
                        if (!checkFileSuffixResultB1) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResultB = super.checkFileSize(badMValFile1, IMG_SIZE);
                        if (!checkFileSizeResultB) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile badMValFile2 = bean.getOrgMBadValFile2()[i];
                    if(StringUtils.isNotBlank(badMValFile2.getOriginalFilename())) {
                        Boolean checkFileSuffixResultB2 = super.checkFileSuffix(badMValFile2, IMG_SUFFIXS);
                        if (!checkFileSuffixResultB2) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResultB2 = super.checkFileSize(badMValFile2, IMG_SIZE);
                        if (!checkFileSizeResultB2) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile badMValFile3 = bean.getOrgMBadValFile3()[i];
                    if(StringUtils.isNotBlank(badMValFile3.getOriginalFilename())) {
                        Boolean checkFileSuffixResultB3 = super.checkFileSuffix(badMValFile3, IMG_SUFFIXS);
                        if (!checkFileSuffixResultB3) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResultB3 = super.checkFileSize(badMValFile3, IMG_SIZE);
                        if (!checkFileSizeResultB3) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                    MultipartFile badMValFile4 = bean.getOrgMBadValFile4()[i];
                    if(StringUtils.isNotBlank(badMValFile4.getOriginalFilename())) {
                        Boolean checkFileSuffixResultB4 = super.checkFileSuffix(badMValFile4, IMG_SUFFIXS);
                        if (!checkFileSuffixResultB4) {
                            throw new BusinessException("上传文件格式不正确,只能上传图片!");
                        }
                        Boolean checkFileSizeResultB4 = super.checkFileSize(badMValFile4, IMG_SIZE);
                        if (!checkFileSizeResultB4) {
                            throw new BusinessException("上传文件过大.(<20MB)");
                        }
                    }
                }
            }
        }
        bean.setActTime(new Date());
        bean.setCrtTime(new Date());
        bean.setUpdTime(new Date());
        this.pd14114601Logic.saveAndEdit(bean, param);
        if (StringUtils.isNotBlank(classesCode)) model.addAttribute("classescode", classesCode);
        super.callBack(null, "保存成功", response);
    }
//    /** BaseController中没有该判断 request是根据控制层获取*/
//    /**
//     * 获得登陆用户信息
//     *
//     * @return 登陆信息
//     */
//    protected LoginUser getLoginUser() {
//        LoginUser loginUser = (LoginUser) SessionManger.getSessionManger(request)
//                .getValue(SessionManger.USER_SESSION_KEY);
//        if (loginUser == null) {
//            throw new BusinessException("登录信息失效，请重新登录！");
//        }
//        return loginUser;
//    }
//
//    /**
//     * 设置共通字段
//     *
//     * @param param Param
//     */
//    protected void setCommonParam(BaseParam param) {
//        LoginUser loginUser = this.getLoginUser();
//        param.setCrtId(loginUser.getEmplId());
//        param.setUpdId(loginUser.getEmplId());
//        param.setActId(loginUser.getEmplId());
//        param.setUserType(loginUser.getUserType());
//    }

}