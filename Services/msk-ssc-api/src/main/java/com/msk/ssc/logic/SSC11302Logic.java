package com.msk.ssc.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.ssc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * <p/>
 * Created by zhao_chen1 on 2016/6/30.
 */
@Service
public class SSC11302Logic extends BaseLogic {

    private static Logger logger = LoggerFactory.getLogger(SSC11302Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    @Autowired
    public CommonLogic commonLogic;

    interface SqlId {
        String DELETE_PRODUCT = "deleteProduct";
        String MODIFY_PRODUCT = "modifyProduct";
        String MODIFY_BID_STATUS = "modifyBidStatus";
        String INSERT_BID_BASIC_INFO = "insertBidBasicInfo";
        String INSERT_BID_PRODUCT_DETAIL = "insertBidProductDetail";
        String MODIFY_BID_PRODUCT_DETAIL = "modifyBidProductDetail";
        String MODIFY_BASIC_BID_INFO = "modifyBasicBidInfo";
        String FIND_MAX_BID_PROJECT_NO = "findMaxBidProjectNo";
        String FIND_CONTRACT_COUNT_BID = "findContractCountByBidId";
        String FIND_BID_BASE_BY_BID  = "findBidBaseByBid";
        String SQL_ID_FIND_PD_DETAIL = "findPD";
        String SQL_ID_FIND_BID_BY_BID_PROJECT_NO = "findBidIdByBidProjectNo";

    }

    /**
     * 中标基础表：根据主键查询
     * @param ssc11301RsBean
     * @return
     */
    public SSC11301RsBean findBidBaseByBidId(SSC11301RsBean ssc11301RsBean){
        return super.findOne(SqlId.FIND_BID_BASE_BY_BID,ssc11301RsBean);
    }


    /**
     * 中标确认书详细页面删除产品数据接口Logic
     *
     * @param ssc11302Param
     * @return
     */
    @Transactional
    public int deleteProduct(SSC11302Param ssc11302Param) {
        logger.info("删除");
        //检查数据版本是否正确
        super.versionValidator("SSC_BID_PRODUCT_DETAIL",new String[]{"DETAIL_ID"},new Object[]{ssc11302Param.getDetailId()},ssc11302Param.getVer());
        return super.modify(SqlId.DELETE_PRODUCT, ssc11302Param);
    }

    /**
     * 根据bidId查询是否生成合同
     * @param param
     * @return
     */
    public Integer  findContractCountByBidId(SSC11301RsParam param){
       Object result = super.findObject(SqlId.FIND_CONTRACT_COUNT_BID, param);
       if(result!= null){
           return (int)result;
       }
        return null;
    }




    /**
     * table编辑保存Logic
     *
     * @param ssc11302RsBeen
     * @return
     */
    @Transactional
    public int modifyProduct(SSC11302RsBeen ssc11302RsBeen) {
        logger.info("编辑保存");
        //检查数据版本是否正确
        if(ssc11302RsBeen.getVer()!=null){
            super.versionValidator("SSC_BID_PRODUCT_DETAIL",new String[]{"DETAIL_ID"},new Object[]{ssc11302RsBeen.getDetailId()},ssc11302RsBeen.getVer());
        }
        return super.modify(SqlId.MODIFY_PRODUCT, ssc11302RsBeen);
    }


    /**
     * 更改状态
     *
     * @param ssc11301RsBeen
     * @return
     */
    @Transactional
    public int modifyBidStatus(SSC11301RsBean ssc11301RsBeen) {
        logger.info("更改状态");
        //检查数据版本是否正确
        super.versionValidator("SSC_BID_BASIC_INFO",new String[]{"BID_ID"},new Object[]{ssc11301RsBeen.getBidId()},ssc11301RsBeen.getVer());
        return super.modify(SqlId.MODIFY_BID_STATUS, ssc11301RsBeen);
    }

    //1130202迁移过来

    /**
     * 新增中标确认书基本信息表Logic
     *
     * @param ssc11301RsParam
     * @return
     */
    @Transactional
    public int insertBidBasicInfo(SSC11301RsParam ssc11301RsParam) {
        logger.info("新增中标确认书基本信息表");
        return super.save(SqlId.INSERT_BID_BASIC_INFO, ssc11301RsParam);
    }

    /**
     *新增中标确认书详细信息表Logic
     *
     * @param
     * @return
     */
    @Transactional
    public int insertBidProductDetail(SSC11302RsBeen ssc11302RsBeen) {
        logger.info("新增中标确认书详细信息表");
        return super.save(SqlId.INSERT_BID_PRODUCT_DETAIL, ssc11302RsBeen);
    }


    /**
     *修改中标确认书详细信息表Logic
     *
     * @param
     * @return
     */
    @Transactional
    public int modifyBidProductDetail(SSC11302RsBeen ssc11302RsBeen) {
        logger.info("修改中标确认书详细信息表");
        //检查数据版本是否正确
        //super.versionValidator("SSC_BID_PRODUCT_DETAIL",new String[]{"DETAIL_ID"},new Object[]{ssc11302RsBeen.getDetailId()},ssc11302RsBeen.getVer());
        return super.save(SqlId.MODIFY_BID_PRODUCT_DETAIL, ssc11302RsBeen);
    }


    /**
     * 修改basic表
     * @param ssc11301RsParam
     * @return
     */
    @Transactional
    public int modifyBasicBidInfo(SSC11301RsParam ssc11301RsParam) {
        logger.info("修改中标确认书详细基础表");
        //检查数据版本是否正确
        if(ssc11301RsParam.getVer()!=null){
            super.versionValidator("SSC_BID_BASIC_INFO",new String[]{"BID_ID"},new Object[]{ssc11301RsParam.getBidId()},ssc11301RsParam.getVer());
        }
        return super.save(SqlId.MODIFY_BASIC_BID_INFO, ssc11301RsParam);
    }


    /**
     * 从数据看查询最大的 bidProjectNo
     * @return
     */
    @Transactional(readOnly = true)
    public String findMaxBidProjectNo(){
        logger.info("查询招标项目编号");
        return (String) super.findObject(SqlId.FIND_MAX_BID_PROJECT_NO,new BaseParam());
    }



    /**
     * 根据中标编号查询中标产品详细信息
     *
     * @param param
     * @return
     */

    @Transactional(readOnly = true)
    public Long findBidPD(SSC11302Param param) {
        return (Long) super.findObject(SqlId.SQL_ID_FIND_PD_DETAIL, param);
    }



    /**
     *根据中标编号查询中标id
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public Long findBidIdByBidProjectNo(SSC11302Param param) {
        return (Long) super.findObject(SqlId.SQL_ID_FIND_BID_BY_BID_PROJECT_NO, param);
    }

}
