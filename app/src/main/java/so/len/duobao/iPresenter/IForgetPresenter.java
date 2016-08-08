package so.len.duobao.iPresenter;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IForgetPresenter {
    /**
     * View
     */
    String getPhone();
    String getMessageCode();
    String getPassword();
    String getRepeatPassword();
    void clearEditText();
    void setTopMenu();

    /**
     * Model
     */
    String getServerCode(String phone);
    String doForget(String phone, String code, String password, String repeatPassword);
    boolean saveData(String phone, String password);

    /**
     * cope
     */
    boolean checkRepeat(String password, String repeatPassword);
}
