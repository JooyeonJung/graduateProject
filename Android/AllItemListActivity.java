package org.techtown.songthemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class AllItemListActivity extends AppCompatActivity {
    DBAdapter dbAdapter;
    Button btnCategory, btnSearchNew, btnMypage;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_item_list);

        this.dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        //전체 데이터 출력
        Cursor cursor = dbAdapter.showAllData();
        startManagingCursor(cursor);
        String[] from = { "name", "keyword1", "keyword2", "keyword3", "price"};
        int[] to = { R.id.textView_name, R.id.textView_keyword1, R.id.textView_keyword2, R.id.textView_keyword3, R.id.textView_price };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(AllItemListActivity.this, R.layout.listitem, cursor, from, to);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        //클릭시 상세 정보
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name =((TextView) view.findViewById(R.id.textView_name)).getText().toString();
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AllItemListActivity.this, ItemDetailActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AllItemListActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AllItemListActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AllItemListActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}
