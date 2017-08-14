package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2101105Bean;
import com.msk.bs.bean.IBS2101102RsParam;
import com.msk.bs.bean.IBS2101102RsResult;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 买手基本信息编辑Logic
 *
 * @author cx
 */
@Service
public class BS2101106Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private IBS2101102RsLogic ibs2101102RsLogic;

    interface SqlId {
        String SQL_ID_FIND_SLACCOUNT = "findSlBuyerhop";
        String SQL_ID_FIND_SLCODE = "findSlCode";
        // 查询买手一级类型
         String  SQL_ID_FIND_BUYERS_CLASS="findBuyersClass";
        // 查询买手二、三级类型
        String  SQL_ID_FIND_BUYERS_CLASSEVEL="findBuyersClassLevel";
        // 新增买手地区编码
        static final String SAVE_BS_COUNT = "saveBsCount";
        // 根据cityCode查询对应的买手数量
        static final String FIND_BUYERCOUNT_BY_CITYCODE = "findBuyerCountByCityCode";
        // 根据cityCode更新对应的买手数量
        static final String UPDATE_BUYERCOUNT_BY_CITYCODE = "updateBuyerCountByCityCode";
        //根据slCode查询银行卡信息
        static final String SQL_ID_FIND_BANK_ACCOUNT = "findBankAccount";
        //根据slAccount查询买手cityCode信息
        static final String SQL_FIND_BS_CITY_CODE = "findBsCityCode";
    }

    @Transactional(readOnly = true)
    public int findSlBuyerhop(BaseParam baseParam) {
        return super.getCount(SqlId.SQL_ID_FIND_SLACCOUNT, baseParam);
    }


    @Transactional(readOnly = true)
    public SlShopInfo findShopId(BaseParam baseParam) {
        return super.findOne(baseParam);
    }

    @Transactional(readOnly = true)
    public BsBasicInfo findSlCode(BaseParam baseParam) {
        return super.findOne(SqlId.SQL_ID_FIND_SLCODE,baseParam);
    }

    /**
     * 查询买手一级类型
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlHouseType> findBuyersClass() {
        BaseParam baseParam = new BaseParam();
        return super.findList(SqlId.SQL_ID_FIND_BUYERS_CLASS, baseParam);
    }

    /**
     * 查询买手二、三级类型
     * @return
     */
    @Transactional(readOnly = true)
    public List<SlHouseType> findBuyersSecondLevel(SlHouseType houseType) {
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("parentTypeCode",houseType.getParentTypeCode());
        baseParam.getFilterMap().put("typeLever",houseType.getTypeLever());
        baseParam.getFilterMap().put("typeCode",houseType.getTypeCode());
        return super.findList(SqlId.SQL_ID_FIND_BUYERS_CLASSEVEL, baseParam);
    }

    /**
     * 根据cityCode查询对应的买手数量
     * @return
     */
    @Transactional(readOnly = true)
    public Long  findBuyerCountByCityCode(String cityCode){
        BaseParam baseParam = new BaseParam();
        baseParam.getFilterMap().put("cityCode",cityCode);
        return  (Long)this.findObject(SqlId.FIND_BUYERCOUNT_BY_CITYCODE, baseParam);
    }

    /**
     * 若某城市下面没有注册过买手
     * @return
     */
    @Transactional
    public void  saveBsCitySeqno(SlBsCitySeqno slBsCitySeqno){
        this.save(SqlId.SAVE_BS_COUNT, slBsCitySeqno);
    }

    /**
     * 更新买手地区表中的买手数
     * @return
     */
    @Transactional
    public void  updateBuyerCountByCityCode(SlBsCitySeqno slBsCitySeqno){
        this.modify(SqlId.UPDATE_BUYERCOUNT_BY_CITYCODE, slBsCitySeqno);
    }

    /**ADD for 买手编码重复处理 at 2016/10/27 by zhu_kai1 Start**/
    /**
     * 获取买手基本信息
     * @param bsBasicInfo
     * @return
     */
    @Transactional(readOnly = true)
    public BsBasicInfo getBsBasicInfo(BsBasicInfo bsBasicInfo) {
        BsBasicInfo basicInfo;BaseParam baseParam = new BaseParam();
        if(StringUtil.isNullOrEmpty(bsBasicInfo.getSlAccount()) && StringUtil.isNullOrEmpty(bsBasicInfo.getSlCode())){
            throw new BusinessException("您保存的买手没有对应的账户信息");
        }
        baseParam.getFilterMap().put("slAccount",bsBasicInfo.getSlAccount());
        baseParam.getFilterMap().put("slCode", bsBasicInfo.getSlCode());
        basicInfo = (BsBasicInfo)this.findObject(SqlId.SQL_FIND_BS_CITY_CODE,baseParam);
        return basicInfo;
    }
    /**ADD for 买手编码重复处理 at 2016/10/27 by zhu_kai1 end**/

    @Transactional(readOnly = true)
    public SlBsBankaccount findBankAccount(BS2101105Bean bs2101105Bean){
        return this.findOne(SqlId.SQL_ID_FIND_BANK_ACCOUNT,bs2101105Bean);
    }
}
