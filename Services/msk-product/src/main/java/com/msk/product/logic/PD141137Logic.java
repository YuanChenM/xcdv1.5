package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdClassestreeMat;
import com.msk.product.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * PD141101Logic
 *
 * @author pxg
 */
@Service
public class PD141137Logic extends BaseLogic {


    /**
     * SQL Map 中SQL ID定义
     *
     * @author pxg
     */
    interface SqlId {
        String SQL_ID_FIND_SL_NAME = "findSlTypeName";
        String SQL_ID_FIND_LIST_SL_PD = "findSlList";
        String SQL_ID_FIND_PD_BLANK = "findPdSlBlank";
        String SQL_ID_FIND_SL_EP_PRO_NAME = "findEpProName";
        String SQL_ID_FIND_SL_CITY = "findSlCity";
        String SQL_ID_FIND_LEVEN4_LIST = "findListLevel4List";
        String SQL_ID_FIND_LEVEN5_LIST = "findListLevel5List";
        String SQL_ID_FIND_LEVEN6_LIST = "findListLevel6List";
        String SQL_ID_FIND_SQY_LIST = "findListsqy";

    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询产品原料信息 xhy
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PdClassestreeMat queryClassMatData(BaseParam param) {
        return super.findOne(param);
    }

    /**
     * 查询卖家类型 xhy
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PD141136Bean findSlType(BaseParam param) {
        PD141136Bean bean = this.findOne(SqlId.SQL_ID_FIND_SL_NAME, param);
        return bean;
    }

    /**
     * 查询总控目录列表所有数据
     *
     * @param param
     * @return PD141137Bean
     */
    public List<PD141137Bean> findListAll(BaseParam param) {
        /*根据传递的id值来查询相应的列表*/
        List<PD141137Bean> beans = super.findList(param);
        //获取供应备案注册线产品及日期 产品特征列表
        BaseParam param1 = new BaseParam();
        if (beans.size() > NumberConst.IntDef.INT_ZERO) {
            for (PD141137Bean slPdList : beans) {
          /*根据产品产品的卖家id查询品牌数据*/
                param.setFilter("slCode", slPdList.getSlCode());
                param.setFilter("brandEpId", slPdList.getBrandEpId());
                param.setFilter("brandId", slPdList.getBrandId());
                PD141137Bean blankBean = super.findOne(SqlId.SQL_ID_FIND_PD_BLANK, param);
                if (blankBean == null) {
                    slPdList.setBrandClass("");
                    slPdList.setBrandName("");
                } else {
                    slPdList.setBrandClass(blankBean.getBrandClass());
                    slPdList.setBrandName(blankBean.getBrandName());
                }
                //获取卖家国籍,生产商名称
                param.setFilter("prodEpId", slPdList.getProdEpId());
                PD141137Bean slName = this.findOne(SqlId.SQL_ID_FIND_SL_EP_PRO_NAME, param);
                if (slName == null) {
                    slPdList.setEpName("");
                } else {
                    slPdList.setEpName(slName.getEpName());
                }
                //国籍
                PD141137Bean slCity = super.findOne(SqlId.SQL_ID_FIND_SL_CITY, param);
                if (slCity == null) {
                    slPdList.setCityName("");
                } else {
                    slPdList.setCityName(slCity.getCityName());
                }
                //获取供应备案注册线产品及日期 产品特征列表
                param1.setFilter("status", String.valueOf(NumberConst.IntDef.INT_ONE));
                param1.setFilter("statusTwo", String.valueOf(NumberConst.IntDef.INT_TWO));
                slPdList.setApplyAndArgList(this.findListAllSlPd(param1, slPdList));

                //获取禁止注册线产品及日期 产品特征列表 3
                param1.setFilter("status", String.valueOf(NumberConst.IntDef.INT_THREE));
                param1.setFilter("statusTwo", null);
                slPdList.setForbidList(this.findListAllSlPd(param1, slPdList));

                //获取试销线产品及日期 产品特征列表 4
                param1.setFilter("status", String.valueOf(NumberConst.IntDef.INT_FOUR));
                slPdList.setMarketingList(this.findListAllSlPd(param1, slPdList));

                //获取5正式上线产品及日期 产品特征列表 5
                param1.setFilter("status", String.valueOf(NumberConst.IntDef.INT_FIVE));
                slPdList.setTopLineList(this.findListAllSlPd(param1, slPdList));

                //获取6正式上线产品及日期 产品特征列表 6
                param1.setFilter("status", String.valueOf(NumberConst.IntDef.INT_SIX));
                slPdList.setDownLineList(this.findListAllSlPd(param1, slPdList));

                //获取7正式上线产品及日期 产品特征列表 7
                param1.setFilter("status", String.valueOf(NumberConst.IntDef.INT_SEVEN));
                slPdList.setBlacklist(this.findListAllSlPd(param1, slPdList));

                //获取8正式上线产品及日期 产品特征列表 8
                param1.setFilter("status", String.valueOf(NumberConst.IntDef.INT_EIGHT));
                slPdList.setBreakGoods(this.findListAllSlPd(param1, slPdList));

                //获取属性结构 获取所有产品特征编码
                param1.setFilter("classesCode", slPdList.getClassesCode());
                param1.setFilter("machiningCode", slPdList.getMachiningCode());
                param1.setFilter("breedCode", slPdList.getBreedCode());
                param1.setFilter("slCode", slPdList.getSlCode());
                List<PD141137FeatureListBean> treeList = super.findList(SqlId.SQL_ID_FIND_LEVEN4_LIST, param1);
                if (treeList.size() > NumberConst.IntDef.INT_ZERO) {
                    for (PD141137FeatureListBean level4 : treeList) {
                        param1.setFilter("featureCode", level4.getFeatureCode());
                        //获取五级tree
                        List<PD141137WeightListBean> treeList2 = super.findList(SqlId.SQL_ID_FIND_LEVEN5_LIST, param1);
                        if (treeList2.size() > NumberConst.IntDef.INT_ZERO) {
                        /*获取六级类别*/
                            for (PD141137WeightListBean level5 : treeList2) {
                                param1.setFilter("weightCode", level5.getWeightCode());
                                List<PD141137NormsListBean> treeList3 = super.findList(SqlId.SQL_ID_FIND_LEVEN6_LIST, param1);
                                if (treeList3.size() > NumberConst.IntDef.INT_ZERO) {
                                    level5.setNormsList(treeList3);
                                }
                            }

                        }
                        level4.setWeightList(treeList2);
                    }
                }
                slPdList.setTreeList(treeList);
                //卖家标准编码库存数据查询
            /*List<PD141137SlNumberBean> listNumber =  super.findList(SqlId.SQL_ID_FIND_SQY_LIST,param1);
            if(listNumber.size()> NumberConst.IntDef.INT_ZERO){
                slPdList.setAllNumber(listNumber);
            }*/
            }
            return beans;
        } else {
            List<PD141137Bean> beansNull = new ArrayList<PD141137Bean>();
            PD141137Bean bean = new PD141137Bean();
            beansNull.add(bean);
            return beansNull;
        }


    }

    /**
     * 通用方法
     *
     * @param param
     * @return
     */
    public List<PD141137StautsBean> findListAllSlPd(BaseParam param, PD141137Bean bean) {
        param.setFilter("classesCode", bean.getClassesCode());
        param.setFilter("machiningCode", bean.getMachiningCode());
        param.setFilter("breedCode", bean.getBreedCode());
        param.setFilter("slCode", bean.getSlCode());
        List<PD141137StautsBean> list = new ArrayList<PD141137StautsBean>();
        list = super.findList(SqlId.SQL_ID_FIND_LIST_SL_PD, param);
        if (list.size() > NumberConst.IntDef.INT_ZERO) {
            for (PD141137StautsBean beanStatus : list) {
                if (null!=beanStatus) {
                    if (null!=beanStatus.getStatusMonitorDate()) {
                        beanStatus.setFeatureDate(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, beanStatus.getStatusMonitorDate()));
                        beanStatus.setStatusMonitorDate(null);
                    }else{
                        beanStatus.setFeatureDate("");
                    }
                }
            }
        }
        return list;
    }


}
