package so.len.duobao.mModel;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import so.len.duobao.api.SERVER;
import so.len.duobao.bean.FourBean;
import so.len.duobao.bean.OneBean;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/19.
 */
public class FourModel implements IFourModel {
    private Context context;
    private FourBean fourBean;

    public FourModel(Context context) {
        this.context = context;
        this.fourBean = new FourBean();
    }

    @Override
    public void getServerData(final IHttpCompleteListener iHttpCompleteListener) {
        VolleyHttp.getInstance().getJson(SERVER.TREASURE, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    Gson gson = new Gson();
                    fourBean = gson.fromJson(json, FourBean.class);
                    if (fourBean.getStatus().equals("1")) {
                        iHttpCompleteListener.loadComplete(fourBean.getMsg());
                    } else {
                        iHttpCompleteListener.loadError(fourBean.getMsg());
                    }
                } else {
                    Logger.e("OneModel http error");
                }
            }
        });
    }

    @Override
    public FourBean getFourBean() {
        return fourBean;
    }
}
