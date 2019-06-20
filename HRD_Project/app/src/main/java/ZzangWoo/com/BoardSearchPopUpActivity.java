package ZzangWoo.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class BoardSearchPopUpActivity extends AppCompatActivity {

    EditText contentTitleEditText;
    ImageView searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_search_pop_up);

        setTitle("글 검색");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8), (int) (height*0.35));

        contentTitleEditText = (EditText) findViewById(R.id.contentTitleEditText);
        searchButton = (ImageView) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = contentTitleEditText.getText().toString();

                if (content.equals("") || content == null) {
                    Toast.makeText(BoardSearchPopUpActivity.this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("content", content);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

}
