package ZzangWoo.com.CustomTask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class StudentAttendanceTask extends AsyncTask <String, Void, String> {

    String TAG = "StudentAttendanceTask Log : ";

    String sendMsg, receiveMsg;

    @Override
    protected String doInBackground(String... strings) {

        try {
            String str;
            URL url = new URL("http://3.17.133.136:8080/Team1/App/App_StudentAttendance.jsp");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            Log.i(TAG, "userName : " + strings[0] + " | idxMonth : " + strings[1]);
            sendMsg = "userName="+strings[0]+"&idxMonth="+strings[1];

            osw.write(sendMsg); // OutputStreamWriter에 담아 전송
            osw.flush();

            // jsp와 통신이 정상적으로 되었을 때 작성되는 코드
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i(TAG, receiveMsg);
            } else {
                Log.i("통신 결과!!", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
