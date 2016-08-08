package so.len.duobao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConfigOpenHelp extends SQLiteOpenHelper {

    private static final String NAME = "config.db";
    private static final int VERSION = 1;

    public ConfigOpenHelp(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE config(id INTEGER PRIMARY KEY AUTOINCREMENT,key TEXT,value TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
