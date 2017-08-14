package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.IPD141147RsParam;
import com.msk.product.bean.IPD141147RsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author pxg
 * @version 创建时间：2016年1月13日 下午14:26:20
 *          产品分类接口
 */
@Service
public class IPD141147Logic extends BaseLogic {


    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 查询数据
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<IPD141147RsResult> queryData(IPD141147RsParam param){
        BaseParam params=new BaseParam();
        params.getFilterMap().put("paramData",param);
        List<IPD141147RsResult> list=super.findList(params);
        if(!CollectionUtils.isEmpty(list) && list.size()>0){
            for (IPD141147RsResult ipd141147RsResult:list) {
                ipd141147RsResult.setManufactureDate("见外包装");
                ipd141147RsResult.setStorageCondition("-18度冷藏");
                ipd141147RsResult.setShelfLife("12个月");
                if(param.getGradeCode().equals("1")){
                    ipd141147RsResult.setBrandName("美侍客");
                    ipd141147RsResult.setPdGrade("A1");
                }else if(param.getGradeCode().equals("2")){
                    ipd141147RsResult.setBrandName("神农客");
                    ipd141147RsResult.setPdGrade("A2");
                }
                else if(param.getGradeCode().equals("3")){
                    ipd141147RsResult.setBrandName("绿美通");
                    ipd141147RsResult.setPdGrade("A3");
                }else {
                    ipd141147RsResult.setBrandName("神农客");
                    ipd141147RsResult.setPdGrade("A2");
                }

            }
        }
        return list;
    }
}