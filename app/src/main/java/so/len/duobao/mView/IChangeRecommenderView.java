package so.len.duobao.mView;

/**
 * Created by Chung on 2016/8/17.
 */
public interface IChangeRecommenderView {
    void initChangeableView();
    void initNoChangeView(String pid);
    String getRecommenderID();
}
