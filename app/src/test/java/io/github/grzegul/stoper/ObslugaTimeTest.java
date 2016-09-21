package io.github.grzegul.stoper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
        assertEquals(expected, ma.obslugaTime(s));
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_colonInt(){
        expected =  "0:15";
        s = ":15";
        assertEquals(expected, ma.obslugaTime(s));
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_intColon(){
        expected =  "2:0";
        s = "2:";
        assertEquals(expected, ma.obslugaTime(s));
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_zeroZeroColonZeroZero(){
        expected =  "0:0";
        s = "00:00";
        assertEquals(expected, ma.obslugaTime(s));
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_zeroIntColonZeroInt(){
        expected =  "2:1";
        s = "02:01";
        assertEquals(expected, ma.obslugaTime(s));
    }

}
