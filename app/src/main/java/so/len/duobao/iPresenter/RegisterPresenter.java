package so.len.duobao.iPresenter;

import so.len.duobao.iModel.IRegisterModel;
import so.len.duobao.iModel.RegisterModel;
import so.len.duobao.iView.IRegisterView;

/**
 * Created by Chung on 2016/8/5.
 */
public class RegisterPresenter implements IRegisterPresenter {
    private IRegisterModel iRegisterModel;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterModel = new RegisterModel();
        this.iRegisterView = iRegisterView;
    }

    /**
     * Model
     */
    @Override
    public String getServerCode(String phone) {
        return iRegisterModel.getServerCode(phone);
    }

    @Override
    public String doRegister(String phone, String code, String password) {
        return iRegisterModel.doRegister(phone, code, password);
    }

    @Override
    public boolean saveData(String phone, String password) {
        return iRegisterModel.saveData(phone, password);
    }



    /**
     * view
     */
    @Override
    public String getPhone() {
        return iRegisterView.getPhone();
    }

    @Override
    public String getMessageCode() {
        return iRegisterView.getMessageCode();
    }

    @Override
    public String getPassword() {
        return iRegisterView.getPassword();
    }

    @Override
    public void clearEditText() {
        iRegisterView.clearEditText();
    }

    @Override
    public void setTopMenu() {
        iRegisterView.setTopMenu();
    }

}
