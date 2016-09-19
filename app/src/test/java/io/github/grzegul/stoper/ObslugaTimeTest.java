package io.github.grzegul.stoper;

import android.test.mock.MockContext;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by jgrzegulski on 2016-09-08.
 */
public class ObslugaTimeTest {
    MainActivity ma;
    private String expected;

    @Before
    public void  setUp(){

        expected =  "0:30";
        ma = new MainActivity();
    }

    @Test
    public void obslugaTime_pelnyFormatCzasowy(){
        ma.setSekundy(30);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
}
