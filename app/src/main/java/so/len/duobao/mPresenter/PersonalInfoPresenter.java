package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.IPersonalInfoModel;
import so.len.duobao.mModel.PersonalInfoModel;
import so.len.duobao.mView.IPersonalInfoView;

/**
 * Created by Chung on 2016/8/12.
 */
public class PersonalInfoPresenter {
    private IPersonalInfoModel iPersonalInfoModel;
    private IPersonalInfoView iPersonalInfoView;
    private Context context;

    public PersonalInfoPresenter(IPersonalInfoView iPersonalInfoView, Context context) {
        this.context = context;
        this.iPersonalInfoView = iPersonalInfoView;
        this.iPersonalInfoModel = new PersonalInfoModel(context);
    }

    public void initView(){
        iPersonalInfoModel.getServerData(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                iPersonalInfoView.initView(iPersonalInfoModel.getPersonalBean());
            }

            @Override
            public void loadError(String msg) {

            }
        });

    }
}
