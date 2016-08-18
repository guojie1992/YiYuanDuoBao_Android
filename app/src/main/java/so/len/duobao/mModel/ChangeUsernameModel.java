package so.len.duobao.mModel;

/**
 * Created by Chung on 2016/8/14.
 */
public class ChangeUsernameModel implements IChangeUsernameModel {
    @Override
    public String setUsername(String uid, String username) {
        return uid + username;
    }
}
