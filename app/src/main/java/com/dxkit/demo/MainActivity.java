package com.dxkit.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dx.tracer.util.NetUtil;
import com.dxkit.demo.test.CrashTest;
import com.dxkit.library.utils.DxLog;


public class MainActivity extends AppCompatActivity {


    private TextView mNetStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNetStatus = findViewById(R.id.net_status);

        int netType = NetUtil.getNetworkType(MainActivity.this);

        mNetStatus.setText("网类型：" + netType);

        initListener();
    }

    private void initListener() {
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hello click", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.crash1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DxLog.d("DXX", "crash1: " + CrashTest.crashMethod1());
            }
        });

        findViewById(R.id.crash2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashTest.crashMethod2();
            }
        });


        findViewById(R.id.getCrashStr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DxLog.d("DXX", "getCrashString: " + NetUtil.getCrashString());
            }
        });
    }

}