package so.len.duobao.mModel;

import so.len.duobao.bean.ThreeBean;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/19.
 */
public interface IThreeModel {
    void getServerData(IHttpCompleteListener iHttpCompleteListener);
    ThreeBean getThreeBean();
}
