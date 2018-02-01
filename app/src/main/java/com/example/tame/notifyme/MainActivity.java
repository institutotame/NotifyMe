package com.example.tame.notifyme;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mNotifyButton;
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNotifyButton = findViewById(R.id.notify);
        mNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }


    //Method to send Notification
    public void sendNotification() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("canalUno", "canalUno", NotificationManager.IMPORTANCE_HIGH);
            mNotifyManager.createNotificationChannel(mChannel);
        }

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, "canalUno")
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(notificationPendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);



        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);

       }

}
