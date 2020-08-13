package org.techtown.songthemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CategoryActivity extends AppCompatActivity {
    Intent intent;
    ImageButton womenCloth_img, menCloth_img, fashion_img, beauty_img, birth_img, sports_img,
            digital_img, book_img, life_img, vehicle_img, star_img, etc_img;
    Button btnCategory, btnSearchNew, btnMypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //여성의류 중분류
        womenCloth_img = (ImageButton)findViewById(R.id.womenCloth_img);
        womenCloth_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, WomenCategoryActivity.class);
                startActivity(intent);
            }
        });

        //남성의류 버튼
        menCloth_img = (ImageButton)findViewById(R.id.menCloth_img);
        menCloth_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "남성의류");
                startActivity(intent);
            }
        });

        //패션잡화 버튼
        fashion_img = (ImageButton)findViewById(R.id.fashion_img);
        fashion_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "패션잡화");
                startActivity(intent);
            }
        });

        //뷰티/미용 버튼
        beauty_img = (ImageButton)findViewById(R.id.beauty_img);
        beauty_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "뷰티/미용");
                startActivity(intent);
            }
        });

        //유아동/출산 버튼
        birth_img = (ImageButton)findViewById(R.id.birth_img);
        birth_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "유아동/출산");
                startActivity(intent);
            }
        });

        //스포츠/레저 버튼
        sports_img = (ImageButton)findViewById(R.id.sports_img);
        sports_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "스포츠/레저");
                startActivity(intent);
            }
        });

        //디지털/가전 버튼
        digital_img = (ImageButton)findViewById(R.id.digital_img);
        digital_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "디지털/가전");
                startActivity(intent);
            }
        });

        //도서/티켓/애완 버튼
        book_img = (ImageButton)findViewById(R.id.book_img);
        book_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "도서/티켓/애완");
                startActivity(intent);
            }
        });

        //생활/문구/식품 버튼
        life_img = (ImageButton)findViewById(R.id.life_img);
        life_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "생활/문구/식품");
                startActivity(intent);
            }
        });

        //차량/오토바이 버튼
        vehicle_img = (ImageButton)findViewById(R.id.vehicle_img);
        vehicle_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "차량/오토바이");
                startActivity(intent);
            }
        });

        //스타굿즈 버튼
        star_img = (ImageButton)findViewById(R.id.star_img);
        star_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "스타굿즈");
                startActivity(intent);
            }
        });

        //기타 버튼
        etc_img = (ImageButton)findViewById(R.id.etc_img);
        etc_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryItemListActivity.class);
                intent.putExtra("categoryName", "기타");
                startActivity(intent);
            }
        });

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CategoryActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}
