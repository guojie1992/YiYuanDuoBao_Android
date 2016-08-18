package so.len.duobao.mModel;

import so.len.duobao.mListener.IHttpComplete;

/**
 * Created by Chung on 2016/8/5.
 */
public interface ILoginModel {
    void doLogin(String phone, String password, IHttpComplete iHttpComplete);
    boolean saveData(String phone);
}
