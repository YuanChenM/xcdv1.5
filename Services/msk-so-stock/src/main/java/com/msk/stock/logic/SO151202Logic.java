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
import com.msk.stock.bean.SO151201Bean;
import com.msk.stock.bean.SO151202Bean;
import com.msk.stock.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhang_qiang1
 */
@Service
public class SO151202Logic extends BaseLogic {
    @Autowired
    private BaseRedisDao redisDao;

    /**
     *
     * @param basePageParam
     * @return
     */
    public PageResult<SO151202Bean> search  (BasePageParam basePageParam){
          PageResult<SO151202Bean> pageResult=new PageResult<>();
        if(this.isFindPage(basePageParam)){
            pageResult=  super.findPage(basePageParam, SO151202Bean.class);
            this.setSLParmeter(  pageResult);// 设置卖家  供应商 信息
        }else {// 不查询数据库   查询条件  没有返回值
            List<SO151202Bean> list = new ArrayList<>();
            pageResult.setData(list);
        }

        return  pageResult;
    }

    /**
     *
     * @param basePageParam
     * @return
     */
    public boolean isFindPage(BasePageParam basePageParam){
        boolean flag=false;
        String slCodeDis= (String) basePageParam.getFilterMap().get("slCodeDis");
        if(StringUtil.isEmpty(slCodeDis)){
            flag=true;
        }else {
            List<String> slCodeList = new ArrayList<String>();
            //卖家显示编码不为空
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

               if(slCodeList.size()>0){
                   flag=true;
                 basePageParam.getFilterMap().put("slCodeList",slCodeList);
           }
        }
        return  flag;
    }

    /**
     *
     * @param pageResult
     */
    public void setSLParmeter(PageResult<SO151202Bean> pageResult){
        List<SO151202Bean> so151202BeanList= pageResult.getData();
        if(so151202BeanList!=null&&so151202BeanList.size()>0){
            for(SO151202Bean so151201Bean:so151202BeanList){
                this.setSlforBean(so151201Bean);
            }
        }
    }

    /**
     *
     * @param bean
     */
    public void setSlforBean(SO151202Bean bean){
        String dbSalePayFrom=  bean.getSalePlatform();
        String redisSalePayFrom=  this.getsalePlatform().get(dbSalePayFrom);
        bean.setSalePlatform(redisSalePayFrom == null ? "" : redisSalePayFrom);//供货平台
        bean.setSlCodeDis(this.findSLCodeDis(bean.getSlCode()));//显示用卖家编码
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
     * 更新库存量
     * @param param
     */
    public int saveStockQty(BasePageParam param) {
        int result = super.modify(SqlId.SAVESTOCKSL, param);
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
        String SAVESTOCKSL = "saveStockSL";
    }
}
