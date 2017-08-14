package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PDtncProviderBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * PD14114902Logic
 *
 * @author XHY
 */

@Service
public class PD14114902Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * sqlName
     */
    interface SqlId {
        static final String SQL_ID_FIND_List_TNC_PRO = "findListTncProkey"; //获取市场需求标准列表
        static final String SQL_ID_FIND_List_TNC_PRO_NO = "findListTncProNo"; //获取市场需求标准列表
    }
    /**
     * 动态加载页面
     * @param bean
     * @return
     */
    @Transactional(readOnly = true)
    public List<PDtncProviderBean> queryListPro(BaseParam param,PDtncProviderBean bean) {

        List<PDtncProviderBean> list=  super.findList(SqlId.SQL_ID_FIND_List_TNC_PRO,param);
        if(list!=null){
            for(PDtncProviderBean bean1:list){
                bean1.setGetDivName(bean.getGetDivName());
                bean1.setProFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, bean1.getFixDate()));
                bean1.setProRaiseDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, bean1.getRaiseDate()));
            }
            return list;
        }
        return null;
    }

    /**
     * 动态加载页面
     * @param bean
     * @return
     */
    @Transactional(readOnly = true)
    public List<PDtncProviderBean> queryListProNo(BaseParam param,PDtncProviderBean bean) {

        List<PDtncProviderBean> list=  super.findList(SqlId.SQL_ID_FIND_List_TNC_PRO_NO,param);
        if(list!=null){
            for(PDtncProviderBean bean1:list){
                bean1.setGetNoProName(bean.getGetNoProName());
                bean1.setProFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, bean1.getFixDate()));
            }
            return list;
        }
        return null;
    }
}
