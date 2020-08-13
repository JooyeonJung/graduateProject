package org.techtown.songthemarket;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    DatabaseHelper mDbHelper;
    SQLiteDatabase mDb;
    Context mCtx;
    String databaseName = "mydata_test_17.db";   //바꾸기
    String tableName = "bunjangDataTable_test_17";   //바꾸기
    int databaseVersion = 1;
    private static final String TAG = "DBAdapter";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PRICE = "price";
    public static final String KEY_KEYWORD_1 = "keyword1";
    public static final String KEY_KEYWORD_2 = "keyword2";
    public static final String KEY_KEYWORD_3 = "keyword3";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_CATEGORY_NAME_1 = "category_name_1";
    public static final String KEY_CATEGORY_NAME_2 = "category_name_2";


    public class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context) {
            super(context, databaseName,null, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            println("onCreate() 호출됨.");
            String sql = "create table if not exists " + tableName + "(_id integer primary key, name text, price text, keyword1 text, keyword2 text, keyword3 text, description text, category_name_1 text, category_name_2 text);";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            println("onUpgrade() 호출됨.");
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            onCreate(mDb);
        }
    }

    public DBAdapter(Context context){
        this.mCtx = context;
    }

    public DBAdapter open() throws SQLException {
        println("open() 호출됨.");

        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDbHelper.close();
    }

    public boolean insertData(String name, String price, String keyword1, String keyword2, String keyword3, String description, String category_name_1, String category_name_2) {
        println("insertData() 호출됨.");

        String sql = "insert into " + tableName + "(name, price, keyword1, keyword2, keyword3, description, category_name_1, category_name_2) values(?,?,?,?,?,?,?,?);";
        Object[] params = {name, price, keyword1, keyword2, keyword3, description, category_name_1, category_name_2};

        mDb.execSQL(sql, params);

        return true;
    }

    public Cursor fetchAllNotes(){
        return mDb.query(tableName, new String[] {KEY_ID, KEY_NAME, KEY_PRICE, KEY_KEYWORD_1, KEY_KEYWORD_2, KEY_KEYWORD_3, KEY_DESCRIPTION, KEY_CATEGORY_NAME_1, KEY_CATEGORY_NAME_2},  null, null, null, null, null);
    }

    public Cursor showAllData(){
        println("showAllData() 호출됨.");

        return mDb.rawQuery("SELECT * FROM " + tableName, null);
    }

    public Cursor showWordData(String word){
        println("showWordData() 호출됨.");
        //word는 검색어

        return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE name LIKE '%" + word + "%'", null);
    }

    public Cursor showLikeData(String s){
        println("showLikeData() 호출됨.");
        //s는 찜한, 최근 본 상품

        return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE name IN (" + s + ")", null);
    }

    public Cursor showItemDetail(String n){
        println("showItemDetail() 호출됨.");

        return mDb.rawQuery("SELECT price, keyword1, keyword2, keyword3, description, category_name_1, category_name_2 FROM " + tableName + " WHERE name='" + n + "'", null);
    }

    public Cursor showCategoryItem(String title){
        println("showCategoryItem() 호출됨.");
        String category = null;
        //title은 카테고리

        if(title.equals("긴팔 티셔츠")){
            category = "310010";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("반팔 티셔츠")){
            category = "310020";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("맨투맨/후드티")){
            category = "310030";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("블라우스")){
            category = "310040";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("셔츠/남방")){
            category = "310050";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("니트/스웨터")){
            category = "310060";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("가디건")){
            category = "310070";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("조끼/베스트")){
            category = "310080";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("야상/점퍼/패딩")){
            category = "310090";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("자켓")){
            category = "310100";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("코트")){
            category = "310110";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("원피스")){
            category = "310120";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("스커트/치마")){
            category = "310130";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("청바지/스키니")){
            category = "310140";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("면/캐주얼바지")){
            category = "310150";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("반바지/핫팬츠")){
            category = "310160";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("레깅스")){
            category = "310170";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("비즈니스 정장")){
            category = "310180";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("트레이닝")){
            category = "310190";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("언더웨어/속옷")){
            category = "310200";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("빅사이즈")){
            category = "310210";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("테마/이벤트")){
            category = "310220";

            //string, 숫자 매칭
            return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_2=" + category, null);
        }
        else if(title.equals("남성의류")){
            category = "320";
        }
        else if(title.equals("패션잡화")){
            category = "400";
        }
        else if(title.equals("뷰티/미용")){
            category = "410";
        }
        else if(title.equals("유아동/출산")){
            category = "500";
        }
        else if(title.equals("디지털/가전")){
            category = "600";
        }
        else if(title.equals("스포츠/레저")){
            category = "700";
        }
        else if(title.equals("차량/오토바이")){
            category = "750";
        }
        else if(title.equals("생활/문구/식품")){
            category = "800";
        }
        else if(title.equals("도서/티켓/애완")){
            category = "900";
        }
        else if(title.equals("스타굿즈")){
            category = "910";
        }
        else if(title.equals("기타")){
            category = "999";
        }

        //string, 숫자 매칭
        return mDb.rawQuery("SELECT * FROM " + tableName +" WHERE category_name_1=" + category, null);
    }

    public static void println(String data){
        Log.d(TAG, data);
    }
}
