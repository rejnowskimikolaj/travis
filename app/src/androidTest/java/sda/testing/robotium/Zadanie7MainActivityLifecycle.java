package sda.testing.robotium;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import sda.testing.R;
import sda.testing.activity.MainActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Zadanie7MainActivityLifecycle {

    // Napisz testy, które sprawdzą,
    // 1. sprawdź czy Button o id button4 jest niewidoczny, zmien orientacje, sprawdz czy jest widoczny, kliknij, sprawdz czy tekst sie zmienił

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Solo solo;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
    }

    @Test
    public void test1() throws Exception {
        Button button4 = (Button) solo.getView(R.id.button4);
        assertEquals(View.GONE, button4.getVisibility());

        solo.setActivityOrientation(Solo.LANDSCAPE);

        assertTrue(solo.waitForText("Button 4", 1, 2000, true));

        button4 = (Button) solo.getView(R.id.button4);
        assertEquals(View.VISIBLE, button4.getVisibility());

        solo.clickOnView(button4);

        assertTrue(solo.waitForText("Button 4", 1, 2000, true));
        TextView text = (TextView) solo.getView(R.id.text);
        assertEquals("Button 4", text.getText());
    }
}