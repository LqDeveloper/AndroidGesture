package com.example.gesturedemo;

import android.Manifest;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecognizeActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {
    GestureOverlayView gestureOverlayView;
    ImageView imageView;
    Gesture gesture;
    private GestureLibrary gestureLibrary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize);
        gestureOverlayView = findViewById(R.id.gesture);
        imageView = findViewById(R.id.imageView);
        gestureOverlayView.addOnGesturePerformedListener(this);
        gestureLibrary = GestureLibraries.fromFile(getExternalCacheDir().getAbsolutePath()+"/gesture");
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        this.gesture = gesture;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x123);
        }
        List<Prediction> predictions = gestureLibrary.recognize(gesture);
        List<String> list = new ArrayList<>();
        for (Prediction prediction : predictions){
            if (prediction.score >=2.0){
                list.add("与手势【"+prediction.name+"】相似度为 " + prediction.score);
            }
        }
        if (list.size() > 0){
            Toast.makeText(this,list.get(0),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x123){
            Bitmap bitmap = gesture.toBitmap(200,100,10,-0x10000);
            imageView.setImageBitmap(bitmap);
//            GestureLibrary gestureLibrary = GestureLibraries.fromFile(getExternalCacheDir().getAbsolutePath()+"/gesture");
//            gestureLibrary.addGesture("myGesture",gesture);
//            gestureLibrary.save();
        }
    }
}
