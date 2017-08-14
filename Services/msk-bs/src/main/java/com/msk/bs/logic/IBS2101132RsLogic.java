package com.msk.bs.logic;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.bs.bean.IBS2101132RsParam;
import com.msk.bs.bean.IBS2101140Bean;
import com.msk.bs.bean.IBS2101140RsParam;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.SlBsBuyer;
import com.msk.core.entity.SlBsBuyerHis;
import com.msk.core.entity.SlBsReason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gao_min on 2016/10/13.
 */
@Service
public class IBS2101132RsLogic extends BaseLogic {
    private Logger logger = LoggerFactory.getLogger(IBS2101132RsLogic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Autowired
    private CommonLogic commonLogic;

    interface SqlId {
        // 根据原因ID查询关系解除原因 .
        static String FIND_SL_BS_REASON = "findSlBsReason";
        // 修改根据买家/管家编码查询管家专属会员表信息，将del_flag设为1.
        static String FIND_BUYERID_IFEXIT = "findBuyerIfExist";
        // 查询请求中的管家编码是否在表中存在.
        static String FIND_HOUSE_CODE_IFEXIT = "findHouseCodeIfExist";
        // 修改delFlag 为 1.
        static String UPDATE_BS_BUYER_INFO = "updateBsBuyerInfo";
    }

    /**
     * 解除买家管家关系
     *
     * @param param
     * @return
     */
    @Transactional
    public RsResponse relieveRelationship(IBS2101132RsParam param) {
        RsResponse rs = new RsResponse();

        // 根据买家/管家编码查询sl_bs_buyer表中要删除的内容.
        // 1. 请求中有买家ID时.
        if (null != param.getBuyerIds() && param.getBuyerIds().size() > 0) {
            // 查询buyerId是否存在.
            param.setFilterObject("buyerIds", param.getBuyerIds());
            List<SlBsBuyer> slBsBuyerList = this.findList(SqlId.FIND_BUYERID_IFEXIT, param);
            List<String> buyerIdList = new ArrayList<String>();
            for (SlBsBuyer slBsBuyer : slBsBuyerList) {
                buyerIdList.add(slBsBuyer.getBuyerId());
            }
            StringBuffer noExistBuyerId = new StringBuffer();
            for (String i : param.getBuyerIds()) {
                if (!buyerIdList.contains(i)) {
                    noExistBuyerId.append(i + ",");
                }
            }
            if (null != noExistBuyerId && noExistBuyerId.length() > 0) {
                String buyerId = noExistBuyerId.toString();
                buyerId = buyerId.substring(0, buyerId.length() - 1);
                rs.setStatus(SystemConst.RsStatus.FAIL);
                if (!StringUtil.isNullOrEmpty(param.getHouseCode())) {
                    rs.setMessage("解绑失败！该管家编码" + param.getHouseCode() + "对应的买家编码" + buyerId + "不存在！");
                } else {
                    rs.setMessage("解绑失败！买家编码" + buyerId + "不存在！");
                }
                return rs;
            }
            rs = buildResponse(slBsBuyerList, param);
        }// 请求中只有管家编码时.
        else {
            List<SlBsBuyer> slHouseCodeList = this.findList(SqlId.FIND_HOUSE_CODE_IFEXIT, param);
            if (null != slHouseCodeList && slHouseCodeList.size() > 0) {
                rs = buildResponse(slHouseCodeList, param);

            } else {
                rs.setStatus(SystemConst.RsStatus.FAIL);
                rs.setMessage("解绑失败！该管家编码" + param.getHouseCode() + "不存在！");
                return rs;
            }
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("买家管家关系解绑成功!");
        return rs;
    }
    @Transactional
    public RsResponse buildResponse(List<SlBsBuyer> slBsBuyerList, IBS2101132RsParam param) {
        RsResponse rs = new RsResponse();
        List<SlBsBuyerHis> buyerHisList = new ArrayList<>();
        if (null != slBsBuyerList && slBsBuyerList.size() > 0) {
            for (SlBsBuyer slBsBuyer : slBsBuyerList) {
                SlBsBuyerHis slBsBuyerHis = buildSlBsBuyerHis(slBsBuyer);
                if (!StringUtil.isNullOrEmpty(param.getUnbandSide())) {
                    // 根据原因ID查询解绑原因sl_bs_reason.
                    SlBsReason slBsReason = this.findOne(SqlId.FIND_SL_BS_REASON, param);
                    if (null != slBsReason) {
                        // 买家解除关系原因.
                        if (param.getUnbandSide().equals("1")) {
                            slBsBuyerHis.setBuyerReason(slBsReason.getMsqReason());
                        }
                        // 买手店解除关系原因.
                        if (param.getUnbandSide().equals("2")) {
                            slBsBuyerHis.setBuyershopReason(slBsReason.getMsqReason());
                        }
                    }
                }
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
            param.setFilterObject("buyerIdList", buyerIdsList);
            if (!CollectionUtils.isEmpty(buyerIdsList)) {
                // 修改delFlag 为 1.
                logger.info("解除关系数量为" + slBsBuyerList.size());
                int updateResult = this.modify(SqlId.UPDATE_BS_BUYER_INFO, param);
                logger.info("成功解除关系数量为" + updateResult);

                //同步解除买家接口数据
                this.asyncUnBindDate(slBsBuyerList);
            }
        }
        return rs;
    }
    @Transactional
    public SlBsBuyerHis buildSlBsBuyerHis(SlBsBuyer slBsBuyer) {
        SlBsBuyerHis slBsBuyerHis = new SlBsBuyerHis();
        // 买手编码.
        slBsBuyerHis.setSlCode(slBsBuyer.getSlCode());
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


    //同步解除买家接口数据
    private void asyncUnBindDate(List<SlBsBuyer> slBsBuyerList){
        logger.info("同步解除买家接口数据开始");
        if(!CollectionUtils.isEmpty(slBsBuyerList)){
            IBS2101140RsParam ibs2101140RsParam = new IBS2101140RsParam();
            List<IBS2101140Bean>  relationList = new ArrayList<>();
            IBS2101140Bean ibs2101140Bean = null;
            for(SlBsBuyer bsBuyer : slBsBuyerList){
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
}
