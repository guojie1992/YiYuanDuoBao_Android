package so.len.duobao.bean;

/**
 * Created by Chung on 2016/8/18.
 */
public class MineBean {
    private MineData data;
    private String status;
    private String msg;

    public MineData getData() {
        return data;
    }

    public void setData(MineData data) {
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

    public class MineData{
        private String pic;
        private String nickname;
        private int vip;
        private String moneyBack;
        private String integralBack;
        private String signIn;
        private String pid;


        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public String getMoneyBack() {
            return moneyBack;
        }

        public void setMoneyBack(String moneyBack) {
            this.moneyBack = moneyBack;
        }

        public String getIntegralBack() {
            return integralBack;
        }

        public void setIntegralBack(String integralBack) {
            this.integralBack = integralBack;
        }

        public String getSignIn() {
            return signIn;
        }

        public void setSignIn(String signIn) {
            this.signIn = signIn;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
    }
}
