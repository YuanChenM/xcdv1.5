package com.msk.order.repository;

import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.entity.SoSubOrderDetailLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liu_tao2 on 2016/8/10.
 */
@Repository("soSubOrderDetailLogRepository")
public interface SoSubOrderDetailLogRepository extends BaseRepository<SoSubOrderDetailLog,Long> {
}
