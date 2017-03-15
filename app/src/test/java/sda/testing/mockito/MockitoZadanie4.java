package sda.testing.mockito;

import android.content.res.Resources;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import sda.testing.R;
import sda.testing.activity.MainActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoZadanie4 {

    // 1. Make a MainActivity mock, that has mocked Resources, when anyInt is passed to getString() always a "NotTesting" String is returned
    // 2. Make a MainActivity mock, that has mocked Resources, when only R.string.app_name is passed to getString(), always a "NotTesting" String
    //    is returned. Check if other string is not overrided.

    // rozwiązanie
    @Test
    public void test1() {
        MainActivity mainActivity = mock(MainActivity.class);
        Resources mockResources = mock(Resources.class);
        when(mockResources.getString(anyInt())).thenReturn("NotTesting");
        when(mainActivity.getResources()).thenReturn(mockResources);

        assertEquals("NotTesting", mainActivity.getResources().getString(R.string.app_name));
        assertEquals("NotTesting", mainActivity.getResources().getString(R.string.prompt_email));
    }

    @Test
    public void test2() {
        MainActivity mainActivity = mock(MainActivity.class);
        Resources mockResources = mock(Resources.class);
        when(mockResources.getString(ArgumentMatchers.eq(R.string.app_name))).thenReturn("NotTesting");
        when(mainActivity.getResources()).thenReturn(mockResources);

        assertEquals("NotTesting", mainActivity.getResources().getString(R.string.app_name));
        assertNotEquals("NotTesting", mainActivity.getResources().getString(R.string.prompt_email));
    }
}