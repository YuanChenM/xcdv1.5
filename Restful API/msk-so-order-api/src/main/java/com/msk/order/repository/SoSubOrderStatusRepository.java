package com.msk.order.repository;

import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.entity.SoSubOrderStatus;
import org.springframework.stereotype.Repository;

/**
 * Created by liu_tao2 on 2016/8/10.
 */
@Repository("soSubOrderStatusRepository")
public interface SoSubOrderStatusRepository extends BaseRepository<SoSubOrderStatus,Long> {
}
