package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.FourModel;
import so.len.duobao.mModel.IFourModel;
import so.len.duobao.mView.IFourView;

/**
 * Created by Chung on 2016/8/9.
 */
public class FourPresenter  {
    private Context context;
    private IFourModel iFourModel;
    private IFourView iFourView;

    public FourPresenter(IFourView iFourView, Context context) {
        this.context = context;
        this.iFourView = iFourView;
        iFourModel = new FourModel(context);
    }

    public void initView(){
        iFourModel.getServerData(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                iFourView.initView(iFourModel.getFourBean());
            }

            @Override
            public void loadError(String msg) {

            }
        });
    }
}
