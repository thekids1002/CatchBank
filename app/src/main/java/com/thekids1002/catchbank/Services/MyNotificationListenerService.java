package com.thekids1002.catchbank.Services;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

import com.thekids1002.catchbank.DTO.PostRequest;
import com.thekids1002.catchbank.DTO.PostResponse;
import com.thekids1002.catchbank.MainActivity;
import com.thekids1002.catchbank.Retrofit.APIClient;
import com.thekids1002.catchbank.Services.retrofit_services.RequestPostService;
import com.thekids1002.catchbank.Utils.Constant;
import com.thekids1002.catchbank.Utils.HttpStatusCode;
import com.thekids1002.catchbank.Utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyNotificationListenerService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if(Constant.gI().listBankPackage.contains(sbn.getPackageName())){
            String notificationContent = sbn.getNotification().extras.getString(Notification.EXTRA_TEXT);
            LogUtil.LogDebug(notificationContent);
            PostRequest post = new PostRequest(notificationContent,sbn.getPackageName());
            doPost(notificationContent, post);
        }
    }

    private void doPost(String notificationContent, PostRequest post) {
        Call<PostResponse> call = APIClient.getClient().create(RequestPostService.class).postBank(post);
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    if(response.isSuccessful() && response.code() == HttpStatusCode.OK.getCode()){
                        MainActivity.addNotificationToList(notificationContent);
                    }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Toast.makeText(MyNotificationListenerService.this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        String notificationContent = "Remove|" + sbn.getNotification().tickerText + "|" + sbn.getPackageName();
        LogUtil.LogDebug(notificationContent);
    }
}