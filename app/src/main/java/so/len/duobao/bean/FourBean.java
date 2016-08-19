package so.len.duobao.bean;

import java.util.List;

/**
 * Created by Chung on 2016/8/19.
 */
public class FourBean {
    private String status;
    private String msg;
    private List<FourData> data;

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

    public List<FourData> getData() {
        return data;
    }

    public void setData(List<FourData> data) {
        this.data = data;
    }

    public class FourData{
        private String id;
        private String title;
        private String price;
        private String number;
        private String issue;
        private String path;
        private String progess;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getIssue() {
            return issue;
        }

        public void setIssue(String issue) {
            this.issue = issue;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getProgess() {
            return progess;
        }

        public void setProgess(String progess) {
            this.progess = progess;
        }
    }
}
