package so.len.duobao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    public static int getStatusHeight(Activity activity) {
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
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static String getVersionName(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        String versionName = packInfo.versionName;
        return versionName;
    }

    public static int getVersionCode(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        int versionCode = packInfo.versionCode;
        return versionCode;
    }

    public static File downFile(String httpUrl, Context context, Handler handler) {
        String fileName = httpUrl.substring(httpUrl.lastIndexOf("/") + 1);
        File tmpFile = new File("/sdcard/update");
        if (!tmpFile.exists()) {
            tmpFile.mkdir();
        }
        File file = new File("/sdcard/update/" + fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            URL url = new URL(httpUrl);
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();

                int contentLength = conn.getContentLength();
                int currentLength = 0;

                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[5120];
                conn.connect();
                double count = 0;
                if (conn.getResponseCode() >= 400) {
                    toast(context, "连接超时");
                } else {
                    while (count <= 100) {
                        if (is != null) {
                            int numRead = is.read(buf);
                            if (numRead <= 0) {
                                break;
                            } else {
                                fos.write(buf, 0, numRead);
                                currentLength += numRead;
                                int progress = (int) Math.floor((100 * currentLength)/contentLength);
                                Message msg = new Message();
                                msg.obj = progress;
                                handler.sendMessage(msg);
                            }
                        } else {
                            break;
                        }
                    }
                }
                conn.disconnect();
                fos.close();
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file;
    }

    public static void openFile(File file, Context context) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

}
