package com.msk.product.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.district.bean.DistrictParam;
import com.msk.district.bean.LgcsAreaBean;
import com.msk.product.bean.IPD14111401RsResult;
import com.msk.product.bean.IPD141114RsResult;
import com.msk.product.utils.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  rwf
 * Created by Administrator on 2016/1/26.
 */
@Service
public class IPD141114Logic extends BaseLogic {
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    /**
     * 查询物流区sqlId
     */
    interface SqlId {
        static final String SQL_ID_FIND_ALL_LOGIAREA_INFO = "findAllLogiarea";
    }
    /**
     * @return IPD141114RsResult 返回物流区集合
     * 查询数据库中所有的物流区编号和物流区名称
     */
    @Transactional(readOnly = true)
    public IPD141114RsResult findAllRecordList() {
        IPD141114RsResult ipd141114RsResult= new IPD141114RsResult();
        /*add by dai_youcheng start at 2016-6-17*/
        DistrictParam param = new DistrictParam();
        List<LgcsAreaBean> listArea = RestUtil.findDistrict(param);
        List<IPD14111401RsResult> list = new ArrayList<IPD14111401RsResult>();
        for(LgcsAreaBean bean :listArea){
           IPD14111401RsResult ipd14111401RsResult = new  IPD14111401RsResult();
           ipd14111401RsResult.setLogiAreaCode(bean.getLgcsAreaCode());
           ipd14111401RsResult.setLogiAreaName(bean.getLgcsAreaName());
           list.add(ipd14111401RsResult);
        }
        //List<IPD14111401RsResult> list=super.findList(SqlId.SQL_ID_FIND_ALL_LOGIAREA_INFO,null);
        /*add by dai_youcheng start at 2016-6-17*/
        ipd141114RsResult.setLogiAreaList(list);
        return ipd141114RsResult;
    }

}
