package com.msk.buyers.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.ByBuyerPdCla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * IBY121203Logic.
 *
 * @author zhou_yajun
 */
@Service
public class IBY121203Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121203Logic.class);

    @Autowired
    private CommonLogic commonLogic;
    /**
     * SQL Map 中SQL ID定义
     *
     * @author zhou_yajun
     */
    interface SqlId {
        //买家经营产品类别获取
        static String SQL_FIND_PD_CLASSIFICATION = "findPdClassification";
        //买家经营产品类别更新
        static String SQL_BUYER_PD_CLASSIFICATION_MODIFY = "buyerPdClassificationModify";
        //买家经营产品类别插入
        static String SQL_BUYER_PD_CLASSIFICATION_INSERT = "buyerPdClassificationInsert";
        //买家经营产品类别删除
        static String SQL_BUYER_PD_CLASSIFICATION_DELETE = "buyerPdClassificationDelete";
        //买家经营产品类别获取
        static String SQL_FIND_PD_CLASS_LIST = "findPdClassList";
        //验证买家id是否存在
        static String TEST_BUYER_ID = "testBuyerId";
    }
    /**
     * (non-Javadoc)
     *
     * @see BaseLogic#setBaseDao(BaseDao)
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 买家经营产品类别更新接口
     * @param param
     * @return
     */
    @Transactional
    public Integer buyerPdClassificationModify(List<ByBuyerPdCla> param){
        //将DB中买家的数据DEL_FLG全部设置为1
        if (CollectionUtils.isEmpty(param)){
            return 0;
        }
        Integer returnNumber= 0;
        super.modify(SqlId.SQL_BUYER_PD_CLASSIFICATION_DELETE,param.get(NumberConst.IntDef.INT_ZERO));
        if(!CollectionUtils.isEmpty(param)){

            for(int i = NumberConst.IntDef.INT_ZERO;i < param.size();i++){
                //根据传入参数获取买家的经营类别
                BaseParam inParam = new BaseParam();
                inParam.setFilter("buyerId", param.get(i).getBuyerId());
                inParam.setFilter("classCode",param.get(i).getClassCode());
                inParam.setFilter("className",param.get(i).getClassName());
                inParam.setFilter("machiningCodeU",param.get(i).getMachiningCode());
                inParam.setFilter("delFlg","1");
                //获取到数据则更新表
                ByBuyerPdCla pdClassification = super.findOne(SqlId.SQL_FIND_PD_CLASSIFICATION,inParam);
                if(null == pdClassification){
                    int testLine =  this.getCount(SqlId.TEST_BUYER_ID,inParam);
                    if(testLine > NumberConst.IntDef.INT_ZERO){
                        if(!StringUtil.isNullOrEmpty(param.get(i).getClassCode()) && !StringUtil.isNullOrEmpty(param.get(i).getClassName())){
                            returnNumber = buyerPdClassificationSave(param.get(i));
                        }
                    }
                }else{
                    ByBuyerPdCla updateParam = param.get(i);
                    updateParam.setId(pdClassification.getId());
//                    updateParam.setUpdTime(DateTimeUtil.getCustomerDate());
                    returnNumber = super.modify(SqlId.SQL_BUYER_PD_CLASSIFICATION_MODIFY,updateParam);
                }
            }

        }
        return returnNumber;
    }
    /**
     * 买家经营产品类别插入
     * @param insertParam
     * @return
     */
    @Transactional
    public Integer buyerPdClassificationSave(ByBuyerPdCla insertParam){
        Long id = commonLogic.maxId("by_buyer_pd_cla","ID");
        insertParam.setId(id);
        insertParam.setCrtId(insertParam.getUpdId());
        insertParam.setCrtTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
        insertParam.setActId(insertParam.getUpdId());
        insertParam.setActTime(DateTimeUtil.getCustomerDate());
        insertParam.setUpdTime(DateTimeUtil.getCustomerDate());
       return super.save(SqlId.SQL_BUYER_PD_CLASSIFICATION_INSERT,insertParam);
    }

    /**
     * 买家经营产品类别查询
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByBuyerPdCla> buyerPdClassificationFind(ByBuyerPdCla param){
        BaseParam inParam = new BaseParam();
        inParam.setFilter("buyerId",param.getBuyerId());
        inParam.setFilter("delFlg","0");
        List<ByBuyerPdCla> pdClassificationList = super.findList(SqlId.SQL_FIND_PD_CLASSIFICATION,inParam);
        return pdClassificationList;
    }

    /**
     * 获取买家经营产品类型
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<ByBuyerPdCla> buyerPdClassList(BuyerRelationParam param){
        List<ByBuyerPdCla> pdClassificationList = super.findList(SqlId.SQL_FIND_PD_CLASS_LIST,param);
        return pdClassificationList;
    }
}
