package io.github.grzegul.stoper;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jgrzegulski on 2016-09-08.
 */
public class ObslugaTimeTestExpected0 {
    MainActivity ma = new MainActivity();
    private String expected =  "0:0";
    private String s;


    @Test
    public void obslugaTime_checkIfDataIsCorrect_empty(){
        s = "";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_1Colon(){
        s = ":";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_2Colon(){
        s = "::";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_3Colon(){
        s = ":::";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_4Colon(){
        s = "::::";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_10Colon(){
        s = "::::::::::";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_intColonIntColonInt(){
        s = "3:3:3";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Test
    public void obslugaTime_checkIfDataIsCorrect_ColonIntColonInt(){
        s = ":3:3";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Ignore
    @Test
    public void obslugaTime_checkIfDataIsCorrect_intColonIntColon(){
        s = "2:3:";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
    @Ignore
    @Test
    public void obslugaTime_checkIfDataIsCorrect_largeNumbers(){
        s = "999999999999999999999999999999999999999999999999999999999999999999999999999999999";
        ma.obslugaTime(s);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
    }
}
