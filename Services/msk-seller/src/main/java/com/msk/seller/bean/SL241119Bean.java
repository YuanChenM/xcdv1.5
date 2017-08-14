package com.msk.seller.bean;

import com.msk.core.entity.PdMctStd;
import com.msk.core.entity.PdNormsStd;

import java.util.List;

/**
 * 
 * SL241117Bean.
 * @author gyh
 */
public class SL241119Bean extends PdNormsStd {
    /**是否已经选中*/
    private String checkFlag;
    /**卖家产品ID*/
    private Integer slPdPkgId;
    private String slCode;
    private String slPdId;
    /**
     * Getter method for property <tt>slPdPkgId</tt>.
     *
     * @return property value of slPdPkgId
     */
    public Integer getSlPdPkgId() {
        return slPdPkgId;
    }

    /**
     * Setter method for property <tt>slPdPkgId</tt>.
     *
     * @param slPdPkgId value to be assigned to property slPdPkgId
     */
    public void setSlPdPkgId(Integer slPdPkgId) {
        this.slPdPkgId = slPdPkgId;
    }

    /**
     * Getter method for property <tt>checkFlag</tt>.
     *
     * @return property value of checkFlag
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    /**
     * Setter method for property <tt>checkFlag</tt>.
     *
     * @param checkFlag value to be assigned to property checkFlag
     */
    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getSlPdId() {
        return slPdId;
    }

    public void setSlPdId(String slPdId) {
        this.slPdId = slPdId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
