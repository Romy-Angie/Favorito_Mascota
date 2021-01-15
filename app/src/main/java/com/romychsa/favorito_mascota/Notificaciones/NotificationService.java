package com.romychsa.favorito_mascota.Notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.romychsa.favorito_mascota.Activity.MainActivity;
import com.romychsa.favorito_mascota.Activity.PerfilUsuario;
import com.romychsa.favorito_mascota.Fragment.PerfilFragment;
import com.romychsa.favorito_mascota.R;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat.WearableExtender;

import static com.romychsa.favorito_mascota.Activity.MainActivity.CANAL_ID;

public class NotificationService extends FirebaseMessagingService {

    public static  final  String TAG = "FIREBASE";
    public static  final  int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Intent i = new Intent();
            i.setAction("TOQUE_ANIMAL");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

            Intent j = new Intent(this, MainActivity.class);
            //j.setAction("VER_MI_PERFIL");
            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, j, PendingIntent.FLAG_ONE_SHOT);

            Intent k = new Intent();
            k.setAction("SEGUIR_USUARIO");
            PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, k, PendingIntent.FLAG_UPDATE_CURRENT);

            Intent l = new Intent(this, PerfilUsuario.class);
            //l.setAction("PERFIL_USUARIO");
            PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0, l, PendingIntent.FLAG_ONE_SHOT);


            Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Action action =
                    new NotificationCompat.Action.Builder(R.drawable.ic_full_sms,
                            getString(R.string.texto_accion_toque), pendingIntent)
                            .build();

            NotificationCompat.Action action1 =
                    new NotificationCompat.Action.Builder(R.drawable.ic_full_verperfil,
                            getString(R.string.texto_accion_verperfil), pendingIntent1)
                            .build();

            NotificationCompat.Action action2 =
                    new NotificationCompat.Action.Builder(R.drawable.ic_full_follow,
                            getString(R.string.texto_accion_seguir), pendingIntent2)
                            .build();

            NotificationCompat.Action action3 =
                    new NotificationCompat.Action.Builder(R.drawable.ic_full_verusuario,
                            getString(R.string.texto_accion_verusuario), pendingIntent3)
                            .build();

            NotificationCompat.WearableExtender wearableExtender =
                    new NotificationCompat.WearableExtender();

            NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CANAL_ID)
                    .setSmallIcon(R.drawable.notification)
                    .setContentTitle("Notification")
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setSound(sonido)
                    .setContentIntent(pendingIntent1)
                    .setAutoCancel(true)
                    //'.extend(wearableExtender.addAction(action))
                    .extend(wearableExtender.addAction(action1))
                    .extend(wearableExtender.addAction(action2))
                    .extend(wearableExtender.addAction(action3))
                    ;
/*
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification.build());
*/
            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID, notification.build());


        }
    }
}
