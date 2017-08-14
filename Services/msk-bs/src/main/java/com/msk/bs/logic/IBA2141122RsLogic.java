package com.msk.bs.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBA2141122Bean;
import com.msk.bs.bean.IBA2141122RsParam;
import com.msk.bs.bean.IBA2141122RsResult;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlHouseAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 买手销售订单
 * Created by ni_shaotang on 2016/10/11.
 */
@Service
public class IBA2141122RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    public List<IBA2141122Bean> findBuyerBss(IBA2141122RsParam param) {
        //调用订单接口获取买手销售订单列表
        RsResponse<IBA2141122RsResult> response = CommRestUtil.findBssList(param);
        List<IBA2141122Bean> list = null;
        if (response.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {
            list = response.getResult().getOrders();
            param.getFilterMap().put("orderList", list);
            List<SlHouseAccount> houseList = this.findList(param);//根据管家code获取管家信息
            for (IBA2141122Bean order : list) {
                String orderTaker = order.getOrderTaker();
                for (SlHouseAccount houseAccount : houseList) {
                    if(houseAccount.getHouseCode().equals(orderTaker)){//比对信息，插入冻品管家名称
                        order.setHouseName(houseAccount.getHouseShowName());
                        continue;
                    }
                }
            }
        }
        return list;
    }
}
