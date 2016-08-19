package so.len.duobao.bean;

import java.util.List;

/**
 * Created by Chung on 2016/8/19.
 */
public class TwoBean {
    private TwoData data;
    private String status;
    private String msg;

    public TwoData getData() {
        return data;
    }

    public void setData(TwoData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class TwoData{
        private List<TwoDataPoint> integral_goods_list;
        private List<TwoDataM> money_goods_list;

        public List<TwoDataPoint> getIntegral_goods_list() {
            return integral_goods_list;
        }

        public void setIntegral_goods_list(List<TwoDataPoint> integral_goods_list) {
            this.integral_goods_list = integral_goods_list;
        }

        public List<TwoDataM> getMoney_goods_list() {
            return money_goods_list;
        }

        public void setMoney_goods_list(List<TwoDataM> money_goods_list) {
            this.money_goods_list = money_goods_list;
        }
    }
}
