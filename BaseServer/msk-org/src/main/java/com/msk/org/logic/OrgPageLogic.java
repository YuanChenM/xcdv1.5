package com.msk.org.logic;

import com.msk.org.entity.OrgPage;
import com.msk.org.repository.OrgPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgPageLogic {
    @Autowired
    private OrgPageRepository pageRepository;
    public List<OrgPage> findPageListBySysCode(String sysCode){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC,"sort"));
        return this.pageRepository.findPageListBySysCodeAndDelFlgAndIsMenu(sysCode,"0","1",sort);
    }
}
