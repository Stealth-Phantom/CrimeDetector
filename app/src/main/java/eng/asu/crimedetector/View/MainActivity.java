package eng.asu.crimedetector.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import eng.asu.crimedetector.Model.MyFirebaseMessagingService;
import eng.asu.crimedetector.R;

public class MainActivity extends AppCompatActivity {
    MyFirebaseMessagingService myFirebaseMessagingService = new MyFirebaseMessagingService();
    String token;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("MAIN", "Fetching FCM registration token failed", task.getException());
                            Toast.makeText(MainActivity.this,"Fetching Token Failed", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else{
                            // Get new FCM registration token
                            token = task.getResult();

                            // Log and toast
                            //String msg = getString(R.string.msg_token_fmt, token);
                            Log.d("MAIN", token);
                            Toast.makeText(MainActivity.this,"Token starting with " + token.substring(0,5) + " was created", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void clickSafe(View view)
    {
        Log.d("CLICK","clicked!!!");
        count++;
        if(count == 3)
        {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Label",token);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(MainActivity.this,"Token was copied to clipboard", Toast.LENGTH_LONG).show();
            count = 0;
        }
    }

}