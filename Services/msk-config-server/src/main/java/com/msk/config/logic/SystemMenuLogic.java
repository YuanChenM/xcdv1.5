package com.msk.config.logic;

import com.msk.config.bean.TreeBean;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shi_yuxi on 2016/6/27.
 */

@Service
public class SystemMenuLogic{
    public void getSystemMenu(String systemCodeId, List<TreeBean> listAll, TreeBean systemMenu){
        if(!StringUtils.isEmpty(systemCodeId)){
            List<TreeBean> list = new ArrayList<TreeBean>();
            for(TreeBean treeBean : listAll){
                if(systemCodeId.equals(treeBean.getpId())){
                    TreeBean tb = new TreeBean();
                    BeanUtils.copyProperties(treeBean, tb);
                    tb.setpId(null);
                    tb.setId(null);
                    tb.setMap(null);
                    tb.setType(null);
                    list.add(tb);
                    getSystemMenu(treeBean.getId(), listAll, tb);
                }
            }
            systemMenu.setList(list);
        }
    }
}
