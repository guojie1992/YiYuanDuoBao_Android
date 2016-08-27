package so.len.duobao.mModel;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import so.len.duobao.api.SERVER;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/17.
 */
public class SettingsModel implements ISettingsModel {
    private Context context;

    public SettingsModel(Context context) {
        this.context = context;
    }

    @Override
    public void getVersion() {

    }

    @Override
    public void update() {

    }

    @Override
    public void logout(final IHttpCompleteListener iHttpCompleteListener) {
        VolleyHttp.getInstance().getJson(SERVER.LOGOUT, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if(isConnectSuccess && (!json.isEmpty())){
                    try {
                        Logger.json(json);
                        JSONObject jsonObject = new JSONObject(json);
                        if(jsonObject.getString("status").equals("1")){
                            iHttpCompleteListener.loadComplete();
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
}
