package so.len.duobao.iPresenter;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IRegisterPresenter {
    /**
     * View
     */
    String getPhone();
    String getMessageCode();
    String getPassword();
    void clearEditText();
    void setTopMenu();

    /**
     * Model
     */
    String getServerCode(String phone);
    String doRegister(String phone, String code, String password);
    boolean saveData(String phone, String password);
}
