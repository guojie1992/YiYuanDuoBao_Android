package so.len.duobao.mModel;

import so.len.duobao.bean.FiveBean;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/19.
 */
public interface IFiveModel {
    void getServerData(IHttpCompleteListener iHttpCompleteListener);
    FiveBean getFiveBean();
}
