package org.techtown.songthemarket;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryItemListActivity extends AppCompatActivity {
    SQLiteDatabase db;
    TextView textView_title;
    Button btnCategory, btnSearchNew, btnMypage;
    DBAdapter dbAdapter;
    static ArrayList<String> likeCategory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item_list);

        Intent intent = getIntent();
        String title = intent.getStringExtra("categoryName");

        textView_title = (TextView)findViewById(R.id.textView_title);
        textView_title.setText(title);

        this.dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        //선택한 카테고리로 검색
        Cursor cursor = dbAdapter.showCategoryItem(title);
        startManagingCursor(cursor);
        String[] from = { "name", "keyword1", "keyword2", "keyword3", "price"};
        int[] to = { R.id.textView_name, R.id.textView_keyword1, R.id.textView_keyword2, R.id.textView_keyword3, R.id.textView_price };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(CategoryItemListActivity.this, R.layout.listitem, cursor, from, to);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        //클릭시 상세 정보
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name =((TextView) view.findViewById(R.id.textView_name)).getText().toString();
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CategoryItemListActivity.this, ItemDetailActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryItemListActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryItemListActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryItemListActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        //관심있는 카테고리
        if(!likeCategory.contains(title)){
            likeCategory.add(title);
        }
        //최대 3개
        if(likeCategory.size() > 3){
            likeCategory.remove(0);
        }
    }

    ArrayList<String> getLikeCategoryList(){
        return likeCategory;
    }
}
