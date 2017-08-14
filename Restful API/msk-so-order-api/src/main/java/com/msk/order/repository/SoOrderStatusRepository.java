package com.msk.order.repository;

import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.entity.SoOrderStatus;
import org.springframework.stereotype.Repository;

/**
 * Created by liu_tao2 on 2016/8/10.
 */
@Repository("soOrderStatusRepository")
public interface SoOrderStatusRepository extends BaseRepository<SoOrderStatus, Long> {
}
