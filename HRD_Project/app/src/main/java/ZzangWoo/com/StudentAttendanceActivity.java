package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ZzangWoo.com.SearchingFragment.Search2to3Fragment;
import ZzangWoo.com.SearchingFragment.Search3to4Fragment;
import ZzangWoo.com.SearchingFragment.Search4to5Fragment;
import ZzangWoo.com.SearchingFragment.SearchWholeFragment;

public class StudentAttendanceActivity extends AppCompatActivity {

    String TAG = "StudentAttendanceActivity Log : ";

    public final String PREFERENCE = "pref";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Button wholeSearchButton, search2to3Button, search3to4Button, search4to5Button;

    TextView studentInformation;

    ImageView searchButton, homeButton, refreshButton;

    SearchWholeFragment searchWholeFragment;
    Search2to3Fragment search2to3Fragment;
    Search3to4Fragment search3to4Fragment;
    Search4to5Fragment search4to5Fragment;

    String strStudentNum = "0";
    int studentNum = 0;
    String userName;

    int selectFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        final String userID = pref.getString("userID", null);
        final String userName = pref.getString("userName", null);

        bindView();

        /*********** 관리자 계정인지 일반 계정인지에 따라서 제목다름 ***********/
        if (userID.equals("root")) {
            setTitle("관리자 출석부 조회");
            searchButton.setVisibility(View.VISIBLE);
            refreshButton.setVisibility(View.VISIBLE);
            studentInformation.setText("학생 이름을 입력한 후에\n출석부 조회가 가능합니다");
        } else {
            setTitle(userName + "님 출석부 조회");
        }
        /***********************************************************************/

        /******************************* 홈 버튼 누를 때 *********************************/
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userID.equals("root")) {
                    editor = pref.edit();
                    editor.putString("strUserName", null);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), LoginSuccessRootMain.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginSuccessMain.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
            }
        });
        /*********************************************************************************/

        /******************************** 검색버튼 누를 때 ********************************/
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchingPopUpActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        /**********************************************************************************/

        /******************************** 새로고침 버튼 누를 때 *********************************/
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectFragment == 0) {
                    Toast.makeText(StudentAttendanceActivity.this, "조회부터 하셔야 새로고침이 작동합니다", Toast.LENGTH_SHORT).show();
                } else {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer); // 현재 켜져있는 fragment id 가져오기
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); // fragment 화면 바꿔줄 준비
                    transaction.detach(fragment)
                            .attach(fragment)
                            .commit();
                }
            }
        });
        /****************************************************************************************/

        /******************************* Fragment 움직이게 하는 버튼 설정 **********************************/
        wholeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFragment++;
                Log.i(TAG, "전체조회버튼");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, searchWholeFragment).commit();
                /********* 버튼 눌릴때 눌린거 표시 ************/
                wholeSearchButton.setTextColor(Color.parseColor("#2b90d9"));
                wholeSearchButton.setTypeface(null, Typeface.BOLD);
                search2to3Button.setTextColor(Color.parseColor("#274c5e"));
                search2to3Button.setTypeface(null, Typeface.NORMAL);
                search3to4Button.setTextColor(Color.parseColor("#274c5e"));
                search3to4Button.setTypeface(null, Typeface.NORMAL);
                search4to5Button.setTextColor(Color.parseColor("#274c5e"));
                search4to5Button.setTypeface(null, Typeface.NORMAL);
                /*********************************************/
            }
        });

        search2to3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFragment++;
                Log.i(TAG, "2월~3월버튼");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, search2to3Fragment).commit();

                wholeSearchButton.setTextColor(Color.parseColor("#274c5e"));
                wholeSearchButton.setTypeface(null, Typeface.NORMAL);
                search2to3Button.setTextColor(Color.parseColor("#2b90d9"));
                search2to3Button.setTypeface(null, Typeface.BOLD);
                search3to4Button.setTextColor(Color.parseColor("#274c5e"));
                search3to4Button.setTypeface(null, Typeface.NORMAL);
                search4to5Button.setTextColor(Color.parseColor("#274c5e"));
                search4to5Button.setTypeface(null, Typeface.NORMAL);
            }
        });

        search3to4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFragment++;
                Log.i(TAG, "3월~4월버튼");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, search3to4Fragment).commit();

                wholeSearchButton.setTextColor(Color.parseColor("#274c5e"));
                wholeSearchButton.setTypeface(null, Typeface.NORMAL);
                search2to3Button.setTextColor(Color.parseColor("#274c5e"));
                search2to3Button.setTypeface(null, Typeface.NORMAL);
                search3to4Button.setTextColor(Color.parseColor("#2b90d9"));
                search3to4Button.setTypeface(null, Typeface.BOLD);
                search4to5Button.setTextColor(Color.parseColor("#274c5e"));
                search4to5Button.setTypeface(null, Typeface.NORMAL);
            }
        });

        search4to5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFragment++;
                Log.i(TAG, "4월~5월버튼");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, search4to5Fragment).commit();

                wholeSearchButton.setTextColor(Color.parseColor("#274c5e"));
                wholeSearchButton.setTypeface(null, Typeface.NORMAL);
                search2to3Button.setTextColor(Color.parseColor("#274c5e"));
                search2to3Button.setTypeface(null, Typeface.NORMAL);
                search3to4Button.setTextColor(Color.parseColor("#274c5e"));
                search3to4Button.setTypeface(null, Typeface.NORMAL);
                search4to5Button.setTextColor(Color.parseColor("#2b90d9"));
                search4to5Button.setTypeface(null, Typeface.BOLD);
            }
        });
        /*****************************************************************************************/


    }

    private void bindView() {

        wholeSearchButton = (Button) findViewById(R.id.wholeSearchButton);
        search2to3Button = (Button) findViewById(R.id.search2to3Button);
        search3to4Button = (Button) findViewById(R.id.search3to4Button);
        search4to5Button = (Button) findViewById(R.id.search4to5Button);

        searchButton = (ImageView) findViewById(R.id.searchButton);
        homeButton = (ImageView) findViewById(R.id.homeButton);
        refreshButton = (ImageView) findViewById(R.id.refreshButton);

        studentInformation = (TextView) findViewById(R.id.studentInformation);

        searchWholeFragment = new SearchWholeFragment();
        search2to3Fragment = new Search2to3Fragment();
        search3to4Fragment = new Search3to4Fragment();
        search4to5Fragment = new Search4to5Fragment();

    }

    /************************************ 팝업창에서 가져온 값 판별 *********************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                strStudentNum = data.getStringExtra("intUserName");
                userName = data.getStringExtra("strUserName");

                studentNum = Integer.parseInt(strStudentNum);

                studentInformation.setText(userName + " 학생 출석부 조회");

                editor = pref.edit();
                //editor.putString("userName", userName);
                editor.putString("strUserName", userName);
                editor.commit();
            }
        }
    }
    /***********************************************************************************************/
}
