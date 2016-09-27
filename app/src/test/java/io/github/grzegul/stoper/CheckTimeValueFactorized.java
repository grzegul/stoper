package io.github.grzegul.stoper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by jgrzegulski on 2016-09-08.
 */
@RunWith(Parameterized.class)
public class CheckTimeValueFactorized {
    MainActivity ma = new MainActivity();
    private String expected;
    private String actual;

    public CheckTimeValueFactorized(String expected, String actual) {
        this.expected = expected;
        this.actual = actual;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        Object [][] expectedOutputs = {
                {"21:15", "21:15"},
                {"0:15", ":15"},
                {"2:0", "2:"},
                {"0:0", "00:00"},
                {"2:1", "02:01"},
                {"0:5", "05"},
                {"5:0", "5"},
                {"0:0", ""},
                {"0:0", ":"},
                {"0:0", "::"},
                {"0:0", ":::"},
                {"0:0", "::::"},
                {"0:0", "3:3:3"},
                {"0:0", ":3:3"},
                {"0:0", "2:3:"},
                {"0:0", "999999999999999999999999999999999999999999999999999999999999999999999999999999999"}};

        return Arrays.asList(expectedOutputs);
    }


    @Test
    public void obslugaTime_checkIfDataIsCorrect_acceptedData(){
        assertEquals(expected, ma.checkTimeValue(actual));
    }

}
