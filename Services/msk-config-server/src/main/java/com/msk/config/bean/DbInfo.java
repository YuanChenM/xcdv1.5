package com.msk.config.bean;

/**
 * Created by shi_yuxi on 2016/7/15.
 */
public class DbInfo {

    private String dbIp;

    private String dbUserName;

    private String dbPwd;

    private String dbName;

    private String redisIp;

    private String redisPwd;

    private String redisDbNum;

    public String getDbIp() {
        return dbIp;
    }

    public void setDbIp(String dbIp) {
        this.dbIp = dbIp;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getRedisIp() {
        return redisIp;
    }

    public void setRedisIp(String redisIp) {
        this.redisIp = redisIp;
    }

    public String getRedisPwd() {
        return redisPwd;
    }

    public void setRedisPwd(String redisPwd) {
        this.redisPwd = redisPwd;
    }

    public String getRedisDbNum() {
        return redisDbNum;
    }

    public void setRedisDbNum(String redisDbNum) {
        this.redisDbNum = redisDbNum;
    }
}
