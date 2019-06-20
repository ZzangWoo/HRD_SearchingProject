package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ZzangWoo.com.OtherFunction.BackPressCloseHandler;

public class LoginSuccessRootMain extends AppCompatActivity {

    Button hrdButton, boardButton, findAllButton, logoutButton;

    BackPressCloseHandler backPressCloseHandler;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success_root_main);

        bindView(); // 깔끔해 보이길래 위젯 설정 메소드 만들어봄

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        editor = pref.edit();

        /******************************** 출석부 조회 버튼 클릭 ********************************/
        hrdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentAttendanceActivity.class);
                startActivity(intent);
            }
        });
        /***************************************************************************************/

        /******************************* 게시판 버튼 클릭 ***********************************/
        boardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardMainActivity.class);
                startActivity(intent);
            }
        });
        /************************************************************************************/

        /******************************* 회원조회 버튼 클릭 ***********************************/
        findAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindAllActivity.class);
                startActivity(intent);
            }
        });
        /**************************************************************************************/

        /******************************** 로그아웃 버튼 클릭 ******************************/
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginSuccessRootMain.this, "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        /*********************************************************************************/

    }

    /********************* 위젯 연결 메소드 *****************************/
    private void bindView() {

        hrdButton = (Button) findViewById(R.id.hrdButton);
        boardButton = (Button) findViewById(R.id.boardButton);
        findAllButton = (Button) findViewById(R.id.findAllButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        backPressCloseHandler = new BackPressCloseHandler(this);

    }
    /********************************************************************/

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
