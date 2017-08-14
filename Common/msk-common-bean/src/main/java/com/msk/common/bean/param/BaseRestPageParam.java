package com.msk.common.bean.param;

/**
 * Created by jackjiang on 16/8/8.
 */
public class BaseRestPageParam extends BaseRestParam {
    /** 每页数量 */
    private int pageCount;
    /** 查询页数 */
    private int pageNo;
    /** 是否分页 */
    private boolean paging;
    /** 开始行数 */
    private int startPos;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }
}
