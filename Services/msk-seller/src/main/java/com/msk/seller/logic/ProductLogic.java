package com.msk.seller.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.*;
import com.msk.product.bean.PDInfoParam;
import com.msk.product.bean.PDInfoResult;
import com.msk.product.bean.ProductBeanResult;
import com.msk.seller.utils.ISLRestUtil;
import com.msk.seller.utils.SLControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liu_yan2 on 2016/6/23.
 */
public class ProductLogic extends BaseLogic {

    /**
     * 调用接口查询所有一级分类信息
     * @return
     */
    public List<PdClasses> findPdClasses(BaseParam param) {
        RsResponse<PdClasses[]> response= ISLRestUtil.findPdClasses(param);
        PdClasses[] results = response.getResult();
        return (null == results) ? new ArrayList<PdClasses>() : Arrays.asList(results);
    }

    /**
     * 调用接口 查询二级分类信息
     * liu_yan2
     * @param param
     * @return
     */
    public List<PdMachining> findPdMachining(BaseParam param) {
        RsResponse<PdMachining[]> response= ISLRestUtil.findPdMachining(param);
        PdMachining[] results = response.getResult();
        return (null == results) ? new ArrayList<PdMachining>() : Arrays.asList(results);
    }

    /**
     * 调用接口 查询品种信息
     * liu_yan2
     * 查询品种信息
     * @param param
     * @return
     */
    public List<PdBreed> findPdBreed(BaseParam param) {
        RsResponse<PdBreed[]> response= ISLRestUtil.findPdBreed(param);
        PdBreed[] results = response.getResult();
        return (null == results) ? new ArrayList<PdBreed>() : Arrays.asList(results);
    }




    /**
     * 调用接口 查询特征信息
     * liu_yan2
     *
     * @param param
     * @return
     */
    public List<PdFeature> findPdFeature(BaseParam param) {
        RsResponse<PdFeature[]> response= ISLRestUtil.findPdFeature(param);
        PdFeature[] results = response.getResult();
        return (null == results) ? new ArrayList<PdFeature>() : Arrays.asList(results);
    }

    /**
     * 调用接口 查询包装净重信息
     * liu_yan2
     *
     * @param param
     * @return
     */
    public List<PdWeight> findPdWeight(BaseParam param) {
        RsResponse<PdWeight[]> response= ISLRestUtil.findPdWeight(param);
        PdWeight[] results = response.getResult();
        return (null == results) ? new ArrayList<PdWeight>() : Arrays.asList(results);
    }

    /**
     * 调用接口查询产品标准表列表
     * liu_yan2
     * @param param
     * @return
     */
    public List<PdStandard> findPdStandard(BaseParam  param) {
        RsResponse<PdStandard[]> response= ISLRestUtil.findProductStandard(param);
        PdStandard[] results = response.getResult();
        return (null == results) ? new ArrayList<PdStandard>() : Arrays.asList(results);
    }


    /**
     * 调用接口 查询外包装标准
     * liu_yan2
     * @param param
     * @return
     */
    public List<PdNormsStd> findPdNormsStd(BasePageParam param) {
        RsResponse<PdNormsStd[]> response= ISLRestUtil.findPdNormsStd(param);
        PdNormsStd[] results = response.getResult();
        return (null == results) ? new ArrayList<PdNormsStd>() : Arrays.asList(results);
    }

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
