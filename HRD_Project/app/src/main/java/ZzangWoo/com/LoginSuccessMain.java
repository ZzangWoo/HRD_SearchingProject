package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ZzangWoo.com.OtherFunction.BackPressCloseHandler;

public class LoginSuccessMain extends AppCompatActivity {

    String TAG = "LoginSuccessActivity.java 로그 : ";

    TextView userNameTextView;

    Button hrdButton, boardButton, logoutButton, modifyButton;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success_main);

        userNameTextView = (TextView) findViewById(R.id.userNameTextView);

        hrdButton = (Button) findViewById(R.id.hrdButton);
        boardButton = (Button) findViewById(R.id.boardButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        modifyButton = (Button) findViewById(R.id.modifyButton);

        backPressCloseHandler = new BackPressCloseHandler(this);

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        editor = pref.edit();
        String userName = pref.getString("userName", null);

        userNameTextView.setText(userName + "님\n로그인하셨습니다");

        /****************************** 게시판 버튼 ********************************/
        boardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardMainActivity.class);
                startActivity(intent);
            }
        });
        /***************************************************************************/

        /****************************** 출석부 조회 버튼 ********************************/
        hrdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentAttendanceActivity.class);
                startActivity(intent);
            }
        });
        /********************************************************************************/

        /******************************* 회원수정 버튼 ***********************************/
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ModifyPopUpActivity.class);
                startActivity(intent);
            }
        });
        /*********************************************************************************/

        /************************************** 로그아웃 버튼 **************************************/
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginSuccessMain.this, "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        /*******************************************************************************************/

    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
