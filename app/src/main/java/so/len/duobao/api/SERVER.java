package so.len.duobao.api;

/**
 * Created by Chung on 2016/8/3.
 */
public class SERVER {
    //服务器地址
    public static final String DOMAIN = "http://yydb.314s.com";

    //前缀
    public static final String BEFORE = DOMAIN + "/index.php?s=/Home";

    //用户模块
    public static final String USER = BEFORE + "/User";
    //商城模块
    public static final String MALL = BEFORE + "/Mall";
    //抢钱模块
    public static final String ROB = BEFORE + "/Rob";


    //注册接口
    public static final String REGISTER = USER + "/register";
    //登陆接口
    public static final String LOGIN = USER + "/login";
    //忘记密码接口
    public static final String FORGET_PASSWORD = USER + "/forgetpwd";

    //我的接口
    public static final String USER_INFO = USER + "/show_my_info";

    //主页接口
    public static final String HOME_PAGE = USER + "/show_index";

    //商城接口
    public static final String SHOP = MALL + "/index";

    //抽奖接口
    public static final String LOTTREY = USER + "/show_prize";

    //夺宝接口
    public static final String TREASURE = USER + "/show_grab";


    //抢钱接口
    public static final String GIFT = ROB + "/index";
}
