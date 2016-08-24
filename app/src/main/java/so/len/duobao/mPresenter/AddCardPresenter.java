package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.AddCardModel;
import so.len.duobao.mModel.IAddCardModel;
import so.len.duobao.mView.IAddCardView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/17.
 */
public class AddCardPresenter {
    private Context context;
    private IAddCardModel iAddCardModel;
    private IAddCardView iAddCardView;

    public AddCardPresenter(IAddCardView iAddCardView, Context context) {
        this.context = context;
        this.iAddCardModel = new AddCardModel(context);
        this.iAddCardView = iAddCardView;
    }

    public void initView(){
        iAddCardModel.getBankList(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                iAddCardView.initView(iAddCardModel.getBankListBean());
            }
            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context,msg);
            }
        });
    }
    public void addCard(){
        iAddCardModel.addCard(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                CommonUtils.toast(context, "添加成功");
            }

            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        },iAddCardView.getUsername() , iAddCardView.getBankID(), iAddCardView.getCardNum());
    }
}
