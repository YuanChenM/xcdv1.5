package com.msk.batch.dao;

import com.msk.batch.bean.BasePageParam;
import com.msk.batch.bean.PageResult;
import com.msk.batch.consts.NumberConst;
import com.msk.batch.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.msk.core.entity.BatchRecord;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Batch Record Dao
 * @author jiang_nan
 */
@Repository
public class BatchRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public PageResult<BatchRecord> selectPageResult(BasePageParam param){
        PageResult<BatchRecord> result = new PageResult<>();
        String countSql = "SELECT COUNT(1) FROM BATCH_RECORD WHERE 1=1 ";
        String conditionSql = "";
        Map<String,Object> filterMap = param.getFilterMap();
        Object [] sqlParam = null;
        if(!CollectionUtils.isEmpty(filterMap)){
            sqlParam = new Object[filterMap.size()];
            int index = NumberConst.IntDef.INT_ZERO;
            String status = (String)filterMap.get("status");
            if(!StringUtil.isEmpty(status)){
                sqlParam [index++] = status;
                conditionSql += "AND STATUS = ?";
            }
        }
        String selectSql = "SELECT " +
                                    "RUN_ID, "+
                                    "BATCH_CODE, " +
                                    "STATUS, " +
                                    "RESULT, " +
                                    "DEL_FLG, " +
                                    "CRT_ID, " +
                                    "CRT_TIME, " +
                                    "UPD_ID, " +
                                    "UPD_TIME, " +
                                    "ACT_ID, " +
                                    "ACT_TIME, " +
                                    "VER, " +
                                    "EXECUTE_MODEL, " +
                                    "PARAMETER " +
                                    "FROM BATCH_RECORD " +
                                    "WHERE 1=1 ";
        Integer recordsTotal = this.jdbcTemplate.queryForObject(countSql + conditionSql,sqlParam,Integer.class);
        result.setRecordsTotal(recordsTotal);
        List<BatchRecord> data = this.jdbcTemplate.query(selectSql + conditionSql, sqlParam, new RowMapper<BatchRecord>() {
            @Override
            public BatchRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
                BatchRecord entity = new BatchRecord();
                entity.setRunId(rs.getLong("RUN_ID"));
                entity.setBatchCode(rs.getString("BATCH_CODE"));
                entity.setStatus(rs.getInt("STATUS"));
                entity.setResult(rs.getString("RESULT"));
                entity.setDelFlg(rs.getString("DEL_FLG"));
                entity.setCrtId(rs.getString("CRT_ID"));
                entity.setCrtTime(rs.getDate("CRT_TIME"));
                entity.setUpdId(rs.getString("UPD_ID"));
                entity.setUpdTime(rs.getDate("UPD_TIME"));
                entity.setActId(rs.getString("ACT_ID"));
                entity.setActTime(rs.getDate("ACT_TIME"));
                entity.setVer(rs.getInt("VER"));
                entity.setExecuteModel(rs.getString("EXECUTE_MODEL"));
                entity.setParameter(rs.getString("PARAMETER"));
                return entity;
            }
        });
        result.setData(data);


        return result;
    }



    public int update(BatchRecord batchRecord){
        return jdbcTemplate.update("UPDATE BATCH_RECORD " +
                                   "SET " +
                                   "STATUS = ?" +
                                   ",RESULT = ?" +
                                   ",UPD_ID = ?" +
                                   ",UPD_TIME = ?" +
                                   ",VER = VER+1 " +
                                   "WHERE RUN_ID=?"
                                   ,new Object[]{
                                   batchRecord.getStatus(),
                                   batchRecord.getResult(),
                                   batchRecord.getUpdId(),
                                   batchRecord.getUpdTime(),
                                   batchRecord.getRunId()});
    }


    /**
     * Batch记录插入
     * @param batchRecord Batch记录
     * @return 影响行数
     */
    public int insert(BatchRecord batchRecord){
        Long runId = this.maxId();
        if(runId == null){
            runId = new Long(0L);
        }
        runId = new Long(runId.longValue() + 1L);
        batchRecord.setRunId(runId);
        return jdbcTemplate.update("INSERT INTO BATCH_RECORD(" +
                                    "RUN_ID, " +
                                    "BATCH_CODE, " +
                                    "STATUS, " +
                                    "RESULT, " +
                                    "DEL_FLG, " +
                                    "CRT_ID, " +
                                    "CRT_TIME, " +
                                    "UPD_ID, " +
                                    "UPD_TIME, " +
                                    "ACT_ID, " +
                                    "ACT_TIME, " +
                                    "VER, " +
                                    "EXECUTE_MODEL, " +
                                    "PARAMETER) " +
                                    "VALUES (" +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?," +
                                    "?)"
                                    ,new Object[]{
                                     batchRecord.getRunId()
                                    ,batchRecord.getBatchCode()
                                    ,batchRecord.getStatus()
                                    ,batchRecord.getResult()
                                    ,batchRecord.getDelFlg()
                                    ,batchRecord.getCrtId()
                                    ,batchRecord.getCrtTime()
                                    ,batchRecord.getUpdId()
                                    ,batchRecord.getUpdTime()
                                    ,batchRecord.getActId()
                                    ,batchRecord.getActTime()
                                    ,batchRecord.getVer()
                                    ,batchRecord.getExecuteModel()
                                    ,batchRecord.getParameter()
                                    });
    }

    public Long maxId(){
        return this.jdbcTemplate.queryForObject("SELECT MAX(RUN_ID) FROM BATCH_RECORD",Long.class);
    }


}
