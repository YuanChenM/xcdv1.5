package com.msk.bs.bean;

import com.msk.core.entity.BsBasicInfo;
import com.msk.core.entity.SlBsBankaccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 买手账户和基本信息
 * Created by ni_shaotang on 2016/10/28.
 */
@ApiModel(value = "IBS2101137Bean", description = "买手账户和基本信息")
public class IBS2101137Bean extends BsBasicInfo {

    @ApiModelProperty(value = "手机号码")
    private String slTel;

    @ApiModelProperty(value = "买手显示名称")
    private String slShowName;

    @ApiModelProperty(value = "联系人姓名")
    private String slContact;

    @ApiModelProperty(value = "登录密码")
    private String accountPsd;

    @ApiModelProperty(value = "认证状态")
    private Integer authStatus;

    @ApiModelProperty(value = "注册来源")
    private String fromFlg;

    @ApiModelProperty(value = "买手详细地址")
    private String address;

    @ApiModelProperty(value = "微信号码")
    private String webChat;

    @ApiModelProperty(value = "QQ号码")
    private String qq;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "固定电话")
    private String tel;

    @ApiModelProperty(value = "传真号")
    private String fax;

    @ApiModelProperty(value = "身份证号")
    private String cardId;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "一级分类Code")
    private String bsTypeCode1;

    @ApiModelProperty(value = "买手一级分类名称")
    private String bsTypeName1;

    @ApiModelProperty(value = "二级分类Code")
    private String bsTypeCode2;

    @ApiModelProperty(value = "买手二级分类名称")
    private String bsTypeName2;

    @ApiModelProperty(value = "三级分类Code")
    private String bsTypeCode3;

    @ApiModelProperty(value = "买手三级分类名称")
    private String bsTypeName3;

    @ApiModelProperty(value = "银行账号情报列表")
    private List<SlBsBankaccount> bankInfoList  = new ArrayList<SlBsBankaccount>();
    @ApiModelProperty(value = "当前冻品管家数量")
    private Integer stewardNum;
    @ApiModelProperty(value = "当前专属买家数量")
    private Integer buyerNum;
    @ApiModelProperty(value = "店铺ID")
    private String shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "店铺Logo")
    private String shopLogo;
    @ApiModelProperty(value = "经营特色1")
    private String managingCharact1;
    @ApiModelProperty(value = "经营特色2")
    private String managingCharact2;
    @ApiModelProperty(value = "经营特色3")
    private String managingCharact3;


    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public String getSlContact() {
        return slContact;
    }

    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    public String getAccountPsd() {
        return accountPsd;
    }

    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getFromFlg() {
        return fromFlg;
    }

    public void setFromFlg(String fromFlg) {
        this.fromFlg = fromFlg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebChat() {
        return webChat;
    }

    public void setWebChat(String webChat) {
        this.webChat = webChat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBsTypeCode1() {
        return bsTypeCode1;
    }

    public void setBsTypeCode1(String bsTypeCode1) {
        this.bsTypeCode1 = bsTypeCode1;
    }

    public String getBsTypeName1() {
        return bsTypeName1;
    }

    public void setBsTypeName1(String bsTypeName1) {
        this.bsTypeName1 = bsTypeName1;
    }

    public String getBsTypeCode2() {
        return bsTypeCode2;
    }

    public void setBsTypeCode2(String bsTypeCode2) {
        this.bsTypeCode2 = bsTypeCode2;
    }

    public String getBsTypeName2() {
        return bsTypeName2;
    }

    public void setBsTypeName2(String bsTypeName2) {
        this.bsTypeName2 = bsTypeName2;
    }

    public String getBsTypeCode3() {
        return bsTypeCode3;
    }

    public void setBsTypeCode3(String bsTypeCode3) {
        this.bsTypeCode3 = bsTypeCode3;
    }

    public String getBsTypeName3() {
        return bsTypeName3;
    }

    public void setBsTypeName3(String bsTypeName3) {
        this.bsTypeName3 = bsTypeName3;
    }

    public List<SlBsBankaccount> getBankInfoList() {
        return bankInfoList;
    }

    public void setBankInfoList(List<SlBsBankaccount> bankInfoList) {
        this.bankInfoList = bankInfoList;
    }

    public Integer getStewardNum() {
        return stewardNum;
    }

    public void setStewardNum(Integer stewardNum) {
        this.stewardNum = stewardNum;
    }

    public Integer getBuyerNum() {
        return buyerNum;
    }

    public void setBuyerNum(Integer buyerNum) {
        this.buyerNum = buyerNum;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getManagingCharact1() {
        return managingCharact1;
    }

    public void setManagingCharact1(String managingCharact1) {
        this.managingCharact1 = managingCharact1;
    }

    public String getManagingCharact2() {
        return managingCharact2;
    }

    public void setManagingCharact2(String managingCharact2) {
        this.managingCharact2 = managingCharact2;
    }

    public String getManagingCharact3() {
        return managingCharact3;
    }

    public void setManagingCharact3(String managingCharact3) {
        this.managingCharact3 = managingCharact3;
    }
}
