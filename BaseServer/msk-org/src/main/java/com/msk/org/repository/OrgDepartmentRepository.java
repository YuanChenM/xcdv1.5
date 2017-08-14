package com.msk.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.msk.org.entity.OrgDepartment;

@Repository("orgDepartmentRepository")
public interface OrgDepartmentRepository extends PagingAndSortingRepository<OrgDepartment,Long>{

    @Query(value = "SELECT OD.dept_Id,OD.dept_Code,OD.dept_Name " +
                   "FROM ORG_DEPT AS OD " +
                   "INNER JOIN ORG_DEPT_EMPL AS ODE ON OD.dept_Id = ODE.dept_Id " +
                   "WHERE ODE.empl_Id=?1",nativeQuery = true)
    List<Object[]> findEmployDepartment(Long emplId);

    @Query(value = "SELECT " +
                    "ORA.PAGE_CODE," +
                    "ORA.SYS_CODE " +
                    "FROM ORG_DEPT AS OD " +
                    "INNER JOIN ORG_ROLE AS ORO " +
                    "ON ORO.DEPT_ID = OD.DEPT_ID " +
                    "INNER JOIN ORG_ROLE_AUTH AS ORA " +
                    "ON ORA.ROLE_ID = ORO.ROLE_ID " +
                    "WHERE OD.DEPT_ID = ?1 AND OD.DEL_FLG='0' AND ORA.ACTION_CODE = 'MENU'",nativeQuery = true)
    List<Object[]> findDepartmentRole(Long departmentId);

    @Query(value = "SELECT OD " +
                    "FROM OrgDepartment OD," +
                    "OrgDepartmentEmploy ODE," +
                    "OrgEmploy OE " +
                    "WHERE OD.deptId = ODE.deptEmplPrimaryKey.deptId " +
                    "AND ODE.deptEmplPrimaryKey.emplId = OE.employId AND OE.employCode=?1")
    List<OrgDepartment> findEmployDepartment(String employCode);

}
