package com.msk.auth.utils;

import com.msk.org.bean.SessionKey;
import com.msk.org.entity.OrgRoleAuth;
import com.msk.org.entity.OrgRoleAuthPrimaryKey;
import com.msk.org.entity.OrgSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class AuthorityUtils {
    public static Iterator<OrgSystem> createSystemMenu(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<OrgSystem> systemMenu = (List<OrgSystem>) session.getAttribute(SessionKey.getSystemMenu());
        List<OrgRoleAuth> roleAuthList = (List<OrgRoleAuth>) session.getAttribute(SessionKey.getUserRoleAuth());
        Iterator<OrgSystem> systemIterator = createAuthMenuIterator(systemMenu, roleAuthList);
        return systemIterator;
    }

    private static Iterator<OrgSystem> createAuthMenuIterator(List<OrgSystem> systemMenu,
        List<OrgRoleAuth> roleAuthList) {
        Iterator<OrgSystem> systemIterator = systemMenu.iterator();
        List<OrgRoleAuth> pageAuthRoleList = createPageAuthList(roleAuthList);
        while (systemIterator.hasNext()) {
            OrgSystem system = systemIterator.next();
            String systemCode = system.getSysCode();
            boolean isRoleAuthSystem = false;
            for (OrgRoleAuth roleAuth : pageAuthRoleList) {
                OrgRoleAuthPrimaryKey roleAuthPrimaryKey = roleAuth.getRoleAuthPrimaryKey();
                String roleAuthSystemCode = roleAuthPrimaryKey.getSysCode();
                if (!roleAuthSystemCode.contains(systemCode)) {
                    continue;
                }
                isRoleAuthSystem = true;
                String pageCode = roleAuthPrimaryKey.getPageCode();
                if (pageCode.equals("ALL")) {
                    break;
                }
            }
            if (!isRoleAuthSystem) {
                systemIterator.remove();
            }
        }
        return systemIterator;
    }

    private static List<OrgRoleAuth> createPageAuthList(List<OrgRoleAuth> roleAuthList) {
        List<OrgRoleAuth> pageAuthRoleList = new ArrayList<>();
        for (OrgRoleAuth roleAuth : roleAuthList) {
            OrgRoleAuthPrimaryKey roleAuthPrimaryKey = roleAuth.getRoleAuthPrimaryKey();
            String buttonId = roleAuthPrimaryKey.getActionCode();
            if (buttonId != null && !("".equals(buttonId.trim()))) {
                continue;
            }
            pageAuthRoleList.add(roleAuth);
        }
        return pageAuthRoleList;
    }

    public static boolean checkButtonAuth(HttpServletRequest request, String pageCode, String actionCode) {
        HttpSession session = request.getSession(false);
        List<OrgRoleAuth> roleAuthList = (List<OrgRoleAuth>) session.getAttribute(SessionKey.getUserRoleAuth());
        List<OrgRoleAuth> buttonAuthList = createButtonAuthList(roleAuthList);
        for (OrgRoleAuth buttonAuth : buttonAuthList) {
            OrgRoleAuthPrimaryKey roleAuthPrimaryKey = buttonAuth.getRoleAuthPrimaryKey();
            String roleAuthPageCode = roleAuthPrimaryKey.getPageCode();
            String roleAuthButtonCode = roleAuthPrimaryKey.getActionCode();
            if ("ALL".equalsIgnoreCase(roleAuthPageCode) || "ALL".equalsIgnoreCase(roleAuthButtonCode)) {
                return true;
            }
            if (roleAuthPageCode.equals(pageCode) && roleAuthButtonCode.equals(actionCode)) {
                return true;
            }
        }
        return false;
    }

    private static List<OrgRoleAuth> createButtonAuthList(List<OrgRoleAuth> roleAuthList) {
        List<OrgRoleAuth> buttonAuthList = new ArrayList<>();
        if (roleAuthList == null || roleAuthList.size() == 0) {
            return buttonAuthList;
        }
        for (OrgRoleAuth roleAuth : roleAuthList) {
            OrgRoleAuthPrimaryKey roleAuthPrimaryKey = roleAuth.getRoleAuthPrimaryKey();
            String buttonId = roleAuthPrimaryKey.getActionCode();
            if ("MENU".equals(buttonId.trim())) {
                continue;
            }
            buttonAuthList.add(roleAuth);
        }
        return buttonAuthList;
    }

}
