package so.len.duobao.iPresenter;

import so.len.duobao.iModel.ISettingsModel;
import so.len.duobao.iModel.SettingsModel;
import so.len.duobao.iView.ISettingsView;

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
