package so.len.duobao.mModel;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import so.len.duobao.api.SERVER;
import so.len.duobao.bean.OneBean;
import so.len.duobao.bean.ThreeBean;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/19.
 */
public class ThreeModel implements IThreeModel {
    private Context context;
    private ThreeBean threeBean;

    public ThreeModel(Context context) {
        this.context = context;
        threeBean = new ThreeBean();
    }

    @Override
    public void getServerData(final IHttpCompleteListener iHttpCompleteListener) {
        VolleyHttp.getInstance().getJson(SERVER.LOTTREY, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    Gson gson = new Gson();
                    threeBean = gson.fromJson(json, ThreeBean.class);
                    if (threeBean.getStatus().equals("1")) {
                        iHttpCompleteListener.loadComplete();
                    } else {
                        iHttpCompleteListener.loadError(threeBean.getMsg());
                    }
                } else {
                    Logger.e("OneModel http error");
                }
            }
        });
    }

    @Override
    public ThreeBean getThreeBean() {
        return threeBean;
    }
}
