package com.msk.bs.logic;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.StringConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.bean.PageResult;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.*;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.*;
import com.msk.district.bean.DistrictBean;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.seller.bean.ISLSellerRsParam;
import com.msk.seller.bean.ISLSellerRsResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 买手店列表
 *
 * @author cx
 */
@Service
public class BS2101101Logic extends BaseLogic {

    private Logger logger = LoggerFactory.getLogger(BS2101101Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    private BS2101109Logic bs2101109Logic;


    interface SqlID {
        // 根据买手code查询管家专属会员表信息.
        static String FIND_SL_BS_BUYER_INFO = "findSlBsBuyerInfo";
        // 修改delFlag 为 1.
        static String UPDATE_BS_BUYER_INFO = "updateBsBuyerInfo";
    }

    /**
     * 查询买手店列表
     *
     * @param param param
     * @return 结果
     */
    @Transactional(readOnly = true)
    public PageResult<IBS210110301Bean> findBS2101101List(BS2101101Param param) {
        PageResult<IBS210110301Bean> pageResult = new PageResult<IBS210110301Bean>();
        pageResult.setRecordsTotal(this.getPageCount(param));
        if (pageResult.getRecordsTotal() != NumberConst.IntDef.INT_ZERO) {
            String lgcsAreaName = StringUtil.toSafeString(param.getFilterMap().get("lgcsAreaName"));
            if (!StringUtil.isNullOrEmpty(lgcsAreaName)) {
                param.setPaging(false);
            }

            List<IBS210110301Bean> list = this.findPageList(param, IBS210110301Bean.class);

            String[] composeCodes = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                IBS210110301Bean bean = list.get(i);
                //省编码+城市编码+区县编码
                StringBuffer composeCode = new StringBuffer();
                composeCode.append(bean.getProvinceCode()).append(bean.getCityCode()).append(bean.getDistrictCode());
                composeCodes[i] = composeCode.toString();
                if (list.get(i) != null && !StringUtils.isEmpty(list.get(i).getLgcsAreaCode())) {
                    DistrictParam params = new DistrictParam();
                    params.setLgcsAreaCode(list.get(i).getLgcsAreaCode());
                    List<LgcsAreaBean> logisticsAreaList = CommRestUtil.getLogisticsAreaList(params);
                    list.get(i).setLgcsAreaName(logisticsAreaList.get(0).getLgcsAreaName());
                }else{
                    list.get(i).setLgcsAreaName(StringConst.EMPTY);
                }
            }

            DistrictParam districtParam = new DistrictParam();

            districtParam.setComposeCodes(composeCodes);
            districtParam.setLgcsAreaName(lgcsAreaName);
            String provinceCode = StringUtil.toSafeString(param.getFilterMap().get("provinceCode"));
            districtParam.setProvinceCode(provinceCode);
            districtParam.setCityCode(StringUtil.toSafeString(param.getFilterMap().get("cityCode")));
            districtParam.setDistrictCode(StringUtil.toSafeString(param.getFilterMap().get("districtCode")));

            //调用行政区划接口
            List<DistrictBean> districtList = CommRestUtil.getRegion(districtParam);

            List<IBS210110301Bean> newList = new ArrayList<IBS210110301Bean>();

            //填充行政区划及物流区名称
            for (IBS210110301Bean bean : list) {
                if (CollectionUtils.isEmpty(districtList)) {
//                    bean.setLgcsAreaName(StringConst.EMPTY);
                    bean.setCityName(StringConst.EMPTY);
                }
                for (DistrictBean disBean : districtList) {
                    if (!StringUtil.isNullOrEmpty(bean.getProvinceCode())
                            && bean.getProvinceCode().equals(disBean.getProvinceCode())
                            && !StringUtil.isNullOrEmpty(bean.getCityCode())
                            && bean.getCityCode().equals(disBean.getCityCode())
                            && !StringUtil.isNullOrEmpty(bean.getDistrictCode())
                            && bean.getDistrictCode().equals(disBean.getDistrictCode())) {
//                        if (!StringUtil.isNullOrEmpty(bean.getLgcsAreaCode()) && bean.getLgcsAreaCode().equals(disBean.getLgcsAreaCode())) {
//                            bean.setLgcsAreaName(disBean.getLgcsAreaName());
//                        } else {
//                            bean.setLgcsAreaName(StringConst.EMPTY);
//                        }
                        bean.setSingleCityName(disBean.getCityName());
                        bean.setCityName(disBean.getProvinceName() + disBean.getCityName() + disBean.getDistrictName());
                        newList.add(bean);
                        break;
                    } else {
                        bean.setSingleCityName(StringConst.EMPTY);
//                        bean.setLgcsAreaName(StringConst.EMPTY);
                        bean.setCityName(StringConst.EMPTY);
                    }
                }
            }

            if (!StringUtil.isNullOrEmpty(lgcsAreaName)) {
                list = newList;
                //当有物流区名称查询且查询的行政区划为空，则列表页面无数据
                if (CollectionUtils.isEmpty(districtList)) {
                    list = new ArrayList<>();
                }
                pageResult.setRecordsTotal(list.size());
                if (list.size() > param.getPageSize()) {
                    int endPos = param.getStartPos() + param.getPageSize();
                    if (endPos > list.size()) {
                        endPos = list.size();
                    }
                    list = list.subList(param.getStartPos(), endPos);
                }
            }
            pageResult.setData(list);
        } else {
            pageResult.setData(new ArrayList<IBS210110301Bean>());
        }
        return pageResult;
    }

    @Transactional
    public IBS2101102RsResult deleteSlCode(RsRequest<IBS2101102RsParam> param) {

        IBS2101102RsResult rs = new IBS2101102RsResult();
        IBS2101102RsParam iBS2101102RsParam = param.getParam();
        //基本信息code
        String slCode = "";
        //获取id
        String loginId = iBS2101102RsParam.getLoginId();
        //获取买手账户
        BsAccount bsAccount = iBS2101102RsParam.getSlAccount();
        //买手基本信息
        BsBasicInfo bsBasicInfo = iBS2101102RsParam.getSlSeller();
        ISLSellerRsParam rsParam = new ISLSellerRsParam();
        Date date = DateTimeUtil.getCustomerDate();

        if (null != bsAccount) {
            bsAccount.setCrtId(loginId);
            bsAccount.setCrtTime(date);
            bsAccount.setUpdId(loginId);
            bsAccount.setUpdTime(date);
            bsAccount.setActId(loginId);
            bsAccount.setActTime(date);
            //Modif for 需求变更 #2569 at 2016/11/4 by ni_shaotang Start
            //认证状态为空默认给他2:已认证
            bsAccount.setAuthStatus(2);
            //删除账户信息(更新删除标志为“1”)
            bsAccount.setDelFlg("1");
            int num = bs2101109Logic.updateBsAccount(bsAccount);
            if (num == 0) {
                throw new BusinessException("删除买手账户失败");
            }
            //Modif for 需求变更 #2569 at 2016/11/4 by ni_shaotang end
        }
        if (null != bsBasicInfo) {
            //Modif for 需求变更 #2569 at 2016/11/4 by ni_shaotang Start
            SlSeller slSeller = new SlSeller();
            bsBasicInfo.setCrtId(loginId);
            bsBasicInfo.setCrtTime(date);
            bsBasicInfo.setUpdId(loginId);
            bsBasicInfo.setUpdTime(date);
            bsBasicInfo.setActId(loginId);
            bsBasicInfo.setActTime(date);
            //删除账户信息(更新删除标志为“1”)
            bsBasicInfo.setDelFlg("1");
            int num = bs2101109Logic.updateBsBasicInfo(bsBasicInfo);
            if (num == 0) {
                throw new BusinessException("删除买手基本信息失败");
            }
            rs.setHouseCode(bsBasicInfo.getSlCode());
            //Modif for 需求变更 #2569 at 2016/11/4 by ni_shaotang end
        }
        return rs;
    }

    /**
     * 删除买手下面的管家
     */
    @Transactional
    public void deleteHouseAccountBySlCode(BS2101101Bean bs2101101Bean) {
        this.modify(bs2101101Bean);
    }

    /**
     * 解除买手下面所有的管家下面的买家的绑定关系
     */
    @Transactional
    public void unbindHkRelation(BS2101101Bean bs2101101Bean) {

        BS2101101BsBean bS2101101BsBean = new BS2101101BsBean();
        bS2101101BsBean.setSlCode(bs2101101Bean.getSlCode());
        // 根据买手code查询管家专属会员表信息.
        List<SlBsBuyer> slBsBuyerList = this.findList(SqlID.FIND_SL_BS_BUYER_INFO, bS2101101BsBean);
        // 解除所有查询到的管家下的买家信息.
        List<SlBsBuyerHis> buyerHisList = new ArrayList<>();
        if (null != slBsBuyerList && slBsBuyerList.size() > 0) {

            for (SlBsBuyer slBsBuyer : slBsBuyerList) {
                SlBsBuyerHis slBsBuyerHis = buildSlBsBuyerHis(slBsBuyer);
                buyerHisList.add(slBsBuyerHis);
            }
        }
        if (!CollectionUtils.isEmpty(buyerHisList)) {
            logger.info("插入履历数量为" + buyerHisList.size());
            int saveResult = this.batchSave(buyerHisList);
            logger.info("成功插入履历数量为" + saveResult);
        }

        List<String> buyerIdsList = new ArrayList<>();
        if (null != slBsBuyerList) {
            for (SlBsBuyer buyerId : slBsBuyerList) {
                buyerIdsList.add(buyerId.getBuyerId());
            }
            bS2101101BsBean.setFilterObject("buyerIdList", buyerIdsList);
            if (!CollectionUtils.isEmpty(buyerIdsList)) {
                Date date = DateTimeUtil.getCustomerDate();
                bS2101101BsBean.setUpdTime(date);
                bS2101101BsBean.setUpdId(bs2101101Bean.getUpdId());
                // 修改delFlag 为 1.
                logger.info("解除关系数量为" + slBsBuyerList.size());
                int updateResult = this.modify(SqlID.UPDATE_BS_BUYER_INFO, bS2101101BsBean);
                logger.info("成功解除关系数量为" + updateResult);
                //同步解除买家接口数据
                this.asyncUnBindDate(slBsBuyerList);
            }

        }

    }

    //同步解除买家接口数据
    private void asyncUnBindDate(List<SlBsBuyer> slBsBuyerList) {
        logger.info("同步解除买家接口数据开始");
        if (!CollectionUtils.isEmpty(slBsBuyerList)) {
            IBS2101140RsParam ibs2101140RsParam = new IBS2101140RsParam();
            List<IBS2101140Bean> relationList = new ArrayList<>();
            IBS2101140Bean ibs2101140Bean = null;
            for (SlBsBuyer bsBuyer : slBsBuyerList) {
                ibs2101140RsParam.setSlCode(bsBuyer.getSlCode());
                ibs2101140RsParam.setHouseCode(bsBuyer.getHouseCode());
                ibs2101140Bean = new IBS2101140Bean();
                ibs2101140Bean.setBuyerId(bsBuyer.getBuyerId());
                ibs2101140Bean.setRelationType("0");
                relationList.add(ibs2101140Bean);
            }
            ibs2101140RsParam.setRelationList(relationList);
            CommRestUtil.updateBuyerHkRelationship(ibs2101140RsParam);
        }
        logger.info("同步解除买家接口数据结束");
    }

    // 封装存入履历表中的参数.
    @Transactional
    public SlBsBuyerHis buildSlBsBuyerHis(SlBsBuyer slBsBuyer) {
        SlBsBuyerHis slBsBuyerHis = new SlBsBuyerHis();
        // 买手编码.
        slBsBuyerHis.setSlCode(slBsBuyer.getSlCode());
        // 删除买手时，管家买家解绑原因统一写成买手注销.
        slBsBuyerHis.setBuyershopReason("买手注销");
        // 管家ID.
        slBsBuyerHis.setHouseAccount(slBsBuyer.getHouseCode());
        // 买家ID.
        slBsBuyerHis.setBuyerId(slBsBuyer.getBuyerId());
        // 开始日时.
        slBsBuyerHis.setStartTime(slBsBuyer.getStartTime());
        // 结束日时.
        slBsBuyerHis.setEndTime(slBsBuyer.getEndTime());
        // 认证方式.
        slBsBuyerHis.setApplySide(slBsBuyer.getApplySide());
        // 申请状态.
        slBsBuyerHis.setApplyStatus(slBsBuyer.getApplyStatus());
        // 申请日时.
        slBsBuyerHis.setApplyTime(slBsBuyer.getApplyTime());
        slBsBuyerHis.setHisId(commonLogic.maxId("SL_BS_BUYER_HIS", "HIS_ID"));

        slBsBuyerHis.setDelFlg(slBsBuyer.getDelFlg());
        slBsBuyerHis.setCrtId(slBsBuyer.getCrtId());
        slBsBuyerHis.setUpdId(slBsBuyer.getUpdId());
        slBsBuyerHis.setActId(slBsBuyer.getActId());
        slBsBuyerHis.setCrtTime(slBsBuyer.getCrtTime());
        slBsBuyerHis.setUpdTime(slBsBuyer.getUpdTime());
        slBsBuyerHis.setActTime(slBsBuyer.getActTime());
        slBsBuyerHis.setVer(slBsBuyer.getVer());

        return slBsBuyerHis;

    }

}
