package io.github.grzegul.stoper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private Button btnOK, btnStart, btnStop;
    private TextView textViewTime;
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

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOK = (Button) findViewById(R.id.btnOK);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setText("hh:mm:ss");

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
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
                textViewTime.setText(getGodziny()+":"+getMinuty()+":"+getSekundy());

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer = new CounterClass(getGodziny()*60*60*1000+getMinuty()*60*1000+getSekundy()*1000, getInterwal());
                timer.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
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
        }
    }
}
