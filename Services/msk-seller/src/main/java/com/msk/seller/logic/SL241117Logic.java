package com.msk.seller.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.PdStandard;
import com.msk.core.entity.PdTncStdDiscussProvider;
import com.msk.core.entity.SlPdTncStdNew;
import com.msk.core.entity.SlProduct;
import com.hoperun.core.utils.StringUtil;
import com.msk.product.bean.ProductStdResult;
import com.msk.product.bean.TncStdBean;
import com.msk.seller.bean.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.seller.utils.ISLRestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 卖家产品加工质量标准Logic
 *
 * @author gyh
 */

@Service
public class SL241117Logic extends BaseLogic {

    @Autowired
    private Sl241116Logic sl241116Logic;
    @Autowired
    private SL241106Logic sl241106Logic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {

        static final String SQL_ID_MODIFY_SL_PRODUCT = "modifySlProduct";
        static final String SQL_ID_GET_MCT_COUNT = "getMctCount";
        static final String SQL_ID_GET_TNC_COUNT = "getTncCount";
        static final String SQL_ID_GET_STD_COUNT = "getStdCount";
        // 查询数据是否存在
        static final String SQL_ID_FIND_TNC_STD_COUNT = "findTncStdCount";
        // 查询数据是否存在
        static final String SQL_ID_FIND_MCT_STD_COUNT = "findMctStdCount";
        // 查询数据是否存在
        static final String SQL_ID_OTHER_STD_COUNT = "otherStdCount";
        // 标准数据信息补全
        static final String SQL_ID_COMPLETE_TNC_DATE = "completeTncDate";
    }

    /**
     * 加工技术标准同意信息
     *
     * @param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SlPdStdAgreeFlgBean> getMctCount(BaseParam param) {
        return this.findList(SqlId.SQL_ID_GET_MCT_COUNT, param);
    }

    /**
     * 加工质量标准同意信息
     *
     * @param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SlPdStdAgreeFlgBean> getTncCount(BaseParam param) {
        return this.findList(SqlId.SQL_ID_GET_TNC_COUNT, param);
    }

    /**
     * 检查其他标准是否同意信息
     *
     * @param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public boolean getStdCount(BaseParam param) {
        // 判断产品二级分类是否为白条
        if ("1".equals(param.getFilterMap().get("machiningCode"))) {
            param.setFilter("stdFlg", "1");
            if (this.getCount(SqlId.SQL_ID_GET_STD_COUNT, param) < 1) {
                return false;
            }
            param.setFilter("stdFlg", "2");
            if (this.getCount(SqlId.SQL_ID_GET_STD_COUNT, param) < 1) {
                return false;
            }
        }
        param.setFilter("stdFlg", "3");
        if (this.getCount(SqlId.SQL_ID_GET_STD_COUNT, param) < 1) {
            return false;
        }
        param.setFilter("stdFlg", "4");
        if (this.getCount(SqlId.SQL_ID_GET_STD_COUNT, param) < 1) {
            return false;
        }
        param.setFilter("stdFlg", "5");
        if (this.getCount(SqlId.SQL_ID_GET_STD_COUNT, param) < 1) {
            return false;
        }
        return true;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 检查卖家产品加工技术标准、其他标准，包装标准是否符合要求
     *
     * @param param 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public String checkAgree(BasePageParam param) {
        String auditStatus = null;
        auditStatus = StringUtil.toSafeString(param.getFilterMap().get("auditStatus"));
        // 判断加工技术标准是否存在不同意
        List<SlPdStdAgreeFlgBean> agreeFlgBeans = this.getMctCount(param);
        if (!CollectionUtils.isEmpty(agreeFlgBeans) && agreeFlgBeans.size() > 0) {
            for (SlPdStdAgreeFlgBean bean : agreeFlgBeans) {
                // 不同意
                if ("0".equals(bean.getAgreeFlg())) {
                    return "该卖家产品的加工技术标准存在不同意！请检查后提交";
                }
            }
        } else {
            return "该卖家产品的加工技术标准不存在！请检查后提交";
        }
        // 加工技术标准全部同意
        // 检查卖家产品加工技术标准定级是否为【合格】
        List<SL241116Bean> sl241116Beans = sl241116Logic.findSlProductList(param);
        if (!CollectionUtils.isEmpty(sl241116Beans) && sl241116Beans.size() > 0) {
            // 不合格
            if (sl241116Beans.get(0).getSlQltGradeCode() != 2) {
                return "该卖家产品的加工技术标准定级不规范！请检查后提交";
            }
            if (auditStatus != null && auditStatus.length() > 0 && sl241116Beans.get(0).getTncAuditStatus() != 1) {
                return "请先定级该产品加工质量标准!";
            }
        }
        // 判断加工质量标准是否存在不同意
        List<SlPdStdAgreeFlgBean> agreeFlgBeans1 = this.getTncCount(param);
        if (!CollectionUtils.isEmpty(agreeFlgBeans1) && agreeFlgBeans1.size() > 0) {
            for (SlPdStdAgreeFlgBean bean : agreeFlgBeans1) {
                // 不同意
                if ("0".equals(bean.getAgreeFlg())) {
                    return "该卖家产品的加工质量标准存在不同意！请检查后提交";
                }
            }
        } else {
            return "该卖家产品的加工质量标准不存在！请检查后提交";
        }
        // 加工质量标准全部同意
        // 检查卖家产品加工质量标准定级是否为【A1/A2/A3】
        if (!CollectionUtils.isEmpty(sl241116Beans) && sl241116Beans.size() > 0) {
            // 不合格
            if (sl241116Beans.get(0).getSlTncGradeCode() != 1 && sl241116Beans.get(0).getSlTncGradeCode() != 2
                    && sl241116Beans.get(0).getSlTncGradeCode() != 3) {
                return "该卖家产品的加工质量标准定级不规范！请检查后提交";
            }
            if (auditStatus != null && auditStatus.length() > 0 && sl241116Beans.get(0).getQltAuditStatus() != 1) {
                return "请先定级该产品加工技术标准!";
            }
        }
        // 加工技术标准，加工技术等级，其他指标都符合
        // 包装标准是否存在
        // liu_yan2 因为要调用接口，重写了findPageList
        List<SL241106Bean> sl241106Beans = sl241106Logic.findPageList(param);
        if (CollectionUtils.isEmpty(sl241106Beans) && sl241106Beans.size() < 1) {
            return "该卖家产品的包装标准不存在！请检查后提交";
        }

        // 判断其他指标是否存在
        if (!this.getStdCount(param)) {
            return "该卖家产品的其他标准不存在！请检查后提交";
        }
        return "1";
    }

    /**
     * 调用接口批量 保存不同意数据到卖家产品技术标准不同意表中
     * liu_yan2
     * 
     * @param providers 参数
     * @return 结果
     */
    @Transactional
    public int saveNotAgree(List<PdTncStdDiscussProvider> providers) {
        int result = 0;
        if (!CollectionUtils.isEmpty(providers) && providers.size() > 0) {
            PDInfoParam pdInfoParam = new PDInfoParam();
            pdInfoParam.setTncProviders(providers);
            result = ISLRestUtil.batchInsertTncProvider(pdInfoParam);
        }
        return result;
    }

    /**
     * 修改卖家产品信息的产品质量标准定级
     *
     * @param slProduct 参数
     * @return 结果
     */
    @Transactional(readOnly = false)
    public int modifySlProduct(SlProduct slProduct) {
        slProduct.setUpdTime(DateTimeUtil.getCustomerDate());
        return super.modify(SqlId.SQL_ID_MODIFY_SL_PRODUCT, slProduct);
    }

    /**
     * 检查卖家产品加工技术标准、其他标准，包装标准是否符合要求
     * 该出条件确认 都是通过 OLD代码逻辑 查看对应 SQL得出
     * 
     * @param paramList 参数
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SlProduct> checkAgreeNew(List<SlProduct> paramList) {
        // 判断加工技术标准是否存在不同意(数据不存在\返回AgreeFlg不为1:是的话条件不满足)
        // 用于收集不满足条件的
        List<SlProduct> errParamList = new ArrayList<SlProduct>();
        // 用于收集满足条件数据 减少冗余数据的执行
        List<SlProduct> sucParamList = new ArrayList<SlProduct>();
        // 判断加工技术标准是否存在不同意
        for (SlProduct param : paramList) {
            int count = this.getCount(SqlId.SQL_ID_FIND_MCT_STD_COUNT, param);
            if (count >= NumberConst.IntDef.INT_ONE) {
                sucParamList.add(param);
            } else {
                errParamList.add(param);
            }
        }
        // 检查卖家产品加工技术标准定级是否为【合格】
        // 检查卖家产品加工质量标准定级是否为【A1/A2/A3】
        for (int i = 0; i < sucParamList.size(); i++) {
            SlProduct param = sucParamList.get(i);
            int count = sl241116Logic.findSlProductCount(param);
            if (count < NumberConst.IntDef.INT_ONE) {
                // 数据不存在移除该目标+加入错误列表中
                errParamList.add(param);
                sucParamList.remove(i);
                i = i - 1;
            }
        }
        // 判断加工质量标准是否存在不同意
        for (int i = 0; i < sucParamList.size(); i++) {
            SlProduct param = sucParamList.get(i);
            SlPdTncStdNew slPdTncStdNew = new SlPdTncStdNew();
            slPdTncStdNew.setSlPdId(param.getSlPdId());
            slPdTncStdNew.setSlCode(param.getSlCode());
            slPdTncStdNew.setAgreeFlg("1");
            int count = this.getCount(SqlId.SQL_ID_FIND_TNC_STD_COUNT, slPdTncStdNew);
            if (count < NumberConst.IntDef.INT_ONE) {
                // 数据不存在移除该目标+加入错误列表中
                errParamList.add(param);
                sucParamList.remove(i);
                i = i - 1;
            }
        }
        // 加工技术标准，加工技术等级，其他指标都符合
        // 包装标准是否存在
        for (int i = 0; i < sucParamList.size(); i++) {
            SlProduct param = sucParamList.get(i);
            int count = sl241106Logic.findSlPdPkgCount(param);
            if (count < NumberConst.IntDef.INT_ONE) {
                // 数据不存在移除该目标+加入错误列表中
                errParamList.add(param);
                sucParamList.remove(i);
                i = i - 1;
            }
        }
        // 判断其他指标是否存在
        this.checkOtherStd(sucParamList, errParamList);
        return errParamList;
    }

    /**
     * 批量修改卖家产品信息的产品质量标准定级
     * 管忠恒
     * @param slProductList 参数
     * @return 结果
     */
    @Transactional
    public void modifySlProduct(List<SL241116Bean> slProductList) {
        for (SL241116Bean bean : slProductList) {
            bean.setUpdTime(DateTimeUtil.getCustomerDate());
            super.modify(SqlId.SQL_ID_MODIFY_SL_PRODUCT, bean);
        }
    }

    /**
     * 根据条件查询卖家该产品加工质量标准
     * 管忠恒
     * @param slPdTncStdNewList
     * @return 结果
     */
    @Transactional
    public void findSlPdTncStdList(List<SlPdTncStdNew> slPdTncStdNewList) {
        Date nowDate = DateTimeUtil.getCustomerDate();
        for (SlPdTncStdNew slPdTncStdNew : slPdTncStdNewList) {
            int count = this.getCount(SqlId.SQL_ID_FIND_TNC_STD_COUNT, slPdTncStdNew);
            if (count >= NumberConst.IntDef.INT_ONE) {
                // 存在做修改操作
                slPdTncStdNew.setUpdTime(nowDate);
                this.modify(slPdTncStdNew);
            } else {
                // 数据不存在插入操作
                slPdTncStdNew.setCrtTime(nowDate);
                this.save(slPdTncStdNew);
            }
        }
    };

    /**
     * 其他标准同意信息
     * 管忠恒 替代 getStdCount方法
     * 
     * @param paramList
     * @return 结果
     */
    @Transactional(readOnly = true)
    public void checkOtherStd(List<SlProduct> paramList, List<SlProduct> errParamList) {
        // 判断产品二级分类是否为白条
        for (SlProduct param : paramList) {
            BaseParam baseParam = new BaseParam();
            baseParam.setFilter("slCode", param.getSlCode());
            baseParam.getFilterMap().put("slPdId", param.getSlPdId());
            if ("1".equals(param.getMachiningCode())) {
                baseParam.getFilterMap().put("setFlgs", new String[] { "1", "2" });
            } else {
                baseParam.getFilterMap().put("setFlgs", new String[] { "5", "4", "3" });
            }
            int count = this.getCount(SqlId.SQL_ID_OTHER_STD_COUNT, baseParam);
            if (count < NumberConst.IntDef.INT_ONE) {
                errParamList.add(param);
            }
        }
    }

    /**
     * 补全加工标准前台数据信息
     */
    @Transactional
    public List<SL241117Bean> completeTncDate(ProductStdResult result, String slCode, Integer slPdId) {
        List<SL241117Bean> resultBean = new ArrayList<SL241117Bean>();
        ProductStdBean pro = new ProductStdBean();
        List<TncStdBean> tncList = result.getTncStdList();
        pro.setTncList(tncList);
        pro.setSlCode(slCode);
        pro.setSlPdId(slPdId);
        List<SL241117Bean> list = this.findList(SqlId.SQL_ID_COMPLETE_TNC_DATE, pro);
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<SL241117Bean>();
        }
        // 基于数据库表结构 该处判断出的
        for (TncStdBean tnc : tncList) {
            SL241117Bean bean = new SL241117Bean();
            bean.setSlCode(slCode);
            bean.setSlPdId(slPdId);
            BeanUtils.copyProperties(tnc, bean);
            for (int i = 0; i < list.size(); i++) {
                SL241117Bean slbean = list.get(i);
                if (bean.getTncStdItemId().equals(slbean.getStdItemId())
                        && bean.getStandardId().equals(slbean.getStandardId())) {
                    bean.setAgreeFlg(slbean.getAgreeFlg());
                    bean.setStdVal(slbean.getStdVal());
                    list.remove(i);
                    i = i - 1;
                    break;
                }
            }
            resultBean.add(bean);
        }
        return resultBean;
    }
}
