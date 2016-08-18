package so.len.duobao.mPresenter;

import so.len.duobao.mModel.ChangeRecommenderModel;
import so.len.duobao.mModel.IChangeRecommenderModel;
import so.len.duobao.mView.IChangeRecommenderView;

/**
 * Created by Chung on 2016/8/17.
 */
public class ChangeRecommenderPresenter {
    private IChangeRecommenderModel iChangeRecommenderModel;
    private IChangeRecommenderView iChangeRecommenderView;

    public ChangeRecommenderPresenter(IChangeRecommenderView iChangeRecommenderView) {
        this.iChangeRecommenderModel = new ChangeRecommenderModel();
        this.iChangeRecommenderView = iChangeRecommenderView;
    }

    public void initView(){
        iChangeRecommenderView.initView();
    }
}
