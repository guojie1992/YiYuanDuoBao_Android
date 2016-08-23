package so.len.duobao.bean;

import java.util.List;

/**
 * Created by Chung on 2016/8/19.
 */
public class FiveBean {
    private String status;
    private String msg;
    private GIFT rob_list;
    private WALLET html_list;
    private List<My_List> my_list;
    private List<History_List> history_list;

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

    public GIFT getRob_list() {
        return rob_list;
    }

    public void setRob_list(GIFT rob_list) {
        this.rob_list = rob_list;
    }

    public WALLET getHtml_list() {
        return html_list;
    }

    public void setHtml_list(WALLET html_list) {
        this.html_list = html_list;
    }

    public List<My_List> getMy_list() {
        return my_list;
    }

    public void setMy_list(List<My_List> my_list) {
        this.my_list = my_list;
    }

    public List<History_List> getHistory_list() {
        return history_list;
    }

    public void setHistory_list(List<History_List> history_list) {
        this.history_list = history_list;
    }

    public class GIFT{
        private String rob_number;
        private String rob_list_id;
        private String rob_copies;
        private String progress_bar;
        private int next_time_status;
        private long next_time;

        public String getRob_number() {
            return rob_number;
        }

        public void setRob_number(String rob_number) {
            this.rob_number = rob_number;
        }

        public String getRob_list_id() {
            return rob_list_id;
        }

        public void setRob_list_id(String rob_list_id) {
            this.rob_list_id = rob_list_id;
        }

        public String getRob_copies() {
            return rob_copies;
        }

        public void setRob_copies(String rob_copies) {
            this.rob_copies = rob_copies;
        }

        public String getProgress_bar() {
            return progress_bar;
        }

        public void setProgress_bar(String progress_bar) {
            this.progress_bar = progress_bar;
        }

        public int getNext_time_status() {
            return next_time_status;
        }

        public void setNext_time_status(int next_time_status) {
            this.next_time_status = next_time_status;
        }

        public long getNext_time() {
            return next_time;
        }

        public void setNext_time(long next_time) {
            this.next_time = next_time;
        }
    }

    public class WALLET{
        private String goods_count;
        private String vouchers_count;
        private String beans;

        public String getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }

        public String getVouchers_count() {
            return vouchers_count;
        }

        public void setVouchers_count(String vouchers_count) {
            this.vouchers_count = vouchers_count;
        }

        public String getBeans() {
            return beans;
        }

        public void setBeans(String beans) {
            this.beans = beans;
        }
    }

    public class My_List{
        private String rob_name;
        private String rob_time;

        public String getRob_name() {
            return rob_name;
        }

        public void setRob_name(String rob_name) {
            this.rob_name = rob_name;
        }

        public String getRob_time() {
            return rob_time;
        }

        public void setRob_time(String rob_time) {
            this.rob_time = rob_time;
        }
    }

    public class History_List{
        private String data_time;
        private List<History_List_Item> data_list;

        public String getData_time() {
            return data_time;
        }

        public void setData_time(String data_time) {
            this.data_time = data_time;
        }

        public List<History_List_Item> getData_list() {
            return data_list;
        }

        public void setData_list(List<History_List_Item> data_list) {
            this.data_list = data_list;
        }

    }

    public class History_List_Item{
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
