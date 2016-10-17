package so.len.duobao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.logging.Logger;

import so.len.duobao.utils.CommonUtils;

public class ConfigOpenHelp extends SQLiteOpenHelper {

    private static final String NAME = "config.db";
    private static int VERSION_CODE = 1;

    public ConfigOpenHelp(Context context) {
        super(context, NAME, null, VERSION_CODE);
        try {
            this.VERSION_CODE = CommonUtils.getVersionCode(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE config(id INTEGER PRIMARY KEY AUTOINCREMENT,key TEXT,value TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        com.orhanobut.logger.Logger.d("VERSION_CODE" + VERSION_CODE);
        com.orhanobut.logger.Logger.d("i:" + i + "j:" + j);
    }
}
