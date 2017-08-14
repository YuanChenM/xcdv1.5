package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.common.consts.CodeMasterConst;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.PdNormsStd;
import com.msk.product.bean.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 卖家产品等级操作
 * xhy
 */
@Service
public class PD141404Logic extends BaseLogic {


    @Autowired
    private CommonLogic commonLogic;

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }


    /**
     * SQL Map 中SQL ID定义
     *
     * @author xhy
     */
    interface SqlId {


        static final String SQL_ID_FIND_MAXID = "findMaxIdGrade";//获取等级最大批次数
        //查询数据
        static final String SQL_ID_FIND_LIST_PD_ORG = "findListOrgs";
        static final String SQL_ID_FIND_LIST_PD_FED = "findListFeds";
        static final String SQL_ID_FIND_LIST_PD_MCT = "findListMcts";
        static final String SQL_ID_FIND_LIST_PD_TNC = "findListTncs";
        static final String SQL_ID_FIND_List_TNC_MARKEY = "findListTncMarkey"; //获取市场需求标准列表
        static final String SQL_ID_FIND_List_TNC_PROVIDER = "findListTncProvider"; //获取供应商习惯标准列表
        static final String SQL_ID_FIND_List_TNC_MARKEY_NO = "findListTncMarkeyNo"; //获取市场需求禁止日列表
        static final String SQL_ID_FIND_List_TNC_PROVIDER_NO = "findListTncProviderNo"; //获取供应商习惯标准列表
        static final String SQL_ID_FIND_LIST_PD_GNQ = "findListGnqs";
        static final String SQL_ID_FIND_LIST_PD_GNQ_TWO = "findListGnqChilds"; //二级
        static final String SQL_ID_FIND_LIST_PD_SFT = "findListSfts";
        static final String SQL_ID_FIND_LIST_PD_SFT_TWO = "findListSftChilds"; //二级
        static final String SQL_ID_FIND_LIST_PD_TSP = "findListTsp";
        static final String SQL_ID_FIND_LIST_PD_TSP_TWO = "findListTspChilds"; //二级
        static final String SQL_ID_COUNT = "countSize";
        static final String SQL_ID_FIND_LIST_PD_NORMS = "findListNorms";

        //修改数据
        static final String SQL_ID_MODIFY_GRADE = "modifyGrade";//保存产品等级数据
        static final String SQL_ID_MODIFY_LIST_PD_ORG = "modifyOrgs";//修改产品种源标准
        static final String SQL_ID_MODIFY_LIST_PD_FED = "modifyFeds";//修改产品饲养标准
        static final String SQL_ID_MODIFY_LIST_PD_MCT = "modifyMcts";//修改产品加工技术标准
        static final String SQL_ID_MODIFY_LIST_PD_TNC = "modifyTncs";//修改产品加工质量标准
        static final String SQL_ID_MODIFY_LIST_PD_GNQ = "modifyGnqs";//修改产品通用质量标准
        static final String SQL_ID_MODIFY_LIST_PD_SFT = "modifySfts";//修改安全标准
        static final String SQL_ID_MODIFY_LIST_PD_TSP = "modifyTsps";//修改产品运输标准
        static final String SQL_ID_MODIFY_LIST_PD_NORMS = "modifyNorms";//修改产品运输标准

        //保存数据
        static final String SQL_ID_SAVE_GRADE = "saveGrade";//保存产品等级数据
        static final String SQL_ID_SAVE_LIST_PD_ORG = "saveOrgs";//保存产品种源标准
        static final String SQL_ID_SAVE_LIST_PD_FED = "saveFeds";//保存产品饲养标准
        static final String SQL_ID_SAVE_LIST_PD_MCT = "saveMcts";//保存产品加工技术标准
        static final String SQL_ID_SAVE_LIST_PD_TNC = "saveTncs";//保存产品加工质量标准
        static final String SQL_ID_SAVE_LIST_PD_GNQ = "saveGnqs";//保存产品通用质量标准
        static final String SQL_ID_SAVE_LIST_PD_SFT = "saveSfts";//保存安全标准
        static final String SQL_ID_SAVE_LIST_PD_TSP = "saveTsps";//保存产品运输标准
        static final String SQL_ID_SAVE_LIST_PD_NORMS = "saveNorms"; //保存产品数据


        static final String SQL_ID_FIND_NORMS_STANDARDID = "findnormsStandardId"; //保存产品数据
    }

    /**
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public int getSize(BaseParam param) {
        return this.getCount(SqlId.SQL_ID_COUNT, param);
    }

    /**
     * 查询产品中源保准数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141404OrgBean> findListOrg(BaseParam param) {
        return super.findList(SqlId.SQL_ID_FIND_LIST_PD_ORG, param);

    }

    /**
     * 查询产品饲养标准数据
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141404FedBean> findListFed(BaseParam param) {
        return super.findList(SqlId.SQL_ID_FIND_LIST_PD_FED, param);
    }

    /**
     * 查询产品加工技术
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141404MctBean> findListMct(BaseParam param) {
        return super.findList(SqlId.SQL_ID_FIND_LIST_PD_MCT, param);
    }

    /**
     * 查询产品通用质量标准
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141404TncBean> findInitListTnc(BaseParam param) {
        param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));
        param.setFilter("parentId", CodeMasterConst.IsAvailable.AVAILABLE);

   /*     查询一级类目*/
        List<PD141404TncBean> list = super.findList(SqlId.SQL_ID_FIND_LIST_PD_TNC, param);//查询商品个数
        for (PD141404TncBean pd141149Bean : list) {
            //获取加工质量标准值
            if (CodeMasterConst.IsAvailable.AVAILABLE.equals(pd141149Bean.getIsCatalog())) {
                param.setFilter("levelId", CodeMasterConst.OrgLevel.TOW);
                param.setFilter("parentId", pd141149Bean.getTncStdItemId());
                List<PD141404TncBean> pd141149BeanChilds = findList(SqlId.SQL_ID_FIND_LIST_PD_TNC, param);
                pd141149Bean.setPdTncStdList(pd141149BeanChilds);
            }
            //获取市场需求标准列表
            param.setFilter("tncStdItemId", pd141149Bean.getTncStdItemId());
            param.setFilter("discussStatus", String.valueOf(NumberConst.IntDef.INT_TWO));
            List<PDtncMarkeyBean> tncMarList = super.findList(SqlId.SQL_ID_FIND_List_TNC_MARKEY, param);
            if (tncMarList != null && tncMarList.size() > NumberConst.IntDef.INT_ZERO) {
                for (PDtncMarkeyBean marBean : tncMarList) {
                    marBean.setFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, marBean.getFixDate()));
                    marBean.setRaiseDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, marBean.getRaiseDate()));
                }
                pd141149Bean.setPdTncMarkeyList(tncMarList);

            }

            //获取供应商习惯性标准
            List<PDtncProviderBean> tncProList = super.findList(SqlId.SQL_ID_FIND_List_TNC_PROVIDER, param);
            if (tncProList != null && tncMarList.size() > NumberConst.IntDef.INT_ZERO) {
                for (PDtncProviderBean proBean : tncProList) {
                    proBean.setProFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, proBean.getFixDate()));
                    proBean.setProRaiseDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, proBean.getRaiseDate()));
                }
                pd141149Bean.setPdTncProviderList(tncProList);
            }

            //获取市场需求禁止日
            List<PDtncMarkeyBean> tncMarListNo = super.findList(SqlId.SQL_ID_FIND_List_TNC_MARKEY_NO, param);
            if (tncMarListNo != null && tncMarListNo.size() > NumberConst.IntDef.INT_ZERO) {
                for (PDtncMarkeyBean marBean : tncMarListNo) {
                    marBean.setFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, marBean.getFixDate()));
                }
                pd141149Bean.setPdTncMarkeyNoList(tncMarListNo);
            }

            //获取供应商禁止日
            List<PDtncProviderBean> tncProListNo = super.findList(SqlId.SQL_ID_FIND_List_TNC_PROVIDER_NO, param);
            if (tncProListNo != null && tncProListNo.size() > NumberConst.IntDef.INT_ZERO) {
                for (PDtncProviderBean proBean : tncProListNo) {
                    proBean.setProFixDateShow(DateTimeUtil.formatDate(DateTimeUtil.FORMAT_DATE_YYYYMMDD, proBean.getFixDate()));
                }
                pd141149Bean.setPdTncProviderNoList(tncProListNo);
            }
        }
        return list;
    }

    /**
     * 查询产品加工技术
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141404GnqBean> findInitListGnq(BaseParam param) {
        param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));
        param.setFilter("parentId", CodeMasterConst.IsAvailable.AVAILABLE);
        List<PD141404GnqBean> listParent = super.findList(SqlId.SQL_ID_FIND_LIST_PD_GNQ, param);
        if (listParent.size() > NumberConst.IntDef.INT_ZERO) {
            for (PD141404GnqBean listGnqparent : listParent) {
                param.setFilter("parentId", listGnqparent.getGnqStdItemId());
                param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_TWO));
                List<PD141404GnqChildBean> listChilds = super.findList(SqlId.SQL_ID_FIND_LIST_PD_GNQ_TWO, param);
                listGnqparent.setPdGnqChildList(listChilds);
            }

        }
        return listParent;
    }

    /**
     * 查询产品安全标准
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141404SftBean> findInitListSft(BaseParam param) {
        param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));
        param.setFilter("parentId", CodeMasterConst.IsAvailable.AVAILABLE);
        List<PD141404SftBean> listParent = super.findList(SqlId.SQL_ID_FIND_LIST_PD_SFT, param);
        if (listParent.size() > NumberConst.IntDef.INT_ZERO) {
            for (PD141404SftBean listSftparent : listParent) {
                param.setFilter("parentId", listSftparent.getSftStdItemId());
                param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_TWO));
                List<PD141404SftChildBean> listChilds = super.findList(SqlId.SQL_ID_FIND_LIST_PD_SFT_TWO, param);
                listSftparent.setPdSftChildList(listChilds);
            }

        }
        return listParent;
    }

    /**
     * 查询产品运输标准
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141404TspBean> findInitListTsp(BaseParam param) {
        param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_ONE));
        param.setFilter("parentId", CodeMasterConst.IsAvailable.AVAILABLE);
        List<PD141404TspBean> listParent = super.findList(SqlId.SQL_ID_FIND_LIST_PD_TSP, param);
        if (listParent.size() > NumberConst.IntDef.INT_ZERO) {
            for (PD141404TspBean listTspParent : listParent) {
                param.setFilter("parentId", listTspParent.getTspStdItemId());
                param.setFilter("levelId", String.valueOf(NumberConst.IntDef.INT_TWO));
                List<PD141404TspChildBean> listChilds = super.findList(SqlId.SQL_ID_FIND_LIST_PD_TSP_TWO, param);
                listTspParent.setPdTspChildList(listChilds);
            }

        }
        return listParent;
    }

    /**
     * 查询产品保准标准数据显示
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<PD141404NormsChildBean> findInitListNorms(BaseParam param) {
        return this.findList(SqlId.SQL_ID_FIND_LIST_PD_NORMS, param);
    }

    /**
     * 数据修改数据
     *
     * @param beans
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void updateOnline(PD141404Bean beans, BaseParam param) {
        //修改产品等级数据
        param.setFilter("resultInfo", beans.getResultInfo());
        param.setFilter("resultGrade", beans.getResultGrade());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        param.setUpdTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        int modifyOk = super.modify(SqlId.SQL_ID_MODIFY_GRADE, param);
        if (modifyOk > NumberConst.IntDef.INT_ZERO) {
            //修改原种种源等级状态在线管控表数据
            if (beans.getShowTable().equals(String.valueOf(NumberConst.IntDef.INT_ONE))) {
                if (beans.getOrgStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getOrgStdItemId().length; i++) {
                        param.setFilter("orgStdItemId", beans.getOrgStdItemId()[i]);
                        param.setFilter("orgResultFlg", beans.getOrgCheckBox()[i]);
                        param.setFilter("orgResultInfo", beans.getOrgResultInfo()[i]);
                        super.modify(SqlId.SQL_ID_MODIFY_LIST_PD_ORG, param);
                    }
                }
                //修改饲养在线管控表数据
                if (beans.getFedStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getFedStdItemId().length; i++) {
                        param.setFilter("fedStdItemId", beans.getFedStdItemId()[i]);
                        param.setFilter("fedResultFlg", beans.getFedCheckBox()[i]);
                        param.setFilter("fedResultInfo", beans.getFedResultInfo()[i]);
                        super.modify(SqlId.SQL_ID_MODIFY_LIST_PD_FED, param);
                    }
                }
            }
            //修改加工技术数据
            if (beans.getMctStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getMctStdItemId().length; i++) {
                    param.setFilter("mctStdItemId", beans.getMctStdItemId()[i]);
                    param.setFilter("mctResultFlg", beans.getMctCheckBox()[i]);
                    param.setFilter("mctResultInfo", beans.getMctResultInfo()[i]);
                    super.modify(SqlId.SQL_ID_MODIFY_LIST_PD_MCT, param);
                }
            }
            //修改加工质量数据
            if (beans.getTncStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getTncStdItemId().length; i++) {
                    param.setFilter("tncStdItemId", beans.getTncStdItemId()[i]);
                    param.setFilter("tncResultFlg", beans.getTncCheckBox()[i]);
                    param.setFilter("tncResultInfo", beans.getTncResultInfo()[i]);
                    super.modify(SqlId.SQL_ID_MODIFY_LIST_PD_TNC, param);
                }
            }
            //修改通用质量数据
            if (beans.getGnqStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getGnqStdItemId().length; i++) {
                    param.setFilter("gnqStdItemId", beans.getGnqStdItemId()[i]);
                    param.setFilter("gnqResultFlg", beans.getGnqCheckBox()[i]);
                    param.setFilter("gnqResultInfo", beans.getGnqResultInfo()[i]);
                    super.modify(SqlId.SQL_ID_MODIFY_LIST_PD_GNQ, param);
                }
            }
            //修改产品安全数据
            if (beans.getSftStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getSftStdItemId().length; i++) {
                    param.setFilter("sftStdItemId", beans.getSftStdItemId()[i]);
                    param.setFilter("sftResultFlg", beans.getSftCheckBox()[i]);
                    param.setFilter("sftResultInfo", beans.getSftResultInfo()[i]);
                    super.modify(SqlId.SQL_ID_MODIFY_LIST_PD_SFT, param);
                }
            }
            //修改产品运输数据
            if (beans.getTspStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getTspStdItemId().length; i++) {
                    param.setFilter("tspStdItemId", beans.getTspStdItemId()[i]);
                    param.setFilter("tspResultFlg", beans.getTspCheckBox()[i]);
                    param.setFilter("tspResultInfo", beans.getTspResultInfo()[i]);
                    super.modify(SqlId.SQL_ID_MODIFY_LIST_PD_TSP, param);
                }
            }
            //修改产品产品包装数据
            if (StringUtils.isNotBlank(beans.getNormsCode())) {
                param.setFilter("normsCode", beans.getNormsCode());
                param.setFilter("normsCodeOld", beans.getNormsCodeOld());
                param.setFilter("normsName", beans.getNormsName());
                param.setFilter("resultInnerweightFlg", beans.getResultInnerweightFlg());
                param.setFilter("resultInnererrorFlg", beans.getResultInnererrorFlg());
                param.setFilter("resultInnercountFlg", beans.getResultInnercountFlg());
                param.setFilter("resultInnersizeFlg", beans.getResultInnersizeFlg());
                param.setFilter("resultInnermatFlg", beans.getResultInnermatFlg());
                param.setFilter("resultOutspecFlg", beans.getResultOutspecFlg());
                param.setFilter("resultOutweightFlg", beans.getResultOutweightFlg());
                param.setFilter("resultOutsizeFlg", beans.getResultOutsizeFlg());
                param.setFilter("resultOutmatFlg", beans.getResultOutmatFlg());
                super.modify(SqlId.SQL_ID_MODIFY_LIST_PD_NORMS, param);
            }

        }
    }

    /**
     * 产品保存操作
     *
     * @param param
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void saveData(PD141404Bean beans, BaseParam param) {
        beans.setCrtId(param.getCrtId());
        beans.setUpdId(param.getUpdId());
        //保存产品等级表中数据
        beans.setOnlineId(commonLogic.maxId("PD_GRADE_SELLER_ONLINE", "ONLINE_ID").toString()); //获取最大id
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  Start
        beans.setUpdTime(new Date());
        beans.setActTime(new Date());
        beans.setCrtTime(new Date());
        //Add: 创建时间，修改时间，生效时间横展开修改sysdate 2016/09/06   BY  杨春艳  End
        int exist = super.save(SqlId.SQL_ID_SAVE_GRADE, beans);
        if (exist > NumberConst.IntDef.INT_ZERO) {
            //保存成功,保存子等级
            if (beans.getShowTable().equals(String.valueOf(NumberConst.IntDef.INT_ONE))) {
                //保存原种种源等级状态在线管控表数据
                param.setUpdTime(new Date());
                param.setActTime(new Date());
                param.setCrtTime(new Date());
                if (beans.getOrgStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getOrgStdItemId().length; i++) {
                        param.setFilter("onlineId", commonLogic.maxId("PD_ORG_SELLER_ONLINE", "ONLINE_ID").toString());
                        param.setFilter("orgStdItemId", beans.getOrgStdItemId()[i]);
                        param.setFilter("orgResultFlg", beans.getOrgCheckBox()[i]);
                        param.setFilter("orgResultInfo", beans.getOrgResultInfo()[i]);
                        param.setFilter("orgStdItemName", beans.getOrgStdItemName()[i]);
                        super.save(SqlId.SQL_ID_SAVE_LIST_PD_ORG, param);
                    }
                }
                //保存饲养在线管控表数据
                if (beans.getFedStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                    for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getFedStdItemId().length; i++) {
                        param.setFilter("onlineId", commonLogic.maxId("PD_FED_SELLER_ONLINE", "ONLINE_ID").toString());
                        param.setFilter("fedStdItemId", beans.getFedStdItemId()[i]);
                        param.setFilter("fedResultFlg", beans.getFedCheckBox()[i]);
                        param.setFilter("fedResultInfo", beans.getFedResultInfo()[i]);
                        param.setFilter("fedStdItemName", beans.getFedStdItemName()[i]);
                        super.save(SqlId.SQL_ID_SAVE_LIST_PD_FED, param);
                    }
                }
            }
            //保存加工技术数据
            if (beans.getMctStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getMctStdItemId().length; i++) {
                    param.setFilter("onlineId", commonLogic.maxId("PD_MCT_SELLER_ONLINE", "ONLINE_ID").toString());
                    param.setFilter("mctStdItemId", beans.getMctStdItemId()[i]);
                    param.setFilter("mctResultFlg", beans.getMctCheckBox()[i]);
                    param.setFilter("mctResultInfo", beans.getMctResultInfo()[i]);
                    param.setFilter("mctStdItemName", beans.getMctStdItemName()[i]);
                    super.save(SqlId.SQL_ID_SAVE_LIST_PD_MCT, param);
                }
            }
            //保存加工质量数据
            if (beans.getTncStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getTncStdItemId().length; i++) {
                    param.setFilter("onlineId", commonLogic.maxId("PD_TNC_SELLER_ONLINE", "ONLINE_ID").toString());
                    param.setFilter("tncStdItemId", beans.getTncStdItemId()[i]);
                    param.setFilter("tncResultFlg", beans.getTncCheckBox()[i]);
                    param.setFilter("tncResultInfo", beans.getTncResultInfo()[i]);
                    param.setFilter("tncStdItemName", beans.getTncStdItemName()[i]);
                    super.save(SqlId.SQL_ID_SAVE_LIST_PD_TNC, param);
                }
            }
            //保存通用质量数据
            if (beans.getGnqStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getGnqStdItemId().length; i++) {
                    param.setFilter("onlineId", commonLogic.maxId("PD_GNQ_SELLER_ONLINE", "ONLINE_ID").toString());
                    param.setFilter("gnqStdItemId", beans.getGnqStdItemId()[i]);
                    param.setFilter("gnqResultFlg", beans.getGnqCheckBox()[i]);
                    param.setFilter("gnqResultInfo", beans.getGnqResultInfo()[i]);
                    param.setFilter("gnqStdItemName", beans.getGnqStdItemName()[i]);
                    super.save(SqlId.SQL_ID_SAVE_LIST_PD_GNQ, param);
                }
            }
            //修改产品安全数据
            if (beans.getSftStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getSftStdItemId().length; i++) {
                    param.setFilter("onlineId", commonLogic.maxId("PD_SFT_SELLER_ONLINE", "ONLINE_ID").toString());
                    param.setFilter("sftStdItemId", beans.getSftStdItemId()[i]);
                    param.setFilter("sftResultFlg", beans.getSftCheckBox()[i]);
                    param.setFilter("sftResultInfo", beans.getSftResultInfo()[i]);
                    param.setFilter("sftStdItemName", beans.getSftStdItemName()[i]);
                    super.save(SqlId.SQL_ID_SAVE_LIST_PD_SFT, param);
                }
            }
            //修改产品运输数据
            if (beans.getTspStdItemId().length > NumberConst.IntDef.INT_ZERO) {
                for (int i = NumberConst.IntDef.INT_ZERO; i < beans.getTspStdItemId().length; i++) {
                    param.setFilter("onlineId", commonLogic.maxId("PD_TSP_SELLER_ONLINE", "ONLINE_ID").toString());
                    param.setFilter("tspStdItemId", beans.getTspStdItemId()[i]);
                    param.setFilter("tspResultFlg", beans.getTspCheckBox()[i]);
                    param.setFilter("tspResultInfo", beans.getTspResultInfo()[i]);
                    param.setFilter("tspStdItemName", beans.getTspStdItemName()[i]);
                    super.save(SqlId.SQL_ID_SAVE_LIST_PD_TSP, param);
                }
            }
            //保存产品包装数据
            if (StringUtils.isNotBlank(beans.getNormsCode())) {
                param.setFilter("onlineId", commonLogic.maxId("PD_NORMS_SELLER_ONLINE", "ONLINE_ID").toString());
                param.setFilter("normsCode", beans.getNormsCode());
                param.setFilter("normsName", beans.getNormsName());
                param.setFilter("resultInnerweightFlg", beans.getResultInnerweightFlg());
                param.setFilter("resultInnererrorFlg", beans.getResultInnererrorFlg());
                param.setFilter("resultInnercountFlg", beans.getResultInnercountFlg());
                param.setFilter("resultInnersizeFlg", beans.getResultInnersizeFlg());
                param.setFilter("resultInnermatFlg", beans.getResultInnermatFlg());
                param.setFilter("resultOutspecFlg", beans.getResultOutspecFlg());
                param.setFilter("resultOutweightFlg", beans.getResultOutweightFlg());
                param.setFilter("resultOutsizeFlg", beans.getResultOutsizeFlg());
                param.setFilter("resultOutmatFlg", beans.getResultOutmatFlg());
                super.save(SqlId.SQL_ID_SAVE_LIST_PD_NORMS, param);
            }
        }
    }

    /**
     * 获取包装standardId
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PdNormsStd findNormsStandardId(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_NORMS_STANDARDID, param);
    }

    /**
     * 获取产品等级最大批次数
     *
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public PD141403Bean findMaxId(BaseParam param) {
        return super.findOne(SqlId.SQL_ID_FIND_MAXID, param);
    }

}
