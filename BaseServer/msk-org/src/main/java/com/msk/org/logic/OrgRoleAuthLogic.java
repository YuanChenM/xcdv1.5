package com.msk.org.logic;

import com.msk.org.entity.OrgRoleAuth;
import com.msk.org.repository.OrgRoleAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgRoleAuthLogic {
    @Autowired
    private OrgRoleAuthRepository roleAuthRepository;

    public List<OrgRoleAuth> searchDepartmentRole(String departmentId){
        return this.roleAuthRepository.findDepartmentRoleAuth(departmentId);
    }
}
