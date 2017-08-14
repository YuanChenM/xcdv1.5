package com.msk.order.bean.result;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/8/19.
 */
public class ISO151409RestResult implements Serializable {

    private Integer totalCount;

    private Integer totalPage;

    private Integer pageNo;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 退货单信息列表
     */
    private List<ISO151409ReturnInfoRestResult> returnInfos;

    public List<ISO151409ReturnInfoRestResult> getReturnInfos() {
        return returnInfos;
    }

    public void setReturnInfos(List<ISO151409ReturnInfoRestResult> returnInfos) {
        this.returnInfos = returnInfos;
    }
}
