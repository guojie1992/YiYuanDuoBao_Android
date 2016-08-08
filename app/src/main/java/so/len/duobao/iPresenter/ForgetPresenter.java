package so.len.duobao.iPresenter;

import so.len.duobao.iModel.ForgetModel;
import so.len.duobao.iModel.IForgetModel;
import so.len.duobao.iView.IForgetView;

/**
 * Created by Chung on 2016/8/5.
 */
public class ForgetPresenter implements IForgetPresenter {
    private IForgetModel iForgetModel;
    private IForgetView iForgetView;

    public ForgetPresenter(IForgetView iForgetView) {
        this.iForgetModel = new ForgetModel();
        this.iForgetView = iForgetView;
    }

    /**
     * View
     * @return
     */
    @Override
    public String getPhone() {
        return iForgetView.getPhone();
    }

    @Override
    public String getMessageCode() {
        return iForgetView.getMessageCode();
    }

    @Override
    public String getPassword() {
        return iForgetView.getPassword();
    }

    @Override
    public String getRepeatPassword() {
        return iForgetView.getRepeatPassword();
    }

    @Override
    public void clearEditText() {
        iForgetView.clearEditText();
    }

    @Override
    public void setTopMenu() {
        iForgetView.setTopMenu();
    }


    /**
     * Model
     * @param phone
     * @return
     */
    @Override
    public String getServerCode(String phone) {
        return iForgetModel.getServerCode(phone);
    }

    @Override
    public String doForget(String phone, String code, String password, String repeatPassword) {
        if(checkRepeat(password, repeatPassword)){
            return iForgetModel.doForget(phone, code, password, repeatPassword);
        }else {
            clearEditText();
            return "两次密码输入不一致！";
        }
    }

    @Override
    public boolean saveData(String phone, String password) {
        return iForgetModel.saveData(phone, password);
    }


    /**
     * cope
     * @param password
     * @param repeatPassword
     * @return
     */
    @Override
    public boolean checkRepeat(String password, String repeatPassword) {
        return iForgetView.getPassword().equals(iForgetView.getRepeatPassword())?true:false;
    }
}
