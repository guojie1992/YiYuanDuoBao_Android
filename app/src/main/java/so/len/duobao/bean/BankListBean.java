package so.len.duobao.bean;

import java.util.List;

/**
 * Created by Chung on 2016/8/24.
 */
public class BankListBean {
    private String status;
    private String msg;
    private List<Bank> data;

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

    public List<Bank> getData() {
        return data;
    }

    public void setData(List<Bank> data) {
        this.data = data;
    }

    public class Bank{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
