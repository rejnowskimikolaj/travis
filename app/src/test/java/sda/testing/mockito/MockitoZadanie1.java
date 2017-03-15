package sda.testing.mockito;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoZadanie1 {

    // Uzupełnij testy, wykorzystując metodę mock()

    @Test
    public void test1() {
        MyClass test = null;

        // rozwiązanie
        test = mock(MyClass.class);
        when(test.getUniqueId()).thenReturn(43);
        // rozwiązanie

        assertEquals(test.getUniqueId(), 43);
    }

    @Test
    public void test2() {
        Iterator i = null;

        // rozwiązanie
        i = mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("jest fajne");
        // rozwiązanie

        assertEquals("Mockito jest fajne", i.next() + " " + i.next());
    }

    @Test
    public void test3() {
        Comparable c = null;

        // rozwiązanie
        c = mock(Comparable.class);
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("Android Studio")).thenReturn(2);
        // rozwiązanie

        assertEquals(1, c.compareTo("Mockito"));
        assertEquals(2, c.compareTo("Android Studio"));
    }

    @Test
    public void test4() {
        Comparable c = null;

        // użuj jednej metody when!
        // rozwiązanie
        c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        // rozwiązanie

        assertEquals(-1, c.compareTo(9));
        assertEquals(-1, c.compareTo(-12));
        assertNotEquals(-1, c.compareTo(0.032f));
        assertNotEquals(-1, c.compareTo(2.0d));
        assertNotEquals(-1, c.compareTo(false));
    }

    @Test(expected = MyException.class)
    public void test5() throws IOException {
        // create an configure mock
        OutputStream stream = null;

        // close() powinien wyrzucić wyjątek MyException()
        // rozwiązanie
        stream = mock(OutputStream.class);
        doThrow(new MyException()).when(stream).close();
        // rozwiązanie

        OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
        streamWriter.close();
    }

    private class MyClass {
        private int uniqueId;

        public MyClass(int i) {
            uniqueId = i;
        }

        int getUniqueId() {
            return uniqueId;
        }
    }

    private class MyException extends IOException {
    }
}