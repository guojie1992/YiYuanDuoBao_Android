package so.len.duobao.mPresenter;

import android.app.Activity;
import android.content.Context;

import com.orhanobut.logger.Logger;

import so.len.duobao.database.Config;
import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.ChangePasswordModel;
import so.len.duobao.mModel.IChangePasswordModel;
import so.len.duobao.mView.IChangePasswordView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/17.
 */
public class ChangePasswordPresenter {
    private Context context;
    private IChangePasswordModel iChangePasswordModel;
    private IChangePasswordView iChangePasswordView;

    public ChangePasswordPresenter(IChangePasswordView iChangePasswordView, Context context) {
        this.context = context;
        this.iChangePasswordView = iChangePasswordView;
        this.iChangePasswordModel = new ChangePasswordModel(context);
    }

    public void initView(){
        iChangePasswordView.initView();
    }

    public void changePassword(String oldPassword, final String newPassword, String repeatPassword){
        if(oldPassword.equals(Config.getInstance(context).getConfig("password"))){
            if(newPassword.equals(repeatPassword)){
                iChangePasswordModel.setPassword(oldPassword, newPassword, new IHttpCompleteListener() {
                    @Override
                    public void loadComplete(String msg) {
                        Config.getInstance(context).setConfig("password", newPassword);
                        Logger.i("change username succeed");
                        CommonUtils.toast(context, "修改成功");
                        ((Activity) context).finish();
                    }

                    @Override
                    public void loadError(String msg) {
                        CommonUtils.toast(context, msg);
                    }
                });
            } else {
                CommonUtils.toast(context, "两次密码输入不一致");
            }
        } else {
            CommonUtils.toast(context, "密码错误");
        }
    }
}
