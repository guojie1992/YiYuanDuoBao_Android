package so.len.duobao.mPresenter;

import so.len.duobao.mModel.IPersonalInfoModel;
import so.len.duobao.mModel.PersonalInfoModel;
import so.len.duobao.mView.IPersonalInfoView;

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
