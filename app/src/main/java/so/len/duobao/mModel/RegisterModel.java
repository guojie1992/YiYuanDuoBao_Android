package so.len.duobao.mModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpComplete;

/**
 * Created by Chung on 2016/8/5.
 */
public class RegisterModel implements IRegisterModel {
    private String msg;
    /**
     * 获取验证码
     *
     * @param phone 传送手机号
     * @return 返回是否已发送短信
     */
    @Override
    public void getServerCode(String phone) {
    }

    /**
     * 提交注册信息
     *
     * @param phone    传送手机号
     * @param password 传送密码
     * @return 返回
     */
    @Override
    public void doRegister(String phone, String code, String password, final IHttpComplete iHttpComplete) {
        Map<String, String> args = new HashMap<>();
        args.put("mobile", phone);
        args.put("password", password);

        VolleyHttp.getInstance().postParamsJson(SERVER.REGISTER, new VolleyHttp.JsonResponseListener() {
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
                }
            }
        }, args);
    }

}