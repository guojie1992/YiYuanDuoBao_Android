package so.len.duobao.mModel;

import so.len.duobao.bean.FourBean;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/19.
 */
public interface IFourModel {
    void getServerData(IHttpCompleteListener iHttpCompleteListener);
    FourBean getFourBean();
}
