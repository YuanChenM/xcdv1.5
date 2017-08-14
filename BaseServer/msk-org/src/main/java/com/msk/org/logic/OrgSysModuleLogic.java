package com.msk.org.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.msk.org.entity.OrgSystemModule;
import com.msk.org.repository.OrgSysModuleRepository;

@Service("orgSysModuleLogic")
public class OrgSysModuleLogic {
    @Autowired
    private OrgSysModuleRepository systemModuleRepository;
    public List<OrgSystemModule> findSystemModelByLoginUserType(String loginUserType){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC,"sort"));
        return this.systemModuleRepository.findSystemModelByLoginUserTypeAndDelFlg(loginUserType,"0",sort);
    }

}
