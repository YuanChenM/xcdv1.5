package com.msk.price.bean;

import com.hoperun.core.consts.NumberConst;
import com.msk.common.base.BaseBean;
import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng_hao on 2016/6/6.
 */
public class PricePlateInfoResult  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /*物流区编码*/
    private String lgcsCode;
    /*价盘周期*/
    private String pricePeriod;

    /** 查询结果总数 */
    private int totalCount;
    /** 查询结果总页数 */
    private int totalPage;
    /** 查询结果当前页数 */
    private int pageNo;

    private List<PricePlateInfoBean> searchList ;

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public List<PricePlateInfoBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<PricePlateInfoBean> searchList) {
        this.searchList = searchList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
