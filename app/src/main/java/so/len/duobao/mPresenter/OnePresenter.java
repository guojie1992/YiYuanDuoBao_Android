package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.bean.OneBean;
import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.IOneModel;
import so.len.duobao.mModel.OneModel;
import so.len.duobao.mView.IOneView;

/**
 * Created by Chung on 2016/8/8.
 */
public class OnePresenter {
    private IOneModel iOneModel;
    private IOneView iOneView;
    private Context context;

    public OnePresenter(IOneView iOneView, Context context) {
        this.context = context;
        this.iOneView = iOneView;
        this.iOneModel = new OneModel(context);
    }

    public void initView(){
        iOneModel.getServerData(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                iOneView.initView(iOneModel.getOneBean());
            }

            @Override
            public void loadError(String msg) {

            }
        });
    }

}
