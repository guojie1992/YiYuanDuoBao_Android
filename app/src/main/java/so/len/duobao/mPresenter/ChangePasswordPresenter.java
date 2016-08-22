package so.len.duobao.mPresenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

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

    public void changePassword(String oldPassword, String newPassword, String repeatPassword){
        if(newPassword.equals(repeatPassword)){
            iChangePasswordModel.setPassword(oldPassword, newPassword, new IHttpCompleteListener() {
                @Override
                public void loadComplete() {
                    Logger.i("change username succeed");
                }

                @Override
                public void loadError(String msg) {
                    CommonUtils.toast(context, msg);
                }
            });
        } else {
            CommonUtils.toast(context, "两次密码输入不一致");
        }

    }
}
