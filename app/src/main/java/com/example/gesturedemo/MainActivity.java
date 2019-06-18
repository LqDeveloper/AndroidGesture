package com.example.gesturedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       textView1 = findViewById(R.id.addGesture);
       textView1.setOnClickListener(this);

        textView2 = findViewById(R.id.saveGesture);
        textView2.setOnClickListener(this);

        textView3 = findViewById(R.id.recognizeGesture);
        textView3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case  R.id.addGesture:
                intent = new Intent(MainActivity.this,GestureActivity.class);
                startActivity(intent);
                break;
            case  R.id.saveGesture:
                intent = new Intent(MainActivity.this,SaveActivity.class);
                startActivity(intent);
                break;
            case  R.id.recognizeGesture:
                intent = new Intent(MainActivity.this,RecognizeActivity.class);
                startActivity(intent);
                break;
        }


    }
}
