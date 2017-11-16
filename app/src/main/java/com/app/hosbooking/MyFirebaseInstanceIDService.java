package com.app.hosbooking;

import com.app.hosbooking.util.UtilLog;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "Harry_MyFirebaseIIDService";

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        UtilLog.d(TAG, "Refreshed token: " + token);

        // 생성등록된 토큰을 개인 앱서버에 보내 저장해 두었다가 추가 뭔가를 하고 싶으면 할 수 있도록 한다.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        UtilLog.d(TAG,"sendRegistrationToServer->>>>");


        /*TelephonyManager telManager = (TelephonyManager)this.getSystemService(this.TELEPHONY_SERVICE);
        String phoneNum = telManager.getLine1Number();

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .add("Phone", phoneNum)
                .build();

        AppConfig.app_token = token;

        //request
        Request request = new Request.Builder()
                .url("http://girls.showdong.tv/push2/register.php")
                .post(body)
                .build();
        UtilLog.d(TAG,"requeat->>>>");
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            UtilLog.e(TAG,"error->"+e);
        }*/

    }
}