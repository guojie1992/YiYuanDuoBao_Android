package so.len.duobao.mPresenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import so.len.duobao.activity.MainActivity;
import so.len.duobao.mListener.IHttpComplete;
import so.len.duobao.mModel.IRegisterModel;
import so.len.duobao.mModel.RegisterModel;
import so.len.duobao.mView.IRegisterView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/5.
 */
public class RegisterPresenter {
    private IRegisterModel iRegisterModel;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterModel = new RegisterModel();
        this.iRegisterView = iRegisterView;
    }

    public void initView() {
        iRegisterView.initView();
    }

    public void getServerCode() {
        iRegisterModel.getServerCode(iRegisterView.getPhone());
    }

    public void doRegister(final Context context) {
        iRegisterModel.doRegister(iRegisterView.getPhone(), iRegisterView.getMessageCode(), iRegisterView.getPassword(), new IHttpComplete() {
            @Override
            public void loadComplete() {
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
                CommonUtils.toast(context, "注册成功");
            }

            @Override
            public void loadError(String msg) {
                Logger.i(msg);
            }
        });
        iRegisterView.clearEditText();
    }

}
