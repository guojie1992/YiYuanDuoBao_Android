package so.len.duobao.mModel;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.bean.PersonalBean;
import so.len.duobao.database.Config;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/12.
 */
public class PersonalInfoModel implements IPersonalInfoModel {
    private Context context;
    private PersonalBean personalBean;
    public PersonalInfoModel(Context context) {
        this.context = context;
        personalBean = new PersonalBean();
    }

    @Override
    public void getServerData(final IHttpCompleteListener iHttpCompleteListener) {
        Map<String,String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        VolleyHttp.getInstance().postParamsJson(SERVER.PERSONAL_INFO, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if (isConnectSuccess && (!json.isEmpty())) {
                    Gson gson = new Gson();
                    personalBean = gson.fromJson(json, PersonalBean.class);
                    if (personalBean.getStatus().equals("1")) {
                        iHttpCompleteListener.loadComplete(personalBean.getMsg());
                    } else {
                        iHttpCompleteListener.loadError(personalBean.getMsg());
                    }
                } else {
                    Logger.e("FiveModel http load error");
                }
            }
        }, args);
    }

    @Override
    public PersonalBean getPersonalBean() {
        return personalBean;
    }
}
