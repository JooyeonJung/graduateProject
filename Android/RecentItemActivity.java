package org.techtown.songthemarket;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecentItemActivity extends AppCompatActivity {
    DBAdapter dbAdapter;
    Button btnCategory, btnSearchNew, btnMypage;
    Intent intent = null;
    ArrayList<String> recent = new ArrayList<>();
    String s = null;
    ItemDetailActivity item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_item);

        //최근 본 상품 목록 가져오기 위해 객체 생성
        item = new ItemDetailActivity();
        recent = item.getRecentList();
        //Toast.makeText(getApplicationContext(), like.get(1), Toast.LENGTH_SHORT).show();
        for(int i = 0; i < recent.size(); i++){
            s += "\"" + recent.get(i) + "\",";
        }
        if(s != null){
            s = s.substring(4, s.length() - 1);
        }

        this.dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        //최근 본 상품 검색
        Cursor cursor = dbAdapter.showLikeData(s);
        startManagingCursor(cursor);
        String[] from = { "name", "keyword1", "keyword2", "keyword3", "price"};
        int[] to = { R.id.textView_name, R.id.textView_keyword1, R.id.textView_keyword2, R.id.textView_keyword3, R.id.textView_price };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(RecentItemActivity.this, R.layout.listitem, cursor, from, to);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        //클릭시 상세 정보
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name =((TextView) view.findViewById(R.id.textView_name)).getText().toString();
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RecentItemActivity.this, ItemDetailActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RecentItemActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RecentItemActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RecentItemActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}
