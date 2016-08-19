package so.len.duobao.mModel;

import so.len.duobao.bean.TwoBean;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/19.
 */
public interface ITwoModel {
    void getServerData(IHttpCompleteListener iHttpCompleteListener);
    TwoBean getTwoBean();
}
