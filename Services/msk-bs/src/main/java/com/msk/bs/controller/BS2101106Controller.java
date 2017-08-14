package com.msk.bs.controller;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.BS2101105Bean;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.bs.bean.IBS2101102RsResult;
import com.msk.bs.logic.BS2101106Logic;
import com.msk.bs.logic.BSCommLogic;
import com.msk.bs.logic.IBS2101102RsLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.*;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买手基本信息编辑Controller
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101106")
public class BS2101106Controller extends BaseUploadController {

    private static Logger logger = LoggerFactory.getLogger(BS2101106Controller.class);

    @Autowired
    private BSCommLogic bSCommLogic;
    @Autowired
    private IBS2101102RsLogic ibs2101102RsLogic;
    @Autowired
    private BS2101106Logic bs2101106Logic;


    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(SlSeller slSeller, SlBuyershop slBuyershop, BS2101105Bean bs2101105Bean, Model model) {
        logger.debug("买手基本信息编辑");
        if (isDebug) {
            return null;
        }
        //地区回显
        DistrictParam districtParam = new DistrictParam();
        List<MdProvince> mdProvinces = this.bSCommLogic.findMdProvinces(districtParam);
        List<LgcsAreaBean> logisticsAreasList = CommRestUtil.getLogisticsAreaList(new DistrictParam());
        List<MdCity> cityList = null;
        List<MdDistrict> mdDistrictList = null;
        String provinceCode = "";
        String cityCode = "";
        if (null != slSeller) {
            for (int i = 0; i < mdProvinces.size(); i++) {
                MdProvince mdProvince = mdProvinces.get(i);
                if (mdProvince.getProvinceCode().equals(slSeller.getProvinceCode())) {
                    provinceCode= StringUtil.toSafeString(mdProvince.getProvinceCode());
                }
            }
            cityList = this.bSCommLogic.findCityList(provinceCode);
            for (int i = 0; i < cityList.size(); i++) {
                MdCity mdCity = cityList.get(i);
                if (mdCity.getCityCode().equals(slSeller.getCityCode())) {
                    cityCode = StringUtil.toSafeString(mdCity.getCityCode());
                }
            }
        }
        List<SlHouseType> slHouseTypeList = bs2101106Logic.findBuyersClass();
        mdDistrictList = bSCommLogic.findDistrictList(cityCode);
        String flagNum = bs2101105Bean.getFlagNum();

        if(!StringUtil.isNullOrEmpty(bs2101105Bean.getSlCode())){
            SlBsBankaccount bankaccount = bs2101106Logic.findBankAccount(bs2101105Bean);
            model.addAttribute("bankaccount",bankaccount);
        }
        model.addAttribute("logisticsAreasList",logisticsAreasList);
        model.addAttribute("mdProvinces", mdProvinces);
        model.addAttribute("cityList", cityList);
        model.addAttribute("mdDistrictList", mdDistrictList);

        model.addAttribute("slSeller", slSeller);
        model.addAttribute("slBuyershop", slBuyershop);
        model.addAttribute("bs2101105Bean", bs2101105Bean);
        model.addAttribute("flagNum", flagNum);
        model.addAttribute("slHouseTypeList", slHouseTypeList);

        if(!StringUtil.isNullOrEmpty(bs2101105Bean.getMemo8())){
            SlHouseType houseTypeParam = new SlHouseType();
            //获取三级买手类型信息
            houseTypeParam.setTypeCode(bs2101105Bean.getMemo8());
            houseTypeParam.setTypeLever("2");
            SlHouseType threeLevel = bs2101106Logic.findBuyersSecondLevel(houseTypeParam).get(0);
            model.addAttribute("threeLevel", threeLevel);
            //获取二级买手类型信息
            houseTypeParam.setTypeCode(threeLevel.getParentTypeCode());
            houseTypeParam.setTypeLever("1");
            SlHouseType twoLevel = bs2101106Logic.findBuyersSecondLevel(houseTypeParam).get(0);
            model.addAttribute("twoLevel", twoLevel);
            //获取一级买手类型信息
            houseTypeParam.setTypeCode(twoLevel.getParentTypeCode());
            houseTypeParam.setTypeLever("0");
            SlHouseType oneLevel = bs2101106Logic.findBuyersSecondLevel(houseTypeParam).get(0);
            model.addAttribute("oneLevel", oneLevel.getTypeCode());

            //获取二级分类列表
            houseTypeParam.setParentTypeCode(oneLevel.getTypeCode());
            houseTypeParam.setTypeLever("1");
            houseTypeParam.setTypeCode("");
            List<SlHouseType> twoTypeList = bs2101106Logic.findBuyersSecondLevel(houseTypeParam);
            model.addAttribute("twoTypeList", twoTypeList);
            //获取三级分类列表
            houseTypeParam.setParentTypeCode(twoLevel.getTypeCode());
            houseTypeParam.setTypeLever("2");
            houseTypeParam.setTypeCode("");
            List<SlHouseType> threeTypeList = bs2101106Logic.findBuyersSecondLevel(houseTypeParam);
            model.addAttribute("threeTypeList", threeTypeList);
        }
        return "bs/BS2101106";
    }

    /**
     * 1、根据买手一级类型code查询二级买手类型
     * 2、根据买手二级类型code查询三级买手类型
     * @param houseType
     * @return
     */
    @RequestMapping(value = "findBuyersClassLevel", method = RequestMethod.POST)
    @ResponseBody
    public List<SlHouseType> findBuyersClassLevel(SlHouseType houseType){
        return bs2101106Logic.findBuyersSecondLevel(houseType);
    }



    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(MultipartFile file, BsBasicInfo bsBasicInfo, SlBuyershop slBuyershop, BS2101105Bean bs2101105Bean, HttpServletRequest request, HttpServletResponse response,SlBsBankaccount slBsBankaccount,String loginId) throws IOException {
        RsRequest<IBS2101102RsParam> param = new RsRequest<IBS2101102RsParam>();
        param.setLoginId(loginId);
        IBS2101102RsParam ibs2101102RsParam = new IBS2101102RsParam();
        ibs2101102RsParam.setLoginId(loginId);
        param.setParam(ibs2101102RsParam);

        String slAccount2 = (String) request.getSession().getAttribute("slAccount1");
        // 国籍先写死，1-为国内
        bsBasicInfo.setSlConFlg("1");
        if(null == slAccount2 || "".equals(slAccount2)){

            String slAccount = bs2101105Bean.getSlAccount();
            bsBasicInfo.setSlAccount(slAccount);
        }else {
            bsBasicInfo.setSlAccount(slAccount2);
        }

        //设置买手主分类4
        bsBasicInfo.setSlMainClass(4);
        if (file.getSize() > 0) {
            String imgPath = ImgUpload(file);
            bsBasicInfo.setMemo15(imgPath);
        }
        param.getParam().setSlSeller(bsBasicInfo);

        BaseParam baseParam = new BaseParam();
        String slCode = slBuyershop.getSlCode();
        baseParam.setFilter("slCode", slCode);
        //判断sl_buyershop 是否存在 如不存在设置slcode为“” 接口会走新增操作
        int num = bs2101106Logic.findSlBuyerhop(baseParam);
        if (num <= 0) {
            slBuyershop.setSlCode("");
        }
        param.getParam().setSlBuyerShop(slBuyershop);
        param.getParam().setSlBsBankaccount(slBsBankaccount);

        /*ibs2101102RsLogic.editAccount(param);*/
        super.setCommonParam(bs2101105Bean);
        //Modif for Bug#3157 at 2016/10/08 by ni_shaotang Start
        IBS2101102RsResult rs = ibs2101102RsLogic.editAccount(param);
        // 为了新增买手时，保存买手基本信息成功后，该页面买手编码应显示，故在message中将买手编码拼接
        super.callBack(null, "保存成功,"+rs.getSlSeller().getSlCodeDis()+","+rs.getHouseCode(), response);
        //Modif for Bug#3157 at 2016/10/08 by ni_shaotang end
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
       /* String imgString = result.toString();
        String imgName = imgString.substring(imgString.indexOf("=") + 1, imgString.indexOf("}"));
        String imgPath = "http://t-file.xianchida.com/_download/" + imgName;*/
        String imgPath = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers()+result.get("fileImg");
        return imgPath;
    }

}
