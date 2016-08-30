package so.len.duobao.mPresenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import so.len.duobao.activity.MainActivity;
import so.len.duobao.mListener.IHttpCompleteListener;
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
    private Context context;

    public LoginPresenter(ILoginView iLoginView, Context context) {
        this.context = context;
        this.iLoginModel = new LoginModel(context);
        this.iLoginView = iLoginView;
    }

    public void initView() {
        iLoginView.initView();
    }

    public void doLogin() {
        if (iLoginView.getPhone().isEmpty() || iLoginView.getPhone().length()<11 || iLoginView.getPassword().isEmpty()) {
            CommonUtils.toast(context, "请认真填写");
        } else {
            iLoginModel.doLogin(iLoginView.getPhone(), iLoginView.getPassword(), new IHttpCompleteListener() {
                @Override
                public void loadComplete(String msg) {
                    boolean saveDataResult = saveData();
                    if (saveDataResult) {
                        Logger.i("saveData succeed");
                    }
                    Intent intent = new Intent();
                    intent.setClass(context, MainActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
//                    CommonUtils.toast(context, "登陆成功");
                }

                @Override
                public void loadError(String msg) {
                    Logger.i(msg);
                    CommonUtils.toast(context, msg);
                }
            });
        }
    }

    private boolean saveData() {
        return iLoginModel.saveData(iLoginView.getPhone());
    }

}
