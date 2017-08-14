package com.msk.org.repository;

import com.msk.org.entity.OrgEmploy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orgEmployRepository")
public interface OrgEmployRepository extends PagingAndSortingRepository<OrgEmploy,String>{
    OrgEmploy findEmployByEmployCodeAndUserTypeAndDelFlg(String employCode,String userType,String delFlg);
    @Query(value = "SELECT " +
                   "  ORA.PAGE_CODE, " +
                   "  ORA.SYS_CODE " +
                   "FROM ORG_EMPL AS OE " +
                   "INNER JOIN ORG_EMPL_ROLE AS OER " +
                   "ON OER.EMPL_ID = OE.EMPL_ID " +
                   "INNER JOIN ORG_ROLE_AUTH AS ORA " +
                   "ON ORA.ROLE_ID = OER.ROLE_ID " +
                   "WHERE OE.EMPL_CODE = ?1 AND ORA.ACTION_CODE = 'MENU'",nativeQuery = true)
    List<Object[]> findEmployMenu(String employCode);
}
