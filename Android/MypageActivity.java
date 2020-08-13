package org.techtown.songthemarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MypageActivity extends AppCompatActivity {
    Button btnCategory, btnSearchNew, btnMypage;
    ImageButton likeNext, recentNext, imgBtn1, imgBtn2, imgBtn3;
    Intent intent = null;
    ArrayList<String> likeCategory = new ArrayList<>();
    CategoryItemListActivity item;
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        //관심있는 카테고리
        item = new CategoryItemListActivity();
        likeCategory = item.getLikeCategoryList();

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);

        if(likeCategory.size() == 0){
            tv1.setText("_______________");
            tv2.setText("_______________");
            tv3.setText("_______________");
        }
        else if(likeCategory.size() == 1){
            tv1.setText(likeCategory.get(0));
            tv2.setText("_______________");
            tv3.setText("_______________");
        }
        else if(likeCategory.size() == 2){
            tv1.setText(likeCategory.get(0));
            tv2.setText(likeCategory.get(1));
            tv3.setText("_______________");
        }
        else if(likeCategory.size() == 3){
            tv1.setText(likeCategory.get(0));
            tv2.setText(likeCategory.get(1));
            tv3.setText(likeCategory.get(2));
        }

        imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeCategory.size() > 0){
                    intent = new Intent(MypageActivity.this, CategoryItemListActivity.class);
                    intent.putExtra("categoryName", likeCategory.get(0));
                    startActivity(intent);
                }
            }
        });

        imgBtn2 = (ImageButton)findViewById(R.id.imgBtn2);
        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeCategory.size() > 1){
                    intent = new Intent(MypageActivity.this, CategoryItemListActivity.class);
                    intent.putExtra("categoryName", likeCategory.get(1));
                    startActivity(intent);
                }
            }
        });

        imgBtn3 = (ImageButton)findViewById(R.id.imgBtn3);
        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeCategory.size() > 2){
                    intent = new Intent(MypageActivity.this, CategoryItemListActivity.class);
                    intent.putExtra("categoryName", likeCategory.get(2));
                    startActivity(intent);
                }
            }
        });

       //찜한상품 다음 버튼
        likeNext = (ImageButton)findViewById(R.id.likeNext);
        likeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MypageActivity.this, LikeItemActivity.class);
                startActivity(intent);
            }
        });

        //최근 본 상품 다음 버튼
        recentNext = (ImageButton)findViewById(R.id.recentNext);
        recentNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MypageActivity.this, RecentItemActivity.class);
                startActivity(intent);
            }
        });

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MypageActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MypageActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MypageActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}
