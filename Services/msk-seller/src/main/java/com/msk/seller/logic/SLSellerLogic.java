package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101115Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.BussinessConst;
import com.msk.core.entity.BsBasicInfo;
import com.msk.core.entity.SlSeller;
import com.msk.seller.utils.ISLRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 验证是否同步买手信息
 * Created by li_kai1 on 2016/8/3.
 */

public class SLSellerLogic extends BaseLogic {
    /**
     * logger
     */
    public static Logger logger = LoggerFactory.getLogger(SLSellerLogic.class);
    /**
     *
     * @param baseDao
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao){
        super.setBaseDao(baseDao);
    }

    @Autowired
    private SLAccountLogic slAccountLogic;

    // 定义SqlId
    interface SqlId{
        String SQL_ID_FIND_SELLER = "findSeller";
    }

    /**
     * 同步更新卖家买手信息
     * @param slSeller
     */
    @Transactional
    public void updSellerToBs(SlSeller slSeller) {
        //根据slCode查询卖家的主分类
        SlSeller seller = this.findSeller(slSeller);
        if (seller != null) {
            //更新slMainClass为'4'的卖家,或者更新原卖家为买手
            // if (seller.getSlMainClass().equals(BussinessConst.SlMainClass.BS) || slSeller.getSlMainClass().equals(BussinessConst.SlMainClass.BS)){
            if ((seller.getSlMainClass() != null && seller.getSlMainClass() == BussinessConst.SlMainClass.BS)
                    || (slSeller.getSlMainClass() != null && BussinessConst.SlMainClass.BS == slSeller.getSlMainClass())
                    ) {

                BsBasicInfo basicInfo = new BsBasicInfo();
                BeanUtils.copyProperties(slSeller, basicInfo);

                IBS2101115Param buyerInfoParam = new IBS2101115Param();
                buyerInfoParam.setBsBasicInfo(basicInfo);
                //更新
                buyerInfoParam.setOperationFlg("2");
                if (ISLRestUtil.syncBuyerInfo(buyerInfoParam)) {
                    logger.info("同步卖家模块买手信息,成功!");
                } else {
                    logger.info("同步卖家模块买手信息,失败!");
                    throw new BusinessException("同步卖家模块买手信息,失败!");
                }
            }
        }
    }
         /**
         * 根据slCode判断是否同步更新买手信息
         */
        @Transactional(readOnly = true)
        public SlSeller findSeller(SlSeller slSeller) {
            BaseParam baseParam = new  BaseParam();
            baseParam.getFilterMap().put("slCode",slSeller.getSlCode());
            baseParam.getFilterMap().put("slAccount",slSeller.getSlAccount());
            return this.findOne(SqlId.SQL_ID_FIND_SELLER,baseParam);
        }

    /**
     * 同步插入卖家买手信息
     * @param slSeller
     */
    @Transactional
    public void saveSellerToBs(SlSeller slSeller) {
        //新增
        slSeller.setSlCode(null);
        SlSeller seller = findSeller(slSeller);
        if (slSeller.getSlMainClass().equals(BussinessConst.SlMainClass.BS)){
            // 处理买手
            IBS2101115Param ibs2101115Param = slAccountLogic.saveSLAccountBs(slSeller);

            BsBasicInfo basicInfo = new BsBasicInfo();
            BeanUtils.copyProperties(slSeller, basicInfo);
            basicInfo.setSlCode(seller.getSlCode());
            basicInfo.setSlCodeDis(seller.getSlCodeDis());

            ibs2101115Param.setBsBasicInfo(basicInfo);
            //插入
            ibs2101115Param.setOperationFlg("1");

            if (ISLRestUtil.syncBuyerInfo(ibs2101115Param)){
                logger.info("同步卖家模块买手信息,成功!");
            }else {
                logger.info("同步卖家模块买手信息,失败!");
                throw new BusinessException("同步卖家模块买手信息,失败!");
            }
        }
    }
}
