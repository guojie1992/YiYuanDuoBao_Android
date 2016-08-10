package so.len.duobao.iPresenter;

import so.len.duobao.iModel.IRegisterModel;
import so.len.duobao.iModel.RegisterModel;
import so.len.duobao.iView.IRegisterView;

/**
 * Created by Chung on 2016/8/5.
 */
public class RegisterPresenter {
    private IRegisterModel iRegisterModel;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterModel = new RegisterModel();
        this.iRegisterView = iRegisterView;
    }

    public void initView() {
        iRegisterView.initView();
    }

    public String getServerCode() {
        return iRegisterModel.getServerCode(iRegisterView.getPhone());
    }

    public String doRegister() {
        String registerResult = iRegisterModel.doRegister(iRegisterView.getPhone(), iRegisterView.getMessageCode(), iRegisterView.getPassword());
        iRegisterView.clearEditText();
        return registerResult;
    }

}
