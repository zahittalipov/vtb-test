package com.angelectro.testapp.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.angelectro.testapp.R;
import com.angelectro.testapp.features.message.MessageActivity;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AndroidUtils {

    private static final String CHANNEL_ID = "test_app_01";

    public static void generateNotification(Context activity, String title, String body, String id) {

        Log.wtf(title, body);

        Intent resultIntent = new Intent(activity
                , MessageActivity.class);
        resultIntent.putExtra(MessageActivity.KEY_CONTENT, body);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        NotificationManager mNotificationManager =
                (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(body)
                .setContentTitle(title)
                .setContentIntent(PendingIntent.getActivity(activity
                        , id.hashCode()
                        , resultIntent, PendingIntent.FLAG_ONE_SHOT))
                .setDefaults(Notification.DEFAULT_ALL)
                .setLights(Color.RED, 1000, 500)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = activity.getString(R.string.app_name);// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mNotificationManager.createNotificationChannel(mChannel);
            builder.setChannelId(CHANNEL_ID);
        }

        Notification n = builder.build();
        NotificationManagerCompat.from(activity).notify(id.hashCode(), n);
    }

}

