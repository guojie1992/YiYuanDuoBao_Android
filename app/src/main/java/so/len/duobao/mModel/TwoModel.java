package so.len.duobao.mModel;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import so.len.duobao.api.SERVER;
import so.len.duobao.bean.TwoBean;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/19.
 */
public class TwoModel implements ITwoModel {
    private Context context;
    private TwoBean twoBean;

    public TwoModel(Context context) {
        this.twoBean = new TwoBean();
        this.context = context;
    }

    @Override
    public void getServerData(final IHttpCompleteListener iHttpCompleteListener) {
        VolleyHttp.getInstance().getJson(SERVER.SHOP, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    Gson gson = new Gson();
                    twoBean = gson.fromJson(json, TwoBean.class);
                    if (twoBean.getStatus().equals("1")) {
                        iHttpCompleteListener.loadComplete(twoBean.getMsg());
                    } else {
                        iHttpCompleteListener.loadError(twoBean.getMsg());
                    }
                } else {
                    Logger.e("TwoModel http error");
                }
            }
        });
    }

    @Override
    public TwoBean getTwoBean() {
        return twoBean;
    }
}
