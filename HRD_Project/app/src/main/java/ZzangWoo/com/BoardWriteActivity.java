package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ZzangWoo.com.CustomTask.BoardWriteTask;

public class BoardWriteActivity extends AppCompatActivity {

    EditText titleEditText, contentEditText;
    ImageView writeButton, clearButton;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;

    String boardWriteResult;

    String TAG = "BoardWriteActivity Log : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        setTitle("글쓰기");

        bindView();

        /************************ 글쓰기 버튼 클릭 *************************/
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);

                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                String userID = pref.getString("userID", null);
                
                if (title.equals("") || title == null) {
                    Toast.makeText(BoardWriteActivity.this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (content.equals("") || content == null) {
                    Toast.makeText(BoardWriteActivity.this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {

                    BoardWriteTask boardWriteTask = new BoardWriteTask();
                    try {
                        boardWriteResult = boardWriteTask.execute(title, content, userID).get();
                        Log.i(TAG, "글쓰기 결과 : " + boardWriteResult);
                        if (boardWriteResult.equals("1")) {
                            Toast.makeText(BoardWriteActivity.this, "글쓰기 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), BoardMainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            Toast.makeText(BoardWriteActivity.this, "글쓰기 오류", Toast.LENGTH_SHORT).show();
                        }

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*******************************************************************/

        /************************ X 버튼 클릭 **************************/
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /***************************************************************/

    }

    private void bindView() {
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);

        writeButton = (ImageView) findViewById(R.id.writeButton);
        clearButton = (ImageView) findViewById(R.id.clearButton);
    }
}
