package so.len.duobao.mModel;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.bean.FiveBean;
import so.len.duobao.database.Config;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/19.
 */
public class FiveModel implements IFiveModel {
    private Context context;
    private FiveBean fiveBean;

    public FiveModel(Context context) {
        this.context = context;
        this.fiveBean = new FiveBean();
    }

    @Override
    public void getServerData(final IHttpCompleteListener iHttpCompleteListener) {
        Map<String, String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        args.put("is_ios", "0");
        VolleyHttp.getInstance().postParamsJson(SERVER.GIFT, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    Gson gson = new Gson();
                    fiveBean = gson.fromJson(json, FiveBean.class);
                    if (fiveBean.getStatus().equals("1")) {
                        iHttpCompleteListener.loadComplete();
                    } else {
                        iHttpCompleteListener.loadError(fiveBean.getMsg());
                    }
                } else {
                    Logger.e("FiveModel http load error");
                }
            }
        }, args);
    }

    @Override
    public FiveBean getFiveBean() {
        return fiveBean;
    }

    @Override
    public void go(final IHttpCompleteListener iHttpCompleteListener) {
        Map<String,String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        args.put("rob_list_id", fiveBean.getRob_list().getRob_list_id());
        VolleyHttp.getInstance().postParamsJson(SERVER.GO, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
//                        if (jsonObject.getString("status").equals("1")) {
//                            iHttpCompleteListener.loadComplete();
//                        } else {
                            iHttpCompleteListener.loadError(jsonObject.getString("msg"));
//                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    CommonUtils.toast(context, "请检查网络设置");
                    Logger.e("FiveModel http error");
                }
            }
        }, args);
    }
}
