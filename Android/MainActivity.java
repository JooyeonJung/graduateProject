package org.techtown.songthemarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            // 4초뒤에 다음화면(MainActivity)으로 넘어가기 Handler 사용
            Intent intent = new Intent(getApplicationContext(), SearchAndNewActivity.class);
            startActivity(intent); // 다음화면으로 넘어가기
            finish(); // Activity 화면 제거
        }
    };

    DBAdapter dbAdapter;

    final static String dbName = "bunjang.db";
    final static int dbVersion = 1;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB
        this.dbAdapter = new DBAdapter(this);

        //엑셀 파일에 있는 데이터 가져오기
        //copyExcelDataToDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 다시 화면에 들어어왔을 때 예약 걸어주기
        handler.postDelayed(r, 4000); // 4초 뒤에 Runnable 객체 수행
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 화면을 벗어나면, handler 에 예약해놓은 작업을 취소하자
        handler.removeCallbacks(r); // 예약 취소
    }

    public static void println(String data){
        Log.d(TAG, data);
    }

    public void copyExcelDataToDatabase() {
        println("copyExcelDataToDatabase() 호출됨.");

        Workbook workbook = null;
        Sheet sheet = null;

        try {
            InputStream is = getBaseContext().getResources().getAssets().open("notes.xls");
            workbook = Workbook.getWorkbook(is);

            if (workbook != null) {
                sheet = workbook.getSheet(0);

                if (sheet != null) {
                    int nMaxColumn = 8;
                    int nRowStartIndex = 1;
                    int nRowEndIndex = sheet.getColumn(nMaxColumn - 1).length - 1;
                    int nColumnStartIndex = 0;
                    int nColumnEndIndex = sheet.getRow(2).length - 1;

                    dbAdapter.open();
                    for (int nRow = nRowStartIndex; nRow <= nRowEndIndex; nRow++) {
                        String name = sheet.getCell(nColumnStartIndex, nRow).getContents();
                        String price = sheet.getCell(nColumnStartIndex + 1, nRow).getContents();
                        String keyword1 = sheet.getCell(nColumnStartIndex + 2, nRow).getContents();
                        String keyword2 = sheet.getCell(nColumnStartIndex + 3, nRow).getContents();
                        String keyword3 = sheet.getCell(nColumnStartIndex + 4, nRow).getContents();
                        String description = sheet.getCell(nColumnStartIndex + 5, nRow).getContents();
                        String category_name_1 = sheet.getCell(nColumnStartIndex + 6, nRow).getContents();
                        String category_name_2 = sheet.getCell(nColumnStartIndex + 7, nRow).getContents();

                        dbAdapter.insertData(name,price,keyword1,keyword2,keyword3,description,category_name_1,category_name_2);
                    }
                    dbAdapter.close();
                } else {
                    System.out.println("Sheet is null!!");
                }
            } else {
                System.out.println("WorkBook is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }
}
