package so.len.duobao.iModel;

/**
 * Created by Chung on 2016/8/5.
 */
public interface ILoginModel {
    String doLogin(String phone, String password);
    boolean saveData(String phone);
}
