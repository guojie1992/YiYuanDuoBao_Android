package so.len.duobao.iPresenter;

import so.len.duobao.iModel.ForgetModel;
import so.len.duobao.iModel.IForgetModel;
import so.len.duobao.iView.IForgetView;

/**
 * Created by Chung on 2016/8/5.
 */
public class ForgetPresenter {
    private IForgetModel iForgetModel;
    private IForgetView iForgetView;

    public ForgetPresenter(IForgetView iForgetView) {
        this.iForgetModel = new ForgetModel();
        this.iForgetView = iForgetView;
    }

    public void initView(){
        iForgetView.initView();
    }

    public String getServerCode() {
        return iForgetModel.getServerCode(iForgetView.getPhone());
    }

    public String doForget() {
        if(checkRepeat()){
            return iForgetModel.doForget(iForgetView.getPhone(), iForgetView.getMessageCode(), iForgetView.getPassword(), iForgetView.getRepeatPassword());
        }else {
            iForgetView.clearEditText();
            return "两次密码输入不一致！";
        }
    }

    private boolean checkRepeat() {
        return iForgetView.getPassword().equals(iForgetView.getRepeatPassword())?true:false;
    }
}
