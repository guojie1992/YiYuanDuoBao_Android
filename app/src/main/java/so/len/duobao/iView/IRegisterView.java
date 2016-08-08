package so.len.duobao.iView;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IRegisterView {
    String getPhone();
    String getMessageCode();
    String getPassword();
    void clearEditText();
    void setTopMenu();
}
