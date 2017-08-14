package com.msk.stock.logic;


import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.hoperun.jdbc.redis.BaseRedisDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.RedisDataBaseDef;
import com.msk.seller.bean.ISL231193RsParam;
import com.msk.seller.bean.ISL231193RsResult;
import com.msk.stock.bean.*;
import com.msk.stock.util.ControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * zhang_qiang1   分销正常库存
 */
@Service
public class SO151201Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SO151201Logic.class);

    @Autowired
    private BaseRedisDao redisDao;


    /**
     *    查询销售平台
     * @return
     */
    public TreeMap<String,String> getsalePlatform(){
        redisDao.setDatabase(RedisDataBaseDef.CODE_MASTER_DB);
        Map<String, String> codeMasterMap = redisDao.getRedisMapValue("SupplyPlatform");//  从redis  获取平台参数结合
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(codeMasterMap);
        return  treeMap;

    }



    /**
     *
     * @param basePageParam
     * @return
     */
    public  PageResult<SO151201Bean>  search  (BasePageParam basePageParam){
        PageResult<SO151201Bean> pageResult=new PageResult<>();
         if(this.isFindPage(basePageParam)){
             pageResult =  super.findPage(basePageParam, SO151201Bean.class);
             this.setSLParmeter(  pageResult);// 设置卖家  供应商 信息
         }else {// 不查询数据库   查询条件  没有返回值
             List<SO151201Bean>list=new ArrayList<>();
             pageResult.setData(list);
         }

        return  pageResult;
    }


    /**
     *
     * @param basePageParam
     * @return
     */
    public boolean  isFindPage(BasePageParam basePageParam){
        boolean flag=false;
        String supplierCode= (String) basePageParam.getFilterMap().get("supplierCode");
        String supplierName= (String) basePageParam.getFilterMap().get("supplierName");
        String slCodeDis= (String) basePageParam.getFilterMap().get("slCodeDis");
        if(StringUtil.isNullOrEmpty(supplierCode)&&StringUtil.isNullOrEmpty(supplierName)&&StringUtil.isNullOrEmpty(slCodeDis)){
            flag=true;
        }else {
            //供应商编码不为空
            Set<String> supplierCodeSet = new HashSet<String>();
            if (!StringUtil.isNullOrEmpty(supplierCode)) {
                ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
                isl231193RsParam.setSlCodeDis(supplierCode);
                //根据供应商编码查询卖家编码
                List<ISL231193RsResult> isl231193RsResultList = ControllerUtil.getSlCode(isl231193RsParam, NumberConst.IntDef.INT_ONE);
                for (ISL231193RsResult isl : isl231193RsResultList) {
                    if (!StringUtil.isNullOrEmpty(isl.getSlCode())) {
                        supplierCodeSet.add(isl.getSlCode());
                    }
                }
            }

            //供应商名称不为空
            if (!StringUtil.isNullOrEmpty(supplierName)) {
                ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
                isl231193RsParam.setEpName(supplierName);
                //根据供应商名称查询卖家编码
                List<ISL231193RsResult> isl231193RsResultList = ControllerUtil.getSlCode(isl231193RsParam, NumberConst.IntDef.INT_TWO);
                for (ISL231193RsResult bean : isl231193RsResultList) {
                    supplierCodeSet.add(bean.getSlCode());
                }
            }
            List<String> supplierList = new ArrayList<>();
            supplierList.addAll(supplierCodeSet);

            //卖家显示编码不为空
            List<String> slCodeList = new ArrayList<String>();
            if (!StringUtil.isNullOrEmpty(slCodeDis)) {
                if(this.isAllContants(slCodeDis)){
                    slCodeList.add("0000000");
                }else {
                    ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
                    isl231193RsParam.setSlCodeDis(slCodeDis);
                    //根据卖家显示编码查询卖家编码
                    List<ISL231193RsResult> isl231193RsResultList = ControllerUtil.getSlCode(isl231193RsParam, NumberConst.IntDef.INT_ONE);
                    for (ISL231193RsResult isl : isl231193RsResultList) {
                        slCodeList.add(isl.getSlCode());
                    }
                }
            }

            if(slCodeList.size()>0||supplierList.size()>0){
                basePageParam.getFilterMap().put("slCodeList", slCodeList);
                basePageParam.getFilterMap().put("supplierCodeList", supplierList);
                flag=true;
            }
        }
        return  flag;
    }



    /**
     *
     * @param pageResult
     */
    public void setSLParmeter(PageResult<SO151201Bean> pageResult){
       List<SO151201Bean> so151201BeanList= pageResult.getData();
        if(so151201BeanList!=null&&so151201BeanList.size()>0){
            for(SO151201Bean so151201Bean:so151201BeanList){
              this.setSlforBean(so151201Bean);
            }
        }
    }


    /**
     *
     * @param bean
     */
  public void setSlforBean(SO151201Bean bean){
      String dbSupplyPayFrom=  bean.getSupplyPlayFrom();
      String redisSuuplyPayFrom=  this.getsalePlatform().get(dbSupplyPayFrom);
      bean.setSupplyPlayFrom(redisSuuplyPayFrom==null?"":redisSuuplyPayFrom);//供货平台

      bean.setSlCodeDis(this.findSLCodeDis(bean.getSlCode()));//显示用卖家编码
      bean.setSupplierName(this.findSuuplieName(bean.getSupplierCode()));// 设置供应商名称
      bean.setSupplierCode(this.findSuuplieCode(bean.getSupplierCode()));// 设置 供应商编码
  }


    /**
     *  获取slCode     //根据卖家显示编码查询卖家编码
     * @param slCode
     * @return
     */
    public String findSLCodeDis(String slCode){
        String slCodeDis=" ";
        if("0000000".equals(slCode)){
            slCodeDis=slCode;
        }else {
            ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
            List<String>slCodeList=  new ArrayList<>();
            slCodeList.add(slCode);
            isl231193RsParam.setSlCodeList(slCodeList);
            List<ISL231193RsResult> isl231193RsResultList = ControllerUtil.getSlCode(isl231193RsParam, NumberConst.IntDef.INT_ONE);
            if(isl231193RsResultList!=null&&isl231193RsResultList.size()>0){
                slCodeDis=isl231193RsResultList.get(0).getSlCodeDis();
            }
        }
        return slCodeDis;
    }


    /**
     *
     * @return
     */
    public String findSuuplieCode(String suuplieCode){
     String finalSupperlierCode= " ";
        ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
        List<String>slCodeList=  new ArrayList<>();
        slCodeList.add(suuplieCode);
        isl231193RsParam.setSlCodeList(slCodeList);
        List<ISL231193RsResult> isl231193RsResultList = ControllerUtil.getSlCode(isl231193RsParam, NumberConst.IntDef.INT_ONE);
        if(isl231193RsResultList!=null&&isl231193RsResultList.size()>0){
            finalSupperlierCode=isl231193RsResultList.get(0).getSlCodeDis();
        }
        return  finalSupperlierCode;
    }


    /***
     *
     * @return
     */
    public String findSuuplieName(String suuplieCode){
       String finalSuuperlierName=" " ;
        ISL231193RsParam isl231193RsParam = new ISL231193RsParam();
        List<String>slCodeList=  new ArrayList<>();
        slCodeList.add(suuplieCode);
        isl231193RsParam.setSlCodeList(slCodeList);
        List<ISL231193RsResult> isl231193RsResultList = ControllerUtil.getSlCode(isl231193RsParam, NumberConst.IntDef.INT_TWO);
        if(isl231193RsResultList!=null&&isl231193RsResultList.size()>0){
            finalSuuperlierName=isl231193RsResultList.get(0).getEpName();
        }
        return  finalSuuperlierName;
    }


    /**
     * 因数据库查询 如果slCode =0000000 就直接显示 0000000
     * @param slCodeDis
     * @return
     */
      public boolean isAllContants(String slCodeDis){
          boolean flag=true;
          char[] chars= slCodeDis.toCharArray();
          if(chars.length<8){
              for (char c:chars){
                  if (!String.valueOf(c).equals("0")){
                      flag=false;
                      break;
                  }
              }
          }
          return  flag;
      }

    /**
     * 更新库存量
     * @param param
     */
    public int saveStockQty(BasePageParam param) {
        int result = super.modify(SqlId.SAVESTOCKQTY, param);
        return result;
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * SqlId. 方法名
     */
    interface SqlId {
        String SAVESTOCKQTY = "saveStockQty";
    }

}
