package sda.testing;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import sda.testing.activity.MainActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class Zadanie1MainActivity {

    // Napisz testy, które sprawdzą, czy :
    // 1. Czy na View z Id R.id.text tekst jest ustawiony na domyślny
    // 2. Czy tekst w TextView jest poprawny po kliknięciu na pierwszy Button
    // 3. Czy tekst w TextView jest poprawny po kliknięciu kilku Button'ów (wyszukaj Button'a po tekście na nim)
    // 4. Czy po pokazaniu Menu z Action Bar'a są widoczne teksty z MenuItem1 i MenuItem2 ( użyj withText() i withEffectiveVisibility)
    // 5. Czy kliknięcie w pierwszy Menu item spowoduje pojawienie się Toast'a
    // 6. Czy View z tekstem "Testing" ma rodzica o id R.id.toolbar
    // 7. Czy Button 2 jest na lewo od Button 3
    // 8. Czy View z ustawionym contentDescription "to jest button 3" ma jest tekst Button 3 ( użyj withContentDescription)
    // 9. Czy wszystkie Buttony mają id R.id.button3 - test powinien failować. Sprawdź jak wyglądają logi Espresso. Zignoruj poźniej test.
    // 10. Wykorzystaj test 9 - sprecyzuj wyszukiwanie view dodatkowo po tekście ( użyj allOf )
    // 11. Sprawdź Toast po kliknięciu w 10 element RecyclerView ( użyj RecyclerViewActions.actionOnItemAtPosition )

    // rozwiązanie
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test1() throws Exception {
        onView(withId(R.id.text)).check(matches(withText("Initial text")));
    }

    @Test
    public void test2() throws Exception {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.text)).check(matches(withText("Button 1")));
    }

    @Test
    public void test3() throws Exception {
        onView(withText("Button 1")).perform(click());
        onView(withId(R.id.text)).check(matches(withText("Button 1")));
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.text)).check(matches(withText("Button 2")));
    }

    @Test
    public void test4() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.menu_item_1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withText(R.string.menu_item_2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void test5() throws Exception {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.menu_item_1)).perform(click());

        // sprawdzenie Toast'a
        onView(withText("Clicked Item 1")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void test6() throws Exception {
        onView(withText("Testing")).check(matches(withParent(withId(R.id.toolbar))));
    }

    @Test
    public void test7() throws Exception {
        onView(withId(R.id.button2)).check(isLeftOf(withId(R.id.button3)));
    }

    @Test
    public void test8() throws Exception {
        onView(withContentDescription("to jest button 3")).check(matches(withId(R.id.button3)));
    }

    @Ignore
    @Test
    public void test9() throws Exception {
        onView(Matchers.<View>instanceOf(Button.class)).check(matches(withId(R.id.button3)));
    }

    @Test
    public void test10() throws Exception {
        onView(allOf(Matchers.<View>instanceOf(Button.class), withText("Button 3"))).check(matches(withId(R.id.button3)));
    }

    @Test
    public void test11() throws Exception {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));

        // sprawdzeni Toast'a
        onView(withText("10 : Android10")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
}