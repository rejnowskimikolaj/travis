package sda.testing;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import sda.testing.activity.LoginActivity;
import sda.testing.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class Zadanie2LoginActivity {

    // Napisz testy, które sprawdzą
    // 1. czy po poprawnym zalogowaniu EditText'y do loginu i hasła do logowania są niedostępne (użyj doesNotExist())
    // 2. czy po poprawnym zalogowaniu wystartuje się MainActivity (użyj Intents.intended(), do wyszukania view użyj withID, withHint, withText)
    // 3. Czy pojawi się informacja o pustym polu email (hasErrorText - na odpowiednim view)
    // 4. Czy pojawi się informacja o pustym polu password (hasErrorText - na odpowiednim view)
    // 5. scenariusz zalogowania się bez z błędnymi danymi do logowania - sprawdz z pomocą hasErrorText

    // rozwiązanie
    @Rule
    public IntentsTestRule<LoginActivity> activityTestRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void test1() throws Exception {
        onView(withId(R.id.email)).perform(typeText("foo@example.com"));
        onView(withId(R.id.password)).perform(typeText("foo"));
        onView(withText(R.string.action_sign_in)).perform(click());
        onView(withId(R.id.email)).check(doesNotExist());
        onView(withId(R.id.password)).check(doesNotExist());
    }

    @Test
    public void test2() throws Exception {
        onView(withId(R.id.email)).perform(typeText("foo@example.com"));
        onView(withHint(R.string.prompt_password)).perform(typeText("foo"));
        onView(withText(R.string.action_sign_in)).perform(click());
        Intents.intended(hasComponent(MainActivity.class.getName()));
    }

    @Test
    public void test3() throws Exception {
        onView(withId(R.id.email)).perform(typeText(""));
        onView(withText(R.string.action_sign_in)).perform(click());
        onView(withId(R.id.email)).check(matches(hasErrorText(activityTestRule.getActivity().getString(R.string.error_field_required))));
    }

    @Test
    public void test4() throws Exception {
        onView(withId(R.id.email)).perform(typeText("someEmail"));
        onView(withHint(R.string.prompt_password)).perform(typeText(""));
        onView(withText(R.string.action_sign_in)).perform(click());
        onView(withId(R.id.password)).check(matches(hasErrorText(activityTestRule.getActivity().getString(R.string.error_field_required))));
        // Dla ambitnych - Napisz własną metodę withNullError zwracającą odpowiedni Matcher dla view R.id.email sprawdzającą null'owy getError().
        // onView(withId(R.id.email)).check(matches(withNullError()));
    }

    private Matcher<? super View> withNullError() {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("has not null error");
            }

            @Override
            public boolean matchesSafely(View view) {
                return (view instanceof EditText) && ((EditText) view).getError() == null;
            }
        };
    }

    @Test
    public void test5() throws Exception {
        onView(withId(R.id.email)).perform(typeText("someEmail"));
        onView(withHint(R.string.prompt_password)).perform(typeText("somePassword"));
        onView(withText(R.string.action_sign_in)).perform(click());
        onView(withId(R.id.password)).check(matches(hasErrorText(activityTestRule.getActivity().getString(R.string.error_incorrect_login))));
    }

}