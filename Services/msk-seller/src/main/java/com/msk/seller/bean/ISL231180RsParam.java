package com.msk.seller.bean;

import com.msk.core.entity.*;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.SlEpHonor;

import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class ISL231180RsParam extends BaseEntity{
    /**卖家账号信息*/
    private com.msk.core.entity.SlAccount slAccount;
    /**卖家基本信息*/
    private SlSeller slSeller;
    /**卖家产品类别*/
    private List<SlPdClasses> pdClassesCodeList;
    /**企业基本资质*/
    private SlEnterpriseBean slEnterprise;
    /**企业专业资质*/
    private List<ISL231127CertInfoList> certInfoList;
    /**企业荣誉*/
    private List<SlEpHonor> slEpHonorList;
    /**企业产品品牌及品牌荣誉*/
    private List<ISL231180SlEpBrandList> slEpBrandList;
    /**卖家产品品牌*/
    private List<ISlPdBrand> slPdBrandList;
    /**企业车间*/
    private List<SlEpWorkshop> slEpWorkshopList;
    /**企业生产能力*/
    private SlEpCap slEpCap;
    /**生产商*/
    private List<ISL231180SLEpAuth> slEpAuthList;
    /**企业管理团队*/
    private List<SlEpManager> slEpManagerList;
    /**卖家电商团队*/
    private List<SlEcTeam> slEcTeamList;
    /**企业检测设备*/
    private List<SlEpDd> slEpDdList;
    /**  创建者ID/更新者ID*/
    private String loginId;
    /**删除标志0：有效，1：删除*/
    private String delFlg;
    /**版本号*/
    private Integer ver;
    /**全体/单个*/
    private Integer insertFlag;
    /**生产商添加标示*/
    private Integer manufactureFlag;

    /**
     * Getter method for property <tt>manufactureFlag</tt>.
     *
     * @return property value of manufactureFlag
     */
    public Integer getManufactureFlag() {
        return manufactureFlag;
    }

    /**
     * Setter method for property <tt>manufactureFlag</tt>.
     *
     * @param manufactureFlag value to be assigned to property manufactureFlag
     */
    public void setManufactureFlag(Integer manufactureFlag) {
        this.manufactureFlag = manufactureFlag;
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
    public SlSeller getSlSeller() {
        return slSeller;
    }

    /**
     * Setter method for property <tt>slSeller</tt>.
     *
     * @param slSeller value to be assigned to property slSeller
     */
    public void setSlSeller(SlSeller slSeller) {
        this.slSeller = slSeller;
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
     * Getter method for property <tt>slEnterprise</tt>.
     *
     * @return property value of slEnterprise
     */
    public SlEnterpriseBean getSlEnterprise() {
        return slEnterprise;
    }

    /**
     * Setter method for property <tt>slEnterprise</tt>.
     *
     * @param slEnterprise value to be assigned to property slEnterprise
     */
    public void setSlEnterprise(SlEnterpriseBean slEnterprise) {
        this.slEnterprise = slEnterprise;
    }

    /**
     * Getter method for property <tt>certInfoList</tt>.
     *
     * @return property value of certInfoList
     */
    public List<ISL231127CertInfoList> getCertInfoList() {
        return certInfoList;
    }

    /**
     * Setter method for property <tt>certInfoList</tt>.
     *
     * @param certInfoList value to be assigned to property certInfoList
     */
    public void setCertInfoList(List<ISL231127CertInfoList> certInfoList) {
        this.certInfoList = certInfoList;
    }

    /**
     * Getter method for property <tt>slEpHonorList</tt>.
     *
     * @return property value of slEpHonorList
     */
    public List<SlEpHonor> getSlEpHonorList() {
        return slEpHonorList;
    }

    /**
     * Setter method for property <tt>slEpHonorList</tt>.
     *
     * @param slEpHonorList value to be assigned to property slEpHonorList
     */
    public void setSlEpHonorList(List<SlEpHonor> slEpHonorList) {
        this.slEpHonorList = slEpHonorList;
    }

    /**
     * Getter method for property <tt>slEpBrandList</tt>.
     *
     * @return property value of slEpBrandList
     */
    public List<ISL231180SlEpBrandList> getSlEpBrandList() {
        return slEpBrandList;
    }

    /**
     * Setter method for property <tt>slEpBrandList</tt>.
     *
     * @param slEpBrandList value to be assigned to property slEpBrandList
     */
    public void setSlEpBrandList(List<ISL231180SlEpBrandList> slEpBrandList) {
        this.slEpBrandList = slEpBrandList;
    }

    /**
     * Getter method for property <tt>slPdBrandList</tt>.
     *
     * @return property value of slPdBrandList
     */
    public List<ISlPdBrand> getSlPdBrandList() {
        return slPdBrandList;
    }

    /**
     * Setter method for property <tt>slPdBrandList</tt>.
     *
     * @param slPdBrandList value to be assigned to property slPdBrandList
     */
    public void setSlPdBrandList(List<ISlPdBrand> slPdBrandList) {
        this.slPdBrandList = slPdBrandList;
    }

    /**
     * Getter method for property <tt>slEpWorkshopList</tt>.
     *
     * @return property value of slEpWorkshopList
     */
    public List<SlEpWorkshop> getSlEpWorkshopList() {
        return slEpWorkshopList;
    }

    /**
     * Setter method for property <tt>slEpWorkshopList</tt>.
     *
     * @param slEpWorkshopList value to be assigned to property slEpWorkshopList
     */
    public void setSlEpWorkshopList(List<SlEpWorkshop> slEpWorkshopList) {
        this.slEpWorkshopList = slEpWorkshopList;
    }

    /**
     * Getter method for property <tt>slEpCap</tt>.
     *
     * @return property value of slEpCap
     */
    public SlEpCap getSlEpCap() {
        return slEpCap;
    }

    /**
     * Setter method for property <tt>slEpCap</tt>.
     *
     * @param slEpCap value to be assigned to property slEpCap
     */
    public void setSlEpCap(SlEpCap slEpCap) {
        this.slEpCap = slEpCap;
    }

    /**
     * Getter method for property <tt>slEpAuthList</tt>.
     *
     * @return property value of slEpAuthList
     */
    public List<ISL231180SLEpAuth> getSlEpAuthList() {
        return slEpAuthList;
    }

    /**
     * Setter method for property <tt>slEpAuthList</tt>.
     *
     * @param slEpAuthList value to be assigned to property slEpAuthList
     */
    public void setSlEpAuthList(List<ISL231180SLEpAuth> slEpAuthList) {
        this.slEpAuthList = slEpAuthList;
    }

    /**
     * Getter method for property <tt>slEpManagerList</tt>.
     *
     * @return property value of slEpManagerList
     */
    public List<SlEpManager> getSlEpManagerList() {
        return slEpManagerList;
    }

    /**
     * Setter method for property <tt>slEpManagerList</tt>.
     *
     * @param slEpManagerList value to be assigned to property slEpManagerList
     */
    public void setSlEpManagerList(List<SlEpManager> slEpManagerList) {
        this.slEpManagerList = slEpManagerList;
    }

    /**
     * Getter method for property <tt>slEcTeamList</tt>.
     *
     * @return property value of slEcTeamList
     */
    public List<SlEcTeam> getSlEcTeamList() {
        return slEcTeamList;
    }

    /**
     * Setter method for property <tt>slEcTeamList</tt>.
     *
     * @param slEcTeamList value to be assigned to property slEcTeamList
     */
    public void setSlEcTeamList(List<SlEcTeam> slEcTeamList) {
        this.slEcTeamList = slEcTeamList;
    }

    /**
     * Getter method for property <tt>loginId</tt>.
     *
     * @return property value of loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Setter method for property <tt>loginId</tt>.
     *
     * @param loginId value to be assigned to property loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * Getter method for property <tt>delFlg</tt>.
     *
     * @return property value of delFlg
     */
    @Override
    public String getDelFlg() {
        return delFlg;
    }

    /**
     * Setter method for property <tt>delFlg</tt>.
     *
     * @param delFlg value to be assigned to property delFlg
     */
    @Override
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * Getter method for property <tt>ver</tt>.
     *
     * @return property value of ver
     */
    @Override
    public Integer getVer() {
        return ver;
    }

    /**
     * Setter method for property <tt>ver</tt>.
     *
     * @param ver value to be assigned to property ver
     */
    @Override
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    /**
     * Getter method for property <tt>insertFlag</tt>.
     *
     * @return property value of insertFlag
     */
    public Integer getInsertFlag() {
        return insertFlag;
    }

    /**
     * Setter method for property <tt>insertFlag</tt>.
     *
     * @param insertFlag value to be assigned to property insertFlag
     */
    public void setInsertFlag(Integer insertFlag) {
        this.insertFlag = insertFlag;
    }

    /**
     * Getter method for property <tt>slEpDdList</tt>.
     *
     * @return property value of slEpDdList
     */
    public List<SlEpDd> getSlEpDdList() {
        return slEpDdList;
    }

    /**
     * Setter method for property <tt>slEpDdList</tt>.
     *
     * @param slEpDdList value to be assigned to property slEpDdList
     */
    public void setSlEpDdList(List<SlEpDd> slEpDdList) {
        this.slEpDdList = slEpDdList;
    }
}
