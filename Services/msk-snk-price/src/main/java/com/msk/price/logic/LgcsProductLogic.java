package com.msk.price.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.price.bean.LgcsProductBean;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductPageResult;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 物流区产品同步履历
 */
@Service
public class LgcsProductLogic extends BaseLogic {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(LgcsProductLogic.class);

    @Autowired
    private LgcsProductCopyLogic lgcsProductCopyLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    @Transactional
    public void dataResolve(BaseParam param) throws Exception{
        // 获取同步数据
        List<LgcsProductBean> list =this.findList(SqlId.SQLID_SELECT_LOGS_PRODUCT, param);
        if(null != list && list.size() > 0) {
            int num = lgcsProductCopyLogic.copayProduct(list,param);
            if (num > 0) {
                super.remove(param);;//删除同步数据
            }
        }
        List<LgcsProductBean> newList=getPDSup(param);
        int nn = 0;
        if(null != newList && newList.size() > 0) {
            for(int i=0;i< newList.size();i+=50){
                nn=i+50;
                if(nn > newList.size()){
                    nn = newList.size();
                }
                //添加新的同步数据
                super.batchSave(newList.subList(i, nn));
            }
        }
    }
    /**
     * 获取物流区产品数据
     * @return List
     * @throws Exception
     */
    public List<LgcsProductBean> getPDSup(BaseParam param) throws Exception {
        List<LgcsProductBean> list = new ArrayList<LgcsProductBean>();

        RsRequest<PDInfoParam> requestParam = new RsRequest<PDInfoParam>();
        requestParam.setAuth("MSK00001");
        requestParam.setLoginId("msk01");
        requestParam.setSiteCode("1");
        PDInfoParam pdInfoParam= new PDInfoParam();
        pdInfoParam.setGradeFlag("1");
        requestParam.setParam(pdInfoParam);
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
                    LgcsProductBean bean = new LgcsProductBean();
                    BeanUtils.copyProperties(bean, pdr);
                    bean.setCrtId(param.getCrtId());
                    bean.setCrtTime(nowDate);
                    bean.setUpdId(param.getUpdId());
                    bean.setUpdTime(nowDate);
                    bean.setActId(param.getActId());
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
