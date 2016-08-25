package so.len.duobao.customView;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Chung on 2016/8/3.
 */
public class FragmentViewPager extends ViewPager {
    private boolean isCanScroll = true;
    private int previousX;
    private int previousY;
    private boolean isClick;
    private DisplayMode mode = DisplayMode.DEFAULT;

    public FragmentViewPager(Context context) {
        super(context);
    }

    public FragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否能滑动
     *
     * @param isCanScroll
     */
    public void setScrollable(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isCanScroll) {
            return onTouch(event);
        } else {
            return false;
        }

    }

    public void setDisplayMode(DisplayMode mode) {
        this.mode = mode;
    }

    public enum DisplayMode {
        DISPLAY_BY_EVERY_ONE,
//        DISPLAY_BY_FIRST_ONE,
//        FRAGMENT_FIVE,
        DEFAULT
    }


    private boolean onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isClick = true;
                previousX = (int) event.getX();
                previousY = (int) event.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:
                isClick = false;
                int currentX = (int) event.getX();
                int currentY = (int) event.getY();
                int absX = Math.abs(currentX - previousX);
                int absY = Math.abs(currentY - previousY);
                if (absX >= absY) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                previousX = currentX;
                previousY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                if (isClick)
                    onSingleTouch();
                break;
        }
        return  super.onTouchEvent(event);
    }

    /**
     * 单击
     */
    public void onSingleTouch() {
        if (onSingleTouchListener != null) {
            onSingleTouchListener.onSingleTouch();
        }
    }

    /**
     * 单击事件监听器
     */
    public interface OnSingleTouchListener {
        public void onSingleTouch();
    }

    private OnSingleTouchListener onSingleTouchListener;

    /**
     * 设置单击监听器
     *
     * @param onSingleTouchListener
     */
    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (  isCanScroll ) {
            return super.onInterceptTouchEvent(arg0);
        } else {
            return false;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        switch (mode) {
            case DISPLAY_BY_EVERY_ONE:
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                onRemeasureEveryOne(widthMeasureSpec, heightMeasureSpec);
                break;
//            case DISPLAY_BY_FIRST_ONE:
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//                getChildAt(0).measure(widthMeasureSpec,heightMeasureSpec);
//                setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
//                break;
//            case FRAGMENT_FIVE:
//                int height = 0;
//                //下面遍历所有child的高度
//                for (int i = 0; i < getChildCount(); i++) {
//                    View child = getChildAt(i);
//                    child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//                    int h = child.getMeasuredHeight();
//                    if (h > height) //采用最大的view的高度。
//                        height = h;
//                }
//                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//                break;
            case DEFAULT:
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                break;
        }
    }

    private int measureWidth(int widthMeasureSpec, View v) {
        int result = 0;
        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);
        if (measureMode == MeasureSpec.EXACTLY)
            return measureSize;
        if (v != null)
            result = v.getMeasuredWidth();
        if (measureMode == MeasureSpec.AT_MOST)
            result = Math.min(result, measureSize);
        Log.d("jgm","w"+result);
        return result;
    }

    private int measureHeight(int heightMeasureSpec, View v) {
        int result = 0;
        int measureMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureSize = MeasureSpec.getSize(heightMeasureSpec);
        if (measureMode == MeasureSpec.EXACTLY)
            return measureSize;
        if (v != null)
            result = v.getMeasuredHeight();
        if (measureMode == MeasureSpec.AT_MOST)
            result = Math.min(result, measureSize);
        return result;
    }

    private void onRemeasureEveryOne(int widthMeasureSpec, int heightMeasureSpec) {
        if (getAdapter() == null)
            return;
        Object object = getAdapter().instantiateItem(this, getCurrentItem());
        View view = null;
        if (object instanceof Fragment)
            view = ((Fragment) object).getView();
        if (object instanceof View)
            view = (View) object;

        if (view != null) {
            int i=getChildCount();
            Log.d("jgm",i+"--");
            for(int ii=0;ii<i;ii++){
                View v = getChildAt(ii);
                if(v!=null)
                    v.measure(widthMeasureSpec,heightMeasureSpec);
            }
        }
        setMeasuredDimension(measureWidth(widthMeasureSpec, view), measureHeight(heightMeasureSpec, view));
    }

}
