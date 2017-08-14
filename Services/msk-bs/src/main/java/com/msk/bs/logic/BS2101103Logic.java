package com.msk.bs.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101107Bean;
import com.msk.bs.bean.IBS2101107RsResult;
import com.msk.bs.utils.CommRestUtil;
import com.msk.buyers.bean.BuyerRelationParam;
import com.msk.buyers.bean.IBY121225Param;
import com.msk.common.base.BaseLogic;

/**
 * 买手店管家会员列表
 *
 * @author cx
 */
@Service
public class BS2101103Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询买手店管家会员信息
     *
     * @param param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public PageResult<IBS2101107Bean> findBS2101103List(BasePageParam param) {
        /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc start */
        boolean isPaging = param.isPaging();
        PageResult<IBS2101107Bean> pageResult = new PageResult<IBS2101107Bean>();
        int pageCount = this.getPageCount(param);
        pageResult.setRecordsTotal(pageCount);
        if (pageResult.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
            param.setPaging(false);
            List<IBS2101107Bean> list = this.findPageList(param, IBS2101107Bean.class);

            List<String> buyerIdList = new ArrayList<String>();

            for (IBS2101107Bean bean : list) {
                buyerIdList.add(bean.getBuyerId());
            }
            //调用查询买手店管家会员信息
            BuyerRelationParam buyerRelationParam = new BuyerRelationParam();
            buyerRelationParam.setBuyerCode(StringUtil.toSafeString(param.getFilterMap().get("buyerCode")));
            buyerRelationParam.setBuyerName(StringUtil.toSafeString(param.getFilterMap().get("buyerName")));
            buyerRelationParam.setBuyerAddr(StringUtil.toSafeString(param.getFilterMap().get("buyerAddr")));
            buyerRelationParam.setBusiTel(StringUtil.toSafeString(param.getFilterMap().get("busiTel")));
            buyerRelationParam.setCityCode(StringUtil.toSafeString(param.getFilterMap().get("cityCode")));
            buyerRelationParam.setDistrictCode(StringUtil.toSafeString(param.getFilterMap().get("districtCode")));
            buyerRelationParam.setLgcsAreaCode(StringUtil.toSafeString(param.getFilterMap().get("lgcsAreaCode")));
            buyerRelationParam.setBuyerIdList(buyerIdList);
            buyerRelationParam.setIsLoadBuyerBasic(0);
//            RsResponse<BuyerRelationResult> resultRsResponse = CommRestUtil.getBuyersInfoRelationInfo(buyerRelationParam);


            IBY121225Param rsParam = new IBY121225Param();
            rsParam.setBuyerCode(buyerRelationParam.getBuyerCode());
            rsParam.setBuyerName(buyerRelationParam.getBuyerName());
            rsParam.setBuyerAddr(buyerRelationParam.getBuyerAddr());
            rsParam.setBusiTel(buyerRelationParam.getBusiTel());
            rsParam.setLgcsAreaCode(buyerRelationParam.getLgcsAreaCode());
            rsParam.setCityCode(buyerRelationParam.getCityCode());
            rsParam.setDistrictCode(buyerRelationParam.getDistrictCode());
            rsParam.setBuyerIdList(buyerIdList);

            rsParam.setPaging(isPaging);
            rsParam.setPageNo((param.getStartPos() / param.getPageSize()) + 1);
            rsParam.setPageCount(param.getPageSize());

            IBS2101107RsResult resultRsResponse =  CommRestUtil.searchExclusive(rsParam);
            List<IBS2101107Bean> slBuyerList = new ArrayList<>();
            int  recordsTotal = 0;
            if(null != resultRsResponse){
                slBuyerList = resultRsResponse.getSlBuyerList();
                recordsTotal = resultRsResponse.getTotalCount();
            }
            /**modify bug #3501 查询买手冻品管家的买家信息接口，搜索分页问题 whc end**/


            /*List<BuyerBasicInfoBean> basicInfoList = new ArrayList<>();
            if (null != resultRsResponse && !CollectionUtils.isEmpty(resultRsResponse.getResult().getBuyerBasicInfoList())) {
                basicInfoList = resultRsResponse.getResult().getBuyerBasicInfoList();
            }*/
            /** Modfiy:  Bug#2620 : 买手管家会员列表：查询条件不起作用 2016/9/12   BY  zhukai1  Start */
            List<IBS2101107Bean> bsList = new ArrayList<IBS2101107Bean>();
            /** Modfiy:  Bug#2620 : 买手管家会员列表：查询条件不起作用 2016/9/12   BY  zhukai1  end */
            for (IBS2101107Bean bean : list) {
                bean.setBuyerCode("");
                bean.setBuyerName("");
                bean.setCityName("");
                bean.setBuyerAddr("");
                bean.setBusiTel("");
                //填充返回集合
                if (!CollectionUtils.isEmpty(slBuyerList)) {
                    for (IBS2101107Bean basicInfo : slBuyerList) {
                        if (bean.getBuyerId().equals(basicInfo.getBuyerId())) {
                            bean.setBuyerCode(basicInfo.getBuyerCode());
                            bean.setBuyerName(basicInfo.getBuyerName());
                            bean.setLgcsAreaCode(basicInfo.getLgcsAreaCode());
                            bean.setLgcsAreaName(basicInfo.getLgcsAreaName());
                            bean.setCityCode(basicInfo.getCityCode());
                            bean.setCityName(basicInfo.getCityName());
                            bean.setDistrictCode(basicInfo.getDistrictCode());
                            bean.setDistrictName(basicInfo.getDistrictName());
                            bean.setBuyerAddr(basicInfo.getBuyerAddr());
                            bean.setBusiTel(basicInfo.getBusiTel());
                            bean.setSuperiorType(basicInfo.getSuperiorType());
                            bean.setSuperiorName(basicInfo.getSuperiorName());
                            bsList.add(bean);
                            break;
                        }
                    }
                } else if ((!StringUtil.isNullOrEmpty(buyerRelationParam.getBusiTel()) || !StringUtil.isNullOrEmpty(buyerRelationParam.getBuyerAddr())
                        || !StringUtil.isNullOrEmpty(buyerRelationParam.getBuyerCode()) || !StringUtil.isNullOrEmpty(buyerRelationParam.getBuyerName())
                        || !StringUtil.isNullOrEmpty(buyerRelationParam.getLgcsAreaCode()) || !StringUtil.isNullOrEmpty(buyerRelationParam.getCityCode())
                        || !StringUtil.isNullOrEmpty(buyerRelationParam.getDistrictCode())) && CollectionUtils.isEmpty(slBuyerList)) {
                    bsList = new ArrayList<>();
                }
                /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc  2016/11/1 start */
                /** Modfiy:  Bug#2620 : 买手管家会员列表：查询条件不起作用 2016/9/12   BY  zhukai1  Start */
                /*else if((StringUtil.isNullOrEmpty(buyerRelationParam.getBusiTel()) && StringUtil.isNullOrEmpty(buyerRelationParam.getBuyerAddr())
                        && StringUtil.isNullOrEmpty(buyerRelationParam.getBuyerCode()) && StringUtil.isNullOrEmpty(buyerRelationParam.getBuyerName())
                        && StringUtil.isNullOrEmpty(buyerRelationParam.getLgcsAreaCode()) && StringUtil.isNullOrEmpty(buyerRelationParam.getCityCode())
                        && StringUtil.isNullOrEmpty(buyerRelationParam.getDistrictCode())) && CollectionUtils.isEmpty(slBuyerList)){
                    bsList.add(bean);
                }*/
                /** Modfiy:  Bug#2620 : 买手管家会员列表：查询条件不起作用 2016/9/12   BY  zhukai1  end */

            }
            /** Modfiy:  Bug#2616 : 冻品管家列表：查询已有专属买家，显示的数据记录和当前专属买家数个数不一致 2016/9/12   BY  zhukai1  Start */
//            pageResult.setRecordsTotal(bsList.size());
            //分页
            /*if (bsList.size() > param.getPageSize()) {
                int endPos = param.getStartPos() + param.getPageSize();
                if (endPos > bsList.size()) {
                    endPos = bsList.size();
                }
                bsList = bsList.subList(param.getStartPos(), endPos);
            }*/
            /** Modfiy:  Bug#2616 : 冻品管家列表：查询已有专属买家，显示的数据记录和当前专属买家数个数不一致 2016/9/12   BY  zhukai1  end */
            pageResult.setData(bsList);
            pageResult.setRecordsTotal(recordsTotal);
            /** Bug #3501 买家列表和冻品管家买家列表，一些查询条件不起作用 by whc 2016/11/1 end */
        } else {
            pageResult.setData(new ArrayList<IBS2101107Bean>());
        }
        return pageResult;
    }

}
