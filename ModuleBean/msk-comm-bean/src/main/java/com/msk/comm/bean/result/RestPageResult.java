package com.msk.comm.bean.result;

/**
 * 带分页的服务接口返回值的基类.
 */
public class RestPageResult extends BaseResult {

    /** serialVersionUID */
    private static final long serialVersionUID = 7994693403397775264L;

    /** 查询结果总数 */
    private int totalCount;
    /** 查询结果总页数 */
    private int totalPage;
    /** 查询结果当前页数 */
    private int pageNo;

    /**
     * setTotalPage
     * @param totalCount int
     * @param pageCount int
     */
    public void setTotalPage(int totalCount, int pageCount) {
        if(pageCount > 0){
            this.totalPage = (totalCount - 1) / pageCount + 1;
        }else{
            this.totalPage = 0;
        }
    }

    /**
     * Get the totalCount.
     *
     * @return totalCount
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    /**
     * Set the totalCount.
     *
     * @param totalCount totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Get the totalPage.
     *
     * @return totalPage
     */
    public int getTotalPage() {
        return this.totalPage;
    }

    /**
     * Set the totalPage.
     *
     * @param totalPage totalPage
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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

}
