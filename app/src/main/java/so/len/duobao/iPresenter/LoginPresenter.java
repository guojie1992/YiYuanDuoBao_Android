package so.len.duobao.iPresenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.squareup.otto.Subscribe;

import so.len.duobao.activity.LoginActivity;
import so.len.duobao.activity.MainActivity;
import so.len.duobao.bean.SERVER_INFO;
import so.len.duobao.iModel.ILoginModel;
import so.len.duobao.iModel.LoginModel;
import so.len.duobao.iView.ILoginView;
import so.len.duobao.otto.AppBus;

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

    public String doLogin(Context context) {
        String loginResult = iLoginModel.doLogin(iLoginView.getPhone(), iLoginView.getPassword());

        if(loginResult.equals("1")){
            boolean saveDataResult = saveData();
            if(saveDataResult){
                Logger.i("saveData succeed");
            }
            Intent intent2 = new Intent();
            intent2.setClass(context, MainActivity.class);
            context.startActivity(intent2);
            ((Activity) context).finish();
        }
        iLoginView.clearEditText();
        return loginResult;
    }

    private boolean saveData() {
        return iLoginModel.saveData(iLoginView.getPhone());
    }

}
