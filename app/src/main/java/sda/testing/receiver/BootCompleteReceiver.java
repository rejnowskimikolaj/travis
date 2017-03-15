package sda.testing.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import sda.testing.service.SampleIntentService;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentService = new Intent(context, SampleIntentService.class);
        context.startService(intentService);
    }
}
