package org.techtown.songthemarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    String tableName = "bunjangDataTable";
    private static final String TAG = "DBHelper";
    private static SQLiteDatabase db;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static void println(String data){
        Log.d(TAG, data);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        println("onCreate() 호출됨.");
        String sql = "create table if not exists " + tableName + "(_id integer primary key, name text, price integer, keyword1 text, keyword2 text, keyword3 text, description text, category_name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade 호출됨 : " + oldVersion +", " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public boolean insertData(int _id, String name, int price, String keyword1, String keyword2, String keyword3, String description, String category_name) {
        println("insertData() 호출됨.");

        String sql = "insert into " + tableName + "(_id, name, price, keyword1, keyword2, keyword3, description, category_name) values(?,?,?,?,?,?,?,?);";
        Object[] params = {_id, name, price, keyword1, keyword2, keyword3, description, category_name};

        db = this.getWritableDatabase();
        db.execSQL(sql, params);

        println("데이터 추가함.");
        return true;
    }
    /*
    //여기부터 고치기
    public boolean updateData(int _id, String name, int price, String keyword1, String keyword2, String keyword3, String description, String category_name) {
        println("updateData() 호출됨.");

        String sql = "UPDATE " + tableName + " set name ='" + name + "', tel='" + tel+"',birth ='"+birth+"' WHERE name = '" + name +"'";

        db = this.getWritableDatabase();
        db.execSQL(sql);
        return true;
    }

    public Integer deleteData(String name) {
        db = this.getWritableDatabase();
        return db.delete(tableName,"name = ? ", new String[]{name});
    }
    */
}
