package so.len.duobao.mPresenter;

import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import so.len.duobao.activity.LoginActivity;
import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.ForgetModel;
import so.len.duobao.mModel.IForgetModel;
import so.len.duobao.mView.IForgetView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/5.
 */
public class ForgetPresenter {
    private IForgetModel iForgetModel;
    private IForgetView iForgetView;
    private Context context;

    public ForgetPresenter(IForgetView iForgetView, Context context) {
        this.context = context;
        this.iForgetModel = new ForgetModel(context);
        this.iForgetView = iForgetView;
    }

    public void initView() {
        iForgetView.initView();
    }

    public void getServerCode() {
        iForgetModel.getServerCode(iForgetView.getPhone());
    }

    public void doForget() {
        if (checkRepeat()) {
            if (iForgetView.getPhone().isEmpty() || iForgetView.getPassword().isEmpty() || iForgetView.getMessageCode().isEmpty()) {
                CommonUtils.toast(context, "请认真填写");
            } else {
                iForgetModel.doForget(iForgetView.getPhone(), iForgetView.getMessageCode(), iForgetView.getPassword(), iForgetView.getRepeatPassword(), new IHttpCompleteListener() {
                    @Override
                    public void loadComplete() {
                        Intent intent = new Intent();
                        intent.setClass(context, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(intent);
                        CommonUtils.toast(context, "重置成功");
                    }
                    @Override
                    public void loadError(String msg) {
                        Logger.i(msg);
                    }
                });
            }
        } else {
            iForgetView.clearEditText();
        }
    }

    private boolean checkRepeat() {
        return iForgetView.getPassword().equals(iForgetView.getRepeatPassword()) ? true : false;
    }
}
