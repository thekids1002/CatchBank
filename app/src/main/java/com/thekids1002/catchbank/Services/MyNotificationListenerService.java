package com.thekids1002.catchbank.Services;

import android.app.Notification;
import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import com.thekids1002.catchbank.DTO.PostRequest;
import com.thekids1002.catchbank.DTO.PostResponse;
import com.thekids1002.catchbank.DTO.Vietcombank;
import com.thekids1002.catchbank.Fragment.VCBFragment;
import com.thekids1002.catchbank.MainActivity;
import com.thekids1002.catchbank.Retrofit.APIClient;
import com.thekids1002.catchbank.Services.retrofit_services.PostService;
import com.thekids1002.catchbank.Utils.Constant;
import com.thekids1002.catchbank.Utils.HttpStatusCode;
import com.thekids1002.catchbank.Utils.LogUtil;

import java.util.ArrayList;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyNotificationListenerService extends NotificationListenerService {
    public Context that = this;
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        String packageName = sbn.getPackageName();
        if(Constant.gI().mapSupportBank.containsKey(packageName)){
            try{
                Thread.sleep(500);
            }
            catch (Exception e){

            }
            // nội dung thông báo lấy được từ điện thoại
            String notificationContent = sbn.getNotification().extras.getString(Notification.EXTRA_TEXT);
            // Log
            LogUtil.LogDebug(notificationContent);

            // thực hiện tạo request và post
            String nameOfBank = Constant.gI().mapSupportBank.get(packageName);
            PostRequest post = new PostRequest(notificationContent,nameOfBank);
            doPost(notificationContent, post);
        }
    }

    private void doPost(String notificationContent, PostRequest post) {
       try{
           Call<PostResponse> call = APIClient.getClient().create(PostService.class).postBank(post);
           call.enqueue(new Callback<PostResponse>() {
               @Override
               public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                   if(response.isSuccessful() && response.code() == HttpStatusCode.OK.getCode()){
                       if(post.bankName.equals("VCB")){
                           Vietcombank vcb = BankService.gI().getVietCombankEntity(post);
                           if(BankService.gI().insertVCB(vcb,that )){
                               VCBFragment.vcbList.clear();
                               VCBFragment.vcbList.addAll(BankService.gI().getAllVcbs(that));
                               VCBFragment.vcbAdapter.notifyDataSetChanged();
                           }
                       }
                   }
               }

               @Override
               public void onFailure(Call<PostResponse> call, Throwable t) {
                   Toast.makeText(that, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
               }
           });
       }
       catch (Exception e){
           e.printStackTrace();
           LogUtil.LogDebug(e.getMessage());
           Toast.makeText(that,"Error" + e.getMessage(), Toast.LENGTH_SHORT);
       }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        String notificationContent = "Remove|" + sbn.getNotification().tickerText + "|" + sbn.getPackageName();
        LogUtil.LogDebug(notificationContent);
    }
}