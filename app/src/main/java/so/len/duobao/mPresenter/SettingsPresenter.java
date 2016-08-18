package so.len.duobao.mPresenter;

import so.len.duobao.mModel.ISettingsModel;
import so.len.duobao.mModel.SettingsModel;
import so.len.duobao.mView.ISettingsView;

/**
 * Created by Chung on 2016/8/17.
 */
public class SettingsPresenter {
    private ISettingsModel iSettingsModel;
    private ISettingsView iSettingsView;

    public SettingsPresenter(ISettingsView iSettingsView) {
        this.iSettingsModel = new SettingsModel();
        this.iSettingsView = iSettingsView;
    }

    public void initView(){
        iSettingsView.initView();
    }
}
