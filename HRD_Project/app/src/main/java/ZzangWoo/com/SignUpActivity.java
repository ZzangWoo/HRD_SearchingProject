package ZzangWoo.com;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import ZzangWoo.com.CustomTask.CheckIDTask;
import ZzangWoo.com.CustomTask.CheckNameTask;
import ZzangWoo.com.CustomTask.SignUpFinishTask;

public class SignUpActivity extends AppCompatActivity {

    String TAG = "SignUpActivity.java 로그 : ";

    EditText editTextID, editTextPW, editTextPW2, editTextName, editTextEmail;
    Button checkIDButton, checkNameButton, signupFinishButton;
    TextView checkPWTextView;

    int idChecked = 0, nameChecked = 0, specialID = 0;
    String str_idChecked, str_nameChecked;
    String userID, userPW, userPW2, userName, userEmail;
    String originUserID; // 아이디 체크하고 아이디를 다른걸로 바꿀수 있으니까
    String originUserName; // 이하동문

    String signUpResults; // 회원가입 성공? 실패?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextPW = (EditText) findViewById(R.id.editTextPW);
        editTextPW2 = (EditText) findViewById(R.id.editTextPW2);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        checkIDButton = (Button) findViewById(R.id.checkIDButton);
        checkNameButton = (Button) findViewById(R.id.checkNameButton);
        signupFinishButton = (Button) findViewById(R.id.signupFinishButton);

        checkPWTextView = (TextView) findViewById(R.id.checkPWTextView);

        /************************************** 아이디에 특수문자, 한글 입력 못하게 해줌 ***********************************/
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                Pattern ps = Pattern.compile("^[a-zA-Z0-9]*$");
                if (!ps.matcher(charSequence).matches()) {
                    Toast.makeText(SignUpActivity.this, "특수문자, 한글은 입력할 수 없어요", Toast.LENGTH_SHORT).show();
                    return "";
                }
                return null;
            }
        };
        editTextID.setFilters(new InputFilter[]{filter});
        /********************************************************************************************************************/

        /********************************** 비밀번호 체크부분 *********************************/
        /********************* 첫 번째 비밀번호에 대한 Event *****************************/
        editTextPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userPW = charSequence.toString().replace(" ", "");
                userPW2 = editTextPW2.getText().toString().replace(" ", "");

                if (!userPW.equals(userPW2)) {
                    checkPWTextView.setText("비밀번호\n불일치");
                    checkPWTextView.setTextColor(Color.RED);
                } else {
                    checkPWTextView.setText("비밀번호\n일치");
                    checkPWTextView.setTextColor(Color.BLUE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /********************************************************************************/

        /********************* 두 번째 비밀번호에 대한 Event *****************************/
        editTextPW2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                userPW = editTextPW.getText().toString().replace(" ", "");
                userPW2 = charSequence.toString().replace(" ", "");

                if (!userPW2.equals(userPW)) { // 패스워드가 일치하지 않으면
                    checkPWTextView.setText("비밀번호\n불일치");
                    checkPWTextView.setTextColor(Color.RED);
                } else {
                    checkPWTextView.setText("비밀번호\n일치");
                    checkPWTextView.setTextColor(Color.BLUE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /*******************************************************************************/
        /*************************************************************************************/

        /****************************** 아이디 체크 버튼 ************************************/
        checkIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userID = editTextID.getText().toString().replace(" ", "");
                Log.i(TAG, "입력한 아이디는 " + userID);

                if (userID.equals("") || userID == null) {
                    Toast.makeText(SignUpActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (userID.length() < 4 || userID.length() > 12) { // 아이디 글자 수 검사
                    Toast.makeText(SignUpActivity.this, "아이디를 4~12자까지 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (specialID == 1) { // 한글, 특수문자 들어가있는 상태
                    Toast.makeText(SignUpActivity.this, "특수문자, 한글은 사용할 수 없어요", Toast.LENGTH_SHORT).show();
                } else {

                    CheckIDTask checkIDTask = new CheckIDTask();
                    try {
                        str_idChecked = checkIDTask.execute(userID).get();
                        Log.i(TAG, "아이디 값 : " + str_idChecked);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    /**************** 아이디 존재여부 판별 *******************/
                    if (str_idChecked.equals("0")) { // 아이디체크 성공
                        Toast.makeText(SignUpActivity.this, "사용할 수 있는 아이디입니다", Toast.LENGTH_SHORT).show();
                        idChecked = 1;
                        originUserID = userID; // 아이디 체크 한 아이디 저장!! 빼박이다
                    } else if (str_idChecked.equals("1")) { // 아이디체크 실패 (중복아이디)
                        Toast.makeText(SignUpActivity.this, "이미 존재하는 아이디입니다", Toast.LENGTH_SHORT).show();
                    } else { // 오류
                        Toast.makeText(SignUpActivity.this, "데이터베이스 오류", Toast.LENGTH_SHORT).show();
                    }
                    /*********************************************************/
                }
            }
        });
        /**********************************************************************************/

        /********************************** 이름 체크 버튼 *************************************/
        checkNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = editTextName.getText().toString().replace(" ", "");

                if (userName.equals("") || userName == null) {
                    Toast.makeText(SignUpActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {

                    CheckNameTask checkNameTask = new CheckNameTask();
                    Log.i(TAG, "입력된 이름 : " + userName);
                    try {
                        str_nameChecked = checkNameTask.execute(userName).get();
                        Log.i(TAG, "이름 값 : " + str_nameChecked);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    /*************** 이름 존재 판별 *****************/
                    if (str_nameChecked.equals("0")) { // 없는 이름 ( 이름이 있어야 가입가능 )
                        Toast.makeText(SignUpActivity.this, "없는 이름입니다\n가입이 불가능합니다", Toast.LENGTH_SHORT).show();
                    } else if (str_nameChecked.equals("1")) { // 있는 이름
                        Toast.makeText(SignUpActivity.this, "존재하는 이름입니다\n가입이 가능합니다", Toast.LENGTH_SHORT).show();
                        nameChecked = 1;
                        originUserName = userName;
                    } else {
                        Toast.makeText(SignUpActivity.this, "데이터베이스 오류", Toast.LENGTH_SHORT).show();
                    }
                    /************************************************/

                }

            }
        });
        /****************************************************************************************/

        /********************************* 회원가입 완료 버튼 ***********************************/
        signupFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userID = editTextID.getText().toString().replace(" ", "");
                userPW = editTextPW.getText().toString().replace(" ", "");
                userPW2 = editTextPW2.getText().toString().replace(" ", "");
                userName = editTextName.getText().toString().replace(" ", "");
                userEmail = editTextEmail.getText().toString().replace(" ", "");

                Log.i(TAG, "비밀번호1 : " + userPW + " | 비밀번호2 : " + userPW2);
                Log.i(TAG, "아이디 : " + userID + " | 이름 : " + userName);

                if (userID.equals("") || userID == null) {
                    Toast.makeText(SignUpActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (userPW.equals("") || userPW == null) {
                    Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (userPW2.equals("") || userPW2 == null) {
                    Toast.makeText(SignUpActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (!userPW.equals(userPW2)) {
                    Toast.makeText(SignUpActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                } else if (userName.equals("") || userName == null) {
                    Toast.makeText(SignUpActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (userEmail.equals("") || userEmail == null) {
                    Toast.makeText(SignUpActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (idChecked == 0) {
                    Toast.makeText(SignUpActivity.this, "아이디 체크 버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
                } else if (nameChecked == 0) {
                    Toast.makeText(SignUpActivity.this, "이름 체크 버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
                } else if (!originUserID.equals(userID)) {
                    Toast.makeText(SignUpActivity.this, "체크한 아이디랑 지금 아이디랑 다르시네용", Toast.LENGTH_SHORT).show();
                    idChecked = 0;
                } else if (!originUserName.equals(userName)) {
                    Toast.makeText(SignUpActivity.this, "체크한 이름이랑 지금 이름이랑 다르시네용", Toast.LENGTH_SHORT).show();
                    nameChecked = 0;
                }
                else { // 회원가입 완료

                    SignUpFinishTask signUpFinishTask = new SignUpFinishTask();
                    try {
                        signUpResults = signUpFinishTask.execute(userID, userPW, userName, userEmail).get();
                        Log.i(TAG, "회원가입 받아온 값 : " + signUpResults);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (signUpResults.equals("1")) {
                        Toast.makeText(SignUpActivity.this, "회원가입완료", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUpActivity.this, "회원가입실패", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        /***************************************************************************************/
    }

}
