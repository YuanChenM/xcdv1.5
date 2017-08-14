package com.msk.buyers.rest;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.IBY1213141RsBean;
import com.msk.buyers.logic.BY1213141Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.ConfigManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.ByBuyerDelivery;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.DistrictResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by yuan_zhifei on 2016/7/8.
 */
@RestController
public class IBY1213141RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY1213141RsController.class);

    @Autowired
    private BY1213141Logic by1213141Logic;

    /**
     * 根据id获取买家配送地址信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/deliveryAddr/queryById", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ByBuyerDelivery> queryDdeliveryAddrById(@RequestBody RsRequest<BaseParam> param) {
        logger.info("根据id获取买家配送地址信息");
        RsResponse<ByBuyerDelivery> response = new RsResponse<>();
        ByBuyerDelivery buyerDelivery = by1213141Logic.findOne(param.getParam());
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(buyerDelivery);
        return response;
    }

    /**
     * 更新买家配送信息
     */
    @RequestMapping(value = "/by/deliveryAddr/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ByBuyerDelivery> updateDeliveryAddr(@RequestBody RsRequest<IBY1213141RsBean> bean) {
        logger.info("更新买家配送信息");
        RsResponse<ByBuyerDelivery> response = new RsResponse<>();
        DistrictBean codesBean = new DistrictBean();
        DistrictBean namesBean = new DistrictBean();
        Boolean isCode = false;
        Boolean isName = false;
        int nameCount = 0;
        String message = "";
        //super.setCommonParam(bean.getParam());
        //接口設置共同字段
        if(bean.getParam() != null){
            Date currentDate = DateTimeUtil.getCustomerDate();
            bean.getParam().setCrtId(bean.getLoginId());
            bean.getParam().setCrtTime(currentDate);
            bean.getParam().setUpdId(bean.getLoginId());
            bean.getParam().setUpdTime(currentDate);
            bean.getParam().setActId(bean.getLoginId());
            bean.getParam().setActTime(currentDate);
        }
        //外部调用接口
        if(!"true".equals(bean.getParam().getIsFlag())){
            //传省市区编码不传名称
            if(!StringUtil.isNullOrEmpty(bean.getParam().getProvinceCode())
                    && !StringUtil.isNullOrEmpty(bean.getParam().getCityCode())
                        && !StringUtil.isNullOrEmpty(bean.getParam().getDistrictCode())){
                RsRequest<DistrictParam> request = new RsRequest<>();
                DistrictParam param = new DistrictParam();
                String[] ComposeCodes = {bean.getParam().getCityCode() + bean.getParam().getDistrictCode()};
                param.setComposeCodes(ComposeCodes);
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setSiteCode("1");
                request.setParam(param);
                //调用通过省市区编码获取省市区名称接口
                // "http://localhost:8080/msk-district/api/district/query/district"
                String url = SystemServerManager.DistrictServerManage.getDistrictQueryDistrict();
                RsResponse<DistrictResult> districtList =
                        RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                        });
                if(!CollectionUtils.isEmpty(districtList.getResult().getDistrictList())){
                    isCode = true;
                    codesBean =  districtList.getResult().getDistrictList().get(NumberConst.IntDef.INT_ZERO);
                }else {
                    isCode = false;
                    codesBean = null;
                }
            }
            //传省市区名称不传编码
            if(!StringUtil.isNullOrEmpty(bean.getParam().getProvinceName())
                    && !StringUtil.isNullOrEmpty(bean.getParam().getCityName())
                        && !StringUtil.isNullOrEmpty(bean.getParam().getDistrictName())){
                RsRequest<DistrictParam> request = new RsRequest<>();
                DistrictParam param = new DistrictParam();
                param.setProvinceName(bean.getParam().getProvinceName());
                param.setCityName(bean.getParam().getCityName());
                param.setDistrictName(bean.getParam().getDistrictName());
                request.setAuth("MSK00001");
                request.setLoginId("msk01");
                request.setSiteCode("1");
                request.setParam(param);
                //调用通过省市区名称获取省市区编码接口
                //String url = "http://localhost:8780/msk-district/api/district/query/getCodesFromNames";
                String url =  SystemServerManager.DistrictServerManage.getGetCodesFromNames();
                RsResponse<DistrictResult> districtList =
                        RestClientUtil.post(url, request, new TypeReference<RsResponse<DistrictResult>>() {
                        });

                if (!CollectionUtils.isEmpty(districtList.getResult().getDistrictList())){
                    nameCount = districtList.getResult().getDistrictList().size();
                    if(nameCount > NumberConst.IntDef.INT_ONE){
                        isName = false;
                        message = "省市区(县)名称输入有误,请输入正确的省市区(县)名称！";
                        response.setResult(null);
                        response.setMessage(message);
                        response.setStatus(SystemConst.RsStatus.FAIL);
                    }else {
                        isName = true;
                        namesBean =  districtList.getResult().getDistrictList().get(NumberConst.IntDef.INT_ZERO);
                    }
                }else{
                    isName = false;
                    namesBean = null;
                }
            }
            if(isCode && !isName){
                //判断是否传入省市区(县)名称
                if(!StringUtil.isNullOrEmpty(bean.getParam().getProvinceName())
                        || !StringUtil.isNullOrEmpty(bean.getParam().getCityName())
                        || !StringUtil.isNullOrEmpty(bean.getParam().getDistrictName())
                        || !StringUtil.isNullOrEmpty(bean.getParam().getProvinceCode())){
                    //判断传入的名称是否和通过编码查出的名称对应,对应:新增;不对应:提示错误信息
                    if(!StringUtil.isNullOrEmpty(bean.getParam().getProvinceName())
                            && !bean.getParam().getProvinceName().equals(codesBean.getProvinceName())){
                        message = "省或区(县)名称输入有误！";
                        response.setResult(null);
                        response.setMessage(message);
                        response.setStatus(SystemConst.RsStatus.FAIL);
                    }else if(!StringUtil.isNullOrEmpty(bean.getParam().getCityName())
                            && !bean.getParam().getCityName().equals(codesBean.getCityName())){
                        message = "市名称输入有误！";
                        response.setResult(null);
                        response.setMessage(message);
                        response.setStatus(SystemConst.RsStatus.FAIL);
                    }else if(!StringUtil.isNullOrEmpty(bean.getParam().getDistrictName())
                            && !bean.getParam().getDistrictName().equals(codesBean.getDistrictName())){
                        message = "区(县)名称输入有误！";
                        response.setResult(null);
                        response.setMessage(message);
                        response.setStatus(SystemConst.RsStatus.FAIL);
                    }else if(!StringUtil.isNullOrEmpty(bean.getParam().getProvinceCode())
                            && !bean.getParam().getProvinceCode().equals(codesBean.getProvinceCode())){
                        message = "省编码输入有误！";
                        response.setResult(null);
                        response.setMessage(message);
                        response.setStatus(SystemConst.RsStatus.FAIL);
                    }else {
                        bean.getParam().setProvinceName(codesBean.getProvinceName());
                        bean.getParam().setCityName(codesBean.getCityName());
                        bean.getParam().setDistrictName(codesBean.getDistrictName());
                        ByBuyerDelivery result = this.by1213141Logic.modifyDelivery(bean.getParam());
                        response.setResult(result);
                        response.setStatus(SystemConst.RsStatus.SUCCESS);
                        response.setMessage("处理成功！");
                    }
                }else{
                    bean.getParam().setProvinceName(codesBean.getProvinceName());
                    bean.getParam().setCityName(codesBean.getCityName());
                    bean.getParam().setDistrictName(codesBean.getDistrictName());
                    ByBuyerDelivery result = this.by1213141Logic.modifyDelivery(bean.getParam());
                    response.setResult(result);
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    response.setMessage("处理成功！");
                }
            }else if(isName && !isCode){
                //判断是否传入省市区(县)编码
                if(!StringUtil.isNullOrEmpty(bean.getParam().getProvinceCode())
                        || !StringUtil.isNullOrEmpty(bean.getParam().getCityCode())
                        || !StringUtil.isNullOrEmpty(bean.getParam().getDistrictCode())){
                    //判断传入的编码是否和通过名称查出的编码对应,对应:新增;不对应:提示错误信息
                    if(!StringUtil.isNullOrEmpty(bean.getParam().getProvinceCode())
                            && !bean.getParam().getProvinceCode().equals(namesBean.getProvinceCode())){
                        message = "省编码输入有误！";
                        response.setResult(null);
                        response.setMessage(message);
                        response.setStatus(SystemConst.RsStatus.FAIL);
                    }else if(!StringUtil.isNullOrEmpty(bean.getParam().getCityCode())
                            && !bean.getParam().getCityCode().equals(namesBean.getCityCode())){
                        message = "市编码输入有误！";
                        response.setResult(null);
                        response.setMessage(message);
                        response.setStatus(SystemConst.RsStatus.FAIL);
                    }else if(!StringUtil.isNullOrEmpty(bean.getParam().getDistrictCode())
                            && !bean.getParam().getDistrictCode().equals(namesBean.getDistrictCode())){
                        message = "区(县)编码输入有误！";
                        response.setResult(null);
                        response.setMessage(message);
                        response.setStatus(SystemConst.RsStatus.FAIL);
                    }else{
                        bean.getParam().setProvinceCode(namesBean.getProvinceCode());
                        bean.getParam().setCityCode(namesBean.getCityCode());
                        bean.getParam().setDistrictCode(namesBean.getDistrictCode());
                        ByBuyerDelivery result = this.by1213141Logic.modifyDelivery(bean.getParam());
                        response.setResult(result);
                        response.setStatus(SystemConst.RsStatus.SUCCESS);
                        response.setMessage("处理成功！");
                    }
                }else {
                    bean.getParam().setProvinceCode(namesBean.getProvinceCode());
                    bean.getParam().setCityCode(namesBean.getCityCode());
                    bean.getParam().setDistrictCode(namesBean.getDistrictCode());
                    ByBuyerDelivery result = this.by1213141Logic.modifyDelivery(bean.getParam());
                    response.setResult(result);
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    response.setMessage("处理成功！");
                }

            }else if(!isCode){
                response.setResult(null);
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("省市区(县)名称输入有误,请输入正确的省市区(县)名称！");
            }else if(!isName){
                response.setResult(null);
                response.setStatus(SystemConst.RsStatus.FAIL);
                response.setMessage("省市区(县)名称输入有误,请输入正确的省市区(县)名称！");
            }else if(isCode && isName){
                if(bean.getParam().getProvinceName().equals(codesBean.getProvinceName())
                        && bean.getParam().getCityName().equals(codesBean.getCityName())
                            && bean.getParam().getDistrictName().equals(codesBean.getDistrictName())
                            && bean.getParam().getProvinceCode().equals(namesBean.getProvinceCode())
                            && bean.getParam().getCityCode().equals(namesBean.getCityCode())
                            && bean.getParam().getDistrictCode().equals(namesBean.getDistrictCode())){
                    bean.getParam().setProvinceName(codesBean.getProvinceName());
                    bean.getParam().setCityName(codesBean.getCityName());
                    bean.getParam().setDistrictName(codesBean.getDistrictName());
                    ByBuyerDelivery result = this.by1213141Logic.modifyDelivery(bean.getParam());
                    response.setResult(result);
                    response.setStatus(SystemConst.RsStatus.SUCCESS);
                    response.setMessage("处理成功！");
               } else {
                    response.setResult(null);
                    response.setStatus(SystemConst.RsStatus.FAIL);
                    response.setMessage("省市区(县)编码对应的名称输入有误,请输入正确的省市区(县)编码对应的名称！");
               }
            }
        }else{
            ByBuyerDelivery result = this.by1213141Logic.modifyDelivery(bean.getParam());
            response.setResult(result);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功！");
        }
        return response;
    }

    /**
     * 买家配送地址信息删除
     */
    @RequestMapping(value = "/by/deliveryAddr/delete", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<Integer> deleteDeliveryAddr(@RequestBody RsRequest<IBY1213141RsBean> bean) {
        logger.info("买家配送地址信息删除");
        RsResponse<Integer> response = new RsResponse<>();

        if(bean.getParam() != null){
            Date currentDate = DateTimeUtil.getCustomerDate();
            bean.getParam().setCrtId(bean.getLoginId());
            bean.getParam().setCrtTime(currentDate);
            bean.getParam().setUpdId(bean.getLoginId());
            bean.getParam().setUpdTime(currentDate);
            bean.getParam().setActId(bean.getLoginId());
            bean.getParam().setActTime(currentDate);
        }

        BaseParam param = new BaseParam();
        param.setFilter("id",String.valueOf(bean.getParam().getId()));
        param.getFilterMap().put("updTime", bean.getParam().getUpdTime());
        param.getFilterMap().put("updId", bean.getParam().getUpdId());

        int result = by1213141Logic.deleteDelivery(param);
        response.setResult(result);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        return response;
    }
}
