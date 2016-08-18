package so.len.duobao.mModel;

import so.len.duobao.mListener.IHttpComplete;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IForgetModel {
    void getServerCode(String phone);
    void doForget(String phone, String code, String password, String repeatPassword, IHttpComplete iHttpComplete);
}
