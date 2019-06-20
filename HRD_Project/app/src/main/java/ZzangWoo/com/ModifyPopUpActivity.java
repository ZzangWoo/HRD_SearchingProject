package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ZzangWoo.com.CustomTask.LoginTask;

public class ModifyPopUpActivity extends AppCompatActivity {

    EditText passwordEditText;
    Button modifyButton;
    TextView userIDTextView;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;

    String userID, userPW;

    String modifyCheckResults; // 회원수정 전에 비밀번호 입력 잘 됐나

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pop_up);

        setTitle("회원 수정 확인");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8), (int) (height*0.35));

        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        modifyButton = (Button) findViewById(R.id.modifyButton);
        userIDTextView = (TextView) findViewById(R.id.userIDTextView);

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        userID = pref.getString("userID", null);

        userIDTextView.setText(userID);

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userPW = passwordEditText.getText().toString();

                if (userPW.equals("") || userPW == null) {
                    Toast.makeText(ModifyPopUpActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    // 회원 수정할 때 본인인지 다시 검사해야 하는데
                    // 로그인 과정이랑 똑같아서 LoginTask 우려먹기!!
                    LoginTask loginTask = new LoginTask();
                    try {
                        modifyCheckResults = loginTask.execute(userID, userPW).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (modifyCheckResults.equals("1")) {
                        Toast.makeText(ModifyPopUpActivity.this, "본인 인증 확인", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ModifyActivity.class);
                        startActivity(intent);
                    } else if (modifyCheckResults.equals("2")) {
                        Toast.makeText(ModifyPopUpActivity.this, "비밀번호 다름", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}
