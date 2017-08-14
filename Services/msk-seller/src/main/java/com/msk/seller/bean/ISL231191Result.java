package com.msk.seller.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.*;
import com.msk.core.entity.SlEpAgentAuth;
import com.msk.core.entity.SlEpHonor;

import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
@JsonIgnoreProperties(value={"crtId","crtTime","updId","updTime","delFlg","actId","actTime"})
public class ISL231191Result extends BaseEntity {

    /**卖家产品货号*/
    private String slPdArtNo;

    /**
     * Getter method for property <tt>slPdArtNo</tt>.
     *
     * @return property value of slPdArtNo
     */
    public String getSlPdArtNo() {
        return slPdArtNo;
    }

    /**
     * Setter method for property <tt>slPdArtNo</tt>.
     *
     * @param slPdArtNo value to be assigned to property slPdArtNo
     */
    public void setSlPdArtNo(String slPdArtNo) {
        this.slPdArtNo = slPdArtNo;
    }
}
