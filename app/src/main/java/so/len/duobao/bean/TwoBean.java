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
        private List<TwoDataGoods> goods_list;

        public List<TwoDataGoods> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<TwoDataGoods> goods_list) {
            this.goods_list = goods_list;
        }
    }
}
