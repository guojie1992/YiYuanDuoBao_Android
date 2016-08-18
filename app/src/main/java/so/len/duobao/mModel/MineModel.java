package so.len.duobao.mModel;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;


import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.bean.MineBean;
import so.len.duobao.database.Config;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpComplete;

/**
 * Created by Chung on 2016/8/18.
 */
public class MineModel implements IMineModel {
    private Context context;
    private MineBean mineBean;

    public MineModel(Context context) {
        this.context = context;
        this.mineBean = new MineBean();
    }

    @Override
    public void getData(final IHttpComplete iHttpComplete) {
        Map<String, String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        VolleyHttp.getInstance().postParamsJson(SERVER.USER_INFO, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && json != null) {
                    Gson gson = new Gson();
                    mineBean = gson.fromJson(json, MineBean.class);
                    if (mineBean.getStatus().equals("1")) {
                        iHttpComplete.loadComplete();
                    } else {
                        iHttpComplete.loadError(mineBean.getMsg());
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

    @Override
    public MineBean getMineBean() {
        return mineBean;
    }

}
