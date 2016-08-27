package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.ISettingsModel;
import so.len.duobao.mModel.SettingsModel;
import so.len.duobao.mView.ISettingsView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/17.
 */
public class SettingsPresenter {
    private Context context;
    private ISettingsModel iSettingsModel;
    private ISettingsView iSettingsView;

    public SettingsPresenter(ISettingsView iSettingsView, Context context) {
        this.context = context;
        this.iSettingsModel = new SettingsModel(context);
        this.iSettingsView = iSettingsView;
    }

    public void initView(){
        iSettingsView.initView();
    }

    public void logout() {
        iSettingsModel.logout(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                CommonUtils.toast(context, "退出成功");
            }
            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        });
    }
}
