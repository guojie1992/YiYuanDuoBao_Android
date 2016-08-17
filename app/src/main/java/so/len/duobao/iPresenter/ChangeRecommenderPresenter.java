package so.len.duobao.iPresenter;

import so.len.duobao.iModel.ChangeRecommenderModel;
import so.len.duobao.iModel.IChangeRecommenderModel;
import so.len.duobao.iView.IChangeRecommenderView;

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
