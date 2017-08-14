package com.msk.config.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.msk.comm.consts.NumberConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msk.config.bean.CommConstant;
import com.msk.config.bean.DbInfo;
import com.msk.config.bean.RsRequest;
import com.msk.config.bean.RsResponse;
import com.msk.config.dao.RedisExtendsUtils;
import com.msk.config.logic.CodeMasterLogic;

import redis.clients.jedis.JedisPoolConfig;

@Controller
public class CodeMasterController {
    private static final String CODE_MASTER = "codeMasters";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CodeMasterLogic codeMasterLogic;

    @RequestMapping(value = "/sync/code/master", method = RequestMethod.GET)
    public String syncCodeMaster() {
        return "/codemaster/syncCodeMaster";
    }

    @RequestMapping(value = "/view/code/master", method = RequestMethod.GET)
    public String viewCodeMaster(Model model) {
        String selectSql = "SELECT CONSTANT_TYPE AS constantType, CONSTANT_NAME AS constantName,CONSTANT_VALUE AS constantValue FROM COMM_CONSTANT WHERE DEL_FLG = ?";
        Object[] sqlParam = new Object[1];
        sqlParam[0] = NumberConst.IntDef.INT_ZERO;
        //查询mysql数据
        List<CommConstant> constantList = jdbcTemplate.query(selectSql, sqlParam, new RowMapper<CommConstant>() {
            @Override
            public CommConstant mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommConstant entity = new CommConstant();
                entity.setConstantType(rs.getString("constantType"));
                entity.setConstantName(rs.getString("constantName"));
                entity.setConstantValue(rs.getString("constantValue"));
                return entity;
            }
        });
        model.addAttribute(CODE_MASTER, constantList);
        return "/codemaster/viewCodeMaster";
    }

    @RequestMapping(value = "/sync/code/master/data", method = RequestMethod.POST)
    @ResponseBody
    public RsResponse syncCodeMasterData(@RequestBody RsRequest<DbInfo> request) {
        DbInfo info = request.getParam();
        String url = "jdbc:mysql://" + info.getDbIp().trim() + ":3306/" + info.getDbName().trim();
        DataSource ds = codeMasterLogic.dataSource(url, info.getDbUserName(), info.getDbPwd());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        String selectSql = "SELECT CONSTANT_TYPE AS constantType, CONSTANT_NAME AS constantName,CONSTANT_VALUE AS constantValue FROM COMM_CONSTANT WHERE DEL_FLG = ?";
        Object[] sqlParam = new Object[1];
        sqlParam[0] = NumberConst.IntDef.INT_ZERO;
        //查询mysql数据
        List<CommConstant> constantList = jdbcTemplate.query(selectSql, sqlParam, new RowMapper<CommConstant>() {
            @Override
            public CommConstant mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommConstant entity = new CommConstant();
                entity.setConstantType(rs.getString("constantType"));
                entity.setConstantName(rs.getString("constantName"));
                entity.setConstantValue(rs.getString("constantValue"));
                return entity;
            }
        });
        //构建保存数据
        Map<String, Map<String, String>> constantMap = new HashMap<String, Map<String, String>>();
        if (!CollectionUtils.isEmpty(constantList)) {
            for (final CommConstant constant : constantList) {
                String key = constant.getConstantType();
                Map<String, String> value = constantMap.get(key);
                if (CollectionUtils.isEmpty(value)) {
                    value = new HashMap<String, String>();
                    constantMap.put(key, value);
                }
                value.put(constant.getConstantValue(), constant.getConstantName());
            }
        }
        //初始化redis连接
        //实例化链接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        //设置host
        connectionFactory.setHostName(info.getRedisIp().trim());
        //设置端口
        connectionFactory.setPort(6379);
        //设置密码
        connectionFactory.setPassword(info.getRedisPwd());
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(100);
        connectionFactory.setPoolConfig(poolConfig);
        //初始化connectionFactory
        connectionFactory.afterPropertiesSet();
        //实例化
        StringRedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
        RedisExtendsUtils redisExtendsUtils = new RedisExtendsUtils();
        redisExtendsUtils.setRedisTemplate(redisTemplate);
        redisExtendsUtils.setDatabase(Integer.parseInt(info.getRedisDbNum()));
        //清空db
        redisExtendsUtils.flushdb();
        redisExtendsUtils.setHashBatch(constantMap);
        RsResponse<String> rsResponse = new RsResponse<String>();
        rsResponse.setReturnCode("200");
        rsResponse.setStatus("S");
        return rsResponse;
    }
}
