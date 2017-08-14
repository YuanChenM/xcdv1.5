package com.msk.bms.ssc.controller;
import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bms.ssc.bean.seller.ISL231181RsParam;
import com.msk.bms.ssc.bean.seller.ISL231181Result;
import com.msk.bms.ssc.bean.seller.*;
import com.msk.bms.ssc.controller.common.BusinessConst;
import com.msk.bms.ssc.controller.common.ISSCRestUtil;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.seller.bean.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import static org.slf4j.LoggerFactory.getLogger;
/**
 * 企业详细信息
 * Created by peng_hao on 2016/9/1.
 */
@Controller
@RequestMapping("SSC11328")
public class SSC11328Controller {

    private static Logger logger = getLogger(SSC11328Controller.class);

    private String url = ConfigManager.getFileServer();//文件服务器路径

    /**
     * 根据slAccount查询企业详细信息,跳转企业详细页面
     */
    @RequestMapping(value = "searchSlDetailInfo", method = RequestMethod.POST)
    public String searchSlDetailInfo(ISL231181RsParam param, Model model) {
        //取得返回结果，拼接页面
        ISL231181Result result = ISSCRestUtil.findEpDetail(param);

        //卖家头像
        String baseFileName = BusinessConst.SLPath.BASE;
        //营业执照
        String licFileName = BusinessConst.SLPath.EPBUS;
        //税务登记证
        String taxFileName = BusinessConst.SLPath.EPTAX;
        //组织代码证
        String orgFileName = BusinessConst.SLPath.EPORG;
        //银行开户许可证
        String banFileName = BusinessConst.SLPath.EPBAN;
        //三证合一营业执照
        String thrFileName = BusinessConst.SLPath.EPTHR;
        //食品流通许可证
        String epfooFileName = BusinessConst.SLPath.EPFOO;
        //代理及分销授权书
        String epageFileName = BusinessConst.SLPath.EPAGE;
        //OEM委托授权
        String epoemFileName = BusinessConst.SLPath.EPOEM;
        //设置epId
        Long epId = 0L;
        //设置epName
        String epName=null;
        //如果企业基本资质为空，只展示卖家信息
        if(result.getSlEnterprise()!=null){
            epId = result.getSlEnterprise().getEpId();
            epName=result.getSlEnterprise().getEpName();

            DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
            String licRegCapital = "";
            if (result.getSlEnterprise().getLicRegCapital() != null) {
                licRegCapital = df.format(result.getSlEnterprise().getLicRegCapital());
            }
            String licPaidinCapital = StringConst.EMPTY;
            if (result.getSlEnterprise().getLicPaidinCapital() != null) {
                licPaidinCapital = df.format(result.getSlEnterprise().getLicPaidinCapital());
            }
            String crtDate = DateTimeUtil.formatDate("yyyy-MM-dd", result.getSlEnterprise().getLicCrtDate());
            String licTermEnd = DateTimeUtil.formatDate("yyyy-MM-dd", result.getSlEnterprise().getLicTermEnd());
            String orgTermEnd = DateTimeUtil.formatDate("yyyy-MM-dd", result.getSlEnterprise().getOrgTermEnd());
            result.setFdlTermEnds(orgTermEnd);

            /**拼接卖家头像图片路径*/
            result.setBasePath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" +  baseFileName +"/"+ result.getSlSeller().getSlAccount()+StringConst.DOT);

            result.setLicSpeck("名称：" + StringUtil.toSafeString(result.getSlEnterprise().getEpName()) + "<br/>营业执照注册号：" + StringUtil.toSafeString(result.getSlEnterprise().getLicNo()) + "<br/>住所：" + StringUtil.toSafeString(result.getSlEnterprise().getLicAddr()) + " </br>经营类型：" + StringUtil.toSafeString(result.getSlEnterprise().getLicBusiType()) + "<br/>经营范围：" + StringUtil.toSafeString(result.getSlEnterprise().getLicBusiScope()) + "</br>法人代表：" + StringUtil.toSafeString(result.getSlEnterprise().getLicLegalPerson()) + "<br/>注册资本：" + result.getSlEnterprise().getLicRegCapital() + "(万元)<br/>实收资本：" + licPaidinCapital + " (万元)</br>成立日期：" + crtDate + "<br/>营业期限：" + licTermEnd);
            /**拼接营业执照图片路径*/
            result.setLicPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + result.getSlEnterprise().getEpId() + "/" + licFileName + StringConst.DOT);
            result.setTaxSpeck("税务登记证号：" + StringUtil.toSafeString(result.getSlEnterprise().getTaxNo()) + "<br/>一般增值纳税人资格认定：" + StringUtil.toSafeString(result.getSlEnterprise().getTaxVatNo()));
            /**拼接税务等级证图片路径*/
            result.setTaxPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + result.getSlEnterprise().getEpId() + "/" + taxFileName + StringConst.DOT);
            result.setOrgSpeck("代码：" + result.getSlEnterprise().getOrgNo());
            /**拼接组织代码证图片路径*/
            result.setOrgNoPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + result.getSlEnterprise().getEpId() + "/" + orgFileName + StringConst.DOT);
            result.setBalSpeck("法定代表人：" + StringUtil.toSafeString(result.getSlEnterprise().getBalLegalPerson()) + "<br/>开户银行：" + StringUtil.toSafeString(result.getSlEnterprise().getBalBank()) + "<br/>账号：" + StringUtil.toSafeString(result.getSlEnterprise().getBalAccount()));
            /**拼接银行开户许可证图片路径*/
            result.setBalPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + result.getSlEnterprise().getEpId() + "/" + banFileName + StringConst.DOT);
            result.setLicTypeSpeck("注册号：" + StringUtil.toSafeString(result.getSlEnterprise().getLicNo()) + "<br/>组织机构代码证：" + StringUtil.toSafeString(result.getSlEnterprise().getOrgNo()) + "<br/>税务登记证号：" + StringUtil.toSafeString(result.getSlEnterprise().getTaxNo()) + "<br/>名称：" + StringUtil.toSafeString(result.getSlEnterprise().getEpName()) + "<br/>类型：" + StringUtil.toSafeString(result.getSlEnterprise().getLicBusiType()) + "<br/>住所：" + StringUtil.toSafeString(result.getSlEnterprise().getLicAddr()) + "<br/>法定代表人：" + StringUtil.toSafeString(result.getSlEnterprise().getBalLegalPerson()) + "<br/>注册资本：" + licRegCapital + "<br/>成立日期：" + crtDate + "<br/>营业期限：" + licTermEnd + "<br/>经营范围：" + StringUtil.toSafeString(result.getSlEnterprise().getLicBusiScope()));
            /**拼接三证合一图片路径*/
            result.setLicTypePath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + result.getSlEnterprise().getEpId() + "/" + thrFileName + StringConst.DOT);
            /**拼接食品流通许可证图片路径*/
            result.setFdlPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + result.getSlEnterprise().getEpId() + "/" + epfooFileName + StringConst.DOT);
            /**拼接代理及分销授权书图片路径*/
            result.setAuthPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + result.getSlEnterprise().getEpId() + "/" + epageFileName + StringConst.DOT);
            /**拼接OEM委托授权图片路径*/
            result.setOemAuthPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + result.getSlEnterprise().getEpId() + "/" + epoemFileName + StringConst.DOT);
        }

        //企业专业资质描述
        List<SlEpCertBean> certInfoList = result.getCertInfoList();
        if (!CollectionUtils.isEmpty(certInfoList) ){
            for (int i = 0; i < certInfoList.size(); i++) {
                certInfoList.get(i).setImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + certInfoList.get(i).getEpId() + "/" + BusinessConst.SLPath.CERT + certInfoList.get(i).getCertId() + StringConst.DOT);
            }
        }
        // 企业基本能力描述
        //拼接企业荣誉
        List<SlEpHonorBean> honorBeanList = result.getSlEpHonorList();
        if (!CollectionUtils.isEmpty(honorBeanList)) {
            for (int i = 0; i < honorBeanList.size(); i++) {
                SlEpHonorBean slEpHonorBean = honorBeanList.get(i);
                slEpHonorBean.setAptitudeDesc("发证日期：" + DateTimeUtil.formatDate("yyyy-MM-dd", slEpHonorBean.getCertDate()));
                slEpHonorBean.setCertIssuer("发证单位：" + slEpHonorBean.getCertIssuer());
                slEpHonorBean.setHonorDesc("荣誉描述：" + slEpHonorBean.getHonorDesc());
                slEpHonorBean.setImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + slEpHonorBean.getEpId() + "/" + BusinessConst.SLPath.EPHON + slEpHonorBean.getHonorId() + StringConst.DOT);
            }
        }
        //拼接厂房平面图
        SlEpCapBean epCapBean = result.getSlEpCap();
        if (epCapBean != null) {
            epCapBean.setAptitudeDesc("总资产：" + StringUtil.toSafeString(epCapBean.getFtyAsset()) + "(万元)<br/>占地面积：" + StringUtil.toSafeString(epCapBean.getFtyLandArea()) + "(亩)<br/>厂房面积：" + StringUtil.toSafeString(epCapBean.getFtyFloorArea()) + "(平米)<br/>主要设备：" + StringUtil.toSafeString(epCapBean.getFtyEquipment()) + "<br/>设计产能：" + StringUtil.toSafeString(epCapBean.getFtyDesignCap()) + "<br/>实际产能：" + StringUtil.toSafeString(epCapBean.getFtyActualCap()) + "<br/>外贸销售占比：" + StringUtil.toSafeString(epCapBean.getFtyFtRate()) + "%<br/>直销占比：" + StringUtil.toSafeString(epCapBean.getFtyDsRate()) + "%<br/>代理占比：" + StringUtil.toSafeString(epCapBean.getFtyAsRate()) + "%");
            String fileName = BusinessConst.SLPath.EPWORKSHOP;
            epCapBean.setWorkshopImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epCapBean.getEpId() + "/" + fileName + StringConst.DOT);
        }

        //企业生产车间、设备、产品工艺流程描述
        //拼接车间概括
        List<SlEpWorkshopBean> epWorkshopBeanList = result.getSlEpWorkshopList();
        for (int i = 0; i < epWorkshopBeanList.size(); i++) {
            SlEpWorkshopBean slEpWorkshopBean;
            slEpWorkshopBean = epWorkshopBeanList.get(i);
            slEpWorkshopBean.setWorkshopDesc("1、车间名称：" + StringUtil.toSafeString(slEpWorkshopBean.getWorkshopName()) + "<br/>2、生产产品：" + StringUtil.toSafeString(slEpWorkshopBean.getProduct()) + "<br/>3、工艺流程特点：" + StringUtil.toSafeString(slEpWorkshopBean.getProcess()));
            String fileName = BusinessConst.SLPath.EPWORKSHOPDES + slEpWorkshopBean.getWorkshopId();
            slEpWorkshopBean.setImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + slEpWorkshopBean.getEpId() + "/" + fileName + StringConst.DOT);
        }
        //拼接库容概括
        if (epCapBean != null) {
            epCapBean.setScapDesc("1、原料库容：" + StringUtil.toSafeString(epCapBean.getScapMaterial()) + "(立方)<br/>2、成品库容：" + StringUtil.toSafeString(epCapBean.getScapProduct()) + "(立方)");
            String fileName = BusinessConst.SLPath.EPWAREHOUSE;
            epCapBean.setWarehouseImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epCapBean.getEpId() + "/" + fileName + StringConst.DOT);
        }
        //企业实验室、检测设备及产品质量控制系统描述
        if (epCapBean != null) {
            epCapBean.setAptitudeDesc2("1、面积：" + StringUtil.toSafeString(epCapBean.getLabArea()) + "(平米)<br/>2、功能：" + StringUtil.toSafeString(epCapBean.getLabFunction()) + "<br/>3、投资：" + StringUtil.toSafeString(epCapBean.getLabInvestment()) + "(万元)<br/>4、人员数量：" + StringUtil.toSafeString(epCapBean.getLabMember()) + "(人)");
            epCapBean.setAptitudeDesc3("主要设备及用途：" + StringUtil.toSafeString(epCapBean.getDdEquipment()));
            String fileNameForLab = BusinessConst.SLPath.EPLABORATORY;
            String fileNameForDDEquipment = BusinessConst.SLPath.EPTESTING;
            String fileNameForController = BusinessConst.SLPath.EPQCORGANIZE;
            String fileNameForQuality = BusinessConst.SLPath.EPQUALITY;
            epCapBean.setLabImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epCapBean.getEpId() + "/" + fileNameForLab + StringConst.DOT);
            epCapBean.setDdEquipmentImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epCapBean.getEpId() + "/" + fileNameForDDEquipment + StringConst.DOT);
            epCapBean.setControllerImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epCapBean.getEpId() + "/" + fileNameForController + StringConst.DOT);
            epCapBean.setQualityImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epCapBean.getEpId() + "/" + fileNameForQuality + StringConst.DOT);
        }
        //检测设备描述
        List<SlEpDdBean> slEpDdList = result.getSlEpDdList();
        for (int i = 0; i < slEpDdList.size(); i++) {
            SlEpDdBean slEpDd = slEpDdList.get(i);
            String fileNameForQuality = BusinessConst.SLPath.EPTESTING;
            slEpDd.setSlEpDdPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + slEpDd.getEpId() + "/" + fileNameForQuality + slEpDd.getDdId() + StringConst.DOT /*+ suffixForQuality*/);
        }

        //企业管理团队描述
        List<SlEpManagerBean> slEpManagerList = result.getSlEpManagerList();
        for (SlEpManagerBean bean : slEpManagerList) {
            String fileName = "";
            if ("公司董事长".equals(bean.getMemberDuties())) {
                fileName = BusinessConst.SLPath.BOAMAN;
            }
            if ("公司总经理".equals(bean.getMemberDuties())) {
                fileName = BusinessConst.SLPath.GENMAN;
            }
            if ("公司副总经理".equals(bean.getMemberDuties())) {
                fileName = BusinessConst.SLPath.VICMAN;
            }
            if ("销售部经理".equals(bean.getMemberDuties())) {
                fileName = BusinessConst.SLPath.SALMAN;
            }
            if ("品控部经理".equals(bean.getMemberDuties())) {
                fileName = BusinessConst.SLPath.QCMAN;
            }
            if ("财务部经理".equals(bean.getMemberDuties())) {
                fileName = BusinessConst.SLPath.FINANCE;
            }
            bean.setImage(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + bean.getEpId() + "/" + fileName + StringConst.DOT);
        }

        //卖家电商团队描述
        List<SlEcTeamBean> slEcList = result.getSlEcTeamList();
        List<SlEcTeamBean> slEcTeamMembersList = new ArrayList<SlEcTeamBean>();
        if (!CollectionUtils.isEmpty(slEcList)) {
            for (int i = 0; i < slEcList.size(); i++) {
                SlEcTeamBean bean = slEcList.get(i);
                if (bean.getLeaderFlg().equals("1")) {
                    String fileName = BusinessConst.SLPath.ECTEAM + "01";
                    bean.setImagePath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileName + StringConst.DOT /*+ suffix*/);
                    model.addAttribute("teamLeader", bean);
                } else {
                    bean.setNoLeaderDeil("1、姓名：" + StringUtil.toSafeString(bean.getMemberName()) + "<br/>" + "2、年龄：" + StringUtil.toSafeString(bean.getMemberAge()) + "<br/>3、文化程度：" + StringUtil.toSafeString(bean.getMemberEduc()) + "<br/>4、联系方式：" + StringUtil.toSafeString(bean.getMemberTel()));
                    String fileName = BusinessConst.SLPath.ECTEAM + bean.getMemberId();
                    bean.setImagePath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileName + StringConst.DOT /*+ suffix*/);
                    slEcTeamMembersList.add(bean);
                }
            }
        }
        model.addAttribute("slEcTeamList", slEcTeamMembersList);

        //企业产品品牌描述
        List<SlEpBrandBean> slEpBrandList = result.getSlEpBrandList();
        List<SlEpBrandHonorBean> slEpBrandHonorBean = null;
        for (SlEpBrandBean slEpBrandBean : slEpBrandList) {
            /**品牌路径*/
            String fileName = BusinessConst.SLPath.BRAND + slEpBrandBean.getBrandId();
            slEpBrandBean.setBrandPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileName + StringConst.DOT /*+ suffix*/);
            /**品牌包装路径*/
            String pacFileName = BusinessConst.SLPath.BRANDPAC + slEpBrandBean.getBrandId();
            slEpBrandBean.setBrandPacPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + pacFileName + StringConst.DOT /*+ pacsuffix*/);
            slEpBrandBean.setEpName(epName);
            slEpBrandHonorBean = slEpBrandBean.getSlEpBrandHonorList();
            if (slEpBrandHonorBean != null) {
                for (SlEpBrandHonorBean honorBean : slEpBrandHonorBean) {
                    String fileNameTwo = BusinessConst.SLPath.BRANDHONOR + honorBean.getBrandId();
                    honorBean.setBrandHonorPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileNameTwo + StringConst.DOT /*+ suffix*/);
                    String crtDateStr = DateTimeUtil.formatDate("yyyy-MM-dd", honorBean.getCertDate());
                    honorBean.setCrtDateStr(crtDateStr);
                }
                slEpBrandBean.setSlEpBrandHonorList(slEpBrandHonorBean);
            }
        }
        model.addAttribute("slEpBrandList", slEpBrandList);
        model.addAttribute("result", result);
        this.getProEnterpriseList(result.getSlSeller().getSlCode(), model);
        return "ssc/SSC11328";
    }

    /**
     * 查询是否有生产商列表或者OEM/代理卖家列表
     * @param slCode
     * @param model
     */
    private void getProEnterpriseList(String slCode, Model model) {
        PageResult<ISLEnterpriseRsResult> result = new PageResult<ISLEnterpriseRsResult>();
        ISLEnterpriseRsParam iSLEnterpriseRsParam = new ISLEnterpriseRsParam();
        iSLEnterpriseRsParam.setSlCode(slCode);

        ISLEnterpriseRsParam selfParam=new ISLEnterpriseRsParam();
        selfParam.setPaging(true);
        selfParam.setPageCount(NumberConst.IntDef.INT_ONE);
        selfParam.setPageNo(NumberConst.IntDef.INT_ONE);
        iSLEnterpriseRsParam.setSelfParam(selfParam);

        ISLEnterpriseRsParam oemAgentParam=new ISLEnterpriseRsParam();
        oemAgentParam.setPaging(true);
        oemAgentParam.setPageCount(NumberConst.IntDef.INT_ONE);
        oemAgentParam.setPageNo(NumberConst.IntDef.INT_ONE);
        iSLEnterpriseRsParam.setOemAgentParam(oemAgentParam);

        RsResponse<ISLEnterpriseRsPageResult>  response =ISSCRestUtil.getProEnterpriseList(iSLEnterpriseRsParam);
        if(SystemConst.RsStatus.SUCCESS.equals(response.getStatus())){
            ISLEnterpriseRsPage selfResult = response.getResult().getSelfResult();
            if (selfResult!= null && selfResult.getTotalCount() > NumberConst.IntDef.INT_ZERO) {
                model.addAttribute("hasProducers", true);
            }
            ISLEnterpriseRsPage oemAgentResult = response.getResult().getOemAgentResult();
            if (oemAgentResult!=null && oemAgentResult.getTotalCount() > NumberConst.IntDef.INT_ZERO) {
                model.addAttribute("hasSellers", true);
            }
        } else {
            throw new BusinessException(response.getMessage());
        }
    }
    /**
     * 获取生产商列表
     * @return
     */
    @RequestMapping(value = "searchProducers", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<ISLEnterpriseRsResult> searchProducers(ISL231181RsParam param) {
        PageResult<ISLEnterpriseRsResult> result = new PageResult<ISLEnterpriseRsResult>();
        ISLEnterpriseRsParam iSLEnterpriseRsParam = new ISLEnterpriseRsParam();
        ISLEnterpriseRsParam selfParam=new ISLEnterpriseRsParam();
        selfParam.setPaging(true);
        selfParam.setPageCount(param.getPageSize());
        selfParam.setPageNo((param.getStartPos() / param.getPageSize()) + 1);

        iSLEnterpriseRsParam.setSlCode(param.getSlCode());
        iSLEnterpriseRsParam.setSelfParam(selfParam);

        RsResponse<ISLEnterpriseRsPageResult>  enterpriseList =ISSCRestUtil.getProEnterpriseList(iSLEnterpriseRsParam);

        if(enterpriseList == null || SystemConst.RsStatus.FAIL.equals(enterpriseList.getStatus())){
            result.setData(new ArrayList<ISLEnterpriseRsResult>());
            result.setRecordsTotal(0);
        }else{
            result.setData(enterpriseList.getResult().getSelfResult().getIslEnterpriseList());
            result.setRecordsTotal(enterpriseList.getResult().getSelfResult().getTotalCount());
        }
        return result;
    }

    /**
     * 获取代理卖家/OEM卖家列表
     * @return
     */
    @RequestMapping(value = "searchSellers", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<ISLEnterpriseRsResult> searchSellers(ISL231181RsParam param) {
        PageResult<ISLEnterpriseRsResult> result = new PageResult<ISLEnterpriseRsResult>();
        ISLEnterpriseRsParam iSLEnterpriseRsParam = new ISLEnterpriseRsParam();
        ISLEnterpriseRsParam oemAgentParam=new ISLEnterpriseRsParam();
        oemAgentParam.setPaging(true);
        oemAgentParam.setPageCount(param.getPageSize());
        oemAgentParam.setPageNo((param.getStartPos() / param.getPageSize()) + 1);
        iSLEnterpriseRsParam.setSlCode(param.getSlCode());
        iSLEnterpriseRsParam.setOemAgentParam(oemAgentParam);

        RsResponse<ISLEnterpriseRsPageResult>  enterpriseList =ISSCRestUtil.getProEnterpriseList(iSLEnterpriseRsParam);

        if(enterpriseList == null || SystemConst.RsStatus.FAIL.equals(enterpriseList.getStatus())){
            result.setData(new ArrayList<ISLEnterpriseRsResult>());
            result.setRecordsTotal(0);
        }else{
            result.setData(enterpriseList.getResult().getOemAgentResult().getIslEnterpriseList());
            result.setRecordsTotal(enterpriseList.getResult().getOemAgentResult().getTotalCount());
        }
        return result;
    }


}
