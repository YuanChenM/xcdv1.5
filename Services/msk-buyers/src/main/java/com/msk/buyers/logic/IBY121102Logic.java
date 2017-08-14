package com.msk.buyers.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdClasses;
import com.msk.core.entity.PdMachining;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tao_zhifa on 2016/7/15.
 */
@Service
public class IBY121102Logic extends BaseLogic{
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IBY121102Logic.class);


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 调用接口 查询一级分类信息
     * Created by taozhifa
     */
    public static RsResponse<PdClasses[]> findPdClasses(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductClassesInfo();

        RsResponse<PdClasses[]> districtList =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<PdClasses[]>>() {
                });

            return districtList;

    }

    /**
     * 调用接口 查询二级分类信息
     * Created by taozhifa
     */
    public static RsResponse<PdMachining[]> findPdMachining(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductMachiningInfo();
        RsResponse<PdMachining[]> responce = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdMachining[]>>() {
        });
        return responce;
    }

    /**
     * 调用接口 查询品种信息
     * Created by liu_yan2 on 2016/6/20.
     */
    public static RsResponse<PdBreed[]> findPdBreed(BaseParam param) {
        RsRequest<BaseParam> request = new RsRequest<BaseParam>();
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(param);
        String url = ConfigManager.getMskProductService() + ConfigManager.getProductBreedInfo();
//        String url = "http://10.20.16.22:8081/api/product/findPdBreed";
        RsResponse<PdBreed[]> responce = RestClientUtil.post(url, request, new TypeReference<RsResponse<PdBreed[]>>() {
        });
        return responce;
    }

    @Transactional(readOnly = true)
    public ByBuyerBasicInfo findSuperiorType(BaseParam param){
        return super.findOne(SqlId.FIND_SUPERIOR_TYPE,param);
    }


    @Transactional(readOnly = true)
    public ByBuyerBasicInfo findmMrketingsStatus(BaseParam param){
        return super.findOne(SqlId.FIND_MMRKETINGS_STATUS,param);
    }

    interface SqlId{
         String FIND_SUPERIOR_TYPE = "findSuperiorType";
        String FIND_MMRKETINGS_STATUS = "findmMrketingsStatus";
    }



}
