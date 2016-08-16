package so.len.duobao.api;

/**
 * Created by Chung on 2016/8/3.
 */
public class HTML {
    public static final String DOMAIN = "http://yydb.314s.com/";

    public static final String BEFORE = DOMAIN + "index.php?s=/Home/";

    /**
     * 商城
     */
    public static final String SHOP = BEFORE + "Mall/productDetail.html";
    public static final String CART = BEFORE + "Mall/shoppingCart.html";
    public static final String ORDER = BEFORE + "Mall/comfirmOrder.html";
    public static final String ORDER_SUCCEED = BEFORE + "Mall/orderSuccess.html";

    /**
     * 我的
     */
    public static final String SIGN = BEFORE + "My/signIn.html";
    public static final String MY_ORDER = BEFORE + "My/myOrders.html";
    public static final String MY_RECOMMEND = BEFORE + "My/recommendMan.html";
    public static final String MY_RECOMMEND_GAIN = BEFORE + "My/myCommission.html";
    public static final String MY_M = BEFORE + "My/money.html";
    public static final String MY_POINT = BEFORE + "My/score.html";
    public static final String MY_LEVEL = BEFORE + "My/memberRank.html";
    public static final String MY_CARD = BEFORE + "My/bankCard.html";
    public static final String MY_ADD_CARD = BEFORE + "My/addBankcard.html";
    public static final String MY_RECOMMENDER_CANNOT_CHANGE = BEFORE + "My/refereeNoChange.html";
    public static final String MY_RECOMMENDER_CAN_CAHNGE = BEFORE + "My/referee.html";
    public static final String MY_ADDR = BEFORE + "My/shippingAddress.html";
    public static final String MY_ADD_ADDR = BEFORE + "My/addShipAddress.html";
    public static final String MY_BACK = BEFORE + "My/remission.html";
    public static final String MY_TREASURE = BEFORE + "My/indianaRecords.html";

}
