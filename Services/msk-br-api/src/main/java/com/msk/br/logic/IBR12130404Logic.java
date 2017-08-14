package com.msk.br.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class IBR12130404Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR12130404Logic.class);

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
    }
    /**
     *清空一二级产品原始数据
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
     *清空三四级产品原始数据
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
     *清空五六级产品原始数据
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
     *清空物流区产品原始数据
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
     *同步三四级分类的产品数据
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
     *同步一五六级分类的产品数据
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
     *同步物流区产品数据
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
