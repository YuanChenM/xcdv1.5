package com.msk.bms.ssc.bean.seller;

import com.msk.bms.ssc.bean.seller.*;
import com.msk.core.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
/*@JsonIgnoreProperties(value={"crtId","crtTime","updId","updTime","delFlg","actId","actTime"})*/
public class ISL231181Result extends BaseEntity{

    //卖家产品类别
    private List<SlPdClasses> pdClassesCodeList;
    //企业专业资质
    private List<SlEpCertBean> certInfoList;
    //企业荣誉信息List
    private List<SlEpHonorBean> slEpHonorList;
    //企业产品品牌
    private List<SlEpBrandBean> slEpBrandList;
    //卖家产品品牌
    private List<SlPdBrandBean> slPdBrandList;
    //企业车间
    private List<SlEpWorkshopBean> slEpWorkshopList;
    //生产商
    private List<SlEpAgentAuth> slEpAuthList;
    //企业管理团队
    private List<SlEpManagerBean> slEpManagerList;
    //卖家电商团队
    private List<SlEcTeamBean> slEcTeamList;
    //卖家账户信息
    private SlAccount slAccount;
    //卖家基本信息
    private SlSellerParam slSeller;
    //企业基本资质
    private SlEnterprise slEnterprise;
    //企业生产能力
    private SlEpCapBean slEpCap;
    //企业检测设备
    private List<SlEpDdBean> slEpDdList;

    //营业执照资质描述
    private String licSpeck;
    //税务登记证资质描述
    private String taxSpeck;
    //组织机构代码证资质描述
    private String orgSpeck;
    //银行开户许可证资质描述
    private String balSpeck;
    //三证合一营业执照资质描述
    private String licTypeSpeck;

    //食品流通许可证_有效期开始
    private Date fdlTermBegin;
    //食品流通许可证_有效期截止
    private Date fdlTermEnd;
    private String fdlTermEnds;
    //分销授权单位
    private String atuhEpName;
    //OEM委托授权单位
    private String atuhEpName2;
    //卖家头像图片路径
    private String basePath;
    //营业执照图片路径
    private String licPath;
    //税务登记证图片路径
    private String taxPath;
    //组织代码证
    private String orgNoPath;
    //银行开户许可证
    private String balPath;
    //三证合一营业执照
    private String licTypePath;
    //食品流通许可证
    private String fdlPath;
    //代理及分销授权书
    private String authPath;
    //OEM委托授权
    private String oemAuthPath;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getLicSpeck() {
        return licSpeck;
    }

    public void setLicSpeck(String licSpeck) {
        this.licSpeck = licSpeck;
    }

    public String getTaxSpeck() {
        return taxSpeck;
    }

    public void setTaxSpeck(String taxSpeck) {
        this.taxSpeck = taxSpeck;
    }

    public String getOrgSpeck() {
        return orgSpeck;
    }

    public void setOrgSpeck(String orgSpeck) {
        this.orgSpeck = orgSpeck;
    }

    public String getBalSpeck() {
        return balSpeck;
    }

    public void setBalSpeck(String balSpeck) {
        this.balSpeck = balSpeck;
    }

    public String getLicTypeSpeck() {
        return licTypeSpeck;
    }

    public void setLicTypeSpeck(String licTypeSpeck) {
        this.licTypeSpeck = licTypeSpeck;
    }

    public List<SlEcTeamBean> getSlEcTeamList() {
        return slEcTeamList;
    }

    public void setSlEcTeamList(List<SlEcTeamBean> slEcTeamList) {
        this.slEcTeamList = slEcTeamList;
    }

    public Date getFdlTermBegin() {
        return fdlTermBegin;
    }

    public void setFdlTermBegin(Date fdlTermBegin) {
        this.fdlTermBegin = fdlTermBegin;
    }

    public Date getFdlTermEnd() {
        return fdlTermEnd;
    }

    public void setFdlTermEnd(Date fdlTermEnd) {
        this.fdlTermEnd = fdlTermEnd;
    }

    public String getFdlTermEnds() {
        return fdlTermEnds;
    }

    public void setFdlTermEnds(String fdlTermEnds) {
        this.fdlTermEnds = fdlTermEnds;
    }

    public String getAtuhEpName() {
        return atuhEpName;
    }

    public void setAtuhEpName(String atuhEpName) {
        this.atuhEpName = atuhEpName;
    }

    public String getAtuhEpName2() {
        return atuhEpName2;
    }

    public void setAtuhEpName2(String atuhEpName2) {
        this.atuhEpName2 = atuhEpName2;
    }

    public String getLicPath() {
        return licPath;
    }

    public void setLicPath(String licPath) {
        this.licPath = licPath;
    }

    public String getTaxPath() {
        return taxPath;
    }

    public void setTaxPath(String taxPath) {
        this.taxPath = taxPath;
    }

    public String getOrgNoPath() {
        return orgNoPath;
    }

    public void setOrgNoPath(String orgNoPath) {
        this.orgNoPath = orgNoPath;
    }

    public String getBalPath() {
        return balPath;
    }

    public void setBalPath(String balPath) {
        this.balPath = balPath;
    }

    public String getLicTypePath() {
        return licTypePath;
    }

    public void setLicTypePath(String licTypePath) {
        this.licTypePath = licTypePath;
    }

    public String getFdlPath() {
        return fdlPath;
    }

    public void setFdlPath(String fdlPath) {
        this.fdlPath = fdlPath;
    }

    public String getAuthPath() {
        return authPath;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }

    public String getOemAuthPath() {
        return oemAuthPath;
    }

    public void setOemAuthPath(String oemAuthPath) {
        this.oemAuthPath = oemAuthPath;
    }

    /**
     * Getter method for property <tt>pdClassesCodeList</tt>.
     *
     * @return property value of pdClassesCodeList
     */
    public List<SlPdClasses> getPdClassesCodeList() {
        return pdClassesCodeList;
    }

    /**
     * Setter method for property <tt>pdClassesCodeList</tt>.
     *
     * @param pdClassesCodeList value to be assigned to property pdClassesCodeList
     */
    public void setPdClassesCodeList(List<SlPdClasses> pdClassesCodeList) {
        this.pdClassesCodeList = pdClassesCodeList;
    }

    /**
     * Getter method for property <tt>certInfoList</tt>.
     *
     * @return property value of certInfoList
     */
    public List<SlEpCertBean> getCertInfoList() {
        return certInfoList;
    }

    /**
     * Setter method for property <tt>certInfoList</tt>.
     *
     * @param certInfoList value to be assigned to property certInfoList
     */
    public void setCertInfoList(List<SlEpCertBean> certInfoList) {
        this.certInfoList = certInfoList;
    }

    public List<SlEpHonorBean> getSlEpHonorList() {
        return slEpHonorList;
    }

    public void setSlEpHonorList(List<SlEpHonorBean> slEpHonorList) {
        this.slEpHonorList = slEpHonorList;
    }

    /**
     * Getter method for property <tt>slEpBrandList</tt>.
     *
     * @return property value of slEpBrandList
     */
    public List<SlEpBrandBean> getSlEpBrandList() {
        return slEpBrandList;
    }

    /**
     * Setter method for property <tt>slEpBrandList</tt>.
     *
     * @param slEpBrandList value to be assigned to property slEpBrandList
     */
    public void setSlEpBrandList(List<SlEpBrandBean> slEpBrandList) {
        this.slEpBrandList = slEpBrandList;
    }


    public List<SlPdBrandBean> getSlPdBrandList() {
        return slPdBrandList;
    }

    public void setSlPdBrandList(List<SlPdBrandBean> slPdBrandList) {
        this.slPdBrandList = slPdBrandList;
    }

    public List<SlEpWorkshopBean> getSlEpWorkshopList() {
        return slEpWorkshopList;
    }

    public void setSlEpWorkshopList(List<SlEpWorkshopBean> slEpWorkshopList) {
        this.slEpWorkshopList = slEpWorkshopList;
    }

    /**
     * Getter method for property <tt>slEpAuthList</tt>.
     *
     * @return property value of slEpAuthList
     */
    public List<SlEpAgentAuth> getSlEpAuthList() {
        return slEpAuthList;
    }

    /**
     * Setter method for property <tt>slEpAuthList</tt>.
     *
     * @param slEpAuthList value to be assigned to property slEpAuthList
     */
    public void setSlEpAuthList(List<SlEpAgentAuth> slEpAuthList) {
        this.slEpAuthList = slEpAuthList;
    }

    public List<SlEpManagerBean> getSlEpManagerList() {
        return slEpManagerList;
    }

    public void setSlEpManagerList(List<SlEpManagerBean> slEpManagerList) {
        this.slEpManagerList = slEpManagerList;
    }


    /**
     * Getter method for property <tt>slAccount</tt>.
     *
     * @return property value of slAccount
     */
    public SlAccount getSlAccount() {
        return slAccount;
    }

    /**
     * Setter method for property <tt>slAccount</tt>.
     *
     * @param slAccount value to be assigned to property slAccount
     */
    public void setSlAccount(SlAccount slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * Getter method for property <tt>slSeller</tt>.
     *
     * @return property value of slSeller
     */
    public SlSellerParam getSlSeller() {
        return slSeller;
    }

    /**
     * Setter method for property <tt>slSeller</tt>.
     *
     * @param slSeller value to be assigned to property slSeller
     */
    public void setSlSeller(SlSellerParam slSeller) {
        this.slSeller = slSeller;
    }

    /**
     * Getter method for property <tt>slEnterprise</tt>.
     *
     * @return property value of slEnterprise
     */
    public SlEnterprise getSlEnterprise() {
        return slEnterprise;
    }

    /**
     * Setter method for property <tt>slEnterprise</tt>.
     *
     * @param slEnterprise value to be assigned to property slEnterprise
     */
    public void setSlEnterprise(SlEnterprise slEnterprise) {
        this.slEnterprise = slEnterprise;
    }


    public SlEpCapBean getSlEpCap() {
        return slEpCap;
    }

    public void setSlEpCap(SlEpCapBean slEpCap) {
        this.slEpCap = slEpCap;
    }

    /**
     * Getter method for property <tt>slEpDdList</tt>.
     *
     * @return property value of slEpDdList
     */
    public List<SlEpDdBean> getSlEpDdList() {
        return slEpDdList;
    }

    /**
     * Setter method for property <tt>slEpDdList</tt>.
     *
     * @param slEpDdList value to be assigned to property slEpDdList
     */
    public void setSlEpDdList(List<SlEpDdBean> slEpDdList) {
        this.slEpDdList = slEpDdList;
    }
}
