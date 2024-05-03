package com.thekids1002.catchbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thekids1002.catchbank.Permissions.Permission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<String> notificationList;
    private static ArrayAdapter<String> adapter;
    Context that = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Permission.checkNotificationListenerPermission(that);
        ListView listView = findViewById(R.id.listView);
        notificationList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notificationList);
        listView.setAdapter(adapter);
    }

    public static void addNotificationToList(String notificationContent) {
        // Thêm nội dung thông báo vào danh sách và cập nhật ListView
        notificationList.add(notificationContent);
        adapter.notifyDataSetChanged();
    }
}