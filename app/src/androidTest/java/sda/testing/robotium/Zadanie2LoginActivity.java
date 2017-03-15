package sda.testing.robotium;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import sda.testing.R;
import sda.testing.activity.LoginActivity;
import sda.testing.activity.MainActivity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Zadanie2LoginActivity {

    // Napisz testy, które sprawdzą
    // 1. czy po poprawnym zalogowaniu nie pojawią się informacje o błędach na EditText'ach
    // 2. czy po poprawnym zalogowaniu wystartuje się MainActivity
    // 3. Czy pojawi się informacja o pustym polu email
    // 4. Czy pojawi się informacja o pustym polu password
    // 5. scenariusz zalogowania się bez z błędnymi danymi do logowania

    // rozwiązanie
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private Solo solo;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
    }

    @Test
    public void test1() throws Exception {
        EditText email = (EditText) solo.getView(R.id.email);
        EditText password = (EditText) solo.getView(R.id.password);
        solo.typeText(email, "foo@example.com");
        solo.typeText(password, "foo");
        solo.clickOnButton(solo.getString(R.string.action_sign_in));
        assertFalse(solo.waitForText(solo.getString(R.string.prompt_email), 1, 2000, true));
        assertFalse(solo.waitForText(solo.getString(R.string.prompt_password), 1, 2000, true));
    }

    @Test
    public void test2() throws Exception {
        EditText email = (EditText) solo.getView(R.id.email);
        EditText password = (EditText) solo.getView(R.id.password);
        solo.typeText(email, "foo@example.com");
        solo.typeText(password, "foo");
        solo.clickOnButton(solo.getString(R.string.action_sign_in));
        solo.assertCurrentActivity("Expected MainActivity Activity", MainActivity.class);
    }

    @Test
    public void test3() throws Exception {
        EditText email = (EditText) solo.getView(R.id.email);
        solo.typeText(email, "");
        solo.clickOnButton(solo.getString(R.string.action_sign_in));
        solo.assertCurrentActivity("Expected LoginActivity Activity", LoginActivity.class);
        assertTrue(solo.waitForText(solo.getString(R.string.error_field_required), 1, 2000, true));
    }

    @Test
    public void test4() throws Exception {
        EditText email = (EditText) solo.getView(R.id.email);
        EditText password = (EditText) solo.getView(R.id.password);
        solo.typeText(email, "someEmail");
        solo.typeText(password, "");
        solo.clickOnButton(solo.getString(R.string.action_sign_in));
        solo.assertCurrentActivity("Expected LoginActivity Activity", LoginActivity.class);
        assertTrue(solo.waitForText(solo.getString(R.string.error_field_required), 1, 2000, true));
    }

    @Test
    public void test5() throws Exception {
        EditText email = (EditText) solo.getView(R.id.email);
        EditText password = (EditText) solo.getView(R.id.password);
        solo.typeText(email, "someEmail");
        solo.typeText(password, "somePassword");
        solo.clickOnButton(solo.getString(R.string.action_sign_in));
        solo.assertCurrentActivity("Expected LoginActivity Activity", LoginActivity.class);
        assertTrue(solo.waitForText(solo.getString(R.string.error_incorrect_login), 1, 2000, true));
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}