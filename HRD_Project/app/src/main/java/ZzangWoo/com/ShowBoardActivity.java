package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import ZzangWoo.com.CustomTask.BoardDeleteTask;
import ZzangWoo.com.CustomTask.ShowBoardTask;

public class ShowBoardActivity extends AppCompatActivity {

    TextView bbsIDText, bbsTitleText, userIDText, bbsDateText, bbsContentText;

    ImageView rewriteButton, deleteButton, listButton;

    LinearLayout rewriteLayout1, rewriteLayout2, deleteLayout1, deleteLayout2;

    String showBoardResults;
    String bbsTitle, userID, bbsDate, bbsContent;
    String str_bbsID;
    int bbsID;
    String realUserID; // 로그인한 계정 (userID는 글쓴이 계정)

    String boardDeleteResult;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_board);

        setTitle("글 확인");

        bindView();

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        realUserID = pref.getString("userID", null); // 로그인 한 아이디 받아오기

        str_bbsID = getIntent().getStringExtra("bbsID");
        bbsID = Integer.parseInt(str_bbsID);

        ShowBoardTask showBoardTask = new ShowBoardTask();
        try {
            showBoardResults = showBoardTask.execute(str_bbsID).get();

            JSONObject wrapObject = new JSONObject(showBoardResults);

            bbsTitle = wrapObject.getString("bbsTitle");
            userID = wrapObject.getString("userID"); // 글 쓴 아이디
            bbsDate = wrapObject.getString("bbsDate");
            bbsContent = wrapObject.getString("bbsContent");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        bbsTitleText.setText(bbsTitle);
        userIDText.setText(userID);
        bbsDateText.setText(bbsDate);
        bbsContentText.setText(bbsContent);

        /***** 계정의 종류에 따라 글수정, 글삭제 버튼이 보이고 안보이게 설정 ******/
        if (userID.equals(realUserID) || realUserID.equals("root")) {
            rewriteLayout1.setVisibility(View.VISIBLE);
            rewriteLayout2.setVisibility(View.VISIBLE);
            deleteLayout1.setVisibility(View.VISIBLE);
            deleteLayout2.setVisibility(View.VISIBLE);
        } else {
            rewriteLayout1.setVisibility(View.GONE);
            rewriteLayout2.setVisibility(View.GONE);
            deleteLayout1.setVisibility(View.GONE);
            deleteLayout2.setVisibility(View.GONE);
        }
        /***************************************************************************/

        /********************** 글 수정 버튼 클릭 *********************/
        rewriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardModifyActivity.class);
                intent.putExtra("bbsTitle", bbsTitle);
                intent.putExtra("userID", userID);
                intent.putExtra("bbsContent", bbsContent);
                intent.putExtra("bbsID", str_bbsID);
                startActivity(intent);
            }
        });
        /**************************************************************/

        /********************* 글 삭제 버튼 클릭 ***********************/
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardDeleteTask boardDeleteTask = new BoardDeleteTask();
                try {
                    boardDeleteResult = boardDeleteTask.execute(bbsID + "").get();

                    if (boardDeleteResult.equals("1")) {
                        Toast.makeText(ShowBoardActivity.this, "글삭제 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), BoardMainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ShowBoardActivity.this, "글삭제 실패", Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /***************************************************************/

        /********************** 글 목록 버튼 클릭 *********************/
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**************************************************************/

    }

    private void bindView() {
        bbsIDText = (TextView) findViewById(R.id.bbsIDText);
        bbsTitleText = (TextView) findViewById(R.id.bbsTitleText);
        userIDText = (TextView) findViewById(R.id.userIDText);
        bbsDateText = (TextView) findViewById(R.id.bbsDateText);
        bbsContentText = (TextView) findViewById(R.id.bbsContentText);

        rewriteButton = (ImageView) findViewById(R.id.rewriteButton);
        deleteButton = (ImageView) findViewById(R.id.deleteButton);
        listButton = (ImageView) findViewById(R.id.listButton);

        rewriteLayout1 = (LinearLayout) findViewById(R.id.rewriteLayout1);
        rewriteLayout2 = (LinearLayout) findViewById(R.id.rewriteLayout2);
        deleteLayout1 = (LinearLayout) findViewById(R.id.deleteLayout1);
        deleteLayout2 = (LinearLayout) findViewById(R.id.deleteLayout2);
    }
}
