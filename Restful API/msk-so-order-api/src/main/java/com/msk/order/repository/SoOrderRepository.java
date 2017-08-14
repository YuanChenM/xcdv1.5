package com.msk.order.repository;

import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.bean.result.ISO151407RestOrderCountResult;
import com.msk.order.entity.SoOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/10.
 */
@Repository("soOrderRepository")
public interface SoOrderRepository extends BaseRepository<SoOrder, Long> {
    @Query(value = "select new com.msk.order.bean.result.ISO151407RestOrderCountResult(so.orderSource,count(1)) "
            + " from SoOrder so"
            + " where so.buyerId = :buyerId"
            + " AND so.delFlg = 0"
            + " AND ((:startDate) is null or so.orderTime >= :startDate)"
            + " AND ((:endDate) is null or so.orderTime <= :endDate)"
            + " GROUP BY so.orderSource")
    public List<ISO151407RestOrderCountResult> findOrderCount(@Param("buyerId") String buyerId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
