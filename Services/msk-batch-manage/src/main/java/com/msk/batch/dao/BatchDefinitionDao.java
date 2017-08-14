package com.msk.batch.dao;

import com.msk.batch.bean.PageResult;
import com.msk.core.entity.BatchDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jackjiang on 16/7/1.
 */
@Repository
public class BatchDefinitionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<BatchDefinition> selectPageResult(){
        return this.jdbcTemplate.query("SELECT BATCH_CODE AS batchCode,BATCH_NAME AS batchName,RUN_FREQUENCY AS runFrequency,VER AS ver FROM BATCH_DEFINITION", new RowMapper<BatchDefinition>() {
            @Override
            public BatchDefinition mapRow(ResultSet rs, int rowNum) throws SQLException {
                BatchDefinition entity = new BatchDefinition();
                entity.setBatchCode(rs.getString("batchCode"));
                entity.setBatchName(rs.getString("batchName"));
                entity.setRunFrequency(rs.getInt("runFrequency"));
                entity.setVer(rs.getInt("ver"));
                return entity;
            }
        });
    }
}
