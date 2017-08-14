package com.msk.bs.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.FileUploadUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.*;
import com.msk.bs.logic.*;
import com.msk.common.base.BaseController;
import com.msk.core.entity.SlHouseAccount;
import com.msk.core.entity.SlHouseIntroduce;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by wang_haichun on 2016/8/19.
 */
@Controller
@RequestMapping(value = "BS2102113")
public class BS2102113Controller extends BaseController {

    @Autowired
    private BS2102113Logic bs2102113Logic;
    @Autowired
    private BS2101107Logic bs2101107Logic;
    @Autowired
    private BS2102115Logic bs2102115Logic;
    @Autowired
    private BS2102116Logic bs2102116Logic;
    @Autowired
    private BS2102117Logic bs2102117Logic;

    @RequestMapping(value = "init",
            method = RequestMethod.POST)
    public String init(Model model, String slCode, String houseCode) {
        model.addAttribute("slCode", slCode);
        model.addAttribute("houseCode", houseCode);
        if (!StringUtil.isNullOrEmpty(slCode) && !StringUtil.isNullOrEmpty(houseCode)) {
            //查询服务心得  感悟理想
            SlHouseIntroduce slHouseIntroduce = new SlHouseIntroduce();
            slHouseIntroduce.setHouseCode(houseCode);

            SlHouseIntroduce houseIntroduce = bs2101107Logic.findIntroduceInfoByHouseCode(slHouseIntroduce);
            if(null != houseIntroduce){
                if(!StringUtil.isNullOrEmpty(houseIntroduce.getUploadUrl1())){
                    houseIntroduce.setUploadUrl1(SystemServerManager.CommonServerManager.getMskFlieDownLoadServers()+houseIntroduce.getUploadUrl1());
                }
                if(!StringUtil.isNullOrEmpty(houseIntroduce.getUploadUrl2())){
                    houseIntroduce.setUploadUrl2(SystemServerManager.CommonServerManager.getMskFlieDownLoadServers()+houseIntroduce.getUploadUrl2());
                }
            }
            model.addAttribute("houseIntroduce", houseIntroduce);
        }
        return "bs/BS2102113";
    }


    @RequestMapping(value = "save",
            method = RequestMethod.POST)
    public
    @ResponseBody
    int save(@RequestBody BS2102113Param bs2102113Param) {
        BaseParam baseParam = new BaseParam();
        super.setCommonParam(baseParam);
        int result = bs2102113Logic.saveResume(bs2102113Param, baseParam);
        return result;
    }


    @RequestMapping(value = "saveExperience", method = RequestMethod.POST)
    public String saveWorkExperience(Model model, String slCode, String houseCode, String pageName, Long workId, Long eduId, Long trainId) {
        model.addAttribute("slCode", slCode);
        model.addAttribute("houseCode", houseCode);
        if (null != workId) {
            BS2102116Bean bs2102116Bean = bs2102115Logic.findWorkById(workId);
            model.addAttribute("work", bs2102116Bean);
        }
        if (null != eduId) {
            BS2102117Bean bs2102117Bean = bs2102116Logic.findEduById(eduId);
            model.addAttribute("edu", bs2102117Bean);
        }
        if (null != trainId) {
            BS2102118Bean bs2102118Bean = bs2102117Logic.findTrainById(trainId);
            model.addAttribute("train", bs2102118Bean);
        }
        return "bs/" + pageName;
    }


    @RequestMapping(value = "initWork",
            method = RequestMethod.POST)
    public String workExperienceInit(Model model, String slCode, String houseCode) {
        model.addAttribute("slCode", slCode);
        model.addAttribute("houseCode", houseCode);
        return "bs/BS2102119";
    }

    @RequestMapping(value = "initEdu",
            method = RequestMethod.POST)
    public String eduExperienceInit(Model model, String slCode, String houseCode) {
        model.addAttribute("slCode", slCode);
        model.addAttribute("houseCode", houseCode);
        return "bs/BS2102120";
    }

    @RequestMapping(value = "initTrain",
            method = RequestMethod.POST)
    public String trainExperienceInit(Model model, String slCode, String houseCode) {
        model.addAttribute("slCode", slCode);
        model.addAttribute("houseCode", houseCode);
        return "bs/BS2102121";
    }

    //操作工作经历
    @RequestMapping(value = "searchWork",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BS2102116Bean> searchWorkExp(BS2102116Param bs2102116Param) {
        PageResult<BS2102116Bean> pageResult = new PageResult<>();
        if (!StringUtil.isNullOrEmpty(bs2102116Param.getSlCode()) && !StringUtil.isNullOrEmpty(bs2102116Param.getHouseCode())) {
            pageResult = bs2102115Logic.findWork(bs2102116Param);
            if (pageResult.getData() != null) {
                List<BS2102116Bean> workList = pageResult.getData();
                for (int i = 0; i < workList.size(); i++) {
                    String time = setDateToString(workList.get(i).getWorkStart(), workList.get(i).getWorkEnd());
                    workList.get(i).setWorkTime(time);
                }
                pageResult.setData(workList);
            }
            return pageResult;
        }
        return pageResult;
    }


    @RequestMapping(value = "delWork", method = RequestMethod.POST)
    public
    @ResponseBody
    int delWorkExp(@RequestBody BS2102116Bean bs2102116Bean) {
        if (bs2102116Bean != null && bs2102116Bean.getWorkId() != null) {
            BaseParam baseParam = new BaseParam();
            super.setCommonParam(baseParam);
            bs2102116Bean.setUpdId(baseParam.getUpdId());
            bs2102116Bean.setUpdTime(new Date());
            int result = bs2102115Logic.delWork(bs2102116Bean);
            return result;
        }
        return 0;
    }


    //操作教育经历
    @RequestMapping(value = "searchEdu",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BS2102117Bean> searchEduExp(BS2102116Param bs2102116Param) {
        PageResult<BS2102117Bean> pageResult = new PageResult<>();
        if (!StringUtil.isNullOrEmpty(bs2102116Param.getSlCode()) && !StringUtil.isNullOrEmpty(bs2102116Param.getHouseCode())) {
            pageResult = bs2102116Logic.findEdu(bs2102116Param);
            if (pageResult.getData() != null) {
                List<BS2102117Bean> eduList = pageResult.getData();
                for (int i = 0; i < eduList.size(); i++) {
                    String time = setDateToString(eduList.get(i).getEduStart(), eduList.get(i).getEduEnd());
                    eduList.get(i).setEduTime(time);
                }
                pageResult.setData(eduList);
            }
            return pageResult;
        }
        return pageResult;
    }

    @RequestMapping(value = "delEdu", method = RequestMethod.POST)
    public
    @ResponseBody
    int delEduExp(@RequestBody BS2102117Bean bs2102117Bean) {
        if (bs2102117Bean != null && bs2102117Bean.getEduId() != null) {
            BaseParam baseParam = new BaseParam();
            super.setCommonParam(baseParam);
            bs2102117Bean.setUpdId(baseParam.getUpdId());
            bs2102117Bean.setUpdTime(new Date());
            return bs2102116Logic.delEdu(bs2102117Bean);
        }
        return 0;
    }


    //操作培训经历
    @RequestMapping(value = "searchTrain",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BS2102118Bean> searchTrainExp(BS2102116Param bs2102116Param) {
        PageResult<BS2102118Bean> pageResult = new PageResult<>();
        if (!StringUtil.isNullOrEmpty(bs2102116Param.getSlCode()) && !StringUtil.isNullOrEmpty(bs2102116Param.getHouseCode())) {
            pageResult = bs2102117Logic.findTrain(bs2102116Param);
            if (pageResult.getData() != null) {
                List<BS2102118Bean> trainList = pageResult.getData();
                for (int i = 0; i < trainList.size(); i++) {
                    String time = setDateToString(trainList.get(i).getTrainStart(), trainList.get(i).getTrainEnd());
                    trainList.get(i).setTrainTime(time);
                }
                pageResult.setData(trainList);
            }
            return pageResult;
        }
        return pageResult;
    }


    @RequestMapping(value = "delTrain", method = RequestMethod.POST)
    public
    @ResponseBody
    int delTrainExp(@RequestBody BS2102118Bean bs2102118Bean) {
        if (bs2102118Bean != null && bs2102118Bean.getTrainId() != null) {
            BaseParam baseParam = new BaseParam();
            super.setCommonParam(baseParam);
            bs2102118Bean.setUpdId(baseParam.getUpdId());
            bs2102118Bean.setUpdTime(new Date());
            return bs2102117Logic.delTrain(bs2102118Bean);
        }
        return 0;
    }


    private String setDateToString(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer str = new StringBuffer();
        if (null != startDate) {
            String start = sdf.format(startDate);
            str.append(start);
        }
        if (null != endDate) {
            String end = sdf.format(endDate);
            str.append("至" + end);
        }
        if (null == startDate && null == endDate) {
            str.append("");
        }
        return str.toString();
    }

}
