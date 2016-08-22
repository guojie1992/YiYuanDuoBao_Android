package so.len.duobao.mPresenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

import so.len.duobao.database.Config;
import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.ChangeUsernameModel;
import so.len.duobao.mModel.IChangeUsernameModel;
import so.len.duobao.mView.IChangeUsernameView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/14.
 */
public class ChangeUsernamePresenter {
    private Context context;
    private IChangeUsernameModel iChangeUsernameModel;
    private IChangeUsernameView iChangeUsernameView;

    public ChangeUsernamePresenter(IChangeUsernameView iChangeUsernameView, Context context) {
        this.context = context;
        this.iChangeUsernameModel = new ChangeUsernameModel(context);
        this.iChangeUsernameView = iChangeUsernameView;
    }
    public void initView(){
        iChangeUsernameView.initView();
    }
    public void changeUsername(String username){
        iChangeUsernameModel.setUsername(username, new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                Logger.i("change username succeed");
            }

            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        });
    }
}
