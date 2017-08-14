package com.msk.batch.br;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.batch.BaseBatch;
import com.msk.batch.br.logic.BBR121407Logic;
import com.msk.common.bean.RsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhou_yajun on 2016/8/30.
 */
@Component("BBR121407")
public class BBR121407Batch extends BaseBatch {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR121407Batch.class);

    @Autowired
    private BBR121407Logic bbr121407Logic;

    /**
     * 创建Param
     *
     * @param args String[]
     * @return baseParam baseParam
     */
    @Override
    public BaseParam createParam(String[] args) {
        BaseParam param = new BaseParam();
        //设置共通参数
        Date currentDate = DateTimeUtil.getCustomerDate();
        param.setCrtId("admin");
        param.setCrtTime(currentDate);
        param.setActId("admin");
        param.setActTime(currentDate);
        param.setUpdId("admin");
        param.setUpdTime(currentDate);
        return param;
    }

    /**
     * 业务处理
     *
     * @param param
     */
    @Override
    public void doOperate(BaseParam param) {
        RsRequest<BaseParam> request=new RsRequest<>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        //管家找买家
        this.bbr121407Logic.synHkBuyerRelationShip(param);
    }
}
