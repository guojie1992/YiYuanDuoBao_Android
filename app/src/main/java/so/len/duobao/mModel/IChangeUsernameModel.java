package so.len.duobao.mModel;

import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/14.
 */
public interface IChangeUsernameModel {
    void setUsername(String username, IHttpCompleteListener iHttpCompleteListener);
}
