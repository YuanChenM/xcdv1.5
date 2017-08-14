package com.msk.batch.bean;

/**
 * 页面分页Param.
 */
public class BasePageParam extends BaseParam {

    private static final long serialVersionUID = 1L;

    /** 是否分页 */
    private boolean paging;
    /** 开始行数 */
    private int startPos;
    /** 每页显示的行数 */
    private int pageSize;
    /** 结束行数 */
    private int endPos;

    /**
     * @return the endPos
     */
    public int getEndPos() {
        this.endPos = pageSize;
        return endPos;
    }

    /**
     * @param endPos the endPos to set
     */
    public void setEndPos(int endPos) {
        this.endPos = endPos;
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
     * @return the startPos
     */
    public int getStartPos() {
        return startPos;
    }

    /**
     * @param startPos the startPos to set
     */
    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
