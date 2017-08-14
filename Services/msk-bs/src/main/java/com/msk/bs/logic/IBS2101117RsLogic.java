package com.msk.bs.logic;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101117Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.StringContent;
import java.util.Date;

/**
 * 根据订单时间更新买家和管家关系有效期时间
 * Created by ni_shaotang on 2016/8/10.
 */
@Service
public class IBS2101117RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    public RsResponse<T> updateBuyer(IBS2101117Param param) {
        RsResponse<T> response = new RsResponse<T>();
        if(StringUtil.isNullOrEmpty(param.getBuyerId())){
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("买家id不能为空");
            return response;
        }
        if(null == param.getOrderCrtTime()){
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("订单时间不能为空");
            return response;
        }
        Date endTime = DateTimeUtil.addDay(param.getOrderCrtTime(), param.getDays());//获取有效日期结束时间

        param.setEndTime(endTime);
        int num = this.modify(param);

        if (num > 0) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("更新成功。有效时间变更为：" + DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", param.getOrderCrtTime()) + "至" + DateTimeUtil.formatDate("yyyy-MM-dd HH:mm:ss", param.getEndTime()));
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("更新失败");
        }
        return response;
    }
}
