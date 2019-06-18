package com.example.gesturedemo;

import android.Manifest;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SaveActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener{
    GestureOverlayView gestureOverlayView;
    ImageView imageView;
    Gesture gesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        gestureOverlayView = findViewById(R.id.gesture);
        imageView = findViewById(R.id.imageView);
        gestureOverlayView.addOnGesturePerformedListener(this);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
         this.gesture = gesture;
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
             requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x123);
         }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x123){
            Bitmap bitmap = gesture.toBitmap(200,100,10,-0x10000);
            imageView.setImageBitmap(bitmap);
            GestureLibrary gestureLibrary = GestureLibraries.fromFile(getExternalCacheDir().getAbsolutePath()+"/gesture");
            gestureLibrary.addGesture("myGesture",gesture);
            gestureLibrary.save();
        }
    }
}
