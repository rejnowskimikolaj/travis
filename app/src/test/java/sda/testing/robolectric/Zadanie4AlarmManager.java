package sda.testing.robolectric;

import android.app.Application;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import sda.testing.BuildConfig;
import sda.testing.receiver.SampleBroadcastReceiver;
import sda.testing.service.SampleIntentService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class Zadanie4AlarmManager {

    // Napisz testy, które sprawdzą, czy ( przyda się metoda getNextStartedService() ):
    // 1. Czy metoda onReceive() z SampleBroadcastReceiver'a uruchomi odpowiedni IntentService

    // rozwiązanie
    private Application application;

    @Before
    public void setUp() throws Exception {
        application = RuntimeEnvironment.application;
    }

    @Test
    public void test1_startIntentServiceFromOnReceive() {
        new SampleBroadcastReceiver().onReceive(application, new Intent());

        Intent serviceIntent = shadowOf(application).getNextStartedService();
        assertNotNull(serviceIntent);
        assertEquals(new Intent(application, SampleIntentService.class).getComponent(), serviceIntent.getComponent());
    }
}
