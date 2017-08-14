package com.msk.price.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.price.bean.ISP171185Bean;
import com.msk.price.bean.ISP171185Param;
import com.msk.price.utils.CommRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据产品列表信息获取价盘通道和价格列表
 * Created by ni_shaotang on 2016/8/26.
 */
public class ISP171185Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171184Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获取产品价盘价格信息
     *
     * @param pricePeriod
     * @param prodoutList
     * @return
     */
    @Transactional(readOnly = true)
    public List<ISP171185Bean> getPdPiceWayList(String pricePeriod, List<ISP171185Bean> prodoutList) {
        //定义查询参数
        ISP171185Param isp171185Param = new ISP171185Param();
        isp171185Param.setPricePeriod(pricePeriod);//传入价盘周期

        List<ISP171185Bean> returnList = new ArrayList<ISP171185Bean>();
        List<ISP171185Bean> newPriceList = new ArrayList<ISP171185Bean>();
        //防止参数过多分段查询数据
        int nn = 0;
        if (null != prodoutList && prodoutList.size() > 0) {
            for (int i = 0; i < prodoutList.size(); i += 50) {
                nn = i + 50;
                if (nn > prodoutList.size()) {
                    nn = prodoutList.size();
                }
                isp171185Param.setProductList(prodoutList.subList(i, nn));//参数分段
                List<ISP171185Bean> priceList = this.findList(isp171185Param);//查询结果
                newPriceList.addAll(priceList);
            }
        }

        for (ISP171185Bean param : prodoutList) {//遍历查询产品列表
            for (ISP171185Bean bean : newPriceList) {//遍历结果列表
                //Modif for 没有产品数量时,查询该产品所有价盘 at 2016/09/10 by yangchunyan Start
                if (bean.getPdCode().equals(param.getPdCode())) {//对比条件和结果集
                    //Modif for 参数类型变更 at 2016/09/10 by ni_shaotang Start
                    Long orderQty = param.getOrderQty();//产品数量
                    if(null != orderQty){
                        String wayCode = bean.getOrderLevel();//价盘等级
                        String sellWayCode = bean.getSellWayCode();//价盘等级(4级)
                        Long startQty = bean.getStartQty();//开始数量
                        Long endQty = bean.getEndQty();//结束数量
                        //Modif for 参数类型变更 at 2016/09/10 by ni_shaotang End
                        String pdcode = bean.getPdCode();//产品编码

                        //Add for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang Start
                        //获取订单等级对应的名称
                        String wayName = CommRestUtil.SellWayName.getName(sellWayCode);
                        bean.setSellWayName(wayName);

                        //Add for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang End
                        if (pdcode.length() == 10) {
                            bean.setGradeCode(pdcode.substring(9));//获取产品等级
                        }
                        if (wayCode.equals("0")) {//超级大宗订单不存在上限
                            if (orderQty >= startQty) {
                                returnList.add(bean);
                            }
                        } else {
                            if (orderQty >= startQty && orderQty <= endQty) {
                                returnList.add(bean);
                            }
                        }
                    }else{
                        returnList.add(bean);
                    }
                    //Modif for 没有产品数量时,查询该产品所有价盘 at 2016/09/10 by yangchunyan end
                }
            }
        }
        return returnList;
    }
}
