package so.len.duobao.api;

/**
 * Created by Chung on 2016/8/3.
 */
public class SERVER {
    //服务器地址
//    public static final String DOMAIN = "http://yydb.314s.com";
    public static final String DOMAIN = "http://23.234.54.170";

    //前缀
    public static final String BEFORE = DOMAIN + "/index.php?s=/Home";

    //验证码
    public static final String CODE = BEFORE + "/Index/verificationCode";

    //用户模块
    public static final String USER = BEFORE + "/User";
    //商城模块
    public static final String MALL = BEFORE + "/Mall";
    //抢钱模块
    public static final String ROB = BEFORE + "/Rob";
    //我的
    public static final String MY = BEFORE + "/My";


    //注册接口
    public static final String REGISTER = USER + "/register";
    //登陆接口
    public static final String LOGIN = USER + "/login";
    public static final String LOGOUT = USER + "/logout";
    //忘记密码接口
    public static final String FORGET_PASSWORD = USER + "/forgetpwd";

    //我的接口
    public static final String USER_INFO = USER + "/show_my_info";
    //用户信息
    public static final String PERSONAL_INFO = USER + "/show_my_info_sec";
    public static final String CHANGE_HEAD_PIC = USER + "/my_head_change";
    public static final String CHANGE_USERNAME = USER + "/change_nickname";
    public static final String CHANGE_PASSWORD = USER + "/change_pwd";

    //主页接口
    public static final String HOME_PAGE = USER + "/show_index";

    //商城接口
    public static final String SHOP = MALL + "/index";

    //抽奖接口
    public static final String LOTTREY = USER + "/show_prize";

    //夺宝接口
    public static final String TREASURE = USER + "/show_grab";

    //判断当前账户vip
    public static final String CHECK_VIP = USER + "/show_vip";


    //抢钱接口
    public static final String GIFT = ROB + "/index";
    public static final String GO = ROB + "/start";

    //获取银行列表
    public static final String GET_BANK_LIST = MY + "/bank_support";
    public static final String ADD_BANK_CARD = MY + "/addBankcard";
    public static final String ADD_ADDR = MY + "/addShipAddress";
    public static final String MY_RECOMMENDER = MY + "/referee";
    public static final String CHANGE_MY_RECOMMENDER = MY + "/change_referee";

    //在线更新
    public static final String UPDATE_VERSION = MY + "/version_judge";
}
