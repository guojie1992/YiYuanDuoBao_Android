package so.len.duobao.mModel;

import so.len.duobao.bean.OneBean;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/18.
 */
public interface IOneModel {
    void getServerData(IHttpCompleteListener iHttpCompleteListener);
    OneBean getOneBean();
}
