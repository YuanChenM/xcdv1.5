package com.msk.org.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.msk.org.entity.OrgSystemModule;

@Repository("orgSysModuleRepository")
public interface OrgSysModuleRepository extends PagingAndSortingRepository<OrgSystemModule,String> {
    List<OrgSystemModule> findSystemModelByLoginUserTypeAndDelFlg(String loginUserType, String delFlg, Sort sort);

    @Query(value = "SELECT DISTINCT LEFT(ORA.SYS_CODE,3) AS systemCode " +
                   "FROM ORG_EMPL AS OE " +
                   "INNER JOIN ORG_EMPL_ROLE AS OER " +
                   "ON OER.EMPL_ID = OE.EMPL_ID AND OER.DEL_FLG = 0 " +
                   "INNER JOIN ORG_ROLE AS ORO " +
                   "ON OER.ROLE_ID = ORO.ROLE_ID AND ORO.DEL_FLG = 0 " +
                   "INNER JOIN ORG_ROLE_AUTH AS ORA " +
                   "ON ORA.ROLE_ID = ORO.ROLE_ID AND ORA.DEL_FLG = 0 "+
                   "WHERE OE.EMPL_ID=?1 AND OE.DEL_FLG = 0 ",nativeQuery = true)
    List<String> findUserVisitSystemCode(Long emplId);

}
