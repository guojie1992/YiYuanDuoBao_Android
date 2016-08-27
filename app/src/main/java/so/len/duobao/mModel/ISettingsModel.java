package so.len.duobao.mModel;

import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/17.
 */
public interface ISettingsModel {
    void getVersion();
    void update();
    void logout(IHttpCompleteListener iHttpCompleteListener);
}
