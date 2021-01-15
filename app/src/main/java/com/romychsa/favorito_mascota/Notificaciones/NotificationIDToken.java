package com.romychsa.favorito_mascota.Notificaciones;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.romychsa.favorito_mascota.Activity.MainActivity;
import com.romychsa.favorito_mascota.R;


public class NotificationIDToken extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onNewToken(@NonNull String token){
        Log.d(TAG, "Refresh token:" +  token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token){
        Log.d(TAG, token);
    }



}
