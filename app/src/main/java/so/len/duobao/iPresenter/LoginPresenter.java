package so.len.duobao.iPresenter;

import so.len.duobao.iModel.ILoginModel;
import so.len.duobao.iModel.LoginModel;
import so.len.duobao.iView.ILoginView;

/**
 * Created by Chung on 2016/8/5.
 */
public class LoginPresenter implements ILoginPresenter {
    private ILoginModel iLoginModel;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginModel = new LoginModel();
        this.iLoginView = iLoginView;
    }

    /**
     * Model
     */
    @Override
    public String doLogin(String phone, String password) {
        return iLoginModel.doLogin(phone, password);
    }

    @Override
    public boolean saveData(String phone, String password) {
        return iLoginModel.saveData(phone, password);
    }

    /**
     * View
     */
    @Override
    public String getPhone() {
        return iLoginView.getPhone();
    }

    @Override
    public String getPassword() {
        return iLoginView.getPassword();
    }

    @Override
    public void clearEditText() {
        iLoginView.clearEditText();
    }

    @Override
    public void setTopMenu() {
        iLoginView.setTopMenu();
    }
}
