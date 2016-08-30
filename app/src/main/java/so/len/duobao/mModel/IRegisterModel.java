package so.len.duobao.mModel;

import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IRegisterModel {
    void getServerCode(String phone, IHttpCompleteListener iHttpCompleteListener);
    void doRegister(String phone, String code, String password, IHttpCompleteListener iHttpCompleteListener);
}
