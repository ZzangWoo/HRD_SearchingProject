package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ZzangWoo.com.CustomTask.LoginTask;
import ZzangWoo.com.CustomTask.SearchingNameTask;

public class LoginActivity extends AppCompatActivity {

    String TAG = "LoginActivity.java 로그 : ";
    String loginResults, userName, userEmail;

    String results; // 이름, 이메일 json 받아올 string

    EditText editTextID, editTextPW;
    Button loginButton, signupButton;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextPW = (EditText) findViewById(R.id.editTextPW);

        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signupButton);

        /********************** 로그인 버튼 클릭 ***********************/
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = editTextID.getText().toString().replace(" ", "");
                String userPW = editTextPW.getText().toString().replace(" ", "");

                if (userID.equals("") || userID == null) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (userPW.equals("") || userPW == null) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        LoginTask loginTask = new LoginTask();
                        Log.i(TAG, "아이디 : " + userID + " | 비번 : " + userPW);
                        loginResults = loginTask.execute(userID, userPW).get();
                        Log.i(TAG, "받아온 값 : " + loginResults);
                    } catch (Exception e) {
                        Log.i(TAG, "값 받아오기 실패");
                    }
                    
                    if (loginResults.equals("1")) {
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "로그인 성공 들어옴");
                        try {
                            SearchingNameTask searchingNameTask = new SearchingNameTask();
                            Log.i(TAG, "userID 값 : " + userID);
                            results = searchingNameTask.execute(userID).get();
                            Log.i(TAG, "받아온 json 값 : " + results);
                        } catch (Exception e) {
                            Log.i(TAG, "값 받아오기 실패");
                        }

                        try {
                            JSONObject wrapObject = new JSONObject(results);

                            userName = wrapObject.getString("userName");
                            userEmail = wrapObject.getString("userEmail");

                            Log.i(TAG, "이름 : " + userName + " | 이메일 : " + userEmail);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
                        editor = pref.edit();

                        editor.putString("userID", userID);
                        editor.putString("userPW", userPW);
                        editor.putString("userName", userName);
                        editor.putString("userEmail", userEmail);
                        editor.putInt("loginCheck", 1);
                        editor.commit();

                        if (userID.equals("root")) {
                            Intent intent = new Intent(getApplicationContext(), LoginSuccessRootMain.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), LoginSuccessMain.class);
                            startActivity(intent);
                            Log.i(TAG, "LoginSuccessMain 액티비티로 ㄱㄱ");
                        }

                    } else if (loginResults.equals("2")) {
                        Toast.makeText(LoginActivity.this, "비밀번호 다름", Toast.LENGTH_SHORT).show();
                    } else if (loginResults.equals("3")) {
                        Toast.makeText(LoginActivity.this, "아이디 없음", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "데이터베이스 오류", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        /***************************************************************/
        /********************* 회원가입 버튼 클릭 **********************/
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
