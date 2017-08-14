package com.msk.batch.logic;

import com.msk.batch.dao.BatchDefinitionDao;
import com.msk.core.entity.BatchDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jackjiang on 16/7/1.
 */
@Service
public class BatchDefinitionLogic {
    @Autowired
    private BatchDefinitionDao batchDefinitionDao;
    @Transactional(readOnly = true)
    public List<BatchDefinition> findBatchDefinition(){
        return this.batchDefinitionDao.selectPageResult();
    }
}
