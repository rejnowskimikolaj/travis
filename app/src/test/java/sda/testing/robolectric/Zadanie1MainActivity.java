package sda.testing.robolectric;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import sda.testing.BuildConfig;
import sda.testing.R;
import sda.testing.activity.MainActivity;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class Zadanie1MainActivity {

    // Napisz testy, które sprawdzą, czy :
    // 1. Czy tekst w TextView jest ustawiony na domyślny
    // 2. Czy tekst w TextView jest poprawny po kliknięciu na pierwszy Button
    // 3. Czy tekst w TextView jest poprawny po kliknięciu kilku Button'ów
    // 4. Czy Menu na actionBar ma poprawne tytuły (teksty) - przydatne shadowOf(activity)
    // 5. Czy kliknięcie w pierwszy Menu item spowoduje pojawienie się Toast'a - przydatna metoda shadowOf(activity) i klasa ShadowToast
    // 6. Czy domyślny tytuł ActionBar'a jest poprawny
    // 7. Czy niemiecki tytuł ActionBar'a jest poprawny

    // rozwiązanie
    private MainActivity activity;
    private Button button1;
    private Button button2;
    private TextView text;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
        button1 = (Button) activity.findViewById(R.id.button1);
        button2 = (Button) activity.findViewById(R.id.button2);
        text = (TextView) activity.findViewById(R.id.text);
    }

    @Test
    public void test1_noButtonClicked() {
        assertEquals("Initial text", text.getText());
    }

    @Test
    public void test2_button1Click() {
        button1.performClick();
        assertEquals("Button 1", text.getText());
    }

    @Test
    public void test3_severalButtonClicked() {
        button1.performClick();
        assertEquals("Button 1", text.getText());
        button2.performClick();
        assertEquals("Button 2", text.getText());
    }

    @Test
    public void test4_menuInflatedProperly() {
        final Menu menu = shadowOf(activity).getOptionsMenu();
        assertThat(menu.findItem(R.id.item1).getTitle().toString(), is("First menu item"));
        assertThat(menu.findItem(R.id.item2).getTitle().toString(), is("Second menu item"));
    }

    @Test
    public void test5_clickMenuItem_ToastShowed() {
        shadowOf(activity).clickMenuItem(R.id.item1);
        assertThat(ShadowToast.getTextOfLatestToast(), is("Clicked Item 1"));
    }

    @Test
    public void test6_toolbarTitle() {
        assertThat(activity.getActionBar().getTitle().toString(), is("Testing"));
    }

    @Config(qualifiers = "de")
    @Test
    public void test7_toolbarTitle_Deutsch() {
        assertThat(activity.getActionBar().getTitle().toString(), is("Testing-de"));
    }
}