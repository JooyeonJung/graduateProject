package org.techtown.songthemarket;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {
    DBAdapter dbAdapter;
    CheckBox checkBox;
    Button btnCategory, btnSearchNew, btnMypage;
    TextView textView_name, textView_price, textView_category_1, textView_category_2
            , textView_keyword1, textView_keyword2, textView_keyword3, textView_description;
    static ArrayList<String> like = new ArrayList<>();
    static ArrayList<String> recent = new ArrayList<>();
    String name;
    private static final String TAG = "Detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        checkBox = (CheckBox) findViewById(R.id.checkBox);

        //상세정보
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        textView_name = (TextView)findViewById(R.id.textView_name);
        textView_name.setText(name);

        this.dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        Cursor cursor = dbAdapter.showItemDetail(name);
        startManagingCursor(cursor);

        cursor.moveToNext();
        String price = cursor.getString(0);
        String keyword1 = cursor.getString(1);
        String keyword2 = cursor.getString(2);
        String keyword3 = cursor.getString(3);
        String description = cursor.getString(4);
        String category_name_1 = cursor.getString(5);
        String category_name_2 = cursor.getString(6);

        //카테고리 문자 string으로 바꾸기
        //중분류
        if(category_name_2.equals("310010")){
            category_name_2 = "긴팔 티셔츠";
        }
        else if(category_name_2.equals("310020")){
            category_name_2 = "반팔 티셔츠";
        }
        else if(category_name_2.equals("310030")){
            category_name_2 = "맨투맨/후드티";
        }
        else if(category_name_2.equals("310040")){
            category_name_2 = "블라우스";
        }
        else if(category_name_2.equals("310050")){
            category_name_2 = "셔츠/남방";
        }
        else if(category_name_2.equals("310060")){
            category_name_2 = "니트/스웨터";
        }
        else if(category_name_2.equals("310070")){
            category_name_2 = "가디건";
        }
        else if(category_name_2.equals("310080")){
            category_name_2 = "조끼/베스트";
        }
        else if(category_name_2.equals("310090")){
            category_name_2 = "야상/점퍼/패딩";
        }
        else if(category_name_2.equals("310100")){
            category_name_2 = "자켓";
        }
        else if(category_name_2.equals("310110")){
            category_name_2 = "코트";
        }
        else if(category_name_2.equals("310120")){
            category_name_2 = "원피스";
        }
        else if(category_name_2.equals("310130")){
            category_name_2 = "스커트/치마";
        }
        else if(category_name_2.equals("310140")){
            category_name_2 = "청바지/스키니";
        }
        else if(category_name_2.equals("310150")){
            category_name_2 = "면/캐주얼바지";
        }
        else if(category_name_2.equals("310160")){
            category_name_2 = "반바지/핫팬츠";
        }
        else if(category_name_2.equals("310170")){
            category_name_2 = "레깅스";
        }
        else if(category_name_2.equals("310180")){
            category_name_2 = "비즈니스 정장";
        }
        else if(category_name_2.equals("310190")){
            category_name_2 = "트레이닝";
        }
        else if(category_name_2.equals("310200")){
            category_name_2 = "언더웨어/속옷";
        }
        else if(category_name_2.equals("310210")){
            category_name_2 = "빅사이즈";
        }
        else if(category_name_2.equals("310220")){
            category_name_2 = "테마/이벤트";
        }

        //대분류
        if(category_name_1.equals("310")){
            category_name_1 = "여성의류";
        }
        else if(category_name_1.equals("320")){
            category_name_1 = "남성의류";
        }
        else if(category_name_1.equals("400")){
            category_name_1 = "패션잡화";
        }
        else if(category_name_1.equals("410")){
            category_name_1 = "뷰티/미용";
        }
        else if(category_name_1.equals("500")){
            category_name_1 = "유아동/출산";
        }
        else if(category_name_1.equals("600")){
            category_name_1 = "디지털/가전";
        }
        else if(category_name_1.equals("700")){
            category_name_1 = "스포츠/레저";
        }
        else if(category_name_1.equals("750")){
            category_name_1 = "차량/오토바이";
        }
        else if(category_name_1.equals("800")){
            category_name_1 = "생활/문구/식품";
        }
        else if(category_name_1.equals("900")){
            category_name_1 = "도서/티켓/애완";
        }
        else if(category_name_1.equals("910")){
            category_name_1 = "스타굿즈";
        }
        else if(category_name_1.equals("999")){
            category_name_1 = "기타";
        }

        textView_price = (TextView)findViewById(R.id.textView_price);
        textView_price.setText(price + "원");

        textView_category_1 = (TextView)findViewById(R.id.textView_category_1);
        textView_category_1.setText(category_name_1);

        textView_category_2 = (TextView)findViewById(R.id.textView_category_2);
        textView_category_2.setText(category_name_2);

        textView_keyword1 = (TextView)findViewById(R.id.textView_keyword1);
        textView_keyword1.setText("#" + keyword1);

        textView_keyword2 = (TextView)findViewById(R.id.textView_keyword2);
        textView_keyword2.setText("#" + keyword2);

        textView_keyword3 = (TextView)findViewById(R.id.textView_keyword3);
        textView_keyword3.setText("#" + keyword3);

        textView_description = (TextView)findViewById(R.id.textView_description);
        description = description.replace("\\n", "\n");
        textView_description.setText(description);

        //카테고리 버튼
        btnCategory = (Button)findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button)findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button)findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        //찜하기 상태
        for(int i = 0; i < like.size(); i++){
            if(name.equals(like.get(i))){
                checkBox.setChecked(true);
                break;
            }
        }

        recent.add(name);

        //Toast.makeText(getApplicationContext(), recent.get(0) + " 확인", Toast.LENGTH_SHORT).show();
        //최대 50개 까지만 보이도록
        if(recent.size() > 50){
            recent.remove(0);
        }
    }

    public void checkboxClick(View v){
        if(checkBox.isChecked()){
            Toast.makeText(getApplicationContext(),"찜하기", Toast.LENGTH_SHORT).show();
            like.add(name);
            //Toast.makeText(getApplicationContext(), like.get(0), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"찜하기 취소", Toast.LENGTH_SHORT).show();
            like.remove(name);
        }
    }

    ArrayList<String> getLikeList(){
        return like;
    }

    ArrayList<String> getRecentList(){
        return recent;
    }

    public static void println(String data){
        Log.d(TAG, data);
    }
}
