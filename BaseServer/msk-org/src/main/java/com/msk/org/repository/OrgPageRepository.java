package com.msk.org.repository;

import com.msk.org.entity.OrgPage;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orgPageRepository")
public interface OrgPageRepository extends PagingAndSortingRepository<OrgPage,String> {
    List<OrgPage> findPageListBySysCodeAndDelFlgAndIsMenu(String sysCode, String delFlg, String isMenu, Sort sort);

}
