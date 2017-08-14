package com.msk.org.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.msk.org.entity.OrgAction;

@Repository("orgActionRepository")
public interface OrgActionRepository extends PagingAndSortingRepository<OrgAction,String> {
}
