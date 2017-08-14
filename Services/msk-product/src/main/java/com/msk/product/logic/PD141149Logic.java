package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CodeMasterConst;
import com.msk.core.entity.PdTncStd;
import com.msk.product.bean.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
@Service
public class PD141149Logic extends BaseLogic {


    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {
        static final String SQL_ID_FIND_List_TNC_MARKEY = "findListTncMarkey"; //获取市场需求标准列表
        static final String SQL_ID_FIND_List_TNC_PROVIDER = "findListTncProvider"; //获取供应商习惯标准列表

        static final String SQL_ID_FIND_List_TNC_MARKEY_NO = "findListTncMarkeyNo"; //获取市场需求禁止日列表
        static final String SQL_ID_FIND_List_TNC_PROVIDER_NO = "findListTncProviderNo"; //获取供应商习惯标准列表
        //保存操作
        static final String SQL_ID_FIND_LIST_TNC_STD_COUNT = "findListTncStdCount"; //获取质量加工质量标准指标列表
        static final String SQL_ID_FIND_BREED_NAME_FEANAME = "findName";

        static final String SQL_ID_MODIFY_STD_FLG = "modifyFlg";

    }


    /**
     * @param baseDao
     */
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 页面演示树心结构,查询加工质量标准
     *
     * @param standardId
     * @return PD141149Bean
     */
    @Transactional(readOnly = true)
    public List<PD141149Bean> findInitList(String standardId) {
        BaseParam param = new BaseParam();
        param.setFilter("standardId", standardId);
        param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));
        param.setFilter("parentId", CodeMasterConst.IsAvailable.AVAILABLE);

   /*     查询一级类目*/
        List<PD141149Bean> list = super.findList(param);//查询商品个数
        for (PD141149Bean pd141149Bean : list) {
            //获取加工质量标准值
            if (CodeMasterConst.IsAvailable.AVAILABLE.equals(pd141149Bean.getIsCatalog())) {
                param.setFilter("levelId", CodeMasterConst.OrgLevel.TOW);
                param.setFilter("parentId", pd141149Bean.getTncStdItemId());
                List<PD141149Bean> pd141149BeanChilds = super.findList(param);
                pd141149Bean.setPdTncStdList(pd141149BeanChilds);
            }
            //获取市场需求标准列表
            param.setFilter("tncStdItemId", pd141149Bean.getTncStdItemId());
            param.setFilter("discussStatus", String.valueOf(NumberConst.IntDef.INT_TWO));
            List<PDtncMarkeyBean> tncMarList = super.findList(SqlId.SQL_ID_FIND_List_TNC_MARKEY, param);
            if (tncMarList != null && tncMarList.size() > NumberConst.IntDef.INT_ZERO) {
                for (PDtncMarkeyBean marBean : tncMarList) {
                    marBean.setFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, marBean.getFixDate()));
                    marBean.setRaiseDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, marBean.getRaiseDate()));
                }
                pd141149Bean.setPdTncMarkeyList(tncMarList);

            }

            //获取供应商习惯性标准
            List<PDtncProviderBean> tncProList = super.findList(SqlId.SQL_ID_FIND_List_TNC_PROVIDER, param);
            if (tncProList != null && tncMarList.size() > NumberConst.IntDef.INT_ZERO) {
                for (PDtncProviderBean proBean : tncProList) {
                    proBean.setProFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, proBean.getFixDate()));
                    proBean.setProRaiseDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, proBean.getRaiseDate()));
                }
                pd141149Bean.setPdTncProviderList(tncProList);
            }

            //获取市场需求禁止日
            List<PDtncMarkeyBean> tncMarListNo = super.findList(SqlId.SQL_ID_FIND_List_TNC_MARKEY_NO, param);
            if (tncMarListNo != null && tncMarListNo.size() > NumberConst.IntDef.INT_ZERO) {
                for (PDtncMarkeyBean marBean : tncMarListNo) {
                    marBean.setFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, marBean.getFixDate()));
                }
                pd141149Bean.setPdTncMarkeyNoList(tncMarListNo);
            }

            //获取供应商禁止日
            List<PDtncProviderBean> tncProListNo = super.findList(SqlId.SQL_ID_FIND_List_TNC_PROVIDER_NO, param);
            if (tncProListNo != null && tncProListNo.size() > NumberConst.IntDef.INT_ZERO) {
                for (PDtncProviderBean proBean : tncProListNo) {
                    proBean.setProFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, proBean.getFixDate()));
                }
                pd141149Bean.setPdTncProviderNoList(tncProListNo);
            }
        }
        return list;
    }


    /**
     * 保存修改产品加工质量数据
     *
     * @param bean
     * @author xhy
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void SaveAndEditTnc(PD141149Param bean,BaseParam param) {
        if (StringUtils.isBlank(bean.getStandardId().toString())) throw new BusinessException("保存数据异常,请联系管理员!");
        param.setFilter("standardId", bean.getStandardId().toString());
        //判断数据库中是否存在数据
        int count = super.getCount(SqlId.SQL_ID_FIND_LIST_TNC_STD_COUNT, param);
        //保存修改操作
        if (count <= NumberConst.IntDef.INT_ZERO) {
              /* 保存操作*/
            for (int i = NumberConst.IntDef.INT_ZERO; i < bean.getPdTncTdItemIdArray().length; i++) {
                PdTncStd pdTncStd = new PdTncStd();
                pdTncStd.setStandardId(bean.getStandardId());
                pdTncStd.setTncStdItemId(bean.getPdTncTdItemIdArray()[i]);
                pdTncStd.setTncStdVal1(bean.getContent1Array()[i]);
                pdTncStd.setTncStdVal2(bean.getContent2Array()[i]);
                pdTncStd.setTncStdVal3(bean.getContent3Array()[i]);
                pdTncStd.setRemark(bean.getRemarkArray()[i]);
                pdTncStd.setUpdId(param.getUpdId());
                pdTncStd.setCrtId(param.getCrtId());
                /**Add: 横展开添加共通设置 2016/09/27   BY  任强  Start */
                pdTncStd.setActId(param.getActId());
                /**Add: 横展开添加共通设置 2016/09/27   BY  任强  End */
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                pdTncStd.setActTime(new Date());
                pdTncStd.setCrtTime(new Date());
                pdTncStd.setUpdTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                int saveTnc = super.save(pdTncStd);
                if (saveTnc <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("保存数据异常,请联系管理员!");
            }
            /*param.setFilter("flg", String.valueOf(NumberConst.IntDef.INT_ONE));
            int exist =    super.modify(SqlId.SQL_ID_MODIFY_STD_FLG,param);
            if(exist<= NumberConst.IntDef.INT_ZERO)throw new BusinessException("更新产品加工质量标准标识异常,请联系管理员!");*/
        } else {
            //修改操作
            for (int i = NumberConst.IntDef.INT_ZERO; i < bean.getPdTncTdItemIdArray().length; i++) {
                PdTncStd pdTncStd = new PdTncStd();
                pdTncStd.setStandardId(bean.getStandardId());
                pdTncStd.setTncStdItemId(bean.getPdTncTdItemIdArray()[i]);
                pdTncStd.setTncStdVal1(bean.getContent1Array()[i]);
                pdTncStd.setTncStdVal2(bean.getContent2Array()[i]);
                pdTncStd.setTncStdVal3(bean.getContent3Array()[i]);
                pdTncStd.setRemark(bean.getRemarkArray()[i]);
                pdTncStd.setUpdId(param.getUpdId());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                pdTncStd.setUpdTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                int modifyTnc = super.modify(pdTncStd);
                if (modifyTnc <= NumberConst.IntDef.INT_ZERO) throw new BusinessException("修改数据异常,请联系管理员!");
            }
        }
    }
    /**
     * 获取产品品种数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141124showNameBean> findBreedNameAndFea(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_BREED_NAME_FEANAME, param);
    }
}
