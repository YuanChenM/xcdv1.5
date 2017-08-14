package com.msk.price.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.price.bean.*;
import com.msk.seller.bean.ISL231198RsPageResult;
import com.msk.seller.bean.ISL231198RsResult;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 供应商显示项目设置
 * Created by ni_shaotnag
 */
@Service
public class SP171196Logic extends BaseLogic {
    private static Logger logger = LoggerFactory.getLogger(SP171196Logic.class);
    private static final String falseName = "否";
    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 获取物流区供应商列表
     *
     * @return
     */
    public PageResult<SP171196Bean> getPageSellerList(BasePageParam params) {

        String url = ConfigManager.getMskSellerService() + ConfigManager.getLgcsslinfoService();
        //设置接口参数
        RsRequest<Map<String, Object>> requestParam = new RsRequest<Map<String, Object>>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        //组装参数
        Map<String, Object> param = new HashMap<>();
        param.put("pageCount", params.getPageSize());
        param.put("startSize", params.getStartPos());
        param.put("lgcsCode", params.getFilterMap().get("lgcsCode"));
        param.put("slName", params.getFilterMap().get("slName"));
        requestParam.setParam(param);

        PageResult<SP171196Bean> result = new PageResult<SP171196Bean>();
        //调用接口
        RsResponse<ISL231198RsPageResult> rsResponse = RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ISL231198RsPageResult>>() {
        });
        if (null == rsResponse.getResult()) {
            result.setData(new ArrayList<SP171196Bean>());
            result.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            return result;
        }
        List<SP171196Bean> sList = new ArrayList<SP171196Bean>();
        result.setRecordsTotal(rsResponse.getResult().getTotalCount());
        List<ISL231198RsResult> list = rsResponse.getResult().getPageResult();
        try {
            for (ISL231198RsResult rs : list) {
                SP171196Bean bean = new SP171196Bean();
                BeanUtils.copyProperties(bean, rs);//复制接口数据到供应商
                sList.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setData(sList);
        return result;
    }

    /**
     * 获取供应商显示内容列表
     *
     * @return
     */
    public PageResult<SP171196Bean> getPageViewList(BasePageParam param) {
        PageResult<SP171196Bean> result = this.getPageSellerList(param);//通过接口获取供应商列表
        List<SP171196Bean> list = result.getData();
        if (null != list && list.size() > 0) {
            for (SP171196Bean bean : list) {
                SP171196Bean b = this.getSellerView(bean);
                if (null != b) {
                    bean.setIsNum(null != b.getIsNum() ? b.getIsNum() : falseName);
                    bean.setIsPrice(null != b.getIsPrice() ? b.getIsPrice() : falseName);
                    bean.setIsSupply(null != b.getIsSupply() ? b.getIsSupply() : falseName);
                } else {
                    bean.setIsNum(falseName);
                    bean.setIsPrice(falseName);
                    bean.setIsSupply(falseName);
                }
            }
        }
        return result;
    }

    /**
     * 保存页面显示数据
     *
     * @return
     */
    @Transactional
    public void saveView(SP171196Bean param) {
        Date nowDate = DateTimeUtil.getCustomerDate();
        if (null != param.getSlCode() && null != param.getViewKeys() && null != param.getViewFlg()) {
            long maxPriceId = commonLogic.maxId("SP_VIEW_SET", "VIEW_ID");//获取序列id
            List<SP171196Bean> list = new ArrayList<SP171196Bean>();
            String[] beans = param.getSlCode().split(StringConst.COLON);//获取需要更新权限的供应商
            String[] viewKeys = param.getViewKeys();
            SP171196Bean sbean = null;
            for (int i = 0; i < beans.length; i++) {
                String[] ids = beans[i].split(StringConst.COMMA);
                if (ids.length != 2) {
                    break;
                }
                for (int j = 0; j < viewKeys.length; j++) {
                    sbean = new SP171196Bean();
                    sbean.setLgcsCode(ids[0]);
                    sbean.setSlCode(ids[1]);
                    sbean.setViewKey(viewKeys[j]);
                    sbean.setViewFlg(param.getViewFlg());
                    sbean.setSystemType(param.getSystemType());
                    sbean.setCrtId(param.getCrtId());
                    sbean.setCrtTime(nowDate);
                    sbean.setUpdId(param.getUpdId());
                    sbean.setUpdTime(nowDate);
                    sbean.setActId(param.getActId());
                    sbean.setActTime(nowDate);
                    list.add(sbean);
                }
            }
            SP171196Param bp = null;
            for (SP171196Bean bean : list) {
                bp = new SP171196Param();
                bp.setLgcsCode(bean.getLgcsCode());
                bp.setSlCode(bean.getSlCode());
                bp.setViewKey(bean.getViewKey());
                int num = this.getViewCount(bp);
                if (num > 0) {
                    this.getBaseDao().update(SqlId.SQL_ID_UPDATE_VIEW, bean);
                } else {
                    bean.setViewId(maxPriceId++);
                    this.save(bean);
                }
            }
        }
    }

    /**
     * 保存供应商无库存报价权限
     *
     * @param param
     */
    @Transactional
    public void saveViewInfo(SP171196Bean param) {
        Date nowDate = DateTimeUtil.getCustomerDate();
        SP171196Param bp = new SP171196Param();
        bp.setViewKey(param.getViewKey());
        int num = this.getViewCount(bp);
        SP171196Bean bean = new SP171196Bean();
        bean.setLgcsCode("0");
        bean.setSlCode("0");
        bean.setViewKey(param.getViewKey());
        bean.setViewFlg(param.getViewFlg());
        bean.setSystemType(param.getSystemType());
        bean.setCrtId(param.getCrtId());
        bean.setCrtTime(nowDate);
        bean.setUpdId(param.getUpdId());
        bean.setUpdTime(nowDate);
        bean.setActId(param.getActId());
        bean.setActTime(nowDate);
        if (num > 0) {
            this.getBaseDao().update(SqlId.SQL_ID_UPDATE_VIEW_INFO, bean);
        } else {
            long maxPriceId = commonLogic.maxId("SP_VIEW_SET", "VIEW_ID");//获取序列id
            bean.setViewId(maxPriceId++);
            this.save(bean);
        }
    }

    /**
     * 根据查询条件获取总数
     *
     * @param bp
     * @return
     */
    @Transactional(readOnly = true)
    public int getViewCount(SP171196Param bp) {
        return this.getCount(bp);
    }

    @Transactional(readOnly = true)
    public List<SP171196Bean> findViewList(SP171196Param bp) {
        return this.findList(bp);
    }

    /**
     * 根据物流区code和slcode获取供应商权限
     *
     * @param bean
     * @return
     */
    @Transactional(readOnly = true)
    public SP171196Bean getSellerView(SP171196Bean bean) {
        return this.findOne(SqlId.SQL_ID_FIND_ONE, bean);
    }

    /**
     * 取得供应商申报时间段
     *
     * @param param
     * @return 供应商申报时间段
     */
    @Transactional(readOnly = true)
    public SP171196Bean findConstRatio(SP171196Param param) {
        param.setFilterObject("ratioType", "declareTime");
        SP171196Bean bean = new SP171196Bean();
        bean.setStartTime(NumberConst.IntDef.INT_SIXTEEN);//默认开始时间为16日
        bean.setEndTime(NumberConst.IntDef.INT_TWENTY);//默认结束时间为20日
        List<SP171196Bean> list = super.findList(SqlId.SQL_ID_CONST_RATIO, param);
        if (null != list && list.size() > 0) {
            for (SP171196Bean r : list) {
                if (r.getViewKey().equals("1")) {
                    bean.setStartTime(r.getEndTime());
                }
                if (r.getViewKey().equals("2")) {
                    bean.setEndTime(r.getEndTime());
                }
            }
        }
        return bean;
    }

    /**
     * 供应商无库存是否可以报价
     *
     * @param bean
     */
    @Transactional
    public void saveDeclare(SP171196Bean bean) {
        SP171196Param param = new SP171196Param();
        param.setFilterObject("ratioType", "declareTime");
        List<SP171106Bean> list = super.findList(SqlId.SQL_ID_CONST_RATIO, param);
        if (null != list && list.size() > 0) {
            SP171106Bean rBean = new SP171106Bean();
            rBean.setRatioTypeDetail("1");
            rBean.setRatioTypeDetailVal(new BigDecimal(bean.getStartTime()));
            this.getBaseDao().update(SqlId.SQL_ID_UPDATE_DECLARE, rBean);
            rBean.setRatioTypeDetail("2");
            rBean.setRatioTypeDetailVal(new BigDecimal(bean.getEndTime()));
            this.getBaseDao().update(SqlId.SQL_ID_UPDATE_DECLARE, rBean);
        }
    }

    /**
     * 获取上一个价盘周期以后的列表
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171196Bean> getPriceList(SP171196Param param){
        return this.findList(SqlId.SQL_ID_FIND_PRICE_LIST,param);
    }
    /**
     * 获取上一个价盘周期以后的列表
     * @return
     */
    @Transactional(readOnly = true)
    public List<SP171196Bean> getPriceCycleCode(){
        SP171196Param param = new SP171196Param();
        Date newDate = DateTimeUtil.getCustomerDate();
        param.setActTime(newDate);
        return this.findList(SqlId.SQL_ID_GET_PRICE_CYCLE_CODE,param);
    }
    /**
     * 保存价盘有效期
     * @param param
     */
    @Transactional
    public void savePrice(SP171196Param param){
        Date newDate = DateTimeUtil.getCustomerDate();
        this.modify(SqlId.SQL_ID_DEL_PRICE,param);//把历史记录数据删除状态置为1
        long maxPriceId = commonLogic.maxId("SP_PRICECYCLE_SETTING", "SETTING_ID");//获取序列id
        param.setSettingId(maxPriceId);
        param.setCrtTime(newDate);
        param.setUpdTime(newDate);
        param.setActTime(newDate);
        Date startDate = DateTimeUtil.parseDate(param.getStartDateStr(),"yyyy-MM-dd HH:mm");
        Date endDate = DateTimeUtil.parseDate(param.getEndDateStr(),"yyyy-MM-dd HH:mm");
        param.setValidTimeStart(startDate);
        param.setValidTimeEnd(endDate);
        this.save(SqlId.SQL_ID_SAVE_PRICE,param);//保存信息
    }
    interface SqlId {
        static final String SQL_ID_FIND_ONE = "findOne";//获取供应商显示页面权限
        static final String SQL_ID_UPDATE_VIEW = "updateView";//更新供应商显示页面权限
        static final String SQL_ID_UPDATE_VIEW_INFO = "updateViewInfo";//更新供应商显示页面权限
        static final String SQL_ID_CONST_RATIO = "findConstRatio";//获取公共属性数据
        static final String SQL_ID_UPDATE_DECLARE = "updateDeclare";//更新供应商无库存是否可以报价
        static final String SQL_ID_FIND_PRICE_LIST = "findPriceList";//获取上一个价盘周期以后的列表
        static final String SQL_ID_DEL_PRICE = "delPrice";//删除其他价盘有效信息
        static final String SQL_ID_SAVE_PRICE = "savePrice";//保存有效价盘周期信息
        static final String SQL_ID_GET_PRICE_CYCLE_CODE = "getPriceCycleCode";//获取有效价盘周期
    }
}
