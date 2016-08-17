package so.len.duobao.iPresenter;

import so.len.duobao.iModel.AddCardModel;
import so.len.duobao.iModel.IAddCardModel;
import so.len.duobao.iView.IAddCardView;

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
