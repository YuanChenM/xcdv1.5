package com.msk.seller.bean;

import com.msk.core.entity.SlEcTeam;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public class ISL231145RsResult {
    /** 状态 */
    private String status;
    /** 返回代码 */
    private String returnCode;
    /** 结果消息 */
    private String message;
    /**电商团队list*/
    private List<SlEcTeam> slEcTeamList;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SlEcTeam> getSlEcTeamList() {
        return slEcTeamList;
    }

    public void setSlEcTeamList(List<SlEcTeam> slEcTeamList) {
        this.slEcTeamList = slEcTeamList;
    }
}
