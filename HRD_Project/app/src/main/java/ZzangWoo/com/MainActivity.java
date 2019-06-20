package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton, signupButton;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        int loginCheck = pref.getInt("loginCheck", 0);

        if (loginCheck != 0) {

            String userID = pref.getString("userID", null).replace(" ", "");

            if (userID.equals("root")) { // 관리자 계정 자동로그인
                Toast.makeText(this, "자동로그인 중", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginSuccessRootMain.class);
                startActivity(intent);
            } else { // 일반 계정 자동로그인
                Toast.makeText(this, "자동로그인 중", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginSuccessMain.class);
                startActivity(intent);
            }

        } else {

            loginButton = (Button) findViewById(R.id.loginButton);
            signupButton = (Button) findViewById(R.id.signupButton);

            /********************* 로그인 버튼 클릭 ************************/
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            /***************************************************************/
            /******************** 회원가입 버튼 클릭 ***********************/
            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                }
            });
            /***************************************************************/

        }



    }
}
