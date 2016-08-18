package so.len.duobao.mPresenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import so.len.duobao.activity.MainActivity;
import so.len.duobao.mListener.IHttpComplete;
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

    public ForgetPresenter(IForgetView iForgetView) {
        this.iForgetModel = new ForgetModel();
        this.iForgetView = iForgetView;
    }

    public void initView(){
        iForgetView.initView();
    }

    public void getServerCode() {
        iForgetModel.getServerCode(iForgetView.getPhone());
    }

    public void doForget(final Context context) {
        if(checkRepeat()){
            iForgetModel.doForget(iForgetView.getPhone(), iForgetView.getMessageCode(), iForgetView.getPassword(), iForgetView.getRepeatPassword(), new IHttpComplete() {
                @Override
                public void loadComplete() {
                    Intent intent = new Intent();
                    intent.setClass(context, MainActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                    CommonUtils.toast(context, "重置成功");
                }

                @Override
                public void loadError(String msg) {
                    Logger.i(msg);
                }
            });
        }else {
            iForgetView.clearEditText();
        }
    }

    private boolean checkRepeat() {
        return iForgetView.getPassword().equals(iForgetView.getRepeatPassword())?true:false;
    }
}
