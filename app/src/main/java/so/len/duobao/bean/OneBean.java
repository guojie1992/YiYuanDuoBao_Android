package so.len.duobao.bean;

import java.util.List;

/**
 * Created by Chung on 2016/8/18.
 */
public class OneBean {
    private OneData data;
    private String status;
    private String msg;

    public OneData getData() {
        return data;
    }

    public void setData(OneData data) {
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

    public class OneData{
        private List<OneDataPic> pic;
        private List<OneDataList> list;

        public List<OneDataPic> getPic() {
            return pic;
        }

        public void setPic(List<OneDataPic> pic) {
            this.pic = pic;
        }

        public List<OneDataList> getList() {
            return list;
        }

        public void setList(List<OneDataList> list) {
            this.list = list;
        }
    }
}
