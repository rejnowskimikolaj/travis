package sda.testing.robolectric;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

import sda.testing.BuildConfig;
import sda.testing.receiver.BootCompleteReceiver;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class Zadanie5BootCompleteReceiver {
    private ShadowApplication application;

    // Napisz testy, które sprawdzą, czy ( przyda się metoda ShadowApplication.getInstance(), hasReceiverForIntent()):
    // 1. Czy nasz aplikacja ma zarejestrowany receiver dla akcji "ACTION_BOOT_COMPLETED"
    // 2. Czy nasz aplikacja ma zarejestrowany BootCompleteReceiver, wsród listy zarejestrowanych receiverów (za pomocą nazwy klasy)
    // (przyda się getRegisteredReceivers() i .getBroadcastReceiver() na obiektach ShadowApplication.Wrapper)
    // 3. Dla ambitnych - po wykonaniu zadania 2, przepisz je z wykorzystaniem własnego TypeSafeMatcher'a, który jako parametr przyjmuje klasę Class,
    //  a działa na obiekcie List<ShadowApplication.Wrapper>.

    // rozwiązanie
    @Before
    public void setUp() throws Exception {
        application = ShadowApplication.getInstance();
    }

    @Test
    public void test1_registerServiceOnDeviceBootCompleted() {
        Intent intent = new Intent(Intent.ACTION_BOOT_COMPLETED);
        assertTrue("Reboot Listener not registered ", application.hasReceiverForIntent(intent));
    }

    @Test
    public void test2_myBootCompleteReceiverIsRegistered() {
        List<ShadowApplication.Wrapper> registeredReceivers = application.getRegisteredReceivers();
        boolean receiverFound = false;
        for (ShadowApplication.Wrapper wrapper : registeredReceivers) {
            if (!receiverFound) {
                receiverFound = BootCompleteReceiver.class.getSimpleName().equals(wrapper.getBroadcastReceiver().getClass().getSimpleName());
            }
        }
        assertTrue("No receiver found", receiverFound);
    }

    @Test
    public void test3_myBootCompleteReceiverIsRegisteredAdvanced() {
        assertThat(application.getRegisteredReceivers(), new ReceiverMatcher(BootCompleteReceiver.class));
    }

    public class ReceiverMatcher extends org.hamcrest.TypeSafeMatcher<List<ShadowApplication.Wrapper>> {

        private final String receiverSimpleName;

        ReceiverMatcher(final Class receiverSimpleName) {
            this.receiverSimpleName = receiverSimpleName.getSimpleName();
        }

        @Override
        public void describeTo(org.hamcrest.Description description) {
            description.appendText("matches regular expression=`" + receiverSimpleName + "`");
        }

        @Override
        protected boolean matchesSafely(List<ShadowApplication.Wrapper> item) {
            boolean receiverFound = false;
            for (ShadowApplication.Wrapper wrapper : item) {
                if (!receiverFound) {
                    receiverFound = receiverSimpleName.equals(wrapper.broadcastReceiver.getClass().getSimpleName());
                }
            }
            return receiverFound;
        }
    }
}
