package com.msk.bs.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.Md5Digest;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.*;
import com.msk.bs.logic.BS2101107Logic;
import com.msk.bs.logic.BS2102103Logic;
import com.msk.bs.logic.BSHouseLeverLogic;
import com.msk.bs.logic.IBS2101104RsLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.SlHouseAccount;
import com.msk.core.entity.SlHouseIntroduce;
import com.msk.core.entity.SlHouseType;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by cx on 2016/2/29.
 */
@RestController
@Api(description = "编辑冻品管家信息RestController", tags = {"IBS2101104RsController"})
public class IBS2101104RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101104RsController.class);

    @Autowired
    private IBS2101104RsLogic bS2101104RsLogic;

    @Autowired
    private BSHouseLeverLogic bsHouseLeverLogic;

    @Autowired
    private BS2101107Logic bs2101107Logic;

    @Autowired
    private BS2102103Logic bs2102103Logic;

    /**
     * 编辑冻品管家信息接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "冻品管家信息", notes = "编辑冻品管家信息接口")
    @RequestMapping(value = "/bs/slInfo/slSeller/newOrUpdate", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101104Validator")
    public RsResponse<IBS2101102RsResult> editBSSeller(@RequestBody RsRequest<IBS2101104RsParam> param) {
        logger.debug("编辑冻品管家信息接口");
        RsResponse<IBS2101102RsResult> rs = new RsResponse<IBS2101102RsResult>();
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
//        SlHouseAccount account = bS2101104RsLogic.editBSSeller(param.getParam(),baseParam);
//        IBS2101102RsResult rsResult = new IBS2101102RsResult();
//        rsResult.setHouseAccount(account.getHouseAccount());
//        rsResult.setHouseCode(account.getHouseCode());
//        rs.setResult(rsResult);
//        rs.setMessage("更新成功");
//        return rs;
        RsResponse<SlHouseAccount> rsResponse = this.setHouseAccountParam(param);
        if("1001".equals(rsResponse.getReturnCode())){
            SlHouseAccount account = rsResponse.getResult();
            IBS2101102RsResult rsResult = new IBS2101102RsResult();
            if(account != null){
                rsResult.setHouseAccount(account.getHouseAccount());
                rsResult.setHouseCode(account.getHouseCode());
                rs.setResult(rsResult);
            }
            rs.setMessage("更新成功");
            return rs;
        }else {
            rs.setMessage(rsResponse.getMessage());
            rs.setStatus(SystemConst.RsStatus.FAIL);
            return rs;
        }
    }


    private RsResponse<SlHouseAccount> setHouseAccountParam(RsRequest<IBS2101104RsParam> param){
        IBS2101104RsParam ibs2101104RsParam = param.getParam();
        IBS2101104SlHouseAccount slHouseAccount = ibs2101104RsParam.getSlHouseAccount();

        if(slHouseAccount == null){
            throw new BusinessException("参数不规范，请检查后提交。");
        }
        if(!StringUtil.isNullOrEmpty(slHouseAccount.getHouseAccount()) && slHouseAccount.getHouseAccount().length() > 20){
            throw new BusinessException("管家账号不能超过20位");
        }
        if(!StringUtil.isNullOrEmpty(slHouseAccount.getAccountPsd()) && (slHouseAccount.getAccountPsd().length() > 11 || slHouseAccount.getAccountPsd().length() < 6)){
            throw new BusinessException("登录密码为6-11位");
        }
        if(!StringUtil.isNullOrEmpty(slHouseAccount.getSlIdcard()) &&
                (slHouseAccount.getSlIdcard().length() != 15 && slHouseAccount.getSlIdcard().length() != 18)){
            throw new BusinessException("身份证号格式不正确");
        }
        slHouseAccount.setAuthStatus(2);

        ArrayList<SlHouseType> houseTypeList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(slHouseAccount.getHouseTYPEList())){
            SlHouseType slHouseType = null;
            for(IBS2101104SlHouseAccountParam typeParam :slHouseAccount.getHouseTYPEList()){
                slHouseType = new SlHouseType();
                slHouseType.setParentTypeCode(typeParam.getHkTypeCode());
                slHouseType.setTypeCode(typeParam.getHkSecdTypeCode());
                houseTypeList.add(slHouseType);
            }
        }
        /**
         * 0基本冻品管家  01鸡产品分销冻品管家
         * 1分销   01鸡产品分销冻品管家
         * 2菜场   30菜场冻品管家
         * 3团膳   31团膳冻品管家
         * 4火锅   32
         */
        if(!StringUtil.isNullOrEmpty(slHouseAccount.getHouseCategory())){
            SlHouseType slHouseType =  this.setHouseType(slHouseAccount.getHouseCategory());
            houseTypeList.add(slHouseType);
        }

        ibs2101104RsParam.setHouseTypeList(houseTypeList);
        //
        BaseParam baseParam = new BaseParam();
        baseParam.setCrtId(param.getLoginId());
        baseParam.setUpdId(param.getLoginId());
        baseParam.setActId(param.getLoginId());
        baseParam.setUserType(param.getLoginId());

        RsResponse<SlHouseAccount> rs = new RsResponse<SlHouseAccount>();
        rs.setReturnCode("1002");
        rs.setMessage("保存失败");

        List<BS2102107Bean> slHouseTypeList = null;
        SlHouseAccount houseAccount = null;
        //根据houseAccount 查询信息
        if(!StringUtil.isNullOrEmpty(ibs2101104RsParam.getSlHouseAccount().getHouseAccount()) &&
                !StringUtil.isNullOrEmpty(ibs2101104RsParam.getSlHouseAccount().getHouseCode())){
            houseAccount = bS2101104RsLogic.findHABySlCodeAndAccount(ibs2101104RsParam.getSlHouseAccount());
            //判断是否修改手机号
            if(!checkHouseTel(houseAccount,ibs2101104RsParam)){
                throw new BusinessException("手机号已经存在");
            }
            if(!checkSlCode(houseAccount,ibs2101104RsParam)){
                throw new BusinessException("该管家不在该买手ID下");
            }
        }
        else if(!StringUtil.isNullOrEmpty(ibs2101104RsParam.getSlHouseAccount().getHouseAccount())){
            houseAccount = bS2101104RsLogic.findHouseAccountBasicInfo(ibs2101104RsParam.getSlHouseAccount());
            //判断是否修改手机号
            if(!checkHouseTel(houseAccount,ibs2101104RsParam)){
                throw new BusinessException("手机号已经存在");
            }
            if(!checkSlCode(houseAccount,ibs2101104RsParam)){
                throw new BusinessException("该管家不在该买手ID下");
            }
        }else if (!StringUtil.isNullOrEmpty(ibs2101104RsParam.getSlHouseAccount().getHouseCode())){
            //根据houseCode 查询信息
            houseAccount = bS2101104RsLogic.findHABySlCodeAndHouseCode(ibs2101104RsParam.getSlHouseAccount());
            //判断是否修改手机号
            if(!checkHouseTel(houseAccount,ibs2101104RsParam)){
                throw new BusinessException("手机号已经存在");
            }
            if(!checkSlCode(houseAccount,ibs2101104RsParam)){
                throw new BusinessException("该管家不在该买手ID下");
            }
        }else {
            //根据 tel 查询冻品管家信息
            houseAccount =  bS2101104RsLogic.findHouseAccountByTelIsExist(ibs2101104RsParam.getSlHouseAccount());
            if(!checkSlCode(houseAccount,ibs2101104RsParam)){
                throw new BusinessException("该管家不在该买手ID下");
            }
        }
        if(null == houseAccount){
            if(StringUtil.isNullOrEmpty(slHouseAccount.getSlCode())){
                slHouseAccount.setSlCode("0000000");
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getHouseAccount())){
                slHouseAccount.setHouseAccount(Md5Digest.digest(UUID.randomUUID().toString()).substring(0,10));
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getAccountPsd())){
                slHouseAccount.setAccountPsd("111111");
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getHouseShowName())){
                if(!StringUtil.isNullOrEmpty(slHouseAccount.getHouseContact())){
                    slHouseAccount.setHouseShowName(slHouseAccount.getHouseContact());
                }else {
                    slHouseAccount.setHouseShowName("默认姓名");
                }
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getHouseTel())){
                String temp = String.valueOf(System.nanoTime());
                temp = temp.length() > 10 ? temp.substring(temp.length() - 10,temp.length()) : temp;
                slHouseAccount.setHouseTel("16"+temp);
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getSlIdcard())){
                slHouseAccount.setSlIdcard(String.valueOf(System.nanoTime()));
            }
            logger.info("新增默认构造虚拟地址--begin");
            if(StringUtil.isNullOrEmpty(slHouseAccount.getVprovinceCode())){
                slHouseAccount.setVprovinceCode("01");
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getVcityCode())){
                slHouseAccount.setVcityCode("001");
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getVdistrictCode())){
                slHouseAccount.setVdistrictCode("01");
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getVhouseAddress())){
                slHouseAccount.setVhouseAddress("默认地址");
            }
            logger.info("新增默认构造虚拟地址--end");
            //新增时 没有传分类信息
            if(CollectionUtils.isEmpty(ibs2101104RsParam.getHouseTypeList())){
                houseTypeList.add(this.initHouseType());
                ibs2101104RsParam.setHouseTypeList(houseTypeList);
            }



            return bS2101104RsLogic.editHouseAccount(ibs2101104RsParam,baseParam,slHouseTypeList);
        }else {
            if(StringUtil.isNullOrEmpty(slHouseAccount.getVprovinceCode())){
                slHouseAccount.setVprovinceCode(houseAccount.getVprovinceCode());
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getVcityCode())){
                slHouseAccount.setVcityCode(houseAccount.getVcityCode());
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getVdistrictCode())){
                slHouseAccount.setVdistrictCode(houseAccount.getVdistrictCode());
            }
            if(StringUtil.isNullOrEmpty(slHouseAccount.getVhouseAddress())){
                slHouseAccount.setVhouseAddress(houseAccount.getVhouseAddress());
            }
            // 获取物流区
            DistrictParam vdistrictParam = new DistrictParam();
            vdistrictParam.setCityName(ibs2101104RsParam.getVcityName());
            vdistrictParam.setCityCodes(new String[] { ibs2101104RsParam.getSlHouseAccount().getVcityCode() });
            vdistrictParam.setFlag(0);
            List<CityBean> lgcsAreaBeanList = CommRestUtil.getProvinceCityList(vdistrictParam);
            //获取物流区

            if(!CollectionUtils.isEmpty(lgcsAreaBeanList)){
                BS2102103Param bs2102103Param = new BS2102103Param();
                bs2102103Param.setHouseCode(ibs2101104RsParam.getSlHouseAccount().getHouseCode());
                bs2102103Param.setSlCode(ibs2101104RsParam.getSlHouseAccount().getSlCode());
                bs2102103Param.setLgcsAreaName(lgcsAreaBeanList.get(0).getLgcsAreaName());
                bs2102103Param.setLgcsAreaCode(lgcsAreaBeanList.get(0).getLgcsAreaCode());
                bs2102103Param.setPaging(false);
                PageResult<BS2102107Bean> pageResult = bs2102103Logic.findAllHouseManage(bs2102103Param);
                slHouseTypeList = pageResult.getData();
                if(CollectionUtils.isEmpty(slHouseTypeList) && CollectionUtils.isEmpty(ibs2101104RsParam.getHouseTypeList())){
                    houseTypeList.add(this.initHouseType());
                    ibs2101104RsParam.setHouseTypeList(houseTypeList);
                }

                if(StringUtil.isNullOrEmpty(ibs2101104RsParam.getSlHouseAccount().getHouseAccount())){
                    ibs2101104RsParam.getSlHouseAccount().setHouseAccount(houseAccount.getHouseAccount());
                }
                if(StringUtil.isNullOrEmpty(ibs2101104RsParam.getSlHouseAccount().getHouseCode())){
                    ibs2101104RsParam.getSlHouseAccount().setHouseCode(houseAccount.getHouseCode());
                }
                return bS2101104RsLogic.editHouseAccount(ibs2101104RsParam,baseParam,slHouseTypeList);
            }else {
                return rs;
            }
        }
    }

    //默认构造一个分类信息
    private SlHouseType initHouseType(){
        logger.info("默认构造一个分销冻品管家 :鸡产品分销冻品管家---begin");
        SlHouseType slHouseType = new SlHouseType();
        slHouseType.setParentTypeCode("1");
        slHouseType.setTypeCode("01");
        logger.info("默认构造一个分销冻品管家 :鸡产品分销冻品管家---end");
        return slHouseType;
    }

    private SlHouseType setHouseType(String houseCategory){
        SlHouseType slHouseType = new SlHouseType();
        switch (houseCategory){
            case "0":
                slHouseType.setParentTypeCode("1");
                slHouseType.setTypeCode("01");
                break;
            case "1":
                slHouseType.setParentTypeCode("1");
                slHouseType.setTypeCode("01");
                break;
            case "2":
                slHouseType.setParentTypeCode("2");
                slHouseType.setTypeCode("30");
                break;
            case "3":
                slHouseType.setParentTypeCode("2");
                slHouseType.setTypeCode("31");
                break;
            case "4":
                slHouseType.setParentTypeCode("2");
                slHouseType.setTypeCode("32");
                break;
            default:
                slHouseType.setParentTypeCode("1");
                slHouseType.setTypeCode("01");
                break;
        }
        return slHouseType;
    }

    //判断slcode是否一致
    private boolean checkSlCode(SlHouseAccount houseAccount,IBS2101104RsParam ibs2101104RsParam){
        if(null != houseAccount){
            if(!ibs2101104RsParam.getSlHouseAccount().getSlCode().equals(houseAccount.getSlCode())){
                return false;
            }
        }
        return true;
    }

    //判断修改的手机号是否已经存在
    private boolean checkHouseTel(SlHouseAccount houseAccount,IBS2101104RsParam ibs2101104RsParam){
        //判断是否修改手机号
        if(null != houseAccount){
            if(!ibs2101104RsParam.getSlHouseAccount().getHouseTel().equals(houseAccount.getHouseTel())){
                //判断手机号是否存在
                int houseAccountTe = bS2101104RsLogic.findHouseAccountExistByTel(ibs2101104RsParam.getSlHouseAccount());
                if(houseAccountTe >0){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 检查买手账户是否存在接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "买手账户", notes = "检查买手账户是否存在接口接口")
    @RequestMapping(value = "/bs/checkHouseInfoExist", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> checkBsInfoExist(@RequestBody RsRequest<IBS2101104SlHouseAccount> param) {
        logger.debug("检查买手账户是否存在接口");
        RsResponse<Integer> rs = new RsResponse<Integer>();
        IBS2101104SlHouseAccount accountParam = param.getParam();
        SlHouseAccount houseAccount = bS2101104RsLogic.findHouseAccountBasicInfo(accountParam);
        if(houseAccount == null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("检查买手账户是否存在成功");
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("买手账户已存在");
        return rs;
    }

    /**
     * 检查买手账户是否存在接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "买手账户", notes = "检查冻品管家电话是否存在接口接口")
    @RequestMapping(value = "/bs/checkHouseTelExist", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> checkHouseTelExist(@RequestBody RsRequest<IBS2101104SlHouseAccount> param) {
        logger.debug("检查冻品管家电话是否存在接口");
        RsResponse<Integer> rs = new RsResponse<Integer>();
        IBS2101104SlHouseAccount accountParam = param.getParam();
        SlHouseAccount houseAccount = bS2101104RsLogic.findHouseAccountByTelIsExist(accountParam);
        if(houseAccount == null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("检查冻品管家电话是否存在成功");
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("冻品管家电话已存在");
        return rs;
    }


    /**
     * 上传图片（64位字节码）
     *
     * @param imgBase64
     * @param request
     * @return
     * @throws IOException
     */
    private String uploadImg(String imgBase64, HttpServletRequest request) throws IOException {
        String fileUrl = request.getSession().getServletContext().getRealPath("");
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        byte[] b = decoder.decodeBuffer(imgBase64);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
        }
        //生成jpeg图片
        Long newDate = DateTimeUtil.getCustomerDate().getTime();

        String imgFilePath = fileUrl + "/" + newDate + "backup.jpeg";//生成零时文件
        OutputStream out = new FileOutputStream(imgFilePath);
        out.write(b);
        out.flush();
        out.close();

        File file = new File(imgFilePath);
        Map<String, File> files = new HashMap<String, File>();
        files.put("fileImg", file);
        //上传文件
        Map<String, String> result = FileUploadUtil.uploadFiles(files);

        file.delete();//删除文件
        String imgPath = result.get("fileImg");
        return imgPath;
    }





    /**
     * 编辑冻品管家信息接口
     *
     * @param param param
     * @return rs
     */
    @ApiOperation(value = "添加冻品管家", notes = "添加冻品管家接口")
    @RequestMapping(value = "/bs/houseInfoNew", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> houseInfoNew(@RequestBody RsRequest<IBS2101104RsParam> param,HttpServletRequest request) throws IOException {
        logger.debug("编辑冻品管家信息接口");
        RsResponse<Integer> rs = new RsResponse<Integer>();

        IBS2101104RsParam ibs2101104RsParam = param.getParam();

        SlHouseIntroduce introduce = ibs2101104RsParam.getSlHouseIntroduce();
        if(null != introduce) {
            String uploadUrl1 = introduce.getUploadUrl1();
            String uploadUrl2 = introduce.getUploadUrl2();
            if (!StringUtil.isNullOrEmpty(uploadUrl1)) {
                String imgUrl = this.uploadImg(uploadUrl1, request);
                introduce.setUploadUrl1(imgUrl);
                introduce.setUploadUrl1Type("0");
            }
            if (!StringUtil.isNullOrEmpty(uploadUrl2)) {
                String imgUrl = this.uploadImg(uploadUrl2, request);
                introduce.setUploadUrl2(imgUrl);
                introduce.setUploadUrl2Type("0");
            }
        }
        RsResponse<SlHouseAccount> rsResponse = bS2101104RsLogic.editHouseAccount(param.getParam(), new BaseParam(),null);
        if("1001".equals(rsResponse.getReturnCode())){
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("添加冻品管家成功");
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("添加冻品管家失败");
        return rs;
    }

    /**
     * 获取冻品管家一级分类信息
     *
     * @return rs
     */
    @ApiOperation(value = "冻品管家分类", notes = "获取冻品管家分类接口")
    @RequestMapping(value = "/bs/findHouseTypeList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<SlHouseType>> houseTypeList(@RequestBody RsRequest<SlHouseType> param){
        logger.debug("获取冻品管家分类接口");
        RsResponse<List<SlHouseType>> rs = new RsResponse<List<SlHouseType>>();
        SlHouseType houseType = param.getParam();
        List<SlHouseType> houseTypeList = bsHouseLeverLogic.findSlHouseType(houseType);
        rs.setResult(houseTypeList);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        return rs;

    }
}
