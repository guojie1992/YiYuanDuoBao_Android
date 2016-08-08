package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

/**
 * Created by Chung on 2016/8/3.
 */
public class BaseFragment extends Fragment {
    private String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    protected void toast(String text){
        Toast.makeText(getActivity().getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
    protected void logInfo(String text){
        Logger.i(TAG,text);
    }
    protected void logError(String text){
        Logger.e(TAG,text);
    }
    protected void logWarn(String text){
        Logger.w(TAG,text);
    }
    protected void logDebug(String text){
        Logger.d(TAG,text);
    }
    protected void logVerbose(String text){
        Logger.v(TAG,text);
    }
}
