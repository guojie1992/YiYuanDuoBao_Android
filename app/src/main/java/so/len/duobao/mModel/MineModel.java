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
 * Created by Chung on 2016/8/18.
 */
public class MineModel implements IMineModel {
    private Context context;

    public MineModel(Context context) {
        this.context = context;
    }

    @Override
    public void getData(final IHttpComplete iHttpComplete) {
        Map<String, String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        VolleyHttp.getInstance().postParamsJson(SERVER.USER_INFO, new VolleyHttp.JsonResponseListener() {
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
                } else {
                    Logger.e("MineModel http error");
                }
            }
        }, args);
    }

    @Override
    public void sign() {

    }
}
