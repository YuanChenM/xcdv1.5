package com.msk.product.logic;

import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.PD141130Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * PD141130Logic
 * @author pxg
 */
@Service
public class PD141130Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_FIND_ONE_CLASS = "findOneClass";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    public PageResult<PD141130Bean> queryList(){
        PD141130Bean pd141130Bean=new PD141130Bean();
       // pd141130Bean.setTcBuyinvestListId("1");
        pd141130Bean.setLevelCode("01");
        pd141130Bean.setLevelName("鸡产品");
        pd141130Bean.setBuyerCode("买家编码");
        pd141130Bean.setBuyerName("买家名称");
        pd141130Bean.setInvestigateDate("2015-11-11");
        pd141130Bean.setInvestigateMan("李四");
        PD141130Bean pd141130Bean2=new PD141130Bean();
        pd141130Bean2.setTcBuyinvestListId("2");
        pd141130Bean2.setLevelCode("01");
        pd141130Bean2.setLevelName("鸡产品");
        pd141130Bean2.setBuyerCode("买家编码");
        pd141130Bean2.setBuyerName("买家名称");
        pd141130Bean2.setInvestigateDate("2015-11-11");
        pd141130Bean2.setInvestigateMan("李四");
        PD141130Bean pd141130Bean3=new PD141130Bean();
        pd141130Bean3.setTcBuyinvestListId("3");
        pd141130Bean3.setLevelCode("01");
        pd141130Bean3.setLevelName("鸡产品");
        pd141130Bean3.setBuyerCode("买家编码");
        pd141130Bean3.setBuyerName("买家名称");
        pd141130Bean3.setInvestigateDate("2015-11-11");
        pd141130Bean3.setInvestigateMan("李四");
        List<PD141130Bean> li=new ArrayList<>();
        li.add(pd141130Bean3);
        li.add(pd141130Bean2);
        li.add(pd141130Bean);
        PageResult<PD141130Bean> list=new PageResult<>();
        list.setData(li);
        return list;
    }
}
