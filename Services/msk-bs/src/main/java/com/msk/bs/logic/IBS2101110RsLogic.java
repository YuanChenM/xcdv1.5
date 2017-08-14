package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS210111001RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gyh on 2016/4/8.
 */
public class IBS2101110RsLogic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 编辑买手囤货联盟
     *
     * @param slBsLeaguesList
     * @author gyh
     */
    @Transactional
    public void editSlBsBuyerLeagues(List<IBS210111001RsParam> slBsLeaguesList) {
        for (IBS210111001RsParam leagues : slBsLeaguesList) {
            BaseParam param = new BaseParam();
            //判断是否存在主键,存在修改，不存在新增
            if (null != leagues.getUnionId()) {
                param.getFilterMap().put("unionId", leagues.getUnionId());
                IBS210111001RsParam buyerLeagues = super.findOne(param);

                if (null == buyerLeagues) {
                    throw new BusinessException("联盟ID" + leagues.getUnionId() + "不存在，请检查参数后提交");
                }
                //判断是否执行删除操作
                if ("1".equals(leagues.getDelFlg())) {
                    leagues.setUpdTime(new Date());
                    int result = super.modify(SqlId.SQL_ID_DELETE_LEAGUES, leagues);
                    if (result < 1) {
                        throw new BusinessException("联盟ID" + leagues.getUnionId() + "删除失败，请检查参数后提交");
                    }
                    continue;
                } else {
                    //修改买手囤货联盟
                    leagues.setUpdTime(new Date());
                    int result = super.modify(leagues);
                    if (result < 1) {
                        throw new BusinessException("联盟ID" + leagues.getUnionId() + "修改失败，请检查参数后提交");
                    }
                    //判断如果修改状态则保存数据到履历
                    if (leagues.getApplyStatus().equals(buyerLeagues.getApplyStatus())) {
                        buyerLeagues.setCrtTime(new Date());
                        super.save(SqlId.SQL_ID_SAVE_HIS, buyerLeagues);
                    }
                }
            } else {
                //新增买手囤货联盟
                //业务不明
                leagues.setCrtTime(new Date());
                int result = super.save(leagues);
                if (result < 1) {
                    throw new BusinessException("联盟ID" + leagues.getUnionId() + "新增失败，请检查参数后提交");
                }
            }
        }
    }

    /**
     * sqlId
     */
    interface SqlId {
        /**
         * 删除买手囤货联盟
         */
        static final String SQL_ID_DELETE_LEAGUES = "deleteLeagues";
        /**
         * 保存买手囤货联盟履历
         */
        static final String SQL_ID_SAVE_HIS = "saveHis";
    }

}
