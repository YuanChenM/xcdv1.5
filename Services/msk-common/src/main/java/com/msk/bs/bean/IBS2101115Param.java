package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.BsAccount;
import com.msk.core.entity.BsBasicInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 同步卖家模块买手数据
 * Created by ni_shaotang on 2016/7/29.
 */
@ApiModel(value = "IBS2101115Param",description = "param")
public class IBS2101115Param extends BaseParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用于登录的卖家账号")
    private java.lang.String slAccount;

    @ApiModelProperty(value = "也可以用于登录")
    private java.lang.String slTel;

    @ApiModelProperty(value = "买手显示名称")
    private java.lang.String slShowName;

    @ApiModelProperty(value = "联系人姓名")
    private java.lang.String slContact;

    @ApiModelProperty(value = "加密后的值")
    private java.lang.String accountPsd;

    @ApiModelProperty(value = "图片路径")
    private java.lang.String accountImg;

    @ApiModelProperty(value = "0:未认证,1:认证中,2:已认证")
    private java.lang.Integer authStatus;

    @ApiModelProperty(value = "注册来源")
    private java.lang.String fromFlg;

    @ApiModelProperty(value = "是否删除数据")
    private String delFlg;

    @ApiModelProperty(value = "操作状态 1：插入，2：更新，3删除")
    private String operationFlg;

    @ApiModelProperty(value = "买手详情")
    private BsBasicInfo bsBasicInfo;

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public java.lang.String getSlAccount() {
        return slAccount;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param slAccount 用于登录的卖家账号。
     */
    public void setSlAccount(java.lang.String slAccount) {
        this.slAccount = slAccount;
    }

    /**
     * <p>也可以用于登录。</p>
     *
     * @return the 也可以用于登录
     */
    public java.lang.String getSlTel() {
        return slTel;
    }

    /**
     * <p>也可以用于登录。</p>
     *
     * @param slTel 也可以用于登录。
     */
    public void setSlTel(java.lang.String slTel) {
        this.slTel = slTel;
    }

    /**
     * <p>SL_SHOW_NAME。</p>
     *
     * @return the SL_SHOW_NAME
     */
    public java.lang.String getSlShowName() {
        return slShowName;
    }

    /**
     * <p>SL_SHOW_NAME。</p>
     *
     * @param slShowName SL_SHOW_NAME。
     */
    public void setSlShowName(java.lang.String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * <p>SL_CONTACT。</p>
     *
     * @return the SL_CONTACT
     */
    public java.lang.String getSlContact() {
        return slContact;
    }

    /**
     * <p>SL_CONTACT。</p>
     *
     * @param slContact SL_CONTACT。
     */
    public void setSlContact(java.lang.String slContact) {
        this.slContact = slContact;
    }

    /**
     * <p>加密后的值。</p>
     *
     * @return the 加密后的值
     */
    public java.lang.String getAccountPsd() {
        return accountPsd;
    }

    /**
     * <p>加密后的值。</p>
     *
     * @param accountPsd 加密后的值。
     */
    public void setAccountPsd(java.lang.String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * <p>图片路径。</p>
     *
     * @return the 图片路径
     */
    public java.lang.String getAccountImg() {
        return accountImg;
    }

    /**
     * <p>图片路径。</p>
     *
     * @param accountImg 图片路径。
     */
    public void setAccountImg(java.lang.String accountImg) {
        this.accountImg = accountImg;
    }

    /**
     * <p>0:未认证,1:认证中,2:已认证。</p>
     *
     * @return the 0:未认证,1:认证中,2:已认证
     */
    public java.lang.Integer getAuthStatus() {
        return authStatus;
    }

    /**
     * <p>0:未认证,1:认证中,2:已认证。</p>
     *
     * @param authStatus 0:未认证,1:认证中,2:已认证。
     */
    public void setAuthStatus(java.lang.Integer authStatus) {
        this.authStatus = authStatus;
    }

    /**
     * <p>FROM_FLG。</p>
     *
     * @return the FROM_FLG
     */
    public java.lang.String getFromFlg() {
        return fromFlg;
    }

    /**
     * <p>FROM_FLG。</p>
     *
     * @param fromFlg FROM_FLG。
     */
    public void setFromFlg(java.lang.String fromFlg) {
        this.fromFlg = fromFlg;
    }

    public BsBasicInfo getBsBasicInfo() {
        return bsBasicInfo;
    }

    public void setBsBasicInfo(BsBasicInfo bsBasicInfo) {
        this.bsBasicInfo = bsBasicInfo;
    }

    @Override
    public String getDelFlg() {
        return delFlg;
    }

    @Override
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public String getOperationFlg() {
        return operationFlg;
    }

    public void setOperationFlg(String operationFlg) {
        this.operationFlg = operationFlg;
    }
}
