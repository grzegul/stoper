package io.github.grzegul.stoper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    Button btnStart, btnStop;
    TextView textViewTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        textViewTime.setText("00:00:00");


        String s =  editTextTime.getText().toString();
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
        try {
            Date date = formatter.parse(s);
            textViewTime.setText((CharSequence) date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        int milisekundy = 5000;
        int interwal = 1000;
        final CounterClass timer = new CounterClass(milisekundy, interwal);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        @Override
        public void onFinish(){
            textViewTime.setText("Done");
        }
    }
}
