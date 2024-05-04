package com.thekids1002.catchbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thekids1002.catchbank.Fragment.ACBFragment;
import com.thekids1002.catchbank.Fragment.SettingFragment;
import com.thekids1002.catchbank.Fragment.VCBFragment;
import com.thekids1002.catchbank.Permissions.Permission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int VCB_FRAGMENT_ID = R.id.action_vcb;
    private static final int ACB_FRAGMENT_ID = R.id.action_acb;
    private static final int SETTINGS_FRAGMENT_ID = R.id.action_settings;
    private static ArrayList<String> notificationList;
    private static ArrayAdapter<String> adapter;
    Context that = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Permission.checkNotificationListenerPermission(that);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        replaceFragment(new VCBFragment());
    }


    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == VCB_FRAGMENT_ID) {
            replaceFragment(new VCBFragment());
            return true;
        } else if (itemId == ACB_FRAGMENT_ID) {
            replaceFragment(new ACBFragment());
            return true;
        } else if (itemId == SETTINGS_FRAGMENT_ID) {
            replaceFragment(new SettingFragment());
            return true;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }



}