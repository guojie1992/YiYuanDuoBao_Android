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

/**
 * Created by Chung on 2016/8/5.
 */
public class ForgetModel implements IForgetModel {
    private Context context;

    public ForgetModel(Context context) {
        this.context = context;
    }

    @Override
    public void getServerCode(String phone, final IHttpCompleteListener iHttpCompleteListener) {
        Map<String,String> args = new HashMap<>();
        args.put("mobile", phone);
        VolleyHttp.getInstance().postParamsJson(SERVER.CODE, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if(isConnectSuccess && (!json.isEmpty())){
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if(jsonObject.getString("status").equals("1")){
                            iHttpCompleteListener.loadComplete(jsonObject.getString("code"));
                        }
                        if(jsonObject.getString("status").equals("0")){
                            iHttpCompleteListener.loadError(jsonObject.getString("code"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.i("RegisterModel http error!");
                }
            }
        }, args);
    }

    @Override
    public void doForget(String phone, String code, String password, String repeatPassword, final IHttpCompleteListener iHttpCompleteListener) {
        Map<String, String> args = new HashMap<>();
        args.put("mobile", phone);
        args.put("password", password);
        VolleyHttp.getInstance().postParamsJson(SERVER.FORGET_PASSWORD, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getString("status").equals("1")) {
                            iHttpCompleteListener.loadComplete(jsonObject.getString("msg"));
                        } else {
                            iHttpCompleteListener.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.e("ForgetModel http error");
                }
            }
        }, args);
    }

}
