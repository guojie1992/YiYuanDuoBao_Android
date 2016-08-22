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

/**
 * Created by Chung on 2016/8/17.
 */
public class ChangePasswordModel implements IChangePasswordModel {
    private Context context;

    public ChangePasswordModel(Context context) {
        this.context = context;
    }

    @Override
    public void setPassword(String oldPassword, String newPassword, final IHttpCompleteListener iHttpCompleteListener) {
        Map<String,String> args = new HashMap<>();
        args.put("username", Config.getInstance(context).getConfig("phone"));
        args.put("password", oldPassword);
        args.put("newpassword", newPassword);
        VolleyHttp.getInstance().postParamsJson(SERVER.CHANGE_PASSWORD, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getString("status").equals("1")) {
                            iHttpCompleteListener.loadComplete();
                        } else {
                            iHttpCompleteListener.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.e("ChangePasswordModel http error");
                }
            }
        }, args);
    }
}
