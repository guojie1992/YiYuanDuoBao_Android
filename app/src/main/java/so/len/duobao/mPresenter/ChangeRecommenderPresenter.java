package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.ChangeRecommenderModel;
import so.len.duobao.mModel.IChangeRecommenderModel;
import so.len.duobao.mView.IChangeRecommenderView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/17.
 */
public class ChangeRecommenderPresenter {
    private Context context;
    private IChangeRecommenderModel iChangeRecommenderModel;
    private IChangeRecommenderView iChangeRecommenderView;

    public ChangeRecommenderPresenter(IChangeRecommenderView iChangeRecommenderView, Context context) {
        this.context = context;
        this.iChangeRecommenderModel = new ChangeRecommenderModel(context);
        this.iChangeRecommenderView = iChangeRecommenderView;
    }

    public void initView(){
        iChangeRecommenderModel.getRecommender(new IHttpCompleteListener(){
            @Override
            public void loadComplete() {
                iChangeRecommenderView.initChangeableView();
            }
            @Override
            public void loadError(String pid) {
                iChangeRecommenderView.initNoChangeView(pid);
            }
        });

    }

    public void setRecommender(){
        iChangeRecommenderModel.setRecommender(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                CommonUtils.toast(context, "保存成功");
            }
            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        }, iChangeRecommenderView.getRecommenderID());
    }
}
