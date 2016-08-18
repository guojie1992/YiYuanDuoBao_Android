package so.len.duobao.mPresenter;

import so.len.duobao.mView.IWebView;

/**
 * Created by Chung on 2016/8/12.
 */
public class WebViewPresenter {
    private IWebView iWebView;

    public WebViewPresenter(IWebView iWebView) {
        this.iWebView = iWebView;
    }

    public void initView(){
        iWebView.initView();
    }
}
