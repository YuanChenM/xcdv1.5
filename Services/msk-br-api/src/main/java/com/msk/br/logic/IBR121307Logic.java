package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.IBR121307RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrMPdClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/8/10.
 */
@Service
public class IBR121307Logic extends BaseLogic{

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121307Logic.class);
    @Autowired
    private CommonLogic commonLogic;
    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    interface SqlId{
        String FIND_CLASSES_LIST = "findClassesList";
        String FIND_MACHINING_LIST = "findMachiningList";
    }

    @Transactional(readOnly = true)
    public List<BrMPdClasses> findClassesList(IBR121307RsParam param){
        return super.findList(SqlId.FIND_CLASSES_LIST,param);
    }
    @Transactional(readOnly = true)
    public List<BrMPdClasses> findMachiningList(IBR121307RsParam param){
        return super.findList(SqlId.FIND_MACHINING_LIST,param);
    }
}
