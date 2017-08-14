package com.msk.common.base.mybatis;

import com.msk.comm.bean.result.PageResult;
import com.msk.common.base.BaseDao;
import com.msk.common.config.MyBatisConfig;
import com.msk.common.datasource.DruidDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@Service("com.msk.common.base.mybatis.BaseServices")
public class BaseServices extends com.msk.common.base.BaseServices{

    private static SqlSessionFactory sqlSessionFactory;
    private static ClassLoader classLoader;
    @Qualifier("slaveDataSource")
    @Autowired
    private DruidDataSource slaveDataSource;
    public <T extends Serializable> T findOne(Serializable param, String sqlId){
        SqlSession sqlSession = this.getSqlSession();
        T entity = null;
        try {
            entity = sqlSession.selectOne(getBaseDaoClass().getName()+"."+sqlId,param);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }finally {
            sqlSession.close();
        }
        return entity;
    }
    public <T extends Serializable> List<T> findList(Serializable param, String sqlId){
        SqlSession sqlSession = this.getSqlSession();
        List<T> entity = null;
        try {
            entity = sqlSession.selectList(getBaseDaoClass().getName()+"."+sqlId,param);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }finally {
            sqlSession.close();
        }
        return entity;
    }


    public <T extends Serializable> T findOne(Serializable param,String sqlId,Class<? extends BaseDao> clazz,SqlSession sqlSession){
        T entity = null;
        try {
            entity = sqlSession.selectOne(clazz.getName()+"."+sqlId,param);
        }catch (Exception ex){
            sqlSession.close();
            throw new RuntimeException(ex);
        }
        return entity;
    }

    public <T extends Serializable> List<T> findList(Serializable param,String sqlId,Class<? extends BaseDao> clazz,SqlSession sqlSession){
        List<T> entity = null;
        try {
            entity = sqlSession.selectList(clazz.getName()+"."+sqlId,param);
        }catch (Exception ex){
            sqlSession.close();
            throw new RuntimeException(ex);
        }
        return entity;
    }

    public int count(Serializable param,String sqlId,Class<? extends BaseDao> clazz,SqlSession sqlSession) {
        int count = 0;
        try{
            count = sqlSession.selectOne(clazz.getName()+"."+sqlId,param);
        }finally {
            sqlSession.close();
        }
        return count;
    }

    public <T extends Serializable> T findOne(Serializable param,Class<? extends BaseDao> clazz) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        T entity = null;
        try {
            BaseDao baseDao = sqlSession.getMapper(clazz);
            entity = baseDao.findOne(param);
        }finally {
            sqlSession.close();
        }
        return entity;
    }

    public <T extends Serializable> List<T> findList(Serializable param,Class<? extends BaseDao> clazz) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        List<T> list = null;
        try{
            BaseDao baseDao = sqlSession.getMapper(clazz);
            list = baseDao.findList(param);
        }finally {
            sqlSession.close();
        }
        sqlSession.close();
        return list;
    }

    public <T extends Serializable> List<T> findPageList(Serializable param,Class<? extends BaseDao> clazz) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        List<T> list = null;
        try{
            BaseDao baseDao = sqlSession.getMapper(clazz);
            list = baseDao.findPageList(param);
        }finally {
            sqlSession.close();
        }
        return list;
    }

    public int count(Serializable param,Class<? extends BaseDao> clazz) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        int count = 0;
        try{
            BaseDao baseDao = sqlSession.getMapper(clazz);
            count = baseDao.count(param);
        }finally {
            sqlSession.close();
        }
        return count;
    }

    public int findPageCount(Serializable param,Class<? extends BaseDao> clazz) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        int count = 0;
        try{
            BaseDao baseDao = sqlSession.getMapper(clazz);
            count = baseDao.findPageCount(param);
        }finally {
            sqlSession.close();
        }
        return count;
    }

    public <T extends Serializable> PageResult<T> findPage(Serializable param, Class<? extends BaseDao> clazz){
        int count = this.findPageCount(param,clazz);
        PageResult<T> pageResult = new PageResult<>();
        if(count != 0){
            pageResult.setRecordsTotal(count);
            List<T> pageListData = this.findPageList(param,clazz);
            pageResult.setData(pageListData);
        }
        return pageResult;
    }

    public <T extends BaseDao> Class<T> getBaseDaoClass(){
        return null;
    }

    private SqlSessionFactory getSqlSessionFactory(){
        if(sqlSessionFactory == null){
            MyBatisConfig myBatisConfig = new MyBatisConfig();
            String resource = "mybatis/mybatis-config.xml";
            try {
                InputStream inputStream = getMyBatisConfigInputStream();
                sqlSessionFactory = myBatisConfig.sqlSessionFactory(slaveDataSource,inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory;
    }

    private InputStream getMyBatisConfigInputStream() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(classLoader,resource);
        return inputStream;
    }

    public static void setClassLoader(ClassLoader classLoader){
        BaseServices.classLoader = classLoader;
    }


    public SqlSession getSqlSession(){
        return this.getSqlSessionFactory().openSession();

    }

    public <T extends BaseDao> BaseDao getBaseDao(SqlSession sqlSession, Class<T> clazz){
        BaseDao baseDao = sqlSession.getMapper(clazz);
        return baseDao;
    }

    public void closeSqlSession(SqlSession sqlSession){
        sqlSession.close();
    }
}
