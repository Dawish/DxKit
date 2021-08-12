package com.dxkit.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dxkit.demo.utils.NetUtils;
import com.dxkit.library.utils.DxLog;

public class MainActivity extends AppCompatActivity {


    private TextView mNetStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNetStatus = findViewById(R.id.net_status);

        int netType = NetUtils.getNetworkState(MainActivity.this);

        mNetStatus.setText("网类型：" + netType);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hello click", Toast.LENGTH_SHORT).show();
            }
        });

        DxLog.d("DXX", "Netwrok type:" + getNetworkTypeTest());

    }

    public int getNetworkTypeTest() {
        TelephonyManager telephonyManager = (TelephonyManager) MainActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getNetworkType();
    }

}