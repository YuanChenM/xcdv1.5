package com.msk.bs.logic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101112Bean;
import com.msk.bs.bean.IBS2101112RsParam;
import com.msk.buyers.bean.BuyerBasicInfoBean;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.buyers.bean.BuyerRelationResult;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsPageParam;
import com.msk.common.bean.RsPageResult;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/14.
 */
@Service
public class IBS2101112RsLogic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private BS2101112Logic bS2101112Logic;

    @Autowired
    private BSCommLogic bsCommLogic;

    @Override
    @Transactional(readOnly = true)
    public <T extends BaseEntity> List<T> findPageList(RsPageParam param, RsPageResult pageResult) {
        IBS2101112RsParam iBS2101112RsParam = (IBS2101112RsParam) param;
        if (param.getPageCount() == 0 || param.getPageNo() == 0) {
            param.setPaging(false);
        } else {
            param.setPaging(true);
        }
        param.setFilter("slCode", iBS2101112RsParam.getSlCode());
        param.setFilter("buyerId", iBS2101112RsParam.getBuyerId());
        param.setFilter("buyerFlag", iBS2101112RsParam.getBuyerFlag());
        param.setFilter("provinceCode", iBS2101112RsParam.getProvinceCode());
        param.setFilter("cityCode", iBS2101112RsParam.getCityCode());
        param.setFilter("districtCode", iBS2101112RsParam.getDistrictCode());
        param.setFilter("buyerAddr", iBS2101112RsParam.getBuyerAddr());
        param.setFilter("salesTargetType", iBS2101112RsParam.getSalesTargetType());
        param.setFilter("classCode", iBS2101112RsParam.getClassCode());
        param.setFilter("houseCode", iBS2101112RsParam.getHouseCode());
        param.setFilter("applyStatus", iBS2101112RsParam.getApplyStatus());
        param.setFilter("searchDataFlag", iBS2101112RsParam.getSearchDataFlag());

        param.setFilter("houseCodeDis1", iBS2101112RsParam.getSearchDataFlag());

        Date startDate = iBS2101112RsParam.getStartDate();
        Date endDate = iBS2101112RsParam.getEndDate();
        if((startDate != null)&&(endDate != null)){
            param.setFilter("startDate", startDate.toString());
            param.setFilter("endDate",  endDate.toString());
        }
        List<IBS2101112Bean> beans = bS2101112Logic.findPageList(param, pageResult);
        //获得buyerIdList
        List<String> buyerIdList = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++){
            String buyerId=beans.get(i).getBuyerId();
            buyerIdList.add(buyerId);
        }

        List<BuyerBasicInfoBean> buyerBasicList = new ArrayList<>();
        //如果buyerIdList不为空，构造buyerRelationParam
        if (!CollectionUtils.isEmpty(buyerIdList)){
            BuyerRelationParam buyerRelationParam=new BuyerRelationParam();

            buyerRelationParam.setBuyerIdList(buyerIdList);
            //IsLoadBuyerBasic设为0，接口加载买家基本信息
            buyerRelationParam.setIsLoadBuyerBasic(0);
            buyerRelationParam.setFilterMap(null);



            //调用买家接口，获得基本信息
            RsResponse<BuyerRelationResult> result = getBuyersInfoRelationInfo(buyerRelationParam);
            buyerBasicList = result.getResult().getBuyerBasicInfoList();
        }
        //循环list
        for (IBS2101112Bean iBS2101112Bean :beans) {
            //值为null，设为""
            iBS2101112Bean.getBuyerInfo().setBuyerCode("");
            iBS2101112Bean.getBuyerInfo().setBuyerName("");
            iBS2101112Bean.getBuyerInfo().setLgcsAreaCode("");
            iBS2101112Bean.getBuyerInfo().setProvinceCode("");
            iBS2101112Bean.getBuyerInfo().setCityCode("");
            iBS2101112Bean.getBuyerInfo().setDistrictCode("");
            iBS2101112Bean.getBuyerInfo().setBuyerAddr("");
            iBS2101112Bean.getBuyerInfo().setBusiTel("");
            iBS2101112Bean.getBuyerInfo().setProvinceName("");
            iBS2101112Bean.getBuyerInfo().setCityName("");
            iBS2101112Bean.getBuyerInfo().setDistrictName("");
            //循环基本信息list,判断BuyerId是否相等
            for(BuyerBasicInfoBean basicInfoBean : buyerBasicList){
                if(iBS2101112Bean.getBuyerId().equals(basicInfoBean.getBuyerId())){
                    //BuyerId相等,组装iBS2101112Bean
                    iBS2101112Bean.getBuyerInfo().setBuyerCode(basicInfoBean.getBuyerCode());
                    iBS2101112Bean.getBuyerInfo().setBuyerName(basicInfoBean.getBuyerName());
                    iBS2101112Bean.getBuyerInfo().setLgcsAreaCode(basicInfoBean.getLgcsAreaCode());
                    iBS2101112Bean.getBuyerInfo().setProvinceCode(basicInfoBean.getProvinceCode());
                    iBS2101112Bean.getBuyerInfo().setCityCode(basicInfoBean.getCityCode());
                    iBS2101112Bean.getBuyerInfo().setDistrictCode(basicInfoBean.getDistrictCode());
                    iBS2101112Bean.getBuyerInfo().setBuyerAddr(basicInfoBean.getBuyerAddr());
                    iBS2101112Bean.getBuyerInfo().setBusiTel(basicInfoBean.getBusiTel());
                    iBS2101112Bean.getBuyerInfo().setProvinceName(basicInfoBean.getProvinceName());
                    iBS2101112Bean.getBuyerInfo().setCityName(basicInfoBean.getCityName());
                    iBS2101112Bean.getBuyerInfo().setDistrictName(basicInfoBean.getDistrictName());
                }
            }
        }
        return (List<T>) beans;
    }
    /**
     * 获取买家所有相关的信息
     *
     * @param buyerRelationParam
     * @return
     */
    public RsResponse<BuyerRelationResult> getBuyersInfoRelationInfo(BuyerRelationParam buyerRelationParam) {
        RsRequest<BuyerRelationParam> request = new RsRequest<BuyerRelationParam>();
        request.setAuth("MSK00001");
        request.setLoginId(buyerRelationParam.getActId());
        request.setSiteCode("1");
        buyerRelationParam.setBuyerCodeList(new ArrayList<String>());
        buyerRelationParam.setFilterMap(null);
        //System.err.print(JSONObject.toJSONString(buyerIdList));
        request.setParam(buyerRelationParam);


        //String jsonParam = JSONObject.toJSONString(request);
        String url = ConfigManager.getMskBuyersService() + ConfigManager.getBuyersInfoRelationInfoServices();
        //String url ="http://10.20.16.147:8082/msk-buyers/api/by/buyerInfo/relationInfo";
        //buyerRelationParam.buyerIdList中会出现转义字符，需传入SerializerFeature.PrettyFormat 序列化
        RsResponse<BuyerRelationResult> buyerRelation =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<BuyerRelationResult>>() {
                },SerializerFeature.PrettyFormat);
        return buyerRelation;
    }

}


