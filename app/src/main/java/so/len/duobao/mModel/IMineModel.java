package so.len.duobao.mModel;

import so.len.duobao.bean.MineBean;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/18.
 */
public interface IMineModel {
    void getServerData(IHttpCompleteListener iHttpCompleteListener);
    void sign();
    MineBean getMineBean();
}
