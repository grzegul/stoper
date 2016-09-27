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
public class CheckLoopValueFactorized {
    MainActivity ma = new MainActivity();
    private int expected;
    private String actual;

    public CheckLoopValueFactorized(int expected, String actual) {
        this.expected = expected;
        this.actual = actual;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        Object [][] expectedOutputs = {
                {3, "3"},
                {1, "100"},
                {1, "999999999999999999999999999999999999999999999999999999999999999999999999999999999"}};
        return Arrays.asList(expectedOutputs);
    }


    @Test
    public void obslugaTime_checkIfDataIsCorrect_acceptedData(){
        assertEquals(expected, ma.chceckLoopValue(actual));
    }

}
