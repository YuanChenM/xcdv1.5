package com.msk.org.repository;

import java.util.List;

import com.msk.org.entity.OrgSystem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("orgSysRepository")
public interface OrgSysRepository extends PagingAndSortingRepository<OrgSystem,String> {
    List<OrgSystem> findSysListBySysCodeLike(String sysCode);
}
