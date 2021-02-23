package com.raghu.loadmore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.raghu.loadmore.MainActivity;
import com.raghu.loadmore.R;
import com.sarnava.textwriter.TextWriter;

public class SplashActivity extends AppCompatActivity {
    TextWriter textWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textWriter = findViewById(R.id.textWriter);
        textWriter
                .setWidth(12)
                .setDelay(30)
                .setColor(Color.RED)
                .setConfig(TextWriter.Configuration.INTERMEDIATE)
                .setSizeFactor(30f)
                .setLetterSpacing(25f)
                .setText("LOAD MORE APP")
                .setListener(new TextWriter.Listener() {
                    @Override
                    public void WritingFinished() {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            }
                        }, 3000);
                    }
                })
                .startAnimation();
    }
}