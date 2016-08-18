package so.len.duobao.mPresenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import so.len.duobao.activity.MainActivity;
import so.len.duobao.mListener.IHttpComplete;
import so.len.duobao.mModel.ILoginModel;
import so.len.duobao.mModel.LoginModel;
import so.len.duobao.mView.ILoginView;
import so.len.duobao.utils.CommonUtils;

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

    public void doLogin(final Context context) {
        iLoginModel.doLogin(iLoginView.getPhone(), iLoginView.getPassword(), new IHttpComplete() {
            @Override
            public void loadComplete() {
                boolean saveDataResult = saveData();
                if(saveDataResult){
                    Logger.i("saveData succeed");
                }
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
                CommonUtils.toast(context, "登陆成功");
            }

            @Override
            public void loadError(String msg) {
                Logger.i(msg);
            }
        });
        iLoginView.clearEditText();
    }

    private boolean saveData() {
        return iLoginModel.saveData(iLoginView.getPhone());
    }

}