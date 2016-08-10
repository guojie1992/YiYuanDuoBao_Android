package so.len.duobao.iModel;

/**
 * Created by Chung on 2016/8/5.
 */
public class ForgetModel implements IForgetModel {
    @Override
    public String getServerCode(String phone) {
        return "获取";
    }

    @Override
    public String doForget(String phone, String code, String password, String repeatPassword) {
        return phone + code + password + repeatPassword;
    }

}
