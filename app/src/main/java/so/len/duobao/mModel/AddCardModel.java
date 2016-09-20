package so.len.duobao.mModel;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.bean.BankListBean;
import so.len.duobao.database.Config;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/17.
 */
public class AddCardModel implements IAddCardModel {
    private Context context;
    private BankListBean bankListBean;

    public AddCardModel(Context context) {
        this.context = context;
        this.bankListBean = new BankListBean();
    }

    @Override
    public void addCard(final IHttpCompleteListener iHttpCompleteListener, String username, String bankID, String cardNum, String bankAddr) {
        Map<String,String> args = new HashMap<>();
        args.put("uid", Config.getInstance(context).getConfig("uid"));
        args.put("card_no", cardNum);
        args.put("user_name", username);
        args.put("bank_id", bankID);
        args.put("area", bankAddr);
        VolleyHttp.getInstance().postParamsJson(SERVER.ADD_BANK_CARD, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if(isConnectSuccess && (!json.isEmpty())){
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        if(jsonObject.getString("status").equals("1")){
                            iHttpCompleteListener.loadComplete("");
                        }else {
                            iHttpCompleteListener.loadError(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Logger.e("AddCardModel http error!");
                }
            }
        }, args);
    }

    @Override
    public void getBankList(final IHttpCompleteListener iHttpCompleteListener) {
        VolleyHttp.getInstance().postJson(SERVER.GET_BANK_LIST, new VolleyHttp.JsonResponseListener() {
            @Override
            public void getJson(String json, boolean isConnectSuccess) {
                if(isConnectSuccess && (!json.isEmpty())){
                    Gson gson = new Gson();
                    bankListBean = gson.fromJson(json, BankListBean.class);
                    if(bankListBean.getStatus().equals("1")){
                        iHttpCompleteListener.loadComplete(bankListBean.getMsg());
                    } else {
                        iHttpCompleteListener.loadError(bankListBean.getMsg());
                    }
                } else {
                    Logger.e("AddCardModel http error!");
                }
            }
        });
    }

    @Override
    public BankListBean getBankListBean() {
        return bankListBean;
    }
}
