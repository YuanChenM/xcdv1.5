package com.msk.price.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.price.bean.SP171103Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by XU_WEI
 */
@Service
public class SP171103Logic extends BaseLogic {


    private static Logger logger = LoggerFactory.getLogger(SP171103Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId. 方法名
     */
    interface SqlId {
        String FIND_DEMAND_YEARMONTH_LIST = "findYearMonthList";
    }

    /**
     * OEM申报情况一览
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public PageResult<SP171103Bean> findSP171103BeansList(BasePageParam pageParam) {
        logger.info("供应商申报情况一览");
        PageResult<SP171103Bean> result = super.findPage(pageParam, SP171103Bean.class);
        if(result != null){
            List<SP171103Bean> SP171103list =result.getData();
            for(SP171103Bean SP171103Bean : SP171103list){
                if(StringUtil.isEmpty(SP171103Bean.getDemandId())){
                    //供应商
                    SP171103Bean.setSlName(StringConst.MIDDLE_LINE);
                    //申报数量
                    SP171103Bean.setApplyNum(StringConst.MIDDLE_LINE);
                }
            }
        }
        return result;
    }

    /**
     * 供应商申报发布年月周期取得
     * @param pageParam
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171103Bean> publishYmList(BasePageParam pageParam) {
        logger.info("供应商申报发布年月周期取得");
        List<SP171103Bean> SP171103list = super.findList(SqlId.FIND_DEMAND_YEARMONTH_LIST, pageParam);
        for(SP171103Bean SP171103Bean : SP171103list){
            String demandYearMonth = SP171103Bean.getDemandYearMonth();
            String demandMonth  = demandYearMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX);
            Date date = DateTimeUtil.
                    parseDate(demandYearMonth, DateTimeUtil.FORMAT_YEAR_MONTH);
            String  demandStartDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,DateTimeUtil.firstDay(date));
            String  demandEndDate = DateTimeUtil.formatDate(DateTimeUtil.FORMAT_YYYYMMDD,DateTimeUtil.lastDay(date));
            SP171103Bean.setDemandStartDate(DateTimeUtil.firstDay(date));
            SP171103Bean.setDemandEndDate(DateTimeUtil.lastDay(date));
            SP171103Bean.setDemandYearMonthShow("第" + demandMonth + "期 " + demandStartDate + "-" + demandEndDate);
            String lastMonth = DateTimeUtil.getLastMonth(demandYearMonth);
            SP171103Bean.setDemandLimitedDateShow(lastMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX)
                    + "月16日 - " + lastMonth.substring(NumberConst.IntDef.INT_FOUR, NumberConst.IntDef.INT_SIX) + "月20日");
        }

        return SP171103list;
    }

    /**
     *  获取物流下拉框信息
     * @return
     */
    public  List<LgcsAreaBean>  getLogiticsAreaList(){
        DistrictParam param = new DistrictParam();
        RsRequest<DistrictParam> request = new RsRequest<DistrictParam>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //物流区信息接口url
        String districtUrl = ConfigManager.getMskDistrictService()+ ConfigManager.getDistrictQueryLgcsAreaService();
        RsResponse<DistrictResult> lgcsAreaBeanList =
                RestClientUtil.post(districtUrl, request, new TypeReference<RsResponse<DistrictResult>>() {
                });
        List<LgcsAreaBean> districtList  = lgcsAreaBeanList.getResult().getLgcsAreaList();
        return districtList;
    }

}
