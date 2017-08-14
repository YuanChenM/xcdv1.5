package com.msk.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.DateTimeUtil;
import com.msk.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msk.common.bean.param.BasePageParam;
import com.msk.common.bean.result.PageResult;
import com.msk.common.data.jpa.BaseRepository;
import com.msk.common.data.jpa.condition.BaseOperatorEnum;
import com.msk.common.data.jpa.condition.Filter;
import com.msk.order.entity.SoReturn;
import com.msk.order.repository.SoReturnRepository;
import com.msk.order.service.BaseService;
import com.msk.order.service.ISO151506Service;
import org.springframework.util.CollectionUtils;

/**
 * ISO151506_退货单列表
 * Created by wang_shuai on 2016/8/11.
 */
@Service
public class ISO151506ServiceImpl extends BaseService implements ISO151506Service {
    @Autowired
    private SoReturnRepository returnRepository;

    @Override
    public BaseRepository getRepository() {
        return returnRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<SoReturn> findPageList(final BasePageParam basePageParam) {
        boolean paging = basePageParam.isPaging();
        int recordsTotal = 0;
        List<SoReturn> data = null;
        Filter<SoReturn> filter = getFilterByBasePageParam(basePageParam);
        Sort sort = new Sort(Sort.Direction.DESC, "returnId");
        if (paging) {// 分页
            int page = basePageParam.getStartPos() / basePageParam.getPageSize();
            Pageable pageable = new PageRequest(page, basePageParam.getPageSize(), sort);
            Page<SoReturn> orderPage = this.findAll(filter, pageable);
            recordsTotal = new Long(orderPage.getTotalElements()).intValue();
            data = orderPage.getContent();
        } else {// 不分页
            data = this.findAll(filter, sort);
            if (!CollectionUtils.isEmpty(data)) {
                recordsTotal = data.size();
            }
        }
        PageResult<SoReturn> result = new PageResult();
        result.setRecordsTotal(recordsTotal);
        result.setData(data);
        return result;

    }

    public Filter<SoReturn> getFilterByBasePageParam(BasePageParam baseParam) {
        Filter<SoReturn> filter = new Filter<>();
        filter.add("delFlg", BaseOperatorEnum.EQUAL, SystemConstant.DelFlg.ENABLE);
        Map<String, Object> filterMap = baseParam.getFilterMap();
        String orderCode = (String) filterMap.get("orderCode");
        orderCode = orderCode.trim();
        if (!StringUtil.isEmpty(orderCode)) {
            filter.add("orderCode", BaseOperatorEnum.LIKE, "*" + orderCode + "*");
        }
        String buyerCode = (String) filterMap.get("buyerCode");
        buyerCode = buyerCode.trim();
        if (!StringUtil.isEmpty(buyerCode)) {
            filter.add("buyerCode", BaseOperatorEnum.LIKE, "*" + buyerCode + "*");
        }
        String buyerName = (String) filterMap.get("buyerName");
        buyerName = buyerName.trim();
        if (!StringUtil.isEmpty(buyerName)) {
            filter.add("buyerName", BaseOperatorEnum.LIKE, "*" + buyerName + "*");
        }
        String returnCode = (String) filterMap.get("returnCodeQuery");
        returnCode = returnCode.trim();
        if (!StringUtil.isEmpty(returnCode)) {
            filter.add("returnCode", BaseOperatorEnum.LIKE, "*" + returnCode + "*");
        }
        String returnSource = (String) filterMap.get("returnSource");
        if (!StringUtil.isEmpty(returnSource)) {
            filter.add("returnSource", BaseOperatorEnum.EQUAL, Integer.valueOf(returnSource));
        }
        String isPaid = (String) filterMap.get("isPaid");
        if (!StringUtil.isEmpty(isPaid)) {
            filter.add("isPaid", BaseOperatorEnum.EQUAL, isPaid);
        }
        String returnMode = (String) filterMap.get("returnMode");
        if (!StringUtil.isEmpty(returnMode)) {
            filter.add("returnMode", BaseOperatorEnum.EQUAL, returnMode);
        }
        String startTime = (String) filterMap.get("startTime");
        if (!StringUtil.isEmpty(startTime)) {
            startTime = startTime + " 00:00:00";
            Date startDate = DateTimeUtil.parseDate(startTime, "yyyy-MM-dd HH:mm:ss");
            filter.add("applyTime", BaseOperatorEnum.GREATERTHAN, startDate);
        }
        String endTime = (String) filterMap.get("endTime");
        if (!StringUtil.isEmpty(endTime)) {
            endTime = endTime + " 23:59:59";
            Date endDate = DateTimeUtil.parseDate(endTime, "yyyy-MM-dd HH:mm:ss");
            filter.add("applyTime", BaseOperatorEnum.LESSTHAN, endDate);
        }
        String returnStatus = (String) filterMap.get("returnStatus");
        if (!StringUtil.isEmpty(returnStatus)){
            String[] returnStatusStrArray = returnStatus.split(",");
            Integer[] returnStatusArray = new Integer[returnStatusStrArray.length];
            for (int i=0;i<returnStatusStrArray.length;i++){
                returnStatusArray[i] = Integer.parseInt(returnStatusStrArray[i]);
            }
            if (returnStatusArray.length != 0) {
                filter.add("returnStatus", BaseOperatorEnum.IN, returnStatusArray);
            }
        }

        String returnType = (String) filterMap.get("returnType");
        if (!StringUtil.isEmpty(returnType)) {
            filter.add("returnType", BaseOperatorEnum.EQUAL, returnType);
        }

        return filter;
    }


}
