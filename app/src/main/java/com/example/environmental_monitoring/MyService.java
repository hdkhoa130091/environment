package com.example.environmental_monitoring;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.app.Service;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MyService extends Service {
    DatabaseReference mData;
    private static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "Simplified Coding";
    private static final String CHANNEL_DESC = "Android Push Notification Tutorial";
    Settings setting1 = new Settings(20.0f, 35.0f, 50.0f, 90.0f , 20.0f, 30.0f);
    Settings setting2 = new Settings(20.0f, 35.0f, 50.0f, 90.0f , 20.0f, 30.0f);

    public MyService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mData = FirebaseDatabase.getInstance().getReference();

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(CHANNEL_DESC);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        mData.child("Setting_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (Objects.requireNonNull(snapshot.getValue()).hashCode() == 1) {
                    setting1 = snapshot.getValue(Settings.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child("Setting_2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (Objects.requireNonNull(snapshot.getValue()).hashCode() == 1) {
                    setting2 = snapshot.getValue(Settings.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child("Node_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Data data1 = snapshot.getValue(Data.class);

                assert data1 != null;
                assert setting1 !=null;

                if(data1.temperature < setting1.tMin) sendNotification_1();

                if(data1.temperature > setting1.tMax) sendNotification_2();

                if(data1.humidity < setting1.hMin) sendNotification_3();

                if(data1.humidity > setting1.hMax) sendNotification_4();

                if (data1.mq135 > setting1.mqMax) sendNotification_5();

                if(data1.dust_density > setting1.dustMax) sendNotification_6();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child("Node_2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Data data2 = snapshot.getValue(Data.class);

                assert data2 != null;
                assert setting2 !=null;

                if(data2.temperature < setting2.tMin) sendNotification_7();

                if(data2.temperature > setting2.tMax) sendNotification_8();

                if(data2.humidity < setting2.hMin) sendNotification_9();

                if(data2.humidity > setting2.hMax) sendNotification_10();

                if (data2.mq135 > setting2.mqMax) sendNotification_11();

                if(data2.dust_density > setting2.dustMax) sendNotification_12();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification_1() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo nhiệt độ");
        mBuilder.setContentText("Nhiệt độ Node 1 quá thấp");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(1, mBuilder.build());
    }

    private void sendNotification_2() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo nhiệt độ");
        mBuilder.setContentText("Nhiệt độ Node 1 quá cao");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(2, mBuilder.build());
    }

    private void sendNotification_3() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo độ ẩm");
        mBuilder.setContentText("Độ ẩm Node 1 quá thấp");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(3, mBuilder.build());
    }

    private void sendNotification_4() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo độ ẩm");
        mBuilder.setContentText("Độ ẩm Node 1 quá cao");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(4, mBuilder.build());
    }

    private void sendNotification_5() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo CO2");
        mBuilder.setContentText("Nồng độ CO2 Node 1 quá cao");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(5, mBuilder.build());
    }

    private void sendNotification_6() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo bụi");
        mBuilder.setContentText("Nồng độ bụi Node 1 quá cao");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(6, mBuilder.build());
    }

    private void sendNotification_7() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo nhiệt độ");
        mBuilder.setContentText("Nhiệt độ Node 2 quá thấp");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(7, mBuilder.build());
    }

    private void sendNotification_8() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo nhiệt độ");
        mBuilder.setContentText("Nhiệt độ Node 2 quá cao");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(8, mBuilder.build());
    }

    private void sendNotification_9() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo độ ẩm");
        mBuilder.setContentText("Độ ẩm Node 2 quá thấp");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(9, mBuilder.build());
    }

    private void sendNotification_10() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo độ ẩm");
        mBuilder.setContentText("Độ ẩm Node 2 quá cao");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(10, mBuilder.build());
    }

    private void sendNotification_11() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo CO2");
        mBuilder.setContentText("Nồng độ CO2 Node 2 quá cao");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(11, mBuilder.build());
    }

    private void sendNotification_12() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle("Cảnh báo bụi");
        mBuilder.setContentText("Nồng độ bụi Node 2 quá cao");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplication());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(12, mBuilder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
