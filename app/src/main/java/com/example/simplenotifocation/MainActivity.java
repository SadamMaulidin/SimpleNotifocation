package com.example.simplenotifocation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText title, mContent;
    public  static final int NOTIFICATION_ID = 1;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "Cheese Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        mContent = findViewById(R.id.content);
    }

    public void sentNotification(View view) {
        //setting notification manager
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alert)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_alert))
                .setContentTitle(title.getText().toString())
                .setContentText(mContent.getText().toString())
                .setSubText("ini sub textnya")
                .setAutoCancel(true);

        //untuk android oreo ke atas diperlukan notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null){
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();
        if (notification != null){
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}
