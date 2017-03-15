package sda.testing.robolectric;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import sda.testing.BuildConfig;
import sda.testing.R;
import sda.testing.activity.MainActivity;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class Zadanie7MainActivityLifecycle {

    // Napisz testy, które sprawdzą, czy ( przyda się ActivityController<MainActivity> = Robolectric.buildActivity(MainActivity.class) ):
    // 1. czy po kliknieciu przycisku 1 TextView będzie mieć ustawiony tekst "Button 1", następnie po zasymulowaniu rotacji,
    // czy tekst w TextView powróci na domyślny.

    // rozwiązanie
    private Button button1;
    private TextView text;

    private ActivityController<MainActivity> controller;
    private MainActivity activity;

    @Before
    public void setUp() {
        controller = Robolectric.buildActivity(MainActivity.class);
        activity = controller.create().start().resume().get();
    }

    @Test
    public void test1_mainActivityLifecycle() {
        bindViews();
        button1.performClick();
        assertEquals("Button 1", text.getText());

        Bundle bundle = new Bundle();
        controller.saveInstanceState(bundle)
                .pause()
                .stop()
                .destroy();

        controller = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .restoreInstanceState(bundle)
                .resume();
        activity = controller.get();

        bindViews();

        assertEquals("Initial text", text.getText());
    }

    private void bindViews() {
        button1 = (Button) activity.findViewById(R.id.button1);
        text = (TextView) activity.findViewById(R.id.text);
    }

    @After
    public void tearDown() {
        controller.pause().stop().destroy();
    }
}