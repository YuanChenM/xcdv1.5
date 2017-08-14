package com.msk.seller.logic;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.SlSeller;
import com.msk.seller.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SL241103Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * 
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_QUERYTONE = "querytOne";
        String SQL_ID_QUERYLIST = "queryList";
        String SQL_ID_QUERYLIST2 = "queryList2";
        String SQL_ID_SELECTLIST="findListTeam";
        String SQL_ID_SELECTONE="findOneSlSeller";
        String SQL_ID_FIND_QUERY_SLEPDD="querySlEpDd";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 根据卖家编码查询出卖家的企业ID信息，用与拼接文件服务器图片路径
     * @param slCode 卖家编码
     * @return 返回卖家基本信息对象
     */
    @Transactional(readOnly = true)
    public SlSeller findSlSellerBySlCode(String slCode) {
        BaseParam base = new BaseParam();
        base.setFilter("slCode",slCode);
        SlSeller slSeller= super.findOne(SqlId.SQL_ID_SELECTONE,base);
        return slSeller;
    }

    /**
     * 根据卖家编码查询卖家下面所有的电商团队成员
     * @param slCode 卖家编码
     * @return 返回卖家成员集合
     */
    @Transactional(readOnly = true)
    public List<SL24110307Bean> findEcTeamList(String slCode) {
        BaseParam base = new BaseParam();
        base.setFilter("slCode",slCode);
        base.setFilter("delFlg","0");
        List<SL24110307Bean> list=super.findList(SqlId.SQL_ID_SELECTLIST,base);
        return list;
    }
    /**
     * 根据企业ID查询企业专业资质
     * 
     * @author pxg
     */
    @Transactional(readOnly = true)
    public List<SL24110302Bean> findSL24110302Bean(BaseParam param) {
        return super.findList(SqlId.SQL_ID_QUERYLIST, param);
    }

    /**
     * 根据企业ID企业标识
     * 
     * @author pxg
     */
    @Transactional(readOnly = true)
    public SlSeller findSlSeller(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_QUERYTONE, param);
    }

    /**
     * 根据证照ID查询企业资质详细
     * 
     * @author pxg
     */
    @Transactional(readOnly = true)
    public List<SL24110302_1Bean> findSL24110302_1Bean(BaseParam param) {
        return super.findList(SqlId.SQL_ID_QUERYLIST2, param);
    }


    /**
     * 根据企业ID查询企业检测设备
     * @author pxg
     */
    @Transactional(readOnly = true)
    public List<SlEpDdBean> findEpEquipment(BaseParam param) {
        return super.findList(SqlId.SQL_ID_FIND_QUERY_SLEPDD, param);
    }

    /**
     * @param basePageParam 参数
     * @param slCode 卖家编码
     * @return PageResult
     * @author xhy
     */
    @Transactional(readOnly = true)
    public PageResult<SlEpAgentAuth> findPages(BasePageParam basePageParam, String slCode) {
        basePageParam.setFilter("slCode", slCode);
        PageResult<SlEpAgentAuth> page = super.findPage(basePageParam, SlEpAgentAuth.class);
        for (SlEpAgentAuth sl : page.getData()) {
            if(!StringUtil.isNullOrEmpty(sl.getAuthTermBeginString()) && !StringUtil.isNullOrEmpty(sl.getAuthTermEndString())){
                sl.setOnTime(sl.getAuthTermBeginString() + "~" +sl.getAuthTermEndString());
            }else{
                sl.setOnTime("");
            }
        }
        PageResult<SlEpAgentAuth> pages = new PageResult<SlEpAgentAuth>();
        pages.setData(page.getData());
        pages.setRecordsTotal(page.getData().size());
        return pages;
    }

}
