package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.IThreeModel;
import so.len.duobao.mModel.ThreeModel;
import so.len.duobao.mView.IThreeView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/9.
 */
public class ThreePresenter {
    private IThreeModel iThreeModel;
    private IThreeView iThreeView;
    private Context context;

    public ThreePresenter(IThreeView iThreeView, Context context) {
        this.context = context;
        this.iThreeView = iThreeView;
        this.iThreeModel = new ThreeModel(context);
    }

    public void initView(){
        iThreeModel.getServerData(new IHttpCompleteListener() {
            @Override
            public void loadComplete(String msg) {
                iThreeView.initView(iThreeModel.getThreeBean());
            }

            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        });
    }
}
