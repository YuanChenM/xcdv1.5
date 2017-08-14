/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.bms.ssc.bean.seller;

import com.msk.core.entity.SlEcTeam;

import java.util.List;

/**
 * 企业管理团队bean
 */

public class SlEcTeamBean extends SlEcTeam{

    //负责人图片路径
    private String leaderPath;
    //团队成员
    private List noLeaderPath;
    //团队成员描述
    private String noLeaderDeil;
    /**成员图片路径*/
    private String imagePath;

    public String getLeaderPath() {
        return leaderPath;
    }

    public void setLeaderPath(String leaderPath) {
        this.leaderPath = leaderPath;
    }

    public List getNoLeaderPath() {
        return noLeaderPath;
    }

    public void setNoLeaderPath(List noLeaderPath) {
        this.noLeaderPath = noLeaderPath;
    }

    public String getNoLeaderDeil() {
        return noLeaderDeil;
    }

    public void setNoLeaderDeil(String noLeaderDeil) {
        this.noLeaderDeil = noLeaderDeil;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
