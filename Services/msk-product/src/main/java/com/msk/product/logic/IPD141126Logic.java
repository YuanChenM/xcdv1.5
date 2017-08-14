package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsRequest;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.IPD141126Bean;
import com.msk.product.bean.IPD141126RsParam;
import com.msk.product.bean.IPD141126RsResult;
import com.msk.product.bean.IPD141146RsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FjM on 2016/3/11.
 */
@Service
public class IPD141126Logic extends BaseLogic {
    interface SqlId {
        String SQL_ID_FINDPDSTANDARD = "findPdStandard";
        String SQL_ID_FINDFIRSTLEVEL = "findFirstLevel";
        String SQL_ID_FINDSECONDLEVEL = "findSecondLevel";
        String SQL_ID_FINDSECONDTREE = "findSecondTree";
    }
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    @Transactional(readOnly = true)
    public List<IPD141126RsResult> selectSource(RsRequest<IPD141126RsParam> param){

        //创建输入对象
        IPD141126RsParam ipd141126RsParam = param.getParam();
        //创建第二级小对象
        List<IPD141126Bean> tspStdSublist = new ArrayList<IPD141126Bean>();
        //创建第一级大对象
        List<IPD141126RsResult> firstBeans = new ArrayList<IPD141126RsResult>();


        //开始查询
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("classestreeCode", StringUtil.toSafeString(ipd141126RsParam.getClassesCode() + ipd141126RsParam.getMachiningCode() + ipd141126RsParam.getBreedCode()));

        //去pd_standard表里面查出STANDARD_ID
        PdStandard s = super.findOne(SqlId.SQL_ID_FINDPDSTANDARD, baseParam);
        if (s ==null) throw new BusinessException("输入的参数有误,请核对后输入!(没有找到standardId!)");

        String standardId = s.getStandardId().toString();
        //去pd_fed_std和pd_fed_std_item里面查出第一级数据
        baseParam.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));

        firstBeans = super.findList(SqlId.SQL_ID_FINDFIRSTLEVEL,baseParam);

        int i = 0;

        //迭代第一级的集合，获得单个bean，设置单个bean的gnqStdSublist（第二级bean的集合）
        for(IPD141126RsResult firstBean : firstBeans){
            String parentId = firstBean.getTspStdClaId();
            baseParam.setFilter("parentId",parentId);
            baseParam.setFilter("standardId",standardId);

            tspStdSublist = super.findList(SqlId.SQL_ID_FINDSECONDLEVEL, baseParam);
            if(tspStdSublist.size()!=0){
                i=i+1;
            }
            firstBean.setTspStdSublist(tspStdSublist);
        }

        if(i==0){
            throw new BusinessException("数据为空！");
        }
        return firstBeans;
    }

    @Transactional(readOnly = true)
    public List<BaseEntity> selectSourceTsp(RsRequest<IPD141146RsParam> param){

        //创建输入对象
        IPD141146RsParam ipd141126RsParam = param.getParam();
        //创建第二级小对象
        List<IPD141126Bean> tspStdSublist = new ArrayList<IPD141126Bean>();
        //创建第一级大对象
        List<IPD141126RsResult> firstBeans = new ArrayList<IPD141126RsResult>();

        List<BaseEntity> list = new ArrayList<BaseEntity>();
        //开始查询
        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("classestreeCode", StringUtil.toSafeString(ipd141126RsParam.getClassesCode() + ipd141126RsParam.getMachiningCode() + ipd141126RsParam.getBreedCode()));

        //去pd_standard表里面查出STANDARD_ID
        PdStandard s = super.findOne(SqlId.SQL_ID_FINDPDSTANDARD, baseParam);
        if (s ==null) throw new BusinessException("输入的参数有误,请核对后输入!(没有找到standardId!)");

        String standardId = s.getStandardId().toString();
        //去pd_fed_std和pd_fed_std_item里面查出第一级数据
        baseParam.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));

        firstBeans = super.findList(SqlId.SQL_ID_FINDFIRSTLEVEL,baseParam);

        int i = 0;

        //迭代第一级的集合，获得单个bean，设置单个bean的gnqStdSublist（第二级bean的集合）
        for(IPD141126RsResult firstBean : firstBeans){
            String parentId = firstBean.getTspStdClaId();
            baseParam.setFilter("parentId",parentId);
            baseParam.setFilter("standardId",standardId);

            tspStdSublist = super.findList(SqlId.SQL_ID_FINDSECONDLEVEL, baseParam);
            if(tspStdSublist.size()!=0){
                i=i+1;
            }
            firstBean.setTspStdSublist(tspStdSublist);
            list.add(firstBean);
        }

        if(i==0){
            throw new BusinessException("数据为空！");
        }
        return list;
    }

}
