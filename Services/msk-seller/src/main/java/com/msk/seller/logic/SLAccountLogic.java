package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101115Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.BussinessConst;
import com.msk.core.entity.SlAccount;
import com.msk.core.entity.SlSeller;
import com.msk.seller.utils.ISLRestUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhang_chi on 2016/8/3.
 */
public class SLAccountLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    // 定义SqlId
    interface SqlId {
        String SQL_ID_FIND_ACCOUNT = "findAccount";
    }

    /**
     * 处理 买手情况
     *
     * @param slAccount
     * @return
     */
    @Transactional(readOnly = true)
    public void dealSLAccountBs(SlAccount slAccount) {
        // 查询 sl_seller 表
        BaseParam baseparam = new BaseParam();
        baseparam.getFilterMap().put("slAccount", slAccount.getSlAccount());
        SlSeller slSeller = super.findOne(baseparam);
        if (null != slSeller) {
            // 处理买手的情况
            Integer slMainClass = slSeller.getSlMainClass();
            if (StringUtil.toSafeString(BussinessConst.SlMainClass.BS).equals(StringUtil.toSafeString(slMainClass))) {
                // 准备接口数据
                IBS2101115Param param = new IBS2101115Param();
                BeanUtils.copyProperties(slAccount, param);
                //更新
                param.setOperationFlg("2");
                // 调接口处理
                boolean flag = ISLRestUtil.syncBuyerInfo(param);
                // 更新失败
                if (!flag) {
                    throw new BusinessException("买手新增或变更失败");
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public IBS2101115Param saveSLAccountBs(SlSeller slSeller) {
        // 查询 sl_account 表
        BaseParam baseparam = new BaseParam();
        baseparam.getFilterMap().put("slAccount", slSeller.getSlAccount());
        SlAccount slAccount = this.findOne(SqlId.SQL_ID_FIND_ACCOUNT, baseparam);
        if (null != slAccount) {
            // 处理买手的情况
            IBS2101115Param ibs2101115Param = new IBS2101115Param();
            BeanUtils.copyProperties(slAccount, ibs2101115Param);
            //新增
            ibs2101115Param.setOperationFlg("1");

//            // 调接口处理
//            boolean flag = ISLRestUtil.syncBuyerInfo(param);
//            // 更新失败
//            if (!flag) {
//                throw new BusinessException("买手新增失败");
//            }
            return ibs2101115Param;
        }
        return new IBS2101115Param();
    }
}
