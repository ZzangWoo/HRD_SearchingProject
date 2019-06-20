package ZzangWoo.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import ZzangWoo.com.CustomTask.FindAllTask;

public class FindAllActivity extends AppCompatActivity {

    String TAG = "FindAllActivity.java 로그 : ";
    String findResults;

    TextView userIDText, userPWText, userNameText;
    ImageView homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_all);

        bindView();

        /******************* 회원정보 조회부분 ****************/
        FindAllTask findAllTask = new FindAllTask();
        try {
            findResults = findAllTask.execute().get();
            Log.i(TAG, "받아온 값 : " + findResults);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // String으로 들어온 값을 JSONObject로 1차 파싱
            JSONObject wrapObject = new JSONObject(findResults);

            // JSONObject의 키 "list"의 값들을 JSONArray 형태로 변환
            JSONArray jsonArray = new JSONArray(wrapObject.getString("list"));
            Log.i(TAG, "배열 크기 : " + jsonArray.length());

            JSONObject dataJsonObject = jsonArray.getJSONObject(0);

            userIDText.setText(dataJsonObject.getString("userID"));
            userPWText.setText(dataJsonObject.getString("userPW"));
            userNameText.setText(dataJsonObject.getString("userName"));

            for (int i = 1; i < jsonArray.length(); i++) {
                // Array에서 하나의 JSONObject를 추출
                JSONObject dataJsonObject2 = jsonArray.getJSONObject(i);

                userIDText.setText(userIDText.getText().toString()  + "\n" + dataJsonObject2.getString("userID"));
                userPWText.setText(userPWText.getText().toString() + "\n" + dataJsonObject2.getString("userPW") );
                userNameText.setText(userNameText.getText().toString() + "\n" + dataJsonObject2.getString("userName"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /******************************************************/

        /********************* 홈으로 버튼 클릭 ********************/
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginSuccessRootMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        /************************************************************/

    }

    private void bindView() {

        userIDText = (TextView) findViewById(R.id.userIDText);
        userPWText = (TextView) findViewById(R.id.userPWText);
        userNameText = (TextView) findViewById(R.id.userNameText);

        homeButton = (ImageView) findViewById(R.id.homeButton);

    }
}
