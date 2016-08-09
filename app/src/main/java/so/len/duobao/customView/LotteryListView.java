package so.len.duobao.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * Created by Chung on 2016/8/9.
 */
public class LotteryListView extends ListView {
    public LotteryListView(Context context) {
        super(context);
    }
    public LotteryListView(Context context, AttributeSet as) {
        super(context, as);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }
}
