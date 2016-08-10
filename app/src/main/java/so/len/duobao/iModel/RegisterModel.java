package so.len.duobao.iModel;

/**
 * Created by Chung on 2016/8/5.
 */
public class RegisterModel implements IRegisterModel {
    /**
     * 获取验证码
     * @param phone 传送手机号
     * @return 返回是否已发送短信
     */
    @Override
    public String getServerCode(String phone) {
        return "获取验证码";
    }

    /**
     * 提交注册信息
     * @param phone 传送手机号
     * @param password 传送密码
     * @return 返回
     */
    @Override
    public String doRegister(String phone, String code, String password) {
        return phone + code + password;
    }

}
