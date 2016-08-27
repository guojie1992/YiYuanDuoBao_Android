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
public class AddAddrModel implements IAddAddrModel {
    private Context context;

    public AddAddrModel(Context context) {
        this.context = context;
    }

    @Override
    public void addAddr(final IHttpCompleteListener iHttpCompleteListener, String name, String phone, String district, String detail) {
        Map<String,String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        args.put("consignee", name);
        args.put("phone", phone);
        args.put("area", district);
        args.put("site", detail);
        VolleyHttp.getInstance().postParamsJson(SERVER.ADD_ADDR, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())){
                    try {
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
                    Logger.e("AddAddrModel http error!");
                }
            }
        }, args);
    }
}
