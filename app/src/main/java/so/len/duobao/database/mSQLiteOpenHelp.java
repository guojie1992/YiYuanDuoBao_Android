package so.len.duobao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class mSQLiteOpenHelp extends SQLiteOpenHelper {
    private final static int mSQLITE_VERSION = 1;
    private final static String mSQLITE_NAME = "m.db";

    public mSQLiteOpenHelp(Context context) {
        super(context, mSQLITE_NAME, null, mSQLITE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
