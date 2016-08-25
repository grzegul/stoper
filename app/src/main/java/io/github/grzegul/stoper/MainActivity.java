package io.github.grzegul.stoper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnStop;
    private TextView textViewTime;
    private EditText editTextTime;
    private int godziny = 60*60*1000;
    private int minuty = 60*1000;
    private int sekundy = 1000;
    private int interwal = 1000;
    private CounterClass timer;

    public int getGodziny() {
        return godziny/(60*60*1000);
    }
    public void setGodziny(int godziny) {
        this.godziny = godziny*60*60*1000;
    }
    public int getMinuty() {
        return minuty/(60*1000);
    }
    public void setMinuty(int minuty) {
        this.minuty = minuty*60*1000;
    }
    public int getSekundy() {
        return sekundy/1000;
    }
    public void setSekundy(int sekundy) {
        this.sekundy = sekundy*1000;
    }
    public int getInterwal() {
        return interwal;
    }
    public void setInterwal(int interwal) {
        this.interwal = interwal;
    }
    public void dataRead(EditText editTextTime){
        String s =  editTextTime.getText().toString();
        if((s.contains(":")) && ((s.split(":")).length>3)){
            setSekundy(0);
            setMinuty(0);
            setGodziny(0);
        } else if((s.contains(":")) && ((s.split(":")).length>2)){
            setSekundy(Integer.valueOf(s.split(":")[2]));
            setMinuty(Integer.valueOf(s.split(":")[1]));
            setGodziny(Integer.valueOf(s.split(":")[0]));
        }else if(s.contains(":")){
            setSekundy(Integer.valueOf(s.split(":")[1]));
            setMinuty(Integer.valueOf(s.split(":")[0]));
            setGodziny(0);

        }else{
            setSekundy(0);
            setMinuty(Integer.valueOf(s));
            setGodziny(0);
        }
    }   //czytanie okna tekstowego
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alarm_short);


        editTextTime.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    dataRead(editTextTime); //moja metoda
                    textViewTime.setText(getGodziny()+":"+getMinuty()+":"+getSekundy());
                    editTextTime.setText(getGodziny()+":"+getMinuty()+":"+getSekundy());
                    handled = false;
                }
                return handled;
            }
        });
        //TEN LISTENER ZDAJE SIĘ ANULOWAĆ PRZYCISK STOP
        editTextTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == editTextTime) {
                    if (!hasFocus) {
                        // Close keyboard
                        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
                                .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                    }
                }
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer = new CounterClass(getGodziny()*60*60*1000+getMinuty()*60*1000+getSekundy()*1000, getInterwal());
                timer.start();
//                mp.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
//                mp.start();
            }
        });
    }


    public class CounterClass extends CountDownTimer{
        public CounterClass(long millisInFuture, long countDownInterval){
                super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished){
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
//            Log.d("debuggowanie", hms);
 //           System.out.println(hms);
            textViewTime.setText(hms);
        }

        @Override
        public void onFinish(){
            textViewTime.setText("Done");
            final MediaPlayer mp2 = MediaPlayer.create(getBaseContext(), R.raw.alarm_short);
            mp2.start();
        }
    }
}
