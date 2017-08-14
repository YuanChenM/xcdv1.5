package com.msk.org.utils;

/**
 * ORG_SYS数据管理类（单实例）
 *
 * Created by mabo on 2016/3/26.
 */
public class OrgSysManager {

//    /** 单实例 */
//    private static OrgSysManager instance = null;
//    /** key:sysCode, value:orgSys object */
//    private Map<String, OrgSys> codeMap;
//    /**
//     * logger
//     */
//    private Logger logger = LoggerFactory.getLogger(OrgSysManager.class);
//
//    /**
//     * 私有构造方法
//     */
//    private OrgSysManager() {
//        this.codeMap = new HashMap<String, OrgSys>();
//    }
//
//    /**
//     * 获取单实例对象。
//     *
//     * @return 单实例对象
//     */
//    public static OrgSysManager getInstance() {
//        if (instance == null) {
//            instance = new OrgSysManager();
//        }
//        return instance;
//    }
//
//    /**
//     * 初始化系统数据。
//     *
//     * @param orgSyses
//     */
//    public void initOrgSyses(List<OrgSys> orgSyses) {
//        if (this.codeMap == null) {
//            this.codeMap = new HashMap<String, OrgSys>();
//        }
//        if (orgSyses != null && !orgSyses.isEmpty()) {
//            for (OrgSys orgSys : orgSyses) {
//                if (orgSys != null && !StringUtil.isEmpty(orgSys.getSysCode())) {
//                    codeMap.put(orgSys.getSysCode(), orgSys);
//                }
//            }
//        }
//    }
//
//    /**
//     * 根据系统编码获取系统名称。
//     *
//     * @param sysCode 系统编码
//     * @return 系统名称
//     */
//    public String getSysNameByCode(String sysCode) {
//        String sysName = null;
//
//        if (this.codeMap != null) {
//            OrgSys orgSys = this.codeMap.get(sysCode);
//            if (orgSys != null) {
//                sysName = orgSys.getSysName();
//            }
//        }
//
//        return sysName;
//    }
}
