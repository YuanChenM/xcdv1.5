package com.msk.batch.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BrMPdClasses;
import com.msk.core.entity.BrOClaMachiningInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BBR12140304Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BBR12140304Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId {
        String delete_Machining_Info = "deleteMachiningInfo";
        String add_Machining_Info = "addMachiningInfo";
        String delete_Breed_Fea_Info = "deleteBreedFeaInfo";
        String add_Breed_Fea_Info = "addBreedFeaInfo";
        String delete_Wei_Nor_Info = "deleteWeiNorInfo";
        String add_Wei_Nor_Info = "addWeiNorInfo";
        String DELETE_PD_LGCSAREA = "deletePdLgcsArea";
        String ADD_PD_LGCSAREA = "addPdLgcsArea";
        String UPDATE_BR_M_PD_CLASSES = "updateBrMPdClasses";
        String FIND_BR_M_PD_CLASSES = "findBrMPdClasses";
        String FIND_BR_O_CLA_MACHININGINFOS = "findBrOClaMachiningInfos";
        String FIND_BUYER_POOL_COUNT = "findBuyerPoolCount";
        String SAVE_BUYER_POOL_LOGISTICS_AREA = "saveBuyerPoolLogisticsArea";
        String SAVE_BUYER_POOL_LOGISTICS_AREA_REGION = "saveBuyerPoolLogisticsAreaRegion";
    }

    /**
     * 查询产品是否已存在买家池
     */
    @Transactional(readOnly = true)
    public int findBuyerPoolCount(BaseParam param){
        return this.getCount(SqlId.FIND_BUYER_POOL_COUNT,param);
    }

    /**
     * 保存物流区买家池
     */
    @Transactional
    public int saveBuyerPoolLogisticsArea(BaseParam param){
        return  this.save(SqlId.SAVE_BUYER_POOL_LOGISTICS_AREA,param);
    }

    /**
     * 保存物流区地区买家池
     */
    @Transactional
    public int saveBuyerPoolLogisticsAreaRegion(BaseParam param){
        return this.save(SqlId.SAVE_BUYER_POOL_LOGISTICS_AREA_REGION,param);
    }

    /**
     *
     */

    /**
     * 清空一二级产品原始数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int deleteMachiningInfo(BaseParam param) {
        logger.info("清空一二级产品原始数据");
        return super.remove(SqlId.delete_Machining_Info, param);
    }


    /**
     * 清空三四级产品原始数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int deleteBreedFeaInfo(BaseParam param) {
        logger.info("清空三四级产品原始数据");
        return super.remove(SqlId.delete_Breed_Fea_Info, param);
    }

    /**
     * 清空五六级产品原始数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int deleteWeiNorInfo(BaseParam param) {
        logger.info("清空五六级产品原始数据");
        return super.remove(SqlId.delete_Wei_Nor_Info, param);
    }

    /**
     * 清空物流区产品原始数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int deletePdLgcsArea(BaseParam param) {
        logger.info("清空物流区产品原始数据");
        return super.remove(SqlId.DELETE_PD_LGCSAREA, param);
    }
    /**
     * 查询产品分类信息
     */
    @Transactional(readOnly = true)
    public List<BrOClaMachiningInfo> findBrOClaMachiningInfos(BaseParam param){
        return super.findList(SqlId.FIND_BR_O_CLA_MACHININGINFOS,param);
    }
    /**
     * 查询产品基础数据
     */
    @Transactional(readOnly = true)
    public List<BrMPdClasses> findBrMPdClasses(BaseParam param){
        logger.info("查询产品基础数据");
        return super.findList(SqlId.FIND_BR_M_PD_CLASSES,param);
    }
    /**
     *变更产品基础数据
     */
    @Transactional
    public int updateBrMPdClasses(BrMPdClasses brMPdClasses){
        logger.info("产品基础数据变更");
        return super.save(SqlId.UPDATE_BR_M_PD_CLASSES,brMPdClasses);
    }

    /**
     * 同步一级和二级分类的产品数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int addMachiningInfo(BaseParam param) {
        logger.info("同步一级和二级分类的产品数据");
        return super.save(SqlId.add_Machining_Info, param);
    }

    /**
     * 同步三四级分类的产品数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int addBreedFeaInfo(BaseParam param) {
        logger.info("同步三四级分类的产品数据");
        return super.save(SqlId.add_Breed_Fea_Info, param);
    }

    /**
     * 同步一五六级分类的产品数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int addWeiNorInfo(BaseParam param) {
        logger.info("同步一五六级分类的产品数据");
        return super.save(SqlId.add_Wei_Nor_Info, param);
    }

    /**
     * 同步物流区产品数据
     *
     * @param param
     * @return
     */
    @Transactional
    public int addPdLgcsArea(BaseParam param) {
        logger.info("同步物流区产品数据");
        return super.save(SqlId.ADD_PD_LGCSAREA, param);
    }

}
