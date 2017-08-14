package com.msk.seller.controller;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.common.base.BaseController;
import com.msk.common.config.ConfigManager;
import com.msk.core.entity.SlSeller;
import com.msk.seller.bean.*;
import com.msk.seller.logic.SL24110310Logic;
import com.msk.seller.logic.SL241103Logic;
import com.msk.seller.utils.BusinessConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("SL241103")
public class SL241103Controller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SL241103Controller.class);
    @Autowired
    private SL241103Logic sl241103Logic;

    @Autowired
    private SL24110310Logic sl24110310Logic;

    private String selfFlg = null; // 自产性
    private String agentFlg = null;// 代理性
    private String oemFlg = null;// OEM性
    private String url = ConfigManager.getFileServer();//文件服务器路径

    @RequestMapping(value = "init/{epId}/{slCode}",
            method = RequestMethod.POST)
    public String init(@PathVariable(value = "slCode") String slCode, @PathVariable(value = "epId") String epId, Model mav, HttpServletRequest request, String flg) {
//        String showFlg=StringUtil.toSafeString(request.getSession().getAttribute("showFlg"));
//        String showFlgTwo=StringUtil.toSafeString(request.getSession().getAttribute("showFlgTwo"));
//        if(StringUtil.isNullOrEmpty(showFlg) && StringUtil.isNullOrEmpty(showFlgTwo)){
//            request.getSession().setAttribute("showFlg", epId);
//            request.getSession().setAttribute("showFlgTwo", slCode);
//        }
        mav.addAttribute("showFlg", epId);
        mav.addAttribute("showFlgTwo", slCode);

        mav.addAttribute("flg", flg);
        BaseParam param = new BaseParam();
        param.setFilter("epId", epId);
        mav.addAttribute("epId", epId);
        mav.addAttribute("slCode", slCode);

        param.setFilter("epId", epId);
        SlSeller seller = sl241103Logic.findSlSeller(param);
        if (null == seller) {
            seller = new SlSeller();
            seller.setSelfFlg("0");// 自产性标识
            seller.setAgentFlg("0");
            seller.setOemFlg("0");
            throw new BusinessException("企业信息不存在!");
        }
        selfFlg = seller.getSelfFlg();// 自产性标识
        agentFlg = seller.getAgentFlg();
        oemFlg = seller.getOemFlg();
        mav.addAttribute("slMainClass", seller.getSlMainClass());
        mav.addAttribute("selfFlg", selfFlg);
        mav.addAttribute("agentFlg", agentFlg);
        mav.addAttribute("oemFlg", oemFlg);
            /*if(NumberConst.IntDef.INT_THREE==seller.getSlMainClass()){

            }else if(NumberConst.IntDef.INT_TWO==seller.getSlMainClass()){

            }*/
        this.getOem(mav, slCode, epId);
        this.getSE(mav, slCode, epId);
        return "sl/SL241103";
    }

    /**
     * 企业基本信息描述
     *
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @param mav    model
     * @return string
     */
    @RequestMapping(value = "initSL24110301",
            method = RequestMethod.POST)
    public String initSL24110301(@RequestParam(required = false) String slCode, @RequestParam(required = false) String epId, Model mav) {
        BaseParam param = new BaseParam();
        param.setFilter("epId", epId);
        SlSeller seller = sl241103Logic.findSlSeller(param);
        if (null == seller) {
            seller = new SlSeller();
            seller.setSelfFlg("0");// 自产性标识
            seller.setAgentFlg("0");
            seller.setOemFlg("0");
        }
        selfFlg = seller.getSelfFlg();// 自产性标识
        agentFlg = seller.getAgentFlg();
        oemFlg = seller.getOemFlg();
        mav.addAttribute("selfFlg", selfFlg);
        mav.addAttribute("agentFlg", agentFlg);
        mav.addAttribute("oemFlg", oemFlg);

        SL24110301Bean sL24110301Bean = this.slEnterprise(epId, slCode);
        logger.info("企业基本资质执行");
        mav.addAttribute("sL0301Bean", sL24110301Bean);
        return "sl/SL24110301";
    }

    /**
     * 企业专业资质描述
     *
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @param mav    model
     * @return string
     */
    @RequestMapping(value = "initSL24110302",
            method = RequestMethod.POST)
    public String initSL24110302(@RequestParam(required = false) String slCode, @RequestParam(required = false) String epId, Model mav) {
        logger.info("企业专业资质审批执行");
        List<SL24110302Bean> listEpCert = this.slEpCert(mav, slCode, epId);
        mav.addAttribute("listEpCert", listEpCert);
        return "sl/SL24110302";
    }

    /**
     * 企业基本能力描述
     *
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @param mav    model
     * @return string
     */
    @RequestMapping(value = "initSL24110303",
            method = RequestMethod.POST)
    public String initSL24110303(@RequestParam(required = false) String slCode, @RequestParam(required = false) String epId, Model mav) {
        // 企业基本能力描述
        logger.info("企业能力");
        SL24110303Bean dc = this.slAbilityDc(mav, slCode, epId);
        mav.addAttribute("SL24110303Bean", dc);
        return "sl/SL24110303";
    }

    /**
     * 企业生产车间、设备、产品工艺流程描述
     *
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @param mav    model
     * @return string
     */
    @RequestMapping(value = "initSL24110304",
            method = RequestMethod.POST)
    public String initSL24110304(@RequestParam(required = false) String slCode, @RequestParam(required = false) String epId, Model mav) {
        logger.info("企业生产车间、设备、产品工艺流程描述");
        SL24110304Bean slPdWorkShop = this.slPdWorkShop(mav, slCode, epId);
        mav.addAttribute("SL24110304Bean", slPdWorkShop);
        return "sl/SL24110304";
    }

    /**
     * 实验室、检测设备、品控组织架构及质量控制系统图
     *
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @param mav    model
     * @return string
     */
    @RequestMapping(value = "initSL24110305",
            method = RequestMethod.POST)
    public String initSL24110305(@RequestParam(required = false) String slCode, @RequestParam(required = false) String epId, Model mav) {
        logger.info("实验室、检测设备、品控组织架构及质量控制系统图");
        SL2411030302Bean slLaboratory = this.slLaboratory(mav, slCode, epId);
        mav.addAttribute("SL2411030302Bean", slLaboratory);
        return "sl/SL24110305";
    }

    /**
     * 企业管理团队描述
     *
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @param mav    model
     * @return string
     */
    @RequestMapping(value = "initSL24110306",
            method = RequestMethod.POST)
    public String initSL24110306(@RequestParam(required = false) String slCode, @RequestParam(required = false) String epId, Model mav) {
        this.slTeamDs(mav, slCode, epId);
        return "sl/SL24110306";
    }

    /**
     * 企业检测设备
     *
     * @param epId 企业ID
     * @return string
     */
    @RequestMapping(value = "initSL24110312",
            method = RequestMethod.POST)
    public String initSL24110312(@RequestParam(required = false) String epId, Model mav) {
        this.querySlEpDd(epId, mav);
        return "sl/SL24110312";
    }

    /**
     * 卖家电商团队
     *
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @param mav    model
     * @return string
     */
    @RequestMapping(value = "initSL24110307",
            method = RequestMethod.POST)
    public String initSL24110307(@RequestParam(required = false) String slCode, @RequestParam(required = false) String epId, Model mav) {
        List<SL24110307Bean> list = this.slOrTeamDS(mav, slCode, epId);
        logger.info("卖家电商团队执行");
        mav.addAttribute("list", list);
        return "sl/SL24110307";
    }

    /**
     * 产品品牌描述
     *
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @param mav    model
     * @return string
     */
    @RequestMapping(value = "initSL24110308",
            method = RequestMethod.POST)
    public String initSL24110308(@RequestParam(required = false) String slCode, @RequestParam(required = false) String epId, Model mav) {
        this.slProduct(mav, slCode, epId);
        return "sl/SL24110308";
    }

    /**
     * 查询企业基本资质*
     *
     * @return
     * @author pxg
     */
    public SL24110301Bean slEnterprise(String epId, String slCode) {
        SL24110301Bean sl24110301Bean = new SL24110301Bean();
        BaseParam param = new BaseParam();
        param.setFilter("epId", epId);
        param.setFilter("delFlg", "0");
        param.setFilter("slCode", slCode);
        sl24110301Bean = sl241103Logic.findOne(param);
        if (null != sl24110301Bean) {
            String crtDate = DateTimeUtil.formatDate("yyyy-MM-dd", sl24110301Bean.getLicCrtDate());
            String licTermEnd = DateTimeUtil.formatDate("yyyy-MM-dd", sl24110301Bean.getLicTermEnd());
            String orgTermEnd = DateTimeUtil.formatDate("yyyy-MM-dd", sl24110301Bean.getOrgTermEnd());
            sl24110301Bean.setFdlTermEnds(orgTermEnd);
            DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
            String licRegCapital = "";
            if (sl24110301Bean.getLicRegCapital() != null) {
                licRegCapital = df.format(sl24110301Bean.getLicRegCapital());
            }
            String licPaidinCapital = StringConst.EMPTY;
            if (sl24110301Bean.getLicPaidinCapital() != null) {
                licPaidinCapital = df.format(sl24110301Bean.getLicPaidinCapital());
            }
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
               /* String licSuffix=slUploadFile.getSuffixIfExist(uploadFilePath,licFileName);
                String taxSuffix=slUploadFile.getSuffixIfExist(uploadFilePath,taxFileName);
                String orgSuffix=slUploadFile.getSuffixIfExist(uploadFilePath,orgFileName);
                String banSuffix=slUploadFile.getSuffixIfExist(uploadFilePath,banFileName);
                String thrSuffix=slUploadFile.getSuffixIfExist(uploadFilePath,thrFileName);
                String epfooSuffix=slUploadFile.getSuffixIfExist(uploadFilePath,epfooFileName);
                String epageSuffix=slUploadFile.getSuffixIfExist(uploadFilePath,epageFileName);
                String epoemSuffix=slUploadFile.getSuffixIfExist(uploadFilePath,epoemFileName);*/
            sl24110301Bean.setLicSpeck("名称：" + StringUtil.toSafeString(sl24110301Bean.getEpName()) + "<br/>营业执照注册号：" + StringUtil.toSafeString(sl24110301Bean.getLicNo()) + "<br/>住所：" + StringUtil.toSafeString(sl24110301Bean.getLicAddr()) + " </br>经营类型：" + StringUtil.toSafeString(sl24110301Bean.getLicBusiType()) + "<br/>经营范围：" + StringUtil.toSafeString(sl24110301Bean.getLicBusiScope()) + "</br>法人代表：" + StringUtil.toSafeString(sl24110301Bean.getLicLegalPerson()) + "<br/>注册资本：" + licRegCapital + "(万元)<br/>实收资本：" + licPaidinCapital + " (万元)</br>成立日期：" + crtDate + "<br/>营业期限：" + licTermEnd);
            /**拼接营业执照图片路径*/
            sl24110301Bean.setLicPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sl24110301Bean.getEpId() + "/" + licFileName + StringConst.DOT);
            sl24110301Bean.setTaxSpeck("税务登记证号：" + StringUtil.toSafeString(sl24110301Bean.getTaxNo()) + "<br/>一般增值纳税人资格认定：" + StringUtil.toSafeString(sl24110301Bean.getTaxVatNo()));
            /**拼接税务等级证图片路径*/
            sl24110301Bean.setTaxPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sl24110301Bean.getEpId() + "/" + taxFileName + StringConst.DOT);
            sl24110301Bean.setOrgSpeck("代码：" + sl24110301Bean.getOrgNo());
            /**拼接组织代码证图片路径*/
            sl24110301Bean.setOrgNoPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sl24110301Bean.getEpId() + "/" + orgFileName + StringConst.DOT);
            sl24110301Bean.setBalSpeck("法定代表人：" + StringUtil.toSafeString(sl24110301Bean.getBalLegalPerson()) + "<br/>开户银行：" + StringUtil.toSafeString(sl24110301Bean.getBalBank()) + "<br/>账号：" + StringUtil.toSafeString(sl24110301Bean.getBalAccount()));
            /**拼接银行开户许可证图片路径*/
            sl24110301Bean.setBalPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sl24110301Bean.getEpId() + "/" + banFileName + StringConst.DOT);
            sl24110301Bean.setLicTypeSpeck("注册号：" + StringUtil.toSafeString(sl24110301Bean.getLicNo()) + "<br/>组织机构代码证：" + StringUtil.toSafeString(sl24110301Bean.getOrgNo()) + "<br/>税务登记证号：" + StringUtil.toSafeString(sl24110301Bean.getTaxNo()) + "<br/>名称：" + StringUtil.toSafeString(sl24110301Bean.getEpName()) + "<br/>类型：" + StringUtil.toSafeString(sl24110301Bean.getLicBusiType()) + "<br/>住所：" + StringUtil.toSafeString(sl24110301Bean.getLicAddr()) + "<br/>法定代表人：" + StringUtil.toSafeString(sl24110301Bean.getBalLegalPerson()) + "<br/>注册资本：" + licRegCapital + "<br/>成立日期：" + crtDate + "<br/>营业期限：" + licTermEnd + "<br/>经营范围：" + StringUtil.toSafeString(sl24110301Bean.getLicBusiScope()));
            /**拼接三证合一图片路径*/
            sl24110301Bean.setLicTypePath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sl24110301Bean.getEpId() + "/" + thrFileName + StringConst.DOT);
            /**拼接食品流通许可证图片路径*/
            sl24110301Bean.setFdlPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sl24110301Bean.getEpId() + "/" + epfooFileName + StringConst.DOT);
            /**拼接代理及分销授权书图片路径*/
            sl24110301Bean.setAuthPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sl24110301Bean.getEpId() + "/" + epageFileName + StringConst.DOT);
            /**拼接OEM委托授权图片路径*/
            sl24110301Bean.setOemAuthPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sl24110301Bean.getEpId() + "/" + epoemFileName + StringConst.DOT);
        }
        return sl24110301Bean;
    }

    /**
     * 查询企业专业资质审批*
     *
     * @return
     * @author pxg
     */
    public List<SL24110302Bean> slEpCert(Model mv, String slCode, String epId) {
        logger.info("生产商");
        BaseParam param1 = new BaseParam();
        param1.setFilter("epId", epId);
        param1.setFilter("delFlg", "0");
        List<SL24110302Bean> listEpCert = sl241103Logic.findSL24110302Bean(param1);
        if (listEpCert.size() > 0) {
            for (int i = 0; i < listEpCert.size(); i++) {
                String certId = String.valueOf(listEpCert.get(i).getCertId());
                BaseParam param2 = new BaseParam();
                param2.setFilter("certId", certId);
                param2.setFilter("epId", epId);
                param2.setFilter("delFlg", "0");
                listEpCert.get(i).setBeanList(sl241103Logic.findSL24110302_1Bean(param2));
                listEpCert.get(i).setImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + listEpCert.get(i).getEpId() + "/" + BusinessConst.SLPath.CERT + listEpCert.get(i).getCertId() + StringConst.DOT/*+suffix*/);
            }
        }
        return listEpCert;

    }

    /**
     * rwf
     * 企业基本能力描述
     */
    public SL24110303Bean slAbilityDc(Model mv, String slCode, String epId) {

        SL24110303Bean sL24110303Bean = new SL24110303Bean();
        List<SL2411030301Bean> sL2411030301BeanList = new ArrayList<SL2411030301Bean>();
        List<SL2411030302Bean> sL2411030302BeanList = new ArrayList<SL2411030302Bean>();
        if (!StringUtil.isNullOrEmpty(epId)) {
            logger.info("生产商");
            BaseParam param = new BaseParam();
            param.setFilter("epId", epId);
            param.setFilter("delFlg","0");
            sL2411030301BeanList = sl241103Logic.findList("queryList0301", param);
            sL2411030302BeanList = sl241103Logic.findList("queryList0302", param);
            for (int i = 0; i < sL2411030301BeanList.size(); i++) {
                SL2411030301Bean sL2411030301Bean = sL2411030301BeanList.get(i);
                sL2411030301Bean.setAptitudeDesc("发证日期：" + DateTimeUtil.formatDate("yyyy-MM-dd", sL2411030301Bean.getCertDate()));
                sL2411030301Bean.setCertIssuer("发证单位：" + sL2411030301Bean.getCertIssuer());
                sL2411030301Bean.setHonorDesc("荣誉描述：" + sL2411030301Bean.getHonorDesc());
                //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030301Bean.getEpId()+"/";
                //String fileName=BusinessConst.SLPath.EPHON+sL2411030301Bean.getHonorId();
                //SLUploadFile slUploadFile = new SLUploadFile();
                //String suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                sL2411030301Bean.setImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sL2411030301Bean.getEpId() + "/" + BusinessConst.SLPath.EPHON + sL2411030301Bean.getHonorId() + StringConst.DOT /*+ suffix*/);
            }
            for (int i = 0; i < sL2411030302BeanList.size(); i++) {
                SL2411030302Bean sL2411030302Bean = sL2411030302BeanList.get(i);
                sL2411030302Bean.setAptitudeDesc("总资产：" + StringUtil.toSafeString(sL2411030302Bean.getFtyAsset()) + "(万元)<br/>占地面积：" + StringUtil.toSafeString(sL2411030302Bean.getFtyLandArea()) + "(亩)<br/>厂房面积：" + StringUtil.toSafeString(sL2411030302Bean.getFtyFloorArea()) + "(平米)<br/>主要设备：" + StringUtil.toSafeString(sL2411030302Bean.getFtyEquipment()) + "<br/>设计产能：" + StringUtil.toSafeString(sL2411030302Bean.getFtyDesignCap()) + "<br/>实际产能：" + StringUtil.toSafeString(sL2411030302Bean.getFtyActualCap()) + "<br/>外贸销售占比：" + StringUtil.toSafeString(sL2411030302Bean.getFtyFtRate()) + "%<br/>直销占比：" + StringUtil.toSafeString(sL2411030302Bean.getFtyDsRate()) + "%<br/>代理占比：" + StringUtil.toSafeString(sL2411030302Bean.getFtyAsRate()) + "%");
                //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030302Bean.getEpId()+"/";
                String fileName = BusinessConst.SLPath.EPWORKSHOP;
                //SLUploadFile slUploadFile = new SLUploadFile();
                //String suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                sL2411030302Bean.setWorkshopImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sL2411030302Bean.getEpId() + "/" + fileName + StringConst.DOT /*+ suffix*/);
            }
            sL24110303Bean.setsL2411030301BeanList(sL2411030301BeanList);
            sL24110303Bean.setsL2411030302BeanList(sL2411030302BeanList);
            return sL24110303Bean;
        } else {
            return null;
        }

    }

    /**
     * rwf
     * 企业生产车间、设备、产品工艺流程描述
     */
    public SL24110304Bean slPdWorkShop(Model mv, String slCode, String epId) {
        SL24110304Bean sL24110304Bean = new SL24110304Bean();

        List<SL2411030303Bean> sL2411030303BeanList = new ArrayList<>();
        List<SL2411030302Bean> sL2411030302BeanList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(epId)) {
            logger.info("生产商");
            BaseParam param = new BaseParam();
            param.setFilter("epId", epId);
            param.setFilter("delFlg", "0");
            sL2411030303BeanList = sl241103Logic.findList("queryList0401", param);
            sL2411030302BeanList = sl241103Logic.findList("queryList0402", param);
            for (int i = 0; i < sL2411030303BeanList.size(); i++) {
                SL2411030303Bean sL2411030303Bean = sL2411030303BeanList.get(i);
                sL2411030303Bean.setWorkshopDesc("1、车间名称：" + StringUtil.toSafeString(sL2411030303Bean.getWorkshopName()) + "<br/>2、生产产品：" + StringUtil.toSafeString(sL2411030303Bean.getProduct()) + "<br/>3、工艺流程特点：" + StringUtil.toSafeString(sL2411030303Bean.getProcess()));
                //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030303Bean.getEpId()+"/";
                String fileName = BusinessConst.SLPath.EPWORKSHOPDES + sL2411030303Bean.getWorkshopId();
                //SLUploadFile slUploadFile = new SLUploadFile();
                //String suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                sL2411030303Bean.setImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sL2411030303Bean.getEpId() + "/" + fileName + StringConst.DOT/*+suffix*/);
            }
            for (int i = 0; i < sL2411030302BeanList.size(); i++) {
                SL2411030302Bean sL2411030302Bean = sL2411030302BeanList.get(i);
                sL2411030302Bean.setScapDesc("1、原料库容：" + StringUtil.toSafeString(sL2411030302Bean.getScapMaterial()) + "(立方)<br/>2、成品库容：" + StringUtil.toSafeString(sL2411030302Bean.getScapProduct()) + "(立方)");
                // String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030302Bean.getEpId()+"/";
                String fileName = BusinessConst.SLPath.EPWAREHOUSE;
                //SLUploadFile slUploadFile = new SLUploadFile();
                //String suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                sL2411030302Bean.setWarehouseImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sL2411030302Bean.getEpId() + "/" + fileName + StringConst.DOT /*+ suffix*/);
            }
            sL24110304Bean.setsL2411030303BeanList(sL2411030303BeanList);
            sL24110304Bean.setsL2411030302BeanList(sL2411030302BeanList);
            return sL24110304Bean;
        } else {

            return null;
        }

    }

    /**
     * rwf
     * 企业实验室、检测设备及产品质量控制系统描述
     */
    public SL2411030302Bean slLaboratory(Model mv, String slCode, String epId) {
        SL2411030302Bean sL2411030302Bean = new SL2411030302Bean();
        if (!StringUtil.isNullOrEmpty(epId)) {
            BaseParam param = new BaseParam();
            param.setFilter("epId", epId);
            param.setFilter("delFlg", "0");
            sL2411030302Bean = sl241103Logic.findOne("findOneSL2411030302Bean", param);
            if (sL2411030302Bean != null) {
                sL2411030302Bean.setAptitudeDesc("1、面积：" + StringUtil.toSafeString(sL2411030302Bean.getLabArea()) + "(平米)<br/>2、功能：" + StringUtil.toSafeString(sL2411030302Bean.getLabFunction()) + "<br/>3、投资：" + StringUtil.toSafeString(sL2411030302Bean.getLabInvestment()) + "(万元)<br/>4、人员数量：" + StringUtil.toSafeString(sL2411030302Bean.getLabMember()) + "(人)");
                sL2411030302Bean.setAptitudeDesc2("主要设备及用途：" + StringUtil.toSafeString(sL2411030302Bean.getDdEquipment()));
                //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030302Bean.getEpId()+"/";
                String fileNameForLab = BusinessConst.SLPath.EPLABORATORY;
                String fileNameForDDEquipment = BusinessConst.SLPath.EPTESTING;
                String fileNameForController = BusinessConst.SLPath.EPQCORGANIZE;
                String fileNameForQuality = BusinessConst.SLPath.EPQUALITY;
                //SLUploadFile slUploadFile = new SLUploadFile();
                    /*String suffixForLab=slUploadFile.getSuffixIfExist(uploadFilePath,fileNameForLab);
                    String suffixForDDEquipment=slUploadFile.getSuffixIfExist(uploadFilePath,fileNameForDDEquipment);
                    String suffixForController=slUploadFile.getSuffixIfExist(uploadFilePath,fileNameForController);
                    String suffixForQuality=slUploadFile.getSuffixIfExist(uploadFilePath,fileNameForQuality);*/
                sL2411030302Bean.setLabImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sL2411030302Bean.getEpId() + "/" + fileNameForLab + StringConst.DOT /*+ suffixForLab*/);
                sL2411030302Bean.setDdEquipmentImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sL2411030302Bean.getEpId() + "/" + fileNameForDDEquipment + StringConst.DOT /*+ suffixForDDEquipment*/);
                sL2411030302Bean.setControllerImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sL2411030302Bean.getEpId() + "/" + fileNameForController + StringConst.DOT/* + suffixForController*/);
                sL2411030302Bean.setQualityImgUrl(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + sL2411030302Bean.getEpId() + "/" + fileNameForQuality + StringConst.DOT /*+ suffixForQuality*/);
            }
        }
        return sL2411030302Bean;
    }


    /**
     * pxg
     * 检测设备描述
     */
    public List<SlEpDdBean> querySlEpDd(String epId, Model mav) {
        List<SlEpDdBean> slEpDdList = new ArrayList<>();
        List<SlEpDdBean> slEpDdListTwo = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(epId)) {
            BaseParam param = new BaseParam();
            param.setFilter("epId", epId);
            param.setFilter("delFlg", "0");
            slEpDdList = sl241103Logic.findEpEquipment(param);

            for (int i = 0; i < slEpDdList.size(); i++) {
                SlEpDdBean slEpDd = slEpDdList.get(i);
                //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+sL2411030302Bean.getEpId()+"/";
                //String fileNameForLab=BusinessConst.SLPath.EPLABORATORY;
                //String fileNameForDDEquipment=BusinessConst.SLPath.EPTESTING;
                //String fileNameForController=BusinessConst.SLPath.EPQCORGANIZE;
                String fileNameForQuality = BusinessConst.SLPath.EPTESTING;
                //SLUploadFile slUploadFile = new SLUploadFile();
                    /*String suffixForLab=slUploadFile.getSuffixIfExist(uploadFilePath,fileNameForLab);
                    String suffixForDDEquipment=slUploadFile.getSuffixIfExist(uploadFilePath,fileNameForDDEquipment);
                    String suffixForController=slUploadFile.getSuffixIfExist(uploadFilePath,fileNameForController);
                    String suffixForQuality=slUploadFile.getSuffixIfExist(uploadFilePath,fileNameForQuality);*/
                slEpDd.setSlEpDdPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileNameForQuality + slEpDd.getDdId() + StringConst.DOT /*+ suffixForQuality*/);
                slEpDdListTwo.add(slEpDd);
            }
        }
        mav.addAttribute("slEpDdList", slEpDdListTwo);
        return slEpDdListTwo;
    }

    /**
     * 企业管理团队描述
     *
     * @param mv
     * @param slCode
     * @param epId
     * @return
     * @author chen_xin
     */
    public String slTeamDs(Model mv, @RequestParam(required = false) String slCode, @RequestParam(required = false) String epId) {
        logger.info("查询数据库  ,企业管理团队描述操作");
        if ("".equals(slCode) && null == slCode) {
            logger.info("生产商");
        } else {
            logger.info("卖家");
            BaseParam param = new BaseParam();
            if (StringUtil.isNullOrEmpty(epId)) {
                return null;
            } else {
                param.setFilter("epId", epId);
                param.setFilter("delFlg", "0");
                List<SL24110306Bean> list03061 = sl241103Logic.findList("findList0306", param);
                List<SL24110306Bean> list0306 = new ArrayList<SL24110306Bean>();
                if (null != list03061 && !list03061.isEmpty()) {
                    for (SL24110306Bean sl24110306Bean : list03061) {
                        //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epId+"/";
                        String fileName = "";
                        if ("公司董事长".equals(sl24110306Bean.getMemberDuties())) {
                            fileName = BusinessConst.SLPath.BOAMAN;
                        }
                        if ("公司总经理".equals(sl24110306Bean.getMemberDuties())) {
                            fileName = BusinessConst.SLPath.GENMAN;
                        }
                        if ("公司副总经理".equals(sl24110306Bean.getMemberDuties())) {
                            fileName = BusinessConst.SLPath.VICMAN;
                        }
                        if ("销售部经理".equals(sl24110306Bean.getMemberDuties())) {
                            fileName = BusinessConst.SLPath.SALMAN;
                        }
                        if ("品控部经理".equals(sl24110306Bean.getMemberDuties())) {
                            fileName = BusinessConst.SLPath.QCMAN;
                        }
                        if ("财务部经理".equals(sl24110306Bean.getMemberDuties())) {
                            fileName = BusinessConst.SLPath.FINANCE;
                        }
                        //SLUploadFile slUploadFile = new SLUploadFile();
                        //String suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                        sl24110306Bean.setImage(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileName + StringConst.DOT /*+ suffix*/);
                        list0306.add(sl24110306Bean);
                    }
                    mv.addAttribute("list0306", list0306);
                }
            }
        }
        return "sl/SL24110306";
    }

    /**
     * 卖家电商团队描述
     *
     * @param mav    model
     * @param slCode
     * @param epId
     * @return
     * @author pxg
     */
    public List<SL24110307Bean> slOrTeamDS(Model mav, String slCode, String epId) {
        List<SL24110307Bean> list = new ArrayList<SL24110307Bean>();
        SL24110307Bean bean2 = new SL24110307Bean();
        List<SL24110307Bean> beanList = new ArrayList<SL24110307Bean>();
        logger.info("卖家");
        list = sl241103Logic.findEcTeamList(slCode);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                bean2 = list.get(i);
                if (bean2.getLeaderFlg().equals("1")) {
                    //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epId+"/";
                    String fileName = BusinessConst.SLPath.ECTEAM + "01";
                    ///SLUploadFile slUploadFile = new SLUploadFile();
                    //String suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                    bean2.setImagePath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileName + StringConst.DOT /*+ suffix*/);
                    mav.addAttribute("bean", bean2);
                } else {
                    bean2.setNoLeaderDeil("1、姓名：" + StringUtil.toSafeString(bean2.getMemberName()) + "<br/>" + "2、年龄：" + StringUtil.toSafeString(bean2.getMemberAge()) + "<br/>3、文化程度：" + StringUtil.toSafeString(bean2.getMemberEduc()) + "<br/>4、联系方式：" + StringUtil.toSafeString(bean2.getMemberTel()));
                    //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epId+"/";
                    String fileName = BusinessConst.SLPath.ECTEAM + bean2.getMemberId();
                    // SLUploadFile slUploadFile = new SLUploadFile();
                    // suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                    bean2.setImagePath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileName + StringConst.DOT /*+ suffix*/);
                    beanList.add(bean2);
                }
            }
        }
        mav.addAttribute("beanList", beanList);
        return list;
    }

    /**
     * 企业产品品牌描述
     *
     * @param mv     model
     * @param slCode 卖家编码
     * @param epId   企业ID
     * @return String
     * @author chen_xin
     */
    public String slProduct(Model mv, @RequestParam(required = false) String slCode, @RequestParam(required = false) String epId) {
        // 查询品牌信息 品牌证书 包装 品牌荣誉
        logger.info("查询数据库操作");
        if ("".equals(slCode) && null == slCode) {
            logger.info("生产商");
        } else {
            logger.info("卖家");
            BaseParam param = new BaseParam();

            if (StringUtil.isNullOrEmpty(epId)) {
                return null;
            } else {
                param.setFilter("epId", epId);
                param.setFilter("delFlg", "0");
                List<SL24110308Bean> list0308 = sl241103Logic.findList("findList0308", param);
                List<SL2411030801Bean> list030801 = null;
                for (SL24110308Bean sl24110308Bean : list0308) {
                    /**品牌路径*/
                    //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epId+"/";
                    String fileName = BusinessConst.SLPath.BRAND + sl24110308Bean.getBrandId();
                    //SLUploadFile slUploadFile = new SLUploadFile();
                    //String suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                    sl24110308Bean.setBrandPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileName + StringConst.DOT /*+ suffix*/);
                    /**品牌包装路径*/
                    String pacFileName = BusinessConst.SLPath.BRANDPAC + sl24110308Bean.getBrandId();
                    //String pacsuffix=slUploadFile.getSuffixIfExist(uploadFilePath,pacFileName);
                    sl24110308Bean.setBrandPacPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + pacFileName + StringConst.DOT /*+ pacsuffix*/);
                    param.setFilter("brandId", StringUtil.toSafeString(sl24110308Bean.getBrandId()));
                    list030801 = sl241103Logic.findList("findList030801", param);
                    for (SL2411030801Bean sl2411030801Bean : list030801) {
                        //String uploadFilePath=BusinessConst.SLPath.SERVICEIMAGEPATH+"/"+BusinessConst.SLPath.SLIMAGEPATH+"/"+epId+"/";
                        String fileNameTwo = BusinessConst.SLPath.BRANDHONOR + sl2411030801Bean.getBrandId();
                        //SLUploadFile slUploadFile = new SLUploadFile();
                        //String suffix=slUploadFile.getSuffixIfExist(uploadFilePath,fileName);
                        sl2411030801Bean.setBrandHonorPath(url + "/" + BusinessConst.SLPath.SLIMAGEPATH + "/" + epId + "/" + fileNameTwo + StringConst.DOT /*+ suffix*/);
                    }
                    sl24110308Bean.setSl2411030801BeanList(list030801);
                }

                mv.addAttribute("list0308", list0308);
                   /* mv.addAttribute("list030801", list030801);*/
            }
        }
        return "sl/SL24110308";

    }

    /*
     * 生产商列表
     */
    public String getSE(Model mav, String slCode, String epId) {
        mav.addAttribute("slCode", slCode);
        mav.addAttribute("epId", epId);
        return "sl/SL24110309";
    }

    /**
     * @param mav    假数据页面返回model
     * @param slCode 卖家编号
     * @param epId   企业id
     * @return String 页面
     * @author xhy
     */
    public String getOem(Model mav, String slCode, String epId) {
        mav.addAttribute("slCode", slCode);
        mav.addAttribute("epId", epId);
        return "sl/SL24110310";
    }

    /**
     * @param slCode        用户编号
     * @param basePageParam
     * @return PageResult
     * @author xhy
     */
    @RequestMapping(value = "search",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SlEpAgentAuth> search(@RequestParam(required = false) String slCode, BasePageParam basePageParam) {
        logger.info("生产商列表");
        return this.sl241103Logic.findPages(basePageParam, slCode);
    }

    /**
     * @param slCode        用户编号
     * @param basePageParam
     * @return PageResult
     * @author xhy
     */
    @RequestMapping(value = "searchOem",
            method = RequestMethod.POST)
    public
    @ResponseBody
    PageResult<SlEpOemAuth> oEMTrade(@RequestParam(required = false) String slCode, BasePageParam basePageParam) {
        logger.info("OEM列表");
        return sl24110310Logic.findPages(basePageParam, slCode);
    }
}
