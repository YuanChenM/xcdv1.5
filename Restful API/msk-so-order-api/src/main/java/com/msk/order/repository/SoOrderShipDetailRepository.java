package com.msk.order.repository;

import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.entity.SoOrderShipDetail;
import org.springframework.stereotype.Repository;

/**
 * Created by liu_tao2 on 2016/8/10.
 */
@Repository("soOrderShipDetailRepository")
public interface SoOrderShipDetailRepository extends BaseRepository<SoOrderShipDetail, Long> {
}
