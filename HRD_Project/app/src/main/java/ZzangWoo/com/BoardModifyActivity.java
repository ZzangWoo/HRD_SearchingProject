package ZzangWoo.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ZzangWoo.com.CustomTask.BoardModifyTask;
import ZzangWoo.com.CustomTask.ShowBoardTask;

public class BoardModifyActivity extends AppCompatActivity {

    String TAG = "BoardModifyActivity Log : ";

    EditText titleEditText, contentEditText;
    ImageView modifyButton, clearButton;

    String boardModifyResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_modify);

        setTitle("글수정");

        bindView();

        final String userID = getIntent().getStringExtra("userID");
        final String bbsTitle = getIntent().getStringExtra("bbsTitle");
        final String bbsContent = getIntent().getStringExtra("bbsContent");
        final String str_bbsID = getIntent().getStringExtra("bbsID");

        Log.i(TAG, "bbsID 값 : " + str_bbsID);

        titleEditText.setText(bbsTitle);
        contentEditText.setText(bbsContent);

        /************************* 수정버튼 클릭 *************************/
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modify_bbsTitle = titleEditText.getText().toString();
                String modify_bbsContent = contentEditText.getText().toString();

                if (modify_bbsTitle.equals("") || modify_bbsTitle == null) {
                    Toast.makeText(BoardModifyActivity.this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if (modify_bbsContent.equals("") || modify_bbsContent == null) {
                    Toast.makeText(BoardModifyActivity.this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    BoardModifyTask boardModifyTask = new BoardModifyTask();
                    try {
                        boardModifyResult = boardModifyTask.execute(str_bbsID, modify_bbsTitle, modify_bbsContent).get();

                        if (boardModifyResult.equals("1")) {
                            Toast.makeText(BoardModifyActivity.this, "수정 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), BoardMainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            Toast.makeText(BoardModifyActivity.this, "수정 실패", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*****************************************************************/

        /************************** 취소버튼 클릭 ************************/
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /******************************************************************/

    }

    private void bindView() {
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);

        modifyButton = (ImageView) findViewById(R.id.writeButton);
        clearButton = (ImageView) findViewById(R.id.clearButton);
    }
}
