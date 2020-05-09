package com.yasharth.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class frontActivity extends AppCompatActivity {

    private LottieAnimationView anim;
    private TextView splTxt, createName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        anim = findViewById(R.id.json_anim);
        splTxt = findViewById(R.id.covid_txt);
        createName = findViewById(R.id.creator_name);

        Animation frontAnim = AnimationUtils.loadAnimation(this,R.anim.myanim);

        anim.startAnimation(frontAnim);
        splTxt.startAnimation(frontAnim);
        createName.startAnimation(frontAnim);

        final Intent i = new Intent(this,MainActivity.class);
        Thread time = new Thread()
        {
            public void run (){
                try {
                    sleep(5000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        time.start();
    }
}
