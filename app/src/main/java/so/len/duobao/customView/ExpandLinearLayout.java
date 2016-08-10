package so.len.duobao.customView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;

/**
 * Created by Chung on 2016/8/10.
 */
public class ExpandLinearLayout extends LinearLayout {
    @BindView(R.id.tv_title_item_linearlayout_expand)
    TextView tvTitleItemLinearlayoutExpand;
    @BindView(R.id.iv_arrow_item_linearlayout_expand)
    ImageView ivArrowItemLinearlayoutExpand;
    @BindView(R.id.rl_title_item_linearlayout_expand)
    RelativeLayout rlTitleItemLinearlayoutExpand;
    @BindView(R.id.ll_item_linearlayout_expand)
    LinearLayout llItemLinearlayoutExpand;

    private int itemTextBackground;
    private ColorStateList itemTextColor;
    private float itemTextSize;

    private int llScreenItemHeight = 0;
    private boolean isUp = true;
    private boolean isAnimatorEnd = true;
    private ObjectAnimator animator;
    private OnItemListener listener;
    private float animationF;
    private final List<TextView> itemTexts;
    private int position;
    private final int itemPaddingL_R;
    private final int itemPaddingT_B;
    private final int itemLayoutMargin;
    private String t;

    public ExpandLinearLayout(Context context) {
        this(context, null);
    }

    public ExpandLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.item_linearlayout_expand, null);
        itemTexts = new ArrayList<>();
        addView(view);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(lp);

        ButterKnife.bind(this);

        itemPaddingL_R = (int) getResources().getDimension(R.dimen.dp_10);
        itemPaddingT_B = (int) getResources().getDimension(R.dimen.dp_10);
        itemLayoutMargin = (int) getResources().getDimension(R.dimen.dp_5);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandLinearLayout);
            int arrowResId = typedArray.getResourceId(R.styleable.ExpandLinearLayout_ell_arrowSrc, 0);
            String title = typedArray.getString(R.styleable.ExpandLinearLayout_ell_title);
            float titleSize = typedArray.getDimension(R.styleable.ExpandLinearLayout_ell_titleSize, tvTitleItemLinearlayoutExpand.getTextSize());
            int titleColor = typedArray.getColor(R.styleable.ExpandLinearLayout_ell_titleColor, Color.BLACK);
            itemTextSize = typedArray.getDimension(R.styleable.ExpandLinearLayout_ell_itemTextSize, 0);
            itemTextColor = typedArray.getColorStateList(R.styleable.ExpandLinearLayout_ell_itemTextColor);
            itemTextBackground = typedArray.getResourceId(R.styleable.ExpandLinearLayout_ell_itemTextBackground, 0);
            typedArray.recycle();

            ivArrowItemLinearlayoutExpand.setImageResource(arrowResId);
            tvTitleItemLinearlayoutExpand.setText(title);
            tvTitleItemLinearlayoutExpand.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
            tvTitleItemLinearlayoutExpand.setTextColor(titleColor);

        }
        if (itemTextColor == null)
            itemTextColor = getResources().getColorStateList(R.color.theme);
//        if (itemTextBackground == 0)
//            itemTextBackground = R.drawable.selector_screen_item_background;
        if (itemTextSize == 0)
            itemTextSize = getResources().getDimension(R.dimen.sp_12);

        rlTitleItemLinearlayoutExpand.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showItem(v);
            }
        });

    }


    public void setTitleSize(float textSize) {
        tvTitleItemLinearlayoutExpand.setTextSize(textSize);
    }

    public void setTitleColor(int textColor) {
        tvTitleItemLinearlayoutExpand.setTextColor(textColor);
    }

    public void setItemTextSize(float textSize) {
        itemTextSize = textSize;
    }

    public void setItemTextColor(int textColor) {
        itemTextColor = getResources().getColorStateList(textColor);
    }

    public void setItemTextBackground(int resId) {
        itemTextBackground = resId;
    }

    public void setScreenArrow(int resId) {
        ivArrowItemLinearlayoutExpand.setImageResource(resId);
    }

    public void setTitleText(String text) {
        tvTitleItemLinearlayoutExpand.setText(text);
    }


    public List<TextView> addItemViews(int position, List<String> texts, String t) {
        itemTexts.clear();
        llItemLinearlayoutExpand.removeAllViews();
        this.t = t;
        int i = 0;
        for (String text : texts) {
            addItemView(text, i++);
            if (t.equals(text)) {
                itemTexts.get(itemTexts.size() - 1).setSelected(true);
            }
        }
        this.position = position;
        return itemTexts;
    }

    public void setOnScreenItemListener(OnItemListener listener) {
        this.listener = listener;
    }

    public void addItem(int position, List<String> texts, String t, boolean isUp) {
        ViewGroup.LayoutParams lp = llItemLinearlayoutExpand.getLayoutParams();
        if (isUp)
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        else
            lp.height = 0;
        llItemLinearlayoutExpand.setLayoutParams(lp);
        addItemViews(position, texts, t);
        this.isUp = isUp;
        setArrowRotation(ivArrowItemLinearlayoutExpand, !isUp);
    }

    public interface OnItemListener {
        void onItem(int position, String text, boolean isUp);
    }

    private TextView addItemView(String text, int pos) {
        TextView textView = new TextView(getContext());
        textView.setTag(pos);
        textView.setText(text);
        textView.setTextColor(itemTextColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, itemTextSize);
        textView.setBackgroundResource(itemTextBackground);
        textView.setPadding(itemPaddingL_R, itemPaddingT_B, itemPaddingL_R, itemPaddingT_B);
        itemTexts.add(textView);
        llItemLinearlayoutExpand.addView(textView);

        LayoutParams lp = (LayoutParams) textView.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        lp.width = LayoutParams.MATCH_PARENT;
        lp.topMargin = itemLayoutMargin;
        lp.bottomMargin = itemLayoutMargin;
        lp.leftMargin = itemLayoutMargin;
        lp.rightMargin = itemLayoutMargin;
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }

    private void unSelected() {
        for (TextView t : itemTexts) {
            t.setSelected(false);
        }
    }

    private void showItem(View v) {
        if (llScreenItemHeight == 0)
            llScreenItemHeight = llItemLinearlayoutExpand.getHeight();
        setArrowRotation(v, isUp);
        itemAnimator(isUp);
    }

    private void setArrowRotation(View v, boolean is) {
        if (is) {
            ivArrowItemLinearlayoutExpand.setRotation(180);
        } else {
            ivArrowItemLinearlayoutExpand.setRotation(0);
        }
    }

    private void itemAnimator(boolean up) {
        if (!isAnimatorEnd)
            animator.cancel();
        float startF;
        float endF;
        int duration = 200;
        if (up) {
            if (isAnimatorEnd)
                startF = 1f;
            else
                startF = animationF;
            endF = 0f;
            duration = (int) (duration * (startF - endF));
        } else {
            if (isAnimatorEnd)
                startF = 0f;
            else
                startF = animationF;
            endF = 1f;
            duration = (int) (duration * (endF - startF));
        }
        isUp = !isUp;
        animator = ObjectAnimator.ofFloat(this, "", startF, endF);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationF = (float) animation.getAnimatedValue();
                ViewGroup.LayoutParams lp = llItemLinearlayoutExpand.getLayoutParams();
                if (llScreenItemHeight == 0) {
                    lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    llItemLinearlayoutExpand.setLayoutParams(lp);
                    animationF = 1f;
                    llItemLinearlayoutExpand.setAlpha(animationF);
                    animator.cancel();
                } else {
                    lp.height = (int) (llScreenItemHeight * animationF);
                    llItemLinearlayoutExpand.setLayoutParams(lp);
                    if (animationF == 0)
                        llItemLinearlayoutExpand.setAlpha(1);
                    else
                        llItemLinearlayoutExpand.setAlpha(animationF);
                }
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimatorEnd = true;
                super.onAnimationEnd(animation);
            }
        });
        isAnimatorEnd = false;
        animator.start();
    }

}
