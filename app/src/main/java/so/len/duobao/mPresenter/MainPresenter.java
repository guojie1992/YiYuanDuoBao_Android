package so.len.duobao.mPresenter;

import android.content.Context;
import so.len.duobao.mView.IMainView;

/**
 * Created by Chung on 2016/8/5.
 */
public class MainPresenter {
    private Context context;
    //    private IMainModel iMainModel;
    private IMainView iMainView;

    public MainPresenter(IMainView iMainView, Context context) {
        this.context = context;
        this.iMainView = iMainView;
//        this.iMainModel = new MainModel(context);
    }

    public void initView() {
        iMainView.initView();
    }

}
