package com.msk.order.repository;


import com.msk.common.data.jpa.BaseRepository;
import com.msk.order.entity.SoDeliver;
import org.springframework.stereotype.Repository;

@Repository("soDeliverRepository")
public interface SoDeliverRepository extends BaseRepository<SoDeliver,Long> {
}
