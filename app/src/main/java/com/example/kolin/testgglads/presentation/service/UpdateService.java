package com.example.kolin.testgglads.presentation.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.data.repository.RepositoryImpl;
import com.example.kolin.testgglads.domain.interactor.GetCategoryPostUC;
import com.example.kolin.testgglads.domain.model.Post;
import com.example.kolin.testgglads.domain.repository.Repository;
import com.example.kolin.testgglads.presentation.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


public class UpdateService extends Service {

    private static final String TAG = UpdateService.class.getSimpleName();

    private static final int INTERVAL = 1000 * 5;
    private GetCategoryPostUC getCategoryPostUC;
    private int currentCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        getCategoryPostUC = new GetCategoryPostUC();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service onStartCommand");

        if (!isNetworkAvailable()) {
            return START_NOT_STICKY;
        }

        currentCount = ServicePreferences.getLastResultCount(this);
        String subscribedCategory = ServicePreferences.getSubscribedCategory(this);
        if (subscribedCategory == null){
            stopSelf();
        }


        getCategoryPostUC.execute(new DisposableObserver<List<Post>>() {
            @Override
            public void onNext(List<Post> value) {
                Log.i(TAG, "Got an old result: " + Arrays.toString(value.toArray()));
                setNotification(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, subscribedCategory);

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        getCategoryPostUC.dispose();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = connectivityManager.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                connectivityManager.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }

    void setNotification(List<Post> value) {
        Intent i = MainActivity.newIntent(this);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        Notification notification = null;
        if ((value.size() - currentCount) == 1) {
            notification = new NotificationCompat.Builder(getBaseContext())
                    .setSmallIcon(R.drawable.ic_done_white_24dp)
                    .setContentTitle(value.get(value.size() - 1).getName())
                    .setContentText(value.get(value.size() - 1).getTagLine())
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();

        }
        if ((value.size() - currentCount) > 1) {
            notification = new NotificationCompat.Builder(getBaseContext())
                    .setSmallIcon(R.drawable.ic_done_white_24dp)
                    .setContentTitle("ProductHunt")
                    .setContentText("Check new post every day!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();
        }

        notificationManager.notify(0, notification);
        ServicePreferences.setLastResultCount(this, value.size());
    }

    public static void setServiceAlarmManager(Context context, boolean isOn) {

        Intent intent = new Intent(context, UpdateService.class);
        PendingIntent pintent = PendingIntent.getService(context, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (isOn)
        alarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), INTERVAL, pintent);
        else {
            alarm.cancel(pintent);
            pintent.cancel();
        }
    }

    public static boolean isServiceAlarmOn(Context context) {
        Intent i = UpdateService.newIntent(context);
        PendingIntent pi = PendingIntent
                .getService(context, 0, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    private static Intent newIntent(Context context) {
        return new Intent(context, Service.class);
    }

}
