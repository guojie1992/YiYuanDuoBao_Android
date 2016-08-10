package so.len.duobao.iPresenter;

import com.orhanobut.logger.Logger;

import so.len.duobao.iModel.ILoginModel;
import so.len.duobao.iModel.LoginModel;
import so.len.duobao.iView.ILoginView;

/**
 * Created by Chung on 2016/8/5.
 */
public class LoginPresenter {
    private ILoginModel iLoginModel;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginModel = new LoginModel();
        this.iLoginView = iLoginView;
    }

    public void initView(){
        iLoginView.initView();
    }

    public String doLogin() {
        String loginResult = iLoginModel.doLogin(iLoginView.getPhone(), iLoginView.getPassword());
        if(loginResult.equals("1")){
            boolean saveDataResult = saveData();
            if(saveDataResult){
                Logger.i("saveData succeed");
            }
        }
        iLoginView.clearEditText();
        return loginResult;
    }

    private boolean saveData() {
        return iLoginModel.saveData(iLoginView.getPhone());
    }

}
