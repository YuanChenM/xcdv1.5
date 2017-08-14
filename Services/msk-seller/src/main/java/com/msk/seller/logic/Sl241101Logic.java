package com.msk.seller.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.business.constant.SellerConstant;
import com.msk.common.config.CodeMasterManager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.seller.bean.SL241101Bean;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 卖家审批审核列表Logic.
 *
 * @author gyh
 */
public class Sl241101Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * 
     * @author gyh
     */
    interface SqlId {
        static final String SQL_ID_FIND_SL_CODE_LIST = "findSlCodeList";
        static final String SQL_ID_FIND_PAGE_INFO = "findPageInfo";
    }

    /**
     * 查询卖家编码和企业名称     *
     *
     * @param slCodeList
     * @return
     * @author zhang_chi
     */
    @Transactional(readOnly = true)
    public List<SL241101Bean> findDataList(List<String> slCodeList){
        BasePageParam param = new BasePageParam();
        param.getFilterMap().put("slCodeList",slCodeList);
        List<SL241101Bean> beans = super.findList(SqlId.SQL_ID_FIND_SL_CODE_LIST, param);
        return beans;
    }


    /**
     * 查询卖家编码和企业名称     *
     *
     * @param
     * @return
     * @author
     */
    @Transactional(readOnly = true)
    public List<SL241101Bean> findPageInfo( BasePageParam basePageParam){
        List<SL241101Bean>  lists =  super.findList(SqlId.SQL_ID_FIND_PAGE_INFO, basePageParam);
        if(CollectionUtils.isNotEmpty(lists)){
            // 从redis  获取 卖家主分类
            Map<String, String> slMainClassMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlMainClass.TYPE);
            for(SL241101Bean bean : lists){
                // 处理 slMainClass
                if(!StringUtil.isNullOrEmpty(bean.getSlMainClass())){
                    String value = slMainClassMap.get(bean.getSlMainClass());
                    if(null != value){
                        bean.setSlMainClass(value);
                    }
                }
            }
        }
        return lists;
    }

    /**
     * 查询分页
     *
     * @param
     * @return
     * @author
     */
    @Transactional(readOnly = true)
    public PageResult<SL241101Bean> findPageResult(BasePageParam basePageParam){
        PageResult<SL241101Bean>  result =  this.findPage(basePageParam, SL241101Bean.class);
        if(result.getRecordsTotal() != NumberConst.IntDef.INT_ZERO){
            List<SL241101Bean>  lists = result.getData();
            // 从redis  获取 卖家主分类
            Map<String, String> slMainClassMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlMainClass.TYPE);
            for(SL241101Bean bean : lists){
                // 处理 slMainClass
                if(!StringUtil.isNullOrEmpty(bean.getSlMainClass())){
                    String value = slMainClassMap.get(bean.getSlMainClass());
                    if(null != value){
                        bean.setSlMainClass(value);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 查询分页数据List
     *
     * @param
     * @return
     * @author
     */
    @Transactional(readOnly = true)
    public List<SL241101Bean> findPageResultList(BasePageParam basePageParam){
        List<SL241101Bean>  lists =  this.findPageList(basePageParam, SL241101Bean.class);
        if(CollectionUtils.isNotEmpty(lists)){
            // 从redis  获取 卖家主分类
            Map<String, String> slMainClassMap = CodeMasterManager.findCodeMasterMap(SellerConstant.SlMainClass.TYPE);
            for(SL241101Bean bean : lists){
                // 处理 slMainClass
                if(!StringUtil.isNullOrEmpty(bean.getSlMainClass())){
                    String value = slMainClassMap.get(bean.getSlMainClass());
                    if(null != value){
                        bean.setSlMainClass(value);
                    }
                }
            }
        }
        return lists;
    }




    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
