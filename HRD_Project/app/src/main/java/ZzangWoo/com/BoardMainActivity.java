package ZzangWoo.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import ZzangWoo.com.Adapter.BoardRecyclerAdapter;
import ZzangWoo.com.CustomTask.BoardFirstTask;
import ZzangWoo.com.CustomTask.SearchingBoardTask;

public class BoardMainActivity extends AppCompatActivity {

    String TAG = "BoardMainActivity Log : ";

    ImageView searchButton, writeButton, homeButton, listButton;

    TextView homeText, listText;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    String boardResults, searchingBoardResults;
    ArrayList<HashMap<String, String>> boardListsResult;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String realUserID; // userID가 겹쳐서 real 붙임 ( 관리자계정으로 들어온건지 일반계정으로 들어온건지 판별 )

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_main);

        setTitle("게시판");

        bindView();

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        /*********** 홈 버튼 보이고 목록 버튼 안보이게 ************/
        listButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.VISIBLE);
        listText.setVisibility(View.GONE);
        homeText.setVisibility(View.VISIBLE);
        /**********************************************************/

        /************** SharedPreferences 설정 ****************/
        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        editor = pref.edit();
        /******************************************************/

        BoardFirstTask boardFirstTask = new BoardFirstTask();
        try {

            boardResults = boardFirstTask.execute().get();
            Log.i(TAG, "받아온 json : " + boardResults);
            JSONObject jsonObject = new JSONObject(boardResults);
            Log.i(TAG, "받아온 json 압축해제 : " + jsonObject);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            Log.i(TAG, "받아온 json 압축해제 : " + jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dataObject = jsonArray.getJSONObject(i);

                String bbsID = dataObject.getString("bbsID");
                String bbsTitle = dataObject.getString("bbsTitle");
                String userID = dataObject.getString("userID");
                String bbsDate = dataObject.getString("bbsDate");

                HashMap<String, String> boardLists = new HashMap<String, String>();
                boardLists.put("bbsID", bbsID);
                boardLists.put("bbsTitle", bbsTitle);
                boardLists.put("userID", userID);
                boardLists.put("bbsDate", bbsDate);
                Log.i(TAG, "hashmap에 들어간값은 : " + boardLists);

                boardListsResult.add(boardLists);
            }

            BoardRecyclerAdapter boardRecyclerAdapter = new BoardRecyclerAdapter(boardListsResult);
            recyclerView.setAdapter(boardRecyclerAdapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /********************************* 검색 버튼 클릭 **************************************/
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardSearchPopUpActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        /***************************************************************************************/

        /********************************** 글쓰기 버튼 클릭 **************************************/
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardWriteActivity.class);
                startActivity(intent);
            }
        });
        /******************************************************************************************/

        /********************************** 홈 버튼 클릭 ***************************************/
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realUserID = pref.getString("userID", null);
                if (realUserID.equals("root")) {
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
        /***************************************************************************************/

        /******************************** 글 목록 버튼 클릭 ************************************/
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate(); // 다시 onCreate 실행하는 코드
            }
        });
        /***************************************************************************************/

    }

    private void bindView() {

        searchButton = (ImageView) findViewById(R.id.searchButton);
        writeButton = (ImageView) findViewById(R.id.writeButton);
        homeButton = (ImageView) findViewById(R.id.homeButton);
        listButton = (ImageView) findViewById(R.id.listButton);

        homeText = (TextView) findViewById(R.id.homeText);
        listText = (TextView) findViewById(R.id.listText);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        boardListsResult = new ArrayList<HashMap<String, String>>();

    }
    /************************************** 검색창에서 검색한 이후 ************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                /************ 리스트 버튼 보이고 홈 버튼 안보이게 ******************/
                homeButton.setVisibility(View.GONE);
                listButton.setVisibility(View.VISIBLE);
                homeText.setVisibility(View.GONE);
                listText.setVisibility(View.VISIBLE);
                /*******************************************************************/

                String content = data.getStringExtra("content");
                // ArrayList를 전역변수로 선언하면 팝업창 들렀다 와도 값이 남아있어서
                // 검색한 결과가 누적되서 출력된다 그래서 여기에 선언한거
                ArrayList<HashMap<String, String>> searchBoardListsResult = new ArrayList<>();

                SearchingBoardTask searchingBoardTask = new SearchingBoardTask();
                try {
                    searchingBoardResults = searchingBoardTask.execute(content).get();
                    JSONObject jsonObject = new JSONObject(searchingBoardResults);
                    Log.i(TAG, "받아온 json 압축해제 : " + jsonObject);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    Log.i(TAG, "받아온 json 압축해제 : " + jsonArray);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject dataObject = jsonArray.getJSONObject(i);

                        String bbsID = dataObject.getString("bbsID");
                        String bbsTitle = dataObject.getString("bbsTitle");
                        String userID = dataObject.getString("userID");
                        String bbsDate = dataObject.getString("bbsDate");

                        HashMap<String, String> searchBoardLists = new HashMap<String, String>();
                        searchBoardLists.put("bbsID", bbsID);
                        searchBoardLists.put("bbsTitle", bbsTitle);
                        searchBoardLists.put("userID", userID);
                        searchBoardLists.put("bbsDate", bbsDate);
                        Log.i(TAG, "hashmap에 들어간값은 : " + searchBoardLists);

                        searchBoardListsResult.add(searchBoardLists);
                    }

                    BoardRecyclerAdapter boardRecyclerAdapter = new BoardRecyclerAdapter(searchBoardListsResult);
                    recyclerView.setAdapter(boardRecyclerAdapter);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*****************************************************************************************************/
}
