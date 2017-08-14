package com.msk.common.bean;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;

/**
 * 带分页的服务接口参数的基类.
 */
public class RsPageParam extends BaseParam {

    /** serialVersionUID */
    private static final long serialVersionUID = 7994693403397775264L;

    /** 每页数量 */
    private int pageCount;
    /** 查询页数 */
    private int pageNo;
    /** 是否分页 */
    private boolean paging;
    /** 开始行数 */
    private int startPos;

    /**
     * Get the pageCount.
     *
     * @return pageCount
     */
    public int getPageCount() {
        return this.pageCount;
    }

    /**
     * Set the pageCount.
     *
     * @param pageCount pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Get the pageNo.
     *
     * @return pageNo
     */
    public int getPageNo() {
        return this.pageNo;
    }

    /**
     * Set the pageNo.
     *
     * @param pageNo pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }


    /**
     * @return the paging
     */
    public boolean isPaging() {
        return paging;
    }

    /**
     * @param paging the paging to set
     */
    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    /**
     * Getter method for property <tt>startPos</tt>.
     *
     * @return property value of startPos
     */
    public int getStartPos() {
        if(this.getPageNo() > NumberConst.IntDef.INT_ZERO){
            this.startPos = this.getPageCount() * (this.getPageNo() - NumberConst.IntDef.INT_ONE);
        }else{
            this.startPos = NumberConst.IntDef.INT_ZERO;
        }
        return this.startPos;
    }
    public int getPageSize() {
        return this.pageCount;
    }
}
