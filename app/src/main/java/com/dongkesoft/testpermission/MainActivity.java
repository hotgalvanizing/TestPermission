package com.dongkesoft.testpermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.call_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int check = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

                if (check == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            } else {
                Toast.makeText(MainActivity.this, "没有拨打电话权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void call() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel://1212121212"));
        intent.setAction(Intent.ACTION_CALL);
        startActivity(intent);
    }

}
