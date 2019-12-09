package com.example.android.projectmessage;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity{
    String numberval, msgval,timeval;
    TimePicker timepicker;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      timepicker=(TimePicker)findViewById(R.id.timePicker);
        if (DateFormat.is24HourFormat(this))
             timepicker.setIs24HourView(true);
    }

    public void getnum() {
        EditText text = (EditText) findViewById(R.id.textView2);
        numberval = text.getText().toString();
    }

    public void getmsg() {
        EditText text = (EditText) findViewById(R.id.textView4);
        msgval = text.getText().toString();
    }
    public void getTime1(){
        int h1=timepicker.getCurrentHour();
        int m1= timepicker.getCurrentMinute();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String currentTime = sdf.format(new Date());
        int h2=Integer.parseInt(currentTime.substring(0,2));
        int m2=Integer.parseInt(currentTime.substring(2,4));
        int wt=((h1-h2)*60+(m1-m2))*60000;
        TextView tv=(TextView) findViewById(R.id.time_viewer);
        timeval=h1+" "+m1+" "+h2+" "+m2;
        tv.setText(timeval);
        if(h1>=h2){
        new CountDownTimer(wt, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                callmsg();

            }
        }.start();}
        else{
            Toast.makeText(getApplicationContext(),"Time you set is already over in this day",Toast.LENGTH_SHORT).show();
        }

    }
    public void callmsg(){
        String toNumber = numberval;
        toNumber = toNumber.replace("+", "").replace(" ", "");

        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
        sendIntent.putExtra(Intent.EXTRA_TEXT, msgval);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void submit(View v) {
       getnum();
        getmsg();
       getTime1();


    }
}
