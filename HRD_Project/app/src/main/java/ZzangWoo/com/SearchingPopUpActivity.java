package ZzangWoo.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

public class SearchingPopUpActivity extends AppCompatActivity {

    String TAG = "SearchingPopUpActivity Log : ";

    AutoCompleteTextView studentNameEditText;

    ImageView searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_pop_up);

        setTitle("학생 이름 입력창");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.7), (int) (height*0.65));

        studentNameEditText = (AutoCompleteTextView) findViewById(R.id.studentNameEditText);
        searchButton = (ImageView) findViewById(R.id.searchButton);

        /******************************* 이름 자동완성 설정 ******************************/
        final String[] stuNameLists = {"고준혁","곽현석","권상욱","김범필","김태욱","복승현","성지훈","신수경",
                "우주희","유동희","윤종민","임은수","장진열","정주현","정진호","조수민","조창우","홍인기"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.array_adapter_lists, stuNameLists);
        studentNameEditText.setAdapter(adapter);
        /*********************************************************************************/

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = studentNameEditText.getText().toString().replace(" ", "");
                Log.i(TAG, "입력한 이름 : " + userName);
                /*************** 입력한 이름이 유효한 이름인지 검사 *****************/
                // 이름이 있어서 팝업창이 닫혔는데도 자꾸 이름이 일치하는게 없다고 Toast 메세지가 떠서
                // nameCheck 변수 선언해서 코드 짬
                int nameCheck = 0; // 이름 일치하는거 있으면 0 없으면 1
                if (userName.equals("") || userName == null) {
                    Toast.makeText(SearchingPopUpActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    int i = 0;
                    while (i < stuNameLists.length) {
                        if (userName.equals(stuNameLists[i])) {
                            nameCheck = 0;
                            break;
                        } else {
                            nameCheck = 1;
                        }
                        i++;
                    }
                    if (nameCheck != 0) {
                        Toast.makeText(SearchingPopUpActivity.this, "이름이 일치하는 학생이 없습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("intUserName", i+1+"");
                        intent.putExtra("strUserName", userName);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });

    }
}
