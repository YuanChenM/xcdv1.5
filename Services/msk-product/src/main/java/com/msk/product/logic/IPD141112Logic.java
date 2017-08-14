package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.product.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xhy
 * @version 创建时间：2016年1月26日 下午17:26:20
 *          产品等级&卫生质量标准接口  返回技术和质量标准列表
 */
@Service
public class IPD141112Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     *
     * @author jiang_nan
     */
    interface SqlId {
        String SQL_ID_FIND_LIST_PD_QLT_STD = "findListPdQltStd"; //第四级类目
        String SQL_ID_FIND_LIST_PD_QLT_STD_SUB_CLA = "findListPdQltStdSubCla"; //第三级类目级类目
        String SQL_ID_FIND_LIST_PD_QLT_STD_CLA = "findListPdQltStdCla"; //第二级类目级类目
        String SQL_ID_FIND_LIST_PD_TNC_STD = "findListPdTncStd"; //查询技术标准id
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
    public IPD141112RsResult findListPdGradeHealth(IPD141112RsParam param) {

        if (param == null) {
            param = new IPD141112RsParam();
        }
        //创建返回对象
        IPD141112RsResult rsResult = new IPD141112RsResult();
        //获取分页参数
        rsResult.setTotalCount(this.getPageCount(param));
        rsResult.setPageNo(param.getPageNo());
        if (rsResult.getTotalCount() != NumberConst.IntDef.INT_ZERO) {
            rsResult.setTotalPage(rsResult.getTotalCount(), param.getPageCount());

            //查询所有数据 获取一级类目

            List<IPD141112RsPdClaSubItemResult> list = this.findPageList(param);//一级类目查询
            //循环一级类目,查询数据
            for (IPD141112RsPdClaSubItemResult claSubItem : list) {
                IPD141112RsPdStdClaResult claNew = new IPD141112RsPdStdClaResult();
                claSubItem.setProductCode(claSubItem.getClassesCode().concat(claSubItem.getBreedCode()).concat(claSubItem.getFeatureCode()));
                claSubItem.setProductName(claSubItem.getClassesName() + "/" + claSubItem.getBreedName() + "/" + claSubItem.getFeatureName());
                //查询二级类目
                BaseParam baseparam = new BaseParam();
                List<IPD141112RsPdStdClaResult> cla = this.findList(SqlId.SQL_ID_FIND_LIST_PD_QLT_STD_CLA, baseparam);//二级类目
                for (IPD141112RsPdStdClaResult claResult : cla) {
                    BaseParam baseparam1 = new BaseParam();
                    baseparam1.setFilter("qltStdClaId", claResult.getQltStdClaId());
                    List<IPD141112RsPdStdSubResult> subResult = this.findList(SqlId.SQL_ID_FIND_LIST_PD_QLT_STD_SUB_CLA, baseparam1); //三级类目
                    for (IPD141112RsPdStdSubResult subParam : subResult) {
                        BaseParam baseparam2 = new BaseParam();
                        baseparam2.setFilter("standardId", claSubItem.getStandardId().toString());
                        baseparam2.setFilter("stdSubId", subParam.getQltStdSubId());
                        baseparam2.setFilter("stdClaId", subParam.getQltStdClaId());
                        List<IPD141112RsPdStdItemResult> itemResult = this.findList(SqlId.SQL_ID_FIND_LIST_PD_QLT_STD, baseparam2);
                        subParam.setQltStdItemList(itemResult);
                    }
                    claResult.setQltStdSubList(subResult);
                }
                //查询产品规格参数列表
                BaseParam baseparam3 = new BaseParam();
                baseparam3.setFilter("standardId", claSubItem.getStandardId().toString());
                List<IPD141112RsPdTncResult> tncList = this.findList(SqlId.SQL_ID_FIND_LIST_PD_TNC_STD, baseparam3);

                claSubItem.setTncSpecList(tncList);
                claSubItem.setQtySpecList(cla);
            }
            rsResult.setPdList(list);
        }
        return rsResult;
    }
}