package com.msk.bs.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.BS2101105Bean;
import com.msk.bs.bean.BS2102125Bean;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.bs.logic.*;
import com.msk.common.base.BaseUploadController;
import com.msk.common.bean.RsRequest;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.BsAccount;
import com.msk.core.entity.MdCity;
import com.msk.core.entity.MdDistrict;
import com.msk.core.entity.MdProvince;

/**
 * 买手账号信息编辑
 *
 * @author cx
 */
@Controller
@RequestMapping(value = "BS2101105")
public class BS2101105Controller extends BaseUploadController {

    private static Logger logger = LoggerFactory.getLogger(BS2101105Controller.class);
    @Autowired
    private IBS2101102RsLogic ibs2101102RsLogic;
    @Autowired
    private BSCommLogic bsCommLogic;
    @Autowired
    private BS2101107Logic bs2101107Logic;
    @Autowired
    private BS2101105Logic bs2101105Logic;
    @Autowired
    private BS2102120Logic bS2102120Logic;

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public String init(String slAccount,String slCode,String flagNum,Model model) throws UnsupportedEncodingException {
        logger.debug("买手账号信息编辑");
        if (isDebug) {
            return null;
        }
        //Modif   防止slAccount为空的情况 可以根据slcode去查询信息 2016/10/17 by whc Start
        if(StringUtil.isNullOrEmpty(slAccount) && !StringUtil.isNullOrEmpty(slCode)){
            // 查询买手的基本信息.
            BS2102125Bean bsBasicInfo = new BS2102125Bean();
            bsBasicInfo.setSlCode(slCode);
            BS2102125Bean bsbasicInfo = bS2102120Logic.findBsBasicInfo(bsBasicInfo);
            if(null != bsbasicInfo){
                slAccount = bsbasicInfo.getSlAccount();
            }
        }
        //Modif  防止slAccount为空的情况 可以根据slcode去查询信息 2016/10/17 by whc end

        //Modif for Bug#2604  报错“unterminated string literal” 2016/09/07 by zhu_kai1 Start
        request.getSession().removeAttribute("slAccount1");// 清除session
        //Modif for Bug#2604  报错“unterminated string literal” 2016/09/07 by zhu_kai1 end
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        BS2101105Bean bs2101105Bean = new BS2101105Bean();
        if(StringUtils.hasLength(slAccount)){
            baseParam.getFilterMap().put("slAccount", URLDecoder.decode(slAccount,"utf-8") );
            bs2101105Bean =  bs2101105Logic.findBasicInfoBySlCode(baseParam);
        }
        if (null !=bs2101105Bean){
            bs2101105Bean.setFlagNum(flagNum);
        }
    model.addAttribute("bs2101105Bean",bs2101105Bean);
    return "bs/BS2101105";
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(MultipartFile file,BsAccount bsAccount,HttpServletResponse response,HttpServletRequest request,String loginId) throws IOException {
        RsRequest<IBS2101102RsParam> param = new RsRequest<IBS2101102RsParam>();
        param.setLoginId(loginId);
        IBS2101102RsParam ibs2101102RsParam=new IBS2101102RsParam();
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
        super.setCommonParam(ibs2101102RsParam);
        ibs2101102RsParam.setLoginId(loginId);
        Date date = DateTimeUtil.getCustomerDate();
        ibs2101102RsParam.setCrtTime(date);
        ibs2101102RsParam.setUpdTime(date);
        ibs2101102RsParam.setActTime(date);
        /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
        param.setParam(ibs2101102RsParam);
        String slAccount1 = bsAccount.getSlAccount();
        //将account信息放进session里面
        request.getSession().setAttribute("slAccount1", slAccount1);
        bsAccount.setAuthStatus(2);// 默认为2-已认证
        bsAccount.setSlShowName(bsAccount.getSlContact());
        if (file.getSize() > 0) {
            if(file.getSize()< 4096){
                throw  new BusinessException("上传文件不能小于4KB");
            }
            String imgPath = ImgUpload(file);
            bsAccount.setAccountImg(imgPath);
        }
        param.getParam().setSlAccount(bsAccount);
        ibs2101102RsLogic.editAccount(param);
        super.callBack(null,"保存成功",response);
    }

    /**
     * 根据provinceId查询地市
     * @param provinceCode 省Code
     * @return
     */
    @RequestMapping(value = "findCity", method = RequestMethod.POST)
    public @ResponseBody
    List<MdCity> findCity(String provinceCode) {
        MdProvince mdProvince = this.bs2101107Logic.findProvince(provinceCode);
        return this.bsCommLogic.findCityList(StringUtil.toSafeString(mdProvince.getProvinceCode()));
    }


    /**
     * 校验手机号码、买手账号、买手名称是否已存在
     * * @param type 区分新增或编辑
     * slAccount 买手账号
     * slTel 买手手机号码
     * @return
     */
    @RequestMapping(value = "checkBuyerByTel", method = RequestMethod.POST)
    public @ResponseBody int checkBuyerByTel(String type,String slAccount,String slTel,String slContact) {
        return  bs2101105Logic.checkBuyerByAccountTelContact(type, slAccount, slTel, slContact);
    }

    /**
     * 根据cityId查询地市
     * @param cityCode 省Id
     * @return
     */
    @RequestMapping(value = "findDistrict", method = RequestMethod.POST)
    public @ResponseBody List<MdDistrict> findDistrict(String cityCode) {
        MdCity mdCity = this.bs2101107Logic.findCity(cityCode);
        return this.bsCommLogic.findDistrictList(StringUtil.toSafeString(mdCity.getCityCode()));
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
        String imgPath = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers()+result.get("fileImg");
        return imgPath;
    }

}
