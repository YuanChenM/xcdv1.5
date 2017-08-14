package com.msk.common.base.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public class BaseServices extends com.msk.common.base.BaseServices{


    public <T extends Serializable> T save(T entity){
        return this.getRepository().save(entity);
    }

    public <PK extends Serializable> void remove(PK pk){
        this.getRepository().delete(pk);
    }

    public <T extends Serializable,PK extends Serializable> T findOne(PK pk){
        return this.findOne(pk);
    }


    public <T,PK extends Serializable> CrudRepository<T,PK> getRepository(){
        throw new NullPointerException("JPA Repository没有定义");
    }

}
