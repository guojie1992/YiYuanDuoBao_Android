package so.len.duobao.iPresenter;

import so.len.duobao.activity.ChangePasswordActivity;
import so.len.duobao.iModel.IChangePasswordModel;
import so.len.duobao.iView.IChangePasswordView;

/**
 * Created by Chung on 2016/8/17.
 */
public class ChangePasswordPresenter {
    private IChangePasswordModel iChangePasswordModel;
    private IChangePasswordView iChangePasswordView;

    public ChangePasswordPresenter(IChangePasswordView iChangePasswordView) {
        this.iChangePasswordView = new ChangePasswordActivity();
        this.iChangePasswordView = iChangePasswordView;
    }

    public void initView(){
        iChangePasswordView.initView();
    }
}
