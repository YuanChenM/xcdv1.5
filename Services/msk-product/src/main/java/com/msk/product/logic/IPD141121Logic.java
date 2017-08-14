package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BaseEntity;
import com.msk.product.bean.IPD141121RsParam;
import com.msk.product.bean.IPD141121RsResult;
import com.msk.product.bean.IPD141146RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年3月10日 下午15:26:20
 *          产品技术标准接口  返回技术列表
 */
@Service
public class IPD141121Logic extends BaseLogic {



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
    public List<IPD141121RsResult> findListMct(IPD141121RsParam param) {

        if (param == null) {
            param = new IPD141121RsParam();
        }
        BaseParam baseParam = new BaseParam();
        if(StringUtils.hasLength(param.getClassesCode()) && StringUtils.hasLength(param.getMachiningCode()) && StringUtils.hasLength(param.getBreedCode()))
            baseParam.setFilter("classestreeCode",param.getClassesCode()+param.getMachiningCode()+param.getBreedCode());
        if(StringUtils.hasLength(param.getLevelId()))
            baseParam.setFilter("levelId",param.getLevelId());
        if(StringUtils.hasLength(param.getStdItemId()))
            baseParam.setFilter("stdItemId",param.getStdItemId());
        if(StringUtils.hasLength(param.getStandardId()))
            baseParam.setFilter("standardId",param.getStandardId());
        List<IPD141121RsResult>  beanList= super.findList(baseParam);
        return beanList;
    }

    @Transactional(readOnly = true)
    public List<BaseEntity> selectSourceMct(IPD141146RsParam param) {

        if (param == null) {
            param = new IPD141146RsParam();
        }
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("classestreeCode",param.getClassesCode()+param.getMachiningCode()+param.getBreedCode());
        List<BaseEntity> beanList= super.findList(baseParam);
        return beanList;
    }

}