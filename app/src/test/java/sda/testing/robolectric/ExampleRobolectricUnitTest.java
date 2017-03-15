package sda.testing.robolectric;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import sda.testing.BuildConfig;
import sda.testing.R;
import sda.testing.activity.MainActivity;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ExampleRobolectricUnitTest {

    @Test
    public void isAppNameCorrect() throws Exception {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        assertEquals("Testing", activity.getString(R.string.app_name));
    }

    @Test
    public void isAppNameCorrect_withRuntimeEnviroment() throws Exception {
        assertEquals("Testing", RuntimeEnvironment.application.getString(R.string.app_name));
    }
}