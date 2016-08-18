package so.len.duobao.mModel;

import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IForgetModel {
    void getServerCode(String phone);
    void doForget(String phone, String code, String password, String repeatPassword, IHttpCompleteListener iHttpCompleteListener);
}
