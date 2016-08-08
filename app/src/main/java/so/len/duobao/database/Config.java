package so.len.duobao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Config {
    private static Config mConfig;
    private final ConfigOpenHelp openHelp;

    private Config(Context context) {
        openHelp = new ConfigOpenHelp(context);
    }

    /**
     * get the instance of config class
     *
     * @param context context
     * @return config object
     */
    public static Config getInstance(Context context) {
        if (mConfig == null)
            synchronized (Config.class) {
                if (mConfig == null)
                    mConfig = new Config(context);
            }
        return mConfig;
    }

    /**
     * save config to database
     *
     * @param key    key
     * @param config config
     */
    public synchronized void putConfig(String key, String config) {
        SQLiteDatabase db = openHelp.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("key", key);
        values.put("value", config);
        if (exit(db, key) == null) {
            db.insert("config", null, values);
        } else {
            db.update("config", values, "key=?", new String[]{key});
        }
        db.close();
    }

    /**
     * get config from database
     *
     * @param key key
     */
    public synchronized String getConfig(String key) {
        SQLiteDatabase db = openHelp.getReadableDatabase();
        String value = exit(db, key);
        db.close();
        return value;
    }

    private String exit(SQLiteDatabase db, String key) {
        Cursor cursor = null;
        String value = null;
        try {
            cursor = db.rawQuery("SELECT value FROM config WHERE key=?", new String[]{key});
            if (cursor.moveToFirst())
                value = cursor.getString(0);
        } finally {
            if(cursor!=null)
            cursor.close();
        }
        return value;
    }
}
