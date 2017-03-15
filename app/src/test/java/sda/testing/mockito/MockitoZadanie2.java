package sda.testing.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MockitoZadanie2 {

    // Napisz odpowiednie weryfikacje
    private MyClass test;

    @Before
    public void setUp() throws Exception {
        test = Mockito.mock(MyClass.class);
    }

    @Test
    public void test1() {
        test.testing(12);
        // sprawdź czy wywołano metodę testing() z parametrem 12. Użyj ArtumentMatchers.eq
        verify(test).testing(ArgumentMatchers.eq(12));
    }

    @Test
    public void test2() {
        test.getUniqueId();
        test.getUniqueId();

        // sprawdź czy dwukrotnie wywołano metode getUniqueId()
        verify(test, times(2)).getUniqueId();
    }

    @Test
    public void test3() {
        test.testing(12);

        // sprawdź czy nigdy nie wywołano metody getUniqueId()
        verify(test, never()).getUniqueId();
    }

    @Test
    public void test4() {
        test.testing(12);
        test.testing(13);

        // sprawdz czy przynajmniej raz wywołano metodę testing() z dowolym Integerem
        verify(test, atLeastOnce()).testing(anyInt());
    }

    @Test
    public void test5() {
        test.testing(12);
        test.testing(99);
        test.testing(12);

        // sprawdz czy co najwyżej dwa razy wywołano metodę testing() z parametrem 12
        verify(test, atMost(2)).testing(ArgumentMatchers.eq(12));
    }

    @Test
    public void test6() {
        test.testing(99);

        // sprawdz czy razy wywołano tylko metodę testing() z parametrem 99
        verify(test, only()).testing(ArgumentMatchers.eq(99));
    }


    private class MyClass {
        private int uniqueId;

        public MyClass(int i) {
            uniqueId = i;
        }

        int getUniqueId() {
            return uniqueId;
        }

        void testing(int i) {

        }
    }
}