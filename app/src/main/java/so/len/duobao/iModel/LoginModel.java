package so.len.duobao.iModel;

/**
 * Created by Chung on 2016/8/5.
 */
public class LoginModel implements ILoginModel {
    @Override
    public String doLogin(String phone, String password) {
        return phone + password;
    }

    @Override
    public boolean saveData(String phone, String password) {
        return false;
    }
}
