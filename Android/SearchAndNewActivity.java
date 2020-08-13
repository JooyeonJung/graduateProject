package org.techtown.songthemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchAndNewActivity extends AppCompatActivity {
    Button btnUpload, btnCategory, btnSearchNew, btnMypage;
    Intent intent = null;
    ImageButton searchBtn, allItemBtn;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_new);

        //내 상품 판매하기
        btnUpload = (Button)findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SearchAndNewActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SearchAndNewActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SearchAndNewActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SearchAndNewActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        //검색 버튼
        search = (EditText)findViewById(R.id.search);
        searchBtn = (ImageButton)findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //여기서부터...
                //editText에 있는 검색어 가지고 화면 넘어감...같은거랑 contains 써서
                String word = search.getText().toString();
                intent = new Intent(SearchAndNewActivity.this, ItemListActivity.class);
                intent.putExtra("word", word);
                startActivity(intent);
            }
        });

        //전체상품 보기 버튼
        allItemBtn = (ImageButton)findViewById(R.id.allItemBtn);
        allItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SearchAndNewActivity.this, AllItemListActivity.class);
                startActivity(intent);
            }
        });
    }
}
