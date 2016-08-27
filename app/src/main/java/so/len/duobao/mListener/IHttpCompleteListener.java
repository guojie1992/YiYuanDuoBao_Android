package so.len.duobao.mListener;

/**
 * Created by Chung on 2016/8/18.
 */
public interface IHttpCompleteListener {
    void loadComplete(String msg);
    void loadError(String msg);
}
