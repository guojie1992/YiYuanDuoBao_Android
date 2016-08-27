package so.len.duobao.mPresenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import so.len.duobao.api.SERVER;
import so.len.duobao.customView.iOSActionSheetDialog;
import so.len.duobao.database.Config;
import so.len.duobao.http.UploadUtil;
import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.IPersonalInfoModel;
import so.len.duobao.mModel.PersonalInfoModel;
import so.len.duobao.mView.IPersonalInfoView;

/**
 * Created by Chung on 2016/8/12.
 */
public class PersonalInfoPresenter {
    private IPersonalInfoModel iPersonalInfoModel;
    private IPersonalInfoView iPersonalInfoView;
    private Context context;
    private File headPIC;

    public PersonalInfoPresenter(IPersonalInfoView iPersonalInfoView, Context context) {
        this.context = context;
        this.iPersonalInfoView = iPersonalInfoView;
        this.iPersonalInfoModel = new PersonalInfoModel(context);
        this.headPIC = new File(context.getFilesDir(), "head.png");
    }

    public void initView(){
        iPersonalInfoModel.getServerData(new IHttpCompleteListener() {
            @Override
            public void loadComplete(String msg) {
                iPersonalInfoView.initView(iPersonalInfoModel.getPersonalBean());
            }

            @Override
            public void loadError(String msg) {

            }
        });
    }


    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        if (uri == null) {
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪

        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        ((Activity) context).startActivityForResult(intent, iOSActionSheetDialog.CROP);
    }

    /**
     * 保存裁剪之后的图片数据
     * @param data pic data
     */
    public void saveImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            try {
                FileOutputStream out = new FileOutputStream(headPIC);
                photo.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();

                MyAsyncTask myTask = new MyAsyncTask();
                myTask.execute();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            Map<String, String> agrs = new HashMap<String, String>();
            Map<String, File> files = new HashMap<String, File>();
            agrs.put("uid", Config.getInstance(context).getConfig("uid"));
            files.put("upload", headPIC);
            UploadUtil.post(SERVER.CHANGE_HEAD_PIC, agrs, files);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            initView();
        }

        public MyAsyncTask() {
            super();
        }
    }



}
