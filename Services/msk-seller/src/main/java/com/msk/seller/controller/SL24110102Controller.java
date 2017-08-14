package com.msk.seller.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.ProvinceBean;
import com.msk.seller.bean.SLUploadFile;
import com.msk.seller.bean.SlEpAgentAuthBean;
import com.msk.seller.logic.SL24110102Logic;
import com.msk.seller.logic.SL241103001Logic;
import com.msk.seller.logic.SL241103010Logic;
import com.msk.seller.utils.BusinessConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by fjm on 2016/1/28.
 */
@Controller
@RequestMapping("SL24110102")
public class SL24110102Controller extends BaseUploadController {

    @Autowired
    private SL24110102Logic sL24110102Logic;
    @Autowired
    private SL241103001Logic sl241103001Logic;
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private SL241103010Logic sl241103010Logic;

    @RequestMapping(value = "update",
        method = RequestMethod.POST)
    public void update(MultipartFile epthrfile, MultipartFile licfile, MultipartFile taxfile, MultipartFile orgfile,
        MultipartFile balfile, SlEnterprise slEnterprise, SlSeller slSeller, String slConFlg, String[] slSecondaryClass,
        String provinceCode, String cityCode, String districtCode, String licType, String licTermUnliimited,
        HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (null == provinceCode || "".equals(provinceCode)) {
            throw new BusinessException("请选择省（含省、直辖市）");
        }
        // Modified by xia_xiaojie on 2016/6/16. Modified start.
        // MdProvince mdProvince = this.sl241103001Logic.findProvince(provinceCode);
        // slSeller.setProvinceCode(provinceCode);
        // String areaCode = this.sl241103001Logic.findAreaCode(mdProvince.getAreaId());
        // slSeller.setAreaCode(areaCode);
        ProvinceBean province = sl241103001Logic.findProvince(provinceCode);
        slSeller.setProvinceCode(provinceCode);
        slSeller.setProvinceName(province.getProvinceName());
        slSeller.setAreaCode(province.getAreaCode());
        slSeller.setAreaName(province.getAreaName());
        // Modified end.

        if (null == cityCode || "".equals(cityCode)) {
            throw new BusinessException("请选择地区（含地级市）");
        }

        // Modified by xia_xiaojie on 2016/6/16. Modified start.
        // MdCity mdCity = this.sl241103001Logic.findCity(cityCode);
        // slSeller.setCityCode(cityCode);
        // String lgcsAreaCode = this.sl241103001Logic.findLgcsAreaCode(mdCity.getLgcsAreaId());
        // slSeller.setLgcsAreaCode(lgcsAreaCode);
        CityBean city = sl241103001Logic.findCity(cityCode);
        slSeller.setCityCode(cityCode);
        slSeller.setCityName(city.getCityName());
        slSeller.setLgcsAreaCode(city.getLgcsAreaCode());
        slSeller.setLgcsAreaName(city.getLgcsAreaName());
        // Modified end.

        if (null == districtCode || "".equals(districtCode)) {
            throw new BusinessException("请选择区（含县级市、县、区）");
        }
        DistrictBean district = sl241103001Logic.findDistrict(cityCode, districtCode);
        slSeller.setDistrictCode(districtCode);
        slSeller.setDistrictName(district.getDistrictName());

        slSeller.setSqaStatus(2);
       // request.getSession().setAttribute("jsp_slCode", slSeller.getSlCode());
        // seller_4.获取ep_id
        Long epid = slEnterprise.getEpId();
        //request.getSession().setAttribute("jsp_epId", epid);
        // 二级经营类型
        slSeller.setSelfFlg("0");
        slSeller.setAgentFlg("0");
        slSeller.setOemFlg("0");

        Boolean flg = false;
        //根据卖家主类型确定二级分类必属于哪种类型
        String  slMainClass = StringUtil.toSafeString(slSeller.getSlMainClass());
        if ((SellerConstant.SlMainClass.PRODUCT+"").equals(slMainClass) || (SellerConstant.SlMainClass.SUPPLY+"").equals(slMainClass) ) {
            slSeller.setSelfFlg("1");
            flg = true;
        }
        if ((SellerConstant.SlMainClass.AGENT+"").equals(slMainClass)) {
            slSeller.setAgentFlg("1");
            flg = true;
        }
        if ((SellerConstant.SlMainClass.OEM+"").equals(slMainClass)) {
            slSeller.setOemFlg("1");
            flg = true;
        }
        /**
         * 遍历传过来的复选框内容，选择了1，为自产型。。。
         */
        if (null != slSecondaryClass) {
            for (int i = 0; i < slSecondaryClass.length; i++) {
                if ((SellerConstant.SlMainClass.PRODUCT+"").equals(slSecondaryClass[i])) {
                    slSeller.setSelfFlg("1");
                    flg = true;
                }
                if ((SellerConstant.SlMainClass.AGENT+"").equals(slSecondaryClass[i])) {
                    slSeller.setAgentFlg("1");
                    flg = true;
                }
                if ((SellerConstant.SlMainClass.OEM+"").equals(slSecondaryClass[i])) {
                    slSeller.setOemFlg("1");
                    flg = true;
                }
            }
        }
        if(!flg){
            throw new BusinessException("二级经营类型不能为空!");
        }
        // slSeller.setCrtId(super.getLoginUser().getCrtId());
        slSeller.setSlConFlg(slConFlg);
        /** 查询该卖家编码下的slseller信息 */
        SlSellerHis slSellerHis = this.sL24110102Logic.findSlSellerBySlCode(slSeller.getSlCode());
        if (null != slSellerHis) {
            if (!slSeller.getCityCode().equals(slSellerHis.getCityCode())) {
                Long hisId = this.commonLogic.maxId("SL_SELLER_HIS", "HIS_ID");
                slSellerHis.setHisId(hisId);
                slSellerHis.setCrtId(getLoginUser().getEmplId());
                this.sL24110102Logic.saveHis(slSellerHis);

            }
            if (!slSeller.getSlMainClass().equals(slSellerHis.getSlMainClass())) {
                BaseParam param = new BaseParam();
                param.setFilter("slCode", slSeller.getSlCode());
                param.setFilter("delFlg", "0");
                List<SlEpAgentAuthBean> slEpAgentAuthBeanList = null;
                if (NumberConst.IntDef.INT_TWO == slSellerHis.getSlMainClass()) {
                    slEpAgentAuthBeanList = sl241103010Logic.queryAgentData(param);
                } else if (NumberConst.IntDef.INT_THREE == slSellerHis.getSlMainClass()) {
                    slEpAgentAuthBeanList = sl241103010Logic.queryOemData(param);
                }
                if (!CollectionUtils.isEmpty(slEpAgentAuthBeanList)) {
                    throw new BusinessException("请先删除对应生产商，再修改类型");
                }
            }
            // 修改卖家编码变更逻辑
            BaseParam base = new BaseParam();
            base.setFilter("cityCode", cityCode);
            base.setFilter("slMainClass", StringUtil.toSafeString(slSeller.getSlMainClass()));
            base.setFilter("slConFlg", slConFlg);
            SlSeller seller = sL24110102Logic.findOne("queryMax", base);
            if (null != seller) {
                slSeller.setSlCodeDis(seller.getSlCodeDis());
            } else {
                String code = this.sl241103001Logic.findAccount(slSeller.getSlMainClass().toString(), cityCode,
                        slSeller.getSlConFlg());
                slSeller.setSlCodeDis(code);
            }
            /** 更新seller到数据库 */
        }
        // Modified end.
        slSeller.setUpdId(getLoginUser().getEmplId());
        this.sL24110102Logic.updateSl(slSeller);
        /** 更新slEnterprise到数据库 */
        slEnterprise.setLicType(licType);
        //slEnterprise.setCrtId(super.getLoginUser().getCrtId());
        slEnterprise.setLicTermUnliimited(licTermUnliimited);
        slEnterprise.setUpdId(getLoginUser().getEmplId());
        this.sL24110102Logic.updateEp(slEnterprise);
        if (null != licfile && 0 != licfile.getSize()) {
            // 图片上传到ftp里面
            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                    + epid + "/";
            String uploadFileName = BusinessConst.SLPath.EPBUS;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(licfile, uploadFilePath, uploadFileName);
        }
        if (null != taxfile && 0 != taxfile.getSize()) {
            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                    + epid + "/";
            String uploadFileName = BusinessConst.SLPath.EPTAX;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(taxfile, uploadFilePath, uploadFileName);
        }
        if (null != epthrfile && 0 != epthrfile.getSize()) {
            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                    + epid + "/";
            String uploadFileName = BusinessConst.SLPath.EPTHR;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(epthrfile, uploadFilePath, uploadFileName);
        }
        if (null != orgfile && 0 != orgfile.getSize()) {
            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                    + epid + "/";
            String uploadFileName = BusinessConst.SLPath.EPORG;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(orgfile, uploadFilePath, uploadFileName);
        }
        if (null != balfile && 0 != balfile.getSize()) {
            String uploadFilePath = BusinessConst.SLPath.SERVICEIMAGEPATH + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/"
                    + epid + "/";
            String uploadFileName = BusinessConst.SLPath.EPBAN;
            SLUploadFile slUploadFile = new SLUploadFile();
            slUploadFile.saveUploadFile(balfile, uploadFilePath, uploadFileName);
        }
        super.callBack(null, "保存成功", response);
    }
}