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
public class ChangeRecommenderModel implements IChangeRecommenderModel {
    private Context context;

    public ChangeRecommenderModel(Context context) {
        this.context = context;
    }

    @Override
    public void setRecommender(final IHttpCompleteListener iHttpCompleteListener, String target) {
        Map<String,String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        args.put("target", target);
        VolleyHttp.getInstance().postParamsJson(SERVER.CHANGE_MY_RECOMMENDER, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if(isConnectSuccess && (!json.isEmpty())){
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getString("status").equals("1")){
                            iHttpCompleteListener.loadComplete(jsonObject.getString("msg"));
                        } else {
                            iHttpCompleteListener.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.e("ChangeReommenderModel http error!");
                }
            }
        }, args);
    }

    @Override
    public void getRecommender(final IHttpCompleteListener iHttpCompleteListener) {
        Map<String,String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        VolleyHttp.getInstance().postParamsJson(SERVER.MY_RECOMMENDER, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if(isConnectSuccess && (!json.isEmpty())){
                    Logger.json(json);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if (jsonObject.getString("status").equals("1")){
                            iHttpCompleteListener.loadComplete(jsonObject.getString("msg"));
                        }
                        if (jsonObject.getString("status").equals("0")) {
                            iHttpCompleteListener.loadError(jsonObject.getString("pid"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.e("ChangeReommenderModel http error!");
                }
            }
        }, args);
    }
}
