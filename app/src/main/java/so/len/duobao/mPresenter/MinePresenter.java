package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.IMineModel;
import so.len.duobao.mModel.MineModel;
import so.len.duobao.mView.IMineView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/11.
 */
public class MinePresenter {
    private IMineView iMineView;
    private IMineModel iMineModel;
    private Context context;

    public MinePresenter(IMineView iMineView, Context context) {
        this.context = context;
        this.iMineModel = new MineModel(context);
        this.iMineView = iMineView;
    }

    public void initView(){
        iMineModel.getServerData(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                iMineView.initView(iMineModel.getMineBean());
            }

            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, "数据加载错误");
            }
        });

    }
}
