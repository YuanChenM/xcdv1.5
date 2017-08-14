package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141108RsParam;
import com.msk.product.bean.IPD141108RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月13日 下午15:42:29
 *          产品标准包装档案卡查询参数
 */
@Service
public class IPD141108Logic extends BaseLogic {


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 产品标准包装档案卡查询参数接口 数据库连接
     *
     * @param param 传入参数
     * @return List 集合
     * @author xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141108RsResult> findListPdQltTncStd(IPD141108RsParam param) {
        if (param == null) param = new IPD141108RsParam();
        BaseParam baseParam = new BaseParam();
        if (param.getActTime() != null) {
            Date date = param.getActTime();
            String daNow = DateTimeUtil.formatDate("yyyy-MM-dd", date);
            baseParam.setFilter("actTime", daNow);
        }
        return super.findList(baseParam);
    }

    /**
     * @param date
     * @return Date 下一天时间
     * @author xhy
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);// +1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
}