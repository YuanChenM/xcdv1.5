package com.msk.batch.sp.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.batch.sp.bean.BSP171103Bean;
import com.msk.batch.sp.bean.BSP171103Param;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.logic.CommonLogic;
import com.msk.common.utils.RestClientUtil;
import com.msk.price.bean.PriceCycleParam;
import com.msk.product.bean.ProductPageResult;
import com.msk.product.bean.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 物流区产品同步
 * Created by ni_shaotang on 2016/5/18.
 */
@Service
public class BSP171103Logic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BSP171103Logic.class);
    /**
     * CommonLogic
     */
    @Autowired
    private BSP171104Logic bsp171104Logic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    /**
     * 数据处理
     * @param param
     */
    @Transactional
    public void dataResolve(BaseParam param) throws Exception{
        BSP171103Param dataParam = (BSP171103Param)param;
        List<BSP171103Bean> list = super.findList (SqlId.SQLID_SELECT_LOGS_PRODUCT,dataParam);//获取同步数据
        if(null != list && list.size() > 0) {
            int num = bsp171104Logic.copayProduct(list);
            if (num > 0) {
                super.remove(dataParam);//删除同步数据
            }
        }
        List<BSP171103Bean> newList=getPDSup();
        int nn = 0;
        if(null != newList && newList.size() > 0) {
            for(int i=0;i< newList.size();i+=50){
                nn=i+50;
                if(nn > newList.size()){
                    nn = newList.size();
                }

                super.batchSave(newList.subList(i,nn));//添加新的同步数据
            }
        }
    }

    /**
     * 获取物流区产品数据
     * @return List
     * @throws Exception
     */
    public List<BSP171103Bean> getPDSup() throws Exception {
        List<BSP171103Bean> list = new ArrayList<BSP171103Bean>();

        RsRequest<PDInfoParam> requestParam = new RsRequest<PDInfoParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        PDInfoParam param= new PDInfoParam();
        param.setGradeFlag("1");
        requestParam.setParam(param);
        //url

        String url = ConfigManager.getMskProductService() + ConfigManager.getProductGetPdSuppService();
        //请求接口
        RsResponse<ProductPageResult> response= RestClientUtil.post(url, requestParam, new TypeReference<RsResponse<ProductPageResult>>() {
        });
        Date nowDate =  DateTimeUtil.getCustomerDate();//获取当前时间
        if(null != response.getResult() && "S".equals(response.getStatus())) {
            ProductPageResult pdResult = response.getResult();
            if(null != pdResult){
                List<PDInfoResult> result = pdResult.getPdInfo();
                for(PDInfoResult pdr : result){
                    BSP171103Bean bean = new BSP171103Bean();
                    BeanUtils.copyProperties(bean,pdr);
                    bean.setCrtId("batch");
                    bean.setCrtTime(nowDate);
                    bean.setUpdId("batch");
                    bean.setUpdTime(nowDate);
                    bean.setActId("batch");
                    bean.setActTime(nowDate);
                    bean.setClasses(pdr.getClassesName());
                    bean.setMachining(pdr.getMachiningName());
                    bean.setBreed(pdr.getBreedName());
                    bean.setFeature(pdr.getFeatureName());
                    bean.setWeight(pdr.getWeightName());
                    bean.setGrade(pdr.getGradeName());
                    if(null != bean.getLgcsCode() && null != bean.getPdCode()){
                        list.add(bean);
                    }
                }
            }
        }
        return list;
    }
    public interface SqlId {
        static final String  SQLID_SELECT_LOGS_PRODUCT = "selectLogsProduct";
    }
}
