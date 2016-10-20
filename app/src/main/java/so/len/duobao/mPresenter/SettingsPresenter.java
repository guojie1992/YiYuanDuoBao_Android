package so.len.duobao.mPresenter;

import android.content.Context;
import android.view.View;

import com.kaopiz.kprogresshud.KProgressHUD;

import so.len.duobao.customView.iOSAlertDialog;
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
    private KProgressHUD kProgressHUD;

    public SettingsPresenter(ISettingsView iSettingsView, Context context) {
        this.context = context;
        this.iSettingsModel = new SettingsModel(context);
        this.iSettingsView = iSettingsView;
        this.kProgressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("检测更新中...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
    }

    public void initView(){
        iSettingsView.initView();
    }

    public void logout() {
        iSettingsModel.logout(new IHttpCompleteListener() {
            @Override
            public void loadComplete(String msg) {
                CommonUtils.toast(context, "退出成功");
            }
            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        });
    }

    public void update(final String currentVersion) {
        kProgressHUD.show();
        iSettingsModel.update(new IHttpCompleteListener() {
            @Override
            public void loadComplete(String newVersion) {
                String content = "　　当前版本：" + currentVersion + "，发现新版本" + newVersion + "，是否下载最新版本安装包？";
                kProgressHUD.dismiss();
                new iOSAlertDialog(context).builder()
                        .setTitle("温馨提示")
                        .setMsg(content)
                        .setCancelable(false)
                        .setPositiveButton("更新", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                iSettingsModel.download();
                            }
                        })
                        .setNegativeButton("暂不更新", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
            }
            @Override
            public void loadError(String msg) {
                String content = "当前已是最新版本";
                kProgressHUD.dismiss();
                new iOSAlertDialog(context).builder()
                        .setTitle("温馨提示")
                        .setMsg(content)
                        .setShowOne(true)
                        .setCancelable(false)
                        .show();
            }
        }, currentVersion);
    }
}
