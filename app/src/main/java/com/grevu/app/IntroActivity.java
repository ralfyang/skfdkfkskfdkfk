package com.grevu.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.grevu.app.view.LoginActivity;

public class IntroActivity extends Activity {

    final static String TAG = "IntroActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        //인트로 테스트
        //추후 데이터 로딩 예정
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    @Override
    public void onResume() {

        super.onResume();
        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
