package com.msk.bs.logic;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101112Bean;
import com.msk.buyers.bean.BuyerBasicInfoBean;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.buyers.bean.BuyerRelationResult;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.utils.RestClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * 查询买手冻品管家的买家履历信息
 * @author cx
 */
@Service
public class BS2101112Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * 查询买手冻品管家的买家履历信息
     * @param param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public PageResult<IBS2101112Bean> findIBS2101112List(BasePageParam param){
        //获得页数
        PageResult<IBS2101112Bean> pageResult = new PageResult<IBS2101112Bean>();
        //获得买手买家关系履历表和冻品管家账户表数据列表
        param.setPaging(false);
        List<IBS2101112Bean> list = this.findPageList(param, IBS2101112Bean.class);
        List<IBS2101112Bean> tempList = new ArrayList<>();
        pageResult.setRecordsTotal(list.size());
        if (list.size() > param.getPageSize()) {
            int endPos = param.getStartPos() + param.getPageSize();
            if (endPos > list.size()) {
                endPos = list.size();
            }
            list = list.subList(param.getStartPos(), endPos);
        }

        //获得buyerIdList
        List<String> buyerIdList = new ArrayList<>();
         for (int i = 0; i < list.size(); i++){
             String buyerId=list.get(i).getBuyerId();
             buyerIdList.add(buyerId);
         }
        //获得查询参数
        String buyerCode = param.getFilterMap().get("buyerInfo.buyerCode").toString();
        String provinceCode = param.getFilterMap().get("provinceCode").toString();
        String cityCode = param.getFilterMap().get("cityCode").toString();
        String districtCode = param.getFilterMap().get("districtCode").toString();
        String buyerName = param.getFilterMap().get("buyerInfo.buyerName").toString();
        String buyerAddr = param.getFilterMap().get("buyerInfo.buyerAddr").toString();
        String busiTel = param.getFilterMap().get("buyerInfo.busiTel").toString();
        List<BuyerBasicInfoBean> buyerBasicList = new ArrayList<>();
        //如果buyerIdList不为空，构造buyerRelationParam
        if (!CollectionUtils.isEmpty(buyerIdList)){
            BuyerRelationParam buyerRelationParam=new BuyerRelationParam();
            buyerRelationParam.setBuyerCode(buyerCode);
            buyerRelationParam.setProvinceCode(provinceCode);
            buyerRelationParam.setCityCode(cityCode);
            buyerRelationParam.setDistrictCode(districtCode);
            buyerRelationParam.setBuyerName(buyerName);
            buyerRelationParam.setBuyerAddr(buyerAddr);
            buyerRelationParam.setBusiTel(busiTel);
            buyerRelationParam.setBuyerIdList(buyerIdList);
             //IsLoadBuyerBasic设为0，接口加载买家基本信息
            buyerRelationParam.setIsLoadBuyerBasic(0);
            //调用买家接口，获得基本信息
            RsResponse<BuyerRelationResult> result = getBuyersInfoRelationInfo(buyerRelationParam);
            buyerBasicList = result.getResult().getBuyerBasicInfoList();
            }
            //循环list
            for (IBS2101112Bean iBS2101112Bean :list) {
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
                        //营业电话
                        iBS2101112Bean.getBuyerInfo().setBusiTel(basicInfoBean.getBusiTel());
                        iBS2101112Bean.getBuyerInfo().setProvinceName(basicInfoBean.getProvinceName());
                        iBS2101112Bean.getBuyerInfo().setCityName(basicInfoBean.getCityName());
                        iBS2101112Bean.getBuyerInfo().setDistrictName(basicInfoBean.getDistrictName());
                        /*
                         判断buyerCode，provinceCode，cityCode，districtCode，buyerName，buyerAddr，busiTel是否有值，
                         如果有值，iBS2101112Bean放入tempList
                         */
                        if(!StringUtil.isEmpty(buyerCode) ||!StringUtil.isEmpty(provinceCode) || !StringUtil.isEmpty(cityCode)
                                || !StringUtil.isEmpty(districtCode) || !StringUtil.isEmpty(buyerName) || !StringUtil.isEmpty(buyerAddr)
                            ||!StringUtil.isEmpty(busiTel)){
                            tempList.add(iBS2101112Bean);
                            break;
                        }
                    }
                }
            }
        /*
        *判断buyerCode，provinceCode，cityCode，districtCode，buyerName，buyerAddr，busiTel是否有值
        *  如果有值，list等于tempList
         */
        if(!StringUtil.isEmpty(buyerCode) ||!StringUtil.isEmpty(provinceCode) || !StringUtil.isEmpty(cityCode)
                || !StringUtil.isEmpty(districtCode) || !StringUtil.isEmpty(buyerName) || !StringUtil.isEmpty(buyerAddr)
                ||!StringUtil.isEmpty(busiTel)){
            list = tempList;
            //如果基本信息list为空，就将list设为空
            if(CollectionUtils.isEmpty(buyerBasicList)){
                list = new ArrayList<>();
            }
            pageResult.setRecordsTotal(list.size());
        }
        pageResult.setData(list);
        return pageResult;
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
        request.setParam(buyerRelationParam);
        String url = ConfigManager.getMskBuyersService() + ConfigManager.getBuyersInfoRelationInfoServices();
        //String url ="http://10.20.16.49:8887/api/by/buyerInfo/relationInfo";
        RsResponse<BuyerRelationResult> buyerRelation =
                RestClientUtil.post(url, request, new TypeReference<RsResponse<BuyerRelationResult>>() {
                });
        return buyerRelation;
    }
}
