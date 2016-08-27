package so.len.duobao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtils {
    /**
     * 检测网络是否可用
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 判断存储卡是否可用
     */
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * MD5加密
     * @param msg
     * @return
     */
    public static String md5Encrypt(String msg) {
        // 获取加密方法
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            byte[] bytes = digest.digest(msg.getBytes());
            StringBuilder builder = new StringBuilder();
            // byte数组转换成字符串
            for (byte b : bytes) {
                int v = b & 255;
                String str = Integer.toHexString(v);
                if (str.length() == 1)
                    builder.append("0");

                builder.append(str);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取系统状态栏高度
    public static int getStatusHeight(Activity activity){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        int height = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            height = activity.getResources().getDimensionPixelSize(x);
            Log.i("StatusHeight", String.valueOf(height));
            return height;
        } catch (Exception e1) {
            Log.i("StatusHeight", "get status bar height fail");
            e1.printStackTrace();
            return 75;
        }
    }

    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static void toast(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    public static String getVersionName(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
        String versionName = packInfo.versionName;
        Logger.d(versionName);
        return versionName;
    }

}
