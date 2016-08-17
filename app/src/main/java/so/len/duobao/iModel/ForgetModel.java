package so.len.duobao.iModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.http.VolleyHttp;

/**
 * Created by Chung on 2016/8/5.
 */
public class ForgetModel implements IForgetModel {
    private String msg;

    @Override
    public String getServerCode(String phone) {
        return "获取";
    }

    @Override
    public String doForget(String phone, String code, String password, String repeatPassword) {
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
                            msg = "重置成功";
                        } else {
                            msg = jsonObject.getString("msg");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, args);
        return msg;
    }

}
