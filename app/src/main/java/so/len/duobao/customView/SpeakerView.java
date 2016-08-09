package so.len.duobao.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import so.len.duobao.R;


public class SpeakerView extends ImageView {
    private static final int[] SPEAKER_RES=new int[]{R.mipmap.gb1,R.mipmap.gb2,R.mipmap.gb3};
    private long time;
    private int i=0;
    private boolean isStartSpeaker;

    public SpeakerView(Context context) {
        this(context,null);
    }

    public SpeakerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if(isStartSpeaker) {
                setImageResource(SPEAKER_RES[i=(++i%3)]);
                postDelayed(this, time);
            }
        }
    };
    public void startSpeaker(long time){
        this.time=time;
        this.isStartSpeaker=true;
        setImageResource(SPEAKER_RES[i=(++i%3)]);
        postDelayed(runnable,time);
    }

    public void stopSpeaker(){
        isStartSpeaker=false;
    }
}
