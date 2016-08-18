package so.len.duobao.mModel;

import so.len.duobao.bean.MineBean;
import so.len.duobao.mListener.IHttpComplete;

/**
 * Created by Chung on 2016/8/18.
 */
public interface IMineModel {
    void getData(IHttpComplete iHttpComplete);
    void sign();
    MineBean getMineBean();
}
