package sda.testing.mockito;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class MockitoZadanie3 {

    // Uzupełnij testy, wykorzystując metodę spy()

    @Test
    public void test1() {
        Calculator test = null;

        // rozwiązanie
        test = spy(new Calculator(50, 10));
        when(test.add()).thenReturn(3);
        // rozwiązanie

        assertEquals(3, test.add());
        assertEquals(40, test.subtract());
        assertEquals(5.0d, test.divide(), 0.0001d);
    }

    @Test
    public void test2() {
        List spyList = spy(new LinkedList());

        // rozwiązanie
        doReturn("foo").when(spyList).get(0);
        // rozwiązanie

        assertEquals("foo", spyList.get(0));

        // podpowiedź!
        try {
            when(spyList.get(0)).thenReturn("foo");
            assertEquals("foo", spyList.get(0));
        } catch (IndexOutOfBoundsException e) {
            // test failuje!
            System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    private class Calculator {
        private int first;
        private int second;

        Calculator(int first, int second) {
            this.first = first;
            this.second = second;
        }

        int add() {
            return first + second;
        }

        int subtract() {
            return first - second;
        }

        double divide() {
            return first / second;
        }
    }
}