package so.len.duobao.iPresenter;

import so.len.duobao.iView.IMineView;

/**
 * Created by Chung on 2016/8/11.
 */
public class MinePresenter {
    private IMineView iMineView;

    public MinePresenter(IMineView iMineView) {
        this.iMineView = iMineView;
    }

    public void initView(){
        iMineView.initView();
    }
}
