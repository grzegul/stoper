package io.github.grzegul.stoper;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;



public class MainActivity extends AppCompatActivity {

    private EditText editTextLoop, editTextTime, editTextBreak;
    private Button btnStart, btnStop;
    private TextView textViewTime, textViewLoop;

    private int minuty = 0;
    private int sekundy = 0;
    private int interwal = 1;
    private int minutyB = 0;
    private int sekundyB = 0;
    private int loop = 1;
    private CounterClass timer;
    private String counterId = "break";

    public String getCounterId() {
        return counterId;
    }
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }
    public int getMinuty() {
        return minuty;
    }
    public void setMinuty(int minuty) {
        this.minuty = minuty;
    }
    public int getSekundy() {
        return sekundy;
    }
    public void setSekundy(int sekundy) {
        this.sekundy = sekundy;
    }
    public int getMinutyB() {
        return minutyB;
    }
    public void setMinutyB(int minutyB) {
        this.minutyB = minutyB;
    }
    public int getSekundyB() {
        return sekundyB;
    }
    public void setSekundyB(int sekundyB) {
        this.sekundyB = sekundyB;
    }
    public int getInterwal() {
        return interwal;
    }
    public int getLoop() {
        return loop;
    }
    public void setLoop(int loop) {
        this.loop = loop;
    }


    //czytanie okna tekstowego i wpisywanie danych na wyjście
    public String obslugaTime(String s){
        if(s.equals("") || s.equals(":")
                        || s.contains("::")
                        || ((s.split(":")).length>2)
                        || (s.endsWith(":")&&((s.split(":")).length>1))
                        || (s.length()>5)){
            setSekundy(0);
            setMinuty(0);
        }else if(s.startsWith(":") && s.length()>1){
            setSekundy(Integer.valueOf(s.split(":")[1]));
            setMinuty(0);
        }else if(s.endsWith(":")){
            setSekundy(0);
            setMinuty(Integer.valueOf(s.split(":")[0]));
        }else if(s.contains(":")){
            setSekundy(Integer.valueOf(s.split(":")[1]));
            setMinuty(Integer.valueOf(s.split(":")[0]));
        }else if(s.startsWith("0")){    //taki cheat na starsze androidy, gdzie klawiatura nie ma ":"
            setSekundy(Integer.valueOf(s));
            setMinuty(0);
        }else{
            setSekundy(0);
            setMinuty(Integer.valueOf(s));
        }
        return getMinuty()+":"+getSekundy();
    }
    public void obslugaBreak(String s){
        if(s.equals("") || s.equals(":") || s.contains("::")){
            setSekundyB(0);
            setMinutyB(0);
        }else if(s.startsWith(":") && s.length()>1){
            setSekundyB(Integer.valueOf(s.split(":")[1]));
            setMinutyB(0);
        }
        else if(s.endsWith(":")){
            setSekundyB(0);
            setMinutyB(Integer.valueOf(s.split(":")[0]));
        }else if((s.contains(":")) && ((s.split(":")).length>2)){
            setSekundyB(30);
            setMinutyB(0);
        }else if(s.contains(":")){
            setSekundyB(Integer.valueOf(s.split(":")[1]));
            setMinutyB(Integer.valueOf(s.split(":")[0]));

        }else{
            setSekundyB(Integer.valueOf(s));
            setMinutyB(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLoop = (EditText) findViewById(R.id.editTextLoop);
        editTextLoop.setText(String.format("%01d", getLoop()));
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextTime.setText(String.format("%01d:%02d", getMinuty(), getSekundy()));
        editTextBreak = (EditText) findViewById(R.id.editTextBreak);
        editTextBreak.setText(String.format("%01d:%02d", getMinutyB(), getSekundyB()));
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewLoop = (TextView) findViewById(R.id.textViewLoop);


        editTextLoop.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String s1 =  editTextLoop.getText().toString();
                    if((Integer.valueOf(s1)<1)||(Integer.valueOf(s1)>99)){
                        setLoop(1);
                    }else{
                        setLoop(Integer.valueOf(s1));
                    }
                    String data1 = String.format("%01d", getLoop());
                    editTextLoop.setText(data1);
                    editTextLoop.clearFocus();
                    textViewLoop.setText(String.valueOf(getLoop()));
                    btnStart.setEnabled(true);
                }
                return false;
            }
        });
        editTextLoop.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == editTextLoop) {
                    if(hasFocus){
                        btnStart.setEnabled(false);
                        editTextLoop.selectAll();
                    } else if (!hasFocus) {
                        // Close keyboard
                        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                        String s1 =  editTextLoop.getText().toString();
                        if((Integer.valueOf(s1)<1)||(Integer.valueOf(s1)>99)){
                            setLoop(1);
                        }else{
                            setLoop(Integer.valueOf(s1));
                        }
                        String data1 = String.format("%01d", getLoop());
                        editTextLoop.setText(data1);
                        editTextLoop.clearFocus();
                        textViewLoop.setText(String.valueOf(getLoop()));
                        btnStart.setEnabled(true);
                    }
                }
            }
        });
        editTextTime.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String s = editTextTime.getText().toString();
                    obslugaTime(s); //moja metoda
                    String data = String.format("%01d:%02d", getMinuty(), getSekundy());
                    textViewTime.setText(data);
                    editTextTime.setText(data);
                    editTextTime.clearFocus();
                    btnStart.setEnabled(true);
                }
                return false;
            }
        });
        editTextTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == editTextTime) {
                    if(hasFocus){
                        btnStart.setEnabled(false);
                        editTextTime.selectAll();
                    } else if (!hasFocus) {
                        // Close keyboard
                        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                        String s =  editTextTime.getText().toString();
                        obslugaTime(s); //moja metoda
                        String data = String.format("%01d:%02d", getMinuty(), getSekundy());
                        textViewTime.setText(data);
                        editTextTime.setText(data);
                        editTextTime.clearFocus();
                        btnStart.setEnabled(true);
                    }
                }
            }
        });
        editTextBreak.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String s = editTextBreak.getText().toString();
                    obslugaBreak(s); //moja druga metoda
                    String data = String.format("%01d:%02d", getMinutyB(), getSekundyB());
                    editTextBreak.setText(data);
                    editTextBreak.clearFocus();
                    btnStart.setEnabled(true);
                }
                return false;
            }
        });
        editTextBreak.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == editTextBreak) {
                    if(hasFocus){
                        btnStart.setEnabled(false);
                        editTextBreak.selectAll();
                    } else if (!hasFocus) {
                        // Close keyboard
                        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                        String s =  editTextBreak.getText().toString();
                        obslugaBreak(s); //moja druga metoda
                        String data = String.format("%01d:%02d", getMinutyB(), getSekundyB());
                        editTextBreak.setText(data);
                        editTextBreak.clearFocus();
                        btnStart.setEnabled(true);
                    }
                }
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                editTextTime.setEnabled(false);
                editTextLoop.setEnabled(false);
                editTextBreak.setEnabled(false);
                setLoop(Integer.valueOf(editTextLoop.getText().toString()));
                textViewLoop.setText(String.valueOf(getLoop()-1));
                startTimer("time", getLoop());
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                editTextTime.setEnabled(true);
                editTextLoop.setEnabled(true);
                editTextBreak.setEnabled(true);
            }
        });
    }

    public void startTimer(String counterId, int loops){
        if(counterId.equals("break")){
            timer = new CounterClass(getMinutyB() * 60 * 1000 + getSekundyB() * 1000, getInterwal() * 1000);
            setCounterId("time");
            textViewTime.setBackgroundResource(R.color.colorBreak);
            timer.start();
        }else{
            if(loops>0){
                timer = new CounterClass(getMinuty() * 60 * 1000 + getSekundy() * 1000, getInterwal() * 1000);
                textViewLoop.setText(String.valueOf(getLoop()-1));
                setLoop(getLoop()-1);
                setCounterId("break");
                textViewTime.setBackgroundResource(R.color.colorTime);
                timer.start();
            }else{
                timer.cancel();
            }
        }

    }

    // WYKONANIE ODLICZANIA
    public class CounterClass extends CountDownTimer{
        public CounterClass(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished){
            long millis = millisUntilFinished;
            String hms = String.format("%01d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            textViewTime.setText(hms);
        }
        @Override
        public void onFinish(){
            if(getLoop()>0){
                startTimer(getCounterId(), getLoop());
                final MediaPlayer mpShort = MediaPlayer.create(getBaseContext(), R.raw.alarm_short);
                mpShort.start();
            }else{
                textViewTime.setText("Done");
                textViewTime.setBackgroundResource(R.color.colorWhite);
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                editTextTime.setEnabled(true);
                editTextLoop.setEnabled(true);
                editTextBreak.setEnabled(true);
                final MediaPlayer mpLong = MediaPlayer.create(getBaseContext(), R.raw.alarm_long);
                mpLong.start();
            }
        }
    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.about){
            Toast.makeText(getApplicationContext(), "Autor: JG", Toast.LENGTH_LONG).show();
        }else if(id==R.id.performance){
            Toast.makeText(getApplicationContext(), "Wprowadź pętlę, czas główny i czas przerwy", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
