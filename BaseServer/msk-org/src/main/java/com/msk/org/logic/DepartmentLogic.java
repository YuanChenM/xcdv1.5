package com.msk.org.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.msk.org.bean.param.PageParam;
import com.msk.org.bean.result.BasePageResult;
import com.msk.org.entity.OrgDepartment;
import com.msk.org.repository.OrgDepartmentRepository;

import java.util.List;

@Service
public class DepartmentLogic {
    @Autowired
    private OrgDepartmentRepository departmentRepository;

    public BasePageResult<OrgDepartment> findPage(PageParam param){
        Pageable pageable = new PageRequest(param.getPage(),param.getRows());
        Page<OrgDepartment> page = this.departmentRepository.findAll(pageable);
        BasePageResult<OrgDepartment> pageResult = new BasePageResult<>();
        pageResult.setTotal(page.getTotalElements());
        pageResult.setRows(page.getContent());
        return pageResult;
    }

    public List<OrgDepartment> findEmployDepartment(String employCode){
        return this.departmentRepository.findEmployDepartment(employCode);
    }

}
