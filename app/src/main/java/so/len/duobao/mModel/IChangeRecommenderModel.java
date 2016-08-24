package so.len.duobao.mModel;

import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/17.
 */
public interface IChangeRecommenderModel {
    void setRecommender(IHttpCompleteListener iHttpCompleteListener, String target);
    void getRecommender(IHttpCompleteListener iHttpCompleteListener);
}
