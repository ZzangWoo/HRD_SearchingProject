package ZzangWoo.com.OtherFunction;

import android.app.Activity;
import android.widget.Toast;

public class BackPressCloseHandler {

    private long backKeyPressedTime = 0;

    private Activity activity;

    public BackPressCloseHandler (Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(activity, "뒤로 버튼을 한번 더 누르면 종료합니다", Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finishAffinity();
        }
    }

}
