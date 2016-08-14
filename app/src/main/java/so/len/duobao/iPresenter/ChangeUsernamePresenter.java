package so.len.duobao.iPresenter;

import so.len.duobao.iModel.ChangeUsernameModel;
import so.len.duobao.iModel.IChangeUsernameModel;
import so.len.duobao.iView.IChangeUsernameView;

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
