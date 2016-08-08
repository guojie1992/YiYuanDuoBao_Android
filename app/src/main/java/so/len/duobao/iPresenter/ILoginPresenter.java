package so.len.duobao.iPresenter;

/**
 * Created by Chung on 2016/8/5.
 */
public interface ILoginPresenter {
    /**
     * Model
     */
    String doLogin(String phone, String password);
    boolean saveData(String phone, String password);

    /**
     * View
     */
    String getPhone();
    String getPassword();
    void clearEditText();
    void setTopMenu();
}
