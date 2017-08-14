package com.msk.org.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.msk.org.entity.OrgRoleAuth;
import com.msk.org.entity.OrgRoleAuthPrimaryKey;

import java.util.List;

@Repository("OrgRoleAuthRepository")
public interface OrgRoleAuthRepository extends PagingAndSortingRepository<OrgRoleAuth,OrgRoleAuthPrimaryKey> {
    @Query(value = "SELECT ORA FROM OrgRoleAuth ORA,OrgRole ORO,OrgEmplRole OER " +
                   "WHERE ORA.roleAuthPrimaryKey.roleId = ORO.roleId " +
                   "AND ORO.roleId=OER.emplRolePrimaryKey.roleId " +
                   "AND OER.emplRolePrimaryKey.emplId=?1")
    List<OrgRoleAuth> findRoleAuth(Long emplId);

    @Query(value = "SELECT ORA FROM OrgRoleAuth ORA,OrgRole ORO " +
            "WHERE ORA.roleAuthPrimaryKey.roleId = ORO.roleId " +
            "AND ORO.deptId=?1 AND ORO.delFlg='0'")
    List<OrgRoleAuth> findDepartmentRoleAuth(String departmentId);

}
