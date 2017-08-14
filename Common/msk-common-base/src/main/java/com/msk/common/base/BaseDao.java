package com.msk.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jackjiang on 16/8/13.
 */
public interface BaseDao {
    <T extends Serializable> T findOne(Serializable param);

    <T extends Serializable> List<T> findList(Serializable param);

    int count(Serializable param);

    <T extends Serializable> List<T> findPageList(Serializable param);

    int findPageCount(Serializable param);
}
