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

    public static class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
        private static final String TAG = "Harry_FirebaseMsgService";

        // [START receive_message]
        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
    //        LoginActivity.mainPrefManager.loadPreference();

            String message = remoteMessage.getData().get("message");
            UtilLog.e(TAG,  "message=>"+message);

            String arr_mesg [] = message.split("\\|");

            /*if(arr_mesg != null && arr_mesg.length > 1 ){
                PushMessage mesg = new PushMessage();
                mesg.setType(arr_mesg[0]);
                mesg.setMessage(arr_mesg[1]);
                AppConfig.pushMessageList.addPushList(mesg);
                LoginActivity.mainPrefManager.save(AppConfig.pushMessageList);
                UtilLog.e(TAG,"size-"+AppConfig.pushMessageList.getpList().size());
                //추가한것
                UtilLog.e(TAG, mesg.getType() +"=>"+mesg.getMessage());

                if(SelectValue.isPushNew && mesg.getType().trim().equals("NEW")){
                    sendNotification(mesg.getMessage());
                }
                if(SelectValue.isPushReply && mesg.getType().trim().equals("REPLY")){
                    sendNotification(mesg.getMessage());
                }
                if(SelectValue.isPushNotice && mesg.getType().trim().equals("NOTICE")){
                    sendNotification(mesg.getMessage());
                }

            }*/

        }

        private void sendNotification(String messageBody) {
            /*Intent intent = new Intent(this, AlertActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//* Request code *//*, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("여고시대")
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                     .setContentIntent(pendingIntent);

            if(SelectValue.isPushSound){
                notificationBuilder.setSound(defaultSoundUri);
            }

            if(SelectValue.isPushBibrate){
                notificationBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
            }
            NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());*/
        }

    }
}