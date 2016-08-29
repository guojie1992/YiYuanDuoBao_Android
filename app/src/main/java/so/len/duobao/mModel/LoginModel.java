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
import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/5.
 */
public class LoginModel implements ILoginModel {
    private Context context;

    public LoginModel(Context context) {
        this.context = context;
    }

    @Override
    public void doLogin(final String phone, String password, final IHttpCompleteListener iHttpCompleteListener) {
        Map<String, String> args = new HashMap<>();
        args.put("mobile", phone);
        args.put("password", password);
        VolleyHttp.getInstance().postParamsJson(SERVER.LOGIN, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getString("status").equals("1")) {
                            iHttpCompleteListener.loadComplete(jsonObject.getString("msg"));
                            Config.getInstance(context).setConfig("uid", jsonObject.getString("uid"));
                            Config.getInstance(context).setConfig("vip", jsonObject.getString("vip"));
                            Config.getInstance(context).setConfig("phone", phone);
                        } else {
                            iHttpCompleteListener.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    CommonUtils.toast(context, "请检查网络设置");
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
