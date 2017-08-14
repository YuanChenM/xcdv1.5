package com.msk.product.logic;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CodeMasterConst;
import com.msk.core.entity.PdStandard;
import com.msk.core.entity.PdTncStd;
import com.msk.order.consts.CommOrderConst;
import com.msk.product.bean.PD141107Bean;
import com.msk.product.bean.PD141107Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 技术标准Logic
 *
 * @author yuan_chen
 */

@Service
public class PD141107Logic extends BaseLogic {

    @Autowired
    private PD141113Logic pd141113Logic;

    /**
     * SQL Map 中SQL ID定义
     *
     * @author yuan_chen
     */
    interface SqlId {
        String SQLID_INSERT_TECHNICAL_STANDARD = "insertTncStd";
        String SQLID_UPDATE_TECHNICAL_STANDARD = "updateTncStd";
        String SQLID_UPDATE_TECHNICAL_STANDARD_FLG = "updateTncStdFlg";
        String SQLID_GET_STD_INFO = "getTncStdInfo";
        String SQLID_COUNT_PD_TNC_STD = "countPdTncStd";
        String SQLID_UPDATE_TNC_FLG = "updateTncFlg";
    }

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(PD141107Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /**
     * 取得技术标准详细页面数据
     *
     * @param param param
     * @return 技术标准数据
     * @author gyh
     */
    @Transactional(readOnly = true)
    public List<PD141107Bean> getTncStd(BasePageParam param) {
        logger.debug("根据标准ID取得技术标准数据");
        // 取得所有分类
        param.setFilter("levelId", CommOrderConst.OrgLevel.ONE);
        param.setFilter("parentId", CommOrderConst.IsAvailable.AVAILABLE);
        List<PD141107Bean> pd141107Beans = super.findList(SqlId.SQLID_GET_STD_INFO, param);
        for (PD141107Bean pd141107Bean : pd141107Beans) {
            if (CommOrderConst.IsAvailable.AVAILABLE.equals(pd141107Bean.getIsCatalog())) {
                param.setFilter("levelId", CommOrderConst.OrgLevel.TOW);
                param.setFilter("parentId", pd141107Bean.getTncStdItemId());
                List<PD141107Bean> pd141107BeanChilds = super.findList(SqlId.SQLID_GET_STD_INFO, param);
                pd141107Bean.setPdTncStdList(pd141107BeanChilds);
            }
        }

        return pd141107Beans;
    }

    /**
     * 取得技术标准详细页面数据（假数据）
     *
     * @return 技术标准数据
     * @author gyh
     */
    public List<PD141107Bean> getTncStdTestData() {
        List<PD141107Bean> tsClaList1 = new ArrayList<PD141107Bean>();
        List<PD141107Bean> pd141107BeanChilds = new ArrayList<PD141107Bean>();
        PD141107Bean bean1 = new PD141107Bean();
        bean1.setTncStdItemId("i01");
        bean1.setTncStdItemName("产品解冻失水率指标");
        bean1.setLevelId("1");
        bean1.setParentId("0");
        bean1.setIsCatalog("1");
        bean1.setTncStdVal1("≤6.5%");
        bean1.setTncStdVal1("≤8%");
        bean1.setTncStdVal1("≤9.5%");
        tsClaList1.add(bean1);

        PD141107Bean bean2 = new PD141107Bean();
        bean2.setTncStdItemId("i02");
        bean2.setTncStdItemName("产品均匀度指标");
        bean2.setLevelId("1");
        bean2.setParentId("0");
        bean2.setIsCatalog("1");
        bean2.setTncStdVal1("160+10g");
        bean2.setTncStdVal1("160+10g");
        bean2.setTncStdVal1("160+5g");
        tsClaList1.add(bean2);

        PD141107Bean bean3 = new PD141107Bean();
        bean3.setTncStdItemId("i03");
        bean3.setTncStdItemName("产品感官指标");
        bean3.setLevelId("1");
        bean3.setParentId("0");
        bean3.setIsCatalog("1");

        PD141107Bean bean7 = new PD141107Bean();
        bean7.setTncStdItemId("i06");
        bean7.setTncStdItemName("色泽");
        bean7.setLevelId("1");
        bean7.setParentId("0");
        bean7.setIsCatalog("1");
        bean7.setTncStdVal1("粉红");
        bean7.setTncStdVal1("粉红");
        bean7.setTncStdVal1("暗黄");
        pd141107BeanChilds.add(bean7);

        PD141107Bean bean8 = new PD141107Bean();
        bean8.setTncStdItemId("i07");
        bean8.setTncStdItemName("淤血");
        bean8.setLevelId("1");
        bean8.setParentId("0");
        bean8.setIsCatalog("1");
        bean8.setTncStdVal1("无");
        bean8.setTncStdVal1("无");
        bean8.setTncStdVal1("≤0.5cm²");
        pd141107BeanChilds.add(bean8);
        bean3.setPdTncStdList(pd141107BeanChilds);
        tsClaList1.add(bean3);

        PD141107Bean bean4 = new PD141107Bean();
        bean4.setTncStdItemId("i04");
        bean4.setTncStdItemName("产品加工配方");
        bean4.setLevelId("1");
        bean4.setParentId("0");
        bean4.setIsCatalog("1");
        bean8.setTncStdVal1("/");
        bean8.setTncStdVal1("/");
        bean8.setTncStdVal1("/");
        tsClaList1.add(bean4);

        PD141107Bean bean5 = new PD141107Bean();
        bean5.setTncStdItemId("i05");
        bean5.setTncStdItemName("产品工艺流程");
        bean5.setLevelId("1");
        bean5.setParentId("0");
        bean5.setIsCatalog("1");
        bean8.setTncStdVal1("精修");
        bean8.setTncStdVal1("精修");
        bean8.setTncStdVal1("精修");
        tsClaList1.add(bean5);

        return tsClaList1;
    }

    /**
     * 更新技术标准值
     *
     * @param param param
     * @author gyh
     */
    @Transactional
    public void modifyTncStdData(PD141107Param param) {
        logger.debug("更新技术标准值");
        String[] pdTncTdItemIdArray = param.getPdTncTdItemIdArray();
        String[] content1Array = param.getContent1Array();
        String[] content2Array = param.getContent2Array();
        String[] content3Array = param.getContent3Array();
        String[] checkArray = param.getCheckArray();

        param.setFilter("standardId", StringUtil.toString(param.getPdStdId()));
        // 根据产品标准id查询记录
        PdStandard pdStandard = pd141113Logic.getPdStandard(param);

        BaseParam baseParam = new BaseParam();
        baseParam.setFilter("standardId", StringUtil.toString(param.getPdStdId()));
        // 根据产品标准id查询是否存在技术标准记录，存在则修改，不存在则删除
        int pdtncTdItemLen = pdTncTdItemIdArray.length;
        if (CommOrderConst.IsAvailable.NOAVAILABLE.equals(pdStandard.getTncFlg())) {
            for (int j = 0; j < pdtncTdItemLen; j++) {
                baseParam.setFilter("tncStdItemId", pdTncTdItemIdArray[j]);
                int result = super.getCount(SqlId.SQLID_COUNT_PD_TNC_STD, baseParam);
                PdTncStd pdTncStd = new PdTncStd();
                pdTncStd.setStandardId(Long.valueOf(param.getPdStdId()));
                pdTncStd.setTncStdItemId(pdTncTdItemIdArray[j]);
                pdTncStd.setTncStdVal1(content1Array[j]);
                pdTncStd.setTncStdVal2(content2Array[j]);
                pdTncStd.setTncStdVal3(content3Array[j]);
                /**Add: 横展开添加共通设置 2016/09/21   BY  任强  Start */
                pdStandard.setUpdId(param.getUpdId());
                pdTncStd.setCrtId(param.getCrtId());
                pdTncStd.setUpdId(param.getUpdId());
                pdTncStd.setActId(param.getActId());
                pdTncStd.setActTime(DateTimeUtil.getCustomerDate());
                /**Add: 横展开添加共通设置 2016/09/21   BY  任强  End */
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                pdTncStd.setCrtTime(new Date());
                pdTncStd.setUpdTime(new Date());
                //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
                // 根据判断产品标准ID和标准项目ID判断是否存在技术标准记录，存在则修改，不存在则新增
                if (result > NumberConst.IntDef.INT_ZERO) {
                    if (CodeMasterConst.IsAvailable.AVAILABLE.equals(checkArray[j])) {
                        pdTncStd.setDelFlg(CodeMasterConst.IsAvailable.NOAVAILABLE);
                        super.modify(SqlId.SQLID_UPDATE_TECHNICAL_STANDARD_FLG, pdTncStd);
                    } else {
                        pdTncStd.setDelFlg(CodeMasterConst.IsAvailable.AVAILABLE);
                        super.modify(SqlId.SQLID_UPDATE_TECHNICAL_STANDARD, pdTncStd);
                    }
                } else {
                    if (CodeMasterConst.IsAvailable.NOAVAILABLE.equals(checkArray[j])) {
                        super.save(SqlId.SQLID_INSERT_TECHNICAL_STANDARD, pdTncStd);
                    }
                }
            }
        } else {
            for (int j = 0; j < pdtncTdItemLen; j++) {
                PdTncStd pdTncStd = new PdTncStd();
                pdTncStd.setStandardId(Long.valueOf(param.getPdStdId()));
                pdTncStd.setTncStdItemId(pdTncTdItemIdArray[j]);
                pdTncStd.setTncStdVal1(content1Array[j]);
                pdTncStd.setTncStdVal2(content2Array[j]);
                pdTncStd.setTncStdVal3(content3Array[j]);
                if (CodeMasterConst.IsAvailable.NOAVAILABLE.equals(checkArray[j])) {
                    //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                    pdTncStd.setCrtTime(new Date());
                    //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
                    super.save(SqlId.SQLID_INSERT_TECHNICAL_STANDARD, pdTncStd);
                }
            }
        }
        pdStandard.setTncFlg(CodeMasterConst.IsAvailable.NOAVAILABLE);
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        pdStandard.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        super.modify(SqlId.SQLID_UPDATE_TNC_FLG, pdStandard);
    }
}
