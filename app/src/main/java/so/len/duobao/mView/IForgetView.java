package so.len.duobao.mView;

/**
 * Created by Chung on 2016/8/5.
 */
public interface IForgetView {
    void initView();
    String getPhone();
    String getMessageCode();
    String getPassword();
    String getRepeatPassword();
    void clearEditText();
}
