package com.msk.br.logic;

import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.br.bean.BY121310RsBean;
import com.msk.br.bean.BY121310RsParam;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.BrOClaMachiningInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 买家买家池归属
 * Created by tao_zhifa on 2016/7/11.
 */
@Service
public class IBR121303Logic extends BaseLogic {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121303Logic.class);

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }

    /*
     * 查询产品二级分类
     * @param param
     * @return
     */
    @Transactional(readOnly = true)
    public List<BY121310RsBean> findMachiningName (BY121310RsParam param){
        return super.findList(SqlId.FIND_MACHINING_NAME,param);
    }

    interface SqlId{
        static  String FIND_MACHINING_NAME = "findMachiningName";
    }
}
