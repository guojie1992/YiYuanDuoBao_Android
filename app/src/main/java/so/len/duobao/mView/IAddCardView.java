package so.len.duobao.mView;

import so.len.duobao.bean.BankListBean;

/**
 * Created by Chung on 2016/8/17.
 */
public interface IAddCardView {
    void initView(BankListBean bankListBean);
    String getUsername();
    String getBankID();
    String getCardNum();
}
