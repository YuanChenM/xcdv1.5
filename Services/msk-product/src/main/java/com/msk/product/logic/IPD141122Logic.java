package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.BaseEntity;
import com.msk.product.bean.IPD141122RsParam;
import com.msk.product.bean.IPD141122RsResult;
import com.msk.product.bean.IPD141146RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjm on 2016/3/9.
 */
@Service
public class IPD141122Logic extends BaseLogic {

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
    @Transactional(readOnly = true)
    public IPD141122RsResult selectSource(RsRequest<IPD141122RsParam> param){
        // 创建输入参数
        IPD141122RsParam ipd141122RsParam = param.getParam();

        //开始查询
        BaseParam params = new BaseParam();

        if(ipd141122RsParam.getBreedCode()=="" || ipd141122RsParam.getBreedCode()==null){
            String classestreeCode = ipd141122RsParam.getClassesCode()+ipd141122RsParam.getMachiningCode();
            params.setFilter("classestreeCode", StringUtil.toSafeString(classestreeCode));
        }else{
            String classestreeCode = ipd141122RsParam.getClassesCode()+ipd141122RsParam.getMachiningCode()+ipd141122RsParam.getBreedCode();
            params.setFilter("classestreeCode", StringUtil.toSafeString(classestreeCode));
        }

        IPD141122RsResult s = super.findOne(params);

        return s;
    }

    @Transactional(readOnly = true)
    public List<BaseEntity> selectSourceMat(RsRequest<IPD141146RsParam> param){
        // 创建输入参数
        IPD141146RsParam ipd141122RsParam = param.getParam();
        List<BaseEntity> results = new ArrayList<>();

        //开始查询
        BaseParam params = new BaseParam();

        if(ipd141122RsParam.getBreedCode()=="" || ipd141122RsParam.getBreedCode()==null){
            String classestreeCode = ipd141122RsParam.getClassesCode()+ipd141122RsParam.getMachiningCode();
            params.setFilter("classestreeCode", StringUtil.toSafeString(classestreeCode));
        }else{
            String classestreeCode = ipd141122RsParam.getClassesCode()+ipd141122RsParam.getMachiningCode()+ipd141122RsParam.getBreedCode();
            params.setFilter("classestreeCode", StringUtil.toSafeString(classestreeCode));
        }

        IPD141122RsResult s = super.findOne(params);

        results.add(s);
        return results;
    }

}
