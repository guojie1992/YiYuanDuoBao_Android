package so.len.duobao.mModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpComplete;

/**
 * Created by Chung on 2016/8/5.
 */
public class ForgetModel implements IForgetModel {
    @Override
    public void getServerCode(String phone) {
    }

    @Override
    public void doForget(String phone, String code, String password, String repeatPassword, final IHttpComplete iHttpComplete) {
        Map<String,String> args = new HashMap<>();
        args.put("mobile", phone);
        args.put("password", password);
        VolleyHttp.getInstance().postParamsJson(SERVER.FORGET_PASSWORD, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getString("status").equals("1")) {
                            iHttpComplete.loadComplete();
                        } else {
                            iHttpComplete.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, args);
    }

}
