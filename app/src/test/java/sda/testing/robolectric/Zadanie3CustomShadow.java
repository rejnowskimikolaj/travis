package sda.testing.robolectric;

import android.content.res.Resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import sda.testing.BuildConfig;
import sda.testing.R;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class Zadanie3CustomShadow {

    // Napisz testy, które sprawdzą ( przydadzą się @Implements(Resources.class), @Config(shadows = ) )
    // 1. poprawność Stringa 'numberOfSongsAvailable' w liczbie mnogiej
    // 2. Nadpiszesz za pomocą własnej implementacji klasy ShadowResources, metodę pobierania QuantityString'a, tak aby zwracała tekst "Some String" - tylko
    //    ale tylko dla QuantityString'a o id numberOfSongsAvailable. Dodaj inny QuantityString do resource'ów i sprawdź, czy on się nie nadpisuje.

    // rozwiązanie
    @Test
    public void test1_checkPlural_OneElement() {
        Resources resources = RuntimeEnvironment.application.getResources();
        assertThat(resources.getQuantityString(R.plurals.numberOfSongsAvailable, 1, 1), is("1 song found."));
    }

    @Config(shadows = Zadanie3CustomShadowResources.class)
    @Test
    public void test2_checkPlural_OneElement_customShadow() {
        Resources resources = RuntimeEnvironment.application.getResources();
        assertThat(resources.getQuantityString(R.plurals.numberOfSongsAvailable, 1, 1), is("Some String"));
    }
}

