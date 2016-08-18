package so.len.duobao.mModel;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.database.Config;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpComplete;

/**
 * Created by Chung on 2016/8/5.
 */
public class LoginModel implements ILoginModel {
    private Context context;

    public LoginModel(Context context) {
        this.context = context;
    }

    @Override
    public void doLogin(String phone, String password, final IHttpComplete iHttpComplete) {
        Map<String, String> args = new HashMap<>();
        args.put("mobile", phone);
        args.put("password", password);
        VolleyHttp.getInstance().postParamsJson(SERVER.LOGIN, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getString("status").equals("1")) {
                            iHttpComplete.loadComplete();
                            Config.getInstance(context).setConfig("uid", jsonObject.getString("uid"));
                        } else {
                            iHttpComplete.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.e("LoginModel http error");
                }
            }
        }, args);
    }

    @Override
    public boolean saveData(String phone) {
        return false;
    }
}
