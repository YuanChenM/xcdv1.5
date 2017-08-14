package com.msk.price.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.price.bean.ISP171184Bean;
import com.msk.price.bean.ISP171184Param;
import com.msk.price.bean.ISP171184Result;
import com.msk.price.bean.ISP171184WayBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 查询价盘通道接口Logic
 *
 * @author peng_hao
 * @version 1.0
 */
public class ISP171184Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171184Logic.class);

    /**
     * SqlId. sql定义
     */
    interface SqlId {
        //获得产品编码，物流区编码，产品等级
        static final String SQL_ID_GET_RESULT = "getResult";
        //获得产品的价盘通道等级，上限和下限
        static final String SQL_ID_GET_WAY = "getWay";
        static final String SQL_ID_GET_PRICE_WAY_COUNT = "getPriceWayCount";
        static final String SQL_ID_GET_PRICE_WAY = "getPriceWay";
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public ISP171184Result findAllList(RsRequest<ISP171184Param> request) {
        ISP171184Result rs = new ISP171184Result();
        ISP171184Param param = request.getParam();
        int totalCount = super.getPageCount(param);
        //暂时没有分页，先设0
        rs.setTotalCount(totalCount);
        rs.setTotalPage(0);
        rs.setPageNo(0);
        //获得产品编码，物流区编码，产品等级
        List<ISP171184Bean> resultList = super.findList(SqlId.SQL_ID_GET_RESULT, param);
        BaseParam baseParam = new BaseParam();
        for (int i = 0; i < resultList.size(); i++) {
            //通过产品编码，物流区编码 得到该产品的价盘通道等级，上限和下限
            baseParam.setFilterObject("pdCode", resultList.get(i).getProductId());
            baseParam.setFilterObject("LgcsCode", resultList.get(i).getLogiAreaCode());
            List<ISP171184WayBean> wayList = super.findList(SqlId.SQL_ID_GET_WAY, baseParam);
            //循环wayList
            for (int j = 0; j < wayList.size(); j++) {
                //如果超级大宗订单（等级值为0）的最大箱数为0时，设为空("")
                if(("0").equals(wayList.get(j).getOrderLevel())){
                    wayList.get(j).setBoxMax("");
                }
                //Del for Bug#3345 at 2016/10/14 by ni_shaotang Start
               //如果大宗一级订单（等级值为1）的最大箱数为0时，设为空("")
//                if(("1").equals(wayList.get(j).getOrderLevel())){
//                    wayList.get(j).setBoxMax("");
//                }
                //Del for Bug#3345 at 2016/10/14 by ni_shaotang End
            }
            resultList.get(i).setWaylist(wayList);
        }
        rs.setSearchList(resultList);
        return rs;
    }

    /**
     * 根据产品code获取价盘通道
     * @param request
     * @return
     */
    @Transactional(readOnly = true)
    public ISP171184Result findListByPdCode(RsRequest<ISP171184Param> request) {
        ISP171184Result rs = new ISP171184Result();
        ISP171184Param param = request.getParam();
        int totalCount = this.getBaseDao().count(SqlId.SQL_ID_GET_PRICE_WAY_COUNT, param);
        //暂时没有分页，先设0
        rs.setTotalCount(totalCount);
        rs.setTotalPage(0);
        rs.setPageNo(0);
        //获得产品编码，物流区编码，产品等级
        List<ISP171184Bean> resultList = super.findList(SqlId.SQL_ID_GET_PRICE_WAY, param);
        BaseParam baseParam = new BaseParam();
        for (int i = 0; i < resultList.size(); i++) {
            //通过产品编码，物流区编码 得到该产品的价盘通道等级，上限和下限
            baseParam.setFilterObject("pdCode", resultList.get(i).getProductId());
            baseParam.setFilterObject("LgcsCode", resultList.get(i).getLogiAreaCode());
            List<ISP171184WayBean> wayList = super.findList(SqlId.SQL_ID_GET_WAY, baseParam);
            //循环wayList
            for (int j = 0; j < wayList.size(); j++) {
                //如果超级大宗订单（等级值为0）的最大箱数为0时，设为空("")
                if(("0").equals(wayList.get(j).getOrderLevel())){
                    wayList.get(j).setBoxMax("");
                }
                //Del for Bug#3345 at 2016/10/14 by ni_shaotang Start
               //如果大宗一级订单（等级值为1）的最大箱数为0时，设为空("")
//                if(("1").equals(wayList.get(j).getOrderLevel())){
//                    wayList.get(j).setBoxMax("");
//                }
                //Del for Bug#3345 at 2016/10/14 by ni_shaotang End
            }
            resultList.get(i).setWaylist(wayList);
        }
        rs.setSearchList(resultList);
        return rs;
    }
}

