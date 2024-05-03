package com.thekids1002.catchbank.Permissions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

public class Permission {

    public static void checkNotificationListenerPermission(Context context) {
        if (!isNotificationListenerEnabled(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Notification Listener Service");
            builder.setMessage("Please grant Notification Access permission to enable this feature.");
            builder.setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                    context.startActivity(intent);
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        }
    }

    public static boolean isNotificationListenerEnabled(Context  context) {
        String packageName = context.getPackageName();
        String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (flat != null) {
            return flat.contains(packageName);
        }
        return false;
    }

}
