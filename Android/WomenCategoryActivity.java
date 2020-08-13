package org.techtown.songthemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WomenCategoryActivity extends AppCompatActivity {
    ImageButton imgBtn1, imgBtn2, imgBtn3, imgBtn4, imgBtn5, imgBtn6, imgBtn7, imgBtn8, imgBtn9, imgBtn10,
            imgBtn11, imgBtn12, imgBtn13, imgBtn14, imgBtn15, imgBtn16, imgBtn17, imgBtn18, imgBtn19,
            imgBtn20, imgBtn21, imgBtn22;
    Intent intent;
    Button btnCategory, btnSearchNew, btnMypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_category);

        //긴팔 티셔츠 버튼
        imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "긴팔 티셔츠");
                startActivity(intent);
            }
        });

        //반팔 티셔츠 버튼
        imgBtn2 = (ImageButton)findViewById(R.id.imgBtn2);
        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "반팔 티셔츠");
                startActivity(intent);
            }
        });

        //맨투맨/후드티 버튼
        imgBtn3 = (ImageButton)findViewById(R.id.imgBtn3);
        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "맨투맨/후드티");
                startActivity(intent);
            }
        });

        //블라우스 버튼
        imgBtn4 = (ImageButton)findViewById(R.id.imgBtn4);
        imgBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "블라우스");
                startActivity(intent);
            }
        });

        //셔츠/남방 버튼
        imgBtn5 = (ImageButton)findViewById(R.id.imgBtn5);
        imgBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "셔츠/남방");
                startActivity(intent);
            }
        });

        //니트/스웨터 버튼
        imgBtn6 = (ImageButton)findViewById(R.id.imgBtn6);
        imgBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "니트/스웨터");
                startActivity(intent);
            }
        });

        //가디건 버튼
        imgBtn7 = (ImageButton)findViewById(R.id.imgBtn7);
        imgBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "가디건");
                startActivity(intent);
            }
        });

        //조끼/베스트 버튼
        imgBtn8 = (ImageButton)findViewById(R.id.imgBtn8);
        imgBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "조끼/베스트");
                startActivity(intent);
            }
        });

        //야상/점퍼/패딩 버튼
        imgBtn9 = (ImageButton)findViewById(R.id.imgBtn9);
        imgBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "야상/점퍼/패딩");
                startActivity(intent);
            }
        });

        //자켓 버튼
        imgBtn10 = (ImageButton)findViewById(R.id.imgBtn10);
        imgBtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "자켓");
                startActivity(intent);
            }
        });

        //코트 버튼
        imgBtn11 = (ImageButton)findViewById(R.id.imgBtn11);
        imgBtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "코트");
                startActivity(intent);
            }
        });

        //원피스 버튼
        imgBtn12 = (ImageButton)findViewById(R.id.imgBtn12);
        imgBtn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "원피스");
                startActivity(intent);
            }
        });

        //스커트/치마 버튼
        imgBtn13 = (ImageButton)findViewById(R.id.imgBtn13);
        imgBtn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "스커트/치마");
                startActivity(intent);
            }
        });

        //청바지/스키니 버튼
        imgBtn14 = (ImageButton)findViewById(R.id.imgBtn14);
        imgBtn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "청바지/스키니");
                startActivity(intent);
            }
        });

        //면/캐주얼바지 버튼
        imgBtn15 = (ImageButton)findViewById(R.id.imgBtn15);
        imgBtn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "면/캐주얼바지");
                startActivity(intent);
            }
        });

        //반바지/핫팬츠 버튼
        imgBtn16 = (ImageButton)findViewById(R.id.imgBtn16);
        imgBtn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "반바지/핫팬츠");
                startActivity(intent);
            }
        });

        //레깅스 버튼
        imgBtn17 = (ImageButton)findViewById(R.id.imgBtn17);
        imgBtn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "레깅스");
                startActivity(intent);
            }
        });

        //비즈니스 정장 버튼
        imgBtn18 = (ImageButton)findViewById(R.id.imgBtn18);
        imgBtn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "비즈니스 정장");
                startActivity(intent);
            }
        });

        //트레이닝 버튼
        imgBtn19 = (ImageButton)findViewById(R.id.imgBtn19);
        imgBtn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "트레이닝");
                startActivity(intent);
            }
        });

        //언더웨어/속옷 버튼
        imgBtn20 = (ImageButton)findViewById(R.id.imgBtn20);
        imgBtn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "언더웨어/속옷");
                startActivity(intent);
            }
        });

        //빅사이즈 버튼
        imgBtn21 = (ImageButton)findViewById(R.id.imgBtn21);
        imgBtn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "빅사이즈");
                startActivity(intent);
            }
        });

        //테마/이벤트 버튼
        imgBtn22 = (ImageButton)findViewById(R.id.imgBtn22);
        imgBtn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "테마/이벤트");
                startActivity(intent);
            }
        });

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WomenCategoryActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}
