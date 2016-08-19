package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.FiveModel;
import so.len.duobao.mModel.IFiveModel;
import so.len.duobao.mView.IFiveView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/9.
 */
public class FivePresenter {
    private Context context;
    private IFiveModel iFiveModel;
    private IFiveView iFiveView;

    public FivePresenter(IFiveView iFiveView, Context context) {
        this.context = context;
        this.iFiveView = iFiveView;
        this.iFiveModel = new FiveModel(context);
    }

    public void initView(){
        iFiveModel.getServerData(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                iFiveView.initView(iFiveModel.getFiveBean());
            }

            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        });
    }
}
