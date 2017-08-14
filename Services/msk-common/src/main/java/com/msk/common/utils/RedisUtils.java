package com.msk.common.utils;

public class RedisUtils {
//    private static String BASE_REDIS_URL = ConfigManager.getRedisBaseUrl();
//    static{
//        BASE_REDIS_URL = ConfigManager.getRedisBaseUrl();
//    }
//    /**
//     * 获取value
//     * @param param
//     * @return
//     */
//    public static String get(RsRequest<String> param) {
//        return get(BASE_REDIS_URL,param);
//    }
//
//    public static String get(String redisUrl,RsRequest<String> param){
//        RsResponse<String> response = RestClientUtil.post(BASE_REDIS_URL,param,RsResponse.class);
//        if(SystemConst.RsStatus.FAIL.equalsIgnoreCase(response.getStatus())) {
//            throw new SystemException("请求Redis错误,无法返回Redis数据");
//        }
//        return response.getResult();
//    }
//
//    /**
//     * 批量获取数据
//     * @param keyList
//     * @return
//     */
//    public static Map<String, String> getValues(List<String> keyList) {
//        RsRequest<List<String>> param = new RsRequest<List<String>>();
//        param.setParam(keyList);
//        RsResponse<Map<String, String>> response = RestClientUtil.post(BASE_REDIS_URL,param,RsResponse.class);
//        if(SystemConst.RsStatus.FAIL.equalsIgnoreCase(response.getStatus())) {
//            throw new SystemException("请求Redis错误,无法返回Redis数据");
//        }
//        return response.getResult();
//
//    }
//
//    public static void set(RsRequest<RedisBean> param) {
//        RsResponse<String> response = RestClientUtil.post(BASE_REDIS_URL,param,RsResponse.class);
//        if(!SystemConst.RsStatus.SUCCESS.equalsIgnoreCase(response.getStatus())) {
//            throw new SystemException("请求Redis错误,设置Redis值");
//        }
//    }
//
//    public static void delValue(RsRequest<String> param) {
//        RsResponse<String> response = RestClientUtil.post(BASE_REDIS_URL,param,RsResponse.class);
//        if(!SystemConst.RsStatus.SUCCESS.equalsIgnoreCase(response.getStatus())) {
//            throw new SystemException("请求Redis错误,删除数据值");
//        }
//    }
//
//    public static void timerDel(RsRequest<RedisDeleteBean> param) {
//        RsResponse<String> response = RestClientUtil.post(BASE_REDIS_URL,param,RsResponse.class);
//        if(!SystemConst.RsStatus.SUCCESS.equalsIgnoreCase(response.getStatus())) {
//            throw new SystemException("请求Redis错误,定时删除");
//        }
//    }
//
//    public static class RedisDeleteBean implements Serializable{
//        private String key;
//        private String value;
//        private int second;
//
//        public String getKey() {
//            return key;
//        }
//
//        public void setKey(String key) {
//            this.key = key;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public void setValue(String value) {
//            this.value = value;
//        }
//
//        public int getSecond() {
//            return second;
//        }
//
//        public void setSecond(int second) {
//            this.second = second;
//        }
//    }
//
//    public static class RedisBean implements Serializable{
//        private String key;
//        private String value;
//
//        public String getKey() {
//            return key;
//        }
//
//        public void setKey(String key) {
//            this.key = key;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public void setValue(String value) {
//            this.value = value;
//        }
//    }



    
}
