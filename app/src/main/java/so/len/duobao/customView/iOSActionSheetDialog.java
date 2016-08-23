package so.len.duobao.customView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import so.len.duobao.R;

import java.io.File;
public class iOSActionSheetDialog {
    private Context context;
    private Dialog dialog;
    private Button btn_take_photo;
    private Button btn_choose_photo;
    private Button btn_cancel;
    private Display display;

    public final static int TAKE = 1;
    public final static int CHOOSE = 2;
    public final static int CROP = 0;

    public iOSActionSheetDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public iOSActionSheetDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_iosactionsheetdialog, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        // 获取自定义Dialog布局中的控件
        btn_take_photo = (Button) view.findViewById(R.id.btn_take_photo);
        btn_take_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.png")));
                ((Activity) context).startActivityForResult(intentFromCapture, TAKE);
            }
        });
        btn_choose_photo = (Button) view.findViewById(R.id.btn_choose_photo);
        btn_choose_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intentFromGallery = new Intent();
                intentFromGallery.setType("image/*"); // 设置文件类型
                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                ((Activity) context).startActivityForResult(intentFromGallery, CHOOSE);
            }
        });
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.iOSActionSheetDialogStyle);
        dialog.setContentView(view);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

//    public iOSActionSheetDialog setTitle(String title) {
//        showTitle = true;
//        txt_title.setVisibility(View.VISIBLE);
//        txt_title.setText(title);
//        return this;
//    }

    public iOSActionSheetDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public iOSActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public void show() {
        dialog.show();
    }
}