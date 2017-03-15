package sda.testing.robolectric;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import sda.testing.BuildConfig;
import sda.testing.R;
import sda.testing.activity.LoginActivity;
import sda.testing.activity.MainActivity;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class Zadanie2LoginActivity {

    // Napisz testy, które sprawdzą ( przydadzą się metody shadowOf(RuntimeEnvironment.application), getNextStartedActivity(), performClick() )
    // 1. czy po poprawnym zalogowaniu nie pojawią się informacje o błędach na EditText'ach
    // 2. czy po poprawnym zalogowaniu wystartuje się MainActivity
    // 3. scenariusz zalogowania się bez podanych danych do logowania
    // 4. scenariusz zalogowania się bez z błędnymi danymi do logowania

    // rozwiązanie
    private EditText emailView;
    private EditText passwordView;
    private Button button;

    @Before
    public void setUp() {
        Activity activity = Robolectric.setupActivity(LoginActivity.class);
        button = (Button) activity.findViewById(R.id.email_sign_in_button);
        emailView = (EditText) activity.findViewById(R.id.email);
        passwordView = (EditText) activity.findViewById(R.id.password);
    }

    @Test
    public void test1_loginSuccess() {
        emailView.setText("foo@example.com");
        passwordView.setText("foo");
        button.performClick();

        assertThat(emailView.getError(), is(nullValue()));
        assertThat(passwordView.getError(), is(nullValue()));
    }

    @Test
    public void test2_loginSuccess_MainActivityStarted() {
        emailView.setText("foo@example.com");
        passwordView.setText("foo");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        Intent nextStartedActivity = application.getNextStartedActivity();
        assertThat(nextStartedActivity.getComponent().getClassName(), is(MainActivity.class.getCanonicalName()));
    }

    @Test
    public void test3_loginWithEmptyUsernameAndPassword() {
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat(application.getNextStartedActivity(), is(nullValue()));
        assertThat(emailView.getError(), is(notNullValue()));
        assertThat(passwordView.getError(), is(notNullValue()));
    }

    @Test
    public void test4_loginFailure() {
        emailView.setText("invalid@email");
        passwordView.setText("invalidpassword");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat(application.getNextStartedActivity(), is(nullValue()));
        assertThat(emailView.getError(), is(notNullValue()));
        assertThat(passwordView.getError(), is(notNullValue()));
    }
}