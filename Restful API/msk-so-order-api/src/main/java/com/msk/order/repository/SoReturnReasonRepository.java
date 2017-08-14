package com.msk.order.repository;

import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.entity.SoReturnReason;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/10.
 */
@Repository("soReturnReasonRepository")
public interface SoReturnReasonRepository extends BaseRepository<SoReturnReason,Long> {
}
