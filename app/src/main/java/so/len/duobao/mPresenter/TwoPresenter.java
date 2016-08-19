package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.ITwoModel;
import so.len.duobao.mModel.TwoModel;
import so.len.duobao.mView.ITwoView;

/**
 * Created by Chung on 2016/8/9.
 */
public class TwoPresenter {
    private ITwoModel iTwoModel;
    private ITwoView iTwoView;
    private Context context;

    public TwoPresenter(ITwoView iTwoView, Context context) {
        this.context = context;
        this.iTwoModel = new TwoModel(context);
        this.iTwoView = iTwoView;
    }

    public void initView(){
        iTwoModel.getServerData(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                iTwoView.initView(iTwoModel.getTwoBean());
            }

            @Override
            public void loadError(String msg) {

            }
        });
    }
}
