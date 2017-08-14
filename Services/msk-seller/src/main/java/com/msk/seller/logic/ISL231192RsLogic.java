package com.msk.seller.logic;


import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.seller.bean.ISL231192Result;
import com.msk.seller.bean.ISL231192RsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pxg on 2016/4/26.
 */
@Service
public class ISL231192RsLogic extends BaseLogic {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ISL231192RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询卖家产品货号对应的详细信息
     * @param param param
     * @return 查询卖家产品货号对应的详细信息
     * @author pxg
     */
    @Transactional(readOnly = true)
    public ISL231192Result queryData(RsRequest<ISL231192RsParam> param) {
        logger.debug("查询卖家产品货号对应的详细信息");
        ISL231192RsParam isl231192RsParam=param.getParam();
        ISL231192Result isl231192Result=new ISL231192Result();
        if(null!=isl231192RsParam){
            BaseParam params=new BaseParam();
            params.setFilter("slCode",isl231192RsParam.getSlCode());
            params.setFilter("slPdArtNo",isl231192RsParam.getSlPdArtNo());
            isl231192Result=super.findOne(params);
            if(null==isl231192Result){
                throw new BusinessException("无对应数据!");
            }
        }
        return isl231192Result;
    }
}
