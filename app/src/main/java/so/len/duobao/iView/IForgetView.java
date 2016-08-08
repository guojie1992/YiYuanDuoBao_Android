package so.len.duobao.iView;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IForgetView {
    String getPhone();
    String getMessageCode();
    String getPassword();
    String getRepeatPassword();
    void clearEditText();
    void setTopMenu();
}
