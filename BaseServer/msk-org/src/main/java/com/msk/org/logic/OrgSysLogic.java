package com.msk.org.logic;

import java.util.*;

import com.msk.org.bean.LoginUser;
import com.msk.org.bean.LoginUserType;
import com.msk.org.bean.param.LoginUserMenuParam;
import com.msk.org.bean.result.EmployVisitPageResult;
import com.msk.org.entity.OrgPage;
import com.msk.org.repository.OrgDepartmentRepository;
import com.msk.org.repository.OrgEmployRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msk.org.entity.OrgSystem;
import com.msk.org.repository.OrgSysRepository;
import org.springframework.util.CollectionUtils;

@Service
public class OrgSysLogic {
    @Autowired
    private OrgSysRepository sysRepository;
    @Autowired
    private OrgPageLogic pageLogic;
    @Autowired
    private OrgEmployRepository employRepository;
    @Autowired
    private OrgDepartmentRepository departmentRepository;
    public List<OrgSystem> findSysListBySysCodeLike(String systemCode){
        List<OrgSystem> sysList = this.sysRepository.findSysListBySysCodeLike(systemCode+"%");
        for (OrgSystem orgSys : sysList){
            List<OrgPage> pageList = pageLogic.findPageListBySysCode(orgSys.getSysCode());
            orgSys.setPageList(pageList);
        }
        return sysList;
    }

    private void initVisitPageList(Set<String> systemCodeList,List<EmployVisitPageResult> employVisitPageList,List<Object[]> employMenuArray){
        for(Object [] employMenu : employMenuArray){
            EmployVisitPageResult employVisitPage = new EmployVisitPageResult();
            String pageCode = String.valueOf(employMenu[0]);
            String systemCode = String.valueOf(employMenu[1]);
            employVisitPage.setPageCode(pageCode);
            employVisitPage.setSystemCode(systemCode);
            employVisitPageList.add(employVisitPage);
            systemCodeList.add(systemCode);
        }


    }

    public List<OrgSystem> findUserMenuList(LoginUserMenuParam longinUserMenuParam){
        String userName = longinUserMenuParam.getUserName();
        String userType = longinUserMenuParam.getUserType();
        List<OrgSystem> systemList = new ArrayList<>();
        List<OrgSystem> returnSystemList = new ArrayList<>();
        Iterator<OrgSystem> systemIterator = null;
        List<Object[]> employMenuArray = null;
        Set<String> systemCodeList = null;
        List<EmployVisitPageResult> employVisitPageList = null;
        if(userType.equals(LoginUserType.getUserTypeEmploy())||userType.equals(LoginUserType.getUserTypeBank())){
            employMenuArray =  employRepository.findEmployMenu(userName);
            employVisitPageList = new ArrayList<>();
            systemCodeList = new HashSet<>();
            this.initVisitPageList(systemCodeList,employVisitPageList,employMenuArray);
        }
        else if(userType.equals(LoginUserType.getUserTypeSeller())){
            employMenuArray =  departmentRepository.findDepartmentRole(2L);
            employVisitPageList = new ArrayList<>();
            systemCodeList = new HashSet<>();
            this.initVisitPageList(systemCodeList,employVisitPageList,employMenuArray);
        }else{
            return null;
        }


        for(String systemCode : systemCodeList){
            List<OrgSystem> systemMenuList = this.findSysListBySysCodeLike(systemCode);
            systemList.addAll(systemMenuList);
        }
        systemIterator =  systemList.iterator();
        while (systemIterator.hasNext()){
            OrgSystem system = systemIterator.next();
            List<OrgPage> pageList = system.getPageList();
            if(CollectionUtils.isEmpty(pageList)){
                systemIterator.remove();
                continue;
            }
            boolean isSystemFlag = false;
            List<OrgPage> visitPageList = new ArrayList<>();
            for(EmployVisitPageResult employVisitPage : employVisitPageList){
                String pageCode = employVisitPage.getPageCode();
                String systemCode = employVisitPage.getSystemCode();
                if(system.getSysCode().indexOf(systemCode)>=0){
                    isSystemFlag = true;
                    if("ALL".equals(pageCode)){
                        visitPageList.addAll(pageList);
                        break;
                    }
                    for (OrgPage page : pageList){
                        if(page.getPageCode().equals(pageCode)){
                            visitPageList.add(page);
                        }
                    }
                }
            }
            if(!isSystemFlag){
                continue;
            }
            system.setPageList(visitPageList);
            returnSystemList.add(system);
        }
        return returnSystemList;
    }

}
