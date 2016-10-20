package so.len.duobao.mPresenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import so.len.duobao.activity.LoginActivity;
import so.len.duobao.mListener.IHttpCompleteListener;
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
    private Context context;
    private static String CODE;

    public RegisterPresenter(IRegisterView iRegisterView, Context context) {
        this.context = context;
        this.iRegisterModel = new RegisterModel(context);
        this.iRegisterView = iRegisterView;
    }

    public void initView() {
        iRegisterView.initView();
    }

    public void getServerCode() {
        iRegisterModel.getServerCode(iRegisterView.getPhone(), new IHttpCompleteListener() {
            @Override
            public void loadComplete(String code) {
                CODE = code;
                Logger.d(CODE);
            }

            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        });
    }

    public void doRegister() {
        if (iRegisterView.getPhone().isEmpty() || iRegisterView.getPhone().length() < 11) {
            CommonUtils.toast(context, "手机号不符合格式");
            return;
        }
        if (iRegisterView.getPassword().isEmpty()) {
            CommonUtils.toast(context, "密码不能为空");
            return;
        }
        if (iRegisterView.getMessageCode().isEmpty()) {
            CommonUtils.toast(context, "验证码不能为空");
            return;
        }
        if (!iRegisterView.getMessageCode().equals(CODE)) {
            CommonUtils.toast(context, "验证码错误");
        } else {
            String upper = "0";
            if (!TextUtils.isEmpty(iRegisterView.getUpperID())) {
                upper = iRegisterView.getUpperID();
            }
            iRegisterModel.doRegister(iRegisterView.getPhone(), iRegisterView.getMessageCode(), iRegisterView.getPassword(), upper, new IHttpCompleteListener() {
                @Override
                public void loadComplete(String msg) {
                    Intent intent = new Intent();
                    intent.setClass(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                    CommonUtils.toast(context, "注册成功");
                    Logger.i(msg);
                }

                @Override
                public void loadError(String msg) {
                    CommonUtils.toast(context, msg);
                    Logger.i(msg);
                }
            });
        }
    }

}
