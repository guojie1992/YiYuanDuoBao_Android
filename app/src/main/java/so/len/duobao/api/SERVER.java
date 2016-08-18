package so.len.duobao.api;

/**
 * Created by Chung on 2016/8/3.
 */
public class SERVER {
    //服务器地址
    public static final String DOMAIN = "http://yydb.314s.com";

    //前缀
    public static final String BEFORE = DOMAIN + "/index.php?s=/Home/User";

    //注册接口
    public static final String REGISTER = BEFORE + "/register";
    //登陆接口
    public static final String LOGIN = BEFORE + "/login";
    //忘记密码接口
    public static final String FORGET_PASSWORD = BEFORE + "/forgetpwd";

    //我的接口
    public static final String USER_INFO = BEFORE + "/show_my_info";

    //主页接口
    public static final String HOME_PAGE = BEFORE + "/show_index";

    //商城接口
    public static final String SHOP = DOMAIN + "/index.php?s=Home/Mall/index";

    //抽奖接口
    public static final String LOTTREY = BEFORE + "/show_draw";

    //夺宝接口


    //抢钱上部分接口
    public static final String GIFT_TOP = BEFORE + "/show_rob_head";
    //抢钱列表接口
    public static final String GIFT_BOTTOM = BEFORE + "/show_rob_list";
}
