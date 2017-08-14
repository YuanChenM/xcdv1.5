package com.msk.buyers.controller;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.br.bean.BY121422Param;
import com.msk.buyers.bean.BY121322Bean;
import com.msk.buyers.bean.BY121322Param;
import com.msk.buyers.bean.BY121322RsParam;
import com.msk.buyers.bean.BY121322RsResult;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.excel.JxlsExcelWrite;
import com.msk.common.utils.FileUploadUtil;
import com.msk.common.utils.FileUtils;
import com.msk.core.entity.BrByPoolFileInfo;
import com.msk.district.bean.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.sso.client.excption.SystemException;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by jiang_zhenping on 2016/8/23.
 */

@Controller
@RequestMapping("BY121324")
public class BY121324Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = getLogger(BY121324Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId, Model model) {
        model.addAttribute("buyerId", buyerId);
        //查询所有物流区
        DistrictParam districtParam = new DistrictParam();
        List<LgcsAreaBean> logisticsAreaList = RestCommUtil.getLogisticsAreaList(districtParam).getResult().getLgcsAreaList();
        model.addAttribute("logisticsAreaList", logisticsAreaList);
        PDInfoParam pdInfoParam = new PDInfoParam();
        pdInfoParam.setType(NumberConst.IntDef.INT_ONE);
        List<PDInfoResult> classesList = RestCommUtil.getPdClassesList(pdInfoParam).getResult().getResult();
        if (!CollectionUtils.isEmpty(classesList)) {
            //传参数
            PDInfoParam machiningParam = new PDInfoParam();
            String classesCode = classesList.get(NumberConst.IntDef.INT_ZERO).getClassesCode();
            machiningParam.setClassesCode(classesCode);
            machiningParam.setType(NumberConst.IntDef.INT_TWO);
            List<PDInfoResult> machining = RestCommUtil.getPdClassesList(machiningParam).getResult().getResult();
            model.addAttribute("classesList", classesList);
            model.addAttribute("machiningList", machining);
        } else {
            model.addAttribute("classesList", null);
            model.addAttribute("machiningList", null);
        }
        return "buyers/BY121324";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<BrByPoolFileInfo> search(BY121422Param param) {
        PageResult<BrByPoolFileInfo> result = new PageResult();
        RsRequest<BY121422Param> fileInfoRequest = new RsRequest<BY121422Param>();
        fileInfoRequest.setSiteCode("1");
        fileInfoRequest.setAuth("MSK00001");
        fileInfoRequest.setLoginId("msk01");
        fileInfoRequest.setParam(param);
        return result;
    }

    /**
     * 物流区变更，获取城市下拉框数据
     *
     * @param lgcsAreaCode
     * @return
     */
    @RequestMapping(value = "lgcsAreaChange/{lgcsAreaCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<CityBean> findCityList(@PathVariable("lgcsAreaCode") String lgcsAreaCode) {
        List<CityBean> cityList = null;
        if (!StringUtil.isNullOrEmpty(lgcsAreaCode)) {
            logger.info("开始调取城市列表接口");
            DistrictParam districtParam = new DistrictParam();
            districtParam.setLgcsAreaCode(lgcsAreaCode);
            districtParam.setIsLoadCity(NumberConst.IntDef.INT_ZERO);
            districtParam.setFlag(NumberConst.IntDef.INT_ZERO);
            RsResponse<DistrictResult> citys = RestCommUtil.getCityList(districtParam);
            cityList = citys.getResult().getCityList();
            logger.info("城市列表接口调取结束");
        }
        return cityList;

    }


    /**
     * 城市变更，获取区县下拉框数据
     *
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "cityChange/{cityCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<DistrictBean> findDistrictList(@PathVariable("cityCode") String cityCode) {
        List<DistrictBean> districtList = null;
        if (!StringUtil.isNullOrEmpty(cityCode)) {
            DistrictParam districtParam = new DistrictParam();
            districtParam.setCityCode(cityCode);
            districtParam.setIsLoadDistrict(NumberConst.IntDef.INT_ZERO);
            districtParam.setFlag(NumberConst.IntDef.INT_ZERO);
            RsResponse<DistrictResult> districts = RestCommUtil.getDistrictList(districtParam);
            districtList = districts.getResult().getDistrictList();
        }
        return districtList;
    }

    /**
     * 一级分类下拉框变更，获取二级分类下拉框
     *
     * @param classesCode
     * @return
     */
    @RequestMapping(value = "classesChange/{classesCode}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    List<PDInfoResult> findMachiningList(@PathVariable("classesCode") String classesCode) {
        List<PDInfoResult> machiningList = null;
        if (!StringUtil.isEmpty(classesCode)) {
            //传参数
            PDInfoParam machiningParam = new PDInfoParam();
            machiningParam.setCode(classesCode);
            machiningParam.setType(NumberConst.IntDef.INT_TWO);
            RsResponse<ProductBeanResult> pdClassesList = RestCommUtil.getPdClassesList(machiningParam);
            machiningList = pdClassesList.getResult().getResult();
        }
        return machiningList;
    }


    /**
     * 文件读写生成Excel文件
     * Created by jiang_zhenping
     * @param param
     */
    @RequestMapping(value = "createExcel", method = RequestMethod.POST)
    @ResponseBody
    public int createExcel(BY121322Param param) {
        int result = 0;
        List<BY121322Bean> list = new ArrayList<BY121322Bean>();
        param.setSearchType("0");
        BY121322RsParam rsParam = new BY121322RsParam();
        BeanUtils.copyProperties(param, rsParam);
        rsParam.setPageCount(param.getPageSize());
        rsParam.setPageNo((param.getStartPos() / param.getPageSize()) + 1);
        RsResponse<BY121322RsResult> by121322RsResult = RestCommUtil.getSaleMarketControlInfo(rsParam);
        if (by121322RsResult.getResult().getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            list = by121322RsResult.getResult().getSlHouseSaleList();
            result = 1;
        }
        //生成excel并上传
        Date date = new Date();
        long time = date.getTime();
        String fileId = "分销买家营销期冻品管家营销信息管控表"+ time;
        String fileServerIp = SystemServerManager.CommonServerManager.getMskFlieDownLoadServers();
        //拼装数据
        Map<String, Object> map = new HashMap<>();
        System.out.println("输出EXCEL");
        map.put("byMarketingsHouseList", list);
        map.put("title", "分销买家营销期冻品管家营销信息管控表");
        InputStream in = null;
        OutputStream out = null;
        String inputPath = null;
        if (result == 0) {
            //分销买家营销期冻品管家模板
            inputPath = "excel/template/BY121322.xlsx";
        }
        String outputPath = FileUtils.getSystemTmpDir() + "/" + fileId + ".xlsx";
        try {
            File excelFile = new File(outputPath);
            in = getClass().getClassLoader().getResourceAsStream(inputPath);
            out = new FileOutputStream(excelFile);
            JxlsExcelWrite jew = new JxlsExcelWrite(in, out);
            jew.excelWrite(map);
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put(fileId, excelFile);
            Map<String, String> resultExcl = FileUploadUtil.uploadFiles(fileMap);

        } catch (FileNotFoundException e) {
            logger.error("模板文件不存在");
            throw new SystemException("模板文件不存在", e);
        } catch (IOException e) {
            logger.error("IO读写错误");
            throw new SystemException("IO读写错误");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("输入流关闭错误");
                    throw new SystemException("输入流关闭错误");
                }
            }
            if (out != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("输出流关闭错误");
                    throw new SystemException("输出流关闭错误");
                }
            }
        }

        return result;
    }
}
