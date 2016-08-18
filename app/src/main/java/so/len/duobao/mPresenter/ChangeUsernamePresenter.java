package so.len.duobao.mPresenter;

import so.len.duobao.mModel.ChangeUsernameModel;
import so.len.duobao.mModel.IChangeUsernameModel;
import so.len.duobao.mView.IChangeUsernameView;

/**
 * Created by Chung on 2016/8/14.
 */
public class ChangeUsernamePresenter {
    private IChangeUsernameModel iChangeUsernameModel;
    private IChangeUsernameView iChangeUsernameView;

    public ChangeUsernamePresenter(IChangeUsernameView iChangeUsernameView) {
        this.iChangeUsernameModel = new ChangeUsernameModel();
        this.iChangeUsernameView = iChangeUsernameView;
    }
    public void initView(){
        iChangeUsernameView.initView();
    }
}
