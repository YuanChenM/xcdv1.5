package com.msk.order.service.impl;

import com.msk.common.bean.param.BaseParam;
import com.msk.common.data.jpa.impl.BaseJdbcImpl;
import com.msk.common.utils.XmlUtils;
import com.msk.order.bean.result.ISO151507BaseReturnResult;
import com.msk.order.bean.result.SO15150701BeanResult;
import com.msk.order.service.ISO151507Service;
import com.msk.order.util.SqlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ISO151506_退货单详情
 * Created by wang_shuai on 2016/8/11.
 */
@Service
public class ISO151507ServiceImpl  implements ISO151507Service {
    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(ISO151507ServiceImpl.class);
    @Autowired
    private BaseJdbcImpl baseJDBC;


    /**
     * 查询退货单明细数据
     * @param param
     * @return
     */
    @Override
    public List<SO15150701BeanResult> findReturnDetailList(BaseParam param) throws IOException {
        logger.debug("查询退货单明细数据");
        String sql = SqlUtil.getSqlBySqlId("ISO151507.findReturnDetailList");
        String returnId = param.getFilterMap().get("returnId").toString();
        List paramList = new ArrayList();
        paramList.add(returnId);
        List<Map<String,Object>> mapList = baseJDBC.queryForListNotCount(sql,paramList,null,true);
        List<SO15150701BeanResult> list = new ArrayList<>();
        //BeanUtils里注册BigDecimalConverter 的方法
        BigDecimal bigDecimalZero = new BigDecimal(0);
        BigDecimalConverter bd = new BigDecimalConverter(bigDecimalZero);
        ConvertUtils.register(bd, java.math.BigDecimal.class);

        if (!CollectionUtils.isEmpty(mapList)){
            for (int i=0;i<mapList.size();i++){
                SO15150701BeanResult so15150701BeanResult = new SO15150701BeanResult();
                try {
                    BeanUtils.populate(so15150701BeanResult,mapList.get(i));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                list.add(so15150701BeanResult);
            }

        }
        return list;
    }


    /**
     * 查询退货单基础信息
     * @param baseParam
     * @return
     */
    @Override
    public ISO151507BaseReturnResult findBaseReturnInfo(BaseParam baseParam) throws IOException{
        logger.debug("查询退货单基础数据");
        String sql = SqlUtil.getSqlBySqlId("ISO151507.findBaseReturnInfo");
        String returnId = baseParam.getFilterMap().get("returnId").toString();
        List paramList = new ArrayList();
        paramList.add(returnId);
        List<Map<String,Object>> mapList = baseJDBC.queryForListNotCount(sql,paramList,null,true);
        ISO151507BaseReturnResult iso151507BaseReturnResult = new ISO151507BaseReturnResult();
        //BeanUtils里注册BigDecimalConverter 的方法
        BigDecimal bigDecimalZero = new BigDecimal(0);
        BigDecimalConverter bd = new BigDecimalConverter(bigDecimalZero);
        ConvertUtils.register(bd, java.math.BigDecimal.class);

        if (!CollectionUtils.isEmpty(mapList)){
            try {
                BeanUtils.populate(iso151507BaseReturnResult,mapList.get(0));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return iso151507BaseReturnResult;
    }
}
