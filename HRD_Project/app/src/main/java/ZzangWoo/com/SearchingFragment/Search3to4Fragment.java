package ZzangWoo.com.SearchingFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import ZzangWoo.com.CustomTask.StudentAttendanceTask;
import ZzangWoo.com.R;
import ZzangWoo.com.StudentAttendanceActivity;

public class Search3to4Fragment extends Fragment {

    String TAG = "Search3to4Fragment Log : ";

    StudentAttendanceActivity activity;

    public final String PREFERENCE = "pref";
    SharedPreferences pref;

    String resultsWhole;
    String idxMonth = "3"; // 전체조회 하라는 걸 알려주는 값

    TextView textDate, textAttendance, textInTime, textOutTime;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (StudentAttendanceActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup fragmentView = (ViewGroup) inflater.inflate(R.layout.activity_search3to4_fragment, container, false);

        // Fragment에서는 getActivity를 통해 액티비티에서 사용한 것들을 연결해줘야함
        Context context = getActivity();

        pref = context.getSharedPreferences(PREFERENCE, context.MODE_PRIVATE);

        textDate = (TextView) fragmentView.findViewById(R.id.textDate);
        textAttendance = (TextView) fragmentView.findViewById(R.id.textAttendance);
        textInTime = (TextView) fragmentView.findViewById(R.id.textInTime);
        textOutTime = (TextView) fragmentView.findViewById(R.id.textOutTime);

        String userID = pref.getString("userID", null);
        String userName;
        if (userID.equals("root")) {
            userName = pref.getString("strUserName", null);
            Log.i(TAG, "root계정 들어옴");
        } else {
            userName = pref.getString("userName", null);
        }

        StudentAttendanceTask studentAttendanceTask = new StudentAttendanceTask();
        try {
            Log.i(TAG, "userName : " + userName + " | idxMonth : " + idxMonth);
            resultsWhole = studentAttendanceTask.execute(userName, idxMonth).get();
            Log.i(TAG, resultsWhole);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // String으로 들어온 값을 JSONObject로 1차 파싱
            JSONObject wrapObject = new JSONObject(resultsWhole);

            // JSONObject의 키 "list"의 값들을 JSONArray 형태로 변환
            JSONArray jsonArray = new JSONArray(wrapObject.getString("list"));
            Log.i(TAG, "배열 크기 : " + jsonArray.length());

            JSONObject dataJsonObject = jsonArray.getJSONObject(0);

            textDate.setText(dataJsonObject.getString("textDate"));
            textAttendance.setText(dataJsonObject.getString("textAttendance"));
            textInTime.setText(dataJsonObject.getString("textInTime"));
            textOutTime.setText(dataJsonObject.getString("textOutTime"));

            for (int i = 1; i < jsonArray.length(); i++) {
                JSONObject dataJsonObject2 = jsonArray.getJSONObject(i);

                textDate.setText(textDate.getText().toString() + "\n" + dataJsonObject2.getString("textDate"));
                textAttendance.setText(textAttendance.getText().toString() + "\n" + dataJsonObject2.getString("textAttendance"));
                textInTime.setText(textInTime.getText().toString() + "\n" + dataJsonObject2.getString("textInTime"));
                textOutTime.setText(textOutTime.getText().toString() + "\n" + dataJsonObject2.getString("textOutTime"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fragmentView;
    }
}
