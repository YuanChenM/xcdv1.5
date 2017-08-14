package com.msk.buyers.bean;

/**
 * BY121312Bean.
 *
 */
public class BY121312Bean
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** 文件名称 */
    private String houseName;
    private String lockBeginTime;
    private String lockEndTime;
    private String excBeginTime;
    private String excEndTime;

    public BY121312Bean(){

    }

    public String getHouseName()
    {
        return houseName;
    }

    public void setHouseName(String houseName)
    {
        this.houseName = houseName;
    }

    public String getLockBeginTime()
    {
        return lockBeginTime;
    }

    public void setLockBeginTime(String lockBeginTime)
    {
        this.lockBeginTime = lockBeginTime;
    }

    public String getLockEndTime()
    {
        return lockEndTime;
    }

    public void setLockEndTime(String lockEndTime)
    {
        this.lockEndTime = lockEndTime;
    }

    public String getExcBeginTime()
    {
        return excBeginTime;
    }

    public void setExcBeginTime(String excBeginTime)
    {
        this.excBeginTime = excBeginTime;
    }

    public String getExcEndTime()
    {
        return excEndTime;
    }

    public void setExcEndTime(String excEndTime)
    {
        this.excEndTime = excEndTime;
    }
}
