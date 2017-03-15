package sda.testing;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import sda.testing.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class Zadanie7MainActivityLifecycle {

    // Napisz testy, które sprawdzą:
    // 1. sprawdź czy Button o id button4 jest niewidoczny, zmien orientacje, sprawdz czy jest widoczny,
    // kliknij, sprawdz czy tekst sie zmienił ( użyj activityTestRule.getActivity().setRequestedOrientation(), withEffectiveVisibility

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test1() throws Exception {
        onView(withId(R.id.button4)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.button4)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withText("Button 4")).perform(click());
        onView(withId(R.id.text)).check(matches(withText("Button 4")));
    }
}