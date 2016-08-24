package so.len.duobao.mModel;

import so.len.duobao.bean.BankListBean;
import so.len.duobao.mListener.IHttpCompleteListener;

/**
 * Created by Chung on 2016/8/17.
 */
public interface IAddCardModel {
    void addCard(IHttpCompleteListener iHttpCompleteListener, String username, String bankID, String cardNum);
    void getBankList(IHttpCompleteListener iHttpCompleteListener);
    BankListBean  getBankListBean();
}
