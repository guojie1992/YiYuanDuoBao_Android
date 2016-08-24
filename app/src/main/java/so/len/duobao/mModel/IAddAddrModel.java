package so.len.duobao.mModel;

import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/17.
 */
public interface IAddAddrModel {
    void addAddr(IHttpCompleteListener iHttpCompleteListener, String name, String phone, String district, String detail);
}
