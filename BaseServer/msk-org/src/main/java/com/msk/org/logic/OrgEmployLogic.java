package com.msk.org.logic;

import com.msk.common.utils.Md5Digest;
import com.msk.org.bean.LoginUserType;
import com.msk.org.bean.param.PageParam;
import com.msk.org.bean.result.BasePageResult;
import com.msk.org.entity.OrgDepartment;
import com.msk.org.entity.OrgEmploy;
import com.msk.org.repository.OrgEmployRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("orgEmployLogic")
public class OrgEmployLogic {
    @Autowired
    private OrgEmployRepository employRepository;

    public OrgEmploy save(OrgEmploy employ){
        return this.employRepository.save(employ);
    }

    public Boolean modifyPassword(){
        return false;
    }

    public BasePageResult<OrgEmploy> findPage(PageParam param){
        Pageable pageable = new PageRequest(param.getPage(),param.getRows());
        Page<OrgEmploy> page = this.employRepository.findAll(pageable);
        BasePageResult<OrgEmploy> pageResult = new BasePageResult<>();
        pageResult.setTotal(page.getTotalElements());
        pageResult.setRows(page.getContent());
        return pageResult;

    }
    public Boolean login(String userName,String password){
        OrgEmploy employ = this.employRepository.findEmployByEmployCodeAndUserTypeAndDelFlg(userName, LoginUserType.getUserTypeEmploy(),"0");
        if(employ != null){
            String employPassword = employ.getPassword();
            return checkEmployPassword(employPassword,password);
        }
        return Boolean.FALSE;
    }

    public boolean checkEmployPassword(String employPassword,String password){
        password = Md5Digest.digest(password);
        if(password.equalsIgnoreCase(employPassword)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    public OrgEmploy findEmployByEmployCode(String employCode){
        return this.employRepository.findEmployByEmployCodeAndUserTypeAndDelFlg(employCode,LoginUserType.getUserTypeEmploy(),"0");
    }
    public OrgEmploy findEmployByEmployCodeAndUserType(String employCode,String userType){
        return this.employRepository.findEmployByEmployCodeAndUserTypeAndDelFlg(employCode,userType,"0");
    }
}
