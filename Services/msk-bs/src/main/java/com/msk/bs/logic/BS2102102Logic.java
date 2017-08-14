package com.msk.bs.logic;

import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.BS2102102Bean;
import com.msk.bs.bean.BS2102102Param;
import com.msk.bs.bean.IBS2101140Bean;
import com.msk.bs.bean.IBS2101140RsParam;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlBsBuyer;
import com.msk.core.entity.SlBsBuyerHis;
import com.msk.district.bean.CityBean;
import com.msk.district.bean.DistrictParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/8/2.
 */
@Service
public class BS2102102Logic extends BaseLogic {

    private Logger logger = LoggerFactory.getLogger(BS2102102Logic.class);

    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface  SqlId{
        // 删除冻品管家
        static  String DELETE_HOUSE_ACCOUNT ="deleteHouseAccount";
        // 删除冻品管家与买家之间的关系
        static  String DELETE_SL_BS_BUYER ="deleteSlBsBuyer";
        // 根据买手code,houseCode查询管家专属会员表信息.
        static String FIND_SL_BS_BUYER_INFO = "findSlBsBuyer";
        // 新增管家与买家关系履历
        static String INSERT_SL_BS_BUYER_HIS = "insertSlBsBuyerHis";
    }
    /**
     * 查询所有冻品管家信息
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<BS2102102Bean> searchHouseInfo(BS2102102Param param) {
        logger.info("开始查询所有冻品管家的信息");
        param.setValidYearMonth(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YEAR_MONTH, new Date()));
        PageResult<BS2102102Bean> pageResult = this.findPage(param, BS2102102Bean.class);
        DistrictParam districtParam = new DistrictParam();
        int length = pageResult.getData().size();
        String[] cityCodes = new String[length];
        for (int i = 0; i < length; i++) {
            BS2102102Bean bs2102102Bean = pageResult.getData().get(i);
            // 城市编码
            cityCodes[i] = bs2102102Bean.getVcityCode();
        }
        districtParam.setCityCodes(cityCodes);
        districtParam.setFlag(0);
        //  根据省编码或物流区编码查询市区信息
        List<CityBean> cityList = CommRestUtil.getProvinceCityList(districtParam);
        // 构建物流区信息
        for (BS2102102Bean bs2102102Bean : pageResult.getData()) {
            /**Add: 创建时间，修改星级在页面显示 2016/09/19   BY  任强  Start */
            if(null != bs2102102Bean.getHouseStar()){
                if( bs2102102Bean.getHouseStar() == null){
                    bs2102102Bean.setHouseStar1("");
                }
                else{
                    bs2102102Bean.setHouseStar1(bs2102102Bean.getHouseStar()+"");
                }
            }
            else{
                bs2102102Bean.setHouseStar1("");
            }
            /**Add: 创建时间，修改星级在页面显示 2016/09/19   BY  任强  Start */
            // 防止该字段为null页面报警告
            bs2102102Bean.setVlgcsAreaName("");
            bs2102102Bean.setVcityName("");
            if (!CollectionUtils.isEmpty(cityList)) {
                for (CityBean city : cityList) {
                    if (!StringUtil.isNullOrEmpty(bs2102102Bean.getVlgcsAreaCode())
                            && bs2102102Bean.getVlgcsAreaCode().equals(city.getLgcsAreaCode())) {
                        bs2102102Bean.setVlgcsAreaName(city.getLgcsAreaName());
                    }
                    if (!StringUtil.isNullOrEmpty(bs2102102Bean.getHouseMangeCityCode())
                            && bs2102102Bean.getHouseMangeCityCode().equals(city.getCityCode())) {
                        bs2102102Bean.setHouseMangeCityName(city.getCityName());
                    }
                    if (!StringUtil.isNullOrEmpty(bs2102102Bean.getVlgcsAreaName())
                            && !StringUtil.isNullOrEmpty(bs2102102Bean.getVcityName())) {
                        break;
                    }
                }
            }
        }
        logger.info("查询所有冻品管家的信息结束");
        return pageResult;
    }

    /**
     * 删除冻品管家信息
     */
    @Transactional
    public void deleteHouseInfo(BS2102102Param param){

        List<SlBsBuyer> slBsBuyerList = this.findList(SqlId.FIND_SL_BS_BUYER_INFO, param);
        // 删除冻品管家
        this.modify(SqlId.DELETE_HOUSE_ACCOUNT,param);
        // 删除冻品管家与买家之间的关系
        this.modify(SqlId.DELETE_SL_BS_BUYER,param);
        // 解除所有查询到的管家下的买家信息.
        List<SlBsBuyerHis> buyerHisList = new ArrayList<>();
        IBS2101140RsParam ibs2101140RsParam = new IBS2101140RsParam();
        IBS2101140Bean ibs2101140Bean = null;
        List<IBS2101140Bean> relationList = new ArrayList<>();
        ibs2101140RsParam.setSlCode(param.getSlCode());
        ibs2101140RsParam.setHouseCode(param.getHouseCode());
        for (SlBsBuyer bsBuyer : slBsBuyerList) {
            SlBsBuyerHis slBsBuyerHis = buildSlBsBuyerHis(bsBuyer);
            buyerHisList.add(slBsBuyerHis);
            ibs2101140Bean = new IBS2101140Bean();
            ibs2101140Bean.setBuyerId(bsBuyer.getBuyerId());
            ibs2101140Bean.setRelationType("0");
            relationList.add(ibs2101140Bean);
        }
        // 插入sl_bs_buer_his履历信息
        if(!CollectionUtils.isEmpty(buyerHisList)){
            this.batchSave(buyerHisList);
        }
        ibs2101140RsParam.setRelationList(relationList);
        // 调用管家与买家解除关系接口
        CommRestUtil.updateBuyerHkRelationship(ibs2101140RsParam);
    }


    @Transactional
    public SlBsBuyerHis buildSlBsBuyerHis(SlBsBuyer slBsBuyer) {
        SlBsBuyerHis slBsBuyerHis = new SlBsBuyerHis();
        // 买手编码.
        slBsBuyerHis.setSlCode(slBsBuyer.getSlCode());
        slBsBuyerHis.setBuyershopReason("2");
        // 管家ID.
        slBsBuyerHis.setHouseAccount(slBsBuyer.getHouseCode());
        // 买家ID.
        slBsBuyerHis.setBuyerId(slBsBuyer.getBuyerId());
        // 开始日时.
        slBsBuyerHis.setStartTime(slBsBuyer.getStartTime());
        // 结束日时.
        slBsBuyerHis.setEndTime(slBsBuyer.getEndTime());
        // 认证方式.
        slBsBuyerHis.setApplySide(slBsBuyer.getApplySide());
        // 申请状态.
        slBsBuyerHis.setApplyStatus(slBsBuyer.getApplyStatus());
        // 申请日时.
        slBsBuyerHis.setApplyTime(slBsBuyer.getApplyTime());
        slBsBuyerHis.setHisId(commonLogic.maxId("SL_BS_BUYER_HIS", "HIS_ID"));

        slBsBuyerHis.setDelFlg(slBsBuyer.getDelFlg());
        slBsBuyerHis.setCrtId(slBsBuyer.getCrtId());
        slBsBuyerHis.setUpdId(slBsBuyer.getUpdId());
        slBsBuyerHis.setActId(slBsBuyer.getActId());
        slBsBuyerHis.setCrtTime(slBsBuyer.getCrtTime());
        slBsBuyerHis.setUpdTime(slBsBuyer.getUpdTime());
        slBsBuyerHis.setActTime(slBsBuyer.getActTime());
        slBsBuyerHis.setVer(slBsBuyer.getVer());

        return slBsBuyerHis;

    }
}
