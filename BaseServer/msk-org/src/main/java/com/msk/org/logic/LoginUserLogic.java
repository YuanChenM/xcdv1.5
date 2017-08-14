package com.msk.org.logic;

import com.msk.org.bean.Department;
import com.msk.org.bean.LoginUser;
import com.msk.org.entity.OrgEmploy;
import com.msk.org.entity.OrgRoleAuth;
import com.msk.org.entity.OrgSystemModule;
import com.msk.org.repository.OrgDepartmentRepository;
import com.msk.org.repository.OrgRoleAuthRepository;
import com.msk.org.repository.OrgSysModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginUserLogic {
    @Autowired
    private OrgEmployLogic employLogic;
    @Autowired
    private OrgDepartmentRepository departmentRepository;
    @Autowired
    private OrgSysModuleRepository sysModuleRepository;
    @Autowired
    private OrgRoleAuthRepository roleAuthRepository;

    public LoginUser getLoginEmployeeInfo(String employeeCode) {
        OrgEmploy employ = employLogic.findEmployByEmployCode(employeeCode);
        if (employ == null) {
            throw new RuntimeException("用户信息没有获得");
        }
        Long employId = employ.getEmployId();
        LoginUser loginUser = new LoginUser();
        this.setLoginUserAttribute(employ, loginUser);

        List<String> systemModuleArray = this.sysModuleRepository.findUserVisitSystemCode(employId);
        loginUser.setSystemModules(this.createSystemModuleList(systemModuleArray));

        List<Object[]> departmentArray = this.departmentRepository.findEmployDepartment(employId);
        loginUser.setDepartmentList(this.createDepartmentList(departmentArray));

        List<OrgRoleAuth> roleAuthList = roleAuthRepository.findRoleAuth(employId);
        loginUser.setUserRoleList(roleAuthList);

        return loginUser;
    }

    private List<OrgSystemModule> createSystemModuleList(List<String> systemModuleArray) {
        List<OrgSystemModule> systemModuleList = new ArrayList<>();
        for (String systemCode : systemModuleArray) {
            OrgSystemModule systemModule = new OrgSystemModule();
            systemModule.setSysCode(systemCode);
            systemModuleList.add(systemModule);
        }
        return systemModuleList;
    }

    private List<Department> createDepartmentList(List<Object[]> departmentArray) {
        List<Department> departmentList = new ArrayList<>();
        for (Object[] departmentObject : departmentArray) {
            int index = 0;
            Long deptId = Long.valueOf(String.valueOf(departmentObject[index++]));
            String departmentCode = (String) departmentObject[index++];
            String departmentName = (String) departmentObject[index++];
            Department department = new Department(deptId, departmentCode, departmentName);
            departmentList.add(department);
        }
        return departmentList;
    }

    private void setLoginUserAttribute(OrgEmploy employ, LoginUser loginUser) {
        loginUser.setEmplId(String.valueOf(employ.getEmployId()));
        loginUser.setEmplNo(employ.getEmployCode());
        loginUser.setEmplName(employ.getEmployName());
        loginUser.setStatus(employ.getStatus());
        loginUser.setEmialAddr(employ.getEmial());
        loginUser.setLoginPwd(employ.getPassword());
    }

}
