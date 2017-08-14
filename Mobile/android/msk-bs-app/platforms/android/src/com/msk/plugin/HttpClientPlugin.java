package com.msk.plugin;

import android.util.Log;
import com.msk.utils.HttpClientUtils;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HttpClientPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action == null || action.trim().equals("")){
            return false;
        }
        JSONObject urlJson = args.getJSONObject(1);
        String url = urlJson.getString("url");
        JSONObject paramJson = args.getJSONObject(0);
        JSONObject resultJson = null;
        if(action.equalsIgnoreCase("POST")){
            resultJson = HttpClientUtils.post(url,paramJson);
        }

        Log.i("返回值:",resultJson.toString());
        int statusCode = resultJson.getInt("statusCode");
        if(statusCode != 200){
            callbackContext.error(resultJson);
        }else{
            callbackContext.success(resultJson);
        }
        return true;
    }
}
