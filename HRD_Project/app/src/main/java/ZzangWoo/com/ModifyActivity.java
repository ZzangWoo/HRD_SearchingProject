package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ZzangWoo.com.CustomTask.CheckNameTask;
import ZzangWoo.com.CustomTask.UpdateTask;

public class ModifyActivity extends AppCompatActivity {

    TextView userIDTextView;

    EditText editTextPW, editTextName, editTextEmail;

    Button modifyFinishButton, checkNameButton;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String userID, userPW, userName, userEmail;
    String originUserName; // 이름체크할 때 쓴 이름 저장 ( 비교하려고 )
    String nameCheckedResult, updateResult;

    int nameChecked = 0; // 체크 안된상태는 0, 되면 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        /**** 회원 수정하고 나서 저장되어있는 sharedpreferences도 수정 *****/

        setTitle("회원 수정");

        bindView();

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        userID = pref.getString("userID", null);
        userName = pref.getString("userName", null);
        userEmail = pref.getString("userEmail", null);

        userIDTextView.setText(userID);
        userIDTextView.setTypeface(Typeface.DEFAULT_BOLD);

        editTextName.setText(userName);
        editTextEmail.setText(userEmail);

        /********************** 이름 체크 버튼 클릭 ***************************/
        checkNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = editTextName.getText().toString().replace(" ", "");

                if (userName.equals("") || userName == null) {
                    Toast.makeText(ModifyActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    CheckNameTask checkNameTask = new CheckNameTask();

                    try {
                        nameCheckedResult = checkNameTask.execute(userName).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (nameCheckedResult.equals("0")) {
                        Toast.makeText(ModifyActivity.this, "없는 이름입니다\n수정이 불가능합니다", Toast.LENGTH_SHORT).show();
                    } else if (nameCheckedResult.equals("1")) {
                        Toast.makeText(ModifyActivity.this, "존재하는 이름입니다\n수정이 가능합니다", Toast.LENGTH_SHORT).show();
                        nameChecked = 1;
                        originUserName = userName;
                    } else {
                        Toast.makeText(ModifyActivity.this, "데이터베이스 오류", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /**********************************************************************/

        /*********************** 수정 버튼 클릭 ****************************/
        modifyFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPW = editTextPW.getText().toString();
                userName = editTextName.getText().toString();
                userEmail = editTextEmail.getText().toString();

                if (userPW.equals("") || userPW == null) {
                    Toast.makeText(ModifyActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (userName.equals("") || userName == null) {
                    Toast.makeText(ModifyActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (userEmail.equals("") || userEmail == null) {
                    Toast.makeText(ModifyActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (nameChecked == 0) {
                    Toast.makeText(ModifyActivity.this, "이름 체크 버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
                } else if (!originUserName.equals(userName)) {
                    Toast.makeText(ModifyActivity.this, "체크한 이름이랑 현재 이름이랑 다르네요", Toast.LENGTH_SHORT).show();
                    nameChecked = 0;
                } else {

                    UpdateTask updateTask = new UpdateTask();
                    try {
                        updateResult = updateTask.execute(userID, userPW, userName, userEmail).get().replace(" ", "");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (updateResult.equals("1")) {
                        Toast.makeText(ModifyActivity.this, "회원수정 완성", Toast.LENGTH_SHORT).show();

                        editor = pref.edit();
                        editor.putString("userName", userName);
                        editor.putString("userEmail", userEmail);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), LoginSuccessMain.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ModifyActivity.this, "회원수정 실패", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        /*******************************************************************/

    }

    private void bindView() {
        userIDTextView = (TextView) findViewById(R.id.userIDTextView);
        editTextPW = (EditText) findViewById(R.id.editTextPW);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        checkNameButton = (Button) findViewById(R.id.checkNameButton);
        modifyFinishButton = (Button) findViewById(R.id.modifyFinishButton);
    }
}
