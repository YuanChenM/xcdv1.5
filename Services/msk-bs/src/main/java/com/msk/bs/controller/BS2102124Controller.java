package com.msk.bs.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.BS2101105Bean;
import com.msk.bs.bean.BS2102125Bean;
import com.msk.bs.logic.BS2101105Logic;
import com.msk.bs.logic.BS2102120Logic;
import com.msk.bs.logic.BSHouseLeverLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.config.server.SystemServerManager;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.SlHouseType;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.ProvinceBean;

/**
 * Created by gao_min on 2016/9/14.
 */
@Controller
@RequestMapping(value = "BS2102124")
public class BS2102124Controller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BS2102124Controller.class);

    @Autowired
    private BS2102120Logic bS2102120Logic;

    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;

    @Autowired
    private BS2101105Logic bs2101105Logic;

    @RequestMapping(value = "init", method = RequestMethod.POST)
    //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 start
    public
    @ResponseBody
    Map<String, Object> init(@RequestParam(value = "slCode", required = true) String slCode,
                             @RequestParam(value = "slAccount", required = false) String slAccount,
                             @RequestParam(value = "accountImg", required = false) String accountImg) {
     //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 end
        Map<String, Object> map = new HashMap<>();

        // 查询买手的基本信息.
        BS2102125Bean bsBasicInfo = new BS2102125Bean();
        bsBasicInfo.setSlCode(slCode);
        BS2102125Bean bsbasicInfo = bS2102120Logic.findBsBasicInfo(bsBasicInfo);
        slAccount = bsbasicInfo.getSlAccount();
        //买手账户信息
        BS2102125Bean bs2102125Bean = null;

        if (null != bsbasicInfo && !StringUtil.isNullOrEmpty(bsbasicInfo.getSlAccount())) {
            // 查询买手账户信息.
            SlAccount slBsAccount = new SlAccount();
            slBsAccount.setSlAccount(bsbasicInfo.getSlAccount());
            bs2102125Bean = bS2102120Logic.findSlBsAccount(slBsAccount);
            map.put("slAccount", bs2102125Bean);

            // 买手详细通讯录地址.
            if (!StringUtil.isNullOrEmpty(bsbasicInfo.getProvinceCode())) {
                DistrictParam param = new DistrictParam();
                String vprovince = "";
                String vcity = "";
                String vdistrict = "";
                // 获取省份信息
                param.setProvinceCode(bsbasicInfo.getProvinceCode());
                List<ProvinceBean> provinceBeanList = CommRestUtil.getProvinceList(param);
                if (!CollectionUtils.isEmpty(provinceBeanList)) {
                    vprovince = getPCDName(provinceBeanList, bsbasicInfo.getProvinceCode());
                }
                if (!StringUtil.isNullOrEmpty(bsbasicInfo.getCityCode())) {
                    //获取城市信息
                    param.setCityCode(bsbasicInfo.getCityCode());
                    List<CityBean> cityBeanList = CommRestUtil.getProvinceCityList(param);
                    if (!CollectionUtils.isEmpty(cityBeanList)) {
                        vcity = getPCDName(cityBeanList, bsbasicInfo.getCityCode());
                    }
                    if (!StringUtil.isNullOrEmpty(bsbasicInfo.getDistrictCode())) {
                        //获取区县信息
                        param.setDistrictCode(bsbasicInfo.getDistrictCode());
                        List<DistrictBean> districtBeanList = CommRestUtil.getDistrictList(param);
                        if (!CollectionUtils.isEmpty(districtBeanList)) {
                            vdistrict = getPCDName(districtBeanList, bsbasicInfo.getDistrictCode());
                        }
                    }
                }
                bsbasicInfo.setMemo9(vprovince + vcity + vdistrict + bsbasicInfo.getMemo9());
            }
            // 获取买手分类信息.
            if (null != bsbasicInfo.getMemo8()) {
                BS2102125Bean bsLevel = new BS2102125Bean();
                SlHouseType houseTypeThree = new SlHouseType();
                houseTypeThree.setTypeCode(bsbasicInfo.getMemo8());
                houseTypeThree.setTypeLever("2");
                // 获取买手三级分类.
                SlHouseType houseTypeThreeLevel = bS2102120Logic.findLevelName(houseTypeThree);
                if (null != houseTypeThreeLevel) {
                    bsLevel.setThreeLever(houseTypeThreeLevel.getTypeName());
                    SlHouseType houseTypeTwo = new SlHouseType();
                    houseTypeTwo.setTypeLever("1");
                    houseTypeTwo.setTypeCode(houseTypeThreeLevel.getParentTypeCode());
                    // 获取买手二级分类.
                    SlHouseType houseTypeTwoLevel = bS2102120Logic.findLevelName(houseTypeTwo);
                    if (null != houseTypeTwoLevel) {
                        bsLevel.setTwoLever(houseTypeTwoLevel.getTypeName());
                        SlHouseType houseTypeOne = new SlHouseType();
                        houseTypeOne.setTypeLever("0");
                        houseTypeOne.setTypeCode(houseTypeTwoLevel.getParentTypeCode());
                        // 获取买手一级分类.
                        SlHouseType houseTypeOneLevel = bS2102120Logic.findLevelName(houseTypeOne);
                        if (null != houseTypeOneLevel) {
                            bsLevel.setOneLever(houseTypeOneLevel.getTypeName());
                        }
                    }
                }
                map.put("bsLevel", bsLevel);
            }
            //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 start
            if(StringUtils.hasLength(slAccount)){
                BaseParam baseParam = new BaseParam();
                try{
                    baseParam.getFilterMap().put("slAccount", StringUtil.isEmpty(slAccount) ? "" : URLDecoder.decode(slAccount,"utf-8") );
                }catch (UnsupportedEncodingException exception){
                    exception.printStackTrace();
                }
                BS2101105Bean bs2101105Bean =  bs2101105Logic.findBasicInfoBySlCode(baseParam);
                if(bs2101105Bean != null && !StringUtil.isNullOrEmpty(bs2101105Bean.getAccountImg())){
                    String imgPath = bs2101105Bean.getAccountImg();
                    String serverPath = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
                    if(!StringUtil.isNullOrEmpty(imgPath) && !StringUtil.isNullOrEmpty(serverPath) && imgPath.indexOf(serverPath) > -1){
                        imgPath = imgPath.substring(imgPath.indexOf(serverPath)+serverPath.length(),imgPath.length());
                        bsbasicInfo.setMemo15(SystemServerManager.CommonServerManager.getMskFlieDownLoad()+imgPath);
                    }
                }
            }
            //Modify 买手信息列表导出图片 by yangchunyan 2016/09/29 end
            map.put("bsBasicInfo", bsbasicInfo);
        }

        if (!StringUtil.isNullOrEmpty(slCode)) {
            bsBasicInfo.setSlCode(slCode);
            // 查询买手性别.
            BS2102125Bean sexInfo = bS2102120Logic.findBsSexInfo(bsBasicInfo);
            if (!StringUtil.isNullOrEmpty(sexInfo.getFlag1()) && null != bs2102125Bean) {
                if ("1".equals(sexInfo.getFlag1())) {
                    bs2102125Bean.setFlag1("男");
                } else if ("2".equals(sexInfo.getFlag1())) {
                    bs2102125Bean.setFlag1("女");
                } else {
                    bs2102125Bean.setFlag1("");
                }
            }

            // 查询买手银行账户信息.
            BS2102125Bean bsBankInfo = bS2102120Logic.findBankInfoBySlCode(bsBasicInfo);
            if (null != bsBankInfo && null != bs2102125Bean) {
                if (!StringUtil.isNullOrEmpty(bsBankInfo.getAccountName())) {
                    // 开户名.
                    bs2102125Bean.setAccountName(bsBankInfo.getAccountName());
                }
                if (!StringUtil.isNullOrEmpty(bsBankInfo.getBankName())) {
                    // 开户行.
                    bs2102125Bean.setBankName(bsBankInfo.getBankName());
                }
                if (!StringUtil.isNullOrEmpty(bsBankInfo.getBankNo())) {
                    // 账号.
                    bs2102125Bean.setBankNo(bsBankInfo.getBankNo());
                }
            }
            map.put("bsSexAndBankInfo", bs2102125Bean);
        }
        return map;
    }

    private String getPCDName(List list, String code) {
        for (Object obj : list) {
            if (obj instanceof ProvinceBean) {
                if (((ProvinceBean) obj).getProvinceCode().equals(code)) {
                    return ((ProvinceBean) obj).getProvinceName();
                }
            }
            if (obj instanceof CityBean) {
                if (((CityBean) obj).getCityCode().equals(code)) {
                    return ((CityBean) obj).getCityName();
                }
            }
            if (obj instanceof DistrictBean) {
                if (((DistrictBean) obj).getDistrictCode().equals(code)) {
                    return ((DistrictBean) obj).getDistrictName();
                }
            }
        }
        return null;
    }
}
