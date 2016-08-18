package so.len.duobao.mPresenter;

import so.len.duobao.mModel.AddCardModel;
import so.len.duobao.mModel.IAddCardModel;
import so.len.duobao.mView.IAddCardView;

/**
 * Created by Chung on 2016/8/17.
 */
public class AddCardPresenter {
    private IAddCardModel iAddCardModel;
    private IAddCardView iAddCardView;

    public AddCardPresenter(IAddCardView iAddCardView) {
        this.iAddCardModel = new AddCardModel();
        this.iAddCardView = iAddCardView;
    }

    public void initView(){
        iAddCardView.initView();
    }
}
