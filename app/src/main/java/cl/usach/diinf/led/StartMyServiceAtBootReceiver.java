package cl.usach.diinf.led;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jiturbe on 31-08-15.
 */
public class StartMyServiceAtBootReceiver extends BroadcastReceiver {

    final String TAG= "StartMyService";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
                Log.d(TAG, "StartMyServiceAtBootReceiver ... dude");
                Intent serviceIntent = new Intent(context, MainActivity.class);
                serviceIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startService(serviceIntent);
            }
        }catch(Exception e){
            Log.d(TAG,e.toString());
        }



    }
}
