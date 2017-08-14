/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "IBS2101126RsParam",description = "param")
public class IBS2101126RsParam extends BaseParam {
    @ApiModelProperty(value = "用户帐号")
    private List<IBS2101127RsParam>   userAccountList;

    public List<IBS2101127RsParam> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<IBS2101127RsParam> userAccountList) {
        this.userAccountList = userAccountList;
    }
}
