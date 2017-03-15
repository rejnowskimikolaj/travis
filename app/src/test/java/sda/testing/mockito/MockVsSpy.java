package sda.testing.mockito;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class MockVsSpy {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void whenCreateMock() {
        List mockedList = mock(ArrayList.class);

//        doCallRealMethod().when(mockedList.add(anyString()));
        mockedList.add("one");
        verify(mockedList).add("one");

        assertEquals(0, mockedList.size());
    }

    @Test
    public void whenCreateSpye() {
        List spyList = spy(new ArrayList());

        spyList.add("one");
        verify(spyList).add("one");

        assertEquals(1, spyList.size());
    }
}