package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.*;
import com.msk.bs.logic.*;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.FileUploadUtil;
import com.msk.core.entity.SlHouseEducation;
import com.msk.core.entity.SlHouseIntroduce;
import com.msk.core.entity.SlHouseTraining;
import com.msk.core.entity.SlHouseWork;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.ProvinceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取冻品管家详情
 * Created by ni_shaotang on 2016/8/18.
 */
@RestController
public class IBA2141203RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBA2141203RsController.class);
    @Autowired
    private IBS2101104RsLogic ibs2101104RsLogic;

    @Autowired
    private IBA2141203RsLogic iba2141203RsLogic;
    @Autowired
    private BS2102115Logic bs2102115Logic;
    @Autowired
    private BS2102116Logic bs2102116Logic;
    @Autowired
    private BS2102117Logic bs2102117Logic;

    /**
     * 获取省信息
     *
     * @return
     */
    @RequestMapping(value = "/ba/getProvinceList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<ProvinceBean>> getProvinceList() {
        logger.info("开始查询省信息列表");
        RsResponse<List<ProvinceBean>> response = new RsResponse<List<ProvinceBean>>();
        DistrictParam param = new DistrictParam();
        List<ProvinceBean> list = CommRestUtil.getProvinceList(param);
        response.setResult(list);
        return response;
    }

    /**
     * 根据省code获取城市信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/getProvinceCityList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<CityBean>> getProvinceCityList(@RequestBody RsRequest<DistrictParam> param) {
        logger.info("开始查询城市信息列表");
        RsResponse<List<CityBean>> response = new RsResponse<List<CityBean>>();
        List<CityBean> list = CommRestUtil.getProvinceCityList(param.getParam());
        response.setResult(list);
        return response;
    }

    /**
     * 根据城市code获取区县信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/getDistrictList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<DistrictBean>> getDistrictList(@RequestBody RsRequest<DistrictParam> param) {
        logger.info("开始查询城市信息列表");
        RsResponse<List<DistrictBean>> response = new RsResponse<List<DistrictBean>>();
        List<DistrictBean> list = CommRestUtil.getDistrictList(param.getParam());
        response.setResult(list);
        return response;
    }

    /**
     * 修改冻品管家信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/saveHouseInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<DistrictBean>> saveHouseInfo(@RequestBody RsRequest<IBS2101104SlHouseAccount> param) {
        logger.info("开始查询城市信息列表");
        RsResponse<List<DistrictBean>> response = new RsResponse<List<DistrictBean>>();
        IBS2101104SlHouseAccount houseAccount = param.getParam();
        response.setStatus(SystemConst.RsStatus.FAIL);
        if (StringUtil.isNullOrEmpty(houseAccount.getSlCode())) {
            response.setMessage("保存失败，买手code不能为空");
        } else if (StringUtil.isNullOrEmpty(houseAccount.getHouseCode())) {
            response.setMessage("保存失败，管家code不能为空");
        } else {
            ibs2101104RsLogic.modifySlHouseAccount(houseAccount);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("保存成功");
        }
        return response;
    }
//Add for 冻品管家自我介绍 at 2016/09/19 by ni_shaotang Start

    /**
     * 查询管家自我介绍信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/queryHouseIntroduce", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SlHouseIntroduce> queryHouseIntroduce(@RequestBody RsRequest<String> param) {
        RsResponse<SlHouseIntroduce> response = new RsResponse<SlHouseIntroduce>();
        SlHouseIntroduce houseIntroduce = iba2141203RsLogic.queryHouseIntroduce(param.getParam());
        String imgUrl = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        if (null != houseIntroduce && !StringUtil.isNullOrEmpty(houseIntroduce.getUploadUrl1())) {
            String uploadUrl1 = houseIntroduce.getUploadUrl1();
            if (uploadUrl1.indexOf("http://") < 0) {
                houseIntroduce.setUploadUrl1(imgUrl + uploadUrl1);
            }
        }
        if (null != houseIntroduce && !StringUtil.isNullOrEmpty(houseIntroduce.getUploadUrl2())) {
            String uploadUrl2 = houseIntroduce.getUploadUrl2();
            if (uploadUrl2.indexOf("http://") < 0) {
                houseIntroduce.setUploadUrl2(imgUrl + uploadUrl2);
            }
        }
        if (null != houseIntroduce && !StringUtil.isNullOrEmpty(houseIntroduce.getUploadUrl3())) {
            String uploadUrl3 = houseIntroduce.getUploadUrl3();
            if (uploadUrl3.indexOf("http://") < 0) {
                houseIntroduce.setUploadUrl3(imgUrl + uploadUrl3);
            }
        }
        response.setResult(houseIntroduce);
        return response;
    }

    /**
     * 查询冻品管家工作经历列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/queryHouseWork", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<BS2102116Bean>> queryHouseWork(@RequestBody RsRequest<BS2102116Param> param) {
        RsResponse<PageResult<BS2102116Bean>> response = new RsResponse<PageResult<BS2102116Bean>>();
        BS2102116Param bs2102116Param = param.getParam();
        bs2102116Param.setPaging(false);
        PageResult<BS2102116Bean> result = bs2102115Logic.findWork(bs2102116Param);
        response.setResult(result);
        return response;
    }

    /**
     * 查询冻品管家教育经历列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/queryHouseEducation", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<BS2102117Bean>> queryHouseEducation(@RequestBody RsRequest<BS2102116Param> param) {
        RsResponse<PageResult<BS2102117Bean>> response = new RsResponse<PageResult<BS2102117Bean>>();
        BS2102116Param bs2102116Param = param.getParam();
        bs2102116Param.setPaging(false);
        PageResult<BS2102117Bean> result = bs2102116Logic.findEdu(bs2102116Param);
        response.setResult(result);
        return response;
    }

    /**
     * 查询冻品管家培训经历列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/queryHouseTraining", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<PageResult<BS2102118Bean>> queryHouseTraining(@RequestBody RsRequest<BS2102116Param> param) {
        RsResponse<PageResult<BS2102118Bean>> response = new RsResponse<PageResult<BS2102118Bean>>();
        BS2102116Param bs2102116Param = param.getParam();
        bs2102116Param.setPaging(false);
        PageResult<BS2102118Bean> result = bs2102117Logic.findTrain(bs2102116Param);
        response.setResult(result);
        return response;
    }

    /**
     * 查询冻品管家工作经历信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/queryWorkInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BS2102116Bean> queryWorkInfo(@RequestBody RsRequest<Long> param) {
        RsResponse<BS2102116Bean> response = new RsResponse<BS2102116Bean>();
        Long workId = param.getParam();
        BS2102116Bean result = bs2102115Logic.findWorkById(workId);
        response.setResult(result);
        return response;
    }

    /**
     * 查询冻品管家教育经历信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/queryEducationInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BS2102117Bean> queryEducationInfo(@RequestBody RsRequest<Long> param) {
        RsResponse<BS2102117Bean> response = new RsResponse<BS2102117Bean>();
        Long eduId = param.getParam();
        BS2102117Bean result = bs2102116Logic.findEduById(eduId);
        response.setResult(result);
        return response;
    }

    /**
     * 查询冻品管家培训经历信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/queryTrainingInfo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BS2102118Bean> queryTrainingInfo(@RequestBody RsRequest<Long> param) {
        RsResponse<BS2102118Bean> response = new RsResponse<BS2102118Bean>();
        Long trainId = param.getParam();
        BS2102118Bean result = bs2102117Logic.findTrainById(trainId);
        response.setResult(result);
        return response;
    }

    /**
     * 更新冻品管家介绍信息（可用于逻辑删除）
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/updateSlHouseIntroduce", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> updateSlHouseIntroduce(@RequestBody RsRequest<SlHouseIntroduce> param, HttpServletRequest request) throws IOException {
        RsResponse<String> response = new RsResponse<String>();
        SlHouseIntroduce introduce = param.getParam();

        String uploadUrl1 = introduce.getUploadUrl1();
        String uploadUrl2 = introduce.getUploadUrl2();
        String uploadUrl3 = introduce.getUploadUrl3();
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
        if (!StringUtil.isNullOrEmpty(uploadUrl3)) {
            String imgUrl = this.uploadImg(uploadUrl3, request);
            introduce.setUploadUrl3(imgUrl);
            introduce.setUploadUrl3Type("0");
        }
        Date newDate = DateTimeUtil.getCustomerDate();
        introduce.setUpdTime(newDate);
        introduce.setUpdId("买手APP");
        introduce.setCrtTime(newDate);
        introduce.setCrtId("买手APP");
        introduce.setActTime(newDate);
        introduce.setActId("买手APP");
        int num = iba2141203RsLogic.modifyIntroduce(introduce);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("保存成功");
        }
        return response;
    }

    /**
     * 保存冻品管家工作经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/saveHouseWork", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> saveHouseWork(@RequestBody RsRequest<SlHouseWork> param) {
        RsResponse<String> response = new RsResponse<String>();
        SlHouseWork slHouseWork = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        slHouseWork.setUpdTime(newDate);
        slHouseWork.setUpdId("买手APP");
        slHouseWork.setCrtTime(newDate);
        slHouseWork.setCrtId("买手APP");
        slHouseWork.setActTime(newDate);
        slHouseWork.setActId("买手APP");
        int num = iba2141203RsLogic.saveSlHouseWork(slHouseWork);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("保存成功");
        }
        return response;
    }

    /**
     * 更新冻品管家工作经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/modifyHouseWork", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> modifyHouseWork(@RequestBody RsRequest<SlHouseWork> param) {
        RsResponse<String> response = new RsResponse<String>();
        SlHouseWork slHouseWork = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        slHouseWork.setUpdTime(newDate);
        slHouseWork.setUpdId("买手APP");
        if (StringUtil.isNullOrEmpty(slHouseWork.getHouseCode())) {
            slHouseWork.setHouseCode(slHouseWork.getActId());
        }
        int num = bs2102115Logic.modifyWork(slHouseWork);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("修改成功");
        }
        return response;
    }

    /**
     * 删除冻品管家工作经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/delHouseWork", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> delHouseWork(@RequestBody RsRequest<BS2102116Bean> param) {
        RsResponse<String> response = new RsResponse<String>();
        BS2102116Bean bs2102116Bean = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        bs2102116Bean.setUpdTime(newDate);
        bs2102116Bean.setUpdId("买手APP");
        int num = bs2102115Logic.delWork(bs2102116Bean);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("删除成功");
        }
        return response;
    }

    /**
     * 保存冻品管家教育经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/saveHouseEducation", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> saveHouseEducation(@RequestBody RsRequest<SlHouseEducation> param) {
        RsResponse<String> response = new RsResponse<String>();
        SlHouseEducation slHouseEducation = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        slHouseEducation.setUpdTime(newDate);
        slHouseEducation.setUpdId("买手APP");
        slHouseEducation.setCrtTime(newDate);
        slHouseEducation.setCrtId("买手APP");
        slHouseEducation.setActTime(newDate);
        slHouseEducation.setActId("买手APP");
        int num = iba2141203RsLogic.saveSlHouseEducation(slHouseEducation);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("保存成功");
        }
        return response;
    }

    /**
     * 更新冻品管家教育经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/modifyHouseEducation", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> modifyHouseEducation(@RequestBody RsRequest<SlHouseEducation> param) {
        RsResponse<String> response = new RsResponse<String>();
        SlHouseEducation slHouseEducation = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        slHouseEducation.setUpdTime(newDate);
        slHouseEducation.setUpdId("买手APP");
        int num = bs2102116Logic.modifyEdu(slHouseEducation);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("修改成功");
        }
        return response;
    }

    /**
     * 删除冻品管家教育经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/delHouseEducation", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> delHouseEducation(@RequestBody RsRequest<BS2102117Bean> param) {
        RsResponse<String> response = new RsResponse<String>();
        BS2102117Bean bs2102117Bean = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        bs2102117Bean.setUpdTime(newDate);
        bs2102117Bean.setUpdId("买手APP");
        int num = bs2102116Logic.delEdu(bs2102117Bean);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("删除成功");
        }
        return response;
    }

    /**
     * 保存冻品管家培训经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/saveHouseTraining", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> saveHouseTraining(@RequestBody RsRequest<SlHouseTraining> param) {
        RsResponse<String> response = new RsResponse<String>();
        SlHouseTraining slHouseTraining = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        slHouseTraining.setUpdTime(newDate);
        slHouseTraining.setUpdId("买手APP");
        slHouseTraining.setCrtTime(newDate);
        slHouseTraining.setCrtId("买手APP");
        slHouseTraining.setActTime(newDate);
        slHouseTraining.setActId("买手APP");
        int num = iba2141203RsLogic.saveSlHousTraining(slHouseTraining);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("保存成功");
        }
        return response;
    }

    /**
     * 更新冻品管家培训经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/modifyHouseTraining", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> modifyHouseTraining(@RequestBody RsRequest<SlHouseTraining> param) {
        RsResponse<String> response = new RsResponse<String>();
        SlHouseTraining slHouseTraining = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        slHouseTraining.setUpdTime(newDate);
        slHouseTraining.setUpdId("买手APP");
        int num = bs2102117Logic.modifyTrain(slHouseTraining);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("修改成功");
        }
        return response;
    }

    /**
     * 删除冻品管家培训经历
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ba/delHouseTraining", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> delHouseTraining(@RequestBody RsRequest<BS2102118Bean> param) {
        RsResponse<String> response = new RsResponse<String>();
        BS2102118Bean bs2102118Bean = param.getParam();
        Date newDate = DateTimeUtil.getCustomerDate();
        bs2102118Bean.setUpdTime(newDate);
        bs2102118Bean.setUpdId("买手APP");
        int num = bs2102117Logic.delTrain(bs2102118Bean);
        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("删除成功");
        }
        return response;
    }

//Add for 冻品管家自我介绍 at 2016/09/19 by ni_shaotang End

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
}
