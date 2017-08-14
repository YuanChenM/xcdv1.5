package com.msk.batch.bs.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlBsBuyerHis;
import com.msk.product.consts.TableNameDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gyh on 2016/3/29.
 * 业务处理类
 */
public class BBS1101101Logic extends BaseLogic {
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /*
    * logger
    */
    private static Logger logger = LoggerFactory.getLogger(BBS1101101Logic.class);

    /**
     * 查询到期的冻品管家与买家的申请关系数据
     *
     * @return 结果
     * @author gyh
     */
    public List<SlBsBuyerHis> findSlBsBuyer() {
        return this.findList(SqlId.SQL_ID_FIND_SL_BSBUYER, null);
    }

    /**
     * 新增查询出的冻品管家与买家关系数据 到 买手店管家买家关系履历表中
     *
     * @return 结果
     * @author gyh
     */
    public int saveSlBsBuyerHis(SlBsBuyerHis his) {
        return this.save(his);
    }

    /**
     * 物理删除查询到期的冻品管家与买家的申请关系数据
     *
     * @param his
     * @return 结果
     * @author gyh
     */
    public int deleteSlBsBuyerHis(SlBsBuyerHis his) {
        return this.remove(his);
    }

    /**
     * 处理到期的冻品管家与买家的申请关系数据
     *
     * @author gyh
     */
    @Transactional(readOnly = false)
    public void removeSlBsBuyer() {
        //取出到期的冻品管家与买家的申请关系数据
        List<SlBsBuyerHis> slBsBuyers = this.findSlBsBuyer();
        for (SlBsBuyerHis slBsBuyerHis : slBsBuyers) {
            //将过期数据存入买手店管家买家关系履历表中
            slBsBuyerHis.setHisId(commonLogic.maxId(TableNameDef.SL_BS_BUYER_HIS, "HIS_ID"));
            this.saveSlBsBuyerHis(slBsBuyerHis);
            //将过期数据从买手店管家买家关系表中物理移出
            this.deleteSlBsBuyerHis(slBsBuyerHis);
        }
    }

    /**
     * SQL Map 中SQL ID定义
     */
    public interface SqlId {
        static final String SQL_ID_FIND_SL_BSBUYER = "findSlBsBuyer";
    }
}
