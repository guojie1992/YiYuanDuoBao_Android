package so.len.duobao.mModel;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import so.len.duobao.api.SERVER;
import so.len.duobao.bean.OneBean;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/18.
 */
public class OneModel implements IOneModel {
    private Context context;
    private OneBean oneBean;

    public OneModel(Context context) {
        this.context = context;
        oneBean = new OneBean();
    }

    @Override
    public void getServerData(final IHttpCompleteListener iHttpCompleteListener) {
        VolleyHttp.getInstance().postJson(SERVER.HOME_PAGE, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    Gson gson = new Gson();
                    oneBean = gson.fromJson(json, OneBean.class);
                    if (oneBean.getStatus().equals("1")) {
                        iHttpCompleteListener.loadComplete(oneBean.getMsg());
                    } else {
                        iHttpCompleteListener.loadError(oneBean.getMsg());
                    }
                } else {
                    Logger.e("OneModel http error");
                }
            }
        });
    }

    @Override
    public OneBean getOneBean() {
        return oneBean;
    }
}
