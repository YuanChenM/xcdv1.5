package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.IPD141127RsParam;
import com.msk.product.bean.IPD141127RsResult;
import com.msk.product.bean.IPD141127RsSftStdResult;
import com.msk.product.bean.IPD141146RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月26日 下午17:26:20
 *          产品等级&卫生质量标准接口  返回技术和质量标准列表
 */
@Service
public class IPD141127Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     *
     * @author XHY
     */
    interface SqlId {
        String SQL_ID_FIND_SFT_STD_LIST = "findListSftStd";

    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询产品等级&卫生质量标准
     *
     * @return List 返回list集合
     * @version xhy
     */
    @Transactional(readOnly = true)
    public List<IPD141127RsResult> findListPdGradeHealth(IPD141127RsParam param) {

        if (param == null) {
            param = new IPD141127RsParam();
        }


            /*获取指定的standardId*/
        PdStandard standId = super.findOne(param);
        if (standId == null) throw new BusinessException("输入的参数有误,请核对后输入!(没有找到standardId!)");
            /*查询一级类目*/
        List<IPD141127RsResult> itemList = super.findList(SqlId.SQL_ID_FIND_SFT_STD_LIST, param);
        if (itemList != null) {
            BaseParam param1 = new BaseParam();
            param1.setFilter("standardId", standId.getStandardId().toString());
            for (IPD141127RsResult stdItemList : itemList) {
                param1.setFilter("sftStdItemId", stdItemList.getSftStdClaId());
                List<IPD141127RsSftStdResult> sftStdList = super.findList(param1);
                stdItemList.setSftStdSublist(sftStdList);
            }
        }
        return itemList;
    }

    @Transactional(readOnly = true)
    public List<BaseEntity> selectSourceSft(IPD141146RsParam param) {

        if (param == null) {
            param = new IPD141146RsParam();
        }

        List<BaseEntity> list = new ArrayList<>();

            /*获取指定的standardId*/
        PdStandard standId = super.findOne(param);
        if (standId == null) throw new BusinessException("输入的参数有误,请核对后输入!(没有找到standardId!)");
            /*查询一级类目*/
        List<IPD141127RsResult> itemList = super.findList(SqlId.SQL_ID_FIND_SFT_STD_LIST, param);
        if (itemList != null) {
            BaseParam param1 = new BaseParam();
            param1.setFilter("standardId", standId.getStandardId().toString());
            for (IPD141127RsResult stdItemList : itemList) {
                param1.setFilter("sftStdItemId", stdItemList.getSftStdClaId());
                List<IPD141127RsSftStdResult> sftStdList = super.findList(param1);
                stdItemList.setSftStdSublist(sftStdList);
                list.add(stdItemList);
            }
        }
        return list;
    }
}