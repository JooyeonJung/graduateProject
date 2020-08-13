package org.techtown.songthemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ItemListActivity extends AppCompatActivity {
    DBAdapter dbAdapter;
    Button btnCategory, btnSearchNew, btnMypage;
    EditText search;
    ImageButton searchBtn;
    private static final String TAG = "ItemList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        //검색어 받아옴
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");

        //Toast.makeText(getApplicationContext(), "검색어 : " + word, Toast.LENGTH_SHORT).show();

        search = (EditText)findViewById(R.id.search);
        search.setText(word);

        this.dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        //입력한 검색어로 검색
        Cursor cursor = dbAdapter.showWordData(word);
        startManagingCursor(cursor);
        String[] from = { "name", "keyword1", "keyword2", "keyword3", "price"};
        int[] to = { R.id.textView_name, R.id.textView_keyword1, R.id.textView_keyword2, R.id.textView_keyword3, R.id.textView_price };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(ItemListActivity.this, R.layout.listitem, cursor, from, to);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        //클릭시 상세 정보
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                println("onItemClick() 호출됨.");
                String name =((TextView) view.findViewById(R.id.textView_name)).getText().toString();
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ItemListActivity.this, ItemDetailActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        //검색 버튼
        searchBtn = (ImageButton)findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = search.getText().toString();
                Cursor cursor = dbAdapter.showWordData(word);
                startManagingCursor(cursor);
                String[] from = { "name", "keyword1", "keyword2", "keyword3", "price"};
                int[] to = { R.id.textView_name, R.id.textView_keyword1, R.id.textView_keyword2, R.id.textView_keyword3, R.id.textView_price };
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(ItemListActivity.this, R.layout.listitem, cursor, from, to);
                ListView list = (ListView) findViewById(R.id.list);
                list.setAdapter(adapter);
            }
        });

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemListActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemListActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemListActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void println(String data){
        Log.d(TAG, data);
    }
}
