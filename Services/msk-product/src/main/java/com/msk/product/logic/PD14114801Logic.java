package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PD141148MctProBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * PD14114901Logic
 *
 * @author xhy
 */

@Service
public class PD14114801Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * sqlName
     */
    interface SqlId {
        static final String SQL_ID_FIND_List_TNC_MARKEY_JIN = "findListTncMarkeyJin"; //获取供应商需求标准列表
    }
    /**
     * 加工技术修改操作
     *
     * @param bean
     * @param fixDateString
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public List<PD141148MctProBean> saveAndEdit(PD141148MctProBean bean ,String fixDateString,BaseParam param) {
        param.setFilter("standardId",bean.getStandardId().toString());
        param.setFilter("mctStdItemId",bean.getMctStdItemId());
        param.setFilter("discussStatus", String.valueOf(NumberConst.IntDef.INT_TWO));
        if (StringUtils.isNotBlank(fixDateString)) {
            bean.setFixDate(DateTimeUtil.parseDate(fixDateString, DateTimeUtil.FORMAT_DATE_YYYYMMDD));
            bean.setDiscussStatus(NumberConst.IntDef.INT_ONE);//结案
            /**Add: 横展开添加共通设置 2016/09/12   BY  任强  Start */
            bean.setUpdId(param.getUpdId());
            /**Add: 横展开添加共通设置 2016/09/12   BY  任强  End */
            //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
            bean.setUpdTime(new Date());
            //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
            int modifoyJan = super.modify(bean);
            if (modifoyJan <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("设置供应商结案日失败!");
            return this.queryListMctProJie(param, bean);

        }
        return null;
    }

    /**
     * 动态加载页面
     * @param bean
     * @return
     */
    @Transactional(readOnly = true)
   public List<PD141148MctProBean> queryListMctProJie(BaseParam param,PD141148MctProBean bean) {

        List<PD141148MctProBean> list=  super.findList(param);
        if(list!=null){
            for(PD141148MctProBean bean1:list){
                bean1.setGetDivName(bean.getGetDivName());
                bean1.setFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, bean1.getFixDate()));
                bean1.setRaiseDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, bean1.getRaiseDate()));
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
       public List<PD141148MctProBean> queryListMctProJin(BaseParam param,PD141148MctProBean bean) {

        List<PD141148MctProBean> list=  super.findList(param);
        if(list!=null){
            for(PD141148MctProBean bean1:list){
                bean1.setGetDivName(bean.getGetDivName());
                bean1.setFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, bean1.getFixDate()));
            }
            return list;
        }
        return null;
    }
}
