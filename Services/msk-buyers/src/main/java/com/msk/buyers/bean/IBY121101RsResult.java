package com.msk.buyers.bean;


import java.util.List;

/**
 * Created by tao_zhifa on 2016/8/2.
 */
public class IBY121101RsResult {
  private   List<IBY121314RsBean> recTimeList ;
    //最早收货时间
  private   List<String> earliestRecTime ;
    //最晚收货时间
  private   List<List<String>> latestRecTime ;

    public List<IBY121314RsBean> getRecTimeList() {
        return recTimeList;
    }

    public void setRecTimeList(List<IBY121314RsBean> recTimeList) {
        this.recTimeList = recTimeList;
    }

    public List<String> getEarliestRecTime() {
        return earliestRecTime;
    }

    public void setEarliestRecTime(List<String> earliestRecTime) {
        this.earliestRecTime = earliestRecTime;
    }

    public List<List<String>> getLatestRecTime() {
        return latestRecTime;
    }

    public void setLatestRecTime(List<List<String>> latestRecTime) {
        this.latestRecTime = latestRecTime;
    }

}
