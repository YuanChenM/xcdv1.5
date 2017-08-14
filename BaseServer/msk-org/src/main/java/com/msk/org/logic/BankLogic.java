package com.msk.org.logic;

import com.msk.org.bean.Department;
import com.msk.org.bean.LoginUser;
import com.msk.org.entity.OrgRoleAuth;
import com.msk.org.entity.OrgSystemModule;
import com.msk.org.repository.OrgDepartmentRepository;
import com.msk.org.repository.OrgRoleAuthRepository;
import com.msk.org.repository.OrgSysModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msk.org.bean.LoginUserType;
import com.msk.org.entity.OrgEmploy;
import com.msk.org.repository.OrgEmployRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankLogic {
    @Autowired
    private OrgEmployRepository employRepository;
    @Autowired
    private OrgEmployLogic employLogic;
    @Autowired
    private OrgDepartmentRepository departmentRepository;
    @Autowired
    private OrgSysModuleRepository sysModuleRepository;
    @Autowired
    private OrgRoleAuthRepository roleAuthRepository;
    public boolean login(String userName,String password){
        OrgEmploy employ = this.employRepository.findEmployByEmployCodeAndUserTypeAndDelFlg(userName, LoginUserType.getUserTypeBank(),"0");
        if(employ != null){
            String employPassword = employ.getPassword();
            return employLogic.checkEmployPassword(employPassword,password);
        }
        return Boolean.FALSE;
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
    public LoginUser getBankUserInfo(String userName){
        OrgEmploy employ = this.employRepository.findEmployByEmployCodeAndUserTypeAndDelFlg(userName, LoginUserType.getUserTypeBank(),"0");
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
    private void setLoginUserAttribute(OrgEmploy employ, LoginUser loginUser) {
        loginUser.setEmplId(String.valueOf(employ.getEmployId()));
        loginUser.setEmplNo(employ.getEmployCode());
        loginUser.setEmplName(employ.getEmployName());
        loginUser.setStatus(employ.getStatus());
        loginUser.setEmialAddr(employ.getEmial());
        loginUser.setLoginPwd(employ.getPassword());
    }


}
