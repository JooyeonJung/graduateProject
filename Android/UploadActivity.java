package org.techtown.songthemarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UploadActivity extends AppCompatActivity {
    NetworkService networkService;
    DBAdapter dbAdapter;
    private Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    CheckBox checkBox1, checkBox2;
    EditText edit_keyword1, edit_keyword2, edit_keyword3, edit_price, edit_description;
    Button btn_finish, btnCategory, btnSearchNew, btnMypage;
    ImageButton btnServerPost, btnServerGet;
    Intent intent = null;
    EditText edit_title, category_recommend_1, category_recommend_2;
    private static final String TAG = "Upload";

    //서버
    public static class AppHelper {
        public static RequestQueue requestQueue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //서버
        btnServerGet = (ImageButton) findViewById(R.id.btnServerGet);
        btnServerGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
                Toast.makeText(getApplicationContext(), "카테고리 검색 중...", Toast.LENGTH_SHORT).show();
            }
        });

        if (AppHelper.requestQueue == null) {

            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        this.dbAdapter = new DBAdapter(this);

        //spinner
        arrayList = new ArrayList<>();
        arrayList.add("--------(지역 선택 안 함)--------");
        arrayList.add("강원도");
        arrayList.add("경기도");
        arrayList.add("경상남도");
        arrayList.add("경상북도");
        arrayList.add("광주광역시");
        arrayList.add("대구광역시");
        arrayList.add("대전광역시");
        arrayList.add("부산광역시");
        arrayList.add("서울특별시");
        arrayList.add("세종특별자치시");
        arrayList.add("울산광역시");
        arrayList.add("인천광역시");
        arrayList.add("전라남도");
        arrayList.add("전라북도");
        arrayList.add("제주특별자치도");
        arrayList.add("충청남도");
        arrayList.add("충청북도");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //checkbox
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        //edittext
        edit_title = (EditText) findViewById(R.id.edit_title);
        category_recommend_1 = (EditText) findViewById(R.id.category_recommend_1);
        category_recommend_2 = (EditText) findViewById(R.id.category_recommend_2);
        edit_keyword1 = (EditText) findViewById(R.id.edit_keyword1);
        edit_keyword2 = (EditText) findViewById(R.id.edit_keyword2);
        edit_keyword3 = (EditText) findViewById(R.id.edit_keyword3);
        edit_price = (EditText) findViewById(R.id.edit_price);
        edit_description = (EditText) findViewById(R.id.edit_description);

        btn_finish = (Button) findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_title.getText().toString();
                String price = edit_price.getText().toString();
                String keyword1 = edit_keyword1.getText().toString();
                String keyword2 = edit_keyword2.getText().toString();
                String keyword3 = edit_keyword3.getText().toString();
                String description = edit_description.getText().toString();
                String category_name_1 = category_recommend_1.getText().toString();
                String category_name_2 = category_recommend_2.getText().toString();

                //중분류
                if (category_name_2.equals("긴팔 티셔츠")) {
                    category_name_2 = "310010";
                } else if (category_name_2.equals("반팔 티셔츠")) {
                    category_name_2 = "310020";
                } else if (category_name_2.equals("맨투맨/후드티")) {
                    category_name_2 = "310030";
                } else if (category_name_2.equals("블라우스")) {
                    category_name_2 = "310040";
                } else if (category_name_2.equals("셔츠/남방")) {
                    category_name_2 = "310050";
                } else if (category_name_2.equals("니트/스웨터")) {
                    category_name_2 = "310060";
                } else if (category_name_2.equals("가디건")) {
                    category_name_2 = "310070";
                } else if (category_name_2.equals("조끼/베스트")) {
                    category_name_2 = "310080";
                } else if (category_name_2.equals("야상/점퍼/패딩")) {
                    category_name_2 = "310090";
                } else if (category_name_2.equals("자켓")) {
                    category_name_2 = "310100";
                } else if (category_name_2.equals("코트")) {
                    category_name_2 = "310110";
                } else if (category_name_2.equals("원피스")) {
                    category_name_2 = "310120";
                } else if (category_name_2.equals("스커트/치마")) {
                    category_name_2 = "310130";
                } else if (category_name_2.equals("청바지/스키니")) {
                    category_name_2 = "310140";
                } else if (category_name_2.equals("면/캐주얼바지")) {
                    category_name_2 = "310150";
                } else if (category_name_2.equals("반바지/핫팬츠")) {
                    category_name_2 = "310160";
                } else if (category_name_2.equals("레깅스")) {
                    category_name_2 = "310170";
                } else if (category_name_2.equals("비즈니스 정장")) {
                    category_name_2 = "310180";
                } else if (category_name_2.equals("트레이닝")) {
                    category_name_2 = "310190";
                } else if (category_name_2.equals("언더웨어/속옷")) {
                    category_name_2 = "310200";
                } else if (category_name_2.equals("빅사이즈")) {
                    category_name_2 = "310210";
                } else if (category_name_2.equals("테마/이벤트")) {
                    category_name_2 = "310220";
                }

                //대분류
                if (category_name_1.equals("여성의류")) {
                    category_name_1 = "310";
                } else if (category_name_1.equals("남성의류")) {
                    category_name_1 = "320";
                } else if (category_name_1.equals("패션잡화")) {
                    category_name_1 = "400";
                } else if (category_name_1.equals("뷰티/미용")) {
                    category_name_1 = "410";
                } else if (category_name_1.equals("유아동/출산")) {
                    category_name_1 = "500";
                } else if (category_name_1.equals("디지털/가전")) {
                    category_name_1 = "600";
                } else if (category_name_1.equals("스포츠/레저")) {
                    category_name_1 = "700";
                } else if (category_name_1.equals("차량/오토바이")) {
                    category_name_1 = "750";
                } else if (category_name_1.equals("생활/문구/식품")) {
                    category_name_1 = "800";
                } else if (category_name_1.equals("도서/티켓/애완")) {
                    category_name_1 = "900";
                } else if (category_name_1.equals("스타굿즈")) {
                    category_name_1 = "910";
                } else if (category_name_1.equals("기타")) {
                    category_name_1 = "999";
                }

                dbAdapter.open();
                dbAdapter.insertData(name, price, keyword1, keyword2, keyword3, description, category_name_1, category_name_2);
                dbAdapter.close();

                //println("intent 호출됨.");
                Intent intent = new Intent(UploadActivity.this, ItemDetailActivity.class);
                intent.putExtra("name", name);
                Toast.makeText(getApplicationContext(), "상품 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        //카테고리 버튼
        btnCategory = (Button) findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UploadActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        //홈 버튼
        btnSearchNew = (Button) findViewById(R.id.btnSearchNew);
        btnSearchNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UploadActivity.this, SearchAndNewActivity.class);
                startActivity(intent);
            }
        });

        //마이페이지 버튼
        btnMypage = (Button) findViewById(R.id.btnMypage);
        btnMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UploadActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }

    //서버
    public void sendRequest(){
        //String url = "https://www.google.co.kr";
        String s = edit_title.getText().toString()  + " " + edit_keyword1.getText().toString() + " " +
                edit_keyword2.getText().toString() + " " + edit_keyword3.getText().toString();
        //학교 서버
        String url = "http://203.252.195.201:3000/cate/" + s;
        //집 서버
        //String url = "http://112.152.20.211:1998/cate/" + s;
        //Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();

        //request로 서버에 보냄
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Listener<String>() {  //응답을 문자열로 받아서 여기다 넣어달란말임(응답을 성공적으로 받았을 떄 이메소드가 자동으로 호출됨
                    @Override
                    public void onResponse(String response) {
                        //response가 서버에서 받아온 것
                        //println("응답 => " + response);
                        try {
                            jsonParsing(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new ErrorListener(){ //에러발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 => "+ error.getMessage());
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        //println("요청 보냄!!");
    }

    public void checkboxClick1(View v){
        if(checkBox1.isChecked()){
            Toast.makeText(getApplicationContext(),"직거래 선택됨", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"직거래 선택 취소됨", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkboxClick2(View v){
        if(checkBox2.isChecked()){
            Toast.makeText(getApplicationContext(),"무료배송 선택됨", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"무료배송 선택 취소됨", Toast.LENGTH_SHORT).show();
        }
    }

    public void println(String data){
        category_recommend_1.setText(data);
    }

    //서버 json파일 파싱
    public void jsonParsing(String data) throws JSONException {
        String top1, top2;

        JSONArray jsonArray = new JSONArray(data);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        top1 = jsonObject.getString("Top1");
        top2 = jsonObject.getString("Top2");

        //Toast.makeText(getApplicationContext(),"Top1" + top1, Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),"Top2" + top2, Toast.LENGTH_SHORT).show();

        category_recommend_1.setText(top1);
        category_recommend_2.setText(top2);
        Toast.makeText(getApplicationContext(), "카테고리 검색 완료", Toast.LENGTH_SHORT).show();
    }
}
