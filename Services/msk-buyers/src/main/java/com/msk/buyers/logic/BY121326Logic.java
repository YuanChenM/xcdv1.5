package com.msk.buyers.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.buyers.bean.BY121122Bean;
import com.msk.buyers.bean.BY121322Bean;
import com.msk.buyers.bean.BY121322RsParam;
import com.msk.buyers.bean.BY121322RsResult;
import com.msk.buyers.utils.RestCommUtil;
import com.msk.common.bean.RsResponse;
import com.msk.common.service.ExcelAsyncPrintService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jiang_zhenping on 2016/8/24
 */
@Component("buyerRegisterPoolLogic")
public class BY121326Logic extends ExcelAsyncPrintService {
    private static Logger logger = LoggerFactory.getLogger(BY121326Logic.class);
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Override
    public Map<String, ?> getDataSource(Object objectParam) {
        Map<String, String> temp = (Map) objectParam;
        String buyerId = temp.get("buyerId");
        String fileStartTime = "";
        String fileEndTime = "";
        if(temp.get("fileStartTime") == "" || temp.get("fileStartTime") == null){
            fileStartTime = temp.get("fileStartTime");
        }else {
            fileStartTime = temp.get("fileStartTime")+" 00:00:00";
        }

        if(temp.get("fileEndTime") == "" || temp.get("fileEndTime") == null){
            fileEndTime = temp.get("fileEndTime");
        }else {
            fileEndTime = temp.get("fileEndTime") +  " 23:59:59";
        }
        String lgcsAreaCode = temp.get("lgcsAreaCode");
        String cityCode = temp.get("cityCode");
        BY121322RsParam param = new BY121322RsParam();
        Map<String, Object> map = new HashMap<>();
        int result = 0;
        List<BY121322Bean> list = new ArrayList<BY121322Bean>();
        List<BY121122Bean> BY121322Beanlist = new ArrayList<BY121122Bean>();
        param.setSearchType("0");
        param.setBuyerId(buyerId);
        param.setStartTime(fileStartTime);
        param.setEndTime(fileEndTime);
        param.setCityCode(cityCode);
        param.setLgcsAreaCode(lgcsAreaCode);
        RsResponse<BY121322RsResult> by121322RsResult = RestCommUtil.getSaleMarketControlInfo(param);
        if (by121322RsResult.getResult().getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            list = by121322RsResult.getResult().getSlHouseSaleList();
            result = 1;
            if(CollectionUtils.isNotEmpty(list)){
                for(BY121322Bean bean:list){
                    BY121122Bean  by121122Bean =new BY121122Bean();
                    if(bean.getStartTime() != null){
                        Date date=new Date(bean.getStartTime().toString());
                        SimpleDateFormat myFmt1=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                        by121122Bean.setStartTime(myFmt1.format((bean.getStartTime())));
                        by121122Bean.setEndTime(myFmt1.format((bean.getEndTime())));
                        by121122Bean.setBuyerPoolName(bean.getBuyerPoolName());
                        by121122Bean.setBuyerOnlineScore(bean.getBuyerOnlineScore());
                        by121122Bean.setChangeReason(bean.getChangeReason());
                        by121122Bean.setDevelopmentWay(bean.getDevelopmentWay());
                        by121122Bean.setHouseShowName(bean.getHouseShowName());
                    }
                    BY121322Beanlist.add(by121122Bean);
                }
            }
        }
        String title = "分销买家营销期冻品管家营销信息管控表";
        System.out.println("输出EXCEL");
        map.put("byMarketingsHouseList", BY121322Beanlist);
        map.put("title", title);
        return map;
    }
}