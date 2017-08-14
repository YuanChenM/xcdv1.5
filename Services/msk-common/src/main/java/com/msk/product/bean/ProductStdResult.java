package com.msk.product.bean;

import com.msk.core.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 产品档案卡结果集
 * Created by yang_chunyan on 2016/6/17.
 */
@ApiModel(value = "ProductStdResult", description = "result")
public class ProductStdResult implements Serializable {

    @ApiModelProperty(value = "通用质量指标标准")
    private List<GnqStdBean> gnqStdlist ;

    @ApiModelProperty(value = "存储运输指标标准")
    private List<TspStdBean> tspStdlist ;

    @ApiModelProperty(value = "产品等级&卫生质量标准")
    private List<SftStdBean> sftStdlist;

    @ApiModelProperty(value = "饲养指标标准")
    private List<FedStdBean> fedStdlist;

    @ApiModelProperty(value = "产品技术标准")
    private List<MctStdBean> mctStdList;

    @ApiModelProperty(value = "原种种源标准")
    private List<OrgStdBean> orgStdList;

    @ApiModelProperty(value = "产品标准质量标准")
    private List<TncStdBean> tncStdList;

    @ApiModelProperty(value = "原料种源信息")
    private List<PdClassestreeMat> matStdList;

    @ApiModelProperty(value = "买家产品技术标准调研")
    private List<ByResearchStdMct> byResearchStdMcts;

    @ApiModelProperty(value = "买家原种种源标准调研")
    private List<ByResearchStdOrg> byResearchStdOrgs;

    @ApiModelProperty(value = "买家饲养指标标准调研")
    private List<ByResearchStdFed> byResearchStdFeds;

    @ApiModelProperty(value = "买家通用质量指标标准调研")
    private List<ByResearchStdGnq> byResearchStdGnqs;

    @ApiModelProperty(value = "买家存储运输指标标准调研")
    private List<ByResearchStdTsp> byResearchStdTsps;

    @ApiModelProperty(value = "买家产品等级&卫生质量标准调研")
    private List<ByResearchStdSft> byResearchStdSfts;

    @ApiModelProperty(value = "买家产品标准质量标准调研")
    private List<ByResearchStdTnc> byResearchStdTncs;

    @ApiModelProperty(value = "买家产品包装标准质调研")
    private List<ByResearchStdNor> byResearchStdNors;


    public List<GnqStdBean> getGnqStdlist() {
        return gnqStdlist;
    }

    public void setGnqStdlist(List<GnqStdBean> gnqStdlist) {
        this.gnqStdlist = gnqStdlist;
    }

    public List<TspStdBean> getTspStdlist() {
        return tspStdlist;
    }

    public void setTspStdlist(List<TspStdBean> tspStdlist) {
        this.tspStdlist = tspStdlist;
    }

    public List<SftStdBean> getSftStdlist() {
        return sftStdlist;
    }

    public void setSftStdlist(List<SftStdBean> sftStdlist) {
        this.sftStdlist = sftStdlist;
    }

    public List<FedStdBean> getFedStdlist() {
        return fedStdlist;
    }

    public void setFedStdlist(List<FedStdBean> fedStdlist) {
        this.fedStdlist = fedStdlist;
    }

    public List<MctStdBean> getMctStdList() {
        return mctStdList;
    }

    public void setMctStdList(List<MctStdBean> mctStdList) {
        this.mctStdList = mctStdList;
    }

    public List<OrgStdBean> getOrgStdList() {
        return orgStdList;
    }

    public void setOrgStdList(List<OrgStdBean> orgStdList) {
        this.orgStdList = orgStdList;
    }

    public List<TncStdBean> getTncStdList() {
        return tncStdList;
    }

    public void setTncStdList(List<TncStdBean> tncStdList) {
        this.tncStdList = tncStdList;
    }

    public List<PdClassestreeMat> getMatStdList() {
        return matStdList;
    }

    public void setMatStdList(List<PdClassestreeMat> matStdList) {
        this.matStdList = matStdList;
    }

    public List<ByResearchStdMct> getByResearchStdMcts() {
        return byResearchStdMcts;
    }

    public void setByResearchStdMcts(List<ByResearchStdMct> byResearchStdMcts) {
        this.byResearchStdMcts = byResearchStdMcts;
    }

    public List<ByResearchStdOrg> getByResearchStdOrgs() {
        return byResearchStdOrgs;
    }

    public void setByResearchStdOrgs(List<ByResearchStdOrg> byResearchStdOrgs) {
        this.byResearchStdOrgs = byResearchStdOrgs;
    }

    public List<ByResearchStdFed> getByResearchStdFeds() {
        return byResearchStdFeds;
    }

    public void setByResearchStdFeds(List<ByResearchStdFed> byResearchStdFeds) {
        this.byResearchStdFeds = byResearchStdFeds;
    }

    public List<ByResearchStdGnq> getByResearchStdGnqs() {
        return byResearchStdGnqs;
    }

    public void setByResearchStdGnqs(List<ByResearchStdGnq> byResearchStdGnqs) {
        this.byResearchStdGnqs = byResearchStdGnqs;
    }

    public List<ByResearchStdTsp> getByResearchStdTsps() {
        return byResearchStdTsps;
    }

    public void setByResearchStdTsps(List<ByResearchStdTsp> byResearchStdTsps) {
        this.byResearchStdTsps = byResearchStdTsps;
    }

    public List<ByResearchStdSft> getByResearchStdSfts() {
        return byResearchStdSfts;
    }

    public void setByResearchStdSfts(List<ByResearchStdSft> byResearchStdSfts) {
        this.byResearchStdSfts = byResearchStdSfts;
    }

    public List<ByResearchStdTnc> getByResearchStdTncs() {
        return byResearchStdTncs;
    }

    public void setByResearchStdTncs(List<ByResearchStdTnc> byResearchStdTncs) {
        this.byResearchStdTncs = byResearchStdTncs;
    }

    public List<ByResearchStdNor> getByResearchStdNors() {
        return byResearchStdNors;
    }

    public void setByResearchStdNors(List<ByResearchStdNor> byResearchStdNors) {
        this.byResearchStdNors = byResearchStdNors;
    }
}
