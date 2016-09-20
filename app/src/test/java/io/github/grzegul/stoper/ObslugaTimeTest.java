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
    MainActivity ma = new MainActivity();
    private String expected;
    private String s;


    @Test
    public void obslugaTime_checkIfDataIsCorrect_fullTimeFormat(){
        expected =  "21:15";
        s = "21:15";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }

}
