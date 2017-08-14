package com.msk.comm.job;

import com.msk.batch.BaseBatch;

/**
 * Created by jackjiang on 16/7/5.
 */
public class ProcessThread extends Thread{
    /**运行ID*/
    private Long runId;
    /**Base Batch*/
    private BaseBatch baseBatch;
    /**Batch 记录处理Logic*/
    private String[] paramArray;
    private ProcessThread(){

    }
    public ProcessThread(Long runId, BaseBatch baseBatch, String[] paramArray){
        this.runId = runId;
        this.baseBatch = baseBatch;
        this.paramArray = paramArray;
    }

    @Override
    public void run() {
        this.baseBatch.execution(this.paramArray);
    }
}
