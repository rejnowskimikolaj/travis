package sda.testing.mockito;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

public class ExampleMockitoUnitTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    MyDatabase databaseMock;

    @Test
    public void testQuery() {
        ClassToTest sut = new ClassToTest(databaseMock);
        sut.query("* from t");
        verify(databaseMock).query("* from t");
    }

    private class ClassToTest {
        private MyDatabase database;

        ClassToTest(MyDatabase database) {
            this.database = database;
        }

        boolean query(String s) {
            return database.query(s);
        }
    }

    private class MyDatabase {
        boolean query(String s) {
            return true;
        }
    }
}