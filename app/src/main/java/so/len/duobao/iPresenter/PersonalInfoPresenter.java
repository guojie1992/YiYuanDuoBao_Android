package so.len.duobao.iPresenter;

import so.len.duobao.iModel.IPersonalInfoModel;
import so.len.duobao.iModel.PersonalInfoModel;
import so.len.duobao.iView.IPersonalInfoView;

/**
 * Created by Chung on 2016/8/12.
 */
public class PersonalInfoPresenter {
    private IPersonalInfoModel iPersonalInfoModel;
    private IPersonalInfoView iPersonalInfoView;

    public PersonalInfoPresenter(IPersonalInfoView iPersonalInfoView) {
        this.iPersonalInfoView = iPersonalInfoView;
        this.iPersonalInfoModel = new PersonalInfoModel();
    }

    public void initView(){
        iPersonalInfoView.initView();
    }
}
