package so.len.duobao.iModel;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IForgetModel {
    String getServerCode(String phone);
    String doForget(String phone, String code, String password, String repeatPassword);
    boolean saveData(String phone, String password);
}
