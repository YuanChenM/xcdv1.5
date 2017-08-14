package com.msk.seller.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdMctStdDiscussProvider;
import com.msk.core.entity.SlPdMctStdNew;
import com.msk.core.entity.SlProduct;
import com.msk.product.bean.MctStdBean;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.ProductStdResult;
import com.msk.seller.bean.ProductStdBean;
import com.msk.seller.bean.SL241118Bean;
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
 * 卖家产品技术标准Logic
 *
 * @author gyh
 */

@Service
public class SL241118Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     *
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_MODIFY_SL_PRODUCT = "modifySlProduct";
        static final String SQL_ID_FIND_SL_PD_MCT_STD_NEW = "findSlPdMctStdNew";
        static final String  SQL_ID_FIND_SL_PD_MCT_STD_NEWS = "findSlPdMctStdNews";
        static final String  SQL_ID_SL_PD_MCT_COUNT = "findSLPdMctCount";
        // 标准数据信息补全
        static final String SQL_ID_COMPLETE_MCT_DATE = "completeMctDate";
    }
    /**
     * 修改卖家产品信息的产品技术标准定级
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
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(SL241118Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    /**
     * 根据条件查询卖家该产品加工技术标准
     * @param param 卖家编码、卖家产品id、产品标准ID、产品标准项目ID
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SlPdMctStdNew> findSlPdMctStdNew(BaseParam param){
        return this.findList(SqlId.SQL_ID_FIND_SL_PD_MCT_STD_NEW,param);
    }
    /**
     * 根据条件批量查询卖家该产品加工技术标准
     * @param param 卖家编码、卖家产品id、产品标准ID、产品标准项目ID
     * @return 结果
     */
    @Transactional(readOnly = true)
    public List<SlPdMctStdNew> findSlPdMctStdNews(BaseParam param){
        return this.findList(SqlId.SQL_ID_FIND_SL_PD_MCT_STD_NEWS,param);
    }
    /**
     * 调用接口批量保存不同意数据到卖家产品技术标准不同意表中
     * liu_yan2
     * @param providers 参数
     * @return 结果
     */
    @Transactional(readOnly = false)
    public int saveNotAgree(List<PdMctStdDiscussProvider> providers) {
        int result = 0;
        if (!CollectionUtils.isEmpty(providers) && providers.size() > 0) {
            PDInfoParam pdInfoParam = new PDInfoParam();
            pdInfoParam.setPdMctStdDiscussProviders(providers);
            result = ISLRestUtil.batchInsertMctProvider(pdInfoParam);
        }
        return result;
    }

    /**
     * 根据条件查询卖家该产品加工质量标准
     * 管忠恒
     * @param slPdMctStdNewList
     * @return 结果
     */
    @Transactional(readOnly = false)
    public void findSlPdMctStdList(List<SlPdMctStdNew> slPdMctStdNewList){
        Date nowDate = DateTimeUtil.getCustomerDate();
        for(SlPdMctStdNew slPdMctStdNew:slPdMctStdNewList){
            int count = this.getCount(SqlId.SQL_ID_SL_PD_MCT_COUNT,slPdMctStdNew);
            if(count>= NumberConst.IntDef.INT_ONE){
                slPdMctStdNew.setUpdTime(nowDate);
                this.modify(slPdMctStdNew);
            }else{
                slPdMctStdNew.setCrtTime(nowDate);
                this.save(slPdMctStdNew);
            }
        }
    }


    /**
     * 补全加工标准前台数据信息
     */
    @Transactional
    public List<SL241118Bean> completeTncDate(ProductStdResult result,String slCode,Integer slPdId){
        List<SL241118Bean> resultBean = new ArrayList<SL241118Bean>();
        List<MctStdBean> mctList = result.getMctStdList();
        ProductStdBean pro = new ProductStdBean();
        pro.setMctList(mctList);
        pro.setSlCode(slCode);
        pro.setSlPdId(slPdId);
        List<SL241118Bean> list = this.findList(SqlId.SQL_ID_COMPLETE_MCT_DATE,pro);
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<SL241118Bean>();
        }
        // 基于数据库表结构 该处判断出的
        for(MctStdBean tnc:mctList){
            SL241118Bean bean = new SL241118Bean();
            bean.setSlCode(slCode);
            bean.setSlPdId(slPdId);
            BeanUtils.copyProperties(tnc, bean);
            for(int i=0;i<list.size();i++){
                SL241118Bean slbean = list.get(i);
                if(bean.getMctStdItemId().equals(slbean.getStdItemId())
                        && bean.getStandardId().equals(slbean.getStandardId())){
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
