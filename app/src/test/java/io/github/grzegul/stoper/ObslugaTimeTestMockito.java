package io.github.grzegul.stoper;

/**
 * Created by jgrzegulski on 2016-09-09.
 */

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.mock.MockContext;
import android.widget.EditText;

@RunWith(MockitoJUnitRunner.class)
public class ObslugaTimeTestMockito {

    private static final String FAKE_STRING = "HELLO WORLD";
    private EditText et;
    MainActivity ma;
    private String expected;

    @Before
    public void  setUp(){
        expected =  "1:0";
        ma = new MainActivity();
        et = mock(EditText.class);
    }

    @Test
    public void obslugaTimeTest() {
        //when(et.getText().toString()).thenReturn("1:00");
        //ma.obslugaTime(et);
        assertEquals(expected, ma.getMinuty()+":"+ma.getSekundy());
        //MainActivity test = mock(MainActivity.class);
        // Given a mocked Context injected into the object under test...
        //when(mMockContext.getString(R.string.timeString)).thenReturn(FAKE_STRING);
        //when(test.obslugaTime().thenReturn(ma.getMinuty()));
        //test.obslugaTime(et);
        // ...when the string is returned from the object under test...
        //String result = MainActivity.obslugaTime(et);

        // ...then the result should be the expected one.
        //assertThat(result, is(FAKE_STRING));

        //ma.obslugaTime(et);
        //assertEquals(expected, test.getMinuty()+":"+test.getSekundy());
    }
}