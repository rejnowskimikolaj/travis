package sda.testing.robotium;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import sda.testing.R;
import sda.testing.activity.MainActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Zadanie1MainActivity {

    // Napisz testy, które sprawdzą, czy :
    // 1. Czy tekst w TextView jest ustawiony na domyślny (użyj metody getView())
    // 2. Czy tekst w TextView jest poprawny po kliknięciu na pierwszy Button (metoda getView(), clickOnButton(String), waitForText())
    // 3. Czy tekst w TextView jest poprawny po kliknięciu kilku Button'ów
    //    (metoda getView(), clickOnButton(index) index weź aplikacji UiAutomator, waitForText())
    // 4. Czy Menu na actionBar ma poprawne tytuły (teksty) - przydatne sendKey()
    // 5. Czy kliknięcie w pierwszy Menu item spowoduje pojawienie się Toast'a (metoda clickOnMenuItem)
    // 6. Czy domyślny tytuł ActionBar'a jest poprawny (użyj waitForTexta)

    // rozwiązanie
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Solo solo;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
    }

    @Test
    public void test1() throws Exception {
        TextView text = (TextView) solo.getView(R.id.text);
        assertEquals("Initial text", text.getText());
    }

    @Test
    public void test2() throws Exception {
        TextView text = (TextView) solo.getView(R.id.text);
        solo.clickOnButton("Button 1");
        assertTrue(solo.waitForText("Button 1", 1, 2000, true));
        assertEquals("Button 1", text.getText());
    }

    @Test
    public void test3() throws Exception {
        TextView text = (TextView) solo.getView(R.id.text);
        solo.clickOnButton("Button 1");
        assertTrue(solo.waitForText("Button 1", 1, 2000, true));
        assertEquals("Button 1", text.getText());
        solo.clickOnButton(1);
        assertTrue(solo.waitForText("Button 2", 1, 2000, true));
        assertEquals("Button 2", text.getText());
    }

    @Test
    public void test4() throws Exception {
        solo.sendKey(Solo.MENU);
        assertTrue(solo.waitForText(solo.getString(R.string.menu_item_1), 1, 2000, true));
        assertTrue(solo.waitForText(solo.getString(R.string.menu_item_2), 1, 2000, true));
    }

    @Test
    public void test5() throws Exception {
        solo.clickOnMenuItem(solo.getString(R.string.menu_item_1));
        assertTrue(solo.waitForText("Clicked Item 1", 1, 2000, true));
    }

    @Test
    public void test6() throws Exception {
        assertTrue(solo.waitForText("Testing", 1, 2000, true));
        assertEquals(R.id.toolbar, ((ViewGroup) solo.getText("Testing").getParent()).getId());
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}