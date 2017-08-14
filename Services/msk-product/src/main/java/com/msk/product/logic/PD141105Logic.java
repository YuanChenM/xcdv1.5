package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdQltStd;
import com.msk.core.entity.PdStandard;
import com.msk.product.bean.PD141105Param;
import com.msk.product.bean.QltStdCla;
import com.msk.product.bean.QltStdItemAndQltstd;
import com.msk.product.bean.QltStdSubCla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 质量标准Logic
 * 
 * @author jiang_nan
 *
 */
@Service
public class PD141105Logic extends BaseLogic {

    /**
     * SQL Map 中SQL ID定义
     * 
     * @author jiang_nan
     * 
     */
    interface SqlId {
        String SQL_ID_FIND_LIST_BY_QLT_STD_CLA_ID = "findListByQltStdClaId";
        String SQL_ID_FIND_LIST_BY_PD_QLT_STD_SUB_ID = "findListByQltStdSubId";
        String SQL_ID_UPDATE_STANDARD_ID = "updateStandardId";
    }

    /**
     * 根据产品标准ID获得产品包装标准
     * 
     * @param standardId 标准ID
     * @return 包装标准数据
     */
    @Transactional(readOnly = true)
    public PdStandard findPdStandardId(String standardId) {
        BaseParam param = new BaseParam();
        param.setFilter("standardId", standardId);
        PdStandard pdStandard = this.findOne(param);
        return pdStandard;
    }

    /**
     * 根据产品标准ID获得质量标准列表数据
     * 
     * @param standardId 标准ID
     * @return 质量标准数据
     */
    @Transactional(readOnly = true)
    public List<QltStdCla> findListByStandardId(String standardId) {
        BaseParam param = new BaseParam();
        List<QltStdCla> qltStdList = this.findList(param);
        if (!CollectionUtils.isEmpty(qltStdList)) {
            for (QltStdCla qltStdCla : qltStdList) {
                String qltStdClaId = qltStdCla.getQltStdClaId();
                List<QltStdSubCla> qualitySubStandardList = this.findListByPdQuaStdId(qltStdClaId, standardId);
                qltStdCla.setQltStdSubClaList(qualitySubStandardList);
            }
        }
        return qltStdList;
    }

    /**
     * 根据质量标准获得子标准数据
     * 
     * @param qltStdClaId 质量标准数据
     * @param standardId 标准ID
     * @return 子标准数据
     */
    @Transactional(readOnly = true)
    public List<QltStdSubCla> findListByPdQuaStdId(String qltStdClaId, String standardId) {
        BaseParam param = new BaseParam();
        param.setFilter("qltStdClaId", qltStdClaId);
        List<QltStdSubCla> qltStdSubClaList = this.findList(SqlId.SQL_ID_FIND_LIST_BY_QLT_STD_CLA_ID, param);
        if (!CollectionUtils.isEmpty(qltStdSubClaList)) {
            for (QltStdSubCla qtStdItem : qltStdSubClaList) {
                String qltStdSubId = qtStdItem.getQltStdSubId();
                List<QltStdItemAndQltstd> qltStdItemAndQltstd = this.findListByPdQuaStdSubId(qltStdSubId, standardId);
                qtStdItem.setQltStdItemAndQltstdList(qltStdItemAndQltstd);
            }
        }
        return qltStdSubClaList;
    }

    /**
     * 根据子标准获得具体标准数据
     * 
     * @param qltStdSubId 子标准数据
     * @param standardId 标准ID
     * @return 标准数据
     */
    @Transactional(readOnly = true)
    public List<QltStdItemAndQltstd> findListByPdQuaStdSubId(String qltStdSubId, String standardId) {
        BaseParam param = new BaseParam();
        param.setFilter("qltStdSubId", qltStdSubId);
        param.setFilter("standardId", standardId);
        List<QltStdItemAndQltstd> qltStdSubClaList = this.findList(SqlId.SQL_ID_FIND_LIST_BY_PD_QLT_STD_SUB_ID, param);
        return qltStdSubClaList;
    }

    /**
     * 更新标准值
     * 
     * @param param param
     */
    @Transactional
    public void modify(PD141105Param param) {
        PdStandard standard = this.findPdStandardId(param.getStandardId());
        if ("1".equals(standard.getQltFlg())) {
            String[] pdQuaStdValIdArray = param.getPdQltStdValIdArray();
            String[] pdQuaStdExcValArray = param.getPdQltStdExcValArray();
            String[] pdQuaStdSuitValArray = param.getPdQltStdSuitValArray();
            String[] pdQuaStdUnqualValArray = param.getPdQltStdUnqualValArray();
            String[] remarkArray = param.getRemarkArray();
            String[] checks = param.getChecks();
            int length = pdQuaStdValIdArray.length;
            for (int i = 0; i < length; i++) {
                String pdQuaStdValId = pdQuaStdValIdArray[i];
                String pdQuaStdExcVal = pdQuaStdExcValArray[i];
                String pdQuaStdSuitVal = pdQuaStdSuitValArray[i];
                String pdQuaStdUnqualVal = pdQuaStdUnqualValArray[i];
                String remark = remarkArray[i];
                if (i>checks.length-1){
                  return ;
                }
                String check = checks[i];
                if ("0".equals(check)) {
                    PdQltStd pdQltStdValue = new PdQltStd();
                    pdQltStdValue.setStandardId(Long.parseLong(param.getStandardId()));
                    pdQltStdValue.setQltStdItemId(pdQuaStdValId);
                    pdQltStdValue.setQltStdExcVal(pdQuaStdExcVal);
                    pdQltStdValue.setQltStdSuitVal(pdQuaStdSuitVal);
                    pdQltStdValue.setQltStdUnqualVal(pdQuaStdUnqualVal);
                    pdQltStdValue.setUpdTime(new Date());
                    pdQltStdValue.setRemark(remark);
                    pdQltStdValue.setDelFlg(check);
                    /**Modify: 添加公共属性 2016/09/20   BY  任强  Start*/
                    pdQltStdValue.setUpdId(param.getUpdId());
                    /**Modify: 添加公共属性 2016/09/20   BY  任强  End**/
                    super.modify(pdQltStdValue);
                } else {
                    BaseParam par = new BaseParam();
                    par.setFilter("standardId", param.getStandardId());
                    par.setFilter("qltStdItemId", pdQuaStdValId);
                    int count = this.getCount("count", par);
                    if (count > 0) {
                        PdQltStd pdQltStdValue = new PdQltStd();
                        pdQltStdValue.setStandardId(Long.parseLong(param.getStandardId()));
                        pdQltStdValue.setQltStdItemId(pdQuaStdValId);
                        pdQltStdValue.setQltStdExcVal(pdQuaStdExcVal);
                        pdQltStdValue.setQltStdSuitVal(pdQuaStdSuitVal);
                        pdQltStdValue.setQltStdUnqualVal(pdQuaStdUnqualVal);
                        pdQltStdValue.setRemark(remark);
                        pdQltStdValue.setDelFlg(check);
                        /**Modify: 添加公共属性 2016/09/20   BY  任强  Start*/
                        pdQltStdValue.setUpdId(param.getUpdId());
                        /**Modify: 添加公共属性 2016/09/20   BY  任强  End**/
                        pdQltStdValue.setUpdTime(new Date());
                        super.modify(pdQltStdValue);
                    }else{
                        PdQltStd pdQltStdValue = new PdQltStd();
                        pdQltStdValue.setStandardId(Long.parseLong(param.getStandardId()));
                        pdQltStdValue.setQltStdItemId(pdQuaStdValId);
                        pdQltStdValue.setQltStdExcVal(pdQuaStdExcVal);
                        pdQltStdValue.setQltStdSuitVal(pdQuaStdSuitVal);
                        pdQltStdValue.setQltStdUnqualVal(pdQuaStdUnqualVal);
                        pdQltStdValue.setRemark(remark);
                        pdQltStdValue.setDelFlg(check);
                        /**Modify: 添加公共属性 2016/09/20   BY  任强  Start*/
                        pdQltStdValue.setActId(param.getActId());
                        /**Modify: 添加公共属性 2016/09/20   BY  任强  End**/
                        pdQltStdValue.setCrtTime(new Date());
                        super.save(pdQltStdValue);
                    }
                }

            }
        } else {
            // 新增
            String[] pdQuaStdValIdArray = param.getPdQltStdValIdArray();
            String[] pdQuaStdExcValArray = param.getPdQltStdExcValArray();
            String[] pdQuaStdSuitValArray = param.getPdQltStdSuitValArray();
            String[] pdQuaStdUnqualValArray = param.getPdQltStdUnqualValArray();
            String[] remarkArray = param.getRemarkArray();
            String[] checks = param.getChecks();
            int length = pdQuaStdValIdArray.length;
            for (int i = 0; i < length; i++) {
                String pdQuaStdValId = pdQuaStdValIdArray[i];
                String pdQuaStdExcVal = pdQuaStdExcValArray[i];
                String pdQuaStdSuitVal = pdQuaStdSuitValArray[i];
                String pdQuaStdUnqualVal = pdQuaStdUnqualValArray[i];
                String remark = remarkArray[i];
                String check = checks[i];
                PdQltStd pdQltStdValue = new PdQltStd();
                pdQltStdValue.setStandardId(Long.parseLong(param.getStandardId()));
                pdQltStdValue.setQltStdItemId(pdQuaStdValId);
                pdQltStdValue.setQltStdExcVal(pdQuaStdExcVal);
                pdQltStdValue.setQltStdSuitVal(pdQuaStdSuitVal);
                pdQltStdValue.setQltStdUnqualVal(pdQuaStdUnqualVal);
                pdQltStdValue.setRemark(remark);
                pdQltStdValue.setDelFlg(check);
                /**Modify: 添加公共属性 2016/09/20   BY  任强  Start*/
                pdQltStdValue.setCrtId(param.getCrtId());
                /**Modify: 添加公共属性 2016/09/20   BY  任强  End**/
                pdQltStdValue.setCrtTime(new Date());
                int num = super.save(pdQltStdValue);
                if (num > 0) {
                    PdStandard stand = new PdStandard();
                    stand.setStandardId(Long.valueOf(param.getStandardId()));
                    stand.setQltFlg("1");
                    /**Modify: 添加公共属性 2016/09/20   BY  任强  Start*/
                    stand.setUpdId(param.getUpdId());
                    stand.setUpdTime(param.getUpdTime());
                    /**Modify: 添加公共属性 2016/09/20   BY  任强  End**/
                    super.modify(SqlId.SQL_ID_UPDATE_STANDARD_ID, stand);
                }
            }
        }
    }
    
    /**
     * 根据产品标准ID获得产品包装标准
     * @return 包装标准数据
     */
    @Transactional(readOnly = true)
    public List<QltStdCla> findListByStandardId() {
        List<QltStdCla> list=new ArrayList<QltStdCla>();
        QltStdCla qltStdCla=new QltStdCla();
        qltStdCla.setQltStdClaId("c01");
        qltStdCla.setQltStdClaName("一类质量标准指标");
        QltStdCla qltStdCla1=new QltStdCla();
        qltStdCla1.setQltStdClaId("c02");
        qltStdCla1.setQltStdClaName("二类质量标准指标");
        QltStdCla qltStdCla2=new QltStdCla();
        qltStdCla2.setQltStdClaId("c03");
        qltStdCla2.setQltStdClaName("三类质量标准指标");
        QltStdSubCla qltStdSubCla=new QltStdSubCla();
        qltStdSubCla.setQltStdSubId("s01");
        qltStdSubCla.setQltStdSubName("微生物指标");
        qltStdSubCla.setQltStdClaId("c01");
        QltStdSubCla qltStdSubCla1=new QltStdSubCla();
        qltStdSubCla1.setQltStdSubId("s02");
        qltStdSubCla1.setQltStdSubName("基础理化指标");
        qltStdSubCla1.setQltStdClaId("c01");
        QltStdSubCla qltStdSubCla2=new QltStdSubCla();
        qltStdSubCla2.setQltStdSubId("s03");
        qltStdSubCla2.setQltStdSubName("农药残留指标");
        qltStdSubCla2.setQltStdClaId("c02");
        QltStdSubCla qltStdSubCla3=new QltStdSubCla();
        qltStdSubCla3.setQltStdSubId("s04");
        qltStdSubCla3.setQltStdSubName("兽药残留指标");
        qltStdSubCla3.setQltStdClaId("c02");
        QltStdSubCla qltStdSubCla4=new QltStdSubCla();
        qltStdSubCla4.setQltStdSubId("s05");
        qltStdSubCla4.setQltStdSubName("重金属指标");
        qltStdSubCla4.setQltStdClaId("c03");
        QltStdSubCla qltStdSubCla5=new QltStdSubCla();
        qltStdSubCla5.setQltStdSubId("s06");
        qltStdSubCla5.setQltStdSubName("添加剂指标");
        qltStdSubCla5.setQltStdClaId("c03");
        List<QltStdSubCla> qualitySubStandardList =new ArrayList<QltStdSubCla>();
        qualitySubStandardList.add(qltStdSubCla);
        qualitySubStandardList.add(qltStdSubCla1);
        List<QltStdSubCla> qualitySubStandardList1 =new ArrayList<QltStdSubCla>();
        qualitySubStandardList1.add(qltStdSubCla2);
        qualitySubStandardList1.add(qltStdSubCla3);
        List<QltStdSubCla> qualitySubStandardList2 =new ArrayList<QltStdSubCla>();
        qualitySubStandardList2.add(qltStdSubCla4);
        qualitySubStandardList2.add(qltStdSubCla5);
        qltStdCla.setQltStdSubClaList(qualitySubStandardList);
        qltStdCla1.setQltStdSubClaList(qualitySubStandardList1);
        qltStdCla2.setQltStdSubClaList(qualitySubStandardList2);
        List<QltStdItemAndQltstd> qltStdItemAndQltstd =new ArrayList<QltStdItemAndQltstd>();
        QltStdItemAndQltstd qltStdItemAndQltstds=new QltStdItemAndQltstd();
        qltStdItemAndQltstds.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds.setQltStdItemId("i01");
        qltStdItemAndQltstds.setQltStdClaId("c01");
        qltStdItemAndQltstds.setQltStdSubId("s01");
        qltStdItemAndQltstds.setQltStdItemName("菌落总数");
        qltStdItemAndQltstds.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds1=new QltStdItemAndQltstd();
        qltStdItemAndQltstds1.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds1.setQltStdItemId("i02");
        qltStdItemAndQltstds1.setQltStdClaId("c01");
        qltStdItemAndQltstds1.setQltStdSubId("s01");
        qltStdItemAndQltstds1.setQltStdItemName("大肠菌群");
        qltStdItemAndQltstds1.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds1.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds1.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds2=new QltStdItemAndQltstd();
        qltStdItemAndQltstds2.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds2.setQltStdItemId("i03");
        qltStdItemAndQltstds2.setQltStdClaId("c01");
        qltStdItemAndQltstds2.setQltStdSubId("s01");
        qltStdItemAndQltstds2.setQltStdItemName("沙门氏菌");
        qltStdItemAndQltstds2.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds2.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds2.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds3=new QltStdItemAndQltstd();
        qltStdItemAndQltstds3.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds3.setQltStdItemId("i04");
        qltStdItemAndQltstds3.setQltStdClaId("c01");
        qltStdItemAndQltstds3.setQltStdSubId("s01");
        qltStdItemAndQltstds3.setQltStdItemName("出血性大肠埃希氏菌");
        qltStdItemAndQltstds3.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds3.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds3.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds4=new QltStdItemAndQltstd();
        qltStdItemAndQltstds4.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds4.setQltStdItemId("i05");
        qltStdItemAndQltstds4.setQltStdClaId("c01");
        qltStdItemAndQltstds4.setQltStdSubId("s02");
        qltStdItemAndQltstds4.setQltStdItemName("挥发性盐基氮");
        qltStdItemAndQltstds4.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds4.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds4.setQltStdUnqualVal("60*20");
        qltStdItemAndQltstd.add(qltStdItemAndQltstds4);
        qltStdItemAndQltstd.add(qltStdItemAndQltstds3);
        qltStdItemAndQltstd.add(qltStdItemAndQltstds2);
        qltStdItemAndQltstd.add(qltStdItemAndQltstds1);
        qltStdItemAndQltstd.add(qltStdItemAndQltstds);
        qltStdSubCla.setQltStdItemAndQltstdList(qltStdItemAndQltstd);
        List<QltStdItemAndQltstd> qltStdItemAndQltstd1 =new ArrayList<QltStdItemAndQltstd>();
        QltStdItemAndQltstd qltStdItemAndQltstds11=new QltStdItemAndQltstd();
        qltStdItemAndQltstds11.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds11.setQltStdItemId("i06");
        qltStdItemAndQltstds11.setQltStdClaId("c02");
        qltStdItemAndQltstds11.setQltStdSubId("s03");
        qltStdItemAndQltstds11.setQltStdItemName("六六六");
        qltStdItemAndQltstds11.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds11.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds11.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds12=new QltStdItemAndQltstd();
        qltStdItemAndQltstds12.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds12.setQltStdItemId("i07");
        qltStdItemAndQltstds12.setQltStdClaId("c02");
        qltStdItemAndQltstds12.setQltStdSubId("s03");
        qltStdItemAndQltstds12.setQltStdItemName("滴滴涕");
        qltStdItemAndQltstds12.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds12.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds12.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds13=new QltStdItemAndQltstd();
        qltStdItemAndQltstds13.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds13.setQltStdItemId("i08");
        qltStdItemAndQltstds13.setQltStdClaId("c02");
        qltStdItemAndQltstds13.setQltStdSubId("s03");
        qltStdItemAndQltstds13.setQltStdItemName("敌敌畏");
        qltStdItemAndQltstds13.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds13.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds13.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds14=new QltStdItemAndQltstd();
        qltStdItemAndQltstds14.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds14.setQltStdItemId("i09");
        qltStdItemAndQltstds14.setQltStdClaId("c02");
        qltStdItemAndQltstds14.setQltStdSubId("s04");
        qltStdItemAndQltstds14.setQltStdItemName("四环素");
        qltStdItemAndQltstds14.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds14.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds14.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds15=new QltStdItemAndQltstd();
        qltStdItemAndQltstds15.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds15.setQltStdItemId("i010");
        qltStdItemAndQltstds15.setQltStdClaId("c02");
        qltStdItemAndQltstds15.setQltStdSubId("s04");
        qltStdItemAndQltstds15.setQltStdItemName("土霉素");
        qltStdItemAndQltstds15.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds15.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds15.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds16=new QltStdItemAndQltstd();
        qltStdItemAndQltstds16.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds16.setQltStdItemId("i011");
        qltStdItemAndQltstds16.setQltStdClaId("c02");
        qltStdItemAndQltstds16.setQltStdSubId("s04");
        qltStdItemAndQltstds16.setQltStdItemName("金霉素");
        qltStdItemAndQltstds16.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds16.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds16.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds17=new QltStdItemAndQltstd();
        qltStdItemAndQltstds17.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds17.setQltStdItemId("i012");
        qltStdItemAndQltstds17.setQltStdClaId("c02");
        qltStdItemAndQltstds17.setQltStdSubId("s04");
        qltStdItemAndQltstds17.setQltStdItemName("乙烯雌酚");
        qltStdItemAndQltstds17.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds17.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds17.setQltStdUnqualVal("60*20");
        qltStdItemAndQltstd1.add(qltStdItemAndQltstds11);
        qltStdItemAndQltstd1.add(qltStdItemAndQltstds12);
        qltStdItemAndQltstd1.add(qltStdItemAndQltstds13);
        qltStdItemAndQltstd1.add(qltStdItemAndQltstds14);
        qltStdItemAndQltstd1.add(qltStdItemAndQltstds15);
        qltStdItemAndQltstd1.add(qltStdItemAndQltstds16);
        qltStdItemAndQltstd1.add(qltStdItemAndQltstds17);
        qltStdSubCla1.setQltStdItemAndQltstdList(qltStdItemAndQltstd1);
        List<QltStdItemAndQltstd> qltStdItemAndQltstd2 =new ArrayList<QltStdItemAndQltstd>();
        QltStdItemAndQltstd qltStdItemAndQltstds171=new QltStdItemAndQltstd();
        qltStdItemAndQltstds171.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds171.setQltStdItemId("i012");
        qltStdItemAndQltstds171.setQltStdClaId("c02");
        qltStdItemAndQltstds171.setQltStdSubId("s04");
        qltStdItemAndQltstds171.setQltStdItemName("乙烯雌酚");
        qltStdItemAndQltstds171.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds171.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds171.setQltStdUnqualVal("60*20");
        qltStdSubCla2.setQltStdItemAndQltstdList(qltStdItemAndQltstd2);
        QltStdItemAndQltstd qltStdItemAndQltstds172=new QltStdItemAndQltstd();
        qltStdItemAndQltstds172.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds172.setQltStdItemId("i013");
        qltStdItemAndQltstds172.setQltStdClaId("c03");
        qltStdItemAndQltstds172.setQltStdSubId("s05");
        qltStdItemAndQltstds172.setQltStdItemName("铅");
        qltStdItemAndQltstds172.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds172.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds172.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds173=new QltStdItemAndQltstd();
        qltStdItemAndQltstds173.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds173.setQltStdItemId("i014");
        qltStdItemAndQltstds173.setQltStdClaId("c03");
        qltStdItemAndQltstds173.setQltStdSubId("s05");
        qltStdItemAndQltstds173.setQltStdItemName("砷");
        qltStdItemAndQltstds173.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds173.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds173.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds174=new QltStdItemAndQltstd();
        qltStdItemAndQltstds174.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds174.setQltStdItemId("i015");
        qltStdItemAndQltstds174.setQltStdClaId("c03");
        qltStdItemAndQltstds174.setQltStdSubId("s05");
        qltStdItemAndQltstds174.setQltStdItemName("汞");
        qltStdItemAndQltstds174.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds174.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds174.setQltStdUnqualVal("60*20");
        QltStdItemAndQltstd qltStdItemAndQltstds175=new QltStdItemAndQltstd();
        qltStdItemAndQltstds175.setStandardId(NumberConst.IntDef.INT_ONE);
        qltStdItemAndQltstds175.setQltStdItemId("i016");
        qltStdItemAndQltstds175.setQltStdClaId("c03");
        qltStdItemAndQltstds175.setQltStdSubId("s06");
        qltStdItemAndQltstds175.setQltStdItemName("/");
        qltStdItemAndQltstds175.setQltStdExcVal("100g*20");
        qltStdItemAndQltstds175.setQltStdSuitVal("80g*20");
        qltStdItemAndQltstds175.setQltStdUnqualVal("60*20");
        qltStdItemAndQltstd2.add(qltStdItemAndQltstds175);
        qltStdItemAndQltstd2.add(qltStdItemAndQltstds174);
        qltStdItemAndQltstd2.add(qltStdItemAndQltstds173);
        qltStdItemAndQltstd2.add(qltStdItemAndQltstds172);
        qltStdItemAndQltstd2.add(qltStdItemAndQltstds171);
        qltStdSubCla2.setQltStdItemAndQltstdList(qltStdItemAndQltstd2);
        list.add(qltStdCla);
        list.add(qltStdCla1);
        list.add(qltStdCla2);
        return list;
    }
    
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
