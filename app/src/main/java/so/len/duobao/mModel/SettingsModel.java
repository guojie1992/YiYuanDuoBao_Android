package so.len.duobao.mModel;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/17.
 */
public class SettingsModel implements ISettingsModel {
    private Context context;
    private String path = "";

    public SettingsModel(Context context) {
        this.context = context;
    }

    @Override
    public void update(final IHttpCompleteListener iHttpCompleteListener, String currentVersion) {
        Map<String,String> args = new HashMap<>();
        args.put("is_ios", "0");
        args.put("version", currentVersion);
        VolleyHttp.getInstance().postParamsJson(SERVER.UPDATE_VERSION, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if(isConnectSuccess && (!json.isEmpty())){
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if(jsonObject.getString("status").equals("1")){
                            iHttpCompleteListener.loadComplete(jsonObject.getString("msg"));
                            path = jsonObject.getString("path");
                        } else {
                            iHttpCompleteListener.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.i("SettingModel http error!");
                }
            }
        }, args);
    }

    @Override
    public void logout(final IHttpCompleteListener iHttpCompleteListener) {
        VolleyHttp.getInstance().postJson(SERVER.LOGOUT, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if(isConnectSuccess && (!json.isEmpty())){
                    try {
//                        Logger.json(json);
                        JSONObject jsonObject = new JSONObject(json);
                        if(jsonObject.getString("status").equals("1")){
                            iHttpCompleteListener.loadComplete(jsonObject.getString("msg"));
                        } else {
                            iHttpCompleteListener.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.i("SettingModel http error!");
                }
            }
        });
    }

    @Override
    public void download() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommonUtils.openFile(CommonUtils.downFile(SERVER.DOMAIN + path, context), context);
            }
        }).start();

//        VolleyHttp.getInstance().postJson(SERVER.DOMAIN + path, new VolleyHttp.JsonResponseListener() {
//            @Override
//            public void getJson(String json, boolean isConnectSuccess) {
//                Logger.d(SERVER.DOMAIN + path);
//            }
//        });
    }
}
